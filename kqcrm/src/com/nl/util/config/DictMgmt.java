package com.nl.util.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nl.base.AbstractDB;
import com.nl.base.utils.SystemTool;
import com.nl.util.db.DBConnection;

public class DictMgmt extends AbstractDB{
    public static final String SPLIT_CHAR = ";";// 分隔符
    private static Logger logger = Logger.getLogger(DictMgmt.class);
    private Connection conn = null;

    /*系统字典*/
    public static final int DICT_SYS_SYSTEM_ID = 1000;//系统编号             
    public static final int DICT_SYS_VALID_FLAG = 1001;//有效状态
    public static final int DICT_DOC_TYPE = 1005;//资料类型
    

    public static final int DICT_KQ_PROVINCES=1010;//省份
    public static final int DICT_KQ_CITY=1011;//地市
    public static final int DICT_kq_REGION=1012;//区域
    
    
    
//    1.所属区域字典，取字典1000
    public static final int DICT_COMPANY_COUNTY=999;
//
//    2.公司类型字典，取字典1001
    public static final int DICT_COMPANY_TYPE=1001;
//
//    3.法人类型字典，取字典1002
    public static final int DICT_COMPANY_LEGAL=1002;
//
//    4.主管部门字典，取字典1003
    public static final int DICT_COMPANY_PARENT_DEPT = 1003;
//
//    5.批准单位字典，取字典1004
    public static final int DICT_COMPANY_APPROVE_DEPT=1004;
//
//    5.人员类型，取字典1005 人员类型，取字典1005;1为企业法人，2为总经理，3为普通员工
    public static final int DICT_COMPANY_USER_TYPE=1005;
//
//    6.人员性别，取字典1006
    public static final int DICT_COMPANY_USER_SEX=1006;
//
//    7.证件类型，取字典1007
    public static final int DICT_COMPANY_USER_CARD=1007;
//
//    8.国籍，取字典1008
    public static final int DICT_COMPANY_USER_NATION=1008;
//
//    9.专业，取字典1009
    public static final int DICT_COMPANY_USER_SPECIALTY=1009;
//
//    10.人员学历，取字典1010
    public static final int DICT_COMPANY_USER_EDU=1010;
//
//    11.职务，取字典1011
    public static final int DICT_COMPANY_USER_POST=1011;
    public static final int DICT_COMPANY_USER_POST_CHILD=1036;
    
//    12.岗位，取字典1012 
    public static final int DICT_COMPANY_USER_STATION=1012; 
//
//    13.职称等级，取字典1013
    public static final int DICT_COMPANY_USER_STA_GRADE=1013;
//
//    14.资格类别，取字典1014
    public static final int DICT_COMPANY_QUALIFICATIONS_TYPE=1014;
    
    //资格级别 1032
    public static final int DICT_COMPANY_QUALIFICATIONS_GRADE = 1032;
    
//    15.物业类型，取字典1015
    public static final int DICT_COMPANY_WUYE_TYPE=1015;
//
//    16.项目区域，同所属区域，取字典1000
//
//    17.项目类型，取字典1016（多层、高层、别墅、其他）
    public static final int DICT_COMPANY_PRO_TYPE=1016;
//
//    18.投资者类型，取字典1017
    public static final int DICT_COMPANY_INVESTOR_TYPE = 1017;
    public static final int DICT_COMPANY_INVESTOR_TYPE_OTHER=1034;
//
//    19.出资类型/出资方式，取字典1018
    public static final int DICT_COMPANY_INVESTMENT_WAY = 1018;
    
//
//    20.申请事项，取字典1019（初次申请、核定等级、资质延续、资质变更）
    public static final int DICT_COMPANY_APPLY_TYPE = 1019;
//
//    21.申请等级，取字典1020（暂定三级、三级），资质等级，也取这个字典
    public static final int DICT_COMPANY_APTITUDE_GRADE = 1020;
//
//    22.文件类型，取字典1021（1为企业资质图片，2为营业执照图片，3为企业章程图片，4为企业服务质量/服务收费制度图片，5为企业人员身份证图片，6为企业人员劳动合同图片，7为企业人员任职文件图片，8为企业人员社保证明图片，9为企业人员职称证书图片，10为物业服务项目合同材料图片，11为物业服务项目项目合同备案证明图片，12为企业上报申报表扫描件图片，13证书变更申请报告图片，14为工商变更核准通知书）
//
//    23.图片文件状态，取字典1022
//
//    24.文件生成方式，取字典2023（1为人工上传，2为系统生成）
//
//    25.证书文件序号，取字典1024（1为证书编号，2为证书正本，3为证书副本）
//
//    26.证书生成方式，取字典1025（1为系统根据模板生成，2为人工修改上传）
//
//    27.资质类别，取字典1026
    public static final int DICT_COMPANY_APTITUDE_TYPE = 1026;
//
//    1，2,3,4为企业信息编号；5,6,7,8,9为企业人员编号；10,11物业项目代码；12，13，14为企业信息编号
    
