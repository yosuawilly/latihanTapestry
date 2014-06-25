package com.server.learning.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import com.server.learning.annotations.PublicPage;
import com.server.learning.services.Authenticator;

@PublicPage
public class Login {
	
	@Property
    private String username;

    @Property
    private String password;
	
	@Persist("flash")
    @Property
    private String error;
	
	@Inject
    private Request request;
	
	@Inject
    private Response response;
	
	@InjectPage
	private Index index;
	
	@InjectPage
	private Home home;
	
	private Object nextPage;
	
	@Inject
    private RequestGlobals requestGlobals;
	
	@InjectService(value = "Authenticator")
	private Authenticator authenticator;
	
	@Inject
    private JavaScriptSupport javaScriptSupport;
	
	void setupRender() {
    	response.setHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
	    response.setHeader("Pragma","No-Cache"); 
	    if (error != null) {
	    	javaScriptSupport.addScript(InitializationPriority.LATE, "alert('"+ error + "')");
	    }
    }
	
	public void onValidateFromForm() {
		if(username==null || password==null) {
			error = "Username of password required";
		} else {
			if(authenticator.login(username, password)) {
				nextPage = home;
			} else {
				error = "Username or password invalid";
			}
		}
	}
	
	public Object onSuccessFromForm() {
		return nextPage;
	}

}