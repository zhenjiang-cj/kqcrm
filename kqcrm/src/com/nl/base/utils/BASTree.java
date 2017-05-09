package com.nl.base.utils;

import java.io.Serializable;
import java.util.Vector;

/**
 * <p>Title: ������WCS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: �����´�½</p>
 *
 * @version 1.0
 */

public class BASTree implements Serializable {
	public String name;
	public String description;
	public String pageDesc;//�˵�������Ϣ

	/**
	 * Ψһ��
	 */
	public long id;
	public Object data;
	public Vector children;
	public BASTree parent;

	/**
	 * ����㹹�캯��,����ʱָ��һ��������һ���ӽ��Vector
	 * @param name           ���������
	 * @param description    ���������
	 * @param id             �����id
	 * @param data           ���������
	 * @param parent         �����ĸ����
	 * @param vectorChildren �������ӽ������
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
	 * ����㹹�캯��,����ʱָ��һ�������
	 * @param name        �������
	 * @param description ���������
	 * @param id          �����id
	 * @param data        ���������
	 * @param parent      �����ĸ����
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
	 * ����㹹�캯��
	 * @param name        �������
	 * @param description ���������
	 * @param id          �����id
	 * @param data        ���������
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
	 * ����㹹�캯��
	 * @param name        �������
	 * @param description ���������
	 * @param id          �����id
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
         * ����㹹�캯��
         * @param name        �������
         * @param id          �����id
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
	 * ���캯��
	 * ȱʡ��������޲ι��캯��
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
	 * @param name �������
	 * roseuid 3F71364E02CF
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * get  name
	 * @return �����
	 * roseuid 3F71364E0351
	 */
	public String getName() {
		return name;
	}

	/**
	 * set Description
	 * @param description ���������
	 * roseuid 3F71364E0379
	 */
	public void setDescription( String description ) {
		this.description = description;
	}

	/**
	 * get  Description
	 * @return ���������
	 * roseuid 3F71364F0009
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set id
	 * @param id �����id,Ψһ
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
	 * @param data ���������
	 * roseuid 3F71364F00F9
	 */
	public void setData( Object data ) {
		this.data = data;
	}

	/**
	 * get  data
	 * @return ���������
	 * roseuid 3F71364F0186
	 */
	public Object getData() {
		return data;
	}

	/**
	 * get  paretn
	 * @return �����ĸ����
	 * roseuid 3F71364F01B8
	 */
	public BASTree getParent() {
		return parent;
	}

	/**
	 * get  children
	 * @return �����������ӽ�㣬����Vector
	 * roseuid 3F71364F0258
	 */
	public java.util.Vector getChildren() {
		return children;
	}

	/**
	 * ���ر������ӽ�������
	 * @return  �ӽ�����
	 * roseuid 3F71364F029E
	 */
	public int getChildrenAmount() {
		if ( this.children == null ) return 0;
		return children.size();
	}

