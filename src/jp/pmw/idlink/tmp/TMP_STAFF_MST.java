package jp.pmw.idlink.tmp;

public class TMP_STAFF_MST extends BaseTmp{

	public String staffIdNumber;
	public String registrionActiveStatus;
	public String furiganaFamilyName;
	public String furiganaGivenName;
	public String familyName;
	public String givenName;
	public String universityName;
	public String employment;
	public String divisionName;
	public String departmentName;

	public TMP_STAFF_MST(){

	}

	public void setSTAFF_ID_NUMBER(String staffIdNumber){
		this.staffIdNumber = staffIdNumber;
	}
	public void setRegistration_ACTIVE_STATUS(String registrionActiveStatus){
		this.registrionActiveStatus = registrionActiveStatus;
	}

	public void setFURIGANA_FAMILY_NAME(String furiganaFamilyName){
		this.furiganaFamilyName = furiganaFamilyName;
	}

	public void setFurigana_GIVEN_NAME(String furiganaGivenName){
		this.furiganaGivenName = furiganaGivenName;
	}
	public void setFAMILY_NAME(String familyName){
		this.familyName = familyName;
	}
	public void setGIVEN_NAME(String givenName){
		this.givenName = givenName;
	}
	public void setUniversity_Name(String universityName){
		this.universityName = universityName;
	}
	public void setEMPLOYMENT(String employment){
		this.employment = employment;
	}
	public void setDIVISION_NAME(String divisionName){
		this.divisionName = divisionName;
	}
	public void setDEPARTMENT_NAME(String departmentName){
		this.departmentName = departmentName;
	}


	public String getSTAFF_ID_NUMBER(){
		return this.staffIdNumber;
	}
	public String getRegistration_ACTIVE_STATUS(){
		return this.registrionActiveStatus ;
	}
	public String getFURIGANA_FAMILY_NAME(){
		return this.furiganaFamilyName;
	}
	public String getFurigana_GIVEN_NAME(){
		return this.furiganaGivenName ;
	}
	public String getFAMILY_NAME(){
		return this.familyName ;
	}
	public String getGIVEN_NAME(){
		return this.givenName;
	}
	public String getUniversity_Name(){
		return this.universityName ;
	}
	public String getEMPLOYMENT(){
		return this.employment ;
	}
	public String getDIVISION_NAME( ){
		return this.divisionName  ;
	}
	public String getDEPARTMENT_NAME( ){
		return this.departmentName;
	}

}
