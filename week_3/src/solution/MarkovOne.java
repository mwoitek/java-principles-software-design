package solution;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {

  public MarkovOne() {
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
    int idx = myRandom.nextInt(myText.length() - 1);
    String key = myText.substring(idx, idx + 1);
    sb.append(key);
    ArrayList<String> follows;
    for (int k = 0; k < numChars - 1; k++) {
      follows = getFollows(key);
      if (follows.isEmpty()) {
        break;
      }
      idx = myRandom.nextInt(follows.size());
      key = follows.get(idx);
      sb.append(key);
    }
    return sb.toString();
  }

  public String toString() {
    return "MarkovModel of order 1";
  }

}
