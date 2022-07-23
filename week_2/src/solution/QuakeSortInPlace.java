package solution;

import java.util.ArrayList;

public class QuakeSortInPlace {

  public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
    int minIdx = from;
    for (int i = from + 1; i < quakes.size(); i++) {
      if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
        minIdx = i;
      }
    }
    return minIdx;
  }

  public void sortByMagnitude(ArrayList<QuakeEntry> in) {
    for (int i = 0; i < in.size(); i++) {
      int minIdx = getSmallestMagnitude(in, i);
      QuakeEntry qi = in.get(i);
      QuakeEntry qmin = in.get(minIdx);
      in.set(i, qmin);
      in.set(minIdx, qi);
    }
  }

  public void dumpCSV(ArrayList<QuakeEntry> list) {
    System.out.println("Latitude,Longitude,Magnitude,Info");
    for (QuakeEntry qe : list) {
      System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(),
          qe.getLocation().getLongitude(), qe.getMagnitude(), qe.getInfo());
    }
  }

  public void createCSV() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    dumpCSV(list);
    System.out.println("# quakes read: " + list.size());
  }

  public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
    double depth;
    int idxMax = from;
    double maxDepth = quakeData.get(from).getDepth();
    for (int idx = from + 1; idx < quakeData.size(); idx++) {
      if ((depth = quakeData.get(idx).getDepth()) > maxDepth) {
        idxMax = idx;
        maxDepth = depth;
      }
    }
    return idxMax;
  }

  public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
    int idxMax;
    QuakeEntry quake;
    QuakeEntry maxQuake;
    for (int idx = 0; idx < in.size(); idx++) {
      idxMax = getLargestDepth(in, idx);
      quake = in.get(idx);
      maxQuake = in.get(idxMax);
      in.set(idx, maxQuake);
      in.set(idxMax, quake);
    }
  }

  public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
    QuakeEntry quakePrev;
    QuakeEntry quakeCurr;
    for (int i = 1; i < quakeData.size() - numSorted; i++) {
      quakePrev = quakeData.get(i - 1);
      quakeCurr = quakeData.get(i);
      if (quakePrev.getMagnitude() > quakeCurr.getMagnitude()) {
        quakeData.set(i, quakePrev);
        quakeData.set(i - 1, quakeCurr);
      }
    }
  }

  public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
    // for (QuakeEntry qe : in) {
    // System.out.println(qe);
    // }
    for (int i = 0; i < in.size() - 1; i++) {
      onePassBubbleSort(in, i);
      // System.out.println("Printing quakes after pass " + i);
      // for (QuakeEntry qe : in) {
      // System.out.println(qe);
      // }
    }
  }

  public void testSort() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/earthquakeDataSampleSix2.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");

    // sortByMagnitude(list);
    sortByMagnitudeWithBubbleSort(list);
    // sortByLargestDepth(list);

    System.out.println("Earthquakes in sorted order:");
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
  }

  public static void main(String[] args) {
    QuakeSortInPlace qs = new QuakeSortInPlace();
    qs.testSort();
  }

}
