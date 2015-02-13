package jp.pmw.idlink.register;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.pmw.id_generator.IdGenerator;
import jp.pmw.idlink.mst.Person;
import jp.pmw.my.time.MyTime;
import jp.pmw.mysql.Connect;
import jp.pmw.sitandgo.config.MyConfig;

import com.jenkov.db.itf.PersistenceException;


public class BelongDeptPersonAdapter<T> {

	private Class<T> clazz;
	BelongDeptPersonAdapter(Class<T> clazz){
		this.clazz = clazz;
	}

	public List<T> getTmpData(String tmpTableName) throws PersistenceException{
		TmpData tmp = new TmpData<T>();
		return tmp.getTmpMst(clazz, tmpTableName);
	}

	/*public String serchDeptId(String univNumber,String deptName) throws SQLException{
		return getDeptId(univNumber,deptName);
	}*/

	public String idAssignment(String mstTableName,String univNumber,String deptId) throws SQLException{
		//String universityName = p.getUniversityName();
		//String deptName = p.getDeptName();


		String year = MyTime.getInstance().getTwoDigitYear();
		int registerCount = getYearRegistrationCount(mstTableName);

		String id = null;

		IdGenerator.setMextUniversity(univNumber);

		if(MyConfig.DB_TABLE_STUDENTS_MST.equals(mstTableName)){
			//
			id = IdGenerator.generateStudentId(deptId, year, registerCount);
		}else if(MyConfig.DB_TABLE_FACULTIES_MST.equals(mstTableName)){
			//
			id = IdGenerator.generateFacultyId(deptId, year, registerCount);
		}
		return id;
	}

	//ROOM_MST用のID数取得
	private int getYearRegistrationCount(String mstTableName) throws SQLException{
		int yearRegistrationCount = 0;
		String sql = "SELECT COUNT(*) FROM `"+mstTableName+"` WHERE `RECORED_INSERT_DATE_TIME` LIKE '%"+MyTime.getInstance().getTwoDigitYear()+"%'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = Connect.getInstance().getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				yearRegistrationCount = rs.getInt("COUNT(*)");
			}
		} finally {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		return yearRegistrationCount;

	}

	public String getUniversityNumber(String universityName) throws SQLException{
		String universityNumber = null;
		String sql = "SELECT `UNIVERSITY_NUMBER` FROM `UNIVERSITY_MINISTRY_MST` WHERE `UNIVERSITY_NAME` LIKE '"+universityName+"'";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = Connect.getInstance().getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				universityNumber = rs.getString("UNIVERSITY_NUMBER");
			}
		} finally {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		return universityNumber;
	}

	public String getDeptId(String univNumber,String deptName) throws SQLException{
		String deptSystemId = null;
		String sql = "SELECT D.DEPT_ID FROM `DEPT_MST` D,`DEPT_SYSTEM_MST` DS "
				+ "WHERE DS.UNIVERSITY_NUMBER LIKE '"+univNumber+"' "
						+ "AND D.DEPT_SYSTEM_ID = DS.DEPT_SYSTEM_ID "
						+ "AND D.DEPT_NAME LIKE '"+deptName+"'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = Connect.getInstance().getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				deptSystemId = rs.getString("DEPT_ID");
			}
		} finally {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		return deptSystemId;
	}
}
