package com.nguyen_vi.learning_english_appv1.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Topic {
    //    Culture
    private String categoryName;
    //    https://www.oxfordlearnersdictionaries.com/topic/category/culture
    private String categoryLink;
    private List<SubtopicLevel1> lstSubtopicLevel1;


}
