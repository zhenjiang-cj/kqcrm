/**
 * ȡ������ѡ�еĽ���ID��������,2003.10.17��
 *
 * @param treeID ����ʶ
 * @return Array
 */
function getSelectIDsAndNames(treeID) {


    var returnNames = new Array();
    var returnIDs = new Array();
    var returnIDsAndNames = new Array();

    if (! treeID || treeID.innerHTML == "")
        return null;

    if (treeID.className == "CheckedNode") {
        returnNames[returnNames.length] = treeID.innerHTML;
        returnIDs[returnIDs.length] = treeID.id;
    }

    var oChildren = treeID.children;

    if (oChildren) {
        for (var i = 0; i < oChildren.length; i ++) {

            var rtNames = getSelectNames(oChildren.item(i));

            if (rtNames != null) {
                for (var j = 0; j < rtNames.length; j ++)
                    returnNames[returnNames.length] = rtNames[j];
            }

            var rtIDs = getSelectIDs(oChildren.item(i));

            if (rtIDs != null) {
                for (var j = 0; j < rtIDs.length; j ++)
                    returnIDs[returnIDs.length] = rtIDs[j];
            }

        }
        //end for
    }

    for (var i = 0; i < returnIDs.length; i++) {
        var index = returnIDsAndNames.length;
        returnIDsAndNames[index] = new Object;
        returnIDsAndNames[index].name = returnNames[i];
        returnIDsAndNames[index].id = returnIDs[i];
    }
    return returnIDsAndNames;
}


/**
 * ȡ������ѡ�еĽ����������,2003.10.17��
 *
 * @param treeID ����ʶ
 * @return Array
 */
function getSelectNames(treeID) {


    var returnNames = new Array();

    if (! treeID || treeID.innerHTML == "")
        return null;

    if (treeID.className == "CheckedNode") {
        returnNames[returnNames.length] = treeID.innerHTML;
    }

    var oChildren = treeID.children;

    if (oChildren) {
        for (var i = 0; i < oChildren.length; i ++) {

            var rtNames = getSelectNames(oChildren.item(i));

            if (rtNames != null) {
                for (var j = 0; j < rtNames.length; j ++)
                    returnNames[returnNames.length] = rtNames[j];
            }

        }
        //end for
    }

    return returnNames;
}


/**
 * ȡ������ѡ�е�ID����,2003.10.17��
 *
 * @param treeID ����ʶ
 * @return Array
 */
function getSelectIDs(treeID) {

    //alert("in getSelectIDs");
    var returnIDs = new Array();

    if (! treeID || treeID.innerHTML == "")
        return null;

    if (treeID.className == "CheckedNode") {
        returnIDs[returnIDs.length] = treeID.id;
    }

    var oChildren = treeID.children;

    if (oChildren) {
        for (var i = 0; i < oChildren.length; i ++) {

            var rtIDs = getSelectIDs(oChildren.item(i));

            if (rtIDs != null) {
                for (var j = 0; j < rtIDs.length; j ++)
                    returnIDs[returnIDs.length] = rtIDs[j];
            }

        }
        //end for
    }

    return returnIDs;
}

/**
 * ȡ������ѡ�е�ID����,2004.02.11��
 *
 * @param treeID ����ʶ
 * @return Array
 */
function getSelectIDsByShowbg(treeID) {

    //alert("in getSelectIDs");
    var returnIDs = new Array();

    if (! treeID || treeID.innerHTML == "")
        return null;

    if (treeID.className == "showbg") {
        returnIDs[returnIDs.length] = treeID.id;
    }

    var oChildren = treeID.children;

    if (oChildren) {
        for (var i = 0; i < oChildren.length; i ++) {

            var rtIDs = getSelectIDsByShowbg(oChildren.item(i));

            if (rtIDs != null) {
                for (var j = 0; j < rtIDs.length; j ++)
                    returnIDs[returnIDs.length] = rtIDs[j];
            }

        }
        //end for
    }

    return returnIDs;
}

/**
 * �������ѡ�еĽ��,��selectSelfUnSelectOthers���� 2003.10.13
 *
 * @param treeID ����ʶ
 */

