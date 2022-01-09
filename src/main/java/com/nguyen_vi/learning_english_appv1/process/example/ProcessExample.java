package com.nguyen_vi.learning_english_appv1.process.example;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProcessExample {
    public static List<String> processExamples(List<Element> ul) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < ul.size(); i++) {
            for (Element li : ul.get(i).getElementsByTag("li")) {
                result.add(li.text());
            }
        }

        return result;
    }

//    Li
    public static  List<String> processExamplesLi(Element element) {
        Optional<Element> examples = element.getElementsByTag("ul").stream().filter(item -> item.className().equals("examples")).findFirst();
        if (examples.isPresent()) {
            Element element1 = examples.get();
            Elements li = element1.getElementsByTag("li");
            List<Element> elementList = li.stream().filter(item ->
                    item.getElementsByTag("span").stream().filter(item1 -> item1.className().equals("x")).findFirst().isPresent()).collect(Collectors.toList());
            List<String> result = elementList.stream().map(item -> item.text()).collect(Collectors.toList());
            return result;
        } else
            return Collections.emptyList();
    }
}
