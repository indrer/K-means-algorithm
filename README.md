# K-means algorithm

A simple algorithm used to group data into clusters to help analyze it better. `Main.java` is just a test class, so ignore it. It does show how this algorithm could be used, though.

### Quick tutorial
```
    ArrayList<Cluster> result = new ArrayList<Cluster>();
    Kmeans kmeans = new Kmeans(data, 5, 1000);
	result = kmeans.iterate();
```

The constructor takes the following parameters: 
* `ArrayList<double[]>`-  data to be clustered, 
* `int` - number of clusters to be created, 
* `int` - number of iterations for the algorithm to run (more iterations produce more accurate result).

`iterate()` runs the algorithm and returns `ArrayList<Cluster>` object. `Cluster` is a class specific for this implementation of the algorithm. `Cluster` contains grouped `Record` objects, which are simply `double[]` arrays.


### Example

[100 randomly generated arrays](https://github.com/u-pi/K-means-algorithm/blob/master/data.txt) of `double` values, each array containing 2 values were used for this example. 5 clusters and 1000 iterations were chosen.

[The results](https://github.com/u-pi/K-means-algorithm/blob/master/result.data) were printed out as [clusters](https://github.com/u-pi/K-means-algorithm/blob/master/clusters.txt). Here is a plot of the clusters:

![Results](https://github.com/u-pi/K-means-algorithm/blob/master/Figure_1.png)

### Usage

Either fork/download this repo and use the files, or if you want a `.jar`, you can use `maven` to compile this project (obviously, have it installed before!).

```
    git clone https://github.com/u-pi/K-means-algorithm.git
    mvn clean package
```

The `.jar` file should be in `target/` folder.