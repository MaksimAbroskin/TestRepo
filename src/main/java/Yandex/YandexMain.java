package Yandex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class YandexMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();

        String[] names = {"company", "compan", "compa", "comp", "com", "co", "c"};
        List<String> companyNames = new ArrayList<>(Arrays.asList(names));
        SuggestService suggestService = new SuggestServiceJavaImpl(companyNames);

        while (scanner.hasNext()) {
            input.append(scanner.next(".").charAt(0));
            System.out.println(input.toString());
            System.out.println(suggestService.suggest(input.toString(), 5));
        }

    }
}
