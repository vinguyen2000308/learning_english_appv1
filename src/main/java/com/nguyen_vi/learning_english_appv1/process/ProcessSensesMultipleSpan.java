package com.nguyen_vi.learning_english_appv1.process;

import com.nguyen_vi.learning_english_appv1.domain.BigSense;
import com.nguyen_vi.learning_english_appv1.domain.OxfordCollocationsDictionary;
import com.nguyen_vi.learning_english_appv1.domain.Sense;
import com.nguyen_vi.learning_english_appv1.process.example.ProcessExample;
import com.nguyen_vi.learning_english_appv1.process.extra_example.ProcessExtraExample;
import com.nguyen_vi.learning_english_appv1.process.oxford_collocations_dictionary.ProcessOxfordCollocationsDictionary;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;


public class ProcessSensesMultipleSpan implements ProcessDoc {
    //    List Span with class = "shcut-g"
    private final List<Element> lstSpan;

    public ProcessSensesMultipleSpan(List<Element> lstSpan) {
        this.lstSpan = lstSpan;
    }

    public List<BigSense> getResult() {
        List<BigSense> bigSenses = new ArrayList<>();
        lstSpan.forEach(item -> {
            BigSense bigSense = new BigSense();
            String bigSenseTitle = processBigSenseTitle(item);
            bigSense.setBigSenseTitle(bigSenseTitle);
            List<Sense> lstSense = processEachSpan(item);
            bigSense.setSenses(lstSense);
            bigSenses.add(bigSense);
        });
        return bigSenses;

    }

    private String processBigSenseTitle(Element element) {
        Elements shcut = element.getElementsByClass("shcut");
        if (shcut.size() == 1)
            return shcut.get(0).text();
        throw new IllegalArgumentException("[Multiple-Span][Process Big Senses Title]");
    }


    public List<String> processExamples(List<Element> lstUl) {
        return ProcessExample.processExamples(lstUl);
    }

    public List<String> processExtraExample(Element element) {
        return ProcessExtraExample.processExtraExample(element);
    }

    public List<String> processCollocation(Element element) {
        return null;
    }

    public Map<String, List<String>> processOxfordCollocationsDictionary(Element element) {
        return ProcessOxfordCollocationsDictionary.processOxfordCollocationsDictionary(element);
    }

    private List<Sense> processEachSpan(Element element) {
        List<Sense> result = new ArrayList<>();
        List<Element> lstLi = element.getElementsByTag("li")
                .stream().filter(li -> li.className().equals("sense"))
                .collect(Collectors.toList());
        Sense sense;
        for (int i = 0; i < lstLi.size(); i++) {
            Element li = lstLi.get(i);
            sense = new Sense();


            /*Def DONE*/
            Element def = li.getElementsByClass("def").get(0);
            sense.setDef(def.text());

            /*List Example*/
            List<Element> lstUl = li.children().stream().filter(node -> node.className().equals("examples")).collect(Collectors.toList());
            if (lstUl.size() > 0) {
//                Pass list of ul
                List<String> lstExample = processExamples(lstUl);
                sense.setExamples(lstExample);
            }

            /*Extra Example*/
            Optional<Element> optionalLstExtraExample = li.getElementsByClass("collapse")
                    .stream().filter(item1 -> {
                        Optional<Node> divWithExtraExamples = item1.childNodes()
                                .stream().filter(item2 -> item2.attr("unbox").equals("extra_examples"))
                                .findFirst();
                        return divWithExtraExamples.isPresent();
                    }).findFirst();
            List<String> lstExtraExample = null;
//            div with class = "collapse" and box_title = "extra_examples"
            if (optionalLstExtraExample.isPresent())
                lstExtraExample = processExtraExample(optionalLstExtraExample.get());
            sense.setExtraExamples(lstExtraExample);


            /*Oxford Dictionary Collocations*/
            Optional<Element> optionalLstOxfordCollocationsDictionary = li.getElementsByClass("collapse").stream()
                    .filter(item1 -> item1.getElementsByClass("box_title").get(0).text().equals("Oxford Collocations Dictionary"))
                    .findFirst();

            Map<String, List<String>> oxfordCollocationsDictionary = new HashMap<>();
            if (optionalLstOxfordCollocationsDictionary.isPresent())
                oxfordCollocationsDictionary = processOxfordCollocationsDictionary(optionalLstOxfordCollocationsDictionary.get());
            List<OxfordCollocationsDictionary> oxfordCollocationsDictionaries = ProcessOxfordCollocationsDictionary.subProcessOxfordCollocationDictionary(oxfordCollocationsDictionary);
            sense.setOxfordCollocationsDictionaries(oxfordCollocationsDictionaries);


            /*Collocations*/
            Optional<Element> optionalLstCollocations = li.getElementsByClass("collapse").stream()
                    .filter(item1 -> item1.getElementsByClass("box_title").get(0).text().equals("Collocations"))
                    .findFirst();

            List<String> lstCollocations = null;
            if (optionalLstCollocations.isPresent())
                lstCollocations = processCollocation(optionalLstCollocations.get());
            sense.setCollocations(lstCollocations);
            result.add(sense);

        }


        return result;
    }


}