function unSelectAllSelectNode(treeID) {
    if (! treeID || treeID.innerHTML == "")
        return;
    if (treeID.className == "CheckedBox") {

        Select2UnSelect(treeID, treeID.id);
        //alert("do unSelectAllSelectNode");
    }
    var oChildren = treeID.children;
    if (oChildren) {
        for (var i = 0; i < oChildren.length; i ++) {
            unSelectAllSelectNode(oChildren.item(i));
        }
        //end for
    }
    return;
}


/**
 * ��ѡ��,2003.10.13,notice it!!!
 *
 * @param oElement ��ǰ����ʶ
 * @param treeID   ����ʶ
 */
function selectSelfUnSelectOthers(oElement, treeID, n) {
    if (oElement.className == "UnCheckedBox") {
        unSelectAllSelectNode(treeID);
        UnSelect2Select(oElement, n);
    }
    return false;
}


/**
 * �����и������Ϊ��ѡ��״̬,2003.10.13,notice it!!!
 *
 * @param oElement ��ǰ����ʶ
 * @param treeID   ����ʶ
 */
function unSelectAllParent(oElement, treeID) {

    if (oElement.parentElement.parentElement.sourceIndex == treeID.sourceIndex)
        return true;

    var oPrevSiblingElement;
    //self < div < td < tr < table(tbody)
    var oParentElement = oElement.parentElement.parentElement.parentElement.parentElement;
    if (oParentElement.tagName == "TBODY")
        oParentElement = oParentElement.parentElement;
    var oGrandFatherElement = oParentElement.parentElement;

    var oChildren = oGrandFatherElement.children;

    for (var i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == oParentElement.sourceIndex) {
            oPrevSiblingElement = oChildren.item(i - 1);
        }
    }
    if (!oPrevSiblingElement)return false;

    oChildren = oPrevSiblingElement.children;

    for (var i = 0; i < oChildren.length; i ++) {
        if (oChildren[i].className == "CheckedBox") {
            //�ı���classname��ͼƬ
            Select2UnSelect(oChildren[i], oChildren[i].id);
        }//end if
        unSelectAllParent(oChildren[i], treeID);
    }
    //end for


    return true;
}


/* ȡ���������н������ơ�
 *
 * @param treeID ����ʶ
 * @return Array
 */
function getAllNodes(treeID) {

    if (! treeID || treeID.innerHTML == "")
        return null;

    if (treeID.className == "NodeText") {
        alert(treeID.innerHTML + treeID.id + "-" + getNumByID(treeID.id));
    }
    var oChildren = treeID.children;
    if (oChildren) {
        for (var i = 0; i < oChildren.length; i ++) {
            getAllNodes(oChildren.item(i));
        }
        //end for
    }
}


/****************************************************************************
 cookie�Ĳ�������
 ****************************************************************************/

// ����ǰ���ñ��浽�ͻ���Cookie��ȥ,cookie��ΪtreeState;
function setCurrState(setting) {
    document.cookie = "treeState=" + escape(setting);
}

// ȡ�õ�ǰCookie������
function getCurrState() {
    var label = "treeState="
    var labelLen = label.length
    var cLen = document.cookie.length
    var i = 0
    while (i < cLen) {
        var j = i + labelLen
        if (document.cookie.substring(i, j) == label) {
            var cEnd = document.cookie.indexOf(";", j)
            if (cEnd == -1) {
                cEnd = document.cookie.length
            }
            return unescape(document.cookie.substring(j, cEnd))
        }
        i++
    }
    return ""
}

//����һ��ȫ�ֱ���ΪCookie�ĵ�ǰ״ֵ̬
var treeState ;

// ���ó�ʼ��״̬�������ǰ��״̬���Ӧ����Ϊ��
function initCurr(cookieLength) {
    //if ( treeState == "" || treeState.length != cookieLength ) {
    treeState = "0";
    for (i = 1; i < cookieLength; i++) {
        treeState += "1";
    }
    setCurrState(treeState);
    //}
    alert(getCurrState());
}

//  �ı�һ���ڵ��״̬ , 1<-> 0�����洢��Cookie��
function toggle(n) {
    treeState = getCurrState();
    //alert("old state:"+treeState);
    var newString = ""
    var expanded = treeState.substring(n, n + 1) // ����Ľڵ�
    newString += treeState.substring(0, n)
    newString += expanded ^ 1 // ���ڵ�״̬�÷�
    newString += treeState.substring(n + 1, treeState.length)
    setCurrState(newString) // ����״̬��дCookie��
    //alert("newString:"+newString+"  treeState:"+getCurrState());
}

