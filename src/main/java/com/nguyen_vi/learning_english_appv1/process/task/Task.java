package com.nguyen_vi.learning_english_appv1.process.task;

import com.nguyen_vi.learning_english_appv1.domain.OxfordCollocationsDictionary;
import com.nguyen_vi.learning_english_appv1.domain.Sense;
import com.nguyen_vi.learning_english_appv1.process.extra_example.ProcessExtraExample;
import com.nguyen_vi.learning_english_appv1.process.oxford_collocations_dictionary.ProcessOxfordCollocationsDictionary;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.nguyen_vi.learning_english_appv1.process.definition.ProcessDef.processDef;
import static com.nguyen_vi.learning_english_appv1.process.example.ProcessExample.processExamplesLi;

public class Task implements Callable<Sense> {

    private final Element element;

    public Task(Element element) {
        this.element = element;
    }

    @Override
    public Sense call() throws Exception {
        Sense sense = new Sense();
        String definitions = processDef(element);
        List<String> examples = processExamplesLi(element);
        List<String> extraExamples = ProcessExtraExample.processExtraExamplesLi(element);
        Map<String, List<String>> oxfordCollocationsDictionary = ProcessOxfordCollocationsDictionary.processOxfordCollocationsDictionary(element);
        List<OxfordCollocationsDictionary> oxfordCollocationsDictionaries = ProcessOxfordCollocationsDictionary.subProcessOxfordCollocationDictionary(oxfordCollocationsDictionary);
        sense.setDef(definitions);
        sense.setExamples(examples);
        sense.setExtraExamples(extraExamples);
        sense.setOxfordCollocationsDictionaries(oxfordCollocationsDictionaries);
        return sense;
    }
}
