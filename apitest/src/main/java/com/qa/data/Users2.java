package com.qa.data;

public class Users2 {
	
	String lp_patient_paymenttype;
	String lp_first_name;
	String lp_last_name;
	String lp_phone_number;
	String lp_dateofvisit;
	String lp_amount;
	String lp_provider_identification;
	String lp_insamount;
	String lp_reference_number;
	String lp_returnurl;
	String lp_resp_code;
	String lp_lookupid;
	String lp_respreason_text;
	String lp_referenceid;
	String Token;
	String url;

	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getLp_referenceid() {
		return lp_referenceid;
	}
	public void setLp_referenceid(String lp_referenceid) {
		this.lp_referenceid = lp_referenceid;
	}
	public String getLp_resp_code() {
		return lp_resp_code;
	}
	public void setLp_resp_code(String lp_resp_code) {
		this.lp_resp_code = lp_resp_code;
	}
	public String getLp_lookupid() {
		return lp_lookupid;
	}
	public void setLp_lookupid(String lp_lookupid) {
		this.lp_lookupid = lp_lookupid;
	}
	public String getReferenceid() {
		return lp_referenceid;
	}
	public void setReferenceid(String referenceid) {
		this.lp_referenceid = referenceid;
	}
	public String getLp_respreason_text() {
		return lp_respreason_text;
	}
	public void setLp_respreason_text(String lp_respreason_text) {
		this.lp_respreason_text = lp_respreason_text;
	}
	public Users2() {
		
	}
	
	
	public Users2(String lp_amount, String lp_patient_paymenttype,String lp_first_name,String lp_last_name, String lp_phone_number,String lp_dateofvisit, String lp_provider_identification,String lp_reference_number,String lp_returnurl,String Token) {
		this.lp_patient_paymenttype=lp_patient_paymenttype;
		this.lp_amount=lp_amount;
		this.lp_first_name=lp_first_name;
		this.lp_last_name=lp_last_name;	
		this.lp_phone_number=lp_phone_number;	
		this.lp_dateofvisit=lp_dateofvisit;	
		this.lp_provider_identification=lp_provider_identification;	
		this.lp_reference_number=lp_reference_number;	
		this.lp_returnurl=lp_returnurl;
		
	}
	public String getLp_patient_paymenttype() {
		return lp_patient_paymenttype;
	}
	public void setLp_patient_paymenttype(String lp_patient_paymenttype) {
		this.lp_patient_paymenttype = lp_patient_paymenttype;
	}
	public String getLp_first_name() {
		return lp_first_name;
	}
	public void setLp_first_name(String lp_first_name) {
		this.lp_first_name = lp_first_name;
	}
	public String getLp_last_name() {
		return lp_last_name;
	}
	public void setLp_last_name(String lp_last_name) {
		this.lp_last_name = lp_last_name;
	}
	public String getLp_phone_number() {
		return lp_phone_number;
	}
	public void setLp_phone_number(String lp_phone_number) {
		this.lp_phone_number = lp_phone_number;
	}
	public String getLp_dateofvisit() {
		return lp_dateofvisit;
	}
	public void setLp_dateofvisit(String lp_dateofvisit) {
		this.lp_dateofvisit = lp_dateofvisit;
	}
	public String getLp_amount() {
		return lp_amount;
	}
	public void setLp_amount(String lp_amount) {
		this.lp_amount = lp_amount;
	}
	public String getLp_provider_identification() {
		return lp_provider_identification;
	}
	public void setLp_provider_identification(String lp_provider_identification) {
		this.lp_provider_identification = lp_provider_identification;
	}
	public String getLp_insamount() {
		return lp_insamount;
	}
	public void setLp_insamount(String lp_insamount) {
		this.lp_insamount = lp_insamount;
	}
	public String getLp_reference_number() {
		return lp_reference_number;
	}
	public void setLp_reference_number(String lp_reference_number) {
		this.lp_reference_number = lp_reference_number;
	}
	public String getLp_returnurl() {
		return lp_returnurl;
	}
	public void setLp_returnurl(String lp_returnurl) {
		this.lp_returnurl = lp_returnurl;
	}
	

}
