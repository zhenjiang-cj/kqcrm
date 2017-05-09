package com.nl.base.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Vector;

/**
 * Copyright (c) 2003, �����´�½  All rights reserved
 *
 * ������Ҫ�ǽ���֯�õ����ݰ��ղ�ͬ��Ҫ������ʵ��������ַ��������
 * ����Ҫ��treeBuilder�ϲ�
 * @author   ��Ӫ����������
 * @author   ������������
 * @version  1.03 2003/08/27 ��־��
 * @version  1.08 2003/10/06 ����
 * �����ȡcookie����,��ѡ��,
 */
public class TreeBuilder {

    private Vector treeNodes = null;    //�ڵ�(TreeNodeStruct)
    private Vector hrefs = null;        //����url
    private Vector targets = null;      //url,λ��
    private Vector titles = null;       //��ʾ��Ϣ
    private Vector onClicks = null;    //�������
    private Vector isExpand = null;    //�Ƿ�չ��
    private Vector canBeSelect = null; //�Ƿ��ܱ�ѡ��
    private Vector isSelected = null;  //�Ƿ�ѡ��

    //0:ȱʡ��1����ѡ��2����ѡ
    private int selectType = 0;

    // false:ȱʡ    true:����tns[]�����ֵ����ÿһ������isFather��isLast����
    private boolean isSetLastAndFather = false;

