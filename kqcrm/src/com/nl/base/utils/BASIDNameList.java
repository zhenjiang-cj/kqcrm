package com.nl.base.utils;

import java.util.Vector;

/**
 * <p>Title: ������WCS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: �����´�½</p>
 *
 * @version 1.0
 */

public class BASIDNameList implements java.io.Serializable {

    /** ��ʶ���� */
    private Vector idV = null;
    /** ���Ƽ��� */
    private Vector nameV = null;
    /** �������� */
    private Vector descV = null;

    /**
     * ���캯��
     */
    public BASIDNameList() {
        this.idV = new Vector();
        this.nameV = new Vector();
        this.descV = new Vector();
    }

    /**
     * ���ֵ�ԡ�
     * @param id ֵ�ı�ʶ
     * @param name ֵ
     */
    public void addItem(long id, String name) {
        this.idV.add(Long.toString(id));
        this.nameV.add(name);
    }

    /**
     * �������
     * @param id ��ʶ
     * @param name ����
     * @param desc ����
     */
    public void addItem(long id, String name, String desc) {
        this.idV.add(Long.toString(id));
        this.nameV.add(name);
        this.descV.add(desc);
    }
    /**
     * ȡ��Ԫ�ص��ܸ�����
     * @return int Ԫ�ظ���
     */
    public int size() {
        return idV == null ? -1 : idV.size();
    }

    /**
     * ͨ���±�ȡ�ñ�ʶ
     * @param index �±�
     * @return long ��ʶ
     */
    public long getID(int index) {
        //return idV == null ? -1 : Long.parseLong(idV.get(index).toString());
        if(idV == null){
            return -1;
        }
        if(idV.get(index) == null){
            return -1;
        }
        return Long.parseLong(idV.get(index).toString());
    }

    /**
     * ͨ���±�ȡ��ֵ��
     * @param index �±�
     * @return String ֵ
     */
    public String getName(int index) {
        //return nameV == null ? null : nameV.get(index).toString();
        if(nameV == null){
            return null;
        }
        if(nameV.get(index) == null){
            return null;
        }
        return nameV.get(index).toString();
    }

    /**
     * ͨ����ʶȡ�ö�Ӧ��ֵ��
     * @param  id ��ʶ
     * @return String  ֵ
     */
    public String getNameByID(long id) {
        if (this.idV == null || nameV == null) {
            return null;
        }
        if(nameV.get(idV.indexOf(Long.toString(id))) == null){
            return null;
        }
        return nameV.get(idV.indexOf(Long.toString(id))).toString();
    }

    /**
     * ͨ���±�ȡ��������
     * @param index �±�
     * @return String ����
     */
    public String getDesc(int index) {
       //return descV == null ? null : descV.get(index).toString();
        if(descV == null){
            return null;
        }
        if(descV.get(index) == null){
            return null;
        }
        return descV.get(index).toString();
    }
    /**
     * ͨ����ʶȡ�ö�Ӧ��������
     * @param  id ��ʶ
     * @return String  ����
     */
    public String getDescByID(long id) {
        if (this.idV == null || descV == null) {
            return null;
        }
        if(descV.get(idV.indexOf(Long.toString(id))) == null){
            return null;
        }
        return descV.get(idV.indexOf(Long.toString(id))).toString();
    }
}

