package solution;

public class DistanceFilter implements Filter {

  private Location location;
  private float maxDistance;
  private String name;

  public DistanceFilter(Location loc, float maxDist) {
    location = loc;
    maxDistance = maxDist;
    name = "DistanceFilter";
  }

  public DistanceFilter(Location loc, float maxDist, String n) {
    location = loc;
    maxDistance = maxDist;
    name = n;
  }

  public boolean satisfies(QuakeEntry qe) {
    return qe.getLocation().distanceTo(location) < maxDistance;
  }

  public String getName() {
    return name;
  }

}
