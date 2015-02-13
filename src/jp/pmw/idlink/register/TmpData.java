package jp.pmw.idlink.register;

import java.util.List;
import jp.pmw.mysql.Connect;
import com.jenkov.db.PersistenceManager;
import com.jenkov.db.itf.IDaos;
import com.jenkov.db.itf.PersistenceException;

public class TmpData<T> {
	TmpData(){
		super();
	}

	//DBのTENP_ROOM_MSTテーブルのレコードを取得する
	//
	public List<T> getTmpMst(Class<T> clazz,String tmpMstTableName) throws PersistenceException{
		PersistenceManager manager = new PersistenceManager();
		IDaos daos = manager.createDaos(Connect.getInstance().getConnection());
		List<T> tempMst = daos.getObjectDao().readList(clazz, "SELECT * FROM `"+tmpMstTableName+"` WHERE `COMPLETE_FLAG` = ? ORDER BY `RECORED_INSERT_DATE_TIME` ASC", 0);
		return tempMst;
	}
}
