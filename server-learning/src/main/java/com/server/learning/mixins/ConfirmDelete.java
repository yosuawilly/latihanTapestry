package com.server.learning.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library="ConfirmDelete.js")
public class ConfirmDelete {
	
	@Parameter(name="message", value = "Are you sure?", defaultPrefix = BindingConstants.LITERAL)
	private String message;
	
	@Inject 
	private JavaScriptSupport js;

	@InjectContainer
	private ClientElement clientElement;
	
	@AfterRender
	void afterRender() {
		JSONObject spec = new JSONObject();
        spec.put("elementId", clientElement.getClientId());
        spec.put("message", message);
        js.addInitializerCall("confirm", spec);
		//js.addScript("new ConfirmDelete('%s', '%s');", clientElement.getClientId(), this.message);
		//js.addInitializerCall("confirmation", new JSONObject("id", this.clientElement.getClientId(), "message",  this.message));
	}
	
}
