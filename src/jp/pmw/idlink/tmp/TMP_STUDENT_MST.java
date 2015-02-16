package jp.pmw.idlink.tmp;

public class TMP_STUDENT_MST extends BaseTmp{

	public String studentIdNumber;
	public String registrationActiveStatus;
	public String furiganaFamilyName;
	public String furiganaGivenName;
	public String familyName;
	public String givenName;
	public String universityName;
	public String enrollmentPeriod;
	public String alias;
	public String grade;
	public String schoolName;
	public String deparmtnemtName;
	public String majorName;
	public String studentRemarks;

	public TMP_STUDENT_MST(){

	}

	public void setStudent_Id_Number(String idNumber){
		this.studentIdNumber = idNumber;
	}
	public void setREGISTRATION_ACTIVE_STATUS(String status){
		this.registrationActiveStatus = status;
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
	public void setENROLLMENT_PERIOD(String enrollmentPeriod){
		this.enrollmentPeriod = enrollmentPeriod;
	}

	public void setAlias(String alias){
		this.alias = alias;
	}
	public void setGrade(String grade){
		this.grade = grade;
	}
	public void setSCHOOL_NAME(String schoolName){
		this.schoolName = schoolName;
	}
	public void setDepartment_Name(String deptName){
		this.deparmtnemtName = deptName;
	}
	public void setMajor_Name(String major){
		this.majorName = major;
	}
	public void setSTUDENT_REMARKS(String remarks){
		this.studentRemarks = remarks;
	}


}
