package com.nguyen_vi.learning_english_appv1.process;

import com.nguyen_vi.learning_english_appv1.domain.Sense;
import com.nguyen_vi.learning_english_appv1.process.oxford_collocations_dictionary.ProcessOxfordCollocationsDictionary;
import com.nguyen_vi.learning_english_appv1.process.task.Task;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProcessSensesMultipleLi {
    private final List<Element> lstLi;

    public ProcessSensesMultipleLi(List<Element> lstLi) {
        this.lstLi = lstLi;
    }
    public List<Sense> getResult() {
        List<Sense> senses = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Sense>> listFutureSense = new ArrayList<>();
        for (int i = 0; i < lstLi.size(); i++) {
            Element element = lstLi.get(i);
            Task task = new Task(element);
            Future<Sense> future = executorService.submit(task);
            listFutureSense.add(future);
        }
        listFutureSense.forEach(item -> {
            try {
                senses.add(item.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdownNow();
        return senses;
    }

    private String processTitle(Element element) {
        Elements sensetop = element.getElementsByClass("sensetop");
        String result = null;
        if (sensetop.size() > 0)
            result = sensetop.get(0).getElementsByTag("span").get(0).text();
        return result;
    }

    public Map<String, List<String>> processOxfordCollocationsDictionary(Element element) {

        return ProcessOxfordCollocationsDictionary.processOxfordCollocationsDictionary(element);
    }


}