    //����ͼƬ��
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
     * ��ղ���
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     * ���������ô˷����ʺϵ�ѡ����ѡ�������
     *
     * @param tns �����ṹ����
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     * �������� �ô˷����ʺϵ�ѡ����ѡ�������
     *
     * @param isFather �Ƿ��Ǹ����
     * <br>  false�����Ǹ���㣻
     * <br>  true���Ǹ���㣻
     * @param name  �������ʾ������
     * @param id  �����ı�ʶ����Ψһ��ʶ�����
     * @param level  �����Ĳ�Σ���0��ʼ�������Ϊ0��
     * @param isLast  �Ƿ���ͬһ������һ�����
     * <br>  false���������һ����㣻
     * <br>  true�������һ����㣻
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     * �������� �ô˷����ʺϵ�ѡ����ѡ�������
     *
     * @param isFather �Ƿ��Ǹ����
     * <br>  false�����Ǹ���㣻
     * <br>  true���Ǹ���㣻
     * @param name  �������ʾ������
     * @param id  �����ı�ʶ����Ψһ��ʶ�����
     * @param level  �����Ĳ�Σ���0��ʼ�������Ϊ0��
     * @param isLast  �Ƿ���ͬһ������һ�����
     * <br>  false���������һ����㣻
     * <br>  true�������һ����㣻
     * @param canBeSelect �Ƿ���Ա�ѡ��
     * <br>  false:����ѡ��
     * <br>  true: ��ѡ��
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     * ���������ô˷����ʺϵ�������¼���ʹ�����Լ����壩�������
     *
     * @param isFather �Ƿ��Ǹ����
     * <br>  false�����Ǹ���㣻
     * <br>  true���Ǹ���㣻
     * @param name  �������ʾ������
     * @param id  �����ı�ʶ����Ψһ��ʶ�����
     * @param level  �����Ĳ�Σ���0��ʼ�������Ϊ0��
     * @param isLast  �Ƿ���ͬһ������һ�����
     * <br>  false���������һ����㣻
     * <br>  true�������һ����㣻
     * @param onClick ������Ӵ������¼��������Ǻ�������"doSubmit()"
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     * ���������ô˷����ʺϵ�������¼���ʹ�����Լ����壩�������
     *
     * @param isFather �Ƿ��Ǹ����
     * <br>  false�����Ǹ���㣻
     * <br>  true���Ǹ���㣻
     * @param name  �������ʾ������
     * @param id  �����ı�ʶ����Ψһ��ʶ�����
     * @param level  �����Ĳ�Σ���0��ʼ�������Ϊ0��
     * @param isLast  �Ƿ���ͬһ������һ�����
     * <br>  false���������һ����㣻
     * <br>  true�������һ����㣻
     * @param onClick ������Ӵ������¼��������Ǻ�������"doSubmit()"
     * @param isExpand ����Ǹ���㣬�����ý���Ƿ�չ�������ӽ�����á�
     * <br>  false����չ����
     * <br>  true��չ����
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     * ���������ô˷����ʺϵ�������¼���ʹ�����Լ����壩�������
     *
     * @param isFather �Ƿ��Ǹ����
     * <br>  false�����Ǹ���㣻
     * <br>  true���Ǹ���㣻
     * @param name  �������ʾ������
     * @param id  �����ı�ʶ����Ψһ��ʶ�����
     * @param level  �����Ĳ�Σ���0��ʼ�������Ϊ0��
     * @param isLast  �Ƿ���ͬһ������һ�����
     * <br>  false���������һ����㣻
     * <br>  true�������һ����㣻
     * @param onClick ������Ӵ������¼��������Ǻ�������"doSubmit()"
     * @param isExpand ����Ǹ���㣬�����ý���Ƿ�չ�������ӽ�����á�
     * <br>  false����չ����
     * <br>  true��չ����
     * @param canBeSelect �Ƿ���Ա�ѡ��
     * <br>  false:����ѡ��
     * <br>  true: ��ѡ��
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     * ���������ô˷����ʺϵ��ֱ�����ӵ�Ŀ��ҳ�棨ʹ�����Լ��������ӵ�ַ�������������������
     *
     * @param isFather �Ƿ��Ǹ����
     * <br>  false�����Ǹ���㣻
     * <br>  true���Ǹ���㣻
     * @param name  �������ʾ������
     * @param id  �����ı�ʶ����Ψһ��ʶ�����
     * @param level  �����Ĳ�Σ���0��ʼ�������Ϊ0��
     * @param isLast  �Ƿ���ͬһ������һ�����
     * <br>  false���������һ����㣻
     * <br>  true�������һ����㣻
     * @param href  ����ý��ָ������ӵ�ַ
     * @param target  ���ӵ�Ŀ�ֱ꣬���_blank, _parent, _self, _top.
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     * ���������ô˷����ʺϼ������������¼���ʹ�����Լ����壩�������
     *
     * @param isFather �Ƿ��Ǹ����
     * <br>  false�����Ǹ���㣻
     * <br>  true���Ǹ���㣻
     * @param name  �������ʾ������
     * @param id  �����ı�ʶ����Ψһ��ʶ�����
     * @param level  �����Ĳ�Σ���0��ʼ�������Ϊ0��
     * @param isLast  �Ƿ���ͬһ������һ�����
     * <br>  false���������һ����㣻
     * <br>  true�������һ����㣻
     * @param href  ����ý��ָ������ӵ�ַ
     * @param target  ���ӵ�Ŀ�ֱ꣬���_blank, _parent, _self, _top.
     * @param onClick ������Ӵ������¼��������Ǻ�������"doSubmit()"
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
     * ���մ��ϵ��µ�˳��������Ľ�㡣
     *
     * @param isFather �Ƿ��Ǹ����
     * <br>  false�����Ǹ���㣻
     * <br>  true���Ǹ���㣻
     * @param name  �������ʾ������
     * @param id  �����ı�ʶ����Ψһ��ʶ�����
     * @param level  �����Ĳ�Σ���0��ʼ�������Ϊ0��
     * @param isLast  �Ƿ���ͬһ������һ�����
     * <br>  false���������һ����㣻
     * <br>  true�������һ����㣻
     * @param href  ����ý��ָ������ӵ�ַ
     * @param target  ���ӵ�Ŀ�ֱ꣬���_blank, _parent, _self, _top.
     * @param onClick ������Ӵ������¼��������Ǻ�������"doSubmit()"
     * @param isExpand ����Ǹ���㣬�����ý���Ƿ�չ�������ӽ�����á�
     * <br>  false����չ����
     * <br>  true��չ����
     * @param canBeSelect �Ƿ���Ա�ѡ��
     * <br>  false:����ѡ��
     * <br>  true: ��ѡ��
     * @param isSelected �Ƿ��ʼ��Ϊѡ��״̬
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
     * ���ý���ѡ���ʽ
     *
     * @param selectType
     * <br>  0����ͨ�����ӣ�
     * <br>  1����ѡ��
     * <br>  2����ѡ��
     * <br>  3: ѡ�������и����
     * <br>  4: ѡ��������и���㡢�ӽ�㶼��ѡ��
     */
    public void setSelectType(int selectType) {
        if (selectType >= 0 && selectType <= 8 )
            this.selectType = selectType;
        else
            this.selectType = 0;
    }


