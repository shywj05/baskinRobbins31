package package_VO;

public class IcecreamVO {
	private int seq;
	private String kinds;
	private int stock;
	private boolean isActivate = true;
	public int getSeq() {
		return seq;
	}
	public String getKinds() {
		return kinds;
	}
	public int getStock() {
		return stock;
	}
	public boolean isActivate() {
		return isActivate;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setKinds(String kinds) {
		this.kinds = kinds;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	@Override
	public String toString() {
		return "IcecreamVO [seq=" + seq + ", kinds=" + kinds + ", stock="
				+ stock + ", isActivate=" + isActivate + "]";
	}

}
