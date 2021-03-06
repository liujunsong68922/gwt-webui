package com.liu.webui.client.smalljava.level5_expression.expressionanalyse;

import java.util.ArrayList;

import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.atom.AtomOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.constvalue.ConstNumberOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.constvalue.ConstStringOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.obj.ObjectCallOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.one.LogicNotOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.two.LogicCompareOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.two.LogicComputeOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.two.MathAddDeaddOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.two.MathMultiDevideOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.var.NewObjectOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.var.VarSetOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.var.VariableDefineOperPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionanalyse.plugin.var.VariableOperPlugin;

//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.atom.AtomOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.constvalue.ConstNumberOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.constvalue.ConstStringOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.obj.ObjectCallOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.one.LogicNotOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.two.LogicCompareOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.two.LogicComputeOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.two.MathAddDeaddOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.two.MathMultiDevideOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.var.NewObjectOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.var.VarSetOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.var.VariableDefineOperPlugin;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.var.VariableOperPlugin;

/**
 * AST??????????????????Block?????????????????????????????????????????????????????????????????????
 * MEMO???????????????????????????????????????????????????????????????????????????????????????????????????????????
 * @author liujunsong
 *
 */
public class ExpressionASTPluginManager {
	private static ArrayList<IAstPlugin> pluginmap=new ArrayList<IAstPlugin>();
	
	public ExpressionASTPluginManager() {
		initMap();
	}
	
	private static void initMap() {
		if(pluginmap.size()==0) {

			pluginmap.add(new MathAddDeaddOperPlugin());
			pluginmap.add(new MathMultiDevideOperPlugin());
			pluginmap.add(new AtomOperPlugin());
			pluginmap.add(new ConstNumberOperPlugin());
			pluginmap.add(new ConstStringOperPlugin());
			pluginmap.add(new VariableOperPlugin());
			pluginmap.add(new VariableDefineOperPlugin());
			pluginmap.add(new LogicCompareOperPlugin());
			pluginmap.add(new LogicComputeOperPlugin());
			pluginmap.add(new ObjectCallOperPlugin());
			pluginmap.add(new LogicNotOperPlugin());
			pluginmap.add(new VarSetOperPlugin());
			pluginmap.add(new NewObjectOperPlugin());
		}
	}

	public static ArrayList<IAstPlugin> getPluginmap() {
		return pluginmap;
	}

	public static void setPluginmap(ArrayList<IAstPlugin> pluginmap) {
		ExpressionASTPluginManager.pluginmap = pluginmap;
	}
	
	
}
