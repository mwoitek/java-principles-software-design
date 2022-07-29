package solution;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne implements IMarkovModel {

  private String myText;
  private Random myRandom;

  public MarkovOne() {
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

}
