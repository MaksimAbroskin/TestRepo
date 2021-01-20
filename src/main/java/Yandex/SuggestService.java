package Yandex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SuggestService {
    HashMap<String, HashSet<String>> map = new HashMap<>();

    public SuggestService(List<String> companyNames) {
        for (String s: companyNames) {
            for (int i = 1; i <= s.length(); i++) {
                String key = s.substring(0, i);
                HashSet<String> set = map.get(key);
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(s);
                map.put(key, set);
            }
        }
    }

    public List<String> suggest(String input, Integer numberOfSuggest) {
        ArrayList<String> result = new ArrayList<>(numberOfSuggest);
        int count = 0;
        if (map.containsKey(input)) {
            for (String s: map.get(input)) {
                result.add(s);
                count++;
                if (count >= numberOfSuggest) {
                    break;
                }
            }
        }
        return result;
    }
}
