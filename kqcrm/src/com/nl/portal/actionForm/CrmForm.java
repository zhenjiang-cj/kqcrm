package com.nl.portal.actionForm;

import com.nl.base.BaseAppActionForm;

public class CrmForm extends BaseAppActionForm {
	private String opflag;
	private String provinces;
	private String city;
	private String region;
	private String kh_id;
	private String kh_name;
	private String kh_phone1;
	private String kh_phone2;
	private String kh_addr;
	private String kh_card;
	private String introduce_name;
	private String channel_source;
	private String is_install;
	private String is_ws;
	
	private String ht_id;
	private String ht_code ;
	private String ht_date_first;
	private String ht_date_current;
	private String ht_pledge;
	private String ht_rent;
	private String prod_name;
	private String prod_code;
	private String ht_year;
	private String remark;
	private String ht_type;
	
	private String hf_id;
	private String hf_date_must;
	private String hf_date_fact;
	private String hf_type;
	private String hf_status;
	private String hf_user_name;
	private String hf_remark;

	private String hf_id1;
	private String hf_date_must1;
	private String hf_date_fact1;
	private String hf_remark1;
	private String hf_user_name1;

	private String hf_id2;
	private String hf_date_must2;
	private String hf_date_fact2;
	private String hf_remark2;
	private String hf_user_name2;

	private String hf_id3;
	private String hf_date_must3;
	private String hf_date_fact3;
	private String hf_remark3;
	private String hf_user_name3;

	private String hf_id4;
	private String hf_date_must4;
	private String hf_date_fact4;
	private String hf_remark4;
	private String hf_user_name4;
	
	private String hf_begin_date;
	private String hf_end_date;
	
	private String[] hf_material;
	
	
	private String ht_begin_date;
	private String ht_end_date;
	private String org_names;
	private String org_ids;
	
	private String repair_id;
	private String warranty_date;
	private String warranty_content;
	private String repair_person;
	private String repair_date;
	private String repair_reason;
	
