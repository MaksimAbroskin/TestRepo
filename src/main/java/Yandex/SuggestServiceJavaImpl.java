package Yandex;

import java.util.*;

public class SuggestServiceJavaImpl implements SuggestService {
    HashMap<String, ArrayList<String>> map = new HashMap<>();

    public SuggestServiceJavaImpl(List<String> companyNames) {
        for (String s: companyNames) {
            for (int i = 1; i <= s.length(); i++) {
                String key = s.substring(0, i);
                ArrayList<String> list = map.get(key);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(s);
                map.put(key, list);
            }
        }
    }

    public List<String> suggest(String input, Integer numberOfSuggest) {
        return map.getOrDefault(input, new ArrayList<>()).subList(0, numberOfSuggest - 1);
    }
}
