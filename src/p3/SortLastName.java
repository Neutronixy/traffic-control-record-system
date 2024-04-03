/**
 * 
 */
package p3;

import java.util.Comparator;

/**
 * Sort by last name
 * @author Roberto Lo Duca 40386172
 *
 */
public class SortLastName implements Comparator<Vehicle> {

	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getLastName().compareTo(o2.getLastName());
	}
}
