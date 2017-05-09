package com.nl.util.config;

public class DictInfo {
	public String iDictId; //×Öµä±àºÅ

	public String iValue; //valueÖµ

	public String strDictDesc; //×ÖµäÃèÊö

	public String strValueDesc; //valueÃèÊö
	
	public String parentId; //¸¸Àà±àºÅ

    private String remark ;//±¸×¢
    
    private String dict_id;
    private String dict_name;
    private String value;
    private String value_name;
    
    public String getDict_id() {
		return dict_id;
	}

	public void setDict_id(String dict_id) {
		this.dict_id = dict_id;
	}

	public String getDict_name() {
		return dict_name;
	}

	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue_name() {
		return value_name;
	}

	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}

	public DictInfo()
	{}

	public String getIDictId()
	{
		return iDictId;
	}

	public void setIDictId(String dictId)
	{
		iDictId = dictId;
	}

	public String getIValue()
	{
		return iValue;
	}

	public void setIValue(String value)
	{
		iValue = value;
	}

	public String getStrDictDesc()
	{
		return strDictDesc;
	}

	public void setStrDictDesc(String strDictDesc)
	{
		this.strDictDesc = strDictDesc;
	}

	public String getStrValueDesc()
	{
		return strValueDesc;
	}

	public void setStrValueDesc(String strValueDesc)
	{
		this.strValueDesc = strValueDesc;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}
}
