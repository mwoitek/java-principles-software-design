package solution;

import java.util.ArrayList;

public class EarthQuakeClient {

  public EarthQuakeClient() {}

  public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    for (QuakeEntry qe : quakeData) {
      if (qe.getMagnitude() > magMin) {
        answer.add(qe);
      }
    }
    return answer;
  }

  public void bigQuakes(double magMin) {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    list = filterByMagnitude(list, magMin);
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
    System.out.println("Found " + list.size() + " quakes that match that criteria");
  }

  public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax,
      Location from) {
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    for (QuakeEntry qe : quakeData) {
      if (qe.getLocation().distanceTo(from) < distMax) {
        answer.add(qe);
      }
    }
    return answer;
  }

  public void closeToMe() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");

    // This location is Durham, NC
    // Location city = new Location(35.988, -78.907);
    // This location is Bridgeport, CA
    Location city = new Location(38.17, -118.82);

    double distKm;
    list = filterByDistanceFrom(list, 1000000.0, city);
    for (QuakeEntry qe : list) {
      distKm = qe.getLocation().distanceTo(city) / 1000.0;
      System.out.println(distKm + " " + qe.getInfo());
    }
    System.out.println("Found " + list.size() + " quakes that match that criteria");
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
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
  }

  public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth,
      double maxDepth) {
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    double depth;
    for (QuakeEntry qe : quakeData) {
      depth = qe.getDepth();
      if (depth > minDepth && depth < maxDepth) {
        answer.add(qe);
      }
    }
    return answer;
  }

  public void quakesOfDepth() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    double minDepth = -10000.0;
    double maxDepth = -5000.0;
    System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
    list = filterByDepth(list, minDepth, maxDepth);
    for (QuakeEntry qe : list) {
      System.out.println(qe);
    }
    System.out.println("Found " + list.size() + " quakes that match that criteria");
  }

  //

  public static void main(String[] args) {
    EarthQuakeClient eqc = new EarthQuakeClient();

    eqc.bigQuakes(5.0);
    System.out.println();

    eqc.closeToMe();
    System.out.println();

    eqc.quakesOfDepth();

    //
  }

}
