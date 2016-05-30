package com.blanke.xgank.config;

/**
 * Created by blanke on 16-5-27.
 */
public class ProjectConfig {
    private final static String BASE_URL = "http://gank.io/";
    private final static String BASE_API_URL = BASE_URL + "api/data/";
    private static String TEST_BASE_API_URL = null;
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static boolean isTestJunit = false;//测试是否在Junit
    private static boolean isTestApp = false;//测试是否是真机APP
    private static boolean isDebug = false;
    private static String[] type = {"福利", "Android", "iOS", "休息视频", "拓展资源", "前端"};

    public static boolean isTest() {
        return isTestApp || isTestApp;
    }

    public static boolean isTestApp() {
        return isTestApp;
    }

    public static void setIsTestApp(boolean isTestApp) {
        ProjectConfig.isTestApp = isTestApp;
        ProjectConfig.isTestJunit = !isTestApp;
    }

    public static String[] getType() {
        return type;
    }

    public static boolean isTestJunit() {
        return isTestJunit;
    }

    public static void setIsTestJunit(boolean isTestJunit) {
        ProjectConfig.isTestJunit = isTestJunit;
        ProjectConfig.isTestApp = !isTestJunit;
    }

    public static String getTestBaseApiUrl() {
        return TEST_BASE_API_URL;
    }

    public static void setTestBaseApiUrl(String testBaseApiUrl) {
        TEST_BASE_API_URL = testBaseApiUrl;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        ProjectConfig.isDebug = isDebug;
    }

    public static String getBaseApiUrl() {
        return isTest() ? TEST_BASE_API_URL : BASE_API_URL;
    }
}
