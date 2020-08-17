package com.ml.onkmean;
import java.util.*;
import java.io.*;

class KMeans {

  public static void runAnIteration (String dataFile, String clusterFile) {
    
    // start out by opening up the two input files
    BufferedReader inData, inClusters;
    try {
      inData = new BufferedReader (new FileReader (dataFile));
      inClusters = new BufferedReader (new FileReader (clusterFile)); 
    } catch (Exception e) {
      throw new RuntimeException (e); 
    }
    
    // this is the list of current clusters
    ArrayList <VectorizedObject> oldClusters = new ArrayList <VectorizedObject> (0);
    
    // this is the list of clusters that come out of the current iteration
    ArrayList <VectorizedObject> newClusters = new ArrayList <VectorizedObject> (0);
    
    try {     
      
      // read in each cluster... each cluster is on one line of the clusters file
      String cur = inClusters.readLine (); 
      while (cur != null) {
        
        // first, read in the old cluster
        VectorizedObject cluster = new VectorizedObject (cur);
        oldClusters.add (cluster);
        
        // now create the new cluster with the same name (key) as the old one, with zero points
        // assigned, and with a vector at the origin
        VectorizedObject newCluster = new VectorizedObject (cluster.getKey (), "0", 
                                           new SparseDoubleVector (cluster.getLocation ().getLength ())); 
        newClusters.add (newCluster);
        
        // read in the next line
        cur = inClusters.readLine (); 
      }
      inClusters.close ();
      
    } catch (Exception e) {
      throw new RuntimeException (e); 
    }

    // now, process the data
    try {     
      
      // read in each data point... each point is on one line of the file
      String cur = inData.readLine (); 

      // while we have not hit the end of the file
      while (cur != null) {
        
	/*****************************************************************************/
        // Add your code here!
        //
        // It should go through each of the centroids, and find the one closest to the
        // data point stored in "cur". Then, it should add the point that is stored in
	// "cur" into the appropriate, corresponding entry in "newClusters"
	/*****************************************************************************/

        // read in the next line from the file
        cur = inData.readLine (); 
      }
      
      System.out.println ("Done with pass thru data.");
      inData.close ();
      
    } catch (Exception e) {
      e.printStackTrace ();
      throw new RuntimeException (e); 
    }

 
    PrintWriter out;
    try {
      out = new PrintWriter (new BufferedWriter (new FileWriter (clusterFile)));
    } catch (Exception e) {
      throw new RuntimeException (e); 
    }
    
    // loops through all of the clusters and writes them out
    for (VectorizedObject i : newClusters) {
      i.getLocation ().multiplyMyselfByHim (1.0 / i.getValueAsInt ());
      String stringRep = i.writeOut ();
      out.println (stringRep);
    }
    
    // and get outta here!
    out.close ();
  }
  
  public static void main (String [] args) {
    
    BufferedReader myConsole = new BufferedReader(new InputStreamReader(System.in));
    if (myConsole == null) {
      throw new RuntimeException ("No console.");
    }
    
    String dataFile = null;
    
    while (dataFile == null) {
      try {
        dataFile = myConsole.readLine ();
      } catch (Exception e) {
    
      }
    }
    
    String clusterFile = null;
    
    while (clusterFile == null) {
      try {
        clusterFile = myConsole.readLine ();
      } catch (Exception e) {
    
      }
    }
    
    Integer k = null;
    
    while (k == null) {
      try {
        String temp = myConsole.readLine ();
        k = Integer.parseInt (temp);
      } catch (Exception e) {
             }
    } 
    
    for (int i = 0; i < k; i++)
      runAnIteration (dataFile, clusterFile); 
  }
}

