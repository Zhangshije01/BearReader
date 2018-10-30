package com.llx.bear.commen;

import com.llx.bear.commen.utils.AppUtils;
import com.llx.bear.commen.utils.FileUtils;

/**
 * @author: zhangshijie
 * Time: 2018/10/12 18:13
 */

public class Constant {

    public static String PATH_DATA = FileUtils.createRootPath(AppUtils.getAppContext()) + "/cache";
    public static String PATH_COLLECT = FileUtils.createRootPath(AppUtils.getAppContext()) + "/collect";
    public static String BASE_PATH = AppUtils.getAppContext().getCacheDir().getPath();

    public static String PATH_TXT = PATH_DATA + "/book/";

    public static String PATH_EPUB = PATH_DATA + "/epub";

    public static String PATH_CHM = PATH_DATA + "/chm";
    public static final String SUFFIX_ZIP = ".zip";
    public static final String ISNIGHT = "isNight";


}
