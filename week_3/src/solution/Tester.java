package solution;

import java.util.ArrayList;
import edu.duke.FileResource;

public class Tester {

  public void testGetFollows() {
    MarkovOne markov = new MarkovOne();
    markov.setTraining("this is a test yes this is a test.");

    ArrayList<String> follows = markov.getFollows("t");
    System.out.println(follows.size());
    System.out.println(follows);

    follows = markov.getFollows("e");
    System.out.println(follows.size());
    System.out.println(follows);

    follows = markov.getFollows("es");
    System.out.println(follows.size());
    System.out.println(follows);

    follows = markov.getFollows(".");
    System.out.println(follows.size());
    System.out.println(follows);

    follows = markov.getFollows("t.");
    System.out.println(follows.size());
    System.out.println(follows);
  }

  public void testGetFollowsWithFile() {
    MarkovOne markov = new MarkovOne();
    FileResource fr = new FileResource("../data/confucius.txt");
    markov.setTraining(fr.asString());
    ArrayList<String> follows = markov.getFollows("t");
    System.out.println(follows.size());
  }

  public static void main(String[] args) {
    Tester t = new Tester();
    // t.testGetFollows();
    t.testGetFollowsWithFile();
  }

}
