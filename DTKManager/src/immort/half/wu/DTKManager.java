package immort.half.wu;

import immort.half.wu.beans.params.*;
import immort.half.wu.beans.results.*;
import immort.half.wu.http.MHttpClient;
import immort.half.wu.http.SimpleCallBack;
import immort.half.wu.utils.FinalString;
import immort.half.wu.utils.SignMD5Util;

import java.util.Map;

public class DTKManager {

    private static DTKManager dtkManager;
    private final MHttpClient mHttpClient;

    private final String appSecret;
    private final String appKey;

    public static void init(String appSecret, String appKey) {
        if (dtkManager == null) {
            synchronized (DTKManager.class) {
                dtkManager = new DTKManager(appSecret, appKey);
            }
        }
    }

    private DTKManager(String appSecret, String appKey) {

        mHttpClient = MHttpClient.init(FinalString.URL_BASE);

        this.appSecret = appSecret;
        this.appKey = appKey;

    }

    public static void getProductList(SimpleCallBack<JsonProductListBean> callback, ParamsProductListBean params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_PRODUCT_LIST,
                    createParams(params),
                    callback
                    );
        }
    }

    public static void getProductInfo(SimpleCallBack<JsonProductInfoBean> callback, ParamsProductInfoBean params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_PRODUCT_INFO,
                    createParams(params),
                    callback
            );
        }
    }

    public static void getProductCategorys(SimpleCallBack<JsonProductCategoryBean> callback, ParamsProductCategoryBean params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_PRODUCT_CATEGORY,
                    createParams(params),
                    callback
            );
        }
    }

    public static void getProductRefresh(SimpleCallBack<JsonProductRefreshBean> callback, ParamsProductRefreshBean params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_PRODUCT_REFRESH,
                    createParams(params),
                    callback
            );
        }
    }

    public static void test(SimpleCallBack<Object> callback, Object params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_PRODUCT_SAVE,
                    createParams(params),
                    callback
            );
        }
    }

    public static void getProductForSave(SimpleCallBack<JsonProductSaveBean> callback, ParamsProductSaveBean params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_PRODUCT_SAVE,
                    createParams(params),
                    callback
            );
        }
    }

    public static void getProductFor9(SimpleCallBack<JsonProduct9Bean> callback, ParamsProduct9Bean params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_PRODUCT_9,
                    createParams(params),
                    callback
            );
        }
    }

    public static void getProductForSearch(SimpleCallBack<JsonProductSearchBean> callback, ParamsProductSearchBean params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_PRODUCT_SEARCH,
                    createParams(params),
                    callback
            );
        }
    }

    public static void getUrlConvert(SimpleCallBack<JsonUrlConvertBean> callback, ParamsUrlConveterBean params) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGetWithBaseUrl(
                    FinalString.URL_CONVERT,
                    createParams(params),
                    callback
            );
        }
    }

    public static void getProductForUserKey(SimpleCallBack<JsonProductUserSaveBean> callback, String userAppKey, String pageIndex) {
        if (checkManager()) {
            dtkManager.mHttpClient.sendGet(
                    FinalString.URL_PRODUCT_USER_SAVE + userAppKey + "&v=2&page=" + pageIndex,
                    callback
            );
        }
    }

    private static Map<String, String> createParams(Object params) {
        return SignMD5Util.getSignMap(params, dtkManager.appSecret, dtkManager.appKey);
    }

    private static boolean checkManager() {
        return dtkManager != null &&  dtkManager.appSecret != null && dtkManager.appKey != null;
    }

    public static void main(String[] args) {
        System.out.println("asaaaaaaaaaa");
    }
}
