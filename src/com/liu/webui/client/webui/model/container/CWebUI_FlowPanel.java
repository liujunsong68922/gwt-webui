package com.liu.webui.client.webui.model.container;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.liu.webui.client.webui.model.NodeFunction;

public class CWebUI_FlowPanel extends CWebUI_AbstractPanel {

	private String paneltype = "flowpanel";
	private String targetPanelType = "FlowPanel";

	/**
	 * RootPanel is generated by DOM itself. So that the RootPanel is the entry
	 * point of WebUI Application.
	 */
	public CWebUI_FlowPanel() {

	}

	@Override
	public void readLocalNode(Node node) {
		int ileft = NodeFunction.readIntAttribute(node, "left");
		int itop = NodeFunction.readIntAttribute(node, "top");
		int iwidth = NodeFunction.readIntAttribute(node, "width");
		int iheight = NodeFunction.readIntAttribute(node, "height");
		this.setBounds(ileft, itop, iwidth, iheight);
	}

	@Override
	public Widget createLocalWidge() {
		FlowPanel pp = new FlowPanel();
		
		//width
		if(super.getWidth()>0) {
			pp.setWidth(super.getWidth()+"px");
		}
		//height
		if(super.getHeight()>0) {
			pp.setHeight(super.getHeight()+"px");
		}
		
		return pp;
	}


}
