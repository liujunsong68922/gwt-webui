package com.liu.webui.client.webui.controller;

//import com.google.gwt.dom.client.Document;
//import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
//import com.liu.ui.client.XMLParseFunction;
import com.liu.webui.client.webui.model.CWebUI_RootFlowPanel;

public class CWebUI_Create {
	public CWebUI_RootFlowPanel createByXMLString(String sdesc) {
		// convert string to xml documnet
		Document doc = this._parseXML(sdesc);
		
		if (doc != null) {
			return createByDocument(doc);
		}else {
			return null;
		}
	}

	public CWebUI_RootFlowPanel createByDocument(Document doc) {
		CWebUI_RootFlowPanel rootpanel = new CWebUI_RootFlowPanel();
		
		//step1:find root element
		//the first node is rootnode.
		Node rootnode = doc.getFirstChild();
		if(rootnode == null) {
			log("Error: rootnode is null.");
			return rootpanel;
		}else {
			//read from rootnode
			//write to rootpanel
			//this will recall other nodes.
			rootpanel.readNode(rootnode);
		}
		return rootpanel;
	}

	private Document _parseXML(String strxml) {
		try {
			Document doc = XMLParser.parse(strxml);
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			Window.alert("Error when parse xml:" + e.toString());
			return null;
		}
	}
	
	public static native void eval(String msg) /*-{
    $wnd.eval(msg);
    }-*/;
	
	//public static native void log(String msg)/*-{
    //$wnd.console.log(msg);
    //}-*/;
	
	public static native void log(Object msg)/*-{
    $wnd.console.log(msg);
    }-*/;
}
