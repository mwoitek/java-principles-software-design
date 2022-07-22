package solution;

public class MagnitudeFilter implements Filter {

  private double minMagnitude;
  private double maxMagnitude;
  private String name;

  public MagnitudeFilter(double min, double max) {
    minMagnitude = min;
    maxMagnitude = max;
    name = "MagnitudeFilter";
  }

  public MagnitudeFilter(double min, double max, String n) {
    minMagnitude = min;
    maxMagnitude = max;
    name = n;
  }

  public boolean satisfies(QuakeEntry qe) {
    double magnitude = qe.getMagnitude();
    return magnitude >= minMagnitude && magnitude <= maxMagnitude;
  }

  public String getName() {
    return name;
  }

}
