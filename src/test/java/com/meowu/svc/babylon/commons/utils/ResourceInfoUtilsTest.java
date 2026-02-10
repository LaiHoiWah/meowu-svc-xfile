package com.meowu.svc.babylon.commons.utils;

import com.google.gson.Gson;
import com.meowu.starter.common.commons.utils.GsonUtils;
import org.junit.Test;

public class ResourceInfoUtilsTest {

    private final static Gson GSON = GsonUtils.getBuilder(true, true).create();

    @Test
    public void getVideoInfo(){
        String path = "C:\\Users\\Lihaihua\\Desktop\\video1869717699.mp4";
//        String path = "C:\\Users\\Lihaihua\\Desktop\\17660467347822.png";
        ResourceInfoUtils.Metadata metadata = ResourceInfoUtils.getMetadata(path);

        System.out.println(GSON.toJson(metadata));
    }
}
