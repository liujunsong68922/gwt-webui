package com.liu.webui.client.webui.model.element;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Node;
import com.liu.webui.client.webui.model.NodeFunction;

public class CWebUI_Image_Element extends CWebUI_Abstract_Element {
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * constructor
	 */
	public CWebUI_Image_Element() {
		super.setElementtype("picture");
		super.setTargetElementType("Picture");
	}

	@Override
	public void readLocalNode(Node node) {
		int ileft = NodeFunction.readIntAttribute(node, "left");
		int itop = NodeFunction.readIntAttribute(node, "top");
		int iwidth = NodeFunction.readIntAttribute(node, "width");
		int iheight = NodeFunction.readIntAttribute(node, "height");
		this.setBounds(ileft, itop, iwidth, iheight);
		
		this.url = NodeFunction.readStringAttribute(node, "url");
		
	}

	@Override
	public Widget createLocalWidge() {
		Image pict = new Image();
		pict.setUrl(this.url);
		return pict;
	}


}
