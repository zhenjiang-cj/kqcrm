package com.nl.base.utils;

import java.io.Serializable;
import java.util.Vector;

/**
 * <p>Title: 镇江人寿WCS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 江苏新大陆</p>
 *
 * @version 1.0
 */

public class BASTree implements Serializable {
	public String name;
	public String description;
	public String pageDesc;//菜单描述信息

	/**
	 * 唯一性
	 */
	public long id;
	public Object data;
	public Vector children;
	public BASTree parent;

	/**
	 * 树结点构造函数,构造时指定一个父结点和一个子结点Vector
	 * @param name           树结点名称
	 * @param description    树结点描述
	 * @param id             树结点id
	 * @param data           树结点数据
	 * @param parent         树结点的父结点
	 * @param vectorChildren 树结点的子结点向量
	 * roseuid 3F71365100C0
	 */
	public BASTree( String name , String description , long id , Object data ,
	                BASTree parent , java.util.Vector vectorChildren ,String pageDesc) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.data = data;
		this.pageDesc = pageDesc;

		this.parent = parent;
		this.children = vectorChildren;
	}

	/**
	 * 树结点构造函数,构造时指定一个父结点
	 * @param name        树结点名
	 * @param description 树结点描述
	 * @param id          树结点id
	 * @param data        树结点数据
	 * @param parent      树结点的父结点
	 * roseuid 3F713650026D
	 */
	public BASTree( String name , String description , long id , Object data , BASTree parent,String pageDesc ) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.data = data;
		this.pageDesc = pageDesc;

		//parent.addChild(this);
		this.parent = parent;
		this.children = null;
	}

	/**
	 * 树结点构造函数
	 * @param name        树结点名
	 * @param description 树结点描述
	 * @param id          树结点id
	 * @param data        树结点数据
	 * roseuid 3F71365000A1
	 */
	public BASTree( String name , String description , long id , Object data,String pageDesc ) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.data = data;
		this.pageDesc = pageDesc;
		this.parent = null;
		this.children = null;
	}

	/**
	 * 树结点构造函数
	 * @param name        树结点名
	 * @param description 树结点描述
	 * @param id          树结点id
	 * roseuid 3F71365000A1
	 */
	public BASTree( String name , String description , Integer id ,String pageDesc) {
		this.name = name;
		this.description = description;
		this.id = id.longValue() ;
		this.data = null;
		this.parent = null;
		this.children = null;
		this.pageDesc = pageDesc;
	}

    /**
         * 树结点构造函数
         * @param name        树结点名
         * @param id          树结点id
         * roseuid 3F71365000A1
         */
        public BASTree( String name ,Integer id ,String pageDesc) {
            this.name = name;
            this.description = "";
            this.id = id.longValue();
    		this.pageDesc = pageDesc;
            this.data = null;
            this.parent = null;
            this.children = null;
        }


	/**
	 * 构造函数
	 * 缺省的树结点无参构造函数
	 * roseuid 3F7136500065
	 */
	public BASTree() {
		this.name = "";
		this.description = "";
		this.pageDesc = "";
		this.id = -9999;
		this.data = new Object();
		this.children = new Vector();
		this.parent = null;
	}

	/**
	 * set name
	 * @param name 树结点名
	 * roseuid 3F71364E02CF
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * get  name
	 * @return 结点名
	 * roseuid 3F71364E0351
	 */
	public String getName() {
		return name;
	}

	/**
	 * set Description
	 * @param description 树结点描述
	 * roseuid 3F71364E0379
	 */
	public void setDescription( String description ) {
		this.description = description;
	}

	/**
	 * get  Description
	 * @return 树结点描述
	 * roseuid 3F71364F0009
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set id
	 * @param id 树结点id,唯一
	 * roseuid 3F71364F003B
	 */
	public void setId( long id ) {
		this.id = id;
	}

	/**
	 * get  id
	 * @return id
	 * roseuid 3F71364F00BD
	 */
	public long getId() {
		return id;
	}

	/**
	 * set data
	 * @param data 树结点数据
	 * roseuid 3F71364F00F9
	 */
	public void setData( Object data ) {
		this.data = data;
	}

	/**
	 * get  data
	 * @return 树结点数据
	 * roseuid 3F71364F0186
	 */
	public Object getData() {
		return data;
	}

	/**
	 * get  paretn
	 * @return 本结点的父结点
	 * roseuid 3F71364F01B8
	 */
	public BASTree getParent() {
		return parent;
	}

	/**
	 * get  children
	 * @return 本结点的所有子结点，放入Vector
	 * roseuid 3F71364F0258
	 */
	public java.util.Vector getChildren() {
		return children;
	}

	/**
	 * 返回本结点的子结点个数。
	 * @return  子结点个数
	 * roseuid 3F71364F029E
	 */
	public int getChildrenAmount() {
		if ( this.children == null ) return 0;
		return children.size();
	}

	/**
	 * 返回本结点所在的树的根结点。
	 * @return  根结点
	 * roseuid 3F71364F02DA
	 */
	public BASTree getRoot() {
		BASTree root = this;
		while ( root.parent != null ) {
			root = root.parent;
		}
		return root;
	}

	/**
	 * 取得该结点的层次，根结点为0，向下依次加一。
	 * @return int 结点的层次
	 * roseuid 3F71364F0316
	 */
	public int getLevel() {
		BASTree root = this;
		int level = 0;
		while ( root.parent != null ) {
			root = root.parent;
			level++;
		}
		return level;
	}

	/**
	 * 根据节点的id对节点进行操作
	 * 根据传进来的id返回结点，需要对本结点所在的树遍历
	 * @param node 从哪个树结点开始递归查找
	 * @param id      树结点id
	 * @return 如果找到该id对应的树节点则返回一个节点，否则返回null;
	 * roseuid 3F71364F0352
	 */
	public BASTree getNodeById( BASTree node , long id ) {

		//id=-9999,是不合法的,不应该使用无参构造函数
		if ( id == -9999 ) return null;

		//先查找本结点
		if ( node.getId() == id )
			return node;

		//递归查找子结点
		if ( node.children == null || node.children.isEmpty() ) {
			// return

		}
        else {
			for ( int i = 0 ; i < node.children.size() ; i++ ) {
				BASTree tmpNode = getNodeById( ( BASTree ) ( node.children.elementAt( i ) ) , id );
				if ( tmpNode != null ) return tmpNode;
			}
		}
		return null;
	}

	/**
	 * 根据节点的id对节点进行操作
	 * 根据传进来的id返回结点，需要对本结点所在的树遍历(从本身开始递归)
	 * @param id      树结点id
	 * @return 如果找到该id对应的树节点则返回一个节点，否则返回null;
	 * roseuid 3F71364F0352
	 */
	public BASTree getNodeById( long id ) {

		//id=-9999,是不合法的,不应该使用无参构造函数
		if ( id == -9999 ) return null;

		//先查找本结点
		if ( this.getId() == id )
			return this;

		//递归查找子结点
		if ( this.children == null || this.children.isEmpty() ) {
			// return

		} else {
			for ( int i = 0 ; i < this.children.size() ; i++ ) {
				BASTree tmpNode = getNodeById( ( BASTree ) ( this.children.elementAt( i ) ) , id );
				if ( tmpNode != null ) return tmpNode;
			}
		}
		return null;
	}


	/**
	 * 根据节点的id对节点进行操作
	 * 根据传进来的id返回子结点，需要对本结点所在的树遍历
	 * @param id      树结点id
	 * @return 如果找到该id对应的树节点则返回一个节点，否则返回null;
	 * roseuid 3F71364F0352
	 */
	public BASTree getSubNodeById( long id ) {
		BASTree tree ;

		//id=-9999,是不合法的,不应该使用无参构造函数
		if ( id == -9999 ) return null;

		//先查找本结点
		if ( this.getId() == id ) {
			tree = ( BASTree ) this.clone();
			tree.setParent( null );
			return tree;
		}


		//递归查找子结点
		if ( this.children == null || this.children.isEmpty() ) {
			// return

		} else {
			for ( int i = 0 ; i < this.children.size() ; i++ ) {
				BASTree tmpNode = getNodeById( ( BASTree ) ( this.children.elementAt( i ) ) , id );
				if ( tmpNode != null ) {
					tree = ( BASTree ) tmpNode.clone();
					tree.setParent( null );
					return tree;
				}
			}
		}
		return null;
	}

	/**
	 * 结点的增删改函数
	 * 为一个树设置父结点
	 * @param parent 设置的父结点@return boolean
	 * roseuid 3F7136510391
	 */
	public boolean setParent( BASTree parent ) {
		if ( this.parent == parent || parent == null ) {
			return false;
		} else {
			return parent.addChild( this );
		}
	}

	/**
	 * 把一棵树/结点作为本树的子树/子结点
	 * 同时维护本结点的子结点指针和子结点的父结点指针。
	 * @param child 添加的子结点
	 * @return boolean
	 * roseuid 3F7136520067
	 */
	public boolean addChild( BASTree child ) {

		if ( this == child )
			return false;
		//判断该结点是否已存在
		if ( children != null ) {
			if ( child == null || children.contains( child ) ) {
				return false;
			}
		}

		//按该子结点是否已有父结点处理
		if ( child.parent == null ) {
			child.parent = this;
			if ( children == null ) children = new Vector();
			return children.add( child );
		} else {
			if ( child.parent.delChild( child ) == false )
				return false;
			child.parent = this;
			if ( children == null ) children = new Vector();
			return children.add( child );
		}
	}

	/**
	 * 把一棵树/结点作为本树的子树/子结点
	 * 同时维护本结点的子结点指针和子结点的父结点指针。
	 * @param child 添加的子结点
	 * @return boolean
	 * roseuid 3F7136520067
	 */
	public boolean addChildb( BASTree child ) {

		//判断该结点是否已存在
		if ( this.getRoot().getNodeById( child.getId() ) != null )
			return false;
		child.parent = this;
		if ( children == null ) children = new Vector();
		return children.add( child );

	}

	/**
	 * 专用,提供给reverseAdd函数,根据传入的id,把一棵树/结点加为本树的子树/子结点
	 * 同时维护本结点的子结点指针和子结点的父结点指针。
	 * @param parentId 添加的父结点的id
	 * @param childId 添加的子结点的id
	 * @param sourceTree 子结点的数据来源
	 * roseuid 3F7136520067
	 */
	private void addChildById( long parentId , long childId , BASTree sourceTree ) {
		//生成子结点的数据
		BASTree sourceNode = sourceTree.getNodeById( sourceTree.getRoot() , childId );
		if ( sourceNode != null ) {
			BASTree childNode = new BASTree( sourceNode.name , sourceNode.description , sourceNode.id , sourceNode.data ,sourceNode.pageDesc);
			( this.getNodeById( this , parentId ) ).addChild( childNode );
		}
	}

	@Override
	public  Object clone() {

		BASTree tree = new BASTree();
		tree.setId( this.id );
		tree.setName( this.name );
		tree.setDescription( this.description );
		tree.setData( this.getData() );

		if ( this.getChildren() == null ) {
		} else {
			Vector v = ( Vector ) this.getChildren().clone();

			for ( int i = 0 ; i < v.size() ; i++ )

				if ( tree.addChild( ( BASTree ) ( ( BASTree ) v.get( i ) ).clone() ) ) ;
		}
		return tree;
	}

	/**
	 * 删除某结点的一个子树/子结点。
	 * 须同时维护本结点的子结点指针和子结点的父结点指针。
	 * @param child 删除的子结点
	 * @return boolean
	 * roseuid 3F7136520126
	 */
	public boolean delChild( BASTree child ) {
		if ( children == null || children.isEmpty() || child == null )
			return false;
		else {
			child.parent = null;
			return children.removeElement( child );
		}
	}

	/**
	 * 删除某结点的所有子树/子结点。
	 * 须同时维护本结点的子结点指针和子结点的父结点指针。
	 * @return boolean
	 * roseuid 3F71365201EE
	 */
	public boolean delAllChildren() {
		if ( children == null || children.isEmpty() )
			return false;
		else {
			for ( int i = 0 ; i < getChildrenAmount() ; i++ ) {
				( ( BASTree ) ( children.elementAt( i ) ) ).parent = null;
			}
			children.removeAllElements();
			return true;
		}
	}

	/**
	 * 将树结点加到指定的父结点下
	 * @param treeNode BASTree 树结点
	 * @param parentID long 父结点标识
	 * roseuid 3F98E25201F4
	 */
	public boolean addTreeNode( BASTree treeNode , Integer parentID ) {
		//在本树上找到这个父结点
        long id = parentID.longValue() ;
		BASTree tmpParentNode = getNodeById( this , id );

		if ( tmpParentNode == null ) {
			//对应没有找到该结点的情况
			return false;
		}
		//把这个子结点加入到父结点
		return tmpParentNode.addChild( treeNode );
	}


	/**
	 * 将树结点加到指定的父结点下
	 * @param treeNode BASTree 树结点
	 * @param parentID long 父结点标识
	 * roseuid 3F98E25201F4
	 */
	public boolean addTreeNodeb( BASTree treeNode , long parentID ) {
		//在本树上找到这个父结点
		BASTree tmpParentNode = getNodeById( this , parentID );

		//把这个子结点加入到父结点
		return tmpParentNode.addChildb( treeNode );
	}


	/**
	 * 根据树结点标识返回根结点到当前结点的名称数组
	 * @param nodeID 树结点标识
	 * @return String[]
	 * roseuid 3F98E48C0128
	 */
	public String[] getPath( long nodeID ) {
		//在本树上找到这个结点
		BASTree tmpNode = getNodeById( this , nodeID );

		if ( tmpNode == null ) {
			//对应没有找到该结点的情况
			return null;
		} else {
			// 给出本树的层次
			int nodeLevel = tmpNode.getLevel();

			//初始化数组
			String[] pathName = new String[nodeLevel + 1];

			// 给数组赋值
			int i = nodeLevel;
			pathName[i] = tmpNode.getName();

			while ( tmpNode.parent != null ) {
				tmpNode = tmpNode.parent;
				i--;
				pathName[i] = tmpNode.getName();
			}

			return pathName;
		}
	}

	/**
	 * 取得结点的下一个，如果有子取其子，没有子取其下一个兄弟，没有兄弟取其父亲的下一个
	 * 子。如果没有下一个结点，返回null。
	 * @return BASTree 该结点的下一个结点对象
	 * roseuid 3F837AB6008C
	 */
	public BASTree getNext() {    // old code
		//if(this.parent == null )  {
		//    //根结点只有子结点,无兄弟结点和父结点
		//    if ( this.getChildrenAmount() >  0 ){
		//        //返回第一个子结点
		//        return (BASTree)(this.children.firstElement());
		//    }
		//    else return null;
		//}
		//else {
		//    //处理非根结点
		//
		//    int tmp_index =  this.parent.children.indexOf(this) ;
		//    if ( this.getChildrenAmount() >  0 ){
		//         //返回第一个子结点
		//         return (BASTree)(this.children.firstElement());
		//    }
		//    else  if ( this.parent.getChildrenAmount() > (tmp_index + 1) ) {
		//         //返回下一个兄弟结点
		//         return (BASTree)(this.parent.children.elementAt( tmp_index + 1) );
		//     }
		//     else {
		//        //返回父亲结点的'下一个'
		//        BASTree tmpNode = this.parent.getNext();
		//        if( tmpNode == this  )
		//           return null;
		//        else
		//           return tmpNode;
		//     }
		//}
		Vector nodeVector = new Vector();
		this.reverseTree( this.getRoot() , nodeVector );

		for ( int i = 0 ; i < nodeVector.size() - 1 ; i++ ) {
			if ( this == (( nodeVector.elementAt( i ) ) ) )
				return ( ( BASTree ) ( nodeVector.elementAt( i + 1 ) ) );
		}
		return null;

	}


	/**
	 * 取得结点的下一个，如果有子取其子，没有子取其下一个兄弟，没有兄弟取其父亲的下一个
	 * 子。如果没有下一个结点，返回null。
	 * @return BASTree 该结点的下一个结点对象
	 * roseuid 3F837AB6008C
	 */

	public BASTree getNextFast( Vector nodeVector ) {
/*		if ( this.parent == null ) {
			// 根结点只有子结点,无兄弟结点和父结点
			if ( this.getChildrenAmount() > 0 ) {
				//返回第一个子结点
				return ( BASTree ) ( this.children.firstElement() );
			} else
				return null;
		} else {
			//处理非根结点

			int tmp_index = this.parent.children.indexOf( this );
			if ( this.getChildrenAmount() > 0 ) {
				//返回第一个子结点
				return ( BASTree ) ( this.children.firstElement() );
			} else if ( this.parent.getChildrenAmount() > ( tmp_index + 1 ) ) {
				//返回下一个兄弟结点
				return ( BASTree ) ( this.parent.children.elementAt( tmp_index + 1 ) );
			} else {
				//返回父亲结点的'下一个'
				BASTree tmpNode = this.parent.getNextFast(nPos);
				if ( tmpNode == this )
					return null;
				else
					return tmpNode;
			}
		}*/
		if ( nodeVector.size() == 0 ) {
			this.getRoot().reverseTree( nodeVector );
		}

		for ( int i = 0 ; i < nodeVector.size() - 1 ; i++ ) {
			if ( this == (( nodeVector.elementAt( i ) )))
				return ( ( BASTree ) ( nodeVector.elementAt( i + 1 ) ) );
		}
		return null;
	}

	/**
	 * 取得结点的下一个，如果有子取其子，没有子取其下一个兄弟，没有兄弟取其父亲的下一个
	 * 子。如果没有下一个结点，返回null。
	 * @return BASTree 该结点的下一个结点对象
	 * roseuid 3F837AB6008C
	 */

	public BASTree getNextFast() {

		Vector nodeVector = new Vector();
		this.getRoot().reverseTree( nodeVector );


		for ( int i = 0 ; i < nodeVector.size() - 1 ; i++ ) {
			if ( this == (( nodeVector.elementAt( i ) ) ) )
				return ( ( BASTree ) ( nodeVector.elementAt( i + 1 ) ) );
		}
		return null;
	}

	/**
	 * 判断该结点是否有子结点。
	 * @return boolean 有则返回true
	 * roseuid 3F837F5A0399
	 */
	public boolean isFather() {
		if ( this.children == null || this.children.size() <= 0 )
			return false;
		else
			return true;
	}

	/**
	 * 判断该结点是不是其父亲的最后一个子。
	 * 即判断该结点是否为vector里的最后一个元素
	 * @return boolean 是则返回true
	 * roseuid 3F837FB503A9
	 */
	public boolean isLast() {
		// 判断该结点是否为vector里的最后一个元素
		if ( this.parent == null ) {
			//结点为根结点,因为只有一个根结点
			return true;
		} else {
			return ( this.id == ( ( BASTree ) ( this.parent.children.lastElement() ) ).id );
		}
	}

	/**
	 * 根据传进来的一个id数组,传出一个BASTree数组
	 * @param  IDs id数组
	 * @return 返回BASTree数组
	 * roseuid 3F837FB503A9
	 */
	public BASTree[] getTreesByNodeIds( long[] IDs ) {
		if ( IDs == null || IDs.length == 0 )
			return null;
		BASTree treeRoot = this.getRoot();//本树的树根

		boolean nodeFlagArray[] = new boolean[IDs.length];
		Vector treeVector = new Vector();

		//初始化  nodeFlagArray
		for ( int i = 0 ; i < IDs.length ; i++ ) {
			nodeFlagArray[i] = true;
		}
		//初始化 treeVector
		BASTree tmpNode ;
		BASTree newNode ;
		for ( int i = 0 ; i < IDs.length ; i++ ) {
			tmpNode = this.getNodeById( this , IDs[i] );
			if ( tmpNode != null ) {
				newNode = new BASTree( tmpNode.name , tmpNode.description , tmpNode.id , tmpNode.data ,tmpNode.pageDesc);
			} else {
				newNode = new BASTree();
			}
			treeVector.addElement( newNode );
		}

		//找出那些真正的 "根" ,此处若id不存在会报错
		for ( int i = 0 ; i < IDs.length ; i++ ) {
			for ( int j = 0 ; j < IDs.length ; j++ ) {
				if ( i != j && this.getNodeById( treeRoot , IDs[j] ).parent == this.getNodeById( treeRoot , IDs[i] ) ) {
					nodeFlagArray[j] = false;
				}
			}
		}

		//有多少 "树根"
		int treeAmount = 0;
		for ( int i = 0 ; i < nodeFlagArray.length ; i++ ) {
			if ( nodeFlagArray[i] == true ) {
				treeAmount++;
			}
		}


		//为这些 "根" 建立BASTree数组
		BASTree treeArray[] = new BASTree[treeAmount];
		tmpNode = null;
		int tmpInt = 0;

		//最初的treeArray数组,每个元素都是一个结点
		for ( int i = 0 ; i < nodeFlagArray.length ; i++ ) {
			if ( nodeFlagArray[i] == true ) {
				tmpNode = this.getNodeById( treeRoot , IDs[i] );
				treeArray[tmpInt] = new BASTree( tmpNode.name , tmpNode.description , tmpNode.id , tmpNode.data,tmpNode.pageDesc );

				tmpInt++;
			}
		}

		//为treeArray的每个元素增加子结点
		for ( int i = 0 ; i < treeAmount ; i++ ) {
			this.reverseAdd( treeArray[i].getId() , IDs , treeArray[i] );
		}

		return treeArray;
	}

   /**
    * 转换固定层次节点为BASIDNameList
    * @param nLevel  层次数
    * @return BASIDNameList
    */
	public BASIDNameList getIDNameListByLevel( int nLevel ) {
		Vector trees = new Vector();
		this.reverseTree( this , trees );
		BASIDNameList list = new BASIDNameList();
		BASTree tree ;

		for ( int i = 0 ; i < trees.size() ; i++ ) {
			tree = ( BASTree ) trees.get( i );
			if ( tree.getLevel() == nLevel )
				list.addItem( tree.getId() , tree.getName() );
		}
		return list;
	}

	/**
	 * 专用函数供getTreesByNodeIds调用,
	 * 在ids数组里递归找id的子孙结点并形成一棵树,放入一个BASTree
	 * @param  IDs id数组
	 * @param  id  id
	 * @param  returnTree  BASTree
	 * roseuid 3F837FB503A9
	 */
	private void reverseAdd( long id , long[] IDs , BASTree returnTree ) {

		BASTree treeRoot = this.getRoot();
		for ( int i = 0 ; i < IDs.length ; i++ ) {
			if ( this.getNodeById( treeRoot , IDs[i] ).parent == this.getNodeById( treeRoot , id ) ) {
				returnTree.addChildById( id , IDs[i] , treeRoot ); //把IDs[i]加为id的子结点
				this.reverseAdd( IDs[i] , IDs , returnTree );
			}
		}
	}

	/**
	 * 递归前序遍历指定结点开始的树,放入一个Vector
	 * @param node
	 * roseuid 3F713653004B
	 */
	public void reverseTree( BASTree node , Vector tmpVector ) {
		if ( node == null ) {
			tmpVector = null;
		} else {
			//先增加本结点
			tmpVector.addElement( node );

			//递归增加子结点
			if ( node.children == null || node.children.isEmpty() ) {
				// return
			} else {
				for ( int i = 0 ; i < node.children.size() ; i++ ) {
					reverseTree( ( BASTree ) ( node.children.elementAt( i ) ) , tmpVector );
				}
			}
		}
	}

	/**
	 * 递归前序遍历指定结点开始的树,放入一个Vector
	 * roseuid 3F713653004B
	 */
	public void reverseTree( Vector vTree ) {

		if ( this == null ) {
			return;
		} else {
			//先增加本结点
			if ( vTree == null )
				vTree = new Vector();
			vTree.addElement( this );

			//递归增加子结点
			if ( this.children == null || this.children.isEmpty() ) {
				// return
			} else {
				for ( int i = 0 ; i < this.children.size() ; i++ ) {
					( ( BASTree ) this.children.get( i ) ).reverseTree( vTree );
				}
			}
		}
	}


	/**
	 * 递归前序遍历指定结点开始的树,放入一个Vector
	 * roseuid 3F713653004B
	 */
	public void reverseID( Vector vTree ) {

		if ( this == null ) {
			return;
		} else {
			//先增加本结点
			if ( vTree == null )
				vTree = new Vector();
			vTree.addElement( new Long( this.getId() ) );

			//递归增加子结点
			if ( this.children == null || this.children.isEmpty() ) {
				// return
			} else {
				for ( int i = 0 ; i < this.children.size() ; i++ ) {
					( ( BASTree ) this.children.get( i ) ).reverseID( vTree );
				}
			}
		}
	}


	/**
	 * 把一棵BASTree树化为TNS格式
	 * @return Vector
	 * roseuid 3F837FB503A9
	 */
	public TreeNodeStruct[] toTNSFormat() {
		if ( this == null ) return null;

		Vector tmpVector = new Vector();

		//前序遍历树放入一个Vector
		reverseTree( this , tmpVector );
		TreeNodeStruct[] tns = new TreeNodeStruct[tmpVector.size()];

		//生成tns
		for ( int i = 0 ; i < tmpVector.size() ; i++ ) {

			BASTree tmpTree = ( BASTree ) tmpVector.elementAt( i );
			tns[i] = new TreeNodeStruct();
			tns[i].setFatherNode( true );
			tns[i].setID( tmpTree.getId() );
			tns[i].setLastNode( true );
			tns[i].setLevel( tmpTree.getLevel() );
			tns[i].setName( tmpTree.getName() );
		}

		//设定tns的isFather&isLast
		int cur_level ;
		//计算isFather
		for ( int k = 0 ; k < tns.length - 1 ; k++ ) {
			cur_level = tns[k].getLevel();
			if ( tns[k + 1].getLevel() <= cur_level )
				tns[k].setFatherNode( false ); //isFather = 0
			else
				tns[k].setFatherNode( true );  //isFather = 1
		}
		tns[tns.length - 1].setFatherNode( false );
		//isFather = 0

		//计算isLast
		for ( int x = 0 ; x < tns.length - 1 ; x++ ) {
			cur_level = tns[x].getLevel();
			for ( int y = x + 1 ; y < tns.length ; y++ ) {
				if ( tns[y].getLevel() < cur_level ) {//遇上上一级节点,为最后一个
					tns[x].setLastNode( true );  //isLast = 1
					break;
				}
				if ( tns[y].getLevel() == cur_level ) {//遇上同级节点,非最后一个
					tns[x].setLastNode( false );  //isLast = 1
					break;
				}
				if ( y == tns.length - 1 ) {
					tns[x].setLastNode( true );           //到末尾
				}
			}
		}
		tns[tns.length - 1].setLastNode( true );

		return tns;
	}


	/**
	 * 树的显示函数
	 * 显示本结点及所有子结点信息。
	 * roseuid 3F713652023E
	 */
	public void displaySelf() {
		System.out.println( "\n******************\nCurrent tree info " );
		System.out.println( "name: " + name );
		System.out.println( "description: " + description );
		System.out.println( "id: " + id );
		System.out.println( "data: " + data );
		//  System.out.println(children == null) ;
		if ( children != null && !children.isEmpty() ) {
			System.out.println( "*** Children info : Alltogether  " +
			        getChildrenAmount() + " children . " );
			for ( int i = 0 ; i < children.size() ; i++ ) {
				System.out.println( "name: " +
				        ( ( BASTree ) children.elementAt( i ) ).name );
				System.out.println( "description: " +
				        ( ( BASTree ) children.elementAt( i ) ).description );
				System.out.println( "id: " +
				        ( ( BASTree ) children.elementAt( i ) ).id );
				System.out.println( "data: " +
				        ( ( BASTree ) children.elementAt( i ) ).data );
			}
			System.out.println( "*** Children info end . \n " );
		} else {
			System.out.println( "No children avaliable . \n " );
		}
	}

	/**
	 * 显示指定结点详细信息。
	 * @param node
	 * roseuid 3F713652028E
	 */
	public void displayNode( BASTree node ) {
		System.out.println( "name: " + node.getName() );
		System.out.println( "description: " + node.getDescription() );
		System.out.println( "id: " + node.getId() );
		System.out.println( "data: " + node.getData() );
		System.out.print( node.getChildrenAmount() + " children : " );
		for ( int i = 0 ; i < node.getChildrenAmount() ; i++ ) {
			System.out.print( ( ( BASTree ) ( node.children.elementAt( i ) ) ).getName() + " , " );
		}
		System.out.println( " ." );
	}


	/**
	 * 显示指定结点详细信息。
	 * roseuid 3F713652028E
	 */
	public void displayNode() {
		System.out.println( "name: " + this.getName() );
		System.out.println( "description: " + this.getDescription() );
		System.out.println( "id: " + this.getId() );
		System.out.println( "data: " + this.getData() );
		System.out.print( this.getChildrenAmount() + " children : " );
		for ( int i = 0 ; i < this.getChildrenAmount() ; i++ ) {
			System.out.print( ( ( BASTree ) ( this.children.elementAt( i ) ) ).getName() + " , " );
		}
		System.out.println( " ." );
	}

	/**
	 * 树形显示指定结点信息。
	 * @param node
	 * roseuid 3F7136520361
	 */
	public void displayTreeNode( BASTree node ) {
		String displayString = "   ";
		for ( int i = 0 ; i < node.getLevel() ; i++ )
			displayString = displayString + "-----";

		System.out.println( displayString + node.getName() + ":"
		        + node.getId() );
	}

	/**
	 * 递归显示指定结点开始的树的信息。
	 * @param node
	 * roseuid 3F713653004B
	 */
	public void displayTree( BASTree node ) {
		//先显示本结点
		displayTreeNode( node );

		//递归显示子结点
		if ( node.children == null || node.children.isEmpty() ) {
			// return
		} else {
			for ( int i = 0 ; i < node.children.size() ; i++ ) {
				displayTree( ( BASTree ) ( node.children.elementAt( i ) ) );
			}
		}
	}

	/**
	 * 递归显示指定结点开始的树的信息。
	 * roseuid 3F713653004B
	 */
	public void displayTree() {
		//先显示本结点
		displayTreeNode( this );

		//递归显示子结点
		if ( this.children == null || this.children.isEmpty() ) {
			// return
		} else {
			for ( int i = 0 ; i < this.children.size() ; i++ ) {
				displayTree( ( BASTree ) ( this.children.elementAt( i ) ) );
			}
		}
	}

	/**
	 *判断节点所在树是否包含ID的节点
	 *@param ID 节点ID
	 *@return true 包含ID, fasle不包含ID
	 */
	public boolean isNode( long ID ) {

		BASTree tRoot = this.getRoot();
		if ( tRoot == null )
			return false;

		if ( this.getNodeById( tRoot , ID ) == null )
			return false;

		return true;
	}

	/**
	 * 获取第一个叶子节点的ID
	 * @return     ID标识

	 */
	public long getFirstNode() throws Exception {

		BASTree tTemp = this.getRoot();


		if ( tTemp == null )
			//throw  new UserException( -1 , "树为空,无节点" );
                  ;
		else
			while ( tTemp.children != null && !tTemp.children.isEmpty() ) {
				tTemp = ( BASTree ) tTemp.getChildren().get( 0 );

			}


		return tTemp.getId();
	}


	/**
	 * test code
	 *roseuid 3F7136530131
	 */
	public static void main( String args[] ) {
		/*System.out.println( "Let's  Start" );

		// 增加结点
		BASTree myTree = new BASTree( "中国" , "" , ( long ) 1 );
		BASTree myTree30 = new BASTree( "广东" , "" , ( long ) 7 );

		BASTree myTree10 = new BASTree( "江苏" , " " , ( long ) 2 );
		BASTree myTree11 = new BASTree( "上海" , "" , ( long ) 3 );

		BASTree myTree20 = new BASTree( "南京" , "" , ( long ) 4 );

		BASTree myTree22 = new BASTree( "徐汇" , "" , ( long ) 6 );


		BASTree myTree32 = new BASTree( "鼓楼区" , "" , ( long ) 9 );
		BASTree myTree21 = new BASTree( "苏州" , "" , ( long ) 5 );

		myTree.addChild( myTree10 );
		myTree.addChild( myTree11 );
		myTree.addChild( myTree30 );

		myTree10.addChild( myTree20 );
		myTree10.addChild( myTree21 );
		myTree20.addChild( myTree32 );
		myTree11.addChild( myTree22 );

		//myTree.displayTree();
		Vector v = new Vector();
		myTree.reverseTree( v );
		for ( int i = 0 ; i < v.size() ; i++ ) {
			BASTree t = ( BASTree ) v.elementAt( i );
			t.displaySelf();
		}*/
/*		Vector temp = new Vector();
		do {
			System.out.println( myTree.name );
			myTree = myTree.getNextFast( temp );
		} while ( myTree != null );*/
/*


		System.out.println( "__________________________________" );

		( ( BASTree ) myTree.clone() ).displayTree();
*/



		//System.out.println("add : "+myTree10.addChild(myTree20));
		//System.out.println("add : "+myTree10.addChild(myTree21));
		//System.out.println("del : "+myTree10.delChild(myTree20));
		//System.out.println("del : "+myTree10.delChild(myTree20));
/*
        myTree.displayTree(myTree21.getRoot());
        System.out.println("***********");
        myTree10.delAllChildren();
        System.out.println(myTree10.addChild(myTree21));
        System.out.println(myTree10.addChild(myTree21));
        myTree.displayTree(myTree10.getRoot());
        System.out.println("***********");*/
//测试递归遍历树结点
//Vector nodeVector = new Vector();
//myTree.reverseTree(myTree,nodeVector );
//
//for( int i =0 ; i<nodeVector.size();i++ )  {
//       System.out.println( ((BASTree)(nodeVector.elementAt(i))).getName());
//}
//System.out.println(nodeVector.toString());

//测试getNext()
//BASTree tmpTree = myTree;
//while(tmpTree != null)    {
//    System.out.println (tmpTree.getName() +" - " +  tmpTree.getId() );
//    tmpTree =  tmpTree.getNext();
//}

		//测试 getTreesByNodeIds
/*        long ids[] = new long[5];
        ids[0] = 6;
        ids[1] = 3;
        ids[2] = 7;
        ids[3] = 2;
        ids[4] = 9;

        BASTree[] resultTreeArray = myTree.getTreesByNodeIds(ids);
        for (int i = 0; i < resultTreeArray.length; i++) {
            System.out.println(resultTreeArray[i].getName() + "的情况:");
            resultTreeArray[i].displayTree(resultTreeArray[i]);
        }*/

	}

	public String getPageDesc() {
		return pageDesc;
	}

	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}

}
