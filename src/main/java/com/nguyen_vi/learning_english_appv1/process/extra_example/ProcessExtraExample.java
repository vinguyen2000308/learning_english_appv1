package com.nguyen_vi.learning_english_appv1.process.extra_example;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProcessExtraExample {
    public static List<String> processExtraExample(Element element) {
        List<String> result = new ArrayList<>();
        Optional<Element> ul = element.getElementsByTag("ul").stream().filter(item -> item.className().equals("examples")).findFirst();
        if (ul.isPresent()) {
            Optional<Element> withExtraExamples = element.getElementsByClass("collapse").stream().filter(item -> {
                Optional<Node> divWithExtraExamples = item.childNodes().stream().filter(item1 -> item1.attr("unbox").equals("extra_examples")).findFirst();
                return divWithExtraExamples.isPresent();
            }).findFirst();
            Elements lstLi = ul.get().getElementsByTag("li");
            lstLi.forEach(item -> {
                result.add(item.text());
            });
        }
        return result;
    }

    public static List<String> processExtraExamplesLi(Element element) {
        Optional<Element> ul = element.getElementsByTag("ul").stream().filter(item -> item.className().equals("examples")).findFirst();
        if (ul.isPresent()) {
            Optional<Element> withExtraExamples = element.getElementsByClass("collapse").stream().filter(item -> {
                Optional<Node> divWithExtraExamples = item.childNodes().stream().filter(item1 -> item1.attr("unbox").equals("extra_examples")).findFirst();
                return divWithExtraExamples.isPresent();
            }).findFirst();
            if (withExtraExamples.isPresent()) {
                Element element1 = withExtraExamples.get();
                Optional<Element> ulOptional = element1.getElementsByTag("ul").stream().filter(item -> item.className().equals("examples")).findFirst();
                if (ulOptional.isPresent()) {
                    List<String> listExtraExamples = ulOptional.get().getElementsByTag("li").stream().map(item -> item.getElementsByTag("span").get(0).text()).collect(Collectors.toList());
                    return listExtraExamples;
                }
            }
        }
        return Collections.emptyList();
    }
}
