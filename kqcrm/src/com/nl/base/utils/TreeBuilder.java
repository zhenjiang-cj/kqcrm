package com.nl.base.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Vector;

/**
 * Copyright (c) 2003, 江苏新大陆  All rights reserved
 *
 * 该类主要是将组织好的数据按照不同的要求组成适当的树的字符串输出。
 * 将来要和treeBuilder合并
 * @author   经营分析开发组
 * @author   其它作者姓名
 * @version  1.03 2003/08/27 葛志磊
 * @version  1.08 2003/10/06 吴鑫
 * 加入读取cookie功能,单选树,
 */
public class TreeBuilder {

    private Vector treeNodes = null;    //节点(TreeNodeStruct)
    private Vector hrefs = null;        //连接url
    private Vector targets = null;      //url,位置
    private Vector titles = null;       //提示信息
    private Vector onClicks = null;    //点击动作
    private Vector isExpand = null;    //是否展开
    private Vector canBeSelect = null; //是否能被选择
    private Vector isSelected = null;  //是否被选择

    //0:缺省；1：单选；2：复选
    private int selectType = 0;

    // false:缺省    true:根据tns[]数组的值设置每一个结点的isFather和isLast属性
    private boolean isSetLastAndFather = false;

    //定义图片；
    private String picURL = "images/tree/";

    private String[] pics = {
        "node.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "space.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "folder_close.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "folder_open.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "noline_close_button.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "top_close_button.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "middle_close_button.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "bottom_close_button.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "noline_open_button.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "top_open_button.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "middle_open_button.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "bottom_open_button.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "vertical_line.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "top_join.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "middle_join.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "bottom_join.gif\" WIDTH=16 HEIGHT=16 BORDER=0 align=\"top\" alt=\"\">",
        "top_join_root.gif\" WIDTH=16 HEIGHT=16 BORDER=0 ALIGN=\"top\" alt=\"\">"
    };

    private int nodeType = 0;
    private int spaceType = 1;
    private int folderCloseType = 2;
    private int folderOpenType = 3;
    private int nolineCloseButtonType = 4;
    private int topCloseButtonType = 5;
    private int middleCloseButtonType = 6;
    private int bottomCloseButtonType = 7;
    private int nolineOpenButtonType = 8;
    private int topOpenButtocType = 9;
    private int middleOpenButtonType = 10;
    private int bottomOpenButtonType = 11;
    private int verticalLineType = 12;
    private int topJoinType = 13;
    private int middleJoinType = 14;
    private int bottomJoinType = 15;
    private int topJoinRootType = 16;


    private int nodeFolderType = this.nodeType;

    public TreeBuilder() {
        this.treeNodes = new Vector();
        this.hrefs = new Vector();
        this.targets = new Vector();
        this.titles = new Vector();
        this.onClicks = new Vector();
        this.isExpand = new Vector();
        this.canBeSelect = new Vector();
        this.isSelected = new Vector();
    }

    /**
     * 清空参数
     *
     */
    public void clear() {
        this.treeNodes.clear();
        this.hrefs.clear();
        this.targets.clear();
        this.titles.clear();
        this.onClicks.clear();
        this.isExpand.clear();
        this.canBeSelect.clear();
        this.isSelected.clear();
    }

    /**
     * 按照从上到下的顺序添加树的结点。
     * ————用此方法适合单选、复选的情况。
     *
     * @param tns 树结点结构数组
     */
    public void addTreeNodes(TreeNodeStruct[] tns) {
        for (int i = 0; i < tns.length; i++)
            this.addTreeNode(tns[i].isFather(),
                    tns[i].getName(),
                    tns[i].getID(),
                    tns[i].getLevel(),
                    tns[i].isLast(),
                    tns[i].getTitle()
            );
    }

    /**
     * 按照从上到下的顺序添加树的结点。
     * ———— 用此方法适合单选、复选的情况。
     *
     * @param isFather 是否是父结点
     * <br>  false：不是父结点；
     * <br>  true：是父结点；
     * @param name  树结点显示的名称
     * @param id  树结点的标识，能唯一标识树结点
     * @param level  树结点的层次，从0开始，根结点为0层
     * @param isLast  是否是同一层的最后一个结点
     * <br>  false：不是最后一个结点；
     * <br>  true：是最后一个结点；
     */
    public void addTreeNode(boolean isFather,
                            String name,
                            long id,
                            int level,
                            boolean isLast,
                            String title) {
        this.addTreeNode(isFather, name, id, level, isLast, null, null, null,title);
    }


