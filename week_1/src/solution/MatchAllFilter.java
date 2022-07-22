package solution;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {

  private ArrayList<Filter> filters;

  public MatchAllFilter() {
    filters = new ArrayList<Filter>();
  }

  public void addFilter(Filter f) {
    filters.add(f);
  }

  public boolean satisfies(QuakeEntry qe) {
    for (Filter f : filters) {
      if (!f.satisfies(qe)) {
        return false;
      }
    }
    return true;
  }

  public String getName() {
    String names = filters.get(0).getName();
    for (int i = 1; i < filters.size(); i++) {
      names += " " + filters.get(i).getName();
    }
    return names;
  }

}
