package com.nl.util;
public enum AppFunc {
	FUNC_GH_DETAIL_EXPORT("13401021"),//�̻�������Ϣ����
	
	/**********�����ϱ�ϵͳ**********/
	FUNC_REQUIREMENT_ADD("138001"),//�����ϱ�
	FUNC_REQUIREMENT_MY_DEAL("138002"),//�ҵĴ���
	FUNC_REQUIREMENT_BY_MY_HAND("138003"),//�ҵĴ���
	FUNC_REQUIREMENT_LIST("138004"),//���̸���
	FUNC_REQUIREMENT_MSGALARM_CONFIGURATION("138005"),//���̸���
	
	/**********�������********/
	FUNC_PORTAL_NOTICE_HISTORY("993011"),//��ʷ����
	FUNC_PORTAL_NOTICE_LIST("99301010"),//�������ҳ��
	FUNC_PORTAL_NOTICE_INSERT("99301020"),//��������
	FUNC_PORTAL_NOTICE_MODIFY("99301030"),//�����޸�
	FUNC_PORTAL_NOTICE_DELETE("99301040"),//����ɾ��
	
	FUNC_PORTAL_NOTICE_MANAGER("993020"),//�������Ա
	

	/**********Ӫ�����ж�********/
	FUNC_PRIVBREAK_FLOW_ADD("139001"),//Ӫ�����жϹ���������
	FUNC_PRIVBREAK_FLOW_MYDEAL("139002"),//Ӫ�����жϹ���������
	FUNC_PRIVBREAK_FLOW_BYMYHAND("139003"),//Ӫ�����жϹ���������
	FUNC_PRIVBREAK_FLOW_LIST("139004"),//Ӫ�����жϹ���������
	FUNC_PRIVBREAK_FLOW_LIST_EXPORT("1390041"),//Ӫ�����жϹ���������
	FUNC_PRIVBREAK_FLOW_CATALOG_CFG("139005")//Ӫ�����жϹ���������
	;
	
	
	
	private String FunctionId="";
	private AppFunc(String FunctionId){
		this.FunctionId=FunctionId;
	}
	@Override
	public String toString() {
		return this.FunctionId;
	}
}
