package solution;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

  private String getLastWordInTitle(QuakeEntry qe) {
    String[] words = qe.getInfo().split(" ");
    return words[words.length - 1];
  }

  public int compare(QuakeEntry q1, QuakeEntry q2) {
    int c;
    String lastWord1 = getLastWordInTitle(q1);
    String lastWord2 = getLastWordInTitle(q2);
    if ((c = lastWord1.compareTo(lastWord2)) != 0) {
      return c;
    }
    return Double.compare(q1.getMagnitude(), q2.getMagnitude());
  }

}
