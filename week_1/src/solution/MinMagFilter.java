package solution;

public class MinMagFilter implements Filter {

  private double magMin;
  private String name;

  public MinMagFilter(double min) {
    magMin = min;
    name = "MinMagFilter";
  }

  public MinMagFilter(double min, String n) {
    magMin = min;
    name = n;
  }

  public boolean satisfies(QuakeEntry qe) {
    return qe.getMagnitude() >= magMin;
  }

  public String getName() {
    return name;
  }

}
