package com.nguyen_vi.learning_english_appv1.process.oxford_collocations_dictionary;

import com.nguyen_vi.learning_english_appv1.domain.OxfordCollocationsDictionary;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessOxfordCollocationsDictionary {


    public static Map<String, List<String>> processOxfordCollocationsDictionary(Element element) {
        Map<String, List<String>> finalResult;
        Map<String, Element> result = new HashMap<>();
        List<Element> lstOxfordCollocationsDictionary = element.getElementsByClass("collapse").stream()
                .filter(item -> item.getElementsByClass("box_title").get(0).text().equals("Oxford Collocations Dictionary")).collect(Collectors.toList());
        if (lstOxfordCollocationsDictionary.size() > 0) {
            lstOxfordCollocationsDictionary.forEach(item -> {
                List<String> keys = item.getElementsByTag("span").stream()
                        .filter(item1 -> item1.className().equals("unbox") && item1.childNodes().size() == 1)
                        .collect(Collectors.toList()).stream().map(item2 -> item2.text()).collect(Collectors.toList());
                if (!keys.isEmpty()) {
                    List<Element> ul = item.getElementsByTag("ul").stream().collect(Collectors.toList());
                    for (int i1 = 0; i1 < keys.size(); i1++) {
                        result.put(keys.get(i1), ul.get(i1));
                    }
                }
            });
            finalResult = processMapOfOxfordCollocationsDictionary(result);
            return finalResult;
        }
        return Collections.emptyMap();
    }

    private static Map<String, List<String>> processMapOfOxfordCollocationsDictionary(Map<String, Element> result) {
        Iterator<String> keySets = result.keySet().stream().iterator();
        Map<String, List<String>> finalResult = new HashMap<>();
        while (keySets.hasNext()) {
            String nextElement = keySets.next();
            try {
                finalResult.put(nextElement, processValue(result.get(nextElement)));
            } catch (NoSuchElementException e) {
                continue;
            }
        }
        return finalResult;
    }

    private static List<String> processValue(Element element) {
        try {
            Elements li1 = element.getElementsByTag("li");
            List<String> li = li1.stream()
                    .map(item -> item.text())
                    .map(item -> item.trim())
                    .filter(item -> !(item.equals("…")))
                    .collect(Collectors.toList());
            return li;
        } catch (NoSuchElementException e) {
            return Collections.emptyList();
        }
    }

    public static List<OxfordCollocationsDictionary> subProcessOxfordCollocationDictionary(Map<String, List<String>> oxfordCollocationsDictionary) {
        Set<String> keys = oxfordCollocationsDictionary.keySet();
        List<OxfordCollocationsDictionary> result = new ArrayList<>();
        keys.forEach(item -> {
            OxfordCollocationsDictionary build = OxfordCollocationsDictionary.builder()
                    .key(item)
                    .values(oxfordCollocationsDictionary.get(item))
                    .build();
            result.add(build);
        });
        return result;
    }

}
