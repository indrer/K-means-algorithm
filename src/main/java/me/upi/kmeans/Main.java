package me.upi.kmeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 * ========================================
 * 			JUST A TEST CLASS
 * 			  PLEASE IGNORE
 * ========================================
 */
public class Main {
	
	public static void main(String[] args) {
		ArrayList<double[]> data = new ArrayList<double []>();
		Random ranGen = new Random();
		
		// Randomly generate ArrayList of double[] arrays
		for (int i = 0; i < 100; i++) {
			double[] temp = new double[2];
			for (int j = 0; j <2; j++) {
				temp[j] = ranGen.nextDouble() + ranGen.nextInt(100);
			}
			data.add(temp);
		}
		
		// Print out generated values
		System.out.println("Data :");
		for (double[] d : data) {
			System.out.println(Arrays.toString(d));
		}
		
		// Create K-means object, run the iteration 1000 times, split it into 5 clusters.
		ArrayList<Cluster> result = new ArrayList<Cluster>();
		try {
			Kmeans alg = new Kmeans(data, 5, 1000);
			result = alg.iterate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Print out clusters
		for (Cluster c : result) {
			System.out.println("Cluster: ");
			for (Record r : c.getCluster()) {
				System.out.println(Arrays.toString(r.getData()));
			}
			System.out.println("\n===============\n");
		}
		
		// Write result to result.data file, for later plotting
		File file = new File("result.data");
		try {
			int counter = 1;
			PrintWriter writer = new PrintWriter(file);
			for (Cluster c : result) {
				for (Record r : c.getCluster()) {
					for (int i = 0; i< r.getData().length; i++) {
						if (i == r.getData().length -1) {
							writer.print(r.getData()[i] + " - cluster " + counter);
						}else {
							writer.print(r.getData()[i] + ", ");
						}
					}
					writer.println();
				}
				counter++;
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
