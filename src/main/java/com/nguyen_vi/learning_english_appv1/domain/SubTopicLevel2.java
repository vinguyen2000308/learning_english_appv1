package com.nguyen_vi.learning_english_appv1.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubTopicLevel2 {
    //    Art equipment
    private String subTopicLevel2Name;
    private String subTopicLevel2Link;
    private List<String> words;

}
