package com.llx.bear.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Time: 2017/9/9 10:20
 * @author zhangshijie
 */

public class AESGsonConverterFactory extends Converter.Factory {

    private AESGsonResponseBodyConverter.JsonBridge jsonBridge;

    public AESGsonConverterFactory setJsonBridge(AESGsonResponseBodyConverter.JsonBridge jsonBridge) {
        this.jsonBridge = jsonBridge;
        return this;
    }

    public static AESGsonConverterFactory create() {
        return create(new Gson());
    }

    public static AESGsonConverterFactory create(AESGsonResponseBodyConverter.JsonBridge jsonBridge) {
        return create(new Gson(),jsonBridge);
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static AESGsonConverterFactory create(Gson gson) {
        return new AESGsonConverterFactory(gson);
    }

    public static AESGsonConverterFactory create(Gson gson, AESGsonResponseBodyConverter.JsonBridge jsonBridge) {
        return new AESGsonConverterFactory(gson).setJsonBridge(jsonBridge);
    }

    private final Gson gson;

    private AESGsonConverterFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        if (jsonBridge == null) {
            return new AESGsonResponseBodyConverter<>(gson, adapter);
        } else {
            return new AESGsonResponseBodyConverter<>(gson, adapter,jsonBridge);
        }
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new AESGsonRequestBodyConverter<>(gson, adapter);
    }


}
