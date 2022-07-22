package solution;

public class DistanceFilter implements Filter {

  private Location location;
  private float maxDistance;

  public DistanceFilter(Location loc, float maxDist) {
    location = loc;
    maxDistance = maxDist;
  }

  public boolean satisfies(QuakeEntry qe) {
    return qe.getLocation().distanceTo(location) < maxDistance;
  }

}
