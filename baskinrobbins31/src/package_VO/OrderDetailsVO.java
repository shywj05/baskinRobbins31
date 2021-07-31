package package_VO;

public class OrderDetailsVO {
	private int seq;
	private int order_seq;
	private int icecream_seq;

	public int getSeq() {
		return seq;
	}

	public int getOrder_seq() {
		return order_seq;
	}

	public int getIcecream_seq() {
		return icecream_seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setOrder_seq(int order_seq) {
		this.order_seq = order_seq;
	}

	public void setIcecream_seq(int icecream_seq) {
		this.icecream_seq = icecream_seq;
	}

	@Override
	public String toString() {
		return "OrderDetailsVO [seq=" + seq + ", order_seq=" + order_seq
				+ ", icecream_seq=" + icecream_seq + "]";
	}
	
	
}
