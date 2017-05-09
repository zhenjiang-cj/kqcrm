/**
 * 取得所有选中的结点和ID名称数组,2003.10.17。
 *
 * @param treeID 树标识
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
 * 取得所有选中的结点名称数组,2003.10.17。
 *
 * @param treeID 树标识
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
 * 取得所有选中的ID数组,2003.10.17。
 *
 * @param treeID 树标识
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
 * 取得所有选中的ID数组,2004.02.11。
 *
 * @param treeID 树标识
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
 * 清空所有选中的结点,供selectSelfUnSelectOthers调用 2003.10.13
 *
 * @param treeID 树标识
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
 * 单选的,2003.10.13,notice it!!!
 *
 * @param oElement 当前结点标识
 * @param treeID   树标识
 */
function selectSelfUnSelectOthers(oElement, treeID, n) {
    if (oElement.className == "UnCheckedBox") {
        unSelectAllSelectNode(treeID);
        UnSelect2Select(oElement, n);
    }
    return false;
}


/**
 * 将所有父结点设为非选中状态,2003.10.13,notice it!!!
 *
 * @param oElement 当前结点标识
 * @param treeID   树标识
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
            //改变结点classname和图片
            Select2UnSelect(oChildren[i], oChildren[i].id);
        }//end if
        unSelectAllParent(oChildren[i], treeID);
    }
    //end for


    return true;
}


/* 取得树的所有结点的名称。
 *
 * @param treeID 树标识
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
 cookie的操作函数
 ****************************************************************************/

// 将当前设置保存到客户端Cookie中去,cookie名为treeState;
function setCurrState(setting) {
    document.cookie = "treeState=" + escape(setting);
}

// 取得当前Cookie的设置
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

//定义一个全局变量为Cookie的当前状态值
var treeState ;

// 设置初始化状态，如果当前无状态或对应长度为空
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

//  改变一个节点的状态 , 1<-> 0，并存储到Cookie中
function toggle(n) {
    treeState = getCurrState();
    //alert("old state:"+treeState);
    var newString = ""
    var expanded = treeState.substring(n, n + 1) // 点击的节点
    newString += treeState.substring(0, n)
    newString += expanded ^ 1 // 将节点状态置反
    newString += treeState.substring(n + 1, treeState.length)
    setCurrState(newString) // 将新状态回写Cookie中
    //alert("newString:"+newString+"  treeState:"+getCurrState());
}

//  改变一个节点的状态 , 0/1 -> 1，并存储到Cookie中
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
            // 将节点状态置选择
        }
        //alert("i="+i+"k="+k+"-"+treeState.substring(i,i+1));
    }

    setCurrState(newString);
    // 将新状态回写Cookie中
    //alert("In toggleUnSelect2Select : new treeState:"+getCurrState() );

}

//  改变一个节点的状态 , 0/1 -> 0，并存储到Cookie中
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
            // 将节点状态置未选择
        }
        //alert("i="+i+"k="+k+"-"+treeState.substring(i,i+1));
    }

    setCurrState(newString);
    // 将新状态回写Cookie中
    //alert("In toggleSelect2UnSelect : new treeState:"+getCurrState() );

}

