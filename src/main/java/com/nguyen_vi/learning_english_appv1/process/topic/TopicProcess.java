package com.nguyen_vi.learning_english_appv1.process.topic;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TopicProcess {

    private Document doc;

    public Map<String, String> getListOfTopic() {

        Map<String, String> lstTopic = new HashMap<>();
//    Get topic list by class = "topic-list"
        Elements topic_list = doc.getElementsByClass("topic_list");
        if (topic_list.size() > 0) {
//            Get list of topic-box
            Elements lstTopicBox = topic_list.get(0).getElementsByClass("topic-box");
            if (lstTopicBox.size() > 0) {
//                Get topic href
                for (Element topicBox : lstTopicBox) {
//                    Get link href
                    Elements a = topicBox.getElementsByTag("a");
                    Elements topicLabel = topicBox.getElementsByClass("topic-label");
                    if (a.size() > 0 && topicLabel.size() > 0) {
                        String href = a.get(0).attr("href");
                        String topicLabelText = topicLabel.text();
                        lstTopic.put(topicLabelText, href);
                    }
                }
            }
            return lstTopic;
        }
        return Collections.emptyMap();
    }


}
