package com.liu.webui.client.smalljava.level1_javafile.analyse.plugin.impl;

import com.liu.webui.client.smalljava.common.StringFindUtil;
import com.liu.webui.client.smalljava.level1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
import com.liu.webui.client.smalljava.level1_javafile.vo.AbstractJavaFileElement;
import com.liu.webui.client.smalljava.level1_javafile.vo.element.JavaFileClassElement;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.liu.smalljavav2.common.StringFindUtil;
//import com.liu.smalljavav2.level1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
//import com.liu.smalljavav2.level1_javafile.vo.AbstractJavaFileElement;
//import com.liu.smalljavav2.level1_javafile.vo.element.JavaFileClassElement;

public class JavaFileClassPlugin implements IJavaFileAnalysePlugin {
	//private Logger logger = LoggerFactory.getLogger(JavaFileClassPlugin.class);
	
	@Override
	public AbstractJavaFileElement findFirstElement(String strdata) {
		if(strdata==null) {
			return null;
		}
		
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		if(strdata.startsWith("public ") 
				|| strdata.startsWith("private ")
				|| strdata.startsWith("class")) {
			int ipos1 = strdata.indexOf("class ");
			if(ipos1<0) {
				//logger.error("��ERROR�� �Ҳ���class�ؼ���.");
				return null;
			}
			strdata = strdata.substring(ipos1+5);
			strdata = util.trimReturnAndSpace(strdata);
			int ipos2 = util.findfirstStringForBlock(strdata, "{");
			if (ipos2<0) {
				//logger.error("��ERROR�� �Ҳ���{.");
				return null;
			}
			//�����õ�class�����ƶ���
			//��ʱ��������ļ̳У���Ľӿ�ʵ����Щ�߼�����
			String classname = strdata.substring(0,ipos2);
			classname = util.trimReturnAndSpace(classname);
			
			int ipos3 = util.findfirstStringForBlock(strdata, "}");
			if (ipos3<0) {
				//logger.error("��ERROR�� �Ҳ���}.");
				return null;
			}
			//�����õ�class�����ݶ���
			String classcontent = strdata.substring(ipos2, ipos3+1);
			classcontent = util.trimReturnAndSpace(classcontent);
			
			String strleftdata;
			if(ipos3<strdata.length()-1) {
				strleftdata = strdata.substring(ipos3+1);
			}else {
				strleftdata ="";
			}
			
			//����VO���󲢷���
			JavaFileClassElement ele = new JavaFileClassElement();
			ele.setClassname(classname);
			ele.setStringcontent(classcontent);
			ele.setComputeleftstring(strleftdata);
			return ele;
		}
		return null;
	}

}
