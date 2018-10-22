package com.llx.bear.commen.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

import com.llx.bear.BearReaderApplication;
import com.llx.bear.commen.Constant;

import java.util.Map;

/**
 * ---------SP 文件的操作类--------
 */
public class SPUtils {

    /**
     * 文件名
     */
    private static String FILENAME = Constant.SP_FILE_NAME;

    /**
     * 存入某个key对应的value值
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        SharedPreferences sp = BearReaderApplication.getInstance().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        }
        edit.commit();
    }

    /**
     * 得到某个key对应的值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static Object get(String key, Object defValue) {
        SharedPreferences sp = BearReaderApplication.getInstance().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        if (defValue instanceof String) {
            return sp.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return sp.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return sp.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sp.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 返回所有数据
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = BearReaderApplication.getInstance().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }


    /**
     * 清除所有内容
     */
    public static void clearAll() {
        SharedPreferences sp = BearReaderApplication.getInstance().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        SharedPreferencesCompat.EditorCompat.getInstance().apply(edit);
    }


}