    //资质备案等级
    public static final int DICT_COMPANY_BAK_GRADE = 1039;
    
    public static DictMgmt instance = new DictMgmt();

    /**
     * 存储数据字典对象 *
     */
    private static LinkedHashMap m_linkedHashMap = null;
    private static LinkedHashMap m_linkedHashMapComplex = null;

    /**
     * te static DictMgmt instance = new DictMgmt();
     * 
     * 
     * private DictMgmt() { super(); }
     * 
     * /** 获取单一实例
     * 
     * @return DictMgmt
     */
    public static DictMgmt getInstance() {
        return instance;
    }

    /**
     * 获取字典表信息的方法
     * 
     * @return ArrayList 字典表列表
     */
    public List loadDict() {
        StringBuffer oSql = new StringBuffer();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DictInfo info = null;
        ArrayList list = null;
        oSql.append(" select t.dict_id,t.value,t.dict_name,t.value_name,t.parent_id,t.sort_id,t.remark,t.is_modify,count(*) over() num ");
        oSql.append(" from km_dict_cfg t where t.is_valid = 1 order by t.sort_id ");
        try {
            stmt = conn.prepareStatement(oSql.toString());
            // 执行SQL语句
            logger.debug("********* portalzj 读取字典表的语句->sql:" + oSql);
            stmt.setFetchSize(512);
            rs = stmt.executeQuery();

            rs.setFetchSize(256);
            boolean isFirst = true;
            while (rs.next()) {
                if (isFirst) {
                    list = new ArrayList(rs.getInt("num"));
                    isFirst = false;
                }
                info = new DictInfo();
                info.setIDictId(rs.getString("dict_id"));
                info.setIValue(rs.getString("value"));
                info.setStrDictDesc(rs.getString("dict_name"));
                info.setStrValueDesc(rs.getString("value_name"));
                info.setParentId(rs.getString("parent_id"));
                info.setRemark(rs.getString("remark"));
                list.add(info);
            }
        } catch (SQLException e) {
            logger.error(oSql);
            e.printStackTrace();
        } finally {
            DBConnection.tryClose(rs, stmt);
        }
        return list;
    }

    /**
     * 获取字典表信息的方法
     * 
     * @return ArrayList 字典表列表
     */
    public List loadComplexDict() {
        StringBuffer oSql = new StringBuffer();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DictInfo info = null;
        ArrayList list = null;
        oSql
                .append("select dictionary_id,value_id,dictionary_name,value_name,parent_value_id,value_desc,count(*) over() num from sys_dictionary_complex where is_valid=1 order by sort ");
        try {
            stmt = conn.prepareStatement(oSql.toString());
            // 执行SQL语句
            logger.debug("::loadComplexDict()->sql:" + oSql);
            stmt.setFetchSize(512);
            rs = stmt.executeQuery();

            rs.setFetchSize(256);
            boolean isFirst = true;
            while (rs.next()) {
                if (isFirst) {
                    list = new ArrayList(rs.getInt("num"));
                    isFirst = false;
                }
                info = new DictInfo();
                info.setParentId(rs.getString("parent_value_id"));
                info.setRemark(rs.getString("value_desc"));
                list.add(info);
            }
        } catch (SQLException e) {
            logger.error(oSql);
            e.printStackTrace();
        } finally {
            DBConnection.tryClose(rs, stmt);
        }
        return list;
    }

