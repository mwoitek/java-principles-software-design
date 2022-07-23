package solution;

import java.util.ArrayList;
import java.util.Collections;

public class DifferentSorters {

  public void sortWithCompareTo() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    Collections.sort(list);
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
    // int quakeNumber = 10;
    // System.out.println("Print quake entry in position " + quakeNumber);
    // System.out.println(list.get(quakeNumber));
  }

  public void sortByMagnitude() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    Collections.sort(list, new MagnitudeComparator());
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
  }

  public void sortByDistance() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    // Location is Durham, NC
    Location where = new Location(35.9886, -78.9072);
    Collections.sort(list, new DistanceComparator(where));
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
  }

  public void sortByTitleAndDepth() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    Collections.sort(list, new TitleAndDepthComparator());
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
    // int quakeNumber = 10;
    // System.out.println("Print quake entry in position " + quakeNumber);
    // System.out.println(list.get(quakeNumber));
  }

  public static void main(String[] args) {
    DifferentSorters ds = new DifferentSorters();

    // ds.sortWithCompareTo();
    // System.out.println();

    ds.sortByTitleAndDepth();
  }

}
