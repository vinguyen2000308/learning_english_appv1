package com.nguyen_vi.learning_english_appv1.process.phonetic;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class PhoneticsProcess {
    private static Map<String, String> processPhoneticsSection(Elements phonetics) {
        Map<String, String> result = new HashMap<>();
        if (phonetics.size() > 0) {
            Elements phonsBrs = phonetics.get(0).getElementsByClass("phons_br");
            if (phonsBrs.size() > 0) {
                Element phonsBr = phonsBrs.get(0);
                Element sound = phonsBr.getElementsByClass("sound").get(0);
                String linkFileMP3 = sound.attr("data-src-mp3");
                Element span = phonsBr.getElementsByTag("span").get(0);
                String text = span.text();
                result.put(text, linkFileMP3);
            }
        }
        return result;
    }

}
