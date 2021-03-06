package com.server.learning.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.*;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.ObjectLocator;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;

import com.learning.engine.BusinessException;
import com.learning.engine.json.PojoJsonMapper;
import com.server.learning.security.SecurityFilter;
import com.server.learning.services.impl.AuthenticationResourceImpl;
import com.server.learning.services.impl.AuthenticatorImpl;
import com.server.learning.services.impl.LearningResourceImpl;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);
        
        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    	binder.bind(AuthenticationResource.class, AuthenticationResourceImpl.class);
    	binder.bind(Authenticator.class, AuthenticatorImpl.class);
    	binder.bind(LearningResource.class, LearningResourceImpl.class);
    }
    
    public static void contributeApplication(Configuration<Object> singletons, ObjectLocator locator) {
    	singletons.add(locator.getService(AuthenticationResource.class));
    	singletons.add(locator.getService(LearningResource.class));
    }
    
    @Match("*Resource")
    public static void adviseException(MethodAdviceReceiver receiver) {
    	MethodAdvice advice = new MethodAdvice() {
			@Override
			public void advise(MethodInvocation invocation) {
				HttpServletRequest context = (HttpServletRequest) invocation.getParameter(0);
                BusinessException be = null;
                try {
                    invocation.proceed();
                } catch (org.jboss.resteasy.spi.UnhandledException e) {
                } catch (BusinessException e) {
                    e.printStackTrace();
                    be = e;
                } catch (Exception e) {
                    e.printStackTrace();
                    be = new BusinessException("LS-0600");
                }
                
                if (be != null) {
                	if ("ind".equals(context.getHeader("Accept-Language")) || "id".equals(context.getHeader("Accept-Language"))) {
                		be.translateMessage(context.getHeader("Accept-Language"));
                	}
                    Object result = PojoJsonMapper.toJson(be);
                    invocation.setReturnValue(result);
                }
			}
		};
		
		receiver.adviseAllMethods(advice);
    }
    
    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");

        // The factory default is true but during the early stages of an application
        // overriding to false is a good idea. In addition, this is often overridden
        // on the command line as -Dtapestry.production-mode=false
        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");

        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions.
        configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0.0-LEARNING");
    }

    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Logger instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     * 
     * <p>
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead. 
     * 
     * <p>
     * If this method was named "build", then the service id would be taken from the 
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */    
    public RequestFilter buildTimingFilter(final Logger log)
    {
        return new RequestFilter()
        {
            public boolean service(Request request, Response response, RequestHandler handler)
                    throws IOException
            {
                long startTime = System.currentTimeMillis();

                try
                {
                    // The responsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.
                    
                    return handler.service(request, response);
                }
                finally
                {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info(String.format("Request time: %d ms", elapsed));
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    /*public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
            @Local
            RequestFilter filter)
    {*/
    public static void contributeComponentRequestHandler(
            OrderedConfiguration<ComponentRequestFilter> configuration)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.
        
        //configuration.add("Timing", filter);
    	configuration.addInstance("RequiresLogin", SecurityFilter.class);
    }
    
    public static void contributeComponentClassResolver(
            Configuration<LibraryMapping> configuration) {
        configuration.add(
            new LibraryMapping("", "com.server.learning"));
   }
}
