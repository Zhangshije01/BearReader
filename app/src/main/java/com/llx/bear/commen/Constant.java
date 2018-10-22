package com.llx.bear.commen;

import java.util.HashMap;

/**
 * @author: zhangshijie
 * Time: 2018/10/12 18:13
 */

public class Constant {
    public static final String SP_FILE_NAME = "bear_reader";
    // 配置的key列表
    public static final class CONFIG_KEY {
        public static final String USER_INFO_KEY = "user_info_key";   // 用户信息的key
        public static final String AUTH_TOKEN_KEY = "auto_token_key"; // auth token的key
        public static final String DEVICE_ID = "device_id";
    }
    // 网络所需要的参数
    public static final class NET_WORK_PAMAS {
        public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChLkDpMdtPigPOI3QI6vjlgkBSl3Q7wjLb3POBliVM0olJ2HtPaut+6pK76nvva4w4UYMj/hE+7VJDcwJdePDu6nTmxn6iSsUq3J6rDHLTmehiZnffDTWvpGvv9boqv7ItCf3mVw7EY4E05wfo2/ZcVuVxc7ZHIMP7ko0HVddvFwIDAQAB";// 加密的公钥
    }
    public static final class NET_WORK_TOKEN_PARAMS {
        // 请求token的参数
        public static HashMap<String, String> TOKEN_PAMAS_MAP = new HashMap<String, String>();

        static {
            TOKEN_PAMAS_MAP.put("client_id", "216941805343761");
            TOKEN_PAMAS_MAP.put("client_secret", "bd4078d018d8fbf11dd37b68251720eb");
            TOKEN_PAMAS_MAP.put("grant_type", "client_credentials");
            TOKEN_PAMAS_MAP.put("response_type", "token");

        }
    }
}
