package solution;

public class PhraseFilter implements Filter {

  private String position;
  private String phrase;
  private String name;

  public PhraseFilter(String pos, String ph) {
    position = pos;
    phrase = ph;
    name = "PhraseFilter";
  }

  public PhraseFilter(String pos, String ph, String n) {
    position = pos;
    phrase = ph;
    name = n;
  }

  public boolean satisfies(QuakeEntry qe) {
    String title = qe.getInfo();
    return (position.equals("start") && title.startsWith(phrase))
        || (position.equals("end") && title.endsWith(phrase))
        || (position.equals("any") && title.contains(phrase));
  }

  public String getName() {
    return name;
  }

}
