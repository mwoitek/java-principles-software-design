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

  public void testSort() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    sortByMagnitude(list);
    for (QuakeEntry qe : list) {
      System.out.println(qe);
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

  public static void main(String[] args) {
    QuakeSortInPlace qs = new QuakeSortInPlace();
    qs.testSort();
  }

}
