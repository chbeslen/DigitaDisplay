class menuItem{
	private String screen;
	private String type;	
	private String name;
	private String discription;
	private String price;
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	menuItem(String s,String t, String n, String d,String p){
		screen = s;
		type = t;
		name = n;
		discription = d;
		price = p;		
	}
	public String toString() { return screen + "\t" + type + "\t" + name + "\t" + discription + "\t" + price + "\n";  }
public String toFile() { return screen + "," + type + "," + name + "," + discription + "," + price + "\n";  }
	
}