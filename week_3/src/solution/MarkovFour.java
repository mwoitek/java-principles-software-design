package solution;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {

  public MarkovFour() {
    myRandom = new Random();
  }

  public void setRandom(int seed) {
    myRandom = new Random(seed);
  }

  public void setTraining(String s) {
    myText = s.trim();
  }

  public String getRandomText(int numChars) {
    if (myText == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int idx = myRandom.nextInt(myText.length() - 4);
    String key = myText.substring(idx, idx + 4);
    sb.append(key);
    ArrayList<String> follows;
    String next;
    for (int k = 0; k < numChars - 4; k++) {
      follows = getFollows(key);
      if (follows.isEmpty()) {
        break;
      }
      idx = myRandom.nextInt(follows.size());
      next = follows.get(idx);
      sb.append(next);
      key = key.substring(1) + next;
    }
    return sb.toString();
  }

  public String toString() {
    return "MarkovModel of order 4";
  }

}
