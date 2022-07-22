package solution;

public class PhraseFilter implements Filter {

  private String position;
  private String phrase;

  public PhraseFilter(String pos, String ph) {
    position = pos;
    phrase = ph;
  }

  public boolean satisfies(QuakeEntry qe) {
    String title = qe.getInfo();
    return (position.equals("start") && title.startsWith(phrase))
        || (position.equals("end") && title.endsWith(phrase))
        || (position.equals("any") && title.contains(phrase));
  }

}
