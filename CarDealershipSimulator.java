import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Random;

public class CarDealershipSimulator {

	
	/**
	    A method used solely for the purpose of formatting strings so that it looks aesthetically pleasing 
	    Adds blank spaces at the end of the string so that it is lined up when outputting list of cars
	    @param s - The string being formatted
    	@param letters - How many letters you have the string to contain
	    @return the formatted string variable
    */
	public static String format(String s, int letters) {
		if (letters-s.length()>0) {
			for (int c=s.length(); c<=letters; c++) {
				s=s+" ";
			}
		}
		return s;
	}

	/**
	 * Reads in the textfile cars.txt and sorts through the information accordingly
	 * Uses the information given in the text file to create an Object of type Car
	 * Creates an arrayList of type Car, and adds each Object of type Car to the arrayList
	 * @param txtFile - the location of the textfile
	 * @return an arrayList of Cars based off the textfile
	 */
	public static ArrayList<Car> importCars(String txtFile) {
		File file=new File(txtFile);
		ArrayList<Car>cars=new ArrayList<>();
		
		try {
			Scanner scanner=new Scanner(file);
			String x;
			int counter;
			String mfr="", color="", model="", power="";
			int range=0, battery=0;
			double safety=0, price=0;
			boolean awd=false;
			
			//Sort through the text file line by line
			while (scanner.hasNextLine()) {
				x=scanner.nextLine();
				Scanner in=new Scanner(x);
				counter=0;
				//Break each line into individual words
				while (in.hasNext()) {
					String y=in.next();
					counter++;
					
					//Use a counter to keep track of infomration being represented at each line
					if (counter==1) mfr=format(y,10);
					else if (counter==2) color=format(y,6);
					else if (counter==3) model=format(y,10);
					else if (counter==4) power=y;
					else if (counter==5) safety=Double.parseDouble(y);
					else if (counter==6) range=Integer.parseInt(y);
					else if (counter==7) {
						if (y.equals("AWD"))
							awd=true;
						else 
							awd=false;
						}
					else if (counter==8) price=Integer.parseInt(y);
					else if (counter==9) battery=Integer.parseInt(y);
				}
				
				Car newCar;
				//If counter goes up to 9, that means it has a field for the battery
				//Initialize it to an electricCar to type Car if so
				if (counter==9)
					newCar=new electricCar(mfr, color, power, 4, model, range, safety, awd, price, battery, "Lithium");
				else 
					newCar=new Car(mfr, color, power, 4, model, range, safety, awd, price);
					
				cars.add(newCar);
				
			}
		}
		catch (IOException | NumberFormatException e) {
			System.out.println("Error reading text file.");
		}
		
		return cars;
	}
	
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		ArrayList<Car>cars=importCars("cars.txt");
		carDealership d = new carDealership();
		String input;
		Scanner in;

		String option="";	//Use option to store the user input
		do {
			try {
				input=scanner.nextLine();
				
				in=new Scanner(input);
				option=in.next();
				if (option.equals("L")) {
					d.displayInventory();
				}
				else if (option.equals("BUY")) {
					System.out.println(d.buyCar(in.nextInt()));
				}
				else if (option.equals("RET")) {
					d.returnCar(in.nextInt());
				}
				else if (option.equals("ADD")) {
					d.AddCars(cars);
				}
				else if (option.equals("SPR")) {
					d.sortByPrice();
				}
				else if (option.equals("SSR")) {
					d.sortBySafety();
				}
				else if (option.equals("SMR")) {
					d.sortByRange();
				}
				else if (option.equals("FPR")) {
					double min=Double.parseDouble(in.next());
					double max=Double.parseDouble(in.next());
					d.filterByPrice(min, max);
				}
				else if (option.equals("FEL")) {
					d.filterByElectric();
				}
				else if (option.equals("FAW")) {
					d.filterbyawd();
				}
				else if (option.equals("FCL")) {
					d.clearFilters();
				}
				else if (option.equals("SALES")) {
					if (!in.hasNext()){
						d.getLog().allTransactions();
					}
					else if (in.hasNextInt()) {
						int y=in.nextInt();
						d.getLog().byMonth(y);
					}
					else{
						String y=in.next();
						if (y.equals("TEAM")) {
							d.getTeam().displayTeam();
						}
						else if (y.equals("TOPSP")) {
							d.getTeam().getTopSP();
						}
						else if (y.equals("STATS")) {
							d.getLog().stats();
						}
						else {
							System.out.println("Input not recognized");
						}
					}
				}
				else if (option.equals("Q")) {
					//nothing
				}
				else {
					System.out.println("Input not recognized");
				}
			}
			catch (IllegalArgumentException | NullPointerException e) {
				System.out.println(e.getMessage());
			}
			catch (NoSuchElementException e) {
				System.out.println("Invalid input");
			}
		}while (!option.equals("Q"));
		scanner.close();
		
	}
}
