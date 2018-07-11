package me.upi.kmeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Kmeans {
	// Number of clusters to be made 
	private int k = 2;
	// Number of iterations (the more, the better grouping of data)
	private int iterations = 100;
	// Data that has been received from a file
	private ArrayList<Record> originalData = new ArrayList<Record>();
	// Data that will have it's array modified, same as @originalData at the beginning
	private ArrayList<Record> data = new ArrayList<Record>();
	// Clusters
	private ArrayList<Cluster> clusters = new ArrayList<Cluster>();
	
	/*===========================================
	* 				Constructors
	*============================================*/
	public Kmeans(ArrayList<double[]> data, int k, int iterations) throws Exception {
		if (k <= 1) {
			throw new Exception("K value has to be bigger than 1!");
		}
		if (iterations < 2) {
			throw new Exception("Iteration number has to be at least 2 (suggested - 50)");
		}
		this.originalData = new ArrayList<Record>(dataToRecords(data));
		this.data = new ArrayList<Record>(originalData);
		this.iterations = iterations;
		this.k = k;
		
		// Initializing first clusters
		getFirstMeans();
	}
	
	public Kmeans(ArrayList<double[]> data) throws Exception {
		this.originalData = new ArrayList<Record>(dataToRecords(data));
		this.data = new ArrayList<Record>(originalData);
		
		// Initializing first clusters
		getFirstMeans();
	}
	
	/*===========================================
	* 				Methods
	*============================================*/
	
	/*
	 * The main method of the algorithm. Groups data into cluster using Euclidean
	 * distance
	 */
	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<Cluster> iterate() {
		ArrayList<Double> distances = new ArrayList<Double>();
		int dataSize = data.size();
		
		// The ED will be calculated as many times @iterations variable
		// is set to.
		for (int i = 0; i < iterations; i ++) {
			// All clusters must be cleared up before another iteration
			// so that the final result does not have repetitive data
			for (Cluster c : clusters) {
				c.clearEntries();
			}
			
			// Iterating through all the members of the provided data.
			for (int j = 0; j < dataSize; j++) {
				// Taking first value
				double[] value = data.get(0).getData();
				// Calculating ED for taken value and clusters' means
				for (int u = 0; u < k; u++) {
					distances.add(me.upi.kmeans.utils.Math.euclideanDistance(clusters.get(u).getMean(), value));
				}
				// Getting smallest distance cluster and putting the value 
				// into the cluster
				int minIndex = distances.indexOf(Collections.min(distances));
				clusters.get(minIndex).addEntry(data.get(0));				
				// Removing record from the data array
				data.remove(0);
				// Resetting distance
				distances = new ArrayList<Double>();
			}
			data = new ArrayList<Record>(originalData);
			//this.data = originalData;
			dataSize = data.size();
		}
		//TODO RUN System.gc()
		//System.out.println(clusters.size());
		for (Cluster c: clusters) {
			sortData(c.getCluster());
		}
		
		return clusters;
	}
	
	/*
	 * Creates array of results from ArrayList of double[] arrays
	 */
	private ArrayList<Record> dataToRecords (ArrayList<double[]> array) {
		ArrayList<Record> result = new ArrayList<Record>();
		// First iteration - over the ArrayList containing double[] arrays
		for (int i = 0; i < array.size(); i++) {
			Record rec = new Record();
			// Second iteration - over a single double[] array's items to populate
			// Record instance.
			for (int j = 0; j < array.get(i).length; j++) {
				rec.populate(array.get(i)[j]);
			}
			result.add(rec);
		}
		return result;
	}
	
	/*
	 *  When the algorithm runs first time, random values are set as cluster means.
	 */
	private void getFirstMeans() throws Exception {
		// K value chosen was too large, cannot create that many clusters
		// with the amount of data given
		if (k >= data.size()) {
			throw new Exception("K value has to be smaller than the size of a data set!");
		}
		ArrayList<Cluster> result = new ArrayList<Cluster>();
		//Randomly takes members of the array for new clusters
		Random ranGen = new Random();
		System.out.println("K: " + k);
		for (int i = 0; i < k; i++) {
			int randomPos = ranGen.nextInt(data.size());
			// Adding data entry to new cluster, which is automatically set 
			// as its mean. Then removing that data from an array, so it is not calcualted
			// afterwards
			result.add(new Cluster(data.get(randomPos)));
			data.remove(randomPos);
		}
		
		clusters = new ArrayList<Cluster>(result);
	}
	
	/*
	 *  Sorts array for whatever reason. Sorts by first data value
	 */
	private void sortData(ArrayList<Record> array) {
		Collections.sort(array, new SortByX());
	}
}
