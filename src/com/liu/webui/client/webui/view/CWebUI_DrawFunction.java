package com.liu.webui.client.webui.view;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webui.client.webui.model.CWebUI_AbstractUI;
import com.liu.webui.client.webui.model.CWebUI_RootFlowPanel;

public class CWebUI_DrawFunction {
	/**
	 * input webui_rootpanel, output :rootpanel
	 * 
	 * @param rootpanel
	 * @param webui_rootpanel
	 */
	public void draw(RootPanel rootpanel, CWebUI_RootFlowPanel webui_rootpanel) {
		consoleLog("[CWebUI_DrawFunction]enter draw function.");
		//Window.alert("enter draw function");
		
		this.clean(rootpanel);
		
		Widget rootwidget = webui_rootpanel.createWidget();

		if (rootwidget != null) {
			rootpanel.add(rootwidget);
			consoleLog("---->Add Root Widget OK.");
		} else {
			consoleLog("---->Root Widget is null.");

		}
	}
	
	private void clean(RootPanel rootpanel) {
		int icount = rootpanel.getWidgetCount();
		Window.alert(""+icount);
		for(int i=icount-1;i>=0;i--) {
			rootpanel.remove(i);
		}
	}

	private native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "CWebDWUI_ParentDW:" + message );
													}-*/;
}