//  �ı�һ���ڵ��״̬ , 0/1 -> 1�����洢��Cookie��
function toggleUnSelect2Select(k) {
    var i;
    treeState = getCurrState();
    //alert("treeState="+treeState+" ,k="+k+",treeState.length="+treeState.length);
    var newString = "";
    for (i = 0; i < treeState.length; i++) {
        if (i != k) {
            newString += treeState.substring(i, i + 1);
        }
        else {
            newString += "1";
            // ���ڵ�״̬��ѡ��
        }
        //alert("i="+i+"k="+k+"-"+treeState.substring(i,i+1));
    }

    setCurrState(newString);
    // ����״̬��дCookie��
    //alert("In toggleUnSelect2Select : new treeState:"+getCurrState() );

}

//  �ı�һ���ڵ��״̬ , 0/1 -> 0�����洢��Cookie��
function toggleSelect2UnSelect(k) {
    var i;
    treeState = getCurrState();
    //alert("treeState="+treeState+" ,k="+k+",treeState.length="+treeState.length);
    var newString = "";
    for (i = 0; i < treeState.length; i++) {
        if (i != k) {
            newString += treeState.substring(i, i + 1);
        }
        else {
            newString += "0";
            // ���ڵ�״̬��δѡ��
        }
        //alert("i="+i+"k="+k+"-"+treeState.substring(i,i+1));
    }

    setCurrState(newString);
    // ����״̬��дCookie��
    //alert("In toggleSelect2UnSelect : new treeState:"+getCurrState() );

}

/**
 * �����ѡ����¼���
 */
function multiSelect(oElement, n)
{
    var newText = new String;
    newText += oElement.innerHTML;
    var oNextSiblingElement;
    var oChildren = oElement.parentElement.parentElement.children;
    for (i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == oElement.parentElement.sourceIndex)
            oNextSiblingElement = oChildren.item(i + 1);
    }

    //if(!oNextSiblingElement)return false; //�������η����Ӱ�������ӽ��

    if (oElement.className == "CheckedBox") {
        //oNextSiblingElement.style.display="none";//�������η�����ʱ��չ���ͱպ�
        oElement.className = "UnCheckedBox";
        //replace����ÿ��ֻ�滻һ��ƥ����ַ���
        newText = newText.replace("checked", "unchecked");

    } else {
        if (oElement.className == "UnCheckedBox") {
            //oNextSiblingElement.style.display="block";//�������η�����ʱ��չ���ͱպ�
            oElement.className = "CheckedBox";
            //replace����ÿ��ֻ�滻һ��ƥ����ַ���
            newText = newText.replace("unchecked", "checked");
        }
    }
    oElement.innerHTML = newText;

    //��ͬ����Ԫ���е��ı����,�ı��ı�����״̬ CheckedNode<->UnCheckedNode
    oChildren = oElement.parentElement.children;
    if (oChildren) {
        for (i = 0; i < oChildren.length; i ++) {
            if (oChildren.item(i).className == "CheckedNode") {
                oChildren.item(i).className = "UnCheckedNode";
                break;
            }
            else if (oChildren.item(i).className == "UnCheckedNode") {
                oChildren.item(i).className = "CheckedNode";
                break;
            }
        }
        //end for
    }

    //�ı�cookie״̬
    toggle(n);

    return true;

}

/**
 * ��ѡ��ѡ��->��ѡ��ȡ����
 */
function Select2UnSelect(oElement, n)
{
    //alert("in Select2UnSelect");
    var newText = new String;
    newText += oElement.innerHTML;
    var oNextSiblingElement;
    var oChildren = oElement.parentElement.parentElement.children;
    var i;
    for (i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == oElement.parentElement.sourceIndex)
            oNextSiblingElement = oChildren.item(i + 1);
    }

    //if(!oNextSiblingElement)return false; //�������η����Ӱ�������ӽ��

    if (oElement.className == "CheckedBox") {
        //oNextSiblingElement.style.display="none";//�������η�����ʱ��չ���ͱպ�
        oElement.className = "UnCheckedBox";
        //replace����ÿ��ֻ�滻һ��ƥ����ַ���
        newText = newText.replace("checked", "unchecked");

    }
    oElement.innerHTML = newText;

    //��ͬ����Ԫ���е��ı����,�ı��ı�����״̬ CheckedNode->UnCheckedNode
    oChildren = oElement.parentElement.children;
    if (oChildren) {
        for (i = 0; i < oChildren.length; i ++) {
            if (oChildren.item(i).className == "CheckedNode")
                oChildren.item(i).className = "UnCheckedNode";
        }
        //end for
    }

    //�ı�cookie״̬
    toggleSelect2UnSelect(n);
    return true;

}

