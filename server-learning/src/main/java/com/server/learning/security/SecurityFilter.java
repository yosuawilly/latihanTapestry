package com.server.learning.security;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;

import com.server.learning.annotations.PublicPage;
import com.server.learning.pages.Home;
import com.server.learning.pages.Index;
import com.server.learning.pages.Login;
import com.server.learning.services.Authenticator;

public class SecurityFilter implements ComponentRequestFilter{
	
	private final PageRenderLinkSource renderLinkSource;
	private final ComponentSource componentSource;
	private final Response response;
	private final Request request;
	private final RequestGlobals requestGlobals;
	private final Authenticator authenticator;
	
	private String homePage = Home.class.getSimpleName();
	private String loginPage = Login.class.getSimpleName();
	
	public SecurityFilter(PageRenderLinkSource renderLinkSource, ComponentSource componentSource,
			Response response, Request request, RequestGlobals requestGlobals, Authenticator authenticator) {
		this.renderLinkSource = renderLinkSource;
		this.componentSource = componentSource;
		this.response = response;
		this.request = request;
		this.requestGlobals = requestGlobals;
		this.authenticator = authenticator;
	}

	@Override
	public void handleComponentEvent(
			ComponentEventRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {
        if(dispatchedToLoginPage(parameters.getActivePageName())) return;
		
		handler.handleComponentEvent(parameters);
	}

	@Override
	public void handlePageRender(PageRenderRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {
		if(dispatchedToLoginPage(parameters.getLogicalPageName())) return;
		
		handler.handlePageRender(parameters);
	}
	
	private boolean dispatchedToLoginPage(String pageName) throws IOException{
		if(authenticator.isLoggedIn()){
			if(pageName.equalsIgnoreCase(loginPage) || pageName.equalsIgnoreCase(Index.class.getSimpleName())){
				Link link = renderLinkSource.createPageRenderLink(homePage);
				response.sendRedirect(link);
				return true;
			}
			return false;
		}
		
		Component page = componentSource.getPage(pageName);
		
		if(page.getClass().isAnnotationPresent(PublicPage.class)) return false;
		
		Link link = renderLinkSource.createPageRenderLink("Login");
		response.sendRedirect(link);
		
		return true;
	}

}
