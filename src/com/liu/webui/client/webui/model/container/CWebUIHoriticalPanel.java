package com.liu.webui.client.webui.model.container;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Node;
import com.liu.webui.client.webui.model.NodeFunction;

public class CWebUIHoriticalPanel extends CWebUI_AbstractPanel {

	public CWebUIHoriticalPanel() {
		super.setPaneltype("horiticalpanel");
		super.setTargetPanelType("HorizalPanel");
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
		HorizontalPanel pp = new HorizontalPanel();
		return pp;
	}


}
