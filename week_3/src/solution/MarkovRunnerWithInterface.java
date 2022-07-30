package solution;

import edu.duke.FileResource;

public class MarkovRunnerWithInterface {

  public void runModel(IMarkovModel markov, String text, int size, int seed) {
    markov.setTraining(text);
    markov.setRandom(seed);
    System.out.println("running with " + markov);
    for (int k = 0; k < 3; k++) {
      String st = markov.getRandomText(size);
      printOut(st);
    }
  }

  public void runMarkov() {
    FileResource fr = new FileResource();
    String st = fr.asString();
    st = st.replace('\n', ' ');
    int size = 200;
    int seed = 333;

    MarkovZero mZero = new MarkovZero();
    runModel(mZero, st, size, seed);

    MarkovOne mOne = new MarkovOne();
    runModel(mOne, st, size, seed);

    MarkovModel mThree = new MarkovModel(3);
    runModel(mThree, st, size, seed);

    MarkovFour mFour = new MarkovFour();
    runModel(mFour, st, size, seed);
  }

  public void testHashMap() {
    EfficientMarkovModel mTwo = new EfficientMarkovModel(2);
    mTwo.setRandom(42);
    mTwo.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
    printOut(mTwo.getRandomText(50));
  }

  public void compareMethods() {
    FileResource fr = new FileResource("../data/hawthorne.txt");
    String s = fr.asString();
    s = s.replace('\n', ' ');

    MarkovModel markov1 = new MarkovModel(2);
    markov1.setRandom(42);
    System.out.println("MarkovModel:");
    long startTime = System.nanoTime();
    markov1.setTraining(s);
    for (int k = 0; k < 3; k++) {
      printOut(markov1.getRandomText(1000));
    }
    long stopTime = System.nanoTime();
    double executionTime = ((double) (stopTime - startTime)) / 1000000000;
    System.out.println("Execution time: " + executionTime + " s");
    System.out.println();

    EfficientMarkovModel markov2 = new EfficientMarkovModel(2);
    markov2.setRandom(42);
    System.out.println("EfficientMarkovModel:");
    startTime = System.nanoTime();
    markov2.setTraining(s);
    for (int k = 0; k < 3; k++) {
      printOut(markov2.getRandomText(1000));
    }
    stopTime = System.nanoTime();
    executionTime = ((double) (stopTime - startTime)) / 1000000000;
    System.out.println("Execution time: " + executionTime + " s");
  }

  private void printOut(String s) {
    String[] words = s.split("\\s+");
    int psize = 0;
    System.out.println("----------------------------------");
    for (int k = 0; k < words.length; k++) {
      System.out.print(words[k] + " ");
      psize += words[k].length() + 1;
      if (psize > 60) {
        System.out.println();
        psize = 0;
      }
    }
    System.out.println("\n----------------------------------");
  }

  public static void main(String args[]) {
    MarkovRunnerWithInterface mr = new MarkovRunnerWithInterface();
    // mr.runMarkov();
    // mr.testHashMap();
    // mr.compareMethods();

    FileResource fr = new FileResource("../data/confucius.txt");
    String s = fr.asString();
    s = s.replace('\n', ' ');

    EfficientMarkovModel markov = new EfficientMarkovModel(6);
    mr.runModel(markov, s, 100, 792);

    markov = new EfficientMarkovModel(5);
    mr.runModel(markov, s, 100, 531);
  }

}
