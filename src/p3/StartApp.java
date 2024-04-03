package p3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Start point for the app. Reads in data from csv file and then presents a menu
 * with several functions - searches and a thread based write to file.
 * 
 * @author Roberto Lo Duca 40386172
 *
 */
public class StartApp {
	
	/**
	 * The list where to collect the data
	 */
	private static List<Vehicle> vehicles = new LinkedList<>();
	
	/**
	 * The file to read
	 */
	private static final File FILE_READ = new File("Traffic.csv");

	/**
	 * Start point for app 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			readData();
			showMenu();
		} catch (Exception e) {
			System.out.println("Error in the program... Terminated." + e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("Program closed.");
		}
	}

	/**
	 * Shows the menu and Coordinates the searches and file write
	 * @throws Exception
	 */
	public static void showMenu() throws Exception {		
		try (Scanner scan = new Scanner(System.in)) {
			String option; // I use string to reduce errors
			do {
				System.out.println("\n1. Display all vehicle data");
				System.out.println("2. Display all HGVs (sorted by Surname)");
				System.out.println("3. Display vehicles and average speeds");
				System.out.println("4. Analyse and display driver age categorie");
				System.out.println("5. Pens - Generate individual penalty notices (new thread needed)");								
				System.out.println("6. Quit");
				System.out.println("Enter option ...\n");
				option = "0";
				try {
					option = scan.nextLine();
				} catch (Exception e) {
					scan.nextLine();
					System.out.println("Error with the option. retry...\n");
				}
				switch (option) {
					case "1":
						vehicleDisplay(vehicles);
						break;
					case "2":
						vehicleDisplay(displayHGVs());
						break;
					case "3":
						licenseAndSpeed();
						break;
					case "4":
						displayCategory();
						break;
					case "5":
						penaltyNotice();
						break;
					case "6":
						System.out.println("Quitting the program...");
						break;
					default:
						System.out.println("Wrong option. try again...");
						break;
				}				
			} while (!option.equals("6"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate individual penalty notices in a new thread
	 */
	private static void penaltyNotice() {
		if (!vehicles.isEmpty()) {
			System.out.println("Generating pen notices (in new Thread).");
			ThreadNotice threadNotice = new ThreadNotice(vehicles);
			Thread thread = new Thread(threadNotice);
			thread.start();
		} else {
			System.out.println("The container of data is empty");
		}
	}

	/**
	 * Group and display the age category of drivers
	 */
	private static void displayCategory() {
		if (!vehicles.isEmpty()) {
			int young = 0;
			int older = 0;
			int senior = 0;
			for (Vehicle element : vehicles) {
				if (element.getAge() >= 18 && element.getAge() <= 29) {
					young++;
				} else if (element.getAge() >= 30 && element.getAge() <= 50) {
					older++;
				} else if (element.getAge() > 50) {
					senior++;
				}
			}
			System.out.println(""
					+ "NEW\t:" + young + "\n"
					+ "OLDER\t:" + older + "\n"
					+ "SENIOR\t:" + senior);
		} else {
			System.out.println("The container of data is empty");
		}
	}

	/**
	 * Display the vehicle license and average speed
	 */
	public static void licenseAndSpeed() {
		if (!vehicles.isEmpty()) {
			long miles = 20;
			long speed;
			Duration duration;
			long diffInMinutes;
			for (Vehicle element : vehicles) {
				duration = Duration.between(element.getTimeIn(), element.getTimeOut()); 
				diffInMinutes = duration.toMinutes();
				speed = (long) ((double) miles / diffInMinutes * 60);
				System.out.printf("License : %s. Average Speed : %d mph\n", element.getLicensePlate(), speed);
			}
		} else {
			System.out.println("The container of data is empty");
		}
	}

	private static List<Vehicle> displayHGVs() {
		if (!vehicles.isEmpty()) {
			List<Vehicle> sorted = new LinkedList<>(vehicles);
			Collections.sort(sorted, new SortLastName());
			return sorted;
		} else {
			System.out.println("The container of data is empty");
			return null;
		}
	}

	/**
	 * Display all vehicle data
	 * @param list - the list to display
	 */
	private static void vehicleDisplay(List<Vehicle> list) {
		if (!list.isEmpty()) {
			System.out.println(""
					+ "All vehicle data\n"
					+ "All Vehicles");
			for (Vehicle element : list) {
				System.out.println(""
						+ "License\t\t:" + element.getLicensePlate() + "\n"
						+ "Type\t\t:" + element.getType() + "\n"
						+ "Time In\t\t:" + element.getTimeIn() + "\n"
						+ "Time Out\t:" + element.getTimeOut() + "\n"
						+ "First name\t:" + element.getFirstName() + "\n"
						+ "Last name\t:" + element.getLastName() + "\n"
						+ "Age\t\t:" + element.getAge() + "\n"
						+ "Town\t\t:" + element.getTown() + "\n"
						+ "email\t\t:" + element.getEmail() + "\n");
			}
		} else {
			System.out.println("The container of data is empty");
		}
	}

	/**
	 * Reads in the data from the csv and maps to the Player class
	 */
	public static void readData()  {
		System.out.println("Loading data...");
		int attempt = 0;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_READ))) {
			Vehicle vehicle;
			System.out.println("File opened");
			String line;
			String[] lineSplit;
			String[] nameSplit;
			bufferedReader.readLine();
			line = bufferedReader.readLine();
			while (line != null) {
				attempt++;
				lineSplit = line.split(",");
				try {
					for (int loop = 0; loop < lineSplit.length; loop++) {
						lineSplit[loop] = lineSplit[loop].trim();
						if (lineSplit[loop].isEmpty()) {
							throw new NullPointerException("The data is missing or corrupted. skip it and read the next one");
						}
					}
					nameSplit = lineSplit[4].split(" ");
					if (nameSplit.length < 2) {
						throw new NullPointerException("The data is missing or corrupted. skip it and read the next one");
					}
					vehicle  = new Vehicle(
							lineSplit[0],
							LocalTime.parse(lineSplit[1]),
							LocalTime.parse(lineSplit[2]),
							lineSplit[3],
							nameSplit[0].trim(),
							nameSplit[1].trim(),
							Integer.parseInt(lineSplit[5]),
							lineSplit[6],
							lineSplit[7]);
					vehicles.add(vehicle);
				} catch (Exception e) {
					System.out.println("Data corrupted: " + e.getMessage());
				}
				line = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("The number is not in the correct format");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("The data is missing or corrupted");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Impossible to read the file");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("General error" + e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println(""
					+ "Attempted to read vehicles data: " + attempt + "\n"
					+ "Vehicle data read successfully: " + vehicles.size() + "\n"
					+ "File successfully read.  Close file\n");
		}
	}
}