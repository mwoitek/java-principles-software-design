package solution;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {

  protected String myText;
  protected Random myRandom;

  public AbstractMarkovModel() {
    myRandom = new Random();
  }

  public void setTraining(String s) {
    myText = s.trim();
  }

  protected ArrayList<String> getFollows(String key) {
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

  abstract public String getRandomText(int numChars);

}
