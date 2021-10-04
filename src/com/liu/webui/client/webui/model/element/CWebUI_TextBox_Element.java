package com.liu.webui.client.webui.model.element;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Node;
import com.liu.webui.client.webui.model.NodeFunction;

public class CWebUI_TextBox_Element extends CWebUI_Abstract_Element {

	/**
	 * constructor
	 */
	public CWebUI_TextBox_Element() {
		super.setElementtype("textbox");
		super.setTargetElementType("TextBox");
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
		TextBox textbox = new TextBox();
		
		textbox.setText(getText());
		return textbox;
	}

}
