package com.server.learning.base;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;

public class BasePage {
	
	@Inject
	private Context tapestryContext;
	
	@Inject
	private ComponentSource componentSource;
	
	@Inject
	private ComponentResources componentResources;
	
	@Inject
	private Messages messages;
	
	@Inject
	private Request request;
	
	public Context getTapestryContext() {
		return tapestryContext;
	}
	
	public ComponentSource getComponentSource() {
		return componentSource;
	}
	
	public ComponentResources getComponentResources() {
		return componentResources;
	}
	
	public Messages getMessages() {
		return messages;
	}
	
	public Request getRequest() {
		return request;
	}
	
	protected void discardPersistentFieldChanges() {
		componentResources.discardPersistentFieldChanges();
	}

}
