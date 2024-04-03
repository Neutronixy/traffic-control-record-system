/**
 * 
 */
package p3;

import java.time.LocalTime;

/**
 * Class used to store vehicle records
 * @author Roberto Lo Duca 40386172
 *
 */
public class Vehicle {
	// LicensePlate,In,Out,Type,name,age,town,email
	// WPM 4567,05:02:00,06:39,HGV,Bridget Kelly,30,Castlereagh,bridget.kelly@example.com
	private String licensePlate;
	private LocalTime timeIn;
	private LocalTime timeOut;
	private String type;
	private String firstName;
	private String lastName;
	private int age;
	private String town;
	private String email;
	
	/**
	 * Default constructor
	 */
	public Vehicle() {
	}

	/**
	 * Constructor with arguments
	 * @param licensePlate
	 * @param timeIn
	 * @param timeOut
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param town
	 * @param email
	 */
	public Vehicle(String licensePlate, LocalTime timeIn, LocalTime timeOut, String type, String firstName,
			String lastName, int age, String town, String email) {
		this.licensePlate = licensePlate;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.town = town;
		this.email = email;
	}

	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * @param licensePlate the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * @return the timeIn
	 */
	public LocalTime getTimeIn() {
		return timeIn;
	}

	/**
	 * @param timeIn the timeIn to set
	 */
	public void setTimeIn(LocalTime timeIn) {
		this.timeIn = timeIn;
	}

	/**
	 * @return the timeOut
	 */
	public LocalTime getTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(LocalTime timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Vehicle [licensePlate=" + licensePlate + ", timeIn=" + timeIn + ", timeOut=" + timeOut + ", type="
				+ type + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", town=" + town
				+ ", email=" + email + "]";
	}
}