	/**
	 * ���ر�������ڵ����ĸ���㡣
	 * @return  �����
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
	 * ȡ�øý��Ĳ�Σ������Ϊ0���������μ�һ��
	 * @return int ���Ĳ��
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
	 * ���ݽڵ��id�Խڵ���в���
	 * ���ݴ�������id���ؽ�㣬��Ҫ�Ա�������ڵ�������
	 * @param node ���ĸ�����㿪ʼ�ݹ����
	 * @param id      �����id
	 * @return ����ҵ���id��Ӧ�����ڵ��򷵻�һ���ڵ㣬���򷵻�null;
	 * roseuid 3F71364F0352
	 */
	public BASTree getNodeById( BASTree node , long id ) {

		//id=-9999,�ǲ��Ϸ���,��Ӧ��ʹ���޲ι��캯��
		if ( id == -9999 ) return null;

		//�Ȳ��ұ����
		if ( node.getId() == id )
			return node;

		//�ݹ�����ӽ��
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
	 * ���ݽڵ��id�Խڵ���в���
	 * ���ݴ�������id���ؽ�㣬��Ҫ�Ա�������ڵ�������(�ӱ���ʼ�ݹ�)
	 * @param id      �����id
	 * @return ����ҵ���id��Ӧ�����ڵ��򷵻�һ���ڵ㣬���򷵻�null;
	 * roseuid 3F71364F0352
	 */
	public BASTree getNodeById( long id ) {

		//id=-9999,�ǲ��Ϸ���,��Ӧ��ʹ���޲ι��캯��
		if ( id == -9999 ) return null;

		//�Ȳ��ұ����
		if ( this.getId() == id )
			return this;

		//�ݹ�����ӽ��
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
	 * ���ݽڵ��id�Խڵ���в���
	 * ���ݴ�������id�����ӽ�㣬��Ҫ�Ա�������ڵ�������
	 * @param id      �����id
	 * @return ����ҵ���id��Ӧ�����ڵ��򷵻�һ���ڵ㣬���򷵻�null;
	 * roseuid 3F71364F0352
	 */
	public BASTree getSubNodeById( long id ) {
		BASTree tree ;

		//id=-9999,�ǲ��Ϸ���,��Ӧ��ʹ���޲ι��캯��
		if ( id == -9999 ) return null;

		//�Ȳ��ұ����
		if ( this.getId() == id ) {
			tree = ( BASTree ) this.clone();
			tree.setParent( null );
			return tree;
		}


		//�ݹ�����ӽ��
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
	 * ������ɾ�ĺ���
	 * Ϊһ�������ø����
	 * @param parent ���õĸ����@return boolean
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
	 * ��һ����/�����Ϊ����������/�ӽ��
	 * ͬʱά���������ӽ��ָ����ӽ��ĸ����ָ�롣
	 * @param child ��ӵ��ӽ��
	 * @return boolean
	 * roseuid 3F7136520067
	 */
	public boolean addChild( BASTree child ) {

		if ( this == child )
			return false;
		//�жϸý���Ƿ��Ѵ���
		if ( children != null ) {
			if ( child == null || children.contains( child ) ) {
				return false;
			}
		}

		//�����ӽ���Ƿ����и���㴦��
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
	 * ��һ����/�����Ϊ����������/�ӽ��
	 * ͬʱά���������ӽ��ָ����ӽ��ĸ����ָ�롣
	 * @param child ��ӵ��ӽ��
	 * @return boolean
	 * roseuid 3F7136520067
	 */
	public boolean addChildb( BASTree child ) {

		//�жϸý���Ƿ��Ѵ���
		if ( this.getRoot().getNodeById( child.getId() ) != null )
			return false;
		child.parent = this;
		if ( children == null ) children = new Vector();
		return children.add( child );

	}

	/**
	 * ר��,�ṩ��reverseAdd����,���ݴ����id,��һ����/����Ϊ����������/�ӽ��
	 * ͬʱά���������ӽ��ָ����ӽ��ĸ����ָ�롣
	 * @param parentId ��ӵĸ�����id
	 * @param childId ��ӵ��ӽ���id
	 * @param sourceTree �ӽ���������Դ
	 * roseuid 3F7136520067
	 */
	private void addChildById( long parentId , long childId , BASTree sourceTree ) {
		//�����ӽ�������
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
	 * ɾ��ĳ����һ������/�ӽ�㡣
	 * ��ͬʱά���������ӽ��ָ����ӽ��ĸ����ָ�롣
	 * @param child ɾ�����ӽ��
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
	 * ɾ��ĳ������������/�ӽ�㡣
	 * ��ͬʱά���������ӽ��ָ����ӽ��ĸ����ָ�롣
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
	 * �������ӵ�ָ���ĸ������
	 * @param treeNode BASTree �����
	 * @param parentID long ������ʶ
	 * roseuid 3F98E25201F4
	 */
	public boolean addTreeNode( BASTree treeNode , Integer parentID ) {
		//�ڱ������ҵ���������
        long id = parentID.longValue() ;
		BASTree tmpParentNode = getNodeById( this , id );

		if ( tmpParentNode == null ) {
			//��Ӧû���ҵ��ý������
			return false;
		}
		//������ӽ����뵽�����
		return tmpParentNode.addChild( treeNode );
	}


	/**
	 * �������ӵ�ָ���ĸ������
	 * @param treeNode BASTree �����
	 * @param parentID long ������ʶ
	 * roseuid 3F98E25201F4
	 */
	public boolean addTreeNodeb( BASTree treeNode , long parentID ) {
		//�ڱ������ҵ���������
		BASTree tmpParentNode = getNodeById( this , parentID );

		//������ӽ����뵽�����
		return tmpParentNode.addChildb( treeNode );
	}


	/**
	 * ����������ʶ���ظ���㵽��ǰ������������
	 * @param nodeID ������ʶ
	 * @return String[]
	 * roseuid 3F98E48C0128
	 */
	public String[] getPath( long nodeID ) {
		//�ڱ������ҵ�������
		BASTree tmpNode = getNodeById( this , nodeID );

		if ( tmpNode == null ) {
			//��Ӧû���ҵ��ý������
			return null;
		} else {
			// ���������Ĳ��
			int nodeLevel = tmpNode.getLevel();

			//��ʼ������
			String[] pathName = new String[nodeLevel + 1];

			// �����鸳ֵ
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
	 * ȡ�ý�����һ�����������ȡ���ӣ�û����ȡ����һ���ֵܣ�û���ֵ�ȡ�丸�׵���һ��
	 * �ӡ����û����һ����㣬����null��
	 * @return BASTree �ý�����һ��������
	 * roseuid 3F837AB6008C
	 */
	public BASTree getNext() {    // old code
		//if(this.parent == null )  {
		//    //�����ֻ���ӽ��,���ֵܽ��͸����
		//    if ( this.getChildrenAmount() >  0 ){
		//        //���ص�һ���ӽ��
		//        return (BASTree)(this.children.firstElement());
		//    }
		//    else return null;
		//}
		//else {
		//    //����Ǹ����
		//
		//    int tmp_index =  this.parent.children.indexOf(this) ;
		//    if ( this.getChildrenAmount() >  0 ){
		//         //���ص�һ���ӽ��
		//         return (BASTree)(this.children.firstElement());
		//    }
		//    else  if ( this.parent.getChildrenAmount() > (tmp_index + 1) ) {
		//         //������һ���ֵܽ��
		//         return (BASTree)(this.parent.children.elementAt( tmp_index + 1) );
		//     }
		//     else {
		//        //���ظ��׽���'��һ��'
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
	 * ȡ�ý�����һ�����������ȡ���ӣ�û����ȡ����һ���ֵܣ�û���ֵ�ȡ�丸�׵���һ��
	 * �ӡ����û����һ����㣬����null��
	 * @return BASTree �ý�����һ��������
	 * roseuid 3F837AB6008C
	 */

	public BASTree getNextFast( Vector nodeVector ) {
/*		if ( this.parent == null ) {
			// �����ֻ���ӽ��,���ֵܽ��͸����
			if ( this.getChildrenAmount() > 0 ) {
				//���ص�һ���ӽ��
				return ( BASTree ) ( this.children.firstElement() );
			} else
				return null;
		} else {
			//����Ǹ����

			int tmp_index = this.parent.children.indexOf( this );
			if ( this.getChildrenAmount() > 0 ) {
				//���ص�һ���ӽ��
				return ( BASTree ) ( this.children.firstElement() );
			} else if ( this.parent.getChildrenAmount() > ( tmp_index + 1 ) ) {
				//������һ���ֵܽ��
				return ( BASTree ) ( this.parent.children.elementAt( tmp_index + 1 ) );
			} else {
				//���ظ��׽���'��һ��'
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
	 * ȡ�ý�����һ�����������ȡ���ӣ�û����ȡ����һ���ֵܣ�û���ֵ�ȡ�丸�׵���һ��
	 * �ӡ����û����һ����㣬����null��
	 * @return BASTree �ý�����һ��������
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
	 * �жϸý���Ƿ����ӽ�㡣
	 * @return boolean ���򷵻�true
	 * roseuid 3F837F5A0399
	 */
	public boolean isFather() {
		if ( this.children == null || this.children.size() <= 0 )
			return false;
		else
			return true;
	}

	/**
	 * �жϸý���ǲ����丸�׵����һ���ӡ�
	 * ���жϸý���Ƿ�Ϊvector������һ��Ԫ��
	 * @return boolean ���򷵻�true
	 * roseuid 3F837FB503A9
	 */
	public boolean isLast() {
		// �жϸý���Ƿ�Ϊvector������һ��Ԫ��
		if ( this.parent == null ) {
			//���Ϊ�����,��Ϊֻ��һ�������
			return true;
		} else {
			return ( this.id == ( ( BASTree ) ( this.parent.children.lastElement() ) ).id );
		}
	}

	/**
	 * ���ݴ�������һ��id����,����һ��BASTree����
	 * @param  IDs id����
	 * @return ����BASTree����
	 * roseuid 3F837FB503A9
	 */
	public BASTree[] getTreesByNodeIds( long[] IDs ) {
		if ( IDs == null || IDs.length == 0 )
			return null;
		BASTree treeRoot = this.getRoot();//����������

		boolean nodeFlagArray[] = new boolean[IDs.length];
		Vector treeVector = new Vector();

		//��ʼ��  nodeFlagArray
		for ( int i = 0 ; i < IDs.length ; i++ ) {
			nodeFlagArray[i] = true;
		}
		//��ʼ�� treeVector
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

		//�ҳ���Щ������ "��" ,�˴���id�����ڻᱨ��
		for ( int i = 0 ; i < IDs.length ; i++ ) {
			for ( int j = 0 ; j < IDs.length ; j++ ) {
				if ( i != j && this.getNodeById( treeRoot , IDs[j] ).parent == this.getNodeById( treeRoot , IDs[i] ) ) {
					nodeFlagArray[j] = false;
				}
			}
		}

		//�ж��� "����"
		int treeAmount = 0;
		for ( int i = 0 ; i < nodeFlagArray.length ; i++ ) {
			if ( nodeFlagArray[i] == true ) {
				treeAmount++;
			}
		}


		//Ϊ��Щ "��" ����BASTree����
		BASTree treeArray[] = new BASTree[treeAmount];
		tmpNode = null;
		int tmpInt = 0;

		//�����treeArray����,ÿ��Ԫ�ض���һ�����
		for ( int i = 0 ; i < nodeFlagArray.length ; i++ ) {
			if ( nodeFlagArray[i] == true ) {
				tmpNode = this.getNodeById( treeRoot , IDs[i] );
				treeArray[tmpInt] = new BASTree( tmpNode.name , tmpNode.description , tmpNode.id , tmpNode.data,tmpNode.pageDesc );

				tmpInt++;
			}
		}

		//ΪtreeArray��ÿ��Ԫ�������ӽ��
		for ( int i = 0 ; i < treeAmount ; i++ ) {
			this.reverseAdd( treeArray[i].getId() , IDs , treeArray[i] );
		}

		return treeArray;
	}

   /**
    * ת���̶���νڵ�ΪBASIDNameList
    * @param nLevel  �����
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
	 * ר�ú�����getTreesByNodeIds����,
	 * ��ids������ݹ���id�������㲢�γ�һ����,����һ��BASTree
	 * @param  IDs id����
	 * @param  id  id
	 * @param  returnTree  BASTree
	 * roseuid 3F837FB503A9
	 */
	private void reverseAdd( long id , long[] IDs , BASTree returnTree ) {

		BASTree treeRoot = this.getRoot();
		for ( int i = 0 ; i < IDs.length ; i++ ) {
			if ( this.getNodeById( treeRoot , IDs[i] ).parent == this.getNodeById( treeRoot , id ) ) {
				returnTree.addChildById( id , IDs[i] , treeRoot ); //��IDs[i]��Ϊid���ӽ��
				this.reverseAdd( IDs[i] , IDs , returnTree );
			}
		}
	}

	/**
	 * �ݹ�ǰ�����ָ����㿪ʼ����,����һ��Vector
	 * @param node
	 * roseuid 3F713653004B
	 */
	public void reverseTree( BASTree node , Vector tmpVector ) {
		if ( node == null ) {
			tmpVector = null;
		} else {
			//�����ӱ����
			tmpVector.addElement( node );

			//�ݹ������ӽ��
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
	 * �ݹ�ǰ�����ָ����㿪ʼ����,����һ��Vector
	 * roseuid 3F713653004B
	 */
	public void reverseTree( Vector vTree ) {

		if ( this == null ) {
			return;
		} else {
			//�����ӱ����
			if ( vTree == null )
				vTree = new Vector();
			vTree.addElement( this );

			//�ݹ������ӽ��
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
	 * �ݹ�ǰ�����ָ����㿪ʼ����,����һ��Vector
	 * roseuid 3F713653004B
	 */
	public void reverseID( Vector vTree ) {

		if ( this == null ) {
			return;
		} else {
			//�����ӱ����
			if ( vTree == null )
				vTree = new Vector();
			vTree.addElement( new Long( this.getId() ) );

			//�ݹ������ӽ��
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
	 * ��һ��BASTree����ΪTNS��ʽ
	 * @return Vector
	 * roseuid 3F837FB503A9
	 */
	public TreeNodeStruct[] toTNSFormat() {
		if ( this == null ) return null;

		Vector tmpVector = new Vector();

		//ǰ�����������һ��Vector
		reverseTree( this , tmpVector );
		TreeNodeStruct[] tns = new TreeNodeStruct[tmpVector.size()];

		//����tns
		for ( int i = 0 ; i < tmpVector.size() ; i++ ) {

			BASTree tmpTree = ( BASTree ) tmpVector.elementAt( i );
			tns[i] = new TreeNodeStruct();
			tns[i].setFatherNode( true );
			tns[i].setID( tmpTree.getId() );
			tns[i].setLastNode( true );
			tns[i].setLevel( tmpTree.getLevel() );
			tns[i].setName( tmpTree.getName() );
		}

		//�趨tns��isFather&isLast
		int cur_level ;
		//����isFather
		for ( int k = 0 ; k < tns.length - 1 ; k++ ) {
			cur_level = tns[k].getLevel();
			if ( tns[k + 1].getLevel() <= cur_level )
				tns[k].setFatherNode( false ); //isFather = 0
			else
				tns[k].setFatherNode( true );  //isFather = 1
		}
		tns[tns.length - 1].setFatherNode( false );
		//isFather = 0

		//����isLast
		for ( int x = 0 ; x < tns.length - 1 ; x++ ) {
			cur_level = tns[x].getLevel();
			for ( int y = x + 1 ; y < tns.length ; y++ ) {
				if ( tns[y].getLevel() < cur_level ) {//������һ���ڵ�,Ϊ���һ��
					tns[x].setLastNode( true );  //isLast = 1
					break;
				}
				if ( tns[y].getLevel() == cur_level ) {//����ͬ���ڵ�,�����һ��
					tns[x].setLastNode( false );  //isLast = 1
					break;
				}
				if ( y == tns.length - 1 ) {
					tns[x].setLastNode( true );           //��ĩβ
				}
			}
		}
		tns[tns.length - 1].setLastNode( true );

		return tns;
	}


	/**
	 * ������ʾ����
	 * ��ʾ����㼰�����ӽ����Ϣ��
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
	 * ��ʾָ�������ϸ��Ϣ��
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
	 * ��ʾָ�������ϸ��Ϣ��
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
	 * ������ʾָ�������Ϣ��
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
	 * �ݹ���ʾָ����㿪ʼ��������Ϣ��
	 * @param node
	 * roseuid 3F713653004B
	 */
	public void displayTree( BASTree node ) {
		//����ʾ�����
		displayTreeNode( node );

		//�ݹ���ʾ�ӽ��
		if ( node.children == null || node.children.isEmpty() ) {
			// return
		} else {
			for ( int i = 0 ; i < node.children.size() ; i++ ) {
				displayTree( ( BASTree ) ( node.children.elementAt( i ) ) );
			}
		}
	}

	/**
	 * �ݹ���ʾָ����㿪ʼ��������Ϣ��
	 * roseuid 3F713653004B
	 */
	public void displayTree() {
		//����ʾ�����
		displayTreeNode( this );

		//�ݹ���ʾ�ӽ��
		if ( this.children == null || this.children.isEmpty() ) {
			// return
		} else {
			for ( int i = 0 ; i < this.children.size() ; i++ ) {
				displayTree( ( BASTree ) ( this.children.elementAt( i ) ) );
			}
		}
	}

	/**
	 *�жϽڵ��������Ƿ����ID�Ľڵ�
	 *@param ID �ڵ�ID
	 *@return true ����ID, fasle������ID
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
	 * ��ȡ��һ��Ҷ�ӽڵ��ID
	 * @return     ID��ʶ

	 */
	public long getFirstNode() throws Exception {

		BASTree tTemp = this.getRoot();


		if ( tTemp == null )
			//throw  new UserException( -1 , "��Ϊ��,�޽ڵ�" );
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

		// ���ӽ��
		BASTree myTree = new BASTree( "�й�" , "" , ( long ) 1 );
		BASTree myTree30 = new BASTree( "�㶫" , "" , ( long ) 7 );

		BASTree myTree10 = new BASTree( "����" , " " , ( long ) 2 );
		BASTree myTree11 = new BASTree( "�Ϻ�" , "" , ( long ) 3 );

		BASTree myTree20 = new BASTree( "�Ͼ�" , "" , ( long ) 4 );

		BASTree myTree22 = new BASTree( "���" , "" , ( long ) 6 );


		BASTree myTree32 = new BASTree( "��¥��" , "" , ( long ) 9 );
		BASTree myTree21 = new BASTree( "����" , "" , ( long ) 5 );

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
//���Եݹ���������
//Vector nodeVector = new Vector();
//myTree.reverseTree(myTree,nodeVector );
//
//for( int i =0 ; i<nodeVector.size();i++ )  {
//       System.out.println( ((BASTree)(nodeVector.elementAt(i))).getName());
//}
//System.out.println(nodeVector.toString());

//����getNext()
//BASTree tmpTree = myTree;
//while(tmpTree != null)    {
//    System.out.println (tmpTree.getName() +" - " +  tmpTree.getId() );
//    tmpTree =  tmpTree.getNext();
//}

		//���� getTreesByNodeIds
/*        long ids[] = new long[5];
        ids[0] = 6;
        ids[1] = 3;
        ids[2] = 7;
        ids[3] = 2;
        ids[4] = 9;

        BASTree[] resultTreeArray = myTree.getTreesByNodeIds(ids);
        for (int i = 0; i < resultTreeArray.length; i++) {
            System.out.println(resultTreeArray[i].getName() + "�����:");
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
