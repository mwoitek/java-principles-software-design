package solution;

import java.util.ArrayList;

public class EarthQuakeClient2 {

  public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    for (QuakeEntry qe : quakeData) {
      if (f.satisfies(qe)) {
        answer.add(qe);
      }
    }
    return answer;
  }

  public void quakesWithFilter() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    System.out.println();

    // System.out.println("MinMagFilter");
    Filter f = new MinMagFilter(4.0);
    ArrayList<QuakeEntry> filteredList = filter(list, f);
    // for (QuakeEntry qe : filteredList) {
    // System.out.println(qe);
    // }
    // System.out.println();

    System.out.println("MagnitudeFilter + DepthFilter");
    f = new MagnitudeFilter(3.5, 4.5);
    filteredList = filter(list, f);
    f = new DepthFilter(-55000.0, -20000.0);
    filteredList = filter(filteredList, f);
    // for (QuakeEntry qe : filteredList) {
    // System.out.println(qe);
    // }
    System.out
        .println("Found " + filteredList.size() + " quakes that match that criteria");
    System.out.println();

    System.out.println("DistanceFilter + PhraseFilter");
    f = new DistanceFilter(new Location(39.7392, -104.9903), 1000000);
    filteredList = filter(list, f);
    f = new PhraseFilter("end", "a");
    filteredList = filter(filteredList, f);
    // for (QuakeEntry qe : filteredList) {
    // System.out.println(qe);
    // }
    System.out
        .println("Found " + filteredList.size() + " quakes that match that criteria");
  }

  public void testMatchAllFilter() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");

    MatchAllFilter maf = new MatchAllFilter();
    maf.addFilter(new MagnitudeFilter(1.0, 4.0, "Magnitude"));
    maf.addFilter(new DepthFilter(-180000.0, -30000.0, "Depth"));
    maf.addFilter(new PhraseFilter("any", "o", "Phrase"));

    ArrayList<QuakeEntry> filteredList = filter(list, maf);
    // for (QuakeEntry qe : filteredList) {
    // System.out.println(qe);
    // }
    System.out
        .println("Found " + filteredList.size() + " quakes that match that criteria");
    System.out.println("Filters used are: " + maf.getName());
  }

  public void testMatchAllFilter2() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedata.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");

    MatchAllFilter maf = new MatchAllFilter();
    maf.addFilter(new MagnitudeFilter(0.0, 5.0));
    maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000));
    maf.addFilter(new PhraseFilter("any", "e"));

    ArrayList<QuakeEntry> filteredList = filter(list, maf);
    // for (QuakeEntry qe : filteredList) {
    // System.out.println(qe);
    // }
    System.out
        .println("Found " + filteredList.size() + " quakes that match that criteria");
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
    EarthQuakeClient2 eqc = new EarthQuakeClient2();

    System.out.println("quakesWithFilter");
    eqc.quakesWithFilter();
    System.out.println();

    System.out.println("testMatchAllFilter");
    eqc.testMatchAllFilter();
    System.out.println();

    System.out.println("testMatchAllFilter2");
    eqc.testMatchAllFilter2();
  }

}
