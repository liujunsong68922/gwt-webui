package com.liu.webui.client.webui.model.container;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Node;
import com.liu.webui.client.webui.model.NodeFunction;

public class CWebUIAbsolutePanel extends CWebUI_AbstractPanel {

	/**
	 * Absolute Layout Panel
	 */
	public CWebUIAbsolutePanel() {
		super.setPaneltype("absolutepanel");
		super.setTargetPanelType("AbsolutePanel");
	}

	@Override
	public void readLocalNode(Node node) {
		int ileft = NodeFunction.readIntAttribute(node, "left");
		int itop = NodeFunction.readIntAttribute(node, "top");
		int iwidth = NodeFunction.readIntAttribute(node, "width");
		int iheight = NodeFunction.readIntAttribute(node, "height");
		this.setBounds(ileft, itop, iwidth, iheight);

		this.setTitle(NodeFunction.readStringAttribute(node, "title"));
	}

	@Override
	public Widget createLocalWidge() {
		AbsolutePanel pp = new AbsolutePanel();
		
		pp.setTitle(this.getTitle());
		return pp;
	}
}
