/**
 * 
 */
package p3;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

/**
 * Thread for the notice
 * @author Roberto Lo Duca 40386172
 *
 */
public class ThreadNotice implements Runnable {
	private List<Vehicle> vehicles;

	/**
	 * Constructor with arguments
	 * @param vehicles
	 */
	public ThreadNotice(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	/**
	 * Start thread
	 */
	@Override
	public void run() {
		StringBuilder builder; // initialization of the variable inside the loop to avoid appending again on the previous text
		long miles = 20;
		long speed;
		Duration duration;
		long diffInMinutes;
		for (Vehicle element : vehicles) {
			builder = new StringBuilder(); // corrected initialization
			duration = Duration.between(element.getTimeIn(), element.getTimeOut()); 
			diffInMinutes = duration.toMinutes();
			speed = (long) ((double) miles / diffInMinutes * 60);
			if ((speed > 60 && element.getType().equalsIgnoreCase("HGV")) || (speed > 50 && element.getType().equalsIgnoreCase("car"))) { // added the condition to add a different penalty to HGV and car
				try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(element.getLastName() + "_pen.txt", false))) {
					builder.append(""
							+ element.getLicensePlate() + "\n"
							+ element.getType().toUpperCase() + "\n" // modified and added spacing in all the lines
							+ "\n"
							+ "Dear " + element.getFirstName().toUpperCase() + " " + element.getLastName().toUpperCase() + "\n"
							+ "You have been recorded travelling at an average speed of " + speed + "\n"
							+ "This has resulted in a fixed penalty point notice. " + "\n"
							+ "\n"
							+ "Depart of Road Traffic Offences"
							);
					bufferedWriter.write(builder.toString());
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Impossible to write the file");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("General error" + e.getMessage());
					e.printStackTrace();
				} finally {
					System.out.println("File for " + element.getLastName() + " written successfully.");
				}
			}
		}
	}
}
