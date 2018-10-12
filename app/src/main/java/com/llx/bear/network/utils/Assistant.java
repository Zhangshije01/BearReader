package com.llx.bear.network.utils;

import android.text.TextUtils;

import com.llx.bear.commen.Constant;


public class Assistant {

    private static boolean _RSA = (!TextUtils.isEmpty(Constant.NET_WORK_PAMAS.RSA_PUBLIC_KEY) && !TextUtils.isEmpty(Constant.NET_WORK_PAMAS.RSA_PUBLIC_KEY));

    private static String key = "bixushi16weimima";// 加解密 秘钥

    /**
     * 装箱（将app客户端的数据发送给服务器需要现将app客户端的数据进行装箱）
     *
     * @param json
     * @return
     */
    public static String Boxing(String json) {
        if (!TextUtils.isEmpty(json)) {
            if (_RSA) {
                try {
                    json = AESCryptoHelper.encrypt(json, key);
                } catch (Exception e) {
                    e.printStackTrace();
                    json = null;
                }
            }
        }
        return json;
    }

    /**
     * 拆箱（接收从服务器返回的数据需要需要先将接受到的数据进行拆箱）
     *
     * @param json
     * @return
     */
    public static String UnBoxing(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                json = AESCryptoHelper.decrypt(json, key);
            } catch (Exception e) {
                e.printStackTrace();
                json = null;
            }
        }
        return json;
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    private static String customEncrypt(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    public static String getBoxing(String s) {
//        return s;
        return Boxing(s);
    }
}