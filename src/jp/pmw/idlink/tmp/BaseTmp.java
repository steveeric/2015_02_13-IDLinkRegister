package jp.pmw.idlink.tmp;

public class BaseTmp {
	public String randomNo;
	public int cmpFlag = 0;

	public void setRandomNo(String randomNo){
		this.randomNo = randomNo;
	}
	public void setTMP_RANDOM_NO(String randomNo){
		this.randomNo = randomNo;
	}
	public void setCOMPLETE_FLAG(int cmp){
		this.cmpFlag = cmp;
	}

	public String getTMP_RANDOM_NO(){
		return this.randomNo;
	}
	public int getCOMPLETE_FLAG(){
		return this.cmpFlag;
	}

	public String init(String str){
		if(str.equals("")){
			str = null;
		}
		return str;
	}
}
