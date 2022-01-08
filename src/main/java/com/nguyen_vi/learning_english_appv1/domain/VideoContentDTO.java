package com.nguyen_vi.learning_english_appv1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoContentDTO {
    private String videoId;
    private String videoScript;
}
