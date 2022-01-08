package com.nguyen_vi.learning_english_appv1;

public class Const {
    private static final String BASE_URL = "https://www.oxfordlearnersdictionaries.com";


    public static String searchByKey(String key) {
        return Const.BASE_URL + "/definition/english/" + key;
    }

    public static String topicKey() {
        return Const.BASE_URL + "/topic/";
    }

    public static String categoryKey(String category) {
        return Const.BASE_URL + "/topic/category/" + category;
    }

    public static String topicSubList(String topic, String subList) {
        return Const.BASE_URL + "/topic/" + topic + "/?sublist=" + subList;
    }
}
