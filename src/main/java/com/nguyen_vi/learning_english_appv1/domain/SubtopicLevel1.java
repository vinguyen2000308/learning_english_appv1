package com.nguyen_vi.learning_english_appv1.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubtopicLevel1 {
    //    Art or Film and theatre
    private String subTopicLeve1Name;
    private String subTopicLevel1Link;
    private List<SubTopicLevel2> lstSubTopicLevel2;
}
