/**
 * A Patient queue implementation using a dynamically-sized circular array.
 * 
 * @author Emelie Coleman
 */
public class MyPatientQueue{
	// instance variables
	private Patient[] queue;
	private int head;
	private int tail;

	// constructor
	public MyPatientQueue() {
		// initialize instance variables
		this.queue = new Patient[7];
		this.head = 0;
		this.tail = 0;
	}

	// functions
	/**
	 * @return the number of patients in the queue
	 */
	public int size() {
		// return the number of patients in the queue
		if (this.tail < this.head) {
			return this.tail + this.queue.length - this.head;
		}
		return this.tail - this.head;
	}

	/**
	 * add patient to end of queue.
	 * @param p - Patient to add to queue
	 */
	public void enqueue(Patient p) {
		// add patient to end of queue and adjust tail
		this.queue[this.tail] = p;
		this.tail = (this.tail + 1) % this.queue.length;
		// resize array, if needed
		// Check if array is full
		if (this.head == this.tail) {
			//copy elements into an array twice as big
			Patient[] temp = new Patient[this.queue.length * 2];
			for (int i = 0; i < this.queue.length; i++) {
				// shift head to beginning of new array
				temp[i] = this.queue[(this.head + i) % this.queue.length];
			}
			// set new head, and tail
			this.tail = this.queue.length;
			this.head = 0;
			this.queue = temp;
		}
	}

	/**
	 * remove and return next patient from the queue
	 * @return patient at front of queue, null if queue is empty
	 */
	public Patient dequeue() {

		if (this.size() == 0) {
			return null;
		}
		// remove and return the patient at the head of the queue
		Patient front = this.queue[this.head];
		this.queue[this.head] = null;
		this.head = (this.head + 1) % this.queue.length;
		// resize array, if needed
		// Check if queue is empty or under 1/4 capacity
		if (this.head == this.tail) {
			// if it is, reset the head and tail to beginning of array
			this.head = 0;
			this.tail = 0;
		} else if (this.size() <= this.queue.length / 4 && this.queue.length / 2 >= 7) {
			// reduce the size of the array
			Patient[] temp = new Patient[this.queue.length / 2];
			for (int i = 0; i < this.size(); i++) {
				// shift head to beginning of new array
				temp[i] = this.queue[(this.head + i) % this.queue.length];
			}
			// set new capacity, head, and tail
			this.queue = temp;
			this.tail = this.size();
			this.head = 0;
		}
		return front;
	}

	/**
	 * return, but do not remove, the patient at index i
	 * @param i - index of patient to return
	 * @return patient at index i, or null if no such element
	 */
	public Patient get(int i) {
		// return, but do not remove, the patient at index i
		if (i < 0 || this.size() == 0) {
			return null;
		}
		return this.queue[(this.head + i) % this.queue.length];
		// -----
	}

	/**
	 * add patient to front of queue
	 * @param p - patient being added to queue
	 */
	public void push(Patient p) {

		// shift the rest back one spot
		for (int i = this.size(); i > 0; i--) {
			this.queue[(this.head + i) % this.queue.length] = this.queue[(this.head + i - 1) % this.queue.length];
		}
		// add Patient p to front of queue and adjust tail
		this.queue[this.head] = p;
		this.tail = (this.tail + 1) % this.queue.length;
		//this.queue = temp1;
		// resize array, if needed
		if (this.head == this.tail) {
			//copy elements into an array twice as big
			Patient[] temp = new Patient[this.queue.length * 2];
			for (int i = 0; i < this.queue.length; i++) {
				// shift head to beginning of new array
				temp[i] = this.queue[(this.head + i) % this.queue.length];
			}
			// set new head, and tail
			this.tail = this.queue.length;
			this.head = 0;
			this.queue = temp;
		}
	}

	/**
	 * remove and return patient at index i from queue
	 * @param i - index of patient to remove
	 * @return patient at index i, null if no such element
	 */
	public Patient dequeue(int i) {

		// remove and return Patient at index i from queue
		if (i < 0 || this.size() == 0 || i > this.size() - 1) {
			return null;
		}
		Patient bye = this.queue[(this.head + i) % this.queue.length];
		this.queue[(this.head + i) % this.queue.length] = null;

		// shift patients down to fill hole left by removed patient
		// how many elements to be shifted over
		int move = this.size() - 1 - i;
		for (int j = 0; j <= move; j++) {
			// shift all elements to the right of the dequeued elements left 1.
			this.queue[(this.head + i + j) % this.queue.length] = this.queue[(this.head + i + j + 1) % this.queue.length];
		}
		this.tail = (this.tail - 1) % this.queue.length;
		// resize array, if needed
		// reset if empty
		if (this.head == this.tail) {
			// if it is, reset the head and tail to beginning of array
			this.head = 0;
			this.tail = 0;
			return bye;
		} else if (this.size() <= this.queue.length / 4 && this.queue.length / 2 >= 7) {
			// reduce the size of the array if less than 1/4 capacity but capacity isn't already 7 or less
			Patient[] temp = new Patient[this.queue.length / 2];
			for (int j = 0; j < this.size(); j++) {
				// shift head to beginning of new array
				temp[j] = this.queue[(this.head + j) % this.queue.length];
			}
			// set new head, and tail
			this.tail = this.size();
			this.queue = temp;
			this.head = 0;
		}
		return bye;
		// -----
	}
}