/**
 * ��ѡ��ȡ��->��ѡ��ѡ�С�
 */
function UnSelect2Select(oElement, n)
{
    //alert("in UnSelect2Select");
    var newText = new String;
    newText += oElement.innerHTML;
    var oNextSiblingElement;
    var oChildren = oElement.parentElement.parentElement.children;
    for (i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == oElement.parentElement.sourceIndex)
            oNextSiblingElement = oChildren.item(i + 1);
    }

    //if(!oNextSiblingElement)return false; //�������η����Ӱ�������ӽ��

    if (oElement.className == "CheckedBox") {
        // to do nothing
    } else {
        if (oElement.className == "UnCheckedBox") {
            //oNextSiblingElement.style.display="block";//�������η�����ʱ��չ���ͱպ�
            oElement.className = "CheckedBox";
            //replace����ÿ��ֻ�滻һ��ƥ����ַ���
            newText = newText.replace("unchecked", "checked");
        }
    }
    oElement.innerHTML = newText;

    //��ͬ����Ԫ���е��ı����,�ı��ı�����״̬ CheckedNode->UnCheckedNode
    oChildren = oElement.parentElement.children;
    if (oChildren) {
        for (i = 0; i < oChildren.length; i ++) {
            if (oChildren.item(i).className == "UnCheckedNode") oChildren.item(i).className = "CheckedNode";
        }
        //end for
    }

    //�ı�cookie״̬
    toggleUnSelect2Select(n);
    return true;

}

/**
 * ��ָ���������и����ѡ�С�
 *
 * @param oElement ָ�����Ԫ��
 * @param treeID   ������ڵ�����ʶ
 */
function selectAllParent(oElement, treeID) {

    if (oElement.parentElement.parentElement.sourceIndex == treeID.sourceIndex)
        return true;

    var oPrevSiblingElement;
    //self < div < td < tr < table(tbody)
    var oParentElement = oElement.parentElement.parentElement.parentElement.parentElement;
    if (oParentElement.tagName == "TBODY")
        oParentElement = oParentElement.parentElement;
    var oGrandFatherElement = oParentElement.parentElement;

    var oChildren = oGrandFatherElement.children;

    for (var i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == oParentElement.sourceIndex) {
            oPrevSiblingElement = oChildren.item(i - 1);
        }
    }
    if (!oPrevSiblingElement)return false;

    oChildren = oPrevSiblingElement.children;

    for (var i = 0; i < oChildren.length; i ++) {
        if (oChildren[i].className == "UnCheckedBox") {
            //�ı���classname��ͼƬ
            //oChildren[i].className = "showbg";
            UnSelect2Select(oChildren[i], oChildren[i].id);
            //alert("oChildren[i]:"+oChildren[i].id);
            selectAllParent(oChildren[i], treeID);

            break;
        }//end if
    }
    //end for

    return true
}

/**
 * �������ӽ����Ϊ��ѡ��״̬
 *
 * @param oElement ��ǰ����ʶ
 */
function unSelectAllChildren(oElement) {

    if (! oElement) return false;

    var oChildren = oElement.parentElement.parentElement.children;
    for (var i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == oElement.parentElement.sourceIndex)
            oNextSiblingElement = oChildren.item(i + 1);
    }
    if (! oNextSiblingElement)return false;


    for (var i = 0; i < 2; i ++) {

        oNextSiblingElement = oNextSiblingElement.children.item(0);
        if (oNextSiblingElement.tagName == "TBODY")
            oNextSiblingElement = oNextSiblingElement.children.item(0);
    }

    var newChildren = oNextSiblingElement.children;
    if (! newChildren)
        return false;

    for (var i = 0; i < newChildren.length; i ++) {

        if (newChildren.item(i).tagName == "DIV") {

            var newElement = newChildren.item(i).children;
            if (newElement) {
                if (newElement.item(1).className == "CheckedBox") {
                    //�ı���ͼƬ��classname
                    //newElement.item(1).className = "hidebg";
                    Select2UnSelect(newElement.item(1), newElement.item(1).id);
                }
                unSelectAllChildren(newElement.item(1));
            }
        }


    }
    return true;
}


