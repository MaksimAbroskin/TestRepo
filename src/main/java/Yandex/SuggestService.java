package Yandex;

import java.util.*;
import java.util.regex.Pattern;

public class SuggestService {
    HashMap<String, HashSet<String>> map = new HashMap<>();
    HashMap<Pattern, String> patternMap = new HashMap<>();
    ArrayList<String> sortedCompanyNames;

    public SuggestService(List<String> companyNames) {
        sortedCompanyNames = new ArrayList<>(companyNames);
        Collections.sort(sortedCompanyNames);
        sortedCompanyNames.replaceAll(String::toLowerCase);

        outloop:
        for (int i = 0; i < sortedCompanyNames.size(); i++) {
            String company = sortedCompanyNames.get(i);
            for (int j = 1; j <= company.length(); j++) {
                String key = company.substring(0, j);
                if (!map.containsKey(key)) {
                    HashSet<String> set = new HashSet<>();
                    set.add(company);
                    map.put(key, set);
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
                if (map.get(company.substring(0, j)).size() == 1 && j < company.length()) {
                    patternMap.put(Pattern.compile(company.substring(0, j) + ".+"), company);
                    map.remove(company.substring(0, j));
                    continue outloop;
                }
            }
        }
    }

    public List<String> suggest(String input, Integer numberOfSuggest) {
        String lowerCaseInput = input.toLowerCase();
        ArrayList<String> result = new ArrayList<>(numberOfSuggest);
        int count = 0;
        if (map.containsKey(lowerCaseInput)) {
            for (String s : map.get(lowerCaseInput)) {
                result.add(s);
                count++;
                if (count >= numberOfSuggest) {
                    break;
                }
            }
        } else {
            Pattern pattern = Pattern.compile(lowerCaseInput + ".+");
            for (Pattern p : patternMap.keySet()) {
                if (p.pattern().equals(pattern.pattern())) {
                    result.add(patternMap.get(p));
                    break;
                }
            }
        }
        return result;
    }
}
