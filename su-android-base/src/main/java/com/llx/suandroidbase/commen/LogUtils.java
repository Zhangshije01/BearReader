package com.llx.suandroidbase.commen;

import com.orhanobut.logger.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 * LogUtils
 * Created by maodeqiang on 2015/7/13.
 */
public class LogUtils {

    /**
     * 日志输出级别NONE
     */
    public static final int LEVEL_NONE = 0;
    /**
     * 日志输出级别V
     */
    public static final int LEVEL_VERBOSE = 1;
    /**
     * 日志输出级别D
     */
    public static final int LEVEL_DEBUG = 2;
    /**
     * 日志输出级别I
     */
    public static final int LEVEL_INFO = 3;
    /**
     * 日志输出级别W
     */
    public static final int LEVEL_WARN = 4;
    /**
     * 日志输出级别E
     */
    public static final int LEVEL_ERROR = 5;
    private static final String TAG = "LogUtils";

    /**
     * 日志输出时的TAG
     */
    private static String mTag = "=====MAODQ=====";
    /**
     * 是否允许输出log
     */
    private static int mDebuggable = 5;

    /**
     * 用于记时的变量
     */
    private static long mTimestamp = 0;
    /**
     * 写文件的锁对象
     */
    private static final Object mLogLock = new Object();

    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void v(String tag, String msg) {
        if (mDebuggable >= LEVEL_VERBOSE) {
            Logger.t(tag).v(msg);
        }
    }


    public static void d(String tag, String method, String msg) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Logger.t(tag).d(msg);
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void d(String tag, String msg) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Logger.t(tag).d(msg);
        }
    }

    /**
     * 以级别为 d 的形式输出异常
     */
    public static void d(Throwable t) {
        d("errTag", t);
    }

    /**
     * 以级别为 d 的形式输出异常
     */
    public static void d(String tag, Throwable t) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Logger.t(tag).d(getCrashInfo(t));
        }
    }

    /**
     * 显示当前运行代码的调用来源
     * 非常耗性能，仅调试使用，尽量不要在循环内部使用
     */
    public static void d(String tag) {//显示当前运行代码的调用来源
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String info = "";
        //for (StackTraceElement s : stackTrace) {
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement s = stackTrace[i];
            info += ("at：" + s.getClassName() + "." + s.getMethodName() + " (" + s.getFileName() + ":" + s.getLineNumber() + ")\n");
            /*info += ("类名：" + s.getClassName() + "  ,  java文件名：" + s.getFileName() + ",  当前方法名字：" + s.getMethodName() + ""
                    + " , 当前代码是第几行：" + s.getLineNumber() + ", ");*/
        }
        if (mDebuggable >= LEVEL_DEBUG) {
            Logger.t(tag).d(info);
        }
    }

    public static void printException(Throwable t) {
        printException("mytag", t);
    }

    public static void printException(String tag, Throwable t) {
        LogUtils.d(tag, getCrashInfo(t));
    }

    public static void json(String tag, String json) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Logger.t(tag).json(json);
        }
    }

    public static void showJson(String json){
        d("jsontag",json);
    }

    /**
     * 以级别为 i 的形式输出LOG
     */
    public static void i(String msg) {
        i("no tag", msg);
    }

    public static void i(String tag, String msg) {
        if (mDebuggable >= LEVEL_INFO) {
            Logger.t(tag).i(msg);
        }
    }


    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    public static void e(String tag, Throwable throwable) {
        if (mDebuggable >= LEVEL_ERROR) {
            if (throwable == null) {
                e(TAG, "参数为空,tag: " + tag);
                return;
            }
            Logger.t(tag).e(throwable.toString());
        }
    }

    public static void e(String tag, String msg) {
        Logger.t(tag).e(msg);
    }

    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void e(String tag, String method, String msg) {
        Logger.t(tag).e(method + ": " + msg);
    }

    /**
     * 把Log存储到文件中
     *
     * @param log  需要存储的日志
     * @param path 存储路径
     */
    /*public static void log2File(String log, String path) {
        log2File(log, path, true);
    }

    public static void log2File(String log, String path, boolean append) {
        synchronized (mLogLock) {
            FileUtil.writeFile(log + "\r\n", path, append);
        }
    }*/

    /**
     * 获取异常的详细信息
     *
     * @param ex
     * @return
     */
    public static String getCrashInfo(Throwable ex) {
        if(ex==null){
            return "";
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.setStackTrace(ex.getStackTrace());
        ex.printStackTrace(printWriter);
        return writer.toString();
    }
}
