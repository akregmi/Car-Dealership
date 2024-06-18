import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class AccountingSystem {

	ArrayList<Transaction>transaction=new ArrayList<>();
	
	public String Add(Calendar d, Car c, String person, String t, double price) {
		int ID=(int)(Math.random()*98)+1;
		Transaction x=new Transaction(ID, d, person, c, t, price);
		transaction.add(x);
		return x.display();
	}
	
	//Return a transaction based on the Transaction ID
	public Transaction getTransaction(int id) {
		for (int i=0; i<transaction.size(); i++) {
			if (id==transaction.get(i).getID())
				return transaction.get(i);
		}
		throw new IllegalArgumentException("Transaction ID not found");
	}
	
	//Display all transactions
	public void allTransactions() {
		if (transaction==null)
			throw new NullPointerException("No transcations");
		for (int c=0; c<transaction.size(); c++)
			System.out.println(transaction.get(c).display());
	}
	
	//Find and display the sales in a given month
	public void byMonth(int month) {
		Calendar date;
		if (transaction==null)
			throw new NullPointerException("No transcations");
		for (int c=0; c<transaction.size(); c++) {
			date=transaction.get(c).getDate();
			if (date.get(Calendar.MONTH)==month) {
				System.out.println(transaction.get(c).display());
			}
		}
	}
	
	public void stats() {
		double totalSales=0;
		int carsSold=transaction.size();
		int carsReturned=0;
		
		//Update totalSales and carsReturned values
		for (int i=0; i<transaction.size(); i++) {
			if (transaction.get(i).getType().equals("RET")) {
				carsReturned++;
				totalSales-=transaction.get(i).getPrice();
			}
			else 
				totalSales+=transaction.get(i).getPrice();
		}
		//Finds carsSold by initializing it to total size of all the sales
		//Then subtracts the returnedCars to give the number of carsSold
		carsSold=carsSold-carsReturned;
		
		//Find the number of sales per month and store them accordingly in an arraylist
		//ie. m.get(index) is number of sales, where index is the month number (index=0 is Jan , 1 is Feb)
		ArrayList<Integer>m=new ArrayList<>();
		Calendar date;
		for (int c=0; c<12; c++) {
			int num=0;
			for (int i=0; i<transaction.size(); i++) {
				date=transaction.get(i).getDate();
				if (date.get(Calendar.MONTH)==c) {
					num++;
				}
			}
			m.add(num);
		}
		
		//Find the highest amount of sales in any given month
		SimpleDateFormat d = new SimpleDateFormat("MMMM");
		int highest=0;
		for (int c=0; c<12; c++) {
			if (m.get(c)>highest) 
				highest=m.get(c);
		}
		
		//Find month(s) that have the highest numbers of sales and store them
		String months="";
		for (int c=0; c<12; c++) {
			if (m.get(c)==highest) {
				date=new GregorianCalendar();
				date.set(Calendar.MONTH, c);
				months+=d.format(date.getTime())+" ";
			}
		}
		
		System.out.print("-----------------------------------\nStats\n"+
				"Total Sales \t: $"+totalSales+
				"\nSales per Month\t: $"+(totalSales/12)+
				"\nBest Month\t: "+months+
				"\nTotal Returned\t: "+carsReturned+
				"\nTotal Sold\t: "+carsSold+
				"\n-----------------------------------"
				);
		
	}
	
	
}
