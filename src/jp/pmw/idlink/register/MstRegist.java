package jp.pmw.idlink.register;

import jp.pmw.mysql.Connect;

import com.jenkov.db.PersistenceManager;
import com.jenkov.db.itf.IDaos;

public class MstRegist {
	public IDaos daos;
	public int successCount = 0;
	public int faileCount = 0;

	MstRegist(){
		PersistenceManager manager = new PersistenceManager();
		this.daos = manager.createDaos(Connect.getInstance().getConnection());
	}
}