    /**
     * 按照从上到下的顺序添加树的结点。
     * ———— 用此方法适合单选、复选的情况。
     *
     * @param isFather 是否是父结点
     * <br>  false：不是父结点；
     * <br>  true：是父结点；
     * @param name  树结点显示的名称
     * @param id  树结点的标识，能唯一标识树结点
     * @param level  树结点的层次，从0开始，根结点为0层
     * @param isLast  是否是同一层的最后一个结点
     * <br>  false：不是最后一个结点；
     * <br>  true：是最后一个结点；
     * @param canBeSelect 是否可以被选中
     * <br>  false:不可选中
     * <br>  true: 可选中
     */
    public void addTreeNode(boolean isFather,
                            String name,
                            long id,
                            int level,
                            boolean isLast,
                            boolean canBeSelect,
                            String title) {
        this.addTreeNode(isFather, name, id, level, isLast, null, null, null, false, canBeSelect, false,title);
    }

    /**
     * 按照从上到下的顺序添加树的结点。
     * ————用此方法适合点击触发事件（使用者自己定义）的情况。
     *
     * @param isFather 是否是父结点
     * <br>  false：不是父结点；
     * <br>  true：是父结点；
     * @param name  树结点显示的名称
     * @param id  树结点的标识，能唯一标识树结点
     * @param level  树结点的层次，从0开始，根结点为0层
     * @param isLast  是否是同一层的最后一个结点
     * <br>  false：不是最后一个结点；
     * <br>  true：是最后一个结点；
     * @param onClick 点击链接触发的事件，必须是函数，如"doSubmit()"
     */
    public void addTreeNode(boolean isFather,
                            String name,
                            long id,
                            int level,
                            boolean isLast,
                            String onClick,
                            String title) {
        this.addTreeNode(isFather, name, id, level, isLast, null, null, onClick,title);
    }

    /**
     * 按照从上到下的顺序添加树的结点。
     * ————用此方法适合点击触发事件（使用者自己定义）的情况。
     *
     * @param isFather 是否是父结点
     * <br>  false：不是父结点；
     * <br>  true：是父结点；
     * @param name  树结点显示的名称
     * @param id  树结点的标识，能唯一标识树结点
     * @param level  树结点的层次，从0开始，根结点为0层
     * @param isLast  是否是同一层的最后一个结点
     * <br>  false：不是最后一个结点；
     * <br>  true：是最后一个结点；
     * @param onClick 点击链接触发的事件，必须是函数，如"doSubmit()"
     * @param isExpand 如果是父结点，决定该结点是否展开；对子结点无用。
     * <br>  false：不展开；
     * <br>  true：展开；
     */
    public void addTreeNode(boolean isFather,
                            String name,
                            long id,
                            int level,
                            boolean isLast,
                            String onClick,
                            boolean isExpand,
                            String title) {
        this.addTreeNode(isFather, name, id, level, isLast, null, null, onClick, isExpand, true, false,title);
    }

    /**
     * 按照从上到下的顺序添加树的结点。
     * ————用此方法适合点击触发事件（使用者自己定义）的情况。
     *
     * @param isFather 是否是父结点
     * <br>  false：不是父结点；
     * <br>  true：是父结点；
     * @param name  树结点显示的名称
     * @param id  树结点的标识，能唯一标识树结点
     * @param level  树结点的层次，从0开始，根结点为0层
     * @param isLast  是否是同一层的最后一个结点
     * <br>  false：不是最后一个结点；
     * <br>  true：是最后一个结点；
     * @param onClick 点击链接触发的事件，必须是函数，如"doSubmit()"
     * @param isExpand 如果是父结点，决定该结点是否展开；对子结点无用。
     * <br>  false：不展开；
     * <br>  true：展开；
     * @param canBeSelect 是否可以被选中
     * <br>  false:不可选中
     * <br>  true: 可选中
     */
    public void addTreeNode(boolean isFather,
                            String name,
                            long id,
                            int level,
                            boolean isLast,
                            String onClick,
                            boolean isExpand,
                            boolean canBeSelect,
                            String title) {
        this.addTreeNode(isFather, name, id, level, isLast, null, null, onClick, isExpand, canBeSelect, false,title);
    }

