package com.nl.base.iBatis;

/**
 *
 * ���ݿ�ģ�鹫���ӿ���
 *
 * @author MaYan
 *
 */
interface ModuleHandle extends java.io.Serializable
{
	public final static int	CONN_NORMAL	= 0;	// ��ͨ����

	public final static int	CONN_TX		= 1;	// ֧�ֲַ�ʽ�����������

	public final static int	CONN_XA		= 2;	// ֧�ֲַ�ʽ������֧�ֿ����ݿ������������

	/**
	 * ��ȡ��׼iBATIS SqlMapClient ��Proxyģʽʵ�ֽӿ�
	 *
	 * @return
	 */
	public SqlMapClientProxy getSqlMapClientProxy();
}
