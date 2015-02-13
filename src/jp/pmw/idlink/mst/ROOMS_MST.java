package jp.pmw.idlink.mst;

import jp.pmw.idlink.tmp.TMP_ROOM_MST;

public class ROOMS_MST {
	private String universityNumber;
	private String roomId;
	private TMP_ROOM_MST tmp;
	public ROOMS_MST(String universityNumber,String roomId,TMP_ROOM_MST tmp){
		this.universityNumber = universityNumber;
		this.roomId = roomId;
		this.tmp = tmp;
	}

	public String getROOM_ID(){
		return roomId;
	}
	public String getUNIVERSITY_NUMBER(){
		return this.universityNumber;
	}
	public String getCAMPUS_NAME(){
		return this.tmp.getCAMPUS_NAME();
	}
	public String getBUILDING_NAME(){
		return this.tmp.getBUILDING_NAME();
	}
	public String getROOM_NAME(){
		return this.tmp.getROOM_NAME();
	}
	public String getMAX_SET_CAPACITY(){
		return this.tmp.getSEAT_COUNT();
	}
}
