

public class Vehicle {
	
	protected String mfr;
	protected String color;
	protected String power;
	protected int numwheels;
	protected int VIN;
	
	public Vehicle(String m, String c, String p, int n) {
		mfr=m;
		color=c;
		power=p;
		numwheels=n;
		VIN=(int) (Math.random()*399)+100;
	}
	

	public String getPower(){
		return power;
	}
	
	public int getNumWheels(){
		return numwheels; 
	}
	
	public String getMfr(){
		return mfr;
	}
	
	public String getColor(){
		return color; 
	}
	
	public int getVIN() {
		return VIN;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public void setMfr(String mfr){
		this.mfr = mfr;
	}
	
	public void setPower(String power){
		this.power = power;
	}
	
	public void setNumWheels(int numWheels){
		this.numwheels = numWheels;
	}
	
	public boolean equals(Object other) {
		Vehicle temp=(Vehicle)other;
		if (mfr.equals(temp.getMfr()) && color.equals(temp.getColor()) && numwheels==temp.getNumWheels())
			return true;
		else
			return false;
	}
	
	public String display() {
		return VIN+"\t"+mfr+"\t"+color;
	}

}
