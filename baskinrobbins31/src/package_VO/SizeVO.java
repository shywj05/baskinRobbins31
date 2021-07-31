package package_VO;

public class SizeVO {
	private int seq;
	private String name;
	private int gram;
	private int flavorKinds;
	private int price;
	private boolean isActivate = true;

	public int getSeq() {
		return seq;
	}

	public String getName() {
		return name;
	}

	public int getGram() {
		return gram;
	}

	public int getFlavorKinds() {
		return flavorKinds;
	}

	public int getPrice() {
		return price;
	}

	public boolean isActivate() {
		return isActivate;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGram(int gram) {
		this.gram = gram;
	}

	public void setFlavorKinds(int flavorKinds) {
		this.flavorKinds = flavorKinds;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}

	@Override
	public String toString() {
		return "사이즈명 : " + name + ", 용량 : " + gram
				+ "g, 선택 가능한 맛의 종류 : " + flavorKinds + ", 가격 : " + price;
	}
}
