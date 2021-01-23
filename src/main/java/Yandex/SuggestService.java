package Yandex;

import java.util.*;

public class SuggestService {
    public HashMap<String, HashSet<String>> map = new HashMap<>();
    ArrayList<String> sortedCompanyNames;

    public SuggestService(List<String> companyNames) {
        sortedCompanyNames = new ArrayList<>(companyNames);
        sortedCompanyNames.replaceAll(String::toLowerCase);
        Collections.sort(sortedCompanyNames);

        outloop:
        for (int i = 0; i < sortedCompanyNames.size(); i++) {
            String company = sortedCompanyNames.get(i);
            for (int j = 1; j <= company.length(); j++) {
                String key = company.substring(0, j);
                if (!map.containsKey(key)) {
                    map.put(key, new HashSet<>(Collections.singletonList(company)));
                } else {
                    continue;
                }
                for (int k = i + 1; k < sortedCompanyNames.size(); k++) {
                    if (sortedCompanyNames.get(k).startsWith(key)) {
                        HashSet<String> set = map.get(key);
                        set.add(sortedCompanyNames.get(k));
                        map.put(key, set);
                    } else {
                        break;
                    }
                }
                if (map.get(key).size() == 1 && j < company.length()) {
                    continue outloop;
                }
            }
        }
    }

    public List<String> suggest(String input, Integer numberOfSuggest) {
        String lowerCaseInput = input.toLowerCase();
        ArrayList<String> result = new ArrayList<>(map.getOrDefault(lowerCaseInput, ifNotContains(lowerCaseInput)));
        return result.subList(0, Math.min(result.size(), numberOfSuggest));
    }

    public HashSet<String> ifNotContains(String in) { // for memory optimization
        for (int i = 1; i < in.length(); i++) {
            String key = in.substring(0, i);
            if (!map.containsKey(key)) {
                break;
            } else if (map.containsKey(key) && map.get(key).size() == 1) {
                for (String s : map.get(key)) {
                    if (s.matches(in + ".*")) {
                        return map.get(key);
                    }
                }
            }
        }
        return new HashSet<>();
    }
}