    /**
     * ���ý���ѡ���ʽ
     * @param picType
     * <br>  true��Ҷ����ͼƬ��
     * <br>  false���رյ��ļ���ͼ��
     */
    public void setUnLastNodePicType(boolean picType) {
        if (picType)
            this.nodeFolderType = this.nodeType;
        else
            this.nodeFolderType = this.folderCloseType;
    }


    /**
     * ����cookie��ֵ�ж��Ƿ��ǵ�һ�ν��뱾ҳ��(�����޷�����request����,����jspҳ����)
     * ���cookie��ֵ���۳��ȶ�Ӧ��,˵���Ǵӱ��ҳ����ת����ҳ��,����cookie����,��ͬMultiTreeBuilder.java���
     * @param request      jspҳ���request����

     * @return �Ƿ��ǵ�һ�ν��뱾ҳ��
     * <br> �������null , ��һ�γ�ʼ��
     * <br> ������طǿ� , �ӱ��ҳ����ת����ҳ��
     */
     public String getTreeCookieValue(HttpServletRequest request) {
        String cookieName="treeState";  //��ֵ��tree_builder.js�����ͬ
        Cookie currentCookie=null;
        Cookie tmpCookies[] = request.getCookies();
        String currentValue=null;
        String currentCookieName=null;


        //��ȡ���е�����cookie

        if(tmpCookies == null || tmpCookies.length == 0)
           return "";

        for(int i=0;i<tmpCookies.length;i++)
        {
           currentCookie=tmpCookies[i];
           currentCookieName=currentCookie.getName();
           currentValue=currentCookie.getValue();

           if( currentCookieName.equals(cookieName) ){
           	//�Ѿ����ʹ���ҳ��,������һ��cookie

           	return currentValue;
           }
        }
        return "";

      }

    /**
     * �����Ƿ����tns[].level�Զ�����isFather��isLast����
     * ����getTreeString(String treeID,String expandType) ����Ч
     * @param isSetLastAndFather
     * <br>  true���ǣ�
     * <br>  false��ȱʡ,��
     */
    public void setIsSetLastAndFather(boolean isSetLastAndFather) {
        this.isSetLastAndFather = isSetLastAndFather;
    }


    /**
     * ����ͼƬ��·�� ��ȱʡΪ"images/tree/"��
     * @param picPath
     * <br>  true��Ҷ����ͼƬ��
     * <br>  false���رյ��ļ���ͼ��
     */
    public void setPicPath(String picPath) {
        this.picURL = picPath;
    }

