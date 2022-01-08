package com.nguyen_vi.learning_english_appv1.process.topic;

import com.nguyen_vi.learning_english_appv1.domain.SubTopicLevel2;
import com.nguyen_vi.learning_english_appv1.domain.SubtopicLevel1;
import com.nguyen_vi.learning_english_appv1.utils.data.DataUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryProcess {

    private Document document;

    public List<SubtopicLevel1> categoryProcess() {

        List<SubtopicLevel1> lstSubtopicLevel1 = new ArrayList<>();
//        Get topic list by class = "topic_list
        Elements topic_list = document.getElementsByClass("topic_list");
        if (topic_list.size() > 0) {
//            Get element by class = "topic-box topic-box-secondary"
            Elements lstTopicBox = topic_list.get(0).getElementsByClass("topic-box");
            if (!lstTopicBox.isEmpty()) {
                for (Element topicBox : lstTopicBox) {
//                    Get topic secondary heading topic-box-secondary-heading

                    SubtopicLevel1 subtopicLevel1 = processSubtopicLevel1(topicBox);
//                      Get lst of subtopic
                    List<SubTopicLevel2> lstSubtopicLevel2 = processListSubtopicLevel2(topicBox);
                    subtopicLevel1.setLstSubTopicLevel2(lstSubtopicLevel2);

                    System.out.println();
                    lstSubtopicLevel1.add(subtopicLevel1);
                }
            }
        }
        return lstSubtopicLevel1;

    }

    private List<SubTopicLevel2> processListSubtopicLevel2(Element topicBox) {
        Elements lstL3 = topicBox.getElementsByClass("l3");
        List<SubTopicLevel2> lstSubtopicLevel2 = new ArrayList<>();
        if (DataUtils.isNotEmpty(lstL3)) {
            Element l3 = lstL3.get(0);
            Elements lstA = l3.getElementsByTag("a");
            if (DataUtils.isNotEmpty(lstA)) {
                for (Element a : lstA) {
                    String href = a.attr("href");
                    String subTopicText = a.text();
                    SubTopicLevel2 subTopicLevel2 = SubTopicLevel2.builder()
                            .subTopicLevel2Name(subTopicText)
                            .subTopicLevel2Link(href)
                            .build();
                    lstSubtopicLevel2.add(subTopicLevel2);
                }
            }
        }
        return lstSubtopicLevel2;
    }

    private SubtopicLevel1 processSubtopicLevel1(Element topicBox) {
        Elements topicBoxSecondaryHeading = topicBox.getElementsByClass("topic-box-secondary-heading");
        SubtopicLevel1 subtopicLevel1 = new SubtopicLevel1();
        if (DataUtils.isNotEmpty(topicBoxSecondaryHeading)) {
//                        a
            Element a = topicBoxSecondaryHeading.get(0);
//                        Get text and href
            String topicSecondaryText = a.text();
            String href = a.attr("href");
            subtopicLevel1.setSubTopicLeve1Name(topicSecondaryText);
            subtopicLevel1.setSubTopicLevel1Link(href);
        }
        return subtopicLevel1;

    }

    public void print(List<SubtopicLevel1> lstSubtopicLevel1) {
        for (int i = 0; i < lstSubtopicLevel1.size(); i++) {
            SubtopicLevel1 subtopicLevel1 = lstSubtopicLevel1.get(i);
            System.out.println(subtopicLevel1.getSubTopicLeve1Name() + "  " + subtopicLevel1.getSubTopicLevel1Link());
            List<SubTopicLevel2> lstSubTopicLevel2 = subtopicLevel1.getLstSubTopicLevel2();
            for (int i1 = 0; i1 < lstSubTopicLevel2.size(); i1++) {
                SubTopicLevel2 subTopicLevel2 = lstSubTopicLevel2.get(i1);
                System.out.println("             " + subTopicLevel2.getSubTopicLevel2Name() + "   " + subTopicLevel2.getSubTopicLevel2Link());
            }
            System.out.println();

        }

    }

}
