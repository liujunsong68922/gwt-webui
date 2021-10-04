package com.liu.webui.client.webui.model.container;

import java.util.ArrayList;

import com.liu.webui.client.webui.model.CWebUI_AbstractUI;

public abstract class CWebUI_AbstractPanel extends CWebUI_AbstractUI {

	private String paneltype;
	private String targetPanelType;

	public String getPaneltype() {
		return paneltype;
	}

	public void setPaneltype(String paneltype) {
		this.paneltype = paneltype;
	}

	public String getTargetPanelType() {
		return targetPanelType;
	}

	public void setTargetPanelType(String targetPanelType) {
		this.targetPanelType = targetPanelType;
	}

}
