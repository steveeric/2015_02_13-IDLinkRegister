package jp.pmw.idlink.register;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jenkov.db.itf.PersistenceException;

import jp.pmw.id_generator.IdGenerator;
import jp.pmw.idlink.mst.ROOMS_MST;
import jp.pmw.idlink.mst.STAFFS_MST;
import jp.pmw.idlink.tmp.TMP_ROOM_MST;
import jp.pmw.idlink.tmp.TMP_STAFF_MST;
import jp.pmw.log.MyLog;
import jp.pmw.my.time.MyTime;
import jp.pmw.mysql.Connect;
import jp.pmw.sitandgo.config.MyConfig;
import jp.pmw.util.error.UtilError;

public class StaffMstRegist extends MstRegist{
	private String tmpMstTableName = MyConfig.DB_TABLE_TMP_STAFF_MST;

	public StaffMstRegist(){
		super();
		try {
			startProcess();
			MyLog.getInstance().info(tmpMstTableName + "から,移行データ数:"+this.successCount+",移行できず数:"+this.faileCount);
		} catch (PersistenceException e) {
			// TODO 自動生成された catch ブロック
			MyLog.getInstance().error(e.getMessage());
			UtilError.getInstance().showError(e.getCause());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			MyLog.getInstance().error(e.getMessage());
		}
	}
	private void startProcess() throws PersistenceException, SQLException{
		TmpData tmp = new TmpData<TMP_STAFF_MST>();
		List<TMP_STAFF_MST> tmpList = tmp.getTmpMst(TMP_STAFF_MST.class, tmpMstTableName);

		for(int i = 0; i<tmpList.size(); i++){
			if(tmpList.get(i).getUniversity_Name() == null){
				MyLog.getInstance().error("大学名がありません.");
			}else{
				String universityName = tmpList.get(i).getUniversity_Name();
				String universityNumber = getUniversityNumber(universityName);
				if(universityNumber == null){
					MyLog.getInstance().error("大学名「"+universityName+"」は、DBから大学番号を取得できません.");
				}else{
					//String id = checkerAlradyItem(universityNumber,tmpList.get(i));
					String id = null;
					if(id != null){
						MyLog.getInstance().info("登録済み:"+tmpList.get(i).toString());
					}else{
						//
						String belongId = getBelongId(universityNumber,tmpList.get(i).getDIVISION_NAME(),tmpList.get(i).getDEPARTMENT_NAME());
						if(belongId == null){
							String divname = "";
							String deptname = "";
							if(tmpList.get(i).getDIVISION_NAME() == null){
								divname = "(null)";
							}else{
								divname = tmpList.get(i).getDIVISION_NAME();
							}
							if(tmpList.get(i).getDEPARTMENT_NAME() == null){
								deptname = "(null)";
							}else{
								deptname = tmpList.get(i).getDEPARTMENT_NAME();
							}
							MyLog.getInstance().error("所属を確認できません.大学番号:"+universityNumber+",所属部署名:"+divname+",所属課名:"+deptname);
							//return ;
						}

						int registerCount = getYearRegistrationRoomIdCount(universityNumber);
						String staffId = IdGenerator.generateStaffId(belongId,MyTime.getInstance().getTwoDigitYear(),registerCount);
						STAFFS_MST roomMst = new STAFFS_MST(staffId,belongId,tmpList.get(i));
						int insertCount = insertMstDate(roomMst);
						if(insertCount == 1){
							//成功
							updateTempCompFlag(tmpList.get(i));
							++this.successCount;
						}else{
							//失敗
							++this.faileCount;
							MyLog.getInstance().error("処理不可:"+tmpList.get(i).toString());
						}
					}
				}
			}
		}
	}

	private String getBelongId(String universityNumber,String divisionName,String departmentName) throws SQLException{
		String belongId = null;
		String sql = "SELECT BELONG_ID FROM `BELONGS_MST` WHERE `UNIVERSITY_NUMBER` LIKE '"+universityNumber+"' AND `DIVISION_NAME` LIKE '"+divisionName+"' AND `DEPARTMENT_NAME` LIKE '"+departmentName+"'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = Connect.getInstance().getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				belongId = rs.getString("BELONG_ID");
			}
		} finally {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		return belongId;
	}

	private String getUniversityNumber(String universityName) throws SQLException{
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

	private String checkerAlradyItem(String uriversityNumber,TMP_ROOM_MST tmpItem) throws SQLException{
		String id = null;

		String buildingName = tmpItem.buildingName;
		String roomName = tmpItem.roomName;

		String firstSQL = "SELECT ROOM_ID FROM `ROOMS_MST` WHERE `UNIVERSITY_NUMBER` LIKE '"+uriversityNumber+"' ";
		String secondSQL = "AND CAMPUS_NAME LIKE ";
		String thirdSQL = "AND `BUILDING_NAME` LIKE '"+buildingName+"' AND `ROOM_NAME` LIKE '"+roomName+"'";

		String sql = "";
		if(tmpItem.getCAMPUS_NAME() != null){
			sql = firstSQL + secondSQL + thirdSQL;
		}else{
			sql = firstSQL + thirdSQL;
		}

		System.out.println(sql);

		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = Connect.getInstance().getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getString("ROOM_ID");
			}
		} finally {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
		}
		return id;
	}

	//ROOM_MST用のID数取得
	private int getYearRegistrationRoomIdCount(String universityNumber) throws SQLException{
		int yearRegistrationCount = 0;
		String sql = "SELECT COUNT(STAFF_ID) FROM `STAFFS_MST` WHERE `RECORED_INSERT_DATE_TIME` LIKE '%"+MyTime.getInstance().getTwoDigitYear()+"%'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = Connect.getInstance().getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				yearRegistrationCount = rs.getInt("COUNT(STAFF_ID)");
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

	private int insertMstDate(STAFFS_MST r) throws PersistenceException{
		//教室情報セット
		return daos.getObjectDao().insert(r);
	}

	private void updateTempCompFlag(TMP_STAFF_MST tmp) throws PersistenceException{
		tmp.setCOMPLETE_FLAG(1);
		//更新
		daos.getObjectDao().update(tmp);
	}

}
