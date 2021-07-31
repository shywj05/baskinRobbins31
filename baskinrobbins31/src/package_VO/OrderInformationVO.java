package package_VO;

public class OrderInformationVO {
    private int seq;
    private String user_id;
    private int size_seq;
    private int spoonCount;
    private String howToPick;
    private boolean refund;
    private boolean isActivate = true;
	public int getSeq() {
		return seq;
	}
	public String getUser_id() {
		return user_id;
	}
	public int getSize_seq() {
		return size_seq;
	}
	public int getSpoonCount() {
		return spoonCount;
	}
	public String getHowToPick() {
		return howToPick;
	}
	public boolean isRefund() {
		return refund;
	}
	public boolean isActivate() {
		return isActivate;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setSize_seq(int size_seq) {
		this.size_seq = size_seq;
	}
	public void setSpoonCount(int spoonCount) {
		this.spoonCount = spoonCount;
	}
	public void setHowToPick(String howToPick) {
		this.howToPick = howToPick;
	}
	public void setRefund(boolean refund) {
		this.refund = refund;
	}
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	
	@Override
	public String toString() {
		return ", id : " + user_id
				+ ", size_seq=" + size_seq + ", spoonCount=" + spoonCount
				+ ", howToPick=" + howToPick + ", refund=" + refund
				+ ", isActivate=" + isActivate + "]";
	}
}
