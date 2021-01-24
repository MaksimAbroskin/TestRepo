package Yandex;

import java.util.*;

public class SuggestService {
    private final HashMap<String, TreeSet<String>> map = new HashMap<>();

    public SuggestService(List<String> companyNames) {

        companyNames.replaceAll(String::toLowerCase);
        Set<String> companyNamesSet = new HashSet<>(companyNames);
        int len = 1;

        while(!companyNamesSet.isEmpty()) {

            HashSet<String> names = new HashSet<>(companyNamesSet);
            Set<String> diff = new HashSet<>();

            while (!names.isEmpty()) {
                String name = names.iterator().next();
                String key = name.substring(0, Math.min(name.length(), len));

                TreeSet<String> toAdd = new TreeSet<>(names);
                toAdd.removeIf(n -> !n.startsWith(key));

                if (toAdd.size() == 1) diff.add(name);
                else if (toAdd.contains(key)) diff.add(key);

                map.merge(key, toAdd, (x, y) -> {x.addAll(y); return x;});

                names.removeAll(toAdd);
            }

            companyNamesSet.removeAll(diff);
            len++;
        }
    }

    public List<String> suggest(String input, Integer numberOfSuggest) {
        String lowerCaseInput = input.toLowerCase();
        List<String> result = new ArrayList<>(map.getOrDefault(lowerCaseInput, ifNotContains(lowerCaseInput)));
        return result.subList(0, Math.min(result.size(), numberOfSuggest));
    }

    private TreeSet<String> ifNotContains(String in) { // for memory optimization
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
        return new TreeSet<>();
    }
}
