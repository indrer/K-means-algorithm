package me.upi.kmeans.utils;

/*
 * Helper class for various math calculations
 */
public class Math {

	// Calculates mean, taking new entry into consideration
	public static double[] calculateMean(double[] mean, double[] newEntry) {
		double[] newMean = new double[mean.length];
		
		for (int i = 0; i < mean.length; i++) {
			newMean[i] = (mean[i] + newEntry[i]) / 2;
		}
		
		return newMean;
	}
	
	// Calculates Euclidean Distance between a mean of a cluster and a data entry
	public static double euclideanDistance(double[] clusterMean, double[] entry) {
		double distance = 0;
		
		for (int i = 0; i < clusterMean.length; i++) {
			distance = distance + (java.lang.Math.pow(entry[i] - clusterMean[i], 2));
		}
		
		return java.lang.Math.sqrt(distance);
	}
	
}
