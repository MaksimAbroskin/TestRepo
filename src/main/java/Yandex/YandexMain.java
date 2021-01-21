package Yandex;

import java.util.*;
import java.util.regex.Pattern;

public class YandexMain {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder input = new StringBuilder();
//
//        String[] names = {"company", "compan", "compa", "comp", "com", "co", "c"};
//        List<String> companyNames = new ArrayList<>(Arrays.asList(names));
//        SuggestService suggestService = new SuggestService(companyNames);

        HashMap<String, String> map = new HashMap<>();
        map.put("ab", "abcde");
        map.put("abcde", "abcde");

        String in = "abc";
        Pattern pattern = Pattern.compile(in + ".*");

        HashSet<String> keySet = new HashSet<>(map.keySet());

        System.out.println(keySet.contains(pattern));

        System.out.println(map.containsKey(pattern.pattern()));


//        while (scanner.hasNext()) {
//            input.append(scanner.next(".").charAt(0));
//            System.out.println(input.toString());
//            System.out.println(suggestService.suggest(input.toString(), 5));
//        }

    }
}
