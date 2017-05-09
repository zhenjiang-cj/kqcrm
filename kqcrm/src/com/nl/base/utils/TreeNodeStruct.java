package com.nl.base.utils;

/**
 * <p>Title: ������WCS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: �����´�½</p>
 *
 * @version 1.0
 */

public class TreeNodeStruct {
    /**  ��ʶ�Ƿ�Ϊ���׽ڵ� */
    private boolean fatherNode;
    /**  �ڵ����� */
    private String name;
    /**  �ڵ��ʶ */
    private long id;
    /**  �ڵ��� */
    private int level;
    /**  ��ʶ��ͬһ�����Ƿ�Ϊ���ڵ� */
    private boolean lastNode;
    /**
     * �˵�����
     */
    private String title;

    /**
     * ����һ���յ����ڵ����
     */
    public TreeNodeStruct() {

    }

    /**
     * ���ݸ��������ݴ���һ�����ڵ����
     *
     * @param fatherNode �Ƿ�Ϊ���׽ڵ�Ĳ���ֵ
     * @param name �ڵ�����
     * @param id �ڵ��ʶ
     * @param level �ڵ�Ĳ��
     * @param isLast ��ͬһ�����Ƿ�Ϊ���ڵ�Ĳ���ֵ
     */
    public TreeNodeStruct(boolean fatherNode,
                          String name,
                          long id,
                          int level,
                          boolean isLast,
                          String title) {
        this.fatherNode = fatherNode;
        this.name = name;
        this.id = id;
        this.level = level;
        this.lastNode = isLast;
        this.title = title;
    }

    /**
     * �����Ƿ��׽ڵ�
     *
     * @param father �Ƿ��׽ڵ�Ĳ���ֵ
     */
    public void setFatherNode(boolean father) {
        this.fatherNode = father;
    }

    /**
     * �ж��Ƿ�Ϊ���׽ڵ�
     *
     * @return �Ƿ��׽ڵ�Ĳ���ֵ
     */
    public boolean isFather() {
        return this.fatherNode;
    }

    /**
     * ���ýڵ�����
     *
     * @param name �ڵ�����
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ȡ�ýڵ�����
     *
     * @return �ڵ�����
     */
    public String getName() {
        return this.name;
    }

    /**
     * ���ýڵ��ʶ
     *
     * @param id �ڵ��ʶ
     */
    public void setID(long id) {
        this.id = id;
    }

    /**
     * ȡ�ýڵ��ʶ
     *
     * @return �ڵ��ʶ
     */
    public long getID() {
        return this.id;
    }

    /**
     * ���ýڵ���
     *
     * @param level �ڵ���
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * ȡ�õ�ǰ�ڵ�Ĳ��
     *
     * @return �ڵ���
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * �����Ƿ����ڵ�
     *
     * @param last �Ƿ����ڵ�Ĳ���ֵ
     */
    public void setLastNode(boolean last) {
        this.lastNode = last;
    }

    /**
     * �ж���ͬһ�����Ƿ�Ϊ���ڵ�
     *
     * @return �Ƿ����ڵ�Ĳ���ֵ
     */
    public boolean isLast() {
        return this.lastNode;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
