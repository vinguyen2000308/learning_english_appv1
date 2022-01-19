package com.nguyen_vi.learning_english_appv1.controller;

import com.nguyen_vi.learning_english_appv1.domain.VideoContentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/script")
public class ScriptController {

    @PostMapping("/video-content-upload")
    public ResponseEntity<String> uploadContent(@RequestBody VideoContentDTO videoContentDTO) {
        System.out.println(videoContentDTO);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


}
