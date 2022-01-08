package com.nguyen_vi.learning_english_appv1.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyen_vi.learning_english_appv1.domain.BigSense;
import com.nguyen_vi.learning_english_appv1.domain.Sense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainProcess {

    private Document doc;

    public String processMain() {
        try {
            Elements content;
            content = doc.getElementsByClass("senses_multiple");
            if (content.isEmpty())
                content = doc.getElementsByClass("sense_single");
            ObjectMapper objectMapper = new ObjectMapper();
            if (Objects.nonNull(content) && content.size() > 0) {
                String jsonResult = null;
                org.jsoup.nodes.Element element = content.get(0);
                String tagName = element.children().get(0).tagName();
                if (tagName.equals("span")) {
                    List<Element> lstSpan = element.getElementsByTag("span").parallelStream().filter(item -> item.className().equals("shcut-g")).collect(Collectors.toList());
                    ProcessSensesMultipleSpan processDocSenseMultiple = new ProcessSensesMultipleSpan(lstSpan);
                    List<BigSense> result = processDocSenseMultiple.getResult();
                    jsonResult = objectMapper.writeValueAsString(result);

                } else if (tagName.equals("li")) {
                    List<org.jsoup.nodes.Element> elements = element.getElementsByTag("li").parallelStream().filter(item -> item.className().equals("sense")).collect(Collectors.toList());
                    ProcessSensesMultipleLi processSensesMultipleLi = new ProcessSensesMultipleLi(elements);
                    List<Sense> result = processSensesMultipleLi.getResult();
                    jsonResult = objectMapper.writeValueAsString(result);
                }
                return jsonResult;
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Not found definition");
    }
}
