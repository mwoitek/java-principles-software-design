package solution;

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

  abstract public String getRandomText(int numChars);

}
