package com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.obj;

import com.google.gwt.user.client.Window;
import com.liu.webui.client.WebUIApp;
import com.liu.webui.client.smalljava.common.UuidObjectManager;
import com.liu.webui.client.smalljava.common.VarValue;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.RootAST;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.obj.ObjectCallOperElement;
import com.liu.webui.client.smalljava.level6_vm.JavaVMImpl;
import com.liu.webui.client.smalljava.space.IClassTable;
import com.liu.webui.client.smalljava.space.IVarTable;

public class ObjectCallEvalPlugin implements IExpressionEval {

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		//loginfo
		
		ObjectCallOperElement objcall = (ObjectCallOperElement) root;
		consoleLog(objcall.getObjname());
		consoleLog(objcall.getMethodname());
		String objname = objcall.getObjname();
		String methodname = objcall.getMethodname();
		
		//利用变量表，将objname转成特定对象
		VarValue varvalue = vartable.getVarValue(objname);
		consoleLog("var data:"+varvalue.getVarsvalue());
		
		Object targetobj = UuidObjectManager.getObject(varvalue.getVarsvalue());
		if(targetobj != null) {
			if (targetobj instanceof WebUIApp) {
				Window.alert("This is WebUIApp");
				callWebUIAppFunction(targetobj,methodname);
				return new VarValue();
			}else {
				JavaVMImpl jvm = new JavaVMImpl();
				Object retobj = jvm.objectCall(targetobj, methodname);
				
				Window.alert(retobj.toString());
				//根据返回值来组织返回值
				//如果返回值是Integer
				if(retobj instanceof Integer) {
					Window.alert("is int.");
					VarValue varvalue1 = new VarValue();
					varvalue1.setVarname("ret");
					varvalue1.setVartype("int");
					varvalue1.setVarsvalue(""+(int)retobj);
					return varvalue1;
				}
				if(retobj instanceof Long) {
					Window.alert("is long.");
					VarValue varvalue2 = new VarValue();
					varvalue2.setVarname("ret");
					varvalue2.setVartype("long");
					varvalue2.setVarsvalue(""+(long)retobj);
					return varvalue2;
				}
				if(retobj instanceof Float) {
					Window.alert("is float.");
					VarValue varvalue3 = new VarValue();
					varvalue3.setVarname("ret");
					varvalue3.setVartype("float");
					varvalue3.setVarsvalue(""+(float)retobj);
					return varvalue3;
				}
				if(retobj instanceof Double) {
					Window.alert("is double.");
					VarValue varvalue4 = new VarValue();
					varvalue4.setVarname("ret");
					varvalue4.setVartype("double");
					varvalue4.setVarsvalue(""+(double)retobj);
					return varvalue4;
				}
				if(retobj instanceof Boolean) {
					Window.alert("is boolean.");
					VarValue varvalue5 = new VarValue();
					varvalue5.setVarname("ret");
					varvalue5.setVartype("boolean");
					varvalue5.setVarsvalue(""+(boolean)retobj);
					return varvalue5;
				}
				if(retobj instanceof String) {
					Window.alert("is String.");
					VarValue varvalue6 = new VarValue();
					varvalue6.setVarname("ret");
					varvalue6.setVartype("String");
					varvalue6.setVarsvalue(""+(String)retobj);
					return varvalue6;
				}

				//除了基础数据类型以外的类型，使用Object来进行存储
				//所以先生成一个uuid来作为唯一的标识符
				String uuid = uuid();
				
				UuidObjectManager objmanage = new UuidObjectManager();
				objmanage.setObject(uuid, retobj);
				
				VarValue varvalue1 = new VarValue();
				varvalue1.setVarname("ret");
				varvalue1.setVartype("Object");
				varvalue1.setVarsvalue(uuid);
				
				return varvalue1;
			}
			
			
			
		}else {
			eval(objname,methodname);
			return new VarValue();
		}

	}

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[ObjectCallEvalPlugin]:" + message );
	}-*/;

	public native void eval(String obj,String method) /*-{
	//alert(message);
	var strcommand = obj+"."+method;
	alert(strcommand);
	var abc = $wnd.eval(strcommand);
	alert(abc);
	}-*/;

	
	public void callWebUIAppFunction(Object obj,String method) {
		WebUIApp app = (WebUIApp) obj;
		if(method.equals("getCount")) {
			int i = app.getCount();
		}
	}
	
	public native String uuid() /*-{
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
	var r = Math.random() * 16 | 0,
	v = c == 'x' ? r : (r & 0x3 | 0x8);
	return v.toString(16);
	});
	}-*/;
}
