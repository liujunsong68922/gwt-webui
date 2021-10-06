package com.liu.webui.client.smalljava.level5_expression.expressionvo.constvalue;

import com.liu.webui.client.smalljava.level5_expression.expressionvo.AbstractLeafElement;

//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractLeafElement;

/**
 * ����һ������
 * @author liujunsong
 *
 */
public abstract class AbstractConstDataElement extends AbstractLeafElement {
	private String datatype;
	private String datavalue;
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDatavalue() {
		return datavalue;
	}
	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}
	
}
