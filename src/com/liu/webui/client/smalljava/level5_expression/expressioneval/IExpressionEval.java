package com.liu.webui.client.smalljava.level5_expression.expressioneval;

import com.liu.webui.client.smalljava.common.VarValue;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.RootAST;
import com.liu.webui.client.smalljava.space.IClassTable;
import com.liu.webui.client.smalljava.space.IVarTable;

//import com.liu.smalljavav2.common.VarValue;
//import com.liu.smalljavav2.level5_expression.expressionvo.RootAST;
//import com.liu.smalljavav2.space.IClassTable;
//import com.liu.smalljavav2.space.IVarTable;

public interface IExpressionEval {
	public VarValue eval(RootAST root,IVarTable vartable,IClassTable classtable);
}
