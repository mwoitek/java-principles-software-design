package solution;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {

  private String myText;
  private Random myRandom;

  public MarkovFour() {
    myRandom = new Random();
  }

  public void setRandom(int seed) {
    myRandom = new Random(seed);
  }

  public void setTraining(String s) {
    myText = s.trim();
  }

  public ArrayList<String> getFollows(String key) {
    ArrayList<String> follows = new ArrayList<String>();
    int keyLength = key.length();
    int pos = 0;
    int idx;
    int startIdx;
    while (pos < myText.length() && (idx = myText.indexOf(key, pos)) != -1) {
      try {
        startIdx = idx + keyLength;
        follows.add(myText.substring(startIdx, startIdx + 1));
        pos = idx + 1;
      } catch (Exception e) {
        break;
      }
    }
    return follows;
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

}
