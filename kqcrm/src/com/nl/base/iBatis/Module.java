package com.nl.base.iBatis;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * ���ݿ�ģ������࣬�ṩ�˰��ڷ�����Ҫ�Ĺ�������
 * @author MaYan
 *
 */
abstract public class Module implements ModuleHandle
{
	/**
	 * <p>
	 * ��������iBATIS�����ļ�
	 * </p>
	 *
	 * @return
	 */
	abstract SqlMapClient reloadSqlMapConfig();

	/**
	 * ��ñ�׼iBATIS��SqlMapClient��δ��SqlMapClientProxy��װ��
	 *
	 * @return
	 */
	abstract SqlMapClient getSqlMapClient();
}
