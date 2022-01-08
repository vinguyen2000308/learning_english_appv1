package com.nguyen_vi.learning_english_appv1.utils.data;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtils {

    public static List<Element> processUl(Element element) {
        List<Element> li = element.getElementsByTag("li").stream().collect(Collectors.toList());
        return li;
    }

    public static String processLi(Element li) {
        Elements a = li.getElementsByTag("a");
        String text = "";
        if (a.size() == 1) {
            text = a.get(0).text();
        }
        return text;
    }

    public static boolean isNotEmpty(Collection<Element> collection)
    {
        return  !collection.isEmpty();
    }
}
