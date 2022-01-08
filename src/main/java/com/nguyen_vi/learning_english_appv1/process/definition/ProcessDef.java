package com.nguyen_vi.learning_english_appv1.process.definition;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProcessDef {
    public static String processDef(Element element) {
        Optional<Element> def = element.getElementsByTag("span").parallelStream().filter(item -> item.className().equals("def")).findFirst();
        if (def.isPresent())
            return def.get().text();
        else {
            Elements sensestop = element.getElementsByClass("sensestop");
            if (sensestop.size() > 0) {
                List<Element> def1 = sensestop.stream().filter(item -> item.getElementsByClass("def").parallelStream().findFirst().isPresent()).collect(Collectors.toList());
                List<String> result = def1.stream().map(item -> item.text()).collect(Collectors.toList());
                return String.join(",", result);
            }
        }
        throw new IllegalArgumentException("[PROCESS DEFINITION] Not found def section ");
    }
}
