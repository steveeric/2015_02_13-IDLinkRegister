package jp.pmw.idlink.register;

import java.sql.SQLException;
import java.util.List;
import com.jenkov.db.itf.PersistenceException;
import jp.pmw.idlink.mst.FACULTIES_MST;
import jp.pmw.idlink.mst.STUDENTS_MST;
import jp.pmw.idlink.tmp.TMP_FACULTY_MST;
import jp.pmw.idlink.tmp.TMP_STUDENT_MST;
import jp.pmw.log.MyLog;
import jp.pmw.sitandgo.config.MyConfig;
import jp.pmw.util.error.UtilError;

public class BelongDeptPersonRegist extends MstRegist{

	//学生　→　教員

	private String[] DB_TMP_ODER_TABLE = {
			MyConfig.DB_TABLE_TMP_STUDENT_MST,
			MyConfig.DB_TABLE_TMP_FACULTY_MST
	};

	public BelongDeptPersonRegist(){
		super();
		try {
			startProcess();
		} catch (PersistenceException e) {
			MyLog.getInstance().error(e.getMessage());
			UtilError.getInstance().showError(e.getCause());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			MyLog.getInstance().error(e.getMessage());
		}
	}

	private void startProcess() throws PersistenceException, SQLException{
		BelongDeptPersonAdapter tmpAdapter = null;
		for(int i=0; i<DB_TMP_ODER_TABLE.length; i++){
			String tmpTableName = DB_TMP_ODER_TABLE[i];

			if(MyConfig.DB_TABLE_TMP_STUDENT_MST.equals(tmpTableName)){
				studentProcess(tmpAdapter,tmpTableName);
			}else if(MyConfig.DB_TABLE_TMP_FACULTY_MST.equals(tmpTableName)){
				facultyProcess(tmpAdapter,tmpTableName);
			}
		}
	}

	private void studentProcess(BelongDeptPersonAdapter tmpAdapter,String tmpTableName) throws PersistenceException, SQLException{
		String mstTableName = MyConfig.DB_TABLE_STUDENTS_MST;

		tmpAdapter = new BelongDeptPersonAdapter<TMP_STUDENT_MST>(TMP_STUDENT_MST.class);
		List<TMP_STUDENT_MST> tmpList = tmpAdapter.getTmpData(tmpTableName);
		for(int i=0;i<tmpList.size();i++){
			String univeName = tmpList.get(i).universityName;
			String deptName = tmpList.get(i).deparmtnemtName;
			String univeNumber = tmpAdapter.getUniversityNumber(univeName);
			String deptId = tmpAdapter.getDeptId(univeNumber,deptName);
			String id = tmpAdapter.idAssignment(mstTableName, univeNumber,deptId);
			STUDENTS_MST mst = new STUDENTS_MST(id,deptId,tmpList.get(i));
			int result = daos.getObjectDao().insert(mst);

			if(result == 1){
				++successCount;
				tmpList.get(i).setCOMPLETE_FLAG(1);
				//更新
				daos.getObjectDao().update(tmpList.get(i));
			}else{
				++faileCount;
			}
		}
		MyLog.getInstance().info(tmpTableName + "から,移行データ数:"+this.successCount+",移行できず数:"+this.faileCount);
	}

	private void facultyProcess(BelongDeptPersonAdapter tmpAdapter,String tmpTableName) throws PersistenceException, SQLException{
		String mstTableName = MyConfig.DB_TABLE_FACULTIES_MST;

		tmpAdapter = new BelongDeptPersonAdapter<TMP_FACULTY_MST>(TMP_FACULTY_MST.class);
		List<TMP_FACULTY_MST> tmpList = tmpAdapter.getTmpData(tmpTableName);
		for(int i=0;i<tmpList.size();i++){
			String univeName = tmpList.get(i).universityName;
			String deptName = tmpList.get(i).deparmtnemtName;
			String univeNumber = tmpAdapter.getUniversityNumber(univeName);
			String deptId = tmpAdapter.getDeptId(univeNumber,deptName);
			String id = tmpAdapter.idAssignment(mstTableName, univeNumber,deptId);
			FACULTIES_MST mst = new FACULTIES_MST(id,deptId,tmpList.get(i));
			int result = daos.getObjectDao().insert(mst);

			if(result == 1){
				++successCount;
				tmpList.get(i).setCOMPLETE_FLAG(1);
				//更新
				daos.getObjectDao().update(tmpList.get(i));
			}else{
				++faileCount;
			}
		}
		MyLog.getInstance().info(tmpTableName + "から,移行データ数:"+this.successCount+",移行できず数:"+this.faileCount);
	}

}
