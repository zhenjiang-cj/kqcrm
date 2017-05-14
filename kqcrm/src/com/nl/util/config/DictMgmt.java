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
    public static final String SPLIT_CHAR = ";";// �ָ���
    private static Logger logger = Logger.getLogger(DictMgmt.class);
    private Connection conn = null;

    /*ϵͳ�ֵ�*/
    public static final int DICT_SYS_SYSTEM_ID = 1009;//ϵͳ���             
    public static final int DICT_SYS_VALID_FLAG = 1001;//��Ч״̬
    public static final int DICT_DOC_TYPE = 1005;//��������
    

    public static final int DICT_KQ_PROVINCES=1010;//ʡ��
    public static final int DICT_KQ_CITY=1011;//����
    public static final int DICT_kq_REGION=1012;//����
     
    
    public static DictMgmt instance = new DictMgmt();

    /**
     * �洢�����ֵ���� *
     */
    private static LinkedHashMap m_linkedHashMap = null;
    private static LinkedHashMap m_linkedHashMapComplex = null;

    /**
     * te static DictMgmt instance = new DictMgmt();
     * 
     * 
     * private DictMgmt() { super(); }
     * 
     * /** ��ȡ��һʵ��
     * 
     * @return DictMgmt
     */
    public static DictMgmt getInstance() {
        return instance;
    }

    /**
     * ��ȡ�ֵ����Ϣ�ķ���
     * 
     * @return ArrayList �ֵ���б�
     */
    public List loadDict() {
        StringBuffer oSql = new StringBuffer();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DictInfo info = null;
        ArrayList list = null;
        oSql.append(" select t.dict_id,t.value,t.dict_name,t.value_name,t.parent_id,t.sort_id,t.remark,t.is_modify,count(*) over() num ");
        oSql.append(" from kq_dict_cfg t where t.is_valid = 1 order by t.sort_id ");
        try {
            stmt = conn.prepareStatement(oSql.toString());
            // ִ��SQL���
            logger.debug("********* portalzj ��ȡ�ֵ������->sql:" + oSql);
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
     * ��ȡ�ֵ����Ϣ�ķ���
     * 
     * @return ArrayList �ֵ���б�
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
            // ִ��SQL���
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
     * ��ϵͳLoad�ֵ����Ϣ
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
                logger.error("��ϵͳLoad�ֵ����Ϣ�ع�ʧ��");
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
     * ���ݴ���͸���id�õ�value 2009.02.04 liuyu
     * 
     * @param dict_id
     *            �ֵ��ʾ
     * @param parent_id
     *            ����ʾ
     * @return �ֵ�ֵ
     */

    public static int getValue(int dict_id, int parent_id) {
        Map map = getDictHashh(dict_id, parent_id);
        if (map == null || map.keySet() == null || map.keySet().size() == 0) {
            return 0;
        }

        return Integer.parseInt(((map.keySet().toArray())[0]).toString());
    }

    /**
     * ���ݴ���͵��б���õ�value 2011.03.17 JINYH
     * 
     * @param dict_id
     *            �ֵ��ʾ
     * @param parent_id
     *            ����ʶ
     * @param city_id
     *            ���б���
     * 
     * @return �ֵ�ֵ
     */

    public static int getValue(int dict_id, String city_id) {
        Map map = getDictHashh(dict_id, city_id);
        if (map == null || map.keySet() == null || map.keySet().size() == 0) {
            return 0;
        }

        return Integer.parseInt(((map.keySet().toArray())[0]).toString());
    }

    /**
     * ���ݴ����Ż�ȡ��Ӧ�ֵ�С����Ϣ��Hash��
     * 
     * @param iDictId
     *            int ������
     * @return Hashtable �����Ӧ��С��hash������Ԫ����Dict_DT)
     */
    public static LinkedHashMap getDictHashh(int iDictId) {
        return (LinkedHashMap) (m_linkedHashMap.get(String.valueOf(iDictId)));
    }

    /**
     * ���ݴ����Ż�ȡ��Ӧ�ֵ�С����Ϣ��Hash��
     * 
     * @param iDictId
     *            ������
     * @param nParentId
     *            �ϼ����
     * @return Hashtable �����Ӧ��С��hash������Ԫ����Dict_DT)
     */
    public static LinkedHashMap getDictHashh(int iDictId, int nParentId) {
        return (LinkedHashMap) (m_linkedHashMapComplex.get(iDictId + ":" + nParentId));
    }

    /**
     * ���ݴ����ź͵��б����ȡ��Ӧ�ֵ�С����Ϣ��Hash�� 2011-03-17 JINYH
     * 
     * @param iDictId
     *            ������
     * @param cityId
     *            ���б���
     * @return Hashtable �����Ӧ��С��hash������Ԫ����Dict_DT)
     */
    public static LinkedHashMap getDictHashh(int iDictId, String cityId) {
        LinkedHashMap cityMap = (LinkedHashMap) m_linkedHashMap.get(iDictId);
        if (null == cityMap) {
            cityMap = new LinkedHashMap();
        }
        return (LinkedHashMap) (cityMap.get(cityId));
    }

    /**
     * �����ֵ���࣬���ݱ�ʾ��ȡָ������
     * 
     * @param nDictId
     *            �ֵ������
     * @param nValue
     *            �ֵ�С����
     * @return ������Ϣ
     */
    public static String getValueDescs(int nDictId, int nValue) {
        return getValueDescs(nDictId, nValue, "");
    }

    /**
     * �����ֵ���࣬���ݱ�ʾ��ȡָ������
     * 
     * @param nDictId
     *            �ֵ������
     * @param nParentId
     *            �ϼ����
     * @param nValue
     *            �ֵ�С����
     * @return ������Ϣ
     */
    public static String getValueDescs(int nDictId, int nParentId, int nValue) {
        return getValueDescs(nDictId, nParentId, nValue, "");
    }

    /**
     * �����ֵ���࣬���ݱ�ʾ��ȡָ������
     * 
     * @param nDictId
     *            �ֵ������
     * @param nValue
     *            �ֵ�С����
     * @param sDefault
     *            ����Ӧ�����Ƿ�������
     * @return ������Ϣ
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
     * �����ֵ���࣬���ݱ�ʾ��ȡָ������
     * 
     * @param nDictId
     *            �ֵ������
     * @param nParentId
     *            �ϼ����
     * @param nValue
     *            �ֵ�С����
     * @param sDefault
     *            ����Ӧ�����Ƿ�������
     * @return ������Ϣ
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
     * �����ֵ���࣬���ݱ�ʾ��ȡָ������
     * 
     * @param nDictId
     *            �ֵ������
     * @param nValue
     *            �ֵ�С����
     * @param sDefault
     *            ����Ӧ�����Ƿ�������
     * @return ������Ϣ
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
     * �����ֵ���࣬���ݱ�ʾ��ȡָ������ 2011-03-16 JINYH�����ӵ��б������
     * 
     * @param cityId
     *            ���б��
     * @param nDictId
     *            �ֵ������
     * @param nValue
     *            �ֵ�С����
     * @param sDefault
     *            ����Ӧ�����Ƿ�������
     * @return ������Ϣ
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
     * �����ֵ���࣬���ݱ�ʾ��ȡָ������
     * 
     * @param nDictId
     *            �ֵ������
     * @param nParentId
     *            �ϼ����
     * @param nValue
     *            �ֵ�С����
     * @param sDefault
     *            ����Ӧ�����Ƿ�������
     * @return ������Ϣ
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
     * ���ݴ����Ż�ȡ��Ӧ�ֵ�С����Ϣ��Hash��
     * 
     * @param nDictId
     *            int ������
     * @param sName
     *            select�ؼ�����
     * @param nSelectNum
     *            ѡ����
     * @return Hashtable �����Ӧ��С��hash������Ԫ����Dict_DT)
     */
    public static String getSelectObj(int nDictId, String sName, int nSelectNum) {

        return getSelectObj(nDictId, sName, true, false, null, nSelectNum, null, null, null, -1, null);
    }

    /**
     * ���ݴ����Ż�ȡ��Ӧ�ֵ�С����Ϣ��Hash��
     * 
     * @param nDictId
     *            int ������
     * @param sName
     *            select�ؼ�����
     * @param hasObjHead
     *            �Ƿ��������ͷ false �����ݣ���option���� true ��select��options����
     * @param isMultiple
     *            �Ƿ�֧�ֶ�ѡ false ��ѡ true ��ѡ Ϊ��1 ,�����Ĭ��ֵ
     * @param strClass
     *            css������ Ϊnull �����Ĭ��ֵ
     * @return Hashtable �����Ӧ��С��hash������Ԫ����Dict_DT)
     */
    public static String getSelectObj(int nDictId, String sName, boolean hasObjHead, boolean isMultiple, String strClass) {

        return getSelectObj(nDictId, sName, hasObjHead, isMultiple, null, -1, null, null, null, -1, strClass);
    }

    /**
     * ���ݴ����Ż�ȡ��Ӧ�ֵ�С����Ϣ��Hash��
     * 
     * @param nDictId
     *            int ������
     * @param sName
     *            select�ؼ�����
     * @param hasObjHead
     *            �Ƿ��������ͷ false �����ݣ���option���� true ��select��options����
     * @param strSelectedValue
     *            Ĭ��ѡ��ֵ���� �ɰ������ѡ���֣���Dict_Dt.SPLIT_CHAR�ָ�
     * @return Hashtable �����Ӧ��С��hash������Ԫ����Dict_DT)
     */
    public static String getSelectObj(int nDictId, String sName, boolean hasObjHead, String strSelectedValue) {

        return getSelectObj(nDictId, sName, hasObjHead, false, strSelectedValue, -1, null, null, null, -1, null);
    }

    /**
     * ���ݴ����ź͵��б����ȡ��Ӧ�ֵ�С����Ϣ��Hash��
     * 
     * @param nDictId
     *            int ������
     * @param sName
     *            select�ؼ�����
     * @param hasObjHead
     *            �Ƿ��������ͷ false �����ݣ���option���� true ��select��options����
     * @param strSelectedValue
     *            Ĭ��ѡ��ֵ���� �ɰ������ѡ���֣���Dict_Dt.SPLIT_CHAR�ָ�
     * @param cityId
     *            String ���б��
     * @return Hashtable �����Ӧ��С��hash������Ԫ����Dict_DT)
     */
    public static String getSelectObj(int nDictId, String sName, boolean hasObjHead, String strSelectedValue, String cityId) {

        return getSelectObj(nDictId, -1, sName, hasObjHead, false, strSelectedValue, -1, null, null, null, -1, null, cityId);
    }

    /**
     * ���ݴ����Ż�ȡ��Ӧ�ֵ�С����Ϣ��Hash��(������)
     * 
     * @param nDictId
     *            int ������
     * @param strSelectedValue
     *            Ĭ��ѡ��ֵ���� �ɰ������ѡ���֣���Dict_Dt.SPLIT_CHAR�ָ�
     * @return Hashtable �����Ӧ��С��hash������Ԫ����Dict_DT)
     */
    public static String getSelectObj(int nDictId, String strSelectedValue) {

        return getSelectObj(nDictId, null, false, false, strSelectedValue, -1, null, null, null, -1, null);
    }

    /**
     * �����ֵ��ʶ ����HTMLҳ���SELECT�� ,�ṩһ�����ӵ�ѡ��
     * 
     * @param iDictId
     *            �ֵ����ʶ
     * @param hasObjHead
     *            �Ƿ��������ͷ 0 �����ݣ���option���� 1 ��select��options����
     * @param strObjName
     *            select�ؼ���Ӧ��name
     * @param strSelectedValue
     *            Ĭ��ѡ��ֵ���� �ɰ������ѡ���֣���Dict_Dt.SPLIT_CHAR�ָ�
     * @param iSize
     *            ��С Ϊ��1 ,�����Ĭ��ֵ
     * @param strClass
     *            css������ Ϊnull �����Ĭ��ֵ
     * @return ���������������Ĵ���
     */
    public static String getSelectObj(int iDictId, String strObjName, boolean hasObjHead, String strSelectedValue, int iSize, String strClass) {
        return getSelectObj(iDictId, strObjName, hasObjHead, false, strSelectedValue, -1, null, null, null, iSize, strClass);
    }

    /**
     * �����ֵ��ʶ ����HTMLҳ���SELECT�� ,�ṩһ�����ӵ�ѡ��
     * 
     * @param iDictId
     *            �ֵ����ʶ
     * @param hasObjHead
     *            �Ƿ��������ͷ false �����ݣ���option���� true ��select��options����
     * @param strObjName
     *            select�ؼ���Ӧ��name
     * @param isMultiple
     *            �Ƿ�֧�ֶ�ѡ false ��ѡ true ��ѡ
     * @param iSelectNum
     *            Ĭ��ѡ��ֵoption��ѭ��� ��ѡ��ʱΪ-1
     * @param strSelectedValue
     *            Ĭ��ѡ��ֵ���� �ɰ������ѡ���֣���Dict_Dt.SPLIT_CHAR�ָ�
     * @param strFilter
     *            ������� �ɰ����������ֵ����Dict_Dt.SPLIT_CHAR�ָ�
     * @param strValue
     *            ����ѡ��ֵ Ϊnull ���޸�����
     * @param strName
     *            ����ѡ������ ��strValue!=nullʱ��Ч
     * @param iSize
     *            ��С Ϊ��1 ,�����Ĭ��ֵ
     * @param strClass
     *            css������ Ϊnull �����Ĭ��ֵ
     * @return ���������������Ĵ���
     */
    public static String getSelectObj(int iDictId, String strObjName, boolean hasObjHead, boolean isMultiple, String strSelectedValue,
            int iSelectNum, String strFilter, String strValue, String strName, int iSize, String strClass) {

        return getSelectObj(iDictId, -1, strObjName, hasObjHead, isMultiple, strSelectedValue, iSelectNum, strFilter, strValue, strName, iSize,
                strClass);
    }

    /**
     * �����ֵ��ʶ ����HTMLҳ���SELECT�� ,�ṩһ�����ӵ�ѡ��
     * 
     * @param iDictId
     *            �ֵ����ʶ
     * @param nParent
     *            �ϼ���ʾ =-1ʱ��Ч
     * @param hasObjHead
     *            �Ƿ��������ͷ false �����ݣ���option���� true ��select��options����
     * @param strObjName
     *            select�ؼ���Ӧ��name
     * @param isMultiple
     *            �Ƿ�֧�ֶ�ѡ false ��ѡ true ��ѡ
     * @param iSelectNum
     *            Ĭ��ѡ��ֵoption��ѭ��� ��ѡ��ʱΪ-1
     * @param strSelectedValue
     *            Ĭ��ѡ��ֵ���� �ɰ������ѡ���֣�DictMgmt.SPLIT_CHAR�ָ�
     * @param strFilter
     *            ������� �ɰ����������ֵ��DictMgmt.SPLIT_CHAR�ָ�
     * @param strValue
     *            ����ѡ��ֵ Ϊnull ���޸�����
     * @param strName
     *            ����ѡ������ ��strValue!=nullʱ��Ч
     * @param iSize
     *            ��С Ϊ��1 ,�����Ĭ��ֵ
     * @param strClass
     *            css������ Ϊnull �����Ĭ��ֵ
     * @return ���������������Ĵ���
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
                // ���SELECT����ͷ
                head = new StringBuffer();
                head.append("<select name=\"").append(strObjName).append("\"");
                // ��������ѡ
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
                // ���SELECT����β
                buffer.append("</select>");
                // buffer.insert(0, head);
                buffer.insert(0, head.toString());
            }
            return buffer.toString();
        }
    }

    /**
     * �����ֵ��ʶ ����HTMLҳ���SELECT�� ,�ṩһ�����ӵ�ѡ��
     * 
     * @param iDictId
     *            �ֵ����ʶ
     * @param nParent
     *            �ϼ���ʾ =-1ʱ��Ч
     * @param hasObjHead
     *            �Ƿ��������ͷ false �����ݣ���option���� true ��select��options����
     * @param strObjName
     *            select�ؼ���Ӧ��name
     * @param isMultiple
     *            �Ƿ�֧�ֶ�ѡ false ��ѡ true ��ѡ
     * @param iSelectNum
     *            Ĭ��ѡ��ֵoption��ѭ��� ��ѡ��ʱΪ-1
     * @param strSelectedValue
     *            Ĭ��ѡ��ֵ���� �ɰ������ѡ���֣�DictMgmt.SPLIT_CHAR�ָ�
     * @param strFilter
     *            ������� �ɰ����������ֵ��DictMgmt.SPLIT_CHAR�ָ�
     * @param strValue
     *            ����ѡ��ֵ Ϊnull ���޸�����
     * @param strName
     *            ����ѡ������ ��strValue!=nullʱ��Ч
     * @param iSize
     *            ��С Ϊ��1 ,�����Ĭ��ֵ
     * @param strClass
     *            css������ Ϊnull �����Ĭ��ֵ
     * @param cityId
     *            String ���б���
     * 
     * @return ���������������Ĵ���
     */
    public static String getSelectObj(int iDictId, int nParent, String strObjName, boolean hasObjHead, boolean isMultiple, String strSelectedValue,
            int iSelectNum, String strFilter, String strValue, String strName, int iSize, String strClass, String cityId) {
        LinkedHashMap hashMap;
        LinkedHashMap hashCityMap;
        if (nParent == -1) {
            if (m_linkedHashMap == null) {
                m_linkedHashMap = new LinkedHashMap();
            }
            hashCityMap = (LinkedHashMap) m_linkedHashMap.get(String.valueOf(iDictId)); // ȡ��������
            hashMap = (LinkedHashMap) hashCityMap.get(String.valueOf(cityId)); // ȡֵ
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
                // ���SELECT����ͷ
                head = new StringBuffer();
                head.append("<select name=\"").append(strObjName).append("\"");
                // ��������ѡ
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
                // ���SELECT����β
                buffer.append("</select>");
                // buffer.insert(0, head);
                buffer.insert(0, head.toString());
            }
            return buffer.toString();
        }
    }

    /**
     * �жϴ����KEYֵ�ڼ������Ƿ����
     * 
     * @param strKey
     *            �ؼ��֣�String��
     * @param strKeySet
     *            �ؼ��ִ�����������ؼ��֣���Dict_Dt.SPLIT_CHAR�ָ�
     * @return ����true�� Ҫ�����ˣ�����false��Ҫ������
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
