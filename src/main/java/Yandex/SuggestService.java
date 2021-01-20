package Yandex;

import java.util.List;

public interface SuggestService {
    List<String> suggest(String input, Integer numberOfSuggest);
}