/**
 * �����ѡ����¼�,ѡ����ѡ��,ȡ������ȡ���ӡ�
 */
function selectSelfAndParents(oElement, treeID, n, treeName) {
    if (oElement.className == "UnCheckedBox") { //δѡ��->ѡ��,��ѡ�����и����
        //�ı�����classname��ͼƬ
        multiSelect(oElement, n);
        //ѡ�����и����
        selectAllParent(oElement, treeID);

        // modify by wh 2004/1/17
        // changeSelect(n,true);
        // end modify

    }
    else {                                     //ѡ��->δѡ��,��ȡ�������ӽ��
        //�ı�����classname��ͼƬ
        multiSelect(oElement, n);
        //ȡ�������ӽ��
        unSelectAllChildren(oElement, treeID);

        // modify by wh 2004/1/17
        // changeSelect(n,true);
        // end modify

    }
}

/**
 * �����ѡ����¼�,ѡ����ȡ�����и�����2003.10.13
 */
function selectSelfUnSelectParentsAndChildren(oElement, treeID, n) {
    if (oElement.className == "UnCheckedBox") { //δѡ��->ѡ��,��ȡ�����и����
        //�ı�����classname��ͼƬ
        multiSelect(oElement, n);
        //ȡ�����и����
        unSelectAllParent(oElement, treeID);
        //ȡ�������ӽ��
        unSelectAllChildren(oElement, treeID);
    }
    else {                                     //ѡ��->δѡ��
        //�ı�����classname��ͼƬ
        multiSelect(oElement, n);
    }
}

/**
 * ��cookie��ȡ������ѡ�еĽ����š�
 *
 * @return Array
 */
function getSelectNodesFromCookie() {

    var returnNodes = new Array();
    var index = returnNodes.length;
    var expanded = "0";

    //ȡcookie
    treeState = getCurrState();
    for (var i = 0; i < treeState.length; i++) {

        expanded = treeState.substring(i, i + 1);

        index = returnNodes.length;
        returnNodes[index] = new Object;

        if (expanded == "1") {
            returnNodes[index].isSelected = 1;
        }
        else {
            returnNodes[index].isSelected = 0;
        }
    }

    return returnNodes;
}


//���·������ɵ���////////////////////////////////////////////////////////////////////////////////
function MM_preloadImages() { //v3.0
    var d = document;
    if (d.images) {
        if (!d.MM_p) d.MM_p = new Array();
        var i,j = d.MM_p.length,a = MM_preloadImages.arguments;
        for (i = 0; i < a.length; i++)
            if (a[i].indexOf("#") != 0) {
                d.MM_p[j] = new Image;
                d.MM_p[j++].src = a[i];
            }
    }
}

function ClickToggleNext(oElement) //for +/- icon.
{
    var newText = new String;
    newText += oElement.innerHTML;
    var oNextSiblingElement;

    var oChildren = oElement.parentElement.parentElement.children;
    for (i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == oElement.parentElement.sourceIndex)
            oNextSiblingElement = oChildren.item(i + 1);
    }
    if (!oNextSiblingElement)return false;
    if (oElement.className == "ClickToggleExpand") {
        oNextSiblingElement.style.display = "none";
        oElement.className = "ClickToggleCollapse";
        //replace����ÿ��ֻ�滻һ��ƥ����ַ���
        newText = newText.replace("open", "close");
        newText = newText.replace("open", "close");

    } else {
        if (oElement.className == "ClickToggleCollapse") {
            oNextSiblingElement.style.display = "block";
            oElement.className = "ClickToggleExpand";

            newText = newText.replace("close", "open");
            newText = newText.replace("close", "open");
        }
    }
    oElement.innerHTML = newText;
    return true
}

/**
 * �������ѡ�еĽ��
 *
 * @param treeID ����ʶ
 */