    /**
     * 按照从上到下的顺序添加树的结点。
     * ————用此方法适合点击直接链接到目标页面（使用者自己定义链接地址，包括参数）的情况。
     *
     * @param isFather 是否是父结点
     * <br>  false：不是父结点；
     * <br>  true：是父结点；
     * @param name  树结点显示的名称
     * @param id  树结点的标识，能唯一标识树结点
     * @param level  树结点的层次，从0开始，根结点为0层
     * @param isLast  是否是同一层的最后一个结点
     * <br>  false：不是最后一个结点；
     * <br>  true：是最后一个结点；
     * @param href  点击该结点指向的链接地址
     * @param target  链接的目标，直接填：_blank, _parent, _self, _top.
     */
    public void addTreeNode(boolean isFather,
                            String name,
                            long id,
                            int level,
                            boolean isLast,
                            String href,
                            String target,
                            String title) {
        this.addTreeNode(isFather, name, id, level, isLast, href, target, null,title);
    }

    /**
     * 按照从上到下的顺序添加树的结点。
     * ————用此方法适合既有链接又有事件（使用者自己定义）的情况。
     *
     * @param isFather 是否是父结点
     * <br>  false：不是父结点；
     * <br>  true：是父结点；
     * @param name  树结点显示的名称
     * @param id  树结点的标识，能唯一标识树结点
     * @param level  树结点的层次，从0开始，根结点为0层
     * @param isLast  是否是同一层的最后一个结点
     * <br>  false：不是最后一个结点；
     * <br>  true：是最后一个结点；
     * @param href  点击该结点指向的链接地址
     * @param target  链接的目标，直接填：_blank, _parent, _self, _top.
     * @param onClick 点击链接触发的事件，必须是函数，如"doSubmit()"
     */
    public void addTreeNode(boolean isFather,
                            String name,
                            long id,
                            int level,
                            boolean isLast,
                            String href,
                            String target,
                            String onClick,
                            String title) {
        this.addTreeNode(isFather, name, id, level, isLast, href, target, onClick, false, true, false,title);
    }

    /**
     * 按照从上到下的顺序添加树的结点。
     *
     * @param isFather 是否是父结点
     * <br>  false：不是父结点；
     * <br>  true：是父结点；
     * @param name  树结点显示的名称
     * @param id  树结点的标识，能唯一标识树结点
     * @param level  树结点的层次，从0开始，根结点为0层
     * @param isLast  是否是同一层的最后一个结点
     * <br>  false：不是最后一个结点；
     * <br>  true：是最后一个结点；
     * @param href  点击该结点指向的链接地址
     * @param target  链接的目标，直接填：_blank, _parent, _self, _top.
     * @param onClick 点击链接触发的事件，必须是函数，如"doSubmit()"
     * @param isExpand 如果是父结点，决定该结点是否展开；对子结点无用。
     * <br>  false：不展开；
     * <br>  true：展开；
     * @param canBeSelect 是否可以被选中
     * <br>  false:不可选中
     * <br>  true: 可选中
     * @param isSelected 是否初始化为选中状态
     */
    public void addTreeNode(boolean isFather,
                            String name,
                            long id,
                            int level,
                            boolean isLast,
                            String href,
                            String target,
                            String onClick,
                            boolean isExpand,
                            boolean canBeSelect,
                            boolean isSelected,
                            String title) {
        if (name == null)
            name = "";
        this.treeNodes.addElement(new TreeNodeStruct(isFather, name, id, level, isLast,title));

        if (href != null)
            this.hrefs.addElement(href);
        else
            this.hrefs.addElement("#");

        if (target != null)
            this.targets.addElement(target);
        else
            this.targets.addElement("");
        
        if (title != null)
        	this.titles.addElement(title);
        else
        	this.titles.addElement("");

        if (onClick != null)
            this.onClicks.addElement(onClick);
        else
            this.onClicks.addElement("");

        this.isExpand.addElement(new Boolean(isExpand));

        this.canBeSelect.addElement(new Boolean(canBeSelect));

        this.isSelected.addElement(new Boolean(isSelected));
    }

