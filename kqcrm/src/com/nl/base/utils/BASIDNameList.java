package com.nl.base.utils;

import java.util.Vector;

/**
 * <p>Title: 镇江人寿WCS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 江苏新大陆</p>
 *
 * @version 1.0
 */

public class BASIDNameList implements java.io.Serializable {

    /** 标识集合 */
    private Vector idV = null;
    /** 名称集合 */
    private Vector nameV = null;
    /** 描述集合 */
    private Vector descV = null;

    /**
     * 构造函数
     */
    public BASIDNameList() {
        this.idV = new Vector();
        this.nameV = new Vector();
        this.descV = new Vector();
    }

    /**
     * 添加值对。
     * @param id 值的标识
     * @param name 值
     */
    public void addItem(long id, String name) {
        this.idV.add(Long.toString(id));
        this.nameV.add(name);
    }

    /**
     * 添加数据
     * @param id 标识
     * @param name 名称
     * @param desc 描述
     */
    public void addItem(long id, String name, String desc) {
        this.idV.add(Long.toString(id));
        this.nameV.add(name);
        this.descV.add(desc);
    }
    /**
     * 取得元素的总个数。
     * @return int 元素个数
     */
    public int size() {
        return idV == null ? -1 : idV.size();
    }

    /**
     * 通过下标取得标识
     * @param index 下标
     * @return long 标识
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
     * 通过下标取得值。
     * @param index 下标
     * @return String 值
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
     * 通过标识取得对应的值。
     * @param  id 标识
     * @return String  值
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
     * 通过下标取得描述。
     * @param index 下标
     * @return String 描述
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
     * 通过标识取得对应的描述。
     * @param  id 标识
     * @return String  描述
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

