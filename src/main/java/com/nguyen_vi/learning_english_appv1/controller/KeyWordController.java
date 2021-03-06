package com.nguyen_vi.learning_english_appv1.controller;

import com.nguyen_vi.learning_english_appv1.service.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
