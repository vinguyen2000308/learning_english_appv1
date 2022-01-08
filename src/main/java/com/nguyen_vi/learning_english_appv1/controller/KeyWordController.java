package com.nguyen_vi.learning_english_appv1.controller;

import com.nguyen_vi.learning_english_appv1.domain.KeyWord;
import com.nguyen_vi.learning_english_appv1.domain.VideoContentDTO;
import com.nguyen_vi.learning_english_appv1.service.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/keywords")
public class KeyWordController {

    @Autowired
    private KeyWordService keyWordService;

    @GetMapping("/search/{keyword}")
    public String searchByKeyWord(@PathVariable(name = "keyword") String keyWord) throws IOException {

        return keyWordService.getKeyWordInfo(keyWord);
    }

    @PostMapping("/video-content-upload")
    public ResponseEntity<String> uploadContent(@RequestBody VideoContentDTO videoContentDTO) {
        System.out.println(videoContentDTO);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
