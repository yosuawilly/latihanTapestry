package com.server.learning.pages.bab;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import com.learning.engine.service.BabService;
import com.server.learning.base.BasePage;

public class Bab extends BasePage{
	
	@Inject
	private BabService babService;
	
	@Property
    private int loopIndex;
	
	@Property
	private com.learning.engine.model.Bab bab;
	
	@Property
	private List<com.learning.engine.model.Bab> babList;
	
	void setupRender() {
		babList = babService.getAll();
	}
	
	void onDelete(long babId) {
		System.out.println(String.valueOf(babId));
		babService.remove(babId);
	}
	
}