    /**
     * 从系统Load字典表信息
     */
    public void loadDictInfo() {
        List list = null;
        List listComplex = null;
        try {
            conn = DBConnection.Open();
            list = loadDict();
            // listComplex = loadComplexDict();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                logger.error("从系统Load字典表信息回滚失败");
                e1.printStackTrace(); // To change body of catch statement use
            }
        } finally {
            DBConnection.tryClose(conn);
        }
        m_linkedHashMap = new LinkedHashMap();
        if ((list != null) && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                DictInfo dict = (DictInfo) list.get(i);

                LinkedHashMap sub_hashMap = (LinkedHashMap) m_linkedHashMap.get(dict.getIDictId());

                if (sub_hashMap == null) {
                    sub_hashMap = new LinkedHashMap();
                }

                sub_hashMap.put(String.valueOf(dict.getIValue()), dict.getStrValueDesc());
                m_linkedHashMap.put(String.valueOf(dict.getIDictId()), sub_hashMap);

            }
        }
        String key = null;
        m_linkedHashMapComplex = new LinkedHashMap();
        if ((listComplex != null) && !listComplex.isEmpty()) {
            int size = listComplex.size();
            for (int i = 0; i < size; i++) {
                DictInfo dict = (DictInfo) listComplex.get(i);
                key = dict.getIDictId() + ":" + dict.getParentId();
                LinkedHashMap sub_hashMap = (LinkedHashMap) m_linkedHashMapComplex.get(key);

                if (sub_hashMap == null) {
                    sub_hashMap = new LinkedHashMap();
                }
                sub_hashMap.put(String.valueOf(dict.getIValue()), dict.getStrValueDesc());
                m_linkedHashMapComplex.put(key, sub_hashMap);
            }
        }
    }

    /**
     * 根据大类和父亲id得到value 2009.02.04 liuyu
     * 
     * @param dict_id
     *            字典表示
     * @param parent_id
     *            父标示
     * @return 字典值
     */

    public static int getValue(int dict_id, int parent_id) {
        Map map = getDictHashh(dict_id, parent_id);
        if (map == null || map.keySet() == null || map.keySet().size() == 0) {
            return 0;
        }

        return Integer.parseInt(((map.keySet().toArray())[0]).toString());
    }

    /**
     * 根据大类和地市编码得到value 2011.03.17 JINYH
     * 
     * @param dict_id
     *            字典表示
     * @param parent_id
     *            父标识
     * @param city_id
     *            地市编码
     * 
     * @return 字典值
     */

    public static int getValue(int dict_id, String city_id) {
        Map map = getDictHashh(dict_id, city_id);
        if (map == null || map.keySet() == null || map.keySet().size() == 0) {
            return 0;
        }

        return Integer.parseInt(((map.keySet().toArray())[0]).toString());
    }

    /**
     * 根据大类编号获取相应字典小类信息的Hash表
     * 
     * @param iDictId
     *            int 大类编号
     * @return Hashtable 大类对应的小类hash表（单个元素是Dict_DT)
     */
    public static LinkedHashMap getDictHashh(int iDictId) {
        return (LinkedHashMap) (m_linkedHashMap.get(String.valueOf(iDictId)));
    }

    /**
     * 根据大类编号获取相应字典小类信息的Hash表
     * 
     * @param iDictId
     *            大类编号
     * @param nParentId
     *            上级编号
     * @return Hashtable 大类对应的小类hash表（单个元素是Dict_DT)
     */
    public static LinkedHashMap getDictHashh(int iDictId, int nParentId) {
        return (LinkedHashMap) (m_linkedHashMapComplex.get(iDictId + ":" + nParentId));
    }

    /**
     * 根据大类编号和地市编码获取相应字典小类信息的Hash表 2011-03-17 JINYH
     * 
     * @param iDictId
     *            大类编号
     * @param cityId
     *            地市编码
     * @return Hashtable 大类对应的小类hash表（单个元素是Dict_DT)
     */
    public static LinkedHashMap getDictHashh(int iDictId, String cityId) {
        LinkedHashMap cityMap = (LinkedHashMap) m_linkedHashMap.get(iDictId);
        if (null == cityMap) {
            cityMap = new LinkedHashMap();
        }
        return (LinkedHashMap) (cityMap.get(cityId));
    }

    /**
     * 根据字典大类，数据标示获取指定名称
     * 
     * @param nDictId
     *            字典大类编号
     * @param nValue
     *            字典小类编号
     * @return 描述信息
     */
    public static String getValueDescs(int nDictId, int nValue) {
        return getValueDescs(nDictId, nValue, "");
    }

    /**
     * 根据字典大类，数据标示获取指定名称
     * 
     * @param nDictId
     *            字典大类编号
     * @param nParentId
     *            上级编号
     * @param nValue
     *            字典小类编号
     * @return 描述信息
     */
    public static String getValueDescs(int nDictId, int nParentId, int nValue) {
        return getValueDescs(nDictId, nParentId, nValue, "");
    }

    /**
     * 根据字典大类，数据标示获取指定名称
     * 
     * @param nDictId
     *            字典大类编号
     * @param nValue
     *            字典小类编号
     * @param sDefault
     *            无相应名称是返回内容
     * @return 描述信息
     */
    public static String getValueDescs(int nDictId, String nValue, String sDefault) {
        if (nValue == null) {
            return sDefault;
        }
        try {
            return getValueDescs(nDictId, Integer.parseInt(nValue), sDefault);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return sDefault;
        }
    }

    /**
     * 根据字典大类，数据标示获取指定名称
     * 
     * @param nDictId
     *            字典大类编号
     * @param nParentId
     *            上级编号
     * @param nValue
     *            字典小类编号
     * @param sDefault
     *            无相应名称是返回内容
     * @return 描述信息
     */
    public static String getValueDescs(int nDictId, int nParentId, String nValue, String sDefault) {
        if (nValue == null) {
            return sDefault;
        }
        try {
            return getValueDescs(nDictId, nParentId, Integer.parseInt(nValue), sDefault);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return sDefault;
        }
    }

    /**
     * 根据字典大类，数据标示获取指定名称
     * 
     * @param nDictId
     *            字典大类编号
     * @param nValue
     *            字典小类编号
     * @param sDefault
     *            无相应名称是返回内容
     * @return 描述信息
     */
    public static String getValueDescs(int nDictId, int nValue, String sDefault) {
        LinkedHashMap m_subHash = (LinkedHashMap) m_linkedHashMap.get(String.valueOf(nDictId));
        if (m_subHash == null) {
            return sDefault;
        } else {
            Object o = m_subHash.get(String.valueOf(nValue));
            if (o != null) {
                return (String) o;
            } else {
                return sDefault;
            }
        }
    }
    


    /**
     * 根据字典大类，数据标示获取指定名称 2011-03-16 JINYH，增加地市编码查找
     * 
     * @param cityId
     *            地市编号
     * @param nDictId
     *            字典大类编号
     * @param nValue
     *            字典小类编号
     * @param sDefault
     *            无相应名称是返回内容
     * @return 描述信息
     */
    public static String getValueDescs(String cityId, int nDictId, int nValue, String sDefault) {
        LinkedHashMap city_subHash = (LinkedHashMap) m_linkedHashMap.get(String.valueOf(nDictId));
        LinkedHashMap m_subHash = null;
        if (city_subHash == null) {
            return sDefault;
        } else {
            m_subHash = (LinkedHashMap) city_subHash.get(String.valueOf(cityId));
            if (m_subHash == null) {
                return sDefault;
            } else {
                Object o = m_subHash.get(String.valueOf(nValue));
                if (o != null) {
                    return (String) o;
                } else {
                    return sDefault;
                }
            }
        }
    }

    /**
     * 根据字典大类，数据标示获取指定名称
     * 
     * @param nDictId
     *            字典大类编号
     * @param nParentId
     *            上级编号
     * @param nValue
     *            字典小类编号
     * @param sDefault
     *            无相应名称是返回内容
     * @return 描述信息
     */
    public static String getValueDescs(int nDictId, int nParentId, int nValue, String sDefault) {
        LinkedHashMap m_subHash = (LinkedHashMap) m_linkedHashMapComplex.get(nDictId + ":" + nParentId);
        if (m_subHash == null) {
            return sDefault;
        } else {
            Object o = m_subHash.get(String.valueOf(nValue));
            if (o != null) {
                return (String) o;
            } else {
                return sDefault;
            }
        }
    }

    /**
     * 根据大类编号获取相应字典小类信息的Hash表
     * 
     * @param nDictId
     *            int 大类编号
     * @param sName
     *            select控件名称
     * @param nSelectNum
     *            选择项
     * @return Hashtable 大类对应的小类hash表（单个元素是Dict_DT)
     */
    public static String getSelectObj(int nDictId, String sName, int nSelectNum) {

        return getSelectObj(nDictId, sName, true, false, null, nSelectNum, null, null, null, -1, null);
    }

    /**
     * 根据大类编号获取相应字典小类信息的Hash表
     * 
     * @param nDictId
     *            int 大类编号
     * @param sName
     *            select控件名称
     * @param hasObjHead
     *            是否包换对象头 false 纯数据，仅option对象 true 含select和options对象
     * @param isMultiple
     *            是否支持多选 false 单选 true 多选 为－1 ,则采用默认值
     * @param strClass
     *            css类名称 为null 则采用默认值
     * @return Hashtable 大类对应的小类hash表（单个元素是Dict_DT)
     */
    public static String getSelectObj(int nDictId, String sName, boolean hasObjHead, boolean isMultiple, String strClass) {

        return getSelectObj(nDictId, sName, hasObjHead, isMultiple, null, -1, null, null, null, -1, strClass);
    }

    /**
     * 根据大类编号获取相应字典小类信息的Hash表
     * 
     * @param nDictId
     *            int 大类编号
     * @param sName
     *            select控件名称
     * @param hasObjHead
     *            是否包换对象头 false 纯数据，仅option对象 true 含select和options对象
     * @param strSelectedValue
     *            默认选中值集合 可包含多个选中字，由Dict_Dt.SPLIT_CHAR分割
     * @return Hashtable 大类对应的小类hash表（单个元素是Dict_DT)
     */
    public static String getSelectObj(int nDictId, String sName, boolean hasObjHead, String strSelectedValue) {

        return getSelectObj(nDictId, sName, hasObjHead, false, strSelectedValue, -1, null, null, null, -1, null);
    }

    /**
     * 根据大类编号和地市编码获取相应字典小类信息的Hash表
     * 
     * @param nDictId
     *            int 大类编号
     * @param sName
     *            select控件名称
     * @param hasObjHead
     *            是否包换对象头 false 纯数据，仅option对象 true 含select和options对象
     * @param strSelectedValue
     *            默认选中值集合 可包含多个选中字，由Dict_Dt.SPLIT_CHAR分割
     * @param cityId
     *            String 地市编号
     * @return Hashtable 大类对应的小类hash表（单个元素是Dict_DT)
     */
    public static String getSelectObj(int nDictId, String sName, boolean hasObjHead, String strSelectedValue, String cityId) {

        return getSelectObj(nDictId, -1, sName, hasObjHead, false, strSelectedValue, -1, null, null, null, -1, null, cityId);
    }

    /**
     * 根据大类编号获取相应字典小类信息的Hash表(纯数据)
     * 
     * @param nDictId
     *            int 大类编号
     * @param strSelectedValue
     *            默认选中值集合 可包含多个选中字，由Dict_Dt.SPLIT_CHAR分割
     * @return Hashtable 大类对应的小类hash表（单个元素是Dict_DT)
     */
    public static String getSelectObj(int nDictId, String strSelectedValue) {

        return getSelectObj(nDictId, null, false, false, strSelectedValue, -1, null, null, null, -1, null);
    }

    /**
     * 根据字典标识 生成HTML页面的SELECT表单 ,提供一个附加的选项
     * 
     * @param iDictId
     *            字典组标识
     * @param hasObjHead
     *            是否包换对象头 0 纯数据，仅option对象 1 含select和options对象
     * @param strObjName
     *            select控件对应的name
     * @param strSelectedValue
     *            默认选中值集合 可包含多个选中字，由Dict_Dt.SPLIT_CHAR分割
     * @param iSize
     *            大小 为－1 ,则采用默认值
     * @param strClass
     *            css类名称 为null 则采用默认值
     * @return 返回生成下拉表单的代码
     */
    public static String getSelectObj(int iDictId, String strObjName, boolean hasObjHead, String strSelectedValue, int iSize, String strClass) {
        return getSelectObj(iDictId, strObjName, hasObjHead, false, strSelectedValue, -1, null, null, null, iSize, strClass);
    }

    /**
     * 根据字典标识 生成HTML页面的SELECT表单 ,提供一个附加的选项
     * 
     * @param iDictId
     *            字典组标识
     * @param hasObjHead
     *            是否包换对象头 false 纯数据，仅option对象 true 含select和options对象
     * @param strObjName
     *            select控件对应的name
     * @param isMultiple
     *            是否支持多选 false 单选 true 多选
     * @param iSelectNum
     *            默认选中值option的循序号 不选择时为-1
     * @param strSelectedValue
     *            默认选中值集合 可包含多个选中字，由Dict_Dt.SPLIT_CHAR分割
     * @param strFilter
     *            过滤项集合 可包含多个过滤值，由Dict_Dt.SPLIT_CHAR分割
     * @param strValue
     *            附加选项值 为null 则无附件项
     * @param strName
     *            附加选项名称 当strValue!=null时生效
     * @param iSize
     *            大小 为－1 ,则采用默认值
     * @param strClass
     *            css类名称 为null 则采用默认值
     * @return 返回生成下拉表单的代码
     */
    public static String getSelectObj(int iDictId, String strObjName, boolean hasObjHead, boolean isMultiple, String strSelectedValue,
            int iSelectNum, String strFilter, String strValue, String strName, int iSize, String strClass) {

        return getSelectObj(iDictId, -1, strObjName, hasObjHead, isMultiple, strSelectedValue, iSelectNum, strFilter, strValue, strName, iSize,
                strClass);
    }

    /**
     * 根据字典标识 生成HTML页面的SELECT表单 ,提供一个附加的选项
     * 
     * @param iDictId
     *            字典组标识
     * @param nParent
     *            上级标示 =-1时无效
     * @param hasObjHead
     *            是否包换对象头 false 纯数据，仅option对象 true 含select和options对象
     * @param strObjName
     *            select控件对应的name
     * @param isMultiple
     *            是否支持多选 false 单选 true 多选
     * @param iSelectNum
     *            默认选中值option的循序号 不选择时为-1
     * @param strSelectedValue
     *            默认选中值集合 可包含多个选中字，DictMgmt.SPLIT_CHAR分割
     * @param strFilter
     *            过滤项集合 可包含多个过滤值，DictMgmt.SPLIT_CHAR分割
     * @param strValue
     *            附加选项值 为null 则无附件项
     * @param strName
     *            附加选项名称 当strValue!=null时生效
     * @param iSize
     *            大小 为－1 ,则采用默认值
     * @param strClass
     *            css类名称 为null 则采用默认值
     * @return 返回生成下拉表单的代码
     */
    public static String getSelectObj(int iDictId, int nParent, String strObjName, boolean hasObjHead, boolean isMultiple, String strSelectedValue,
            int iSelectNum, String strFilter, String strValue, String strName, int iSize, String strClass) {
        LinkedHashMap hashMap;
        if (nParent == -1) {
            if (m_linkedHashMap == null) {
                m_linkedHashMap = new LinkedHashMap();
            }
            hashMap = (LinkedHashMap) m_linkedHashMap.get(String.valueOf(iDictId));
        } else {
            if (m_linkedHashMapComplex == null) {
                m_linkedHashMapComplex = new LinkedHashMap();
            }
            hashMap = (LinkedHashMap) m_linkedHashMapComplex.get(iDictId + ":" + nParent);
        }

        StringBuffer buffer;
        StringBuffer head;
        int i = 0;
        if (hashMap == null) {
            return "<select name=\"" + strObjName + "\"></select>";
        } else {
            buffer = new StringBuffer();
            if (null != strValue) {
                i++;
                buffer.append("<option value=\"").append(strValue).append("\"");
                if ((strSelectedValue != null && isExistKey(strValue, strSelectedValue)) || iSelectNum == i) {
                    buffer.append(" selected ");
                }
                buffer.append(">");
                buffer.append(strName);
                buffer.append("</option>\n");
            }
            Object[] set = hashMap.keySet().toArray();
            for (int j = 0; j < set.length; j++) {
                String as = set[j].toString();
                i++;
                if (strFilter != null && isExistKey(as, strFilter)) {
                    continue;
                }

                buffer.append("<option value=\"").append(as).append("\"");
                if ((strSelectedValue != null && isExistKey(as, strSelectedValue)) || iSelectNum == i) {
                    buffer.append(" selected ");
                }
                buffer.append(">");
                buffer.append(hashMap.get(as));
                buffer.append("</option>\n");
            }
            if (hasObjHead) {
                // 组合SELECT条件头
                head = new StringBuffer();
                head.append("<select name=\"").append(strObjName).append("\"");
                // 如果允许多选
                if (isMultiple) {
                    head.append(" multiple=\"true\"");
                }

                if (iSize != -1) {
                    head.append(" size=\"").append(iSize).append("\"");
                }

                if (strClass != null) {
                    head.append(" class=\"").append(strClass).append("\"");
                }
                head.append(">");
                // 组合SELECT条件尾
                buffer.append("</select>");
                // buffer.insert(0, head);
                buffer.insert(0, head.toString());
            }
            return buffer.toString();
        }
    }

    /**
     * 根据字典标识 生成HTML页面的SELECT表单 ,提供一个附加的选项
     * 
     * @param iDictId
     *            字典组标识
     * @param nParent
     *            上级标示 =-1时无效
     * @param hasObjHead
     *            是否包换对象头 false 纯数据，仅option对象 true 含select和options对象
     * @param strObjName
     *            select控件对应的name
     * @param isMultiple
     *            是否支持多选 false 单选 true 多选
     * @param iSelectNum
     *            默认选中值option的循序号 不选择时为-1
     * @param strSelectedValue
     *            默认选中值集合 可包含多个选中字，DictMgmt.SPLIT_CHAR分割
     * @param strFilter
     *            过滤项集合 可包含多个过滤值，DictMgmt.SPLIT_CHAR分割
     * @param strValue
     *            附加选项值 为null 则无附件项
     * @param strName
     *            附加选项名称 当strValue!=null时生效
     * @param iSize
     *            大小 为－1 ,则采用默认值
     * @param strClass
     *            css类名称 为null 则采用默认值
     * @param cityId
     *            String 地市编码
     * 
     * @return 返回生成下拉表单的代码
     */
    public static String getSelectObj(int iDictId, int nParent, String strObjName, boolean hasObjHead, boolean isMultiple, String strSelectedValue,
            int iSelectNum, String strFilter, String strValue, String strName, int iSize, String strClass, String cityId) {
        LinkedHashMap hashMap;
        LinkedHashMap hashCityMap;
        if (nParent == -1) {
            if (m_linkedHashMap == null) {
                m_linkedHashMap = new LinkedHashMap();
            }
            hashCityMap = (LinkedHashMap) m_linkedHashMap.get(String.valueOf(iDictId)); // 取地市数据
            hashMap = (LinkedHashMap) hashCityMap.get(String.valueOf(cityId)); // 取值
        } else {
            if (m_linkedHashMapComplex == null) {
                m_linkedHashMapComplex = new LinkedHashMap();
            }
            hashMap = (LinkedHashMap) m_linkedHashMapComplex.get(iDictId + ":" + nParent);
        }

        StringBuffer buffer;
        StringBuffer head;
        int i = 0;
        if (hashMap == null) {
            return "<select name=\"" + strObjName + "\"></select>";
        } else {
            buffer = new StringBuffer();
            if (null != strValue) {
                i++;
                buffer.append("<option value=\"").append(strValue).append("\"");
                if ((strSelectedValue != null && isExistKey(strValue, strSelectedValue)) || iSelectNum == i) {
                    buffer.append(" selected ");
                }
                buffer.append(">");
                buffer.append(strName);
                buffer.append("</option>\n");
            }
            Object[] set = hashMap.keySet().toArray();
            for (int j = 0; j < set.length; j++) {
                String as = set[j].toString();
                i++;
                if (strFilter != null && isExistKey(as, strFilter)) {
                    continue;
                }

                buffer.append("<option value=\"").append(as).append("\"");
                if ((strSelectedValue != null && isExistKey(as, strSelectedValue)) || iSelectNum == i) {
                    buffer.append(" selected ");
                }
                buffer.append(">");
                buffer.append(hashMap.get(as));
                buffer.append("</option>\n");
            }
            if (hasObjHead) {
                // 组合SELECT条件头
                head = new StringBuffer();
                head.append("<select name=\"").append(strObjName).append("\"");
                // 如果允许多选
                if (isMultiple) {
                    head.append(" multiple=\"true\"");
                }

                if (iSize != -1) {
                    head.append(" size=\"").append(iSize).append("\"");
                }

                if (strClass != null) {
                    head.append(" class=\"").append(strClass).append("\"");
                }
                head.append(">");
                // 组合SELECT条件尾
                buffer.append("</select>");
                // buffer.insert(0, head);
                buffer.insert(0, head.toString());
            }
            return buffer.toString();
        }
    }

    /**
     * 判断传入的KEY值在集合中是否存在
     * 
     * @param strKey
     *            关键字（String）
     * @param strKeySet
     *            关键字串，包含多个关键字，由Dict_Dt.SPLIT_CHAR分割
     * @return 返回true是 要被过滤；返回false不要被过滤
     */
    private static boolean isExistKey(String strKey, String strKeySet) {

        if (strKey.equals(strKeySet)) {
            return true;
        }

        if (!"".equals(strKeySet) && strKeySet.indexOf(SPLIT_CHAR) != -1) {
            String[] filterKeyArray = strKeySet.split(SPLIT_CHAR);

            for (int i = 0; i < filterKeyArray.length; i++) {
                if (filterKeyArray[i].equals(strKey)) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;

    }
    
    public Map getDict(int dict_id,int parent_id)
	{
    	Map resultMap = new TreeMap();
		List<DictInfo> dictList = null;
		DictInfo dictInfo = null;
		SqlMapClient smc = null;
		try
		{
			HashMap param = new HashMap();
			param.put("dict_id", dict_id);
			param.put("parent_id", parent_id);
			
			smc = getSqlMapClient();
		
			dictList = smc.queryForList("systemLoginSql.getDictList", param);
			if (dictList != null)
			{
				for (int i=0;i<dictList.size();i++)
				{
					dictInfo = dictList.get(i);
					resultMap.put(dictInfo.getIValue(), dictInfo.getStrValueDesc());
				}	
			}
			
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			this.endTransaction(smc);
		}
		return resultMap;
	}
    public static String getNameBysno(String sno){
    	SqlMapClient smc = null;
    	String name ="";
    	try{
    		
    		HashMap param = new HashMap();
			param.put("sno", sno);
			smc = SystemTool.getSqlMapClient();
			name = String.valueOf(smc.queryForObject("systemLoginSql.getOPeratorName", param));
    	}catch(Exception e)
		{
    		System.out.println(e.getMessage());
		}finally{
			try {
				smc.endTransaction();
			} catch (SQLException e) {
				 System.out.println(e.getMessage());
			} 
		}
		return name; 
    }
    

}
