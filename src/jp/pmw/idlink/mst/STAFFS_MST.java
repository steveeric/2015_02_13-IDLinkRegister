package jp.pmw.idlink.mst;

import jp.pmw.idlink.tmp.TMP_STAFF_MST;

public class STAFFS_MST extends Person{

	private String staffId;
	private String belongId;
	private TMP_STAFF_MST tmp;

	public STAFFS_MST(String staffId,String belongId,TMP_STAFF_MST tmp){
		super(tmp.universityName,tmp.departmentName);
		this.staffId = staffId;
		this.belongId = belongId;
		this.tmp = tmp;
	}

	public String getSTAFF_ID(){
		return this.staffId;
	}
	public String getBELONG_ID(){
		return this.belongId;
	}

	public String getSTAFF_ID_NUMBER(){
		return this.tmp.staffIdNumber;
	}
	public String getRegistration_ACTIVE_STATUS(){
		return this.tmp.registrionActiveStatus ;
	}
	public String getFURIGANA_FAMILY_NAME(){
		return this.tmp.furiganaFamilyName;
	}
	public String getFurigana_GIVEN_NAME(){
		return this.tmp.furiganaGivenName ;
	}
	public String getFAMILY_NAME(){
		return this.tmp.familyName ;
	}
	public String getGIVEN_NAME(){
		return this.tmp.givenName;
	}
	public String getFULL_NAME(){
		return this.tmp.familyName + this.tmp.givenName;
	}
	public String getUniversity_Name(){
		return this.tmp.universityName ;
	}
	public String getEMPLOYMENT(){
		return this.tmp.employment ;
	}
	public String getDIVISION_NAME( ){
		return this.tmp.divisionName  ;
	}
	public String getDEPARTMENT_NAME( ){
		return this.tmp.departmentName;
	}


	/*private String staffId;
	private String universityNumber;
	private String registrationActiveStatus;
	private String staffIdNumber;
	private String furiganaFamilyName;
	private String furiganaGivenName;
	private String familyName;
	private String givenName;
	private String fullName;
	private String employment;
	private String divisionNumber;*/

}
