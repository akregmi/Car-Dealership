import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class carDealership {
	ArrayList<Car>cars;
	protected boolean awd, electric, price;
	protected double minPrice, maxPrice;
	protected SalesTeam team=new SalesTeam();
	protected AccountingSystem log=new AccountingSystem();
	
	//Refer to an empty arraylist of type Car in the constructor
	public carDealership() {
		cars=new ArrayList<>();
	}
	
	//Add cars to the end of the arraylist
	public void AddCars(ArrayList<Car>newcars) {
		cars.addAll(newcars);
	}
	
	/**
	 * Buy a car from the Car Dealership
	 * @param VIN of the selected car
	 * @return the 
	 * @return null if the car at the index does not exist
	 */
	public String buyCar(int VIN) {
		Car bought=null;
		String message="Invalid. Car does not exist";
		if (VIN<100 || VIN>499 || cars.size()==0) 
			throw new IllegalArgumentException(message);
		
		else {
			for (int c=0; c<cars.size(); c++) {
				if (cars.get(c).getVIN()==VIN) {
					bought=cars.get(c);
					cars.remove(c);
				}
			}
		}
		
		if (bought==null)
			throw new IllegalArgumentException(message);
		
		double price=bought.getprice();
		String person=team.getSalesPerson();
		Calendar date=GenerateDate();
		
		
		return log.Add(date, bought, person, "BUY", price);
	}
	
	 /**
	  * return a car to the dealership 
	  * place it at the end of the arraylist if it 
	  * @param the transcation ID
	  */
	public void returnCar(int ID) {
		Transaction t=log.getTransaction(ID);
		if (t.getType().equals("BUY")) {
			cars.add(t.getCar());
		}
		else {
			throw new IllegalArgumentException("Invalid ID");
		}
		//Generate and sets random date
		Calendar date=t.getDate();
		int randomDate=30-date.get(Calendar.DATE);
		randomDate=(int)(Math.random()*randomDate)+date.get(Calendar.DATE);
		date.set(Calendar.DATE, randomDate);
		
		System.out.println(log.Add(date, t.getCar(), t.getPerson(), "RET", t.getPrice()));
	}
	
	//Display the list of cars with the appropriate filters
	public void displayInventory() {
		ArrayList<Car>temp=new ArrayList<>(cars);
		
		System.out.println("VIN \tManufacturer \tColour \tModel      \tRange \tSafety \t Price \t\tBattery \tRecharge (mins)");
		for (int c=0; c<temp.size(); c++) {
			boolean remove=false;
			if (awd) {
				if (temp.get(c).getawd()==false)
					remove=true;
			}
			
			if (electric) {
				if (!temp.get(c).getPower().equals("ELECTRIC_MOTOR")) 
					remove=true;
			}
			
			if (price) {
				double cost=temp.get(c).getprice();
				if (cost>maxPrice || cost<minPrice)
					remove=true;
			}
			
			if (!remove) {
				System.out.println(temp.get(c).display());
			}
		}
	}
	
	public void filterByElectric() {
		electric=true;
	}
	
	public void filterbyawd() {
		awd=true;
	}
	
	public void filterByPrice(double min, double max) {
		if (max<=min) 
			throw new IllegalArgumentException("Invalid. Min price is greater than max.");
		price=true;
		minPrice=min;
		maxPrice=max;
	}
	
	public void clearFilters() {
		price=false;
		awd=false;
		electric=false;
	}
	
	public void sortByPrice() {
		Collections.sort(cars);
	}
	
	public void sortBySafety() {
		Collections.sort(cars, new Comparator<Car>() {
			public int compare(Car a, Car b) {
				if (a.getsafety() < b.getsafety()) return 1;
				if (a.getsafety() > b.getsafety()) return -1;
				return 0;
			
		}
		});
	}
	
	public void sortByRange() {
		Collections.sort(cars, new Comparator<Car>() {
			public int compare(Car a, Car b) {
				if (a.getrange() < b.getrange()) return 1;
				if (a.getrange() > b.getrange()) return -1;
				return 0;
			
		}
		});
	}
	
	public SalesTeam getTeam() {
		return team;
	}
	
	public AccountingSystem getLog() {
		return log;
	}
	
	//A static method that generates a random date
	public static Calendar GenerateDate() {
		int month=(int)(Math.random()*11)+1;
		int day=(int)(Math.random()*15)+1;
		Calendar date=new GregorianCalendar(2019, month, day);
		return date;
	}
}

