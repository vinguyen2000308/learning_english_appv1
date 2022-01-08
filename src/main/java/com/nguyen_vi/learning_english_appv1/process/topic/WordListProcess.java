package com.nguyen_vi.learning_english_appv1.process.topic;


import com.nguyen_vi.learning_english_appv1.utils.data.DataUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class WordListProcess {
    private Document document;

    public List<String> processWordList() {
        List<String> result = new ArrayList<>();
        Elements lstTopG = document.getElementsByClass("top-g");
        if (DataUtils.isNotEmpty(lstTopG)) {
            Element topG = lstTopG.get(0);
            Elements lstLi = topG.getElementsByTag("li");
            if (DataUtils.isNotEmpty(lstLi)) {
                for (int i = 0; i < lstLi.size(); i++) {
                    Element li = lstLi.get(i);
                    Elements lstA = li.getElementsByTag("a");
                    String text = lstA.get(0).text();
                    if (DataUtils.isNotEmpty(lstA))
                        if (!result.contains(text))
                            result.add(text);
                }
            }
        }
        return result;
    }
}