function clearSelect(treeID) {

    if (! treeID || treeID.innerHTML == "")
        return;

    if (treeID.className == "showbg") {

        treeID.className = "hidebg";
    }

    var oChildren = treeID.children;

    if (oChildren) {
        for (var i = 0; i < oChildren.length; i ++) {

            clearSelect(oChildren.item(i));

        }
        //end for
    }

    return;
}

/**
 * ����cookie��սڵ�
 *
 * @param treeID ����ʶ
 */

function clearSelectByCookie(tree) {

    if (tree.className == "showbg")
        tree.className = "hidebg";
    return;
}

function directLink(oElement, treeID) {
    var tree = eval(getCookie("selOrgID"));

    if (oElement.className == "hidebg")
        if (tree == null)
            clearSelect(treeID);
        else
            clearSelectByCookie(tree);

    oElement.className = "showbg";

    return false;

}

function singleDirectLink(oElement, treeID) {  //�����ϵ�directLink,��������ӽ�㲻��ɫ,SingleTreeBuilder��

    //if (oElement.className == "hidebg")
    //	clearSelect(treeID);
    //
    //oElement.className = "showbg";
    //
    //return false;

}

function singleSelect(oElement, treeID) {

    if (oElement.className == "showbg")
        oElement.className = "hidebg";
    else {
        clearSelect(treeID);

        oElement.className = "showbg";
    }

}

function selectSelfAndSiblings(oElement, treeID) {

    if (oElement.className == "hidebg") {

        oElement.className = "showbg";

        unSelectAllParent(oElement, treeID);

        unSelectAllChildren(oElement);
    }
    else {

        oElement.className = "hidebg";
    }

}

/**
 * ��ָ���������и����չ����
 *
 * @param treeID ������ڵ�����ʶ
 * @param curID  ָ������ʶ  
 */
function expand(treeID, curID) {

    if (treeID.sourceIndex == curID.sourceIndex ||
        treeID.sourceIndex == curID.parentElement.parentElement.sourceIndex)
        return;

    var oParentElement = curID.parentElement.parentElement.parentElement.parentElement;

    if (oParentElement.tagName == "TBODY")
        oParentElement = oParentElement.parentElement;

    var oGrandFatherElement = oParentElement.parentElement;

    var oChildren = oGrandFatherElement.children;

    var oPrevSiblingElement;

    for (var i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == oParentElement.sourceIndex) {
            oPrevSiblingElement = oChildren.item(i - 1);
        }
    }

    if (!oPrevSiblingElement)return false;

    oChildren = oPrevSiblingElement.children;
    if (oChildren && oChildren.length > 1) {
        if (oChildren[0].className == 'ClickToggleCollapse')
            ClickToggleNext(oChildren[0]);

        expand(treeID, oChildren[0]);
    }
}


/**
 * ��ָ������Լ������еĶ���ѡ�У����������ӣ���added by gzl at 040112
 *
 * @param curID  ָ������ʶ  
 */
function selectSelfAndAllChildren(curID)
{
    //ѡ�е�ǰ�ڵ�
    var oChildren = curID.parentElement.children;

    if (!oChildren)
        return false;

    for (var i = 0; i < oChildren.length; i ++)
    {
        if (oChildren.item(i).sourceIndex == curID.sourceIndex)
        {
            if (oChildren.item(i - 1) && oChildren.item(i - 1).className == "UnCheckedBox")
                oChildren.item(i - 1).click();

            if (oChildren.item(i - 2) && oChildren.item(i - 2).className == "ClickToggleCollapse")
                oChildren.item(i - 2).click();

            break;
        }

    }


    //ѡ�����еĶ���
    oChildren = curID.parentElement.parentElement.children;

    var oNextSiblingElement;

    for (var i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == curID.parentElement.sourceIndex)
            oNextSiblingElement = oChildren.item(i + 1);
    }
    if (! oNextSiblingElement)
        return false;

    //ͨ��<table><tr><td>��ǩ
    for (var i = 0; i < 2; i ++) {
        oNextSiblingElement = oNextSiblingElement.children.item(0);
        if (oNextSiblingElement.tagName == "TBODY")
            oNextSiblingElement = oNextSiblingElement.children.item(0);
    }

    var newChildren = oNextSiblingElement.children;
    if (! newChildren)
        return false;

    for (var i = 0; i < newChildren.length; i ++) {

        if (newChildren.item(i).tagName == "DIV") {

            var newElement = newChildren.item(i).children;
            if (newElement) {
                if (newElement.item(1).className == "UnCheckedBox") {
                    //�ı���ͼƬ��classname
                    UnSelect2Select(newElement.item(1), newElement.item(1).id);
                }
            }
        }
    }

    return false;
}

