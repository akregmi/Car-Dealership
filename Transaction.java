import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction {

	protected int ID;
	protected Calendar date;
	protected String employee;
	protected Car car;
	protected String type;
	protected double price;
		
	public Transaction(int I, Calendar d, String e, Car c, String t, double p) {
		ID=I;
		date=d;
		employee=e;
		car=c;
		type=t;
		price=p;
	}
	
	public String display() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, YYYY");
		
		return( "-----------------------------------"+
				"\nTransaction ID\t: "+ID+
				"\nDate\t\t: "+sdf.format(date.getTime())+
				"\nEmployee\t: "+employee+
				"\nTransaction\t: "+type+
				"\n-----------------------------------"
				);
	}
	
	public int getID() {
		return ID;
	}
	
	public Car getCar() {
		return car;
	}
	
	public String getType() {
		return type;
	}
	
	public String getPerson() {
		return employee;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Calendar getDate() {
		return date;
	}
}
