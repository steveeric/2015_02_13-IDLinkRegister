package jp.pmw.idlink.register;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jenkov.db.PersistenceManager;
import com.jenkov.db.itf.IDaos;
import com.jenkov.db.itf.PersistenceException;

import jp.pmw.id_generator.IdGenerator;
import jp.pmw.idlink.mst.ROOMS_MST;
import jp.pmw.idlink.tmp.TMP_ROOM_MST;
import jp.pmw.log.MyLog;
import jp.pmw.mysql.Connect;
import jp.pmw.sitandgo.config.MyConfig;
import jp.pmw.util.error.UtilError;


public class RoomMstRegist extends MstRegist{
	private String tmpMstTableName = MyConfig.DB_TABLE_TMP_ROOM_MST;

	public RoomMstRegist(){
		super();
		try {
			startProcess();
			MyLog.getInstance().info("移行データ数:"+this.successCount+",移行できず数:"+this.faileCount);
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
		TmpData tmp = new TmpData<TMP_ROOM_MST>();
		List<TMP_ROOM_MST> tmpList = tmp.getTmpRoomMst(TMP_ROOM_MST.class, tmpMstTableName);

		for(int i = 0; i<tmpList.size(); i++){
			if(tmpList.get(i).getUNIVERSITY_NAME() == null){
				MyLog.getInstance().error("大学名が登録されていません.");
			}else{
				String universityName = tmpList.get(i).getUNIVERSITY_NAME();
				String universityNumber = getUniversityNumber(universityName);
				if(universityNumber == null){
					MyLog.getInstance().error("大学名「"+universityName+"」は、DBから大学番号を取得できません.");
				}else{
					//String id = checkerAlradyItem(universityNumber,tmpList.get(i));
					String id = null;
					if(id != null){
						MyLog.getInstance().info(tmpList.get(i).toString());
					}else{
						int registerCount = getYearRegistrationRoomIdCount(universityNumber);
						IdGenerator.setMextUniversity(universityNumber);
						String roomId = IdGenerator.generateRoomId(registerCount);
						ROOMS_MST roomMst = new ROOMS_MST(universityNumber,roomId,tmpList.get(i));
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
		String sql = "SELECT COUNT(ROOM_ID) FROM `ROOMS_MST` WHERE `UNIVERSITY_NUMBER` LIKE '"+universityNumber+"'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = Connect.getInstance().getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				yearRegistrationCount = rs.getInt("COUNT(ROOM_ID)");
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

	private int insertMstDate(ROOMS_MST r) throws PersistenceException{
		//教室情報セット
		return daos.getObjectDao().insert(r);
	}

	private void updateTempCompFlag(TMP_ROOM_MST tmp) throws PersistenceException{
		tmp.setCOMPLETE_FLAG(1);
		//更新
		daos.getObjectDao().update(tmp);
	}




}