	private String begin_date;
	private String end_date;
	
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getOrg_ids() {
		return org_ids;
	}
	public void setOrg_ids(String org_ids) {
		this.org_ids = org_ids;
	}
	public String getHt_begin_date() {
		return ht_begin_date;
	}
	public void setHt_begin_date(String ht_begin_date) {
		this.ht_begin_date = ht_begin_date;
	}
	public String getHt_end_date() {
		return ht_end_date;
	}
	public void setHt_end_date(String ht_end_date) {
		this.ht_end_date = ht_end_date;
	}
	public String getProvinces() {
		return provinces;
	}
	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getKh_id() {
		return kh_id;
	}
	public void setKh_id(String kh_id) {
		this.kh_id = kh_id;
	}
	public String getKh_name() {
		return kh_name;
	}
	public void setKh_name(String kh_name) {
		this.kh_name = kh_name;
	}
	public String getKh_phone1() {
		return kh_phone1;
	}
	public void setKh_phone1(String kh_phone1) {
		this.kh_phone1 = kh_phone1;
	}
	public String getKh_phone2() {
		return kh_phone2;
	}
	public void setKh_phone2(String kh_phone2) {
		this.kh_phone2 = kh_phone2;
	}
	public String getKh_addr() {
		return kh_addr;
	}
	public void setKh_addr(String kh_addr) {
		this.kh_addr = kh_addr;
	}
	public String getKh_card() {
		return kh_card;
	}
	public void setKh_card(String kh_card) {
		this.kh_card = kh_card;
	}
	public String getIntroduce_name() {
		return introduce_name;
	}
	public void setIntroduce_name(String introduce_name) {
		this.introduce_name = introduce_name;
	}
	public String getHt_id() {
		return ht_id;
	}
	public void setHt_id(String ht_id) {
		this.ht_id = ht_id;
	}
	public String getHt_date_first() {
		return ht_date_first;
	}
	public void setHt_date_first(String ht_date_first) {
		this.ht_date_first = ht_date_first;
	}
	public String getHt_date_current() {
		return ht_date_current;
	}
	public void setHt_date_current(String ht_date_current) {
		this.ht_date_current = ht_date_current;
	}
	public String getHt_pledge() {
		return ht_pledge;
	}
	public void setHt_pledge(String ht_pledge) {
		this.ht_pledge = ht_pledge;
	}
	public String getHt_rent() {
		return ht_rent;
	}
	public void setHt_rent(String ht_rent) {
		this.ht_rent = ht_rent;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_code() {
		return prod_code;
	}
	public void setProd_code(String prod_code) {
		this.prod_code = prod_code;
	}
	public String getHt_year() {
		return ht_year;
	}
	public void setHt_year(String ht_year) {
		this.ht_year = ht_year;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHf_id() {
		return hf_id;
	}
	public void setHf_id(String hf_id) {
		this.hf_id = hf_id;
	}
	public String getHf_date_must() {
		return hf_date_must;
	}
	public void setHf_date_must(String hf_date_must) {
		this.hf_date_must = hf_date_must;
	}
	public String getHf_date_fact() {
		return hf_date_fact;
	}
	public void setHf_date_fact(String hf_date_fact) {
		this.hf_date_fact = hf_date_fact;
	}
	public String getHf_type() {
		return hf_type;
	}
	public void setHf_type(String hf_type) {
		this.hf_type = hf_type;
	}
	public String getHf_status() {
		return hf_status;
	}
	public void setHf_status(String hf_status) {
		this.hf_status = hf_status;
	}
	public String getHf_user_name() {
		return hf_user_name;
	}
	public void setHf_user_name(String hf_user_name) {
		this.hf_user_name = hf_user_name;
	}
	public String getHf_remark() {
		return hf_remark;
	}
	public void setHf_remark(String hf_remark) {
		this.hf_remark = hf_remark;
	}
	public String getHf_begin_date() {
		return hf_begin_date;
	}
	public void setHf_begin_date(String hf_begin_date) {
		this.hf_begin_date = hf_begin_date;
	}
	public String getHf_end_date() {
		return hf_end_date;
	}
	public void setHf_end_date(String hf_end_date) {
		this.hf_end_date = hf_end_date;
	}
	public String getHt_code() {
		return ht_code;
	}
	public void setHt_code(String ht_code) {
		this.ht_code = ht_code;
	}
	public String getOrg_names() {
		return org_names;
	}
	public void setOrg_names(String org_names) {
		this.org_names = org_names;
	}
	public String getHf_date_must1() {
		return hf_date_must1;
	}
	public void setHf_date_must1(String hf_date_must1) {
		this.hf_date_must1 = hf_date_must1;
	}
	public String getHf_date_fact1() {
		return hf_date_fact1;
	}
	public void setHf_date_fact1(String hf_date_fact1) {
		this.hf_date_fact1 = hf_date_fact1;
	}
	public String getHf_remark1() {
		return hf_remark1;
	}
	public void setHf_remark1(String hf_remark1) {
		this.hf_remark1 = hf_remark1;
	}
	public String getHf_user_name1() {
		return hf_user_name1;
	}
	public void setHf_user_name1(String hf_user_name1) {
		this.hf_user_name1 = hf_user_name1;
	}
	public String getHf_date_must2() {
		return hf_date_must2;
	}
	public void setHf_date_must2(String hf_date_must2) {
		this.hf_date_must2 = hf_date_must2;
	}
	public String getHf_date_fact2() {
		return hf_date_fact2;
	}
	public void setHf_date_fact2(String hf_date_fact2) {
		this.hf_date_fact2 = hf_date_fact2;
	}
	public String getHf_remark2() {
		return hf_remark2;
	}
	public void setHf_remark2(String hf_remark2) {
		this.hf_remark2 = hf_remark2;
	}
	public String getHf_user_name2() {
		return hf_user_name2;
	}
	public void setHf_user_name2(String hf_user_name2) {
		this.hf_user_name2 = hf_user_name2;
	}
	public String getHf_date_must3() {
		return hf_date_must3;
	}
	public void setHf_date_must3(String hf_date_must3) {
		this.hf_date_must3 = hf_date_must3;
	}
	public String getHf_date_fact3() {
		return hf_date_fact3;
	}
	public void setHf_date_fact3(String hf_date_fact3) {
		this.hf_date_fact3 = hf_date_fact3;
	}
	public String getHf_remark3() {
		return hf_remark3;
	}
	public void setHf_remark3(String hf_remark3) {
		this.hf_remark3 = hf_remark3;
	}
	public String getHf_user_name3() {
		return hf_user_name3;
	}
	public void setHf_user_name3(String hf_user_name3) {
		this.hf_user_name3 = hf_user_name3;
	}
	public String getHf_date_must4() {
		return hf_date_must4;
	}
	public void setHf_date_must4(String hf_date_must4) {
		this.hf_date_must4 = hf_date_must4;
	}
	public String getHf_date_fact4() {
		return hf_date_fact4;
	}
	public void setHf_date_fact4(String hf_date_fact4) {
		this.hf_date_fact4 = hf_date_fact4;
	}
	public String getHf_remark4() {
		return hf_remark4;
	}
	public void setHf_remark4(String hf_remark4) {
		this.hf_remark4 = hf_remark4;
	}
	public String getHf_user_name4() {
		return hf_user_name4;
	}
	public void setHf_user_name4(String hf_user_name4) {
		this.hf_user_name4 = hf_user_name4;
	}
	public String getHf_id1() {
		return hf_id1;
	}
	public void setHf_id1(String hf_id1) {
		this.hf_id1 = hf_id1;
	}
	public String getHf_id2() {
		return hf_id2;
	}
	public void setHf_id2(String hf_id2) {
		this.hf_id2 = hf_id2;
	}
	public String getHf_id3() {
		return hf_id3;
	}
	public void setHf_id3(String hf_id3) {
		this.hf_id3 = hf_id3;
	}
	public String getHf_id4() {
		return hf_id4;
	}
	public void setHf_id4(String hf_id4) {
		this.hf_id4 = hf_id4;
	}
	public String getHt_type() {
		return ht_type;
	}
	public void setHt_type(String ht_type) {
		this.ht_type = ht_type;
	}
	public String getChannel_source() {
		return channel_source;
	}
	public void setChannel_source(String channel_source) {
		this.channel_source = channel_source;
	}
	public String getIs_install() {
		return is_install;
	}
	public void setIs_install(String is_install) {
		this.is_install = is_install;
	}
	public String getOpflag() {
		return opflag;
	}
	public void setOpflag(String opflag) {
		this.opflag = opflag;
	}
	public String getIs_ws() {
		return is_ws;
	}
	public void setIs_ws(String is_ws) {
		this.is_ws = is_ws;
	}
	public String[] getHf_material() {
		return hf_material;
	}
	public void setHf_material(String[] hf_material) {
		this.hf_material = hf_material;
	}
	public String getRepair_id() {
		return repair_id;
	}
	public void setRepair_id(String repair_id) {
		this.repair_id = repair_id;
	}
	public String getWarranty_date() {
		return warranty_date;
	}
	public void setWarranty_date(String warranty_date) {
		this.warranty_date = warranty_date;
	}
	public String getWarranty_content() {
		return warranty_content;
	}
	public void setWarranty_content(String warranty_content) {
		this.warranty_content = warranty_content;
	}
	public String getRepair_person() {
		return repair_person;
	}
	public void setRepair_person(String repair_person) {
		this.repair_person = repair_person;
	}
	public String getRepair_date() {
		return repair_date;
	}
	public void setRepair_date(String repair_date) {
		this.repair_date = repair_date;
	}
	public String getRepair_reason() {
		return repair_reason;
	}
	public void setRepair_reason(String repair_reason) {
		this.repair_reason = repair_reason;
	}

}
