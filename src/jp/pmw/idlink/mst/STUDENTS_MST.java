package jp.pmw.idlink.mst;

import jp.pmw.idlink.tmp.TMP_STUDENT_MST;

public class STUDENTS_MST {
	private String studentId;
	private String deptId;
	private TMP_STUDENT_MST mst;
	public STUDENTS_MST(String studentId,String deptId,TMP_STUDENT_MST mst){
		this.studentId = studentId;
		this.deptId = deptId;
		this.mst = mst;
	}
	public String getStudent_Id(){
		return this.studentId;
	}
	public String getDept_Id(){
		return this.deptId;
	}
	public String getREGISTRATION_ACTIVE_STATUS(){
		return this.mst.registrationActiveStatus;
	}
	public String getStudent_Id_Number(){
		return this.mst.studentIdNumber;
	}
	public String getENROLLMENT_PERIOD(){
		return this.mst.enrollmentPeriod;
	}
	public String getGrade(){
		return this.mst.grade;
	}
	public String getFURIGANA_FAMILY_NAME(){
		return this.mst.furiganaFamilyName;
	}
	public String getFurigana_GIVEN_NAME(){
		return this.mst.furiganaGivenName ;
	}
	public String getFAMILY_NAME(){
		return this.mst.familyName ;
	}
	public String getGIVEN_NAME(){
		return this.mst.givenName;
	}
	public String getFULL_NAME(){
		return this.mst.familyName + this.mst.givenName;
	}
}
