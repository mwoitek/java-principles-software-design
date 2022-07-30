package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {

  private int order;
  private HashMap<String, ArrayList<String>> followsMap;

  public EfficientMarkovModel(int n) {
    order = n;
    followsMap = new HashMap<String, ArrayList<String>>();
    myRandom = new Random();
  }

  public void setRandom(int seed) {
    myRandom = new Random(seed);
  }

  private ArrayList<String> buildFollows(String key) {
    ArrayList<String> follows = new ArrayList<String>();
    int pos = 0;
    int idx;
    int startIdx;
    while (pos < myText.length() && (idx = myText.indexOf(key, pos)) != -1) {
      try {
        startIdx = idx + order;
        follows.add(myText.substring(startIdx, startIdx + 1));
        pos = idx + 1;
      } catch (Exception e) {
        break;
      }
    }
    return follows;
  }

  public void buildMap() {
    String key;
    for (int i = 0; i <= myText.length() - order; i++) {
      key = myText.substring(i, i + order);
      if (!followsMap.containsKey(key)) {
        followsMap.put(key, buildFollows(key));
      }
    }
  }

  public void printHashMapInfo() {
    int numKeys = followsMap.size();
    if (numKeys <= 30) {
      System.out.println("HashMap:");
      for (String key : followsMap.keySet()) {
        System.out.println(key + ": " + followsMap.get(key));
      }
      System.out.println();
    }

    System.out.println("Number of keys: " + numKeys);
    System.out.println();

    int size;
    int maxSize = -1;
    for (String key : followsMap.keySet()) {
      size = followsMap.get(key).size();
      if (size > maxSize) {
        maxSize = size;
      }
    }
    System.out.println("Size of the largest ArrayList: " + maxSize);
    System.out.println();

    System.out.println("Keys that have the maximum size value:");
    for (String key : followsMap.keySet()) {
      if (followsMap.get(key).size() == maxSize) {
        System.out.println(key);
      }
    }
  }

  public void setTraining(String s) {
    myText = s.trim();
    followsMap.clear();
    buildMap();
    // printHashMapInfo();
  }

  public ArrayList<String> getFollows(String key) {
    return followsMap.get(key);
  }

  public String getRandomText(int numChars) {
    if (myText == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int idx = myRandom.nextInt(myText.length() - order);
    String key = myText.substring(idx, idx + order);
    sb.append(key);
    ArrayList<String> follows;
    String next;
    for (int k = 0; k < numChars - order; k++) {
      follows = getFollows(key);
      if (follows.isEmpty()) {
        break;
      }
      idx = myRandom.nextInt(follows.size());
      next = follows.get(idx);
      sb.append(next);
      key = key.substring(1) + next;
    }
    return sb.toString();
  }

  public String toString() {
    return "MarkovModel of order " + order;
  }

}
