package com.liu.webui.client.webui.model.element;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.Text;
import com.liu.webui.client.webui.model.NodeFunction;

public class CWebUI_Html_Element extends CWebUI_Abstract_Element {
		
	/**
	 * constructor,set element type
	 */
	public CWebUI_Html_Element() {
		super.setElementtype("html");
		super.setTargetElementType("Html");
	}

	@Override
	public void readLocalNode(Node node) {
		int ileft = NodeFunction.readIntAttribute(node, "left");
		int itop = NodeFunction.readIntAttribute(node, "top");
		int iwidth = NodeFunction.readIntAttribute(node, "width");
		int iheight = NodeFunction.readIntAttribute(node, "height");
		this.setBounds(ileft, itop, iwidth, iheight);
		
		Node textnode =node.getFirstChild();
		String stext = "";
		if(textnode instanceof Text) {
			Text t1 = (Text)textnode;
			stext = t1.getNodeValue();
		}
		
		this.setText(stext);
	}

	@Override
	public Widget createLocalWidge() {
		HTML html = new HTML();
		html.setText(this.getText());
		
		return html;
	}

}
