package solution;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

  public int compare(QuakeEntry q1, QuakeEntry q2) {
    int c;
    if ((c = q1.getInfo().compareTo(q2.getInfo())) != 0) {
      return c;
    }
    return Double.compare(q1.getDepth(), q2.getDepth());
  }

}
