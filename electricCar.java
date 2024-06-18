
public class electricCar extends Car{
	protected int rechargeTime;
	protected String batteryType;
	
	public electricCar (String m, String c, String p, int n,
			String model, int mr, double sr, boolean awd, double price,
			int rt, String bt) {
		super(m, c, p, n, model, mr, sr, awd, price);
		rechargeTime=rt;
		batteryType=bt;
	}
	
	public void setRechargeTime(int x) {
		rechargeTime=x;
	}
	
	public void setBatteryType(String x) {
		batteryType=x;
	}
	
	public int getRechargeTime() {
		return rechargeTime;
	}
	
	public String getBatteryType() {
		return batteryType;
	}
	
	public String display() {
		return super.display()+"\t"+batteryType+"\t\t"+rechargeTime;
	}
}
