package com.nguyen_vi.learning_english_appv1.domain;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Sense {
    private String def;
    private Map<String, String> phonetics;
    private List<String> examples;
    private List<String> extraExamples;
    private List<String> collocations;
    private List<OxfordCollocationsDictionary> oxfordCollocationsDictionaries;


}