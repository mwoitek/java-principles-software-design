package solution;

import edu.duke.FileResource;

public class MarkovRunner {

  public void runMarkovZero() {
    FileResource fr = new FileResource();
    String st = fr.asString();
    st = st.replace('\n', ' ');
    MarkovZero markov = new MarkovZero();
    markov.setTraining(st);
    for (int k = 0; k < 3; k++) {
      String text = markov.getRandomText(500);
      printOut(text);
    }
  }

  public void runMarkovOne() {
    FileResource fr = new FileResource();
    String st = fr.asString();
    st = st.replace('\n', ' ');
    MarkovOne markov = new MarkovOne();
    markov.setTraining(st);
    for (int k = 0; k < 3; k++) {
      String text = markov.getRandomText(500);
      printOut(text);
    }
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
    MarkovRunner mr = new MarkovRunner();
    mr.runMarkovOne();
  }

}
