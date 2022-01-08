package com.nguyen_vi.learning_english_appv1.process.helper;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelperProcess {

    private static Map<String, List<String>> processHelpSection(Element element) {
        Map<String, List<String>> result = new HashMap<>();
        List<String> canNotUse = new ArrayList<>();
        List<String> anotherAppropriateWords = new ArrayList<>();
        List<String> exampleForAppropriateWords = new ArrayList<>();
        element.getElementsByTag("span").stream().filter(item -> item.childNodes().size() == 1).forEach(item -> {
            if (item.className().equals("wx"))
                canNotUse.add(item.text());
            if (item.className().equals("ei"))
                exampleForAppropriateWords.add(item.text());
            if (item.className().equals("eb"))
                anotherAppropriateWords.add(item.text());
        });
        result.put("canNotUse", canNotUse);
        result.put("anotherAppropriateWords", anotherAppropriateWords);
        result.put("exampleForAppropriateWords", exampleForAppropriateWords);
        return result;
    }

}
