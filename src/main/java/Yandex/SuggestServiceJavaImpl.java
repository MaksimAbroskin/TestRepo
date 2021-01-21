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
        ArrayList<String> fullResult = map.getOrDefault(input, new ArrayList<>());
        return fullResult.subList(0, Math.min(fullResult.size(), numberOfSuggest));
    }
}
