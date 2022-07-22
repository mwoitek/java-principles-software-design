package solution;

import java.util.ArrayList;

public class LargestQuakes {

  public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
    int idxLargest = 0;
    double maxMagnitude = quakeData.get(0).getMagnitude();
    double magnitude;
    for (int idx = 1; idx < quakeData.size(); idx++) {
      if ((magnitude = quakeData.get(idx).getMagnitude()) > maxMagnitude) {
        idxLargest = idx;
        maxMagnitude = magnitude;
      }
    }
    return idxLargest;
  }

  public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    int idx;
    int numRepetitions = howMany <= quakeData.size() ? howMany : quakeData.size();
    ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>(quakeData);
    for (int i = 0; i < numRepetitions; i++) {
      idx = indexOfLargest(quakeDataCopy);
      answer.add(quakeDataCopy.get(idx));
      quakeDataCopy.remove(idx);
    }
    return answer;
  }

  public void findLargestQuakes() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    ArrayList<QuakeEntry> largest = getLargest(list, 50);
    for (QuakeEntry qe : largest) {
      System.out.println(qe);
    }
  }

  public static void main(String[] args) {
    LargestQuakes lq = new LargestQuakes();
    lq.findLargestQuakes();
  }

}
