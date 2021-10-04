package com.liu.webui.client.webui.model.element;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Node;
import com.liu.webui.client.webui.model.NodeFunction;

public class CWebUI_Button_Element extends CWebUI_Abstract_Element {
		
	/**
	 * constructor,set element type
	 */
	public CWebUI_Button_Element() {
		super.setElementtype("button");
		super.setTargetElementType("Button");
	}

	@Override
	public void readLocalNode(Node node) {
		int ileft = NodeFunction.readIntAttribute(node, "left");
		int itop = NodeFunction.readIntAttribute(node, "top");
		int iwidth = NodeFunction.readIntAttribute(node, "width");
		int iheight = NodeFunction.readIntAttribute(node, "height");
		this.setBounds(ileft, itop, iwidth, iheight);
		
		this.setText(NodeFunction.readStringAttribute(node, "value"));
	}

	@Override
	public Widget createLocalWidge() {
		Button button = new Button();
		button.setText(this.getText());
		
		return button;
	}

}
