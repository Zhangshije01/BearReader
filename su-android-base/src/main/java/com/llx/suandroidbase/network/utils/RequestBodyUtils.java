package com.llx.suandroidbase.network.utils;

import com.google.gson.Gson;
import com.llx.suandroidbase.commen.LogUtil;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * Author:  fanlei
 * Time: 2017/9/8 18:11
 */

public class RequestBodyUtils {

    /**
     * 得到请求参数 对请求的参数进行加密
     * @return
     */
    public static RequestBody getRequestBody(Object obj) {
        String reqStr;
        reqStr = new Gson().toJson(obj);
        LogUtil.d("Okhttp", " 请求参数 ：" + reqStr);// 打印数据
        reqStr = Assistant.Boxing(reqStr);// 加密
        return RequestBody.create(okhttp3.MediaType.parse("application/text; charset=utf-8"), reqStr);
    }

    public static RequestBody getWMSRequestBody(Object obj) {
        String reqStr;
        reqStr = new Gson().toJson(obj);
        LogUtil.d("Okhttp", " 请求参数 ：" + reqStr);// 打印数据
//        reqStr = Assistant.Boxing(reqStr);// 加密
        return RequestBody.create(okhttp3.MediaType.parse("application/json"), reqStr);
    }

    public static String getBodyInfo(ResponseBody body){
        String result = "";
        byte[] bytes = new byte[0];
        try {
            bytes = body.bytes();
            String encryption_content = new String(bytes, "UTF-8");// 未解密的串
            result = Assistant.UnBoxing(encryption_content);// 解密后的串
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
