package solution;

import java.util.ArrayList;

public class ClosestQuakes {

  private float[] computeDistances(ArrayList<QuakeEntry> quakeData, Location current) {
    int numQuakes = quakeData.size();
    float[] distances = new float[numQuakes];
    for (int i = 0; i < numQuakes; i++) {
      distances[i] = quakeData.get(i).getLocation().distanceTo(current);
    }
    return distances;
  }

  private int indexOfMin(float[] values) {
    int idxMin = 0;
    float min = values[0];
    for (int idx = 1; idx < values.length; idx++) {
      if (values[idx] < min) {
        idxMin = idx;
        min = values[idx];
      }
    }
    return idxMin;
  }

  public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current,
      int howMany) {
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    float[] distances = computeDistances(quakeData, current);
    int idx;
    int numRepetitions = howMany <= quakeData.size() ? howMany : quakeData.size();
    for (int i = 0; i < numRepetitions; i++) {
      idx = indexOfMin(distances);
      answer.add(quakeData.get(idx));
      distances[idx] = Float.MAX_VALUE;
    }
    return answer;
  }

  public void findClosestQuakes() {
    EarthQuakeParser parser = new EarthQuakeParser();
    String source = "../data/nov20quakedatasmall.atom";
    ArrayList<QuakeEntry> list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
    Location jakarta = new Location(-6.211, 106.845);
    QuakeEntry qe;
    double distanceInMeters;
    ArrayList<QuakeEntry> close = getClosest(list, jakarta, 3);
    for (int k = 0; k < close.size(); k++) {
      qe = close.get(k);
      distanceInMeters = jakarta.distanceTo(qe.getLocation());
      System.out.printf("%4.2f\t %s\n", distanceInMeters / 1000, qe);
    }
    System.out.println("number found: " + close.size());
  }

  public static void main(String[] args) {
    ClosestQuakes cq = new ClosestQuakes();
    cq.findClosestQuakes();
  }

}
