package me.upi.kmeans;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Simple array class to store data for clustering
 */
public class Record {
	private double[] data = new double[0];
	
	public Record() {
	}
	
	/*
	 * The size of an entry is not know, therefore array has to be extended
	 * as the data is added.
	 */
	public void populate(double data) {
		this.data = Arrays.copyOf(this.data, this.data.length + 1); //create new array from old array and allocate one more element
		this.data[this.data.length-1] = data;
	}
	
	public double[] getData() {
		return data;
	}
}

/*
 * Sorts data by first element of the data entry. No need different sort
 * for now. Might implement more in the future.
 */
class SortByX implements Comparator<Record> {
	public int compare(Record o1, Record o2) {
		return (int) (o1.getData()[0] - o2.getData()[0]);
	}
}


