package jp.pmw.idlink.tmp;


public class TMP_ROOM_MST extends BaseTmp{

	public String universityName,campusName,buildingName,roomName,seatCount;

	public TMP_ROOM_MST(){
	}

	public void setUNIVERSITY_NAME(String universityName){
		this.universityName = universityName;
	}

	public void setCAMPUS_NAME(String campusName){
		this.campusName = campusName;
	}

	public void setBUILDING_NAME(String buildingName){
		this.buildingName = buildingName;
	}

	public void setROOM_NAME(String roomName){
		this.roomName = roomName;
	}

	public void setSEAT_COUNT(String seatCount){
		this.seatCount = seatCount;
	}

	public String getUNIVERSITY_NAME(){
		return this.universityName;
	}

	public String getCAMPUS_NAME(){
		return this.campusName;
	}

	public String getBUILDING_NAME(){
		return this.buildingName;
	}

	public String getROOM_NAME(){
		return this.roomName;
	}

	public String getSEAT_COUNT(){
		return this.seatCount;
	}

	public String toString(){
		return "大学名:"+getUNIVERSITY_NAME()+","+"キャンパス名:"+getCAMPUS_NAME()+",建物名:"+getBUILDING_NAME()+",教室名:"+getROOM_NAME();
	}
}

