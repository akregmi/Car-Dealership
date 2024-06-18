
public class Car extends Vehicle implements Comparable<Car>{
	
	protected String model;
	protected int maxRange;
	protected double safetyRating;
	protected boolean awd;
	protected double price;	
	
	public Car (String m, String c, String p, int n, 
			String model, int mr, double sr, boolean awd, double price) {
		super(m, c, p, n);
		this.model=model;
		maxRange=mr;
		safetyRating=sr;
		this.awd=awd;
		this.price=price;
	}
	
	public String display() {
		return super.display()+"\t"+model+"\t"+maxRange+"\t"
				+safetyRating+"\t $"+price;
	}
	
	public String getModel() {
		return model;
	}
	
	public boolean getawd() {
		return awd;
	}
	
	public double getprice() {
		return price;
	}
	
	public double getsafety() {
		return safetyRating;
	}
	
	public int getrange() {
		return maxRange;
	}
	
	public boolean equals(Object other) {
		Car temp=(Car)other;
		if (super.equals(other) && model.equals(temp.getModel()) && awd==temp.getawd())
			return true;
		else 
			return false;
	}
	
	public int compareTo(Car other) {
		if (price > other.price) return 1;
		else if (price < other.price) return -1;
		return 0;
	}
	
	
}
