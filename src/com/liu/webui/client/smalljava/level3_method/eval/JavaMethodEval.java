package com.liu.webui.client.smalljava.level3_method.eval;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.liu.webui.client.smalljava.common.VarValue;
import com.liu.webui.client.smalljava.level3_method.vo.JavaMethodRootVO;
import com.liu.webui.client.smalljava.level4_block.blockanalyse.BlockAnalyse;
import com.liu.webui.client.smalljava.level4_block.blockeval.BlockEvaluator;
import com.liu.webui.client.smalljava.level4_block.blockvo.BasicBlock;
import com.liu.webui.client.smalljava.space.IClassTable;
import com.liu.webui.client.smalljava.space.IVarTable;
//import com.liu.webui.client.smalljava.space.impl.ClassTableImpl;

public class JavaMethodEval {
	//private Logger logger = LoggerFactory.getLogger(JavaMethodEval.class);
	/**
	 * MEMO�����Method�ķ������ã���Ҫ����IVarTable,ClassTable
	 * MEMO:�Ȳ����ǵ��ò���������
	 * MEMO��Method����Ҫ�ѵ��ò������뵽�Լ��ı���������ȥ�������ö���ı�����
	 * @param methodvo
	 * @param vartable
	 * @return
	 */
	public VarValue eval(JavaMethodRootVO methodvo,IVarTable classvartable,IClassTable classtable) {
		if(methodvo == null) {
			//logger.error("methodvo is null.");
			return null;
		}
		
		BasicBlock closedblock = new BasicBlock("",methodvo.getMethodContent(),null);
		closedblock.setClassVarTable(classvartable);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		closedblock.show(0);
		//logger.info("�������������"+isok);	
		
		
		BlockEvaluator node = new BlockEvaluator();
		//ClassTableImpl classtable = new ClassTableImpl();
		boolean b2;
		try {
			b2 = node.execute(closedblock,classtable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
		
		
	}
}
