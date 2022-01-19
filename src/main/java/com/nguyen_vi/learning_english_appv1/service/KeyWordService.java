package com.nguyen_vi.learning_english_appv1.service;

import com.nguyen_vi.learning_english_appv1.common.Const;
import com.nguyen_vi.learning_english_appv1.process.MainProcess;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KeyWordService {
    public String getKeyWordInfo(String keyWord) throws IOException {
        String searchKey = Const.searchByKey(keyWord);
        Document document = Jsoup.connect(searchKey).get();
        MainProcess mainProcess = new MainProcess(document);
        String result = mainProcess.processMain();
        return result;
    }
}