/**
 * 点击复选框的事件。
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

    //if(!oNextSiblingElement)return false; //必须屏蔽否则会影响后面的子结点

    if (oElement.className == "CheckedBox") {
        //oNextSiblingElement.style.display="none";//必须屏蔽否则点击时会展开和闭合
        oElement.className = "UnCheckedBox";
        //replace方法每次只替换一个匹配的字符串
        newText = newText.replace("checked", "unchecked");

    } else {
        if (oElement.className == "UnCheckedBox") {
            //oNextSiblingElement.style.display="block";//必须屏蔽否则点击时会展开和闭合
            oElement.className = "CheckedBox";
            //replace方法每次只替换一个匹配的字符串
            newText = newText.replace("unchecked", "checked");
        }
    }
    oElement.innerHTML = newText;

    //找同级的元素中的文本结点,改变文本结点的状态 CheckedNode<->UnCheckedNode
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

    //改变cookie状态
    toggle(n);

    return true;

}

/**
 * 复选框选中->复选框取消。
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

    //if(!oNextSiblingElement)return false; //必须屏蔽否则会影响后面的子结点

    if (oElement.className == "CheckedBox") {
        //oNextSiblingElement.style.display="none";//必须屏蔽否则点击时会展开和闭合
        oElement.className = "UnCheckedBox";
        //replace方法每次只替换一个匹配的字符串
        newText = newText.replace("checked", "unchecked");

    }
    oElement.innerHTML = newText;

    //找同级的元素中的文本结点,改变文本结点的状态 CheckedNode->UnCheckedNode
    oChildren = oElement.parentElement.children;
    if (oChildren) {
        for (i = 0; i < oChildren.length; i ++) {
            if (oChildren.item(i).className == "CheckedNode")
                oChildren.item(i).className = "UnCheckedNode";
        }
        //end for
    }

    //改变cookie状态
    toggleSelect2UnSelect(n);
    return true;

}

/**
 * 复选框取消->复选框选中。
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

    //if(!oNextSiblingElement)return false; //必须屏蔽否则会影响后面的子结点

    if (oElement.className == "CheckedBox") {
        // to do nothing
    } else {
        if (oElement.className == "UnCheckedBox") {
            //oNextSiblingElement.style.display="block";//必须屏蔽否则点击时会展开和闭合
            oElement.className = "CheckedBox";
            //replace方法每次只替换一个匹配的字符串
            newText = newText.replace("unchecked", "checked");
        }
    }
    oElement.innerHTML = newText;

    //找同级的元素中的文本结点,改变文本结点的状态 CheckedNode->UnCheckedNode
    oChildren = oElement.parentElement.children;
    if (oChildren) {
        for (i = 0; i < oChildren.length; i ++) {
            if (oChildren.item(i).className == "UnCheckedNode") oChildren.item(i).className = "CheckedNode";
        }
        //end for
    }

    //改变cookie状态
    toggleUnSelect2Select(n);
    return true;

}

/**
 * 将指定结点的所有父结点选中。
 *
 * @param oElement 指定结点元素
 * @param treeID   结点所在的树标识
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
            //改变结点classname和图片
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
 * 将所有子结点设为非选中状态
 *
 * @param oElement 当前结点标识
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
                    //改变结点图片和classname
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
 * 点击复选框的事件,选子则选父,取消父则取消子。
 */
function selectSelfAndParents(oElement, treeID, n, treeName) {
    if (oElement.className == "UnCheckedBox") { //未选中->选中,则选择所有父结点
        //改本结点的classname和图片
        multiSelect(oElement, n);
        //选择所有父结点
        selectAllParent(oElement, treeID);

        // modify by wh 2004/1/17
        // changeSelect(n,true);
        // end modify

    }
    else {                                     //选中->未选中,则取消所有子结点
        //改本结点的classname和图片
        multiSelect(oElement, n);
        //取消所有子结点
        unSelectAllChildren(oElement, treeID);

        // modify by wh 2004/1/17
        // changeSelect(n,true);
        // end modify

    }
}

/**
 * 点击复选框的事件,选中则取消所有父和子2003.10.13
 */
function selectSelfUnSelectParentsAndChildren(oElement, treeID, n) {
    if (oElement.className == "UnCheckedBox") { //未选中->选中,则取消所有父结点
        //改本结点的classname和图片
        multiSelect(oElement, n);
        //取消所有父结点
        unSelectAllParent(oElement, treeID);
        //取消所有子结点
        unSelectAllChildren(oElement, treeID);
    }
    else {                                     //选中->未选中
        //改本结点的classname和图片
        multiSelect(oElement, n);
    }
}

/**
 * 从cookie中取得所有选中的结点序号。
 *
 * @return Array
 */
function getSelectNodesFromCookie() {

    var returnNodes = new Array();
    var index = returnNodes.length;
    var expanded = "0";

    //取cookie
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


//以下方法不可调用////////////////////////////////////////////////////////////////////////////////
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
        //replace方法每次只替换一个匹配的字符串
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
 * 清空所有选中的结点
 *
 * @param treeID 树标识
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
 * 根据cookie清空节点
 *
 * @param treeID 树标识
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

function singleDirectLink(oElement, treeID) {  //重载老的directLink,但点击的子结点不变色,SingleTreeBuilder用

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
 * 将指定结点的所有父结点展开。
 *
 * @param treeID 结点所在的树标识
 * @param curID  指定结点标识  
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
 * 将指定结点自己和所有的儿子选中（不包括孙子）。added by gzl at 040112
 *
 * @param curID  指定结点标识  
 */
function selectSelfAndAllChildren(curID)
{
    //选中当前节点
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


    //选中所有的儿子
    oChildren = curID.parentElement.parentElement.children;

    var oNextSiblingElement;

    for (var i = 0; i < oChildren.length; i++) {
        if (oChildren.item(i).sourceIndex == curID.parentElement.sourceIndex)
            oNextSiblingElement = oChildren.item(i + 1);
    }
    if (! oNextSiblingElement)
        return false;

    //通过<table><tr><td>标签
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
                    //改变结点图片和classname
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

    // 如果找到了索引，就代表cookie存在，
    // 反之，就说明不存在。
    if (cookie_pos != -1)
    {
        // 把cookie_pos放在值的开始，只要给值加1即可。
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