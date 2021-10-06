package com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.var;

import com.liu.webui.client.smalljava.common.VarValue;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.RootAST;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.var.VarDefineOperElement;
//import com.liu.webui.client.smalljava.expression.expressionvo.var.NewOperElement;
import com.liu.webui.client.smalljava.space.IClassTable;
import com.liu.webui.client.smalljava.space.IVarTable;

public class DefineOperEvalPlugin implements IExpressionEval {

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		//nullֵ���
		if(root == null || vartable == null || classtable == null) {
			return null;
		}
		
		if(root instanceof VarDefineOperElement) {
			VarDefineOperElement defineoper = (VarDefineOperElement) root;
			String datatype = defineoper.getDatatype();
			String varname = defineoper.getVarname();
			boolean oper = vartable.defineVar(varname, datatype);
			if(oper) {
				VarValue retvalue = new VarValue();
				retvalue.setVartype(datatype);
				retvalue.setVarsvalue(varname);
				return retvalue;
			}
		}
		return null;
	}

}
