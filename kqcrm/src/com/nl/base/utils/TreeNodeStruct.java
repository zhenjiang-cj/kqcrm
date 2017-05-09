package com.nl.base.utils;

/**
 * <p>Title: 镇江人寿WCS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 江苏新大陆</p>
 *
 * @version 1.0
 */

public class TreeNodeStruct {
    /**  标识是否为父亲节点 */
    private boolean fatherNode;
    /**  节点名称 */
    private String name;
    /**  节点标识 */
    private long id;
    /**  节点层次 */
    private int level;
    /**  标识在同一层中是否为最后节点 */
    private boolean lastNode;
    /**
     * 菜单描述
     */
    private String title;

    /**
     * 创建一个空的树节点对象
     */
    public TreeNodeStruct() {

    }

    /**
     * 根据给定的数据创建一个树节点对象
     *
     * @param fatherNode 是否为父亲节点的布尔值
     * @param name 节点名称
     * @param id 节点标识
     * @param level 节点的层次
     * @param isLast 在同一层中是否为最后节点的布尔值
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
     * 设置是否父亲节点
     *
     * @param father 是否父亲节点的布尔值
     */
    public void setFatherNode(boolean father) {
        this.fatherNode = father;
    }

    /**
     * 判断是否为父亲节点
     *
     * @return 是否父亲节点的布尔值
     */
    public boolean isFather() {
        return this.fatherNode;
    }

    /**
     * 设置节点名称
     *
     * @param name 节点名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得节点名称
     *
     * @return 节点名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置节点标识
     *
     * @param id 节点标识
     */
    public void setID(long id) {
        this.id = id;
    }

    /**
     * 取得节点标识
     *
     * @return 节点标识
     */
    public long getID() {
        return this.id;
    }

    /**
     * 设置节点层次
     *
     * @param level 节点层次
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 取得当前节点的层次
     *
     * @return 节点层次
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * 设置是否最后节点
     *
     * @param last 是否最后节点的布尔值
     */
    public void setLastNode(boolean last) {
        this.lastNode = last;
    }

    /**
     * 判断在同一层中是否为最后节点
     *
     * @return 是否最后节点的布尔值
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
