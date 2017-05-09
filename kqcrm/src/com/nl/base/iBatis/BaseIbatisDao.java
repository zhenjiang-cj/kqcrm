package com.nl.base.iBatis;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.base.utils.SystemTool;
import com.nl.util.GlobalConst;
import com.nl.util.Page;
import com.nl.util.PageRequest;

/**
 * @author badqiu
 * @version 1.0
 */
public class BaseIbatisDao<E, PK extends Serializable> extends AbstractDB {

	protected SqlMapClient smc;
	protected String bossCodeStr;


	public BaseIbatisDao(String bossCodeStr) {
		this.smc = SystemTool.getSqlMapClient();
		this.bossCodeStr = bossCodeStr;
	}
	
	public BaseIbatisDao(SqlMapClient smc, String bossCodeStr) {
		this.smc = smc;
		this.bossCodeStr = bossCodeStr;
	}

	@SuppressWarnings("unchecked")
	public E getById(PK primaryKey) throws Exception {
		try {
			return (E) smc.queryForObject(getFindByPrimaryKeyStatement(),
					primaryKey);
		} catch (SQLException e) {
			getLogger(bossCodeStr, GlobalConst.ERROR)
					.error(":::" + getComponentName() + "按主键获取出错！:::");
			throw new Exception(getComponentName() + "按主键获取出错！");
		}
	}

	public void deleteById(PK id) throws Exception {
		try {
			smc.delete(getDeleteStatement(), id);
		} catch (SQLException e) {
			getLogger(bossCodeStr, GlobalConst.ERROR)
					.error(":::" + getComponentName() + "删除出错！:::");
			throw new Exception(getComponentName() + "删除出错！");
		}
	}

	public void save(E entity) throws Exception {
		try {
			smc.insert(getInsertStatement(), entity);
		} catch (SQLException e) {
			getLogger(bossCodeStr, GlobalConst.ERROR)
					.error(":::" + getComponentName() + "添加出错！:::");
			throw new Exception(getComponentName() + "添加出错！");
		}
	}

	public void update(E entity) throws Exception {
		try {
			int num = smc.update(getUpdateStatement(), entity);
			if (num == 0) {
				throw new Exception(getComponentName() + "更新出错！");
			}
		} catch (SQLException e) {
			getLogger(bossCodeStr, GlobalConst.ERROR)
					.error(":::" + getComponentName() + "更新出错！:::");
			throw new Exception(getComponentName() + "更新出错！");
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * 根据条件查询所有数据，不分页
	 */
	public List<E> queryAll(PageRequest pageRequest) throws Exception {
		try {
			List<E> list = smc.queryForList(getFindepageStatement(), pageRequest);
			return list;
		} catch (SQLException e) {
			getLogger(bossCodeStr, GlobalConst.ERROR)
					.error(":::" + getComponentName() + "查询出错！:::");
			throw new Exception(getComponentName() + "查询出错！");
		}
	}

	/* 以下statement 相当于接口 如果必须的话可以继承和修改这些statmentname */
	public String getFindByPrimaryKeyStatement() {
		return getIbatisSqlMapNamespace() + ".getById";
	}

	public String getInsertStatement() {
		return getIbatisSqlMapNamespace() + ".insert";
	}

	public String getUpdateStatement() {
		return getIbatisSqlMapNamespace() + ".update";
	}

	public String getDeleteStatement() {
		return getIbatisSqlMapNamespace() + ".delete";
	}

	public String getFindepageStatement() {
		return getIbatisSqlMapNamespace() + ".findPage";
	}
	
	public String getIbatisSqlMapNamespace() {
		throw new RuntimeException("not yet implement");
	}

	public String getComponentName() {
		throw new RuntimeException("not yet implement");
	}

	public Page queryForPage(Page page, PageRequest pageRequest) throws Exception {
		pageRequest.queryForPage(page);
		return this.queryForPage(pageRequest);
	}
	
	/**
	 * @Title: queryForPage 
	 * @Description: 通过查询bean 分页查询，需要调用bean的queryForPage函数添加分页bean
	 * @author dq   
	 * @date 2014-3-1 下午01:07:56 
	 * @version V1.0  
	 * @param @param pageRequest
	 * @param @return
	 * @param @throws Exception    
	 * @return Page   
	 * @throws
	 */
	public Page queryForPage(PageRequest pageRequest) throws Exception {
		try {

			//查询总数
			Number totalCount = (Number) smc.queryForObject(getFindepageStatement() + ".count", pageRequest);
			
			if (totalCount == null || totalCount.longValue() <= 0) {
				pageRequest.getPage().setTotalCount(0);
				return pageRequest.getPage();
			} else {
				pageRequest.getPage().setTotalCount(totalCount.intValue());
			}

			//查询
			List<E> list = this.queryAll(pageRequest);
			pageRequest.getPage().setItems(list);

			return pageRequest.getPage();
		} catch (Exception e) {
			getLogger(bossCodeStr, GlobalConst.ERROR)
			.error(":::" + getComponentName() + "分页查询出错！:::"+e.getMessage());
			throw new Exception(getComponentName() + "分页查询出错！");
		}
	}
}