    /**
     * 设置结点的选择格式
     *
     * @param selectType
     * <br>  0：普通的链接；
     * <br>  1：单选；
     * <br>  2：复选；
     * <br>  3: 选择本身及所有父结点
     * <br>  4: 选择结点后，所有父结点、子结点都不选中
     */
    public void setSelectType(int selectType) {
        if (selectType >= 0 && selectType <= 8 )
            this.selectType = selectType;
        else
            this.selectType = 0;
    }


    /**
     * 设置结点的选择格式
     * @param picType
     * <br>  true：叶结点的图片；
     * <br>  false：关闭的文件夹图标
     */
    public void setUnLastNodePicType(boolean picType) {
        if (picType)
            this.nodeFolderType = this.nodeType;
        else
            this.nodeFolderType = this.folderCloseType;
    }


    /**
     * 根据cookie的值判断是否是第一次进入本页面(由于无法解析request变量,放入jsp页面中)
     * 如果cookie有值无论长度对应否,说明是从别的页面跳转至本页面,返回cookie内容,不同MultiTreeBuilder.java里的
     * @param request      jsp页面的request变量

     * @return 是否是第一次进入本页面
     * <br> 如果返回null , 第一次初始化
     * <br> 如果返回非空 , 从别的页面跳转至本页面
     */
     public String getTreeCookieValue(HttpServletRequest request) {
        String cookieName="treeState";  //此值与tree_builder.js里的相同
        Cookie currentCookie=null;
        Cookie tmpCookies[] = request.getCookies();
        String currentValue=null;
        String currentCookieName=null;


        //获取现有的所有cookie

        if(tmpCookies == null || tmpCookies.length == 0)
           return "";

        for(int i=0;i<tmpCookies.length;i++)
        {
           currentCookie=tmpCookies[i];
           currentCookieName=currentCookie.getName();
           currentValue=currentCookie.getValue();

           if( currentCookieName.equals(cookieName) ){
           	//已经访问过本页面,生成了一个cookie

           	return currentValue;
           }
        }
        return "";

      }

    /**
     * 设置是否根据tns[].level自动设置isFather和isLast属性
     * 仅在getTreeString(String treeID,String expandType) 里有效
     * @param isSetLastAndFather
     * <br>  true：是；
     * <br>  false：缺省,否
     */
    public void setIsSetLastAndFather(boolean isSetLastAndFather) {
        this.isSetLastAndFather = isSetLastAndFather;
    }


    /**
     * 设置图片的路径 （缺省为"images/tree/"）
     * @param picPath
     * <br>  true：叶结点的图片；
     * <br>  false：关闭的文件夹图标
     */
    public void setPicPath(String picPath) {
        this.picURL = picPath;
    }

