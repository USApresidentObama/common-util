package com.commom.test;

import com.commom.distributed.Func;
import com.commom.util.DistributedUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistributeTest {
    public static void main(String[] args) {
        List<Func<Map<String, Object>>> tasks = new ArrayList<>();
        tasks.add(new Func<Map<String, Object>>() {
            @Override
            public Map<String, Object> method() {

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("1", 1);
                return resultMap;
            }
        });
        tasks.add(new Func<Map<String, Object>>() {
            @Override
            public Map<String, Object> method() {

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("2", 2.2);
                return resultMap;
            }
        });
        tasks.add(new Func<Map<String, Object>>() {
            @Override
            public Map<String, Object> method() {

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("3", 3.3);
                return resultMap;
            }
        });
        Map<String, Object> result = DistributedUtil.parallelCompute(tasks);
        System.out.println(result.toString());

    }
}
