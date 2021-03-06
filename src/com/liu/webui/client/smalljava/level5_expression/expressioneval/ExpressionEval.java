package com.liu.webui.client.smalljava.level5_expression.expressioneval;

import java.util.HashMap;

import com.liu.webui.client.smalljava.common.VarValue;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.atom.AtomEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.constvalue.ConstEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.obj.ObjectCallEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.one.LogicNotPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.LogicAndOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.LogicEqualsOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.LogicGreaterEqualOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.LogicGreaterOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.LogicLitterEqualOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.LogicLitterOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.LogicNotEqualOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.LogicOrOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.MathAddOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.MathDeAddOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.MathDevideOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two.MathMultiOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.var.DefineOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.var.NewOperEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.var.VarSetEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.var.VariableEvalPlugin;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.MiddleAST;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.RootAST;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.atom.AtomElement;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.constvalue.AbstractConstDataElement;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.obj.ObjectCallOperElement;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.one.LogicNotOperElement;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.two.DualOperDataOperElement;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.var.NewOperElement;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.var.VarDataElement;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.var.VarDefineOperElement;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.var.VarSetOperElement;
import com.liu.webui.client.smalljava.space.IClassTable;
import com.liu.webui.client.smalljava.space.IVarTable;

public class ExpressionEval implements IExpressionEval {
	private static HashMap<String, IExpressionEval> evalmap = new HashMap<String, IExpressionEval>();

	/**
	 * ??????RootAST???????????????????????????String????????????
	 */
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		// Part1:??????????????????????????????????????????????????????
		// step0.???????????????????????????
		// Part1:no child leaf point compute rule
		// step0: check whether middle AST point
		if (root instanceof MiddleAST) {
			consoleLog("This is MiddleAST");
			MiddleAST middle = (MiddleAST) root;
			RootAST child = middle.getChildren().get(0);
			// ??????MiddleAST?????????????????????
			// Bypass Middle point, compute its first children
			// MiddleAST has only one child.
			return eval(child, vartable, classtable);
		}

		// step1.??????root?????????????????????????????????
		// step1.if root is constdata,return const data.
		if (root instanceof AbstractConstDataElement) {
			consoleLog("This is AbstractConstDataElement");
			ConstEvalPlugin consteval = new ConstEvalPlugin();
			return consteval.eval(root, vartable, classtable);
		}

		// step2.?????????root???????????????????????????????????????????????????
		// ???????????????????????????????????????????????????????????????????????????????????????????????????
		// if root is a vardata point, return var compute result.
		if (root instanceof VarDataElement) {
			consoleLog("This is VarDataElement");
			VariableEvalPlugin vareval = new VariableEvalPlugin();
			return vareval.eval(root, vartable, classtable);
		}

		// step3.??????root?????????new??????????????????????????????????????????
		if (root instanceof NewOperElement) {
			consoleLog("This is NewOperElement");
			NewOperEvalPlugin neweval = new NewOperEvalPlugin();
			return neweval.eval(root, vartable, classtable);
		}

		// step4.??????root??????????????????????????????????????????????????????
		if (root instanceof VarDefineOperElement) {
			consoleLog("This is VarDefineOperElement");
			DefineOperEvalPlugin defineeval = new DefineOperEvalPlugin();
			return defineeval.eval(root, vartable, classtable);
		}

		// step5.??????root?????????Atom,?????????Atom???????????????
		if (root instanceof AtomElement) {
			consoleLog("This is AtomElement");
			AtomEvalPlugin atomeval = new AtomEvalPlugin();
			return atomeval.eval(root, vartable, classtable);
		}

		// Part2. ??????????????????????????????,???????????????????????????????????????
		// step6.???????????????????????????
		if (root instanceof LogicNotOperElement) {
			consoleLog("This is LogicNotOperElement");
			LogicNotPlugin logicnoteval = new LogicNotPlugin();
			return logicnoteval.eval(root, vartable, classtable);
		}

		// Part3. ???????????????????????????
		if (root instanceof DualOperDataOperElement) {
			consoleLog("This is DualOperDataOperElement");
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// ?????????????????????????????????????????????
			IExpressionEval eeval = this.getEvalPluginByOpercode(oper.getOpercode());
			if(eeval == null) {
				System.out.println("Cannot find oper expressioneval:"+oper.getOpercode());
				consoleLog("Cannot find oper expressioneval:"+oper.getOpercode());
				return null;
			}
			return eeval.eval(root, vartable, classtable);
		}

		// Part3.???????????????????????????????????????????????????????????????????????????
		// step7 ????????????????????????
		if (root instanceof VarSetOperElement) {
			consoleLog("This is VarSetOperElement");
			VarSetEvalPlugin varseteval = new VarSetEvalPlugin();
			return varseteval.eval(root, vartable, classtable);
		}

		// Part4 ?????????????????????
		if (root instanceof ObjectCallOperElement) {
			consoleLog("This is ObjectCallOperElement");
			ObjectCallOperElement objcall = (ObjectCallOperElement) root;
			consoleLog("obj/method:"+ objcall.getObjname()+"/"+objcall.getMethodname());
			
			ObjectCallEvalPlugin plugin1 = new ObjectCallEvalPlugin();
			
			return plugin1.eval(root, vartable, classtable);
		}
				
		// Part5 ????????????????????????????????????????????????????????????
		// ????????????????????????????????????
		System.out.println("---->??????????????????");
		consoleLog("---->??????????????????");
		root.show(0);
		return null;
	}

	/**
	 * MEMO?????????????????????????????????????????????????????????????????????????????????
	 * @param opercode
	 * @return
	 */
	private IExpressionEval getEvalPluginByOpercode(String opercode) {
		initEvalMap();
		return evalmap.get(opercode);
	}

	private static void initEvalMap() {
		if (evalmap.size() == 0) {
			//?????????????????????????????????????????
			evalmap.put("+", new MathAddOperEvalPlugin());
			evalmap.put("-", new MathDeAddOperEvalPlugin());
			evalmap.put("*", new MathMultiOperEvalPlugin());
			evalmap.put("/", new MathDevideOperEvalPlugin());

			//?????????????????????????????????????
			evalmap.put("&&", new LogicAndOperEvalPlugin());
			evalmap.put("||", new LogicOrOperEvalPlugin());
			evalmap.put(">", new LogicGreaterOperEvalPlugin());
			evalmap.put(">=", new LogicGreaterEqualOperEvalPlugin());
			evalmap.put("<", new LogicLitterOperEvalPlugin());
			evalmap.put("<=", new LogicLitterEqualOperEvalPlugin());
			evalmap.put("==", new LogicEqualsOperEvalPlugin());
			evalmap.put("!=", new LogicNotEqualOperEvalPlugin());
		}
	}

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[ExpressionEval]:" + message );
	}-*/;

}
