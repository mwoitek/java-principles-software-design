package solution;

public class DepthFilter implements Filter {

  private double minDepth;
  private double maxDepth;
  private String name;

  public DepthFilter(double min, double max) {
    minDepth = min;
    maxDepth = max;
    name = "DepthFilter";
  }

  public DepthFilter(double min, double max, String n) {
    minDepth = min;
    maxDepth = max;
    name = n;
  }

  public boolean satisfies(QuakeEntry qe) {
    double depth = qe.getDepth();
    return depth >= minDepth && depth <= maxDepth;
  }

  public String getName() {
    return name;
  }

}
