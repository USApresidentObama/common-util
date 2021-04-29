package com.commom.test;

import com.commom.json.JsonUtil;
import java.util.Collections;

public class JsonTest {
    public static void main(String[] args) {
        ResultForm resultForm = new ResultForm();
        resultForm.setData(new User());
        System.out.println(JsonUtil.toJson(resultForm));
    }
}
