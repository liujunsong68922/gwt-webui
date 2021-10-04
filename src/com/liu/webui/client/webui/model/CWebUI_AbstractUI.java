package com.liu.webui.client.webui.model;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.liu.webui.client.webui.model.container.CWebUIHoriticalPanel;
import com.liu.webui.client.webui.model.container.CWebUIVerticalPanel;
import com.liu.webui.client.webui.model.container.CWebUI_FlowPanel;
import com.liu.webui.client.webui.model.element.CWebUI_Button_Element;
import com.liu.webui.client.webui.model.element.CWebUI_Html_Element;
import com.liu.webui.client.webui.model.element.CWebUI_Label_Element;
import com.liu.webui.client.webui.model.element.CWebUI_Image_Element;
import com.liu.webui.client.webui.model.element.CWebUI_TextBox_Element;

public abstract class CWebUI_AbstractUI extends CWebUI_AbstractCommonUI {
	private ArrayList<CWebUI_AbstractUI> children = new ArrayList<CWebUI_AbstractUI>();
	
	// string title
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<CWebUI_AbstractUI> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<CWebUI_AbstractUI> children) {
		this.children = children;
	}

	// this function to read node information to fullfill this object
	public abstract void readLocalNode(Node node);

	// this function is used to create target Widget object.
	public abstract Widget createLocalWidge();
	
	public void readNode(Node node) {
		consoleLog("---->enter readNode");

		// step1:read current level node data
		// from node, to this object
		String nodename = node.getNodeName();

		// check wheath the nodename value is valid.
		// if it is,then read node value to this object.
		// there is a one-level operation
		if (_isValidNodeName(nodename)) {
			this.readLocalNode(node);
		} else {
			Window.alert("Unknown nodename:" + nodename);
			System.out.println("Unknown nodename.");
			return;
		}

		// 2.read node childvalue
		NodeList childnodes = node.getChildNodes();
		if (childnodes.getLength() > 0) {
			for (int index = 0; index < childnodes.getLength(); index++) {
				Node childnode = childnodes.item(index);
				// create child UI element according childnode nodename
				String nodename2 = childnode.getNodeName();
				CWebUI_AbstractUI uichild = this._createByNodename(nodename2);
				if (uichild != null) {
					this.getChildren().add(uichild);

					// call this function in loop way.
					uichild.readNode(childnode);
				}
			}
		}
	};

	private boolean _isValidNodeName(String nodename) {
		return true;
	}

	private CWebUI_AbstractUI _createByNodename(String nodename) {
		if (nodename.equalsIgnoreCase("root")) {
			return new CWebUI_RootFlowPanel();
		}
		if (nodename.equalsIgnoreCase("rootpanel")) {
			return new CWebUI_RootFlowPanel();
		}
		if (nodename.equalsIgnoreCase("flowpanel")) {
			return new CWebUI_FlowPanel();
		}
		if (nodename.equalsIgnoreCase("horiticalpanel")) {
			return new CWebUIHoriticalPanel();
		}
		if (nodename.equalsIgnoreCase("verticalpanel")) {
			return new CWebUIVerticalPanel();
		}
		if (nodename.equalsIgnoreCase("label")) {
			return new CWebUI_Label_Element();
		}
		if (nodename.equalsIgnoreCase("textbox")) {
			return new CWebUI_TextBox_Element();
		}
		if (nodename.equalsIgnoreCase("picture")) {
			return new CWebUI_Image_Element();
		}
		if (nodename.equalsIgnoreCase("button")) {
			return new CWebUI_Button_Element();
		}
		if (nodename.equalsIgnoreCase("html")) {
			return new CWebUI_Html_Element();
		}
		if (nodename.equalsIgnoreCase("image")) {
			return new CWebUI_Image_Element();
		}
		return null;
	}

	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "[CWebUI_AbstractUI]:\r\n" + message );
													}-*/;

	public native String toJsonStrig(Object obj) /*-{
													//alert(message);
													return JSON.stringify(obj);
													}-*/;
	
	public Widget createWidget() {
		Widget localwidget = this.createLocalWidge();
		//Window.alert("create widget ok.");
		if(localwidget != null) {
			consoleLog("localwidget is not null");
			consoleLog("children.size"+children.size());
			//递归处理children里面的对象
			for(CWebUI_AbstractUI childui : children) {
				//Window.alert(childui.toJsonStrig(childui));
				consoleLog("[CWebUI_AbstractUI]: "+childui.toJsonStrig(childui));
				Widget childwidget = childui.createWidget();
				if(childwidget != null) {
					if(localwidget instanceof Panel) {
						Panel pp = (Panel)localwidget;
						pp.add(childwidget);
						
						if(localwidget instanceof AbsolutePanel) {
							//父节点为绝对定位的情况下
							//when parent widget is AbsulutePanel.
							//using absolute position.
							AbsolutePanel ap = (AbsolutePanel) localwidget;
							ap.setWidgetPosition(childwidget, childui.left, childui.top);
						}
					}
				}
				
			}
			return localwidget;
		}else {
			Window.alert("Create Widget is Null.");
			return null;
		}
		
		
	}
}
