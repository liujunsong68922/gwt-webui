package com.liu.webui.client.smalljava.level5_expression.expressioneval.plugin.two;

import com.liu.webui.client.smalljava.level5_expression.expressioneval.IExpressionEval;

import com.liu.webui.client.smalljava.common.VarValue;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.webui.client.smalljava.level5_expression.expressioneval.operelement.BooleanValue;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.RootAST;
import com.liu.webui.client.smalljava.level5_expression.expressionvo.two.DualOperDataOperElement;
import com.liu.webui.client.smalljava.space.IClassTable;
import com.liu.webui.client.smalljava.space.IVarTable;

/**
 * MEMO ִ�мӷ�����
 * @author liujunsong
 *
 */
public class LogicAndOperEvalPlugin implements IExpressionEval {


	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// �ӷ�����
			if (oper.getOpercode().equals("&&")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				// ����һ���µ�������
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					System.out.println("�ӷ�����ʧ�ܣ�����Ϊnull");
					return null;
				}

				if (leftvar.getVartype() == null) {
					System.out.println("�����߼������������������Ϊnull");
					return null;
				}
				if (leftvar.getVartype().equals("boolean")) {
					BooleanValue intoper = new BooleanValue(leftvar.getVarsvalue());
					intoper.doAnd(rightvar.getVarsvalue());
					return intoper;
				}
				System.out.println("��ERROR���߼�Add�Ų��������˲�֧�ֵ��������ͣ�" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}
}