    /**
     * ����֮ǰ�ṩ�Ĳ������������ַ�����
     *
     * @param treeID
     * @return �������String
     */
    public String getTreeString(String treeID) {

        if (this.treeNodes.size() == 0)
            return null;


        //��ʼ��ͼƬ·����
        for (int i = 0; i < this.pics.length; i++)
            this.pics[i] = "<IMG SRC=\"" + this.picURL + this.pics[i];


        TreeNodeStruct[] tns = new TreeNodeStruct[this.treeNodes.size()];
        this.treeNodes.copyInto(tns);

        //�����
        StringBuffer s = new StringBuffer();

        int maxLevel = tns[0].getLevel();

        int minLevel = maxLevel;

        for (int i = 0; i < tns.length; i++) {
            if (tns[i].getLevel() > maxLevel)
                maxLevel = tns[i].getLevel();
            if (tns[i].getLevel() < minLevel)
                minLevel = tns[i].getLevel();
        }

        //����һ������ĳһ���table�Ƿ�����
        boolean[] isComplete = new boolean[maxLevel - minLevel + 1];
        //����һ������֮ǰ�����ϲ��ͼƬ��ʾ���
        boolean[] picType = new boolean[maxLevel - minLevel + 1];


        s.append("        <div id=\"" + treeID + "\">\n");

        for (int i = 0; i < tns.length; i++) {
            int level = tns[i].getLevel() - minLevel;
            //����
            String indent = "";
            String divIndent = "";

            for (int j = 0; j <= level; j++)
                divIndent += "            ";

            indent = divIndent + "    ";

            //�����һ��ͬ��Ԫ�أ��Լ��¼������������ӵ�table��û����ɣ������������ƴ���
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


            //��ͼƬ
            s.append(divIndent + "<div nowrap> \n");
            //���û���ӽ�㣬�Ͳ��õ��
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
            //����ǰ���Ϊ�����ʱ
            if (tns[i].isFather()) {
                //����ǰ���Ϊ�����ʱ
                if (i == 0) {
                    //�����ͬһ�����һ����㣻
                    if (tns[i].isLast()) {
                        //��ʼ��
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.nolineOpenButtonType]);
                        else
                        //��ʼ�ر�
                            s.append(this.pics[this.nolineCloseButtonType]);
                        picType[level] = false;
                    } else {
                        //��ʼ��
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.topOpenButtocType]);
                        else
                        //��ʼ�ر�
                            s.append(this.pics[this.topCloseButtonType]);
                        picType[level] = true;
                    }
                }
                //����ǰ���Ϊͬһ�����һ�����ʱ
                else {
                    if (tns[i].isLast()) {
                        //��ʼ��
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.bottomOpenButtonType]);
                        else
                        //��ʼ�ر�
                            s.append(this.pics[this.bottomCloseButtonType]);
                        picType[level] = false;
                    }
                    //����ǰ��㲻��ͬһ�����һ�����ʱ
                    else {
                        //��ʼ��
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.middleOpenButtonType]);
                        else
                        //��ʼ�ر�
                            s.append(this.pics[this.middleCloseButtonType]);
                        picType[level] = true;
                    }
                }

                //��ӽ��ͼ��
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                    s.append(this.pics[this.folderOpenType]);
                else
                    s.append(this.pics[this.folderCloseType]);
            }
            //����ǰ���û���ӽ��ʱ
            else {
                //����ǵ�һ�����
                if (i == 0) {
                    if (tns[i].isLast()) {
                        s.append(this.pics[this.spaceType]);
                        picType[level] = false;
                    } else {

                        //modified by ge zhilei at 031202 ����ֻ��һ�����ڵ㲻ֹһ�������

                        s.append(this.pics[this.topJoinRootType]);

                        picType[level] = true;
                    }
                }
                //������ǵ�һ�����
                else {
                    if (tns[i].isLast()) {
                        s.append(this.pics[this.bottomJoinType]);
                        picType[level] = false;
                    } else {

                        s.append(this.pics[this.middleJoinType]);

                        picType[level] = true;
                    }
                }

                //��ʾ����ͼ��
                if (level == maxLevel - minLevel + 1)
                    s.append(this.pics[this.nodeType]);
                else
                    s.append(this.pics[this.nodeFolderType]);

            }

            s.append("\n");
            s.append(indent + "</span>\n");

            //���Ա�ѡ��
            if ((((Boolean) this.canBeSelect.get(i))).booleanValue()) {
                //�ӻ�����Ϣ

                if ((((Boolean) this.isSelected.get(i))).booleanValue())
                    s.append(indent + "<a width=\"100%\" class=showbg id=_" + tns[i].getID() + " language=JScript");
                else
                    s.append(indent + "<a width=\"100%\" class=hidebg id=_" + tns[i].getID() + " language=JScript");

                s.append(" href=\"" + (String) this.hrefs.get(i) + "\"");
                s.append(" target=\"" + (String) this.targets.get(i) + "\"");
                s.append(" title=\"" + (String) this.titles.get(i) + "\"");//�����ʾ��Ϣ

                switch (this.selectType) {
                    case 0: //����
                        s.append(" onClick=\"javascript:directLink(this, " + treeID + ");" + (String) this.onClicks.get(i) + "\"");
                        break;
                    case 1://��ѡ
                        s.append(" onClick=\"singleSelect(this, " + treeID + ")\"");
                        break;
                    case 2://��ѡ
                        s.append(" onClick=\"multiSelect(this)\"");
                        break;
                    case 3: //ѡ�������еĸ����游�����
                        s.append(" onClick=\"selectSelfAndParents(this, " + treeID + ")\"");
                        break;
                    case 4: //ֻ��ѡ��ͬ���Ľ��
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

            //����ý�����ӽ�㣬�����һ�����������ӵ�table
            if (tns[i].isFather()) {
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                //չ��������
                    s.append(divIndent + "<table width=\"0\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"DISPLAY: block\">\n" +
                            divIndent + "    <tr>\n" +
                            divIndent + "        <td height=\"1\">\n");
                else
                    s.append(divIndent + "<table width=\"0\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"DISPLAY: none\">\n" +
                            divIndent + "    <tr>\n" +
                            divIndent + "        <td height=\"1\">\n");
                isComplete[level] = true;
            }
            //���������һ�����ʱ��������β����
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
     * ����֮ǰ�ṩ�Ĳ��������ַ���,Ϊһ�����صķ�����
     *
     * @param treeID
     * @param expandType         ��ǰҳ����֮չ������
     * EXPAND_ALL                ȫ��չ��
     * CLOSE_ALL                 ȫ���պ�
     * NORMAL                    ����,���ռ���������ʱָ���ķ�ʽչ��
     * @return �������String
     */
    public String getTreeString(String treeID,String expandType) {

        if (this.treeNodes.size() == 0)
            return null;


        //��ʼ��ͼƬ·����
        for (int i = 0; i < this.pics.length; i++)
            this.pics[i] = "<IMG SRC=\"" + this.picURL + this.pics[i];


        TreeNodeStruct[] tns = new TreeNodeStruct[this.treeNodes.size()];
        this.treeNodes.copyInto(tns);

        //2003.10��,����tns[]�������levelֵ����tns[i].isLast��tns[i].isFather����
        if(this.isSetLastAndFather == true ) {
             int cur_level = 0;
             //����isFather
             for (int k = 0; k < tns.length - 1; k++ ){
                cur_level = tns[k].getLevel();
                if (  tns[k+1].getLevel() <= cur_level )
                   tns[k].setFatherNode(false); //isFather = 0
                else
                   tns[k].setFatherNode(true);  //isFather = 1
             }
             tns[tns.length - 1].setFatherNode(false);; //isFather = 0

             //����isLast
             for (int x = 0; x < tns.length - 1; x++ ){
                cur_level = tns[x].getLevel();
                for(int y = x+1 ; y < tns.length ; y++){
                   if(tns[y].getLevel() < cur_level)  {//������һ���ڵ�,Ϊ���һ��
                      tns[x].setLastNode(true);  //isLast = 1
                      break;
                   }
                   if(tns[y].getLevel() == cur_level) {//����ͬ���ڵ�,�����һ��
                      tns[x].setLastNode(false);  //isLast = 1
                      break;
                   }
                   if(y == tns.length - 1 ) {
                      tns[x].setLastNode(true);           //��ĩβ
                   }
                }
             }
             tns[tns.length-1].setLastNode(true);
        }

        //�ı�����չ����ʽ,ͨ���޸�isExpand vector 2003.10
        if ( expandType == null || expandType.equals("") || expandType.equals("NORMAL") ){
            //do nothing
        }
        else if ( expandType.equals("EXPAND_ALL") ){
            //�ı� isExpand
            this.isExpand.removeAllElements();

            for (int i=0; i<this.treeNodes.size(); i++){
            	//���Ϊ�����,չ��״̬Ϊtrue
            	boolean tmpIsExpand = false ;
            	if( tns[i].isFather() )
            	   tmpIsExpand = true ;
             	this.isExpand.addElement(new Boolean(tmpIsExpand));
            }
        }
        else if ( expandType.equals("CLOSE_ALL") ) {
            //�ı� isExpand
            this.isExpand.removeAllElements();

            for (int i=0; i<this.treeNodes.size(); i++){
            	//���Ϊ�����,չ��״̬Ϊfalse
            	boolean tmpIsExpand = false ;
            	if( tns[i].isFather() )
            	   tmpIsExpand = false ;
             	this.isExpand.addElement(new Boolean(tmpIsExpand));
            }
        }



        //�����
        StringBuffer s = new StringBuffer();

        int maxLevel = tns[0].getLevel();

        int minLevel = maxLevel;

        for (int i = 0; i < tns.length; i++) {
            if (tns[i].getLevel() > maxLevel)
                maxLevel = tns[i].getLevel();
            if (tns[i].getLevel() < minLevel)
                minLevel = tns[i].getLevel();
        }

        //����һ������ĳһ���table�Ƿ�����
        boolean[] isComplete = new boolean[maxLevel - minLevel + 1];
        //����һ������֮ǰ�����ϲ��ͼƬ��ʾ���
        boolean[] picType = new boolean[maxLevel - minLevel + 1];


        s.append("        <div id=\"" + treeID + "\">\n");

        for (int i = 0; i < tns.length; i++) {
            int level = tns[i].getLevel() - minLevel;
            //����
            String indent = "";
            String divIndent = "";

            for (int j = 0; j <= level; j++)
                divIndent += "            ";

            indent = divIndent + "    ";

            //�����һ��ͬ��Ԫ�أ��Լ��¼������������ӵ�table��û����ɣ������������ƴ���
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


            //��ͼƬ
            s.append(divIndent + "<div nowrap> \n");

            //+-ͼ����¼�
            //���û���ӽ�㣬�Ͳ��õ��
            if (tns[i].isFather()) {
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                    s.append(indent + "<span width=\"100%\" class=ClickToggleExpand language=JScript onClick=ClickToggleNext(this)>\n");
                else
                    s.append(indent + "<span width=\"100%\" class=ClickToggleCollapse language=JScript onClick=ClickToggleNext(this)>\n");


            } else {

                s.append(indent + "<span width=\"100%\" class=ClickToggleNohand language=JScript>\n");
            }
            s.append(indent + "    ");

            //��ֱ��ˮƽ����
            for (int j = 0; j < level; j++) {
                if (picType[j])
                    s.append(this.pics[this.verticalLineType]);
                else
                    s.append(this.pics[this.spaceType]);
            }

            //�ļ��кͽ���Բ��ͼ��
            //����ǰ���Ϊ�����ʱ
            if (tns[i].isFather()) {
                //����ǰ���Ϊ�����ʱ
                if (i == 0) {
                    //�����ͬһ�����һ����㣻
                    if (tns[i].isLast()) {
                        //��ʼ��
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.nolineOpenButtonType]);
                        else
                        //��ʼ�ر�
                            s.append(this.pics[this.nolineCloseButtonType]);
                        picType[level] = false;
                    } else {
                        //��ʼ��
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.topOpenButtocType]);
                        else
                        //��ʼ�ر�
                            s.append(this.pics[this.topCloseButtonType]);
                        picType[level] = true;
                    }
                }
                //����ǰ���Ϊͬһ�����һ�����ʱ
                else {
                    if (tns[i].isLast()) {
                        //��ʼ��
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.bottomOpenButtonType]);
                        else
                        //��ʼ�ر�
                            s.append(this.pics[this.bottomCloseButtonType]);
                        picType[level] = false;
                    }
                    //����ǰ��㲻��ͬһ�����һ�����ʱ
                    else {
                        //��ʼ��
                        if (((Boolean) this.isExpand.get(i)).booleanValue())
                            s.append(this.pics[this.middleOpenButtonType]);
                        else
                        //��ʼ�ر�
                            s.append(this.pics[this.middleCloseButtonType]);
                        picType[level] = true;
                    }
                }

                //��ӽ��ͼ��
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                    s.append(this.pics[this.folderOpenType]);
                else
                    s.append(this.pics[this.folderCloseType]);
            }
            //����ǰ���û���ӽ��ʱ
            else {
                //����ǵ�һ�����
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
                //������ǵ�һ�����
                else {
                    if (tns[i].isLast()) {

                        s.append(this.pics[this.bottomJoinType]);

                        picType[level] = false;
                    } else {
                        s.append(this.pics[this.middleJoinType]);

                        picType[level] = true;
                    }
                }

                //��ʾ����ͼ��
                if (level == maxLevel - minLevel + 1)
                    s.append(this.pics[this.nodeType]);
                else
                    s.append(this.pics[this.nodeFolderType]);

            }

            s.append("\n");
            s.append(indent + "</span>\n");

            //���Ա�ѡ��
            if ((((Boolean) this.canBeSelect.get(i))).booleanValue()) {
                //�ӻ�����Ϣ

                if ((((Boolean) this.isSelected.get(i))).booleanValue())
                    s.append(indent + "<a width=\"100%\" class=NodeText id=" + tns[i].getID() + " language=JScript");
                else
                    s.append(indent + "<a width=\"100%\" class=NodeText id=" + tns[i].getID() + " language=JScript");

                s.append(" href=\"" + (String) this.hrefs.get(i) + "\"");
                s.append(" target=\"" + (String) this.targets.get(i) + "\"");
                s.append(" title=\"" + (String) this.titles.get(i) + "\"");//�����ʾ��Ϣ

                switch (this.selectType) {
                    case 0: //����
                        s.append(" onClick=\"javascript:singleDirectLink(this, " + treeID + ");" + (String) this.onClicks.get(i) + "\"");
                        break;
                    case 1://��ѡ
                        s.append(" onClick=\"singleSelect(this, " + treeID + ")\"");
                        break;
                    case 2://��ѡ
                        s.append(" onClick=\"multiSelect(this)\"");
                        break;
                    case 3: //ѡ�������еĸ����游�����
                        s.append(" onClick=\"selectSelfAndParents(this, " + treeID + ")\"");
                        break;
                    case 4: //ֻ��ѡ��ͬ���Ľ��
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

            //����ý�����ӽ�㣬�����һ�����������ӵ�table
            if (tns[i].isFather()) {
                if (((Boolean) this.isExpand.get(i)).booleanValue())
                //չ��������
                    s.append(divIndent + "<table width=\"0\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"DISPLAY: block\">\n" +
                            divIndent + "    <tr>\n" +
                            divIndent + "        <td height=\"1\">\n");
                else
                    s.append(divIndent + "<table width=\"0\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"DISPLAY: none\">\n" +
                            divIndent + "    <tr>\n" +
                            divIndent + "        <td height=\"1\">\n");
                isComplete[level] = true;
            }
            //���������һ�����ʱ��������β����
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