    /**
     * 根据之前提供的产生产生树的字符串。
     *
     * @param treeID
     * @return 获得树的String
     */
    public String getTreeString(String treeID) {

        if (this.treeNodes.size() == 0)
            return null;


        //初始化图片路径；
        for (int i = 0; i < this.pics.length; i++)
            this.pics[i] = "<IMG SRC=\"" + this.picURL + this.pics[i];


        TreeNodeStruct[] tns = new TreeNodeStruct[this.treeNodes.size()];
        this.treeNodes.copyInto(tns);

        //结果；
        StringBuffer s = new StringBuffer();

        int maxLevel = tns[0].getLevel();

        int minLevel = maxLevel;

        for (int i = 0; i < tns.length; i++) {
            if (tns[i].getLevel() > maxLevel)
                maxLevel = tns[i].getLevel();
            if (tns[i].getLevel() < minLevel)
                minLevel = tns[i].getLevel();
        }

        //定义一个控制某一层的table是否完善
        boolean[] isComplete = new boolean[maxLevel - minLevel + 1];
        //定义一个控制之前所有上层的图片显示情况
        boolean[] picType = new boolean[maxLevel - minLevel + 1];


        s.append("        <div id=\"" + treeID + "\">\n");

        for (int i = 0; i < tns.length; i++) {
            int level = tns[i].getLevel() - minLevel;
            //缩进
            String indent = "";
            String divIndent = "";

            for (int j = 0; j <= level; j++)
                divIndent += "            ";

            indent = divIndent + "    ";

            //如果上一个同级元素（以及下级）包含所有子的table还没有完成，加上最后的完善代码
            for (int j = isComplete.length - 1; j >= level; j--) {

                String tempIndent = "";
                for (int k = 0; k < j - level; k++)
                    tempIndent += "    ";

                if (isComplete[j]) {
                    s.append(divIndent + tempIndent + "        </td>\n" +
                            divIndent + tempIndent + "    </tr>\n" +
                            divIndent + tempIndent + "</table>\n");
                    isComplete[j] = false;
                }
            }


            //加图片
            s.append(divIndent + "<div nowrap> \n");
            //如果没有子结点，就不让点击
            if (tns[i].isFather()) {
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                    s.append(indent + "<span width=\"100%\" class=ClickToggleExpand language=JScript onClick=ClickToggleNext(this)>\n");
                else
                    s.append(indent + "<span width=\"100%\" class=ClickToggleCollapse language=JScript onClick=ClickToggleNext(this)>\n");


            } else {

                s.append(indent + "<span width=\"100%\" class=ClickToggleNohand language=JScript>\n");
            }
            s.append(indent + "    ");

            for (int j = 0; j < level; j++) {
                if (picType[j])
                    s.append(this.pics[this.verticalLineType]);
                else
                    s.append(this.pics[this.spaceType]);
            }
            //当当前结点为父结点时
            if (tns[i].isFather()) {
                //当当前结点为根结点时
                if (i == 0) {
                    //如果是同一层最后一个结点；
                    if (tns[i].isLast()) {
                        //初始打开
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.nolineOpenButtonType]);
                        else
                        //初始关闭
                            s.append(this.pics[this.nolineCloseButtonType]);
                        picType[level] = false;
                    } else {
                        //初始打开
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.topOpenButtocType]);
                        else
                        //初始关闭
                            s.append(this.pics[this.topCloseButtonType]);
                        picType[level] = true;
                    }
                }
                //当当前结点为同一层最后一个结点时
                else {
                    if (tns[i].isLast()) {
                        //初始打开
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.bottomOpenButtonType]);
                        else
                        //初始关闭
                            s.append(this.pics[this.bottomCloseButtonType]);
                        picType[level] = false;
                    }
                    //当当前结点不是同一层最后一个结点时
                    else {
                        //初始打开
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.middleOpenButtonType]);
                        else
                        //初始关闭
                            s.append(this.pics[this.middleCloseButtonType]);
                        picType[level] = true;
                    }
                }

                //添加结点图标
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                    s.append(this.pics[this.folderOpenType]);
                else
                    s.append(this.pics[this.folderCloseType]);
            }
            //当当前结点没有子结点时
            else {
                //如果是第一个结点
                if (i == 0) {
                    if (tns[i].isLast()) {
                        s.append(this.pics[this.spaceType]);
                        picType[level] = false;
                    } else {

                        //modified by ge zhilei at 031202 处理只有一级但节点不止一个的情况

                        s.append(this.pics[this.topJoinRootType]);

                        picType[level] = true;
                    }
                }
                //如果不是第一个结点
                else {
                    if (tns[i].isLast()) {
                        s.append(this.pics[this.bottomJoinType]);
                        picType[level] = false;
                    } else {

                        s.append(this.pics[this.middleJoinType]);

                        picType[level] = true;
                    }
                }

                //显示结点的图标
                if (level == maxLevel - minLevel + 1)
                    s.append(this.pics[this.nodeType]);
                else
                    s.append(this.pics[this.nodeFolderType]);

            }

            s.append("\n");
            s.append(indent + "</span>\n");

            //可以被选择
            if ((((Boolean) this.canBeSelect.get(i))).booleanValue()) {
                //加基本信息

                if ((((Boolean) this.isSelected.get(i))).booleanValue())
                    s.append(indent + "<a width=\"100%\" class=showbg id=_" + tns[i].getID() + " language=JScript");
                else
                    s.append(indent + "<a width=\"100%\" class=hidebg id=_" + tns[i].getID() + " language=JScript");

                s.append(" href=\"" + (String) this.hrefs.get(i) + "\"");
                s.append(" target=\"" + (String) this.targets.get(i) + "\"");
                s.append(" title=\"" + (String) this.titles.get(i) + "\"");//添加提示信息

                switch (this.selectType) {
                    case 0: //链接
                        s.append(" onClick=\"javascript:directLink(this, " + treeID + ");" + (String) this.onClicks.get(i) + "\"");
                        break;
                    case 1://单选
                        s.append(" onClick=\"singleSelect(this, " + treeID + ")\"");
                        break;
                    case 2://复选
                        s.append(" onClick=\"multiSelect(this)\"");
                        break;
                    case 3: //选择本身及所有的父（祖父）结点
                        s.append(" onClick=\"selectSelfAndParents(this, " + treeID + ")\"");
                        break;
                    case 4: //只能选择同级的结点
                        s.append(" onClick=\"selectSelfAndSiblings(this, " + treeID + ")\"");
                        break;
                    default:
                        break;
                }
                s.append(">");

                s.append(tns[i].getName() + "</a> \n");

            } else {

                if ((((Boolean) this.isSelected.get(i))).booleanValue())
                    s.append(indent + "<span width=\"100%\" class=showbg id=_" + tns[i].getID() + " language=JScript title=\""+tns[i].getTitle()+"\"");
                else
                    s.append(indent + "<span width=\"100%\" class=hidebg id=_" + tns[i].getID() + " language=JScript title=\""+tns[i].getTitle()+"\"");
                s.append(">");
                s.append(tns[i].getName() + "</span>\n");
            }

            s.append(divIndent + "</div>\n");

            //如果该结点有子结点，则输出一个包含所有子的table
            if (tns[i].isFather()) {
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                //展开它的子
                    s.append(divIndent + "<table width=\"0\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"DISPLAY: block\">\n" +
                            divIndent + "    <tr>\n" +
                            divIndent + "        <td height=\"1\">\n");
                else
                    s.append(divIndent + "<table width=\"0\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"DISPLAY: none\">\n" +
                            divIndent + "    <tr>\n" +
                            divIndent + "        <td height=\"1\">\n");
                isComplete[level] = true;
            }
            //当到达最后一个结点时，处理收尾工作
            if ((i == tns.length - 1) && (level > 0)) {
                for (int k = level - 1; k >= 0; k--) {
                    if (isComplete[k]) {
                        divIndent = "";
                        for (int l = 0; l <= k; l++)
                            divIndent += "            ";
                        s.append(divIndent + "        </td>\n" +
                                divIndent + "    </tr>\n" +
                                divIndent + "</table>\n");
                    }//end if
                }//end for
            }//end if
        }

        s.append("        </div>");

        return s.toString();
    }


   /**
     * 根据之前提供的产生树的字符串,为一个重载的方法。
     *
     * @param treeID
     * @param expandType         当前页面树之展开类型
     * EXPAND_ALL                全部展开
     * CLOSE_ALL                 全部闭合
     * NORMAL                    正常,按照加入树数据时指定的方式展开
     * @return 获得树的String
     */
    public String getTreeString(String treeID,String expandType) {

        if (this.treeNodes.size() == 0)
            return null;


        //初始化图片路径；
        for (int i = 0; i < this.pics.length; i++)
            this.pics[i] = "<IMG SRC=\"" + this.picURL + this.pics[i];


        TreeNodeStruct[] tns = new TreeNodeStruct[this.treeNodes.size()];
        this.treeNodes.copyInto(tns);

        //2003.10加,根据tns[]数组里的level值设置tns[i].isLast和tns[i].isFather属性
        if(this.isSetLastAndFather == true ) {
             int cur_level = 0;
             //计算isFather
             for (int k = 0; k < tns.length - 1; k++ ){
                cur_level = tns[k].getLevel();
                if (  tns[k+1].getLevel() <= cur_level )
                   tns[k].setFatherNode(false); //isFather = 0
                else
                   tns[k].setFatherNode(true);  //isFather = 1
             }
             tns[tns.length - 1].setFatherNode(false);; //isFather = 0

             //计算isLast
             for (int x = 0; x < tns.length - 1; x++ ){
                cur_level = tns[x].getLevel();
                for(int y = x+1 ; y < tns.length ; y++){
                   if(tns[y].getLevel() < cur_level)  {//遇上上一级节点,为最后一个
                      tns[x].setLastNode(true);  //isLast = 1
                      break;
                   }
                   if(tns[y].getLevel() == cur_level) {//遇上同级节点,非最后一个
                      tns[x].setLastNode(false);  //isLast = 1
                      break;
                   }
                   if(y == tns.length - 1 ) {
                      tns[x].setLastNode(true);           //到末尾
                   }
                }
             }
             tns[tns.length-1].setLastNode(true);
        }

        //改变树的展开方式,通过修改isExpand vector 2003.10
        if ( expandType == null || expandType.equals("") || expandType.equals("NORMAL") ){
            //do nothing
        }
        else if ( expandType.equals("EXPAND_ALL") ){
            //改变 isExpand
            this.isExpand.removeAllElements();

            for (int i=0; i<this.treeNodes.size(); i++){
            	//如果为父结点,展开状态为true
            	boolean tmpIsExpand = false ;
            	if( tns[i].isFather() )
            	   tmpIsExpand = true ;
             	this.isExpand.addElement(new Boolean(tmpIsExpand));
            }
        }
        else if ( expandType.equals("CLOSE_ALL") ) {
            //改变 isExpand
            this.isExpand.removeAllElements();

            for (int i=0; i<this.treeNodes.size(); i++){
            	//如果为父结点,展开状态为false
            	boolean tmpIsExpand = false ;
            	if( tns[i].isFather() )
            	   tmpIsExpand = false ;
             	this.isExpand.addElement(new Boolean(tmpIsExpand));
            }
        }



        //结果；
        StringBuffer s = new StringBuffer();

        int maxLevel = tns[0].getLevel();

        int minLevel = maxLevel;

        for (int i = 0; i < tns.length; i++) {
            if (tns[i].getLevel() > maxLevel)
                maxLevel = tns[i].getLevel();
            if (tns[i].getLevel() < minLevel)
                minLevel = tns[i].getLevel();
        }

        //定义一个控制某一层的table是否完善
        boolean[] isComplete = new boolean[maxLevel - minLevel + 1];
        //定义一个控制之前所有上层的图片显示情况
        boolean[] picType = new boolean[maxLevel - minLevel + 1];


        s.append("        <div id=\"" + treeID + "\">\n");

        for (int i = 0; i < tns.length; i++) {
            int level = tns[i].getLevel() - minLevel;
            //缩进
            String indent = "";
            String divIndent = "";

            for (int j = 0; j <= level; j++)
                divIndent += "            ";

            indent = divIndent + "    ";

            //如果上一个同级元素（以及下级）包含所有子的table还没有完成，加上最后的完善代码
            for (int j = isComplete.length - 1; j >= level; j--) {

                String tempIndent = "";
                for (int k = 0; k < j - level; k++)
                    tempIndent += "    ";

                if (isComplete[j]) {
                    s.append(divIndent + tempIndent + "        </td>\n" +
                            divIndent + tempIndent + "    </tr>\n" +
                            divIndent + tempIndent + "</table>\n");
                    isComplete[j] = false;
                }
            }


            //加图片
            s.append(divIndent + "<div nowrap> \n");

            //+-图标的事件
            //如果没有子结点，就不让点击
            if (tns[i].isFather()) {
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                    s.append(indent + "<span width=\"100%\" class=ClickToggleExpand language=JScript onClick=ClickToggleNext(this)>\n");
                else
                    s.append(indent + "<span width=\"100%\" class=ClickToggleCollapse language=JScript onClick=ClickToggleNext(this)>\n");


            } else {

                s.append(indent + "<span width=\"100%\" class=ClickToggleNohand language=JScript>\n");
            }
            s.append(indent + "    ");

            //垂直和水平的线
            for (int j = 0; j < level; j++) {
                if (picType[j])
                    s.append(this.pics[this.verticalLineType]);
                else
                    s.append(this.pics[this.spaceType]);
            }

            //文件夹和结点的圆点图标
            //当当前结点为父结点时
            if (tns[i].isFather()) {
                //当当前结点为根结点时
                if (i == 0) {
                    //如果是同一层最后一个结点；
                    if (tns[i].isLast()) {
                        //初始打开
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.nolineOpenButtonType]);
                        else
                        //初始关闭
                            s.append(this.pics[this.nolineCloseButtonType]);
                        picType[level] = false;
                    } else {
                        //初始打开
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.topOpenButtocType]);
                        else
                        //初始关闭
                            s.append(this.pics[this.topCloseButtonType]);
                        picType[level] = true;
                    }
                }
                //当当前结点为同一层最后一个结点时
                else {
                    if (tns[i].isLast()) {
                        //初始打开
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.bottomOpenButtonType]);
                        else
                        //初始关闭
                            s.append(this.pics[this.bottomCloseButtonType]);
                        picType[level] = false;
                    }
                    //当当前结点不是同一层最后一个结点时
                    else {
                        //初始打开
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.middleOpenButtonType]);
                        else
                        //初始关闭
                            s.append(this.pics[this.middleCloseButtonType]);
                        picType[level] = true;
                    }
                }

                //添加结点图标
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                    s.append(this.pics[this.folderOpenType]);
                else
                    s.append(this.pics[this.folderCloseType]);
            }
            //当当前结点没有子结点时
            else {
                //如果是第一个结点
                if (i == 0) {
                    if (tns[i].isLast()) {

                        //modified by ge zhilei at 031202
                        s.append(this.pics[this.topJoinRootType]);

                        picType[level] = false;
                    } else {

                        s.append(this.pics[this.topJoinType]);

                        picType[level] = true;
                    }
                }
                //如果不是第一个结点
                else {
                    if (tns[i].isLast()) {

                        s.append(this.pics[this.bottomJoinType]);

                        picType[level] = false;
                    } else {
                        s.append(this.pics[this.middleJoinType]);

                        picType[level] = true;
                    }
                }

                //显示结点的图标
                if (level == maxLevel - minLevel + 1)
                    s.append(this.pics[this.nodeType]);
                else
                    s.append(this.pics[this.nodeFolderType]);

            }

            s.append("\n");
            s.append(indent + "</span>\n");

            //可以被选择
            if ((((Boolean) this.canBeSelect.get(i))).booleanValue()) {
                //加基本信息

                if ((((Boolean) this.isSelected.get(i))).booleanValue())
                    s.append(indent + "<a width=\"100%\" class=NodeText id=" + tns[i].getID() + " language=JScript");
                else
                    s.append(indent + "<a width=\"100%\" class=NodeText id=" + tns[i].getID() + " language=JScript");

                s.append(" href=\"" + (String) this.hrefs.get(i) + "\"");
                s.append(" target=\"" + (String) this.targets.get(i) + "\"");
                s.append(" title=\"" + (String) this.titles.get(i) + "\"");//添加提示信息

                switch (this.selectType) {
                    case 0: //链接
                        s.append(" onClick=\"javascript:singleDirectLink(this, " + treeID + ");" + (String) this.onClicks.get(i) + "\"");
                        break;
                    case 1://单选
                        s.append(" onClick=\"singleSelect(this, " + treeID + ")\"");
                        break;
                    case 2://复选
                        s.append(" onClick=\"multiSelect(this)\"");
                        break;
                    case 3: //选择本身及所有的父（祖父）结点
                        s.append(" onClick=\"selectSelfAndParents(this, " + treeID + ")\"");
                        break;
                    case 4: //只能选择同级的结点
                        s.append(" onClick=\"selectSelfAndSiblings(this, " + treeID + ")\"");
                        break;
                    default:
                        break;
                }
                s.append(">");

                s.append(tns[i].getName() + "</a> \n");

            } else {

                if ((((Boolean) this.isSelected.get(i))).booleanValue())
                    s.append(indent + "<span width=\"100%\" class=NodeText id=" + tns[i].getID() + " language=JScript");
                else
                    s.append(indent + "<span width=\"100%\" class=NodeText id=" + tns[i].getID() + " language=JScript");
                s.append(">");
                s.append(tns[i].getName() + "</span>\n");
            }

            s.append(divIndent + "</div>\n");

            //如果该结点有子结点，则输出一个包含所有子的table
            if (tns[i].isFather()) {
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                //展开它的子
                    s.append(divIndent + "<table width=\"0\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"DISPLAY: block\">\n" +
                            divIndent + "    <tr>\n" +
                            divIndent + "        <td height=\"1\">\n");
                else
                    s.append(divIndent + "<table width=\"0\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"DISPLAY: none\">\n" +
                            divIndent + "    <tr>\n" +
                            divIndent + "        <td height=\"1\">\n");
                isComplete[level] = true;
            }
            //当到达最后一个结点时，处理收尾工作
            if ((i == tns.length - 1) && (level > 0)) {
                for (int k = level - 1; k >= 0; k--) {
                    if (isComplete[k]) {
                        divIndent = "";
                        for (int l = 0; l <= k; l++)
                            divIndent += "            ";
                        s.append(divIndent + "        </td>\n" +
                                divIndent + "    </tr>\n" +
                                divIndent + "</table>\n");
                    }//end if
                }//end for
            }//end if
        }

        s.append("        </div>");

        return s.toString();
    }
}

