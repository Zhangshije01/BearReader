package com.llx.bear.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.llx.bear.commen.utils.LogUtil;
import com.llx.bear.network.utils.Assistant;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author:  zhangshijie
 * Time: 2017/9/9 10:22
 */

public class AESGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private JsonBridge jsonBridge;


    AESGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }
    AESGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter,JsonBridge jsonBridge) {
        this.gson = gson;
        this.adapter = adapter;
        this.jsonBridge = jsonBridge;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        byte[] bytes = value.bytes();

        String encryption_content = new String(bytes, "UTF-8");// 未解密的串
        LogUtil.d("OkHttp", "解密前 : " + encryption_content);

        String decrypt_text = Assistant.UnBoxing(encryption_content);// 解密后的串
        LogUtil.d("OkHttp", "解密后 : " + decrypt_text);
        if(jsonBridge!=null){
            jsonBridge.onJson(decrypt_text);
        }
        //解密字符串
        return adapter.fromJson(decrypt_text);
    }

    public static abstract class JsonBridge{
        public abstract void onJson(String json);
    }


}
