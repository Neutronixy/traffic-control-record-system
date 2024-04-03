/**
 * 
 */
package p3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the Vehicle class
 * @author Roberto Lo Duca 40386172
 *
 */
class VehicleTest {
	String licensePlate;
	LocalTime timeIn;
	LocalTime timeOut;
	String type;
	String firstName;
	String lastName;
	int age;
	String town;
	String email;
	
	Vehicle vehicle;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		vehicle = new Vehicle();
		
		licensePlate = "ValidLicense";
		timeIn = LocalTime.parse("05:02:00");
		timeOut = LocalTime.parse("06:39");
		type = "ValidType";
		firstName = "ValidFirstName";
		lastName = "ValidLastName";
		age = 28;
		town = "ValidTown";
		email = "ValidEmail";
	}

	/**
	 * Test method for {@link p3.Vehicle#Vehicle()}.
	 */
	@Test
	void testDefaultConstructor() {
		vehicle = new Vehicle();
		assertNotNull(vehicle);
	}

	/**
	 * Test method for {@link p3.Vehicle#Vehicle(java.lang.String, java.time.LocalTime, java.time.LocalTime, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testConstructorWithArguments() {
		vehicle = new Vehicle(licensePlate, timeIn, timeOut, type, firstName, lastName, age, town, email);
		assertEquals(licensePlate, vehicle.getLicensePlate());
		assertEquals(timeIn, vehicle.getTimeIn());
		assertEquals(timeOut, vehicle.getTimeOut());
		assertEquals(type, vehicle.getType());
		
		assertEquals(firstName, vehicle.getFirstName());
		assertEquals(lastName, vehicle.getLastName());
		assertEquals(age, vehicle.getAge());
		assertEquals(town, vehicle.getTown());
		assertEquals(email, vehicle.getEmail());
	}

	/**
	 * Test method for {@link p3.Vehicle#setLicensePlate(java.lang.String)}.
	 */
	@Test
	void testSetGetLicensePlate() {
		vehicle.setLicensePlate(licensePlate);
		assertEquals(licensePlate, vehicle.getLicensePlate());
	}

	/**
	 * Test method for {@link p3.Vehicle#setTimeIn(java.time.LocalTime)}.
	 */
	@Test
	void testSetGetTimeIn() {
		vehicle.setTimeIn(timeIn);
		assertEquals(timeIn, vehicle.getTimeIn());
	}

	/**
	 * Test method for {@link p3.Vehicle#setTimeOut(java.time.LocalTime)}.
	 */
	@Test
	void testSetGetTimeOut() {
		vehicle.setTimeOut(timeOut);
		assertEquals(timeOut, vehicle.getTimeOut());
	}

	/**
	 * Test method for {@link p3.Vehicle#setType(java.lang.String)}.
	 */
	@Test
	void testSetGetType() {
		vehicle.setType(type);
		assertEquals(type, vehicle.getType());
	}

	/**
	 * Test method for {@link p3.Vehicle#setName(java.lang.String)}.
	 */
	@Test
	void testSetGetFirstName() {
		vehicle.setFirstName(firstName);
		assertEquals(firstName, vehicle.getFirstName());
	}
	
	/**
	 * Test method for {@link p3.Vehicle#setName(java.lang.String)}.
	 */
	@Test
	void testSetGetLastName() {
		vehicle.setLastName(lastName);
		assertEquals(lastName, vehicle.getLastName());
	}

	/**
	 * Test method for {@link p3.Vehicle#setAge(int)}.
	 */
	@Test
	void testSetGetAge() {
		vehicle.setAge(age);
		assertEquals(age, vehicle.getAge());
	}

	/**
	 * Test method for {@link p3.Vehicle#setTown(java.lang.String)}.
	 */
	@Test
	void testSetGetTown() {
		vehicle.setTown(town);
		assertEquals(town, vehicle.getTown());
	}

	/**
	 * Test method for {@link p3.Vehicle#setEmail(java.lang.String)}.
	 */
	@Test
	void testSetGetEmail() {
		vehicle.setEmail(email);
		assertEquals(email, vehicle.getEmail());
	}
}
