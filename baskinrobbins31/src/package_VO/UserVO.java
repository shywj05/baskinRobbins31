package package_VO;

public class UserVO {
    private String id;
    private String name;
    private String pw;
    private int point;
    private boolean isActivate = true;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPw() {
		return pw;
	}
	public int getPoint() {
		return point;
	}
	public boolean isActivate() {
		return isActivate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	@Override
	public String toString() {
		return "아이디 : " + id + ", 이름 : " + name;
	}
}
