package me.upi.kmeans;

import java.util.ArrayList;

public class Cluster {
	
	private double[] mean;
	private ArrayList<Record> data = new ArrayList<Record>();
	
	public Cluster(Record firstEntry) {
		this.mean = new double[firstEntry.getData().length];
		this.mean = firstEntry.getData();
		this.data.add(firstEntry);
	}
	
	public double[] getMean() {
		return this.mean;
	}
	
	public ArrayList<Record> getCluster() {
		return data;
	}
	
	public void addEntry (Record entry) {
		data.add(entry);
		updateMean(entry);
	}
	
	public void clearEntries() {
		data.clear();
	}
	
	public int getSize() {
		return data.size();
	}
	
	private void updateMean (Record newEntry) {
		me.upi.kmeans.utils.Math.calculateMean(mean, newEntry.getData());
	}
}
