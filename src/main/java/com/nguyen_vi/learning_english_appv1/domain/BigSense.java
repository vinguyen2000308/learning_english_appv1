package com.nguyen_vi.learning_english_appv1.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BigSense {
    private String bigSenseTitle;
    private List<Sense> senses;


}
