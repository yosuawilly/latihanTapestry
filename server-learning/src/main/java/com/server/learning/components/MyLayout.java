package com.server.learning.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.Response;

import com.server.learning.services.Authenticator;

public class MyLayout {
	
	@Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;
	
	@Inject
	private ComponentResources componentResources;
	
	@InjectService(value = "Authenticator")
	private Authenticator authenticator;
	
	@Inject
	private Response response;
	
	void setupRender() {
		response.setHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
	    response.setHeader("Pragma","No-Cache");
	}
	
	public String getBabCssClass() {
		if("Bab".equals(componentResources.getPageName())) {
			return "active";
		} else return "";
	}
	
	public String getHomeCssClass() {
		if("Home".equals(componentResources.getPageName())) {
			return "active";
		} else return "";
	}
	
	public String getMateriCssClass() {
		if("Materi".equals(componentResources.getPageName())) {
			return "active";
		} else return "";
	}
	
	public String getTugasCssClass() {
		if("Tugas".equals(componentResources.getPageName())) {
			return "active";
		} else return "";
	}
	
	public String getUploadTugasCssClass() {
		if("UploadTugas".equals(componentResources.getPageName())) {
			return "active";
		} else return "";
	}
	
	public String getQuizCssClass() {
		if("Quiz".equals(componentResources.getPageName())) {
			return "active";
		} else return "";
	}
	
	public String getSiswaCssClass() {
		if("Siswa".equals(componentResources.getPageName())) {
			return "active";
		} else return "";
	}
	
	public Object onActionFromLogout() {
		authenticator.logout();
		return "Login";
	}

}