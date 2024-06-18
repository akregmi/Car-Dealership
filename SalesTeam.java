import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class SalesTeam {

	protected LinkedList<String>employees=new LinkedList<>();
	protected ArrayList<Integer>sales=new ArrayList<>();	//Keeps track of sales per employee
	
	public SalesTeam() {
		employees.add("Tyler");
		employees.add("Anthony");
		employees.add("Dylan");
		employees.add("Joe");
		employees.add("Andreas");
		
		//Initially set sales for each employee = 0
		for (int c=0; c<employees.size(); c++)
			sales.add(0);
	}
	
	public String getSalesPerson() {
		int index=(int)(Math.random()*(employees.size()-1));
		//Increase the number of sales by the randomly generated employee by 1
		int increasedSales=sales.get(index)+1;
		sales.set(index, increasedSales);
		return employees.get(index);
	}
	
	public void displayTeam() {
		Iterator iter=employees.listIterator();
		
		while (iter.hasNext())
			System.out.print(iter.next()+" ");
	}
	
	public void getTopSP() {
		int largest=0;
		for (int c=0; c<sales.size(); c++) {
			if (largest<sales.get(c)) {
				largest=sales.get(c);
			}
		}
		
		for (int c=0; c<sales.size(); c++) {
			if (largest==sales.get(c)) {
				System.out.print(employees.get(c)+" ");
			}
		}
		System.out.println();
	}
	
}