///////Added by Chenhedui.Called from tree_build.js function selectSelfAndParents(oElement, treeID , n,treeName)///////
function changeSelect(n, isChecked) {

    if (isRestore == false) {

        if (isChecked) {//make it cheched
            parent.tmp_outline[n].isSelected = 1;
            var nn = n - 1;
            var superid = parent.tmp_outline[n].parentid;
            var increment = false;
            var incid = -1;
            var subPri = parent.tmp_outline[n].priRes;
            while (nn >= 0) {
                if (parent.tmp_outline[nn].id == superid) {//it is super id
                    if (parent.tmp_outline[nn].isSelected == 0) {//it is not checked before,so it should change its state
                        if (parent.tmp_outline[nn].isModified == 0)
                            parent.tmp_outline[nn].isModified = 1;
                        else
                            parent.tmp_outline[nn].isModified = 0;
                        parent.tmp_outline[nn].resAssigned = parseInt(subPri);
                        parent.tmp_outline[nn].isSelected = 1;
                    }
                    else {
                        if (increment == false)
                            incid = nn;//incid is the first parent node that has been checked
                        increment = true;
                    }
                    if (nn == incid)
                        parent.tmp_outline[nn].resAssigned = parseInt(parent.tmp_outline[nn].resAssigned) + parseInt(subPri);
                    subPri = parent.tmp_outline[nn].priRes;
                    superid = parent.tmp_outline[nn].parentid;
                }
                nn--;
            }
            parent.tmp_outline[n].resAssigned = 0;
        }
        else {//make it unchecked
            parent.tmp_outline[n].isSelected = 0;
            var nn = n + 1;
            var cc = parent.tmp_outline.length;
            var pArr = new Array();
            pArr.push(parent.tmp_outline[n].id);
            while (nn < cc) {//look for the sub-nodes
                if (belongTo(parent.tmp_outline[nn].parentid, pArr) && (parent.tmp_outline[nn].isSelected == 1)) {//it is checked before,so it changs its state
                    if (parent.tmp_outline[nn].isModified == 0)
                        parent.tmp_outline[nn].isModified = 1;
                    else
                        parent.tmp_outline[nn].isModified = 0;
                    pArr.push(parent.tmp_outline[nn].id);
                    parent.tmp_outline[nn].isSelected = 0;
                    //alert(parent.tmp_outline[nn].display+"modified="+parent.tmp_outline[nn].isModified);
                }
                nn++;
            }
            var i = n - 1;
            var parentid = parent.tmp_outline[n].parentid;
            while ((i > 0) && (parent.tmp_outline[i].id != parentid)) i--;
            parent.tmp_outline[i].resAssigned = parseInt(parent.tmp_outline[i].resAssigned) - parseInt(parent.tmp_outline[n].priRes);
        }
        if (parent.tmp_outline[n].isModified == 0)
            parent.tmp_outline[n].isModified = 1;
        else
            parent.tmp_outline[n].isModified = 0;
        //alert(parent.tmp_outline[n].display+"modified="+parent.tmp_outline[n].isModified);
    }
}
function getCookie(cookie_name)
{
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookie_name);

    // ����ҵ����������ʹ���cookie���ڣ�
    // ��֮����˵�������ڡ�
    if (cookie_pos != -1)
    {
        // ��cookie_pos����ֵ�Ŀ�ʼ��ֻҪ��ֵ��1���ɡ�
        cookie_pos += cookie_name.length + 1;
        var cookie_end = allcookies.indexOf(";", cookie_pos);

        if (cookie_end == -1)
        {
            cookie_end = allcookies.length;
        }

        var value = unescape(allcookies.substring(cookie_pos, cookie_end));
    }
    return value;
}

function addSelCookie(selID) {
    document.cookie = "selOrgID=" + escape(selID.id);
}