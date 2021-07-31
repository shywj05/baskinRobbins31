package package_VO;

public class NotifyVO {
	private int seq;
	private String title;
	private String contents;
	private String date;
	private int readView;
	
	public int getSeq() {
		return seq;
	}
	public String getTitle() {
		return title;
	}
	public String getContents() {
		return contents;
	}
	public String getDate() {
		return date;
	}
	public int getReadView() {
		return readView;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setReadView(int readView) {
		this.readView = readView;
	}

	@Override
	public String toString() {
		return title;
	}


	
	
}
