/**
 * A class representing a patient.
 * 
 * @author Emelie Coleman
 */
public class Patient {
	// declare instance variables
	private String name;
	private int arrival_time;
	private int urgency;
	
	// constructor
	public Patient(String name, int arrival_time, int urgency) {
		// initialize instance variables
		this.name = name;
		this.arrival_time = arrival_time;
		this.urgency = urgency;
	}
	
	// functions
	/**
	 * @return this patient's arrival time
	 */
	public int arrival_time() {
		// return this patient's arrival time
		return this.arrival_time;
		// -----
	}
	
	/**
	 * @return this patient's urgency
	 */
	public int urgency() {
		// return this patient's urgency
		return this.urgency;
		//-----
	}
	
	/**
	 * @param time - current simulation time
	 * @return wait time of this patient
	 */
	public int wait_time(int time){
		// return this patient's wait time
		return (time - this.arrival_time);
		// -----
	}

	public String toString() {
		return "Name: " + name + "\nArrival Time: " + arrival_time + "\nUrgency: " + urgency;
	}
}
