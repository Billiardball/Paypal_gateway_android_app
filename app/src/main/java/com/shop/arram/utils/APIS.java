package com.shop.arram.utils;


import com.ciyashop.library.apicall.URLS;

public class APIS {


// ==========================================================================

    //TODO:Copy and Paste URL and Key Below from Admin Panel.

    public final String APP_URL = "https://arram.de/";
    public final String WOO_MAIN_URL = APP_URL + "wp-json/wc/v2/";
    public final String MAIN_URL = APP_URL + "wp-json/pgs-woo-api/v1/";

    public static final String CONSUMERKEY = "kYvZuCB6MVeg";
    public static final String CONSUMERSECRET = "wBkHcY311812bFx8Za6JmPew75DVC6pV1bByrYFTxnAnJBrB";
    public static final String OAUTH_TOKEN = "9682jgjMxSV4fY3mF5i6AwYp";
    public static final String OAUTH_TOKEN_SECRET = "bPPJSUMyrRrLAtKjRg01yiP3jVMXPpjTCcyr4TTVQjMki28G";

    public static final String WOOCONSUMERKEY = "ck_dc6e46c6f25a5c737ee313930e2c3e6ab4dd8ef1";
    public static final String WOOCONSUMERSECRET = "cs_1ae4ddbe59aa4db5427bce36d20ef842f35df7f5";
    public static final String version="3.1.5";

    //================================================================================
//
    public APIS() {
        URLS.APP_URL = APP_URL;
        URLS.WOO_MAIN_URL = WOO_MAIN_URL;
        URLS.MAIN_URL = MAIN_URL;
        URLS.version = version;
        URLS.CONSUMERKEY = CONSUMERKEY;
        URLS.CONSUMERSECRET = CONSUMERSECRET;
        URLS.OAUTH_TOKEN = OAUTH_TOKEN;
        URLS.OAUTH_TOKEN_SECRET = OAUTH_TOKEN_SECRET;
        URLS.WOOCONSUMERKEY = WOOCONSUMERKEY;
        URLS.WOOCONSUMERSECRET = WOOCONSUMERSECRET;
    }
}
