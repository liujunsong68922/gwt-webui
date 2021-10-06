package com.liu.webui.client.smalljava.space;

import java.util.HashMap;

public interface IClassTable {
	/**
	 * ����class��������������ȡClass����
	 * ����ӿ���ΪExpression AST����ʱ�ṩ��
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class getClass(String name) ;
	
	/**
	 * ��ȡ����ClassMap��HashMap����
	 * ����ӿ�ΪBlock AST�����ṩ����������������ض���Class Map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public HashMap<String, Class> getClassmap();
	
	/**
	 * ֱ������һ��HashMap��ȥ��ΪClassMap.
	 * @param classmap
	 */
	@SuppressWarnings("rawtypes")
	public void setClassmap(HashMap<String, Class> classmap);
}
