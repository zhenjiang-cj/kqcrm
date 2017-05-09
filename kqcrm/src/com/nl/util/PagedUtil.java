package com.nl.util;

import java.util.List;
import com.ibatis.common.util.PaginatedList;

public abstract class PagedUtil {
	
	final static public List getPage(final int pageIndex,final PaginatedList pl)
	{ 
		pl.gotoPage(pageIndex);
		return pl;
	}//--
	
	final static public List getNextPage(final PaginatedList pl)
	{
		if(pl.isNextPageAvailable() )
		{
			final int currIndex = pl.getPageIndex();
			if(pl.nextPage())
			{
			  return pl;
			}
			else
			{
				pl.gotoPage(currIndex);
				return pl;
			}
		}
		else
		{
			throw new RuntimeException("Oracle�α겻�������ҳ");
		}
	}//--
	
	final static public List getPrePage(final PaginatedList pl)
	{
		if(pl.isPreviousPageAvailable()   )
		{  
			final int currIndex = pl.getPageIndex();
			if(pl.previousPage())
			{
				return pl;
			}
			else
			{
				pl.gotoPage(currIndex);
				return pl;
			} 
		}
		else
		{
			throw new RuntimeException("Oracle�α겻������ǰ��ҳ");
		}
	}//--
}
