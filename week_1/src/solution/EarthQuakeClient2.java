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
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    System.out.println();

    System.out.println("MinMagFilter");
    Filter f = new MinMagFilter(4.0);
    ArrayList<QuakeEntry> filteredList = filter(list, f);
    for (QuakeEntry qe : filteredList) {
      System.out.println(qe);
    }
    System.out.println();

    System.out.println("MagnitudeFilter + DepthFilter");
    f = new MagnitudeFilter(4.0, 5.0);
    filteredList = filter(list, f);
    f = new DepthFilter(-35000.0, -12000.0);
    filteredList = filter(filteredList, f);
    for (QuakeEntry qe : filteredList) {
      System.out.println(qe);
    }
    System.out.println();

    System.out.println("DistanceFilter + PhraseFilter");
    f = new DistanceFilter(new Location(35.42, 139.43), 10000000);
    filteredList = filter(list, f);
    f = new PhraseFilter("end", "Japan");
    filteredList = filter(filteredList, f);
    for (QuakeEntry qe : filteredList) {
      System.out.println(qe);
    }
  }

  public void testMatchAllFilter() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");

    MatchAllFilter maf = new MatchAllFilter();
    maf.addFilter(new MagnitudeFilter(0.0, 2.0, "Magnitude"));
    maf.addFilter(new DepthFilter(-100000.0, -10000.0, "Depth"));
    maf.addFilter(new PhraseFilter("any", "a", "Phrase"));

    ArrayList<QuakeEntry> filteredList = filter(list, maf);
    for (QuakeEntry qe : filteredList) {
      System.out.println(qe);
    }
    System.out.println("Filters used are: " + maf.getName());
  }

  public void testMatchAllFilter2() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");

    MatchAllFilter maf = new MatchAllFilter();
    maf.addFilter(new MagnitudeFilter(0.0, 3.0));
    maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000000));
    maf.addFilter(new PhraseFilter("any", "Ca"));

    ArrayList<QuakeEntry> filteredList = filter(list, maf);
    for (QuakeEntry qe : filteredList) {
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
