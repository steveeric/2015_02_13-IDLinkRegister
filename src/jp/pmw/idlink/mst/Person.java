package jp.pmw.idlink.mst;

public class Person {
	private String universityname;
	private String deptName;
	Person(String universityname,String deptName){
		this.universityname = universityname;
		this.deptName = deptName;
	}

	public String getUniversityName(){
		return this.universityname;
	}
	public String getDeptName(){
		return this.deptName;
	}
}
