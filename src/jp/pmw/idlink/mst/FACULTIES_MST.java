package jp.pmw.idlink.mst;

import jp.pmw.idlink.tmp.TMP_FACULTY_MST;

public class FACULTIES_MST {
	private String facultyId;
	private String deptId;
	private TMP_FACULTY_MST mst;
	public FACULTIES_MST(String facultyId,String deptId,TMP_FACULTY_MST mst){
		this.facultyId = facultyId;
		this.deptId = deptId;
		this.mst = mst;
	}
	public String getFACULTY_ID(){
		return this.facultyId;
	}
	public String getDept_Id(){
		return this.deptId;
	}
	public String getREGISTRATION_ACTIVE_STATUS(){
		return this.mst.registrationActiveStatus;
	}
	public String getFACULTY_Id_Number(){
		return this.mst.facultyIdNumber;
	}
	public String getEMPLOYMENT(){
		return this.mst.employment;
	}
	public String getPOSITION(){
		return this.mst.position;
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
	public String getNOT_OVERLAP_NAME(){
		return this.mst.notOverlapName;
	}
}

