package com.liu.webui.client.webui.model.element;

import com.liu.webui.client.webui.model.CWebUI_AbstractUI;

/**
 * Elment doesnot have any children.
 * @author liujunsong
 *
 */
public abstract class CWebUI_Abstract_Element 
	extends CWebUI_AbstractUI {
	
	//This is the UI element type
	private String elementtype;

	private String targetElementType;
	
	//This is the name value
	private String name;
	
	//This is the text value, it is the same as label value.
	private String text;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

	public String getElementtype() {
		return elementtype;
	}
	public void setElementtype(String elementtype) {
		this.elementtype = elementtype;
	}
	public String getTargetElementType() {
		return targetElementType;
	}
	public void setTargetElementType(String targetElementType) {
		this.targetElementType = targetElementType;
	}
	
	
	
}
