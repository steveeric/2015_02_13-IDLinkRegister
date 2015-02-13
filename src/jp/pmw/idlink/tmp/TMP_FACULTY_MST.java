package jp.pmw.idlink.tmp;

public class TMP_FACULTY_MST extends BaseTmp{

	public String facultyIdNumber;
	public String registrationActiveStatus;
	public String furiganaFamilyName;
	public String furiganaGivenName;
	public String familyName;
	public String givenName;
	public String notOverlapName;
	public String universityName;
	public String employment;
	public String position;
	public String deparmtnemtName;

	public TMP_FACULTY_MST(){

	}
	public void setFACULTY_ID_NUMBER(String facultyIdNumber){
		this.facultyIdNumber = facultyIdNumber;
	}

	public void setRegistration_ACTIVE_STATUS(String registrionActiveStatus){
		this.registrationActiveStatus = registrionActiveStatus;
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
	public void setPOSITION(String position){
		this.position = position;
	}
	public void setDEPT_NAME(String departmentName){
		this.deparmtnemtName = departmentName;
	}
}
