package cn;

import immort.half.wu.DTKManager;
import immort.half.wu.beans.params.builds.*;
import immort.half.wu.beans.results.*;
import immort.half.wu.http.SimpleCallBack;
import immort.half.wu.utils.JsonUtil;
import okhttp3.Call;

import java.io.IOException;

public class DTKTest {


    public static void main(String[] args) {
        DTKManager.init(Util.Key, Util.Value);

//        DTKManager.getProductList(new SimpleCallBack<JsonProductListBean>() {
//            @Override
//            public void onFail(okhttp3.Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, JsonProductListBean resultBean, String resultString) throws IOException {
//                System.out.println(resultString);
//                System.out.println(JsonUtil.toJson(resultBean));
//
//            }
//        }, new ParamsProductListBeanBuilder().createParamsProductListBean("1"));
//
//
//        DTKManager.getProductInfo(new SimpleCallBack<JsonProductInfoBean>() {
//            @Override
//            public void onResponse(Call call, JsonProductInfoBean resultBean, String resultString) throws IOException {
//                System.out.println(resultString);
//                System.out.println(JsonUtil.toJson(resultBean));
//            }
//
//            @Override
//            public void onFail(Call call, IOException e) {
//
//            }
//        }, new ParamsProductInfoBeanBuilder().setId("22645522").createParamsProductInfoBean());


//        DTKManager.getProductCategorys(new SimpleCallBack<JsonProductCategoryBean>() {
//            @Override
//            public void onResponse(Call call, JsonProductCategoryBean resultBean, String resultString) throws IOException {
//                System.out.println(resultString);
//                System.out.println(JsonUtil.toJson(resultBean));
//            }
//
//            @Override
//            public void onFail(Call call, IOException e) {
//
//            }
//        }, new ParamsProductCategoryBeanBuilder().createParamsProductCategoryBean());


//        DTKManager.getProductForSave(new SimpleCallBack<JsonProductSaveBean>() {
//            @Override
//            public void onResponse(Call call, JsonProductSaveBean resultBean, String resultString) throws IOException {
//                System.out.println(resultString);
//                System.out.println(JsonUtil.toJson(resultBean));
//            }
//
//            @Override
//            public void onFail(Call call, IOException e) {
//
//            }
//        }, new ParamsProductSaveBeanBuilder().createParamsProductSaveBean("1"));
//
//
//        DTKManager.test(new SimpleCallBack<Object>() {
//            @Override
//            public void onResponse(Call call, Object resultBean, String resultString) throws IOException {
//                System.out.println(resultString);
//            }
//
//            @Override
//            public void onFail(Call call, IOException e) {
//
//            }
//        }, new ParamsProductRefreshBeanBuilder().createParamsProductRefreshBean(10, 1));
//
//
//        DTKManager.getProductRefresh(new SimpleCallBack<JsonProductRefreshBean>() {
//            @Override
//            public void onResponse(Call call, JsonProductRefreshBean resultBean, String resultString) throws IOException {
//                System.out.println(resultString);
//                System.out.println(JsonUtil.toJson(resultBean));
//            }
//
//            @Override
//            public void onFail(Call call, IOException e) {
//
//            }
//        }, new ParamsProductRefreshBeanBuilder().createParamsProductRefreshBean(10, 1));


        DTKManager.getProductFor9(new SimpleCallBack<JsonProduct9Bean>() {
            @Override
            public void onResponse(Call call, JsonProduct9Bean resultBean, String resultString) throws IOException {
                System.out.println(resultString);
                System.out.println(JsonUtil.toJson(resultBean));
            }

            @Override
            public void onFail(Call call, IOException e) {

            }
        }, new ParamsProduct9BeanBuilder().createParamsProduct9Bean("100", "2", "2"));


//        DTKManager.getProductForSearch(new SimpleCallBack<JsonProductSearchBean>() {
//            @Override
//            public void onResponse(Call call, JsonProductSearchBean resultBean, String resultString) throws IOException {
//                System.out.println(resultString);
//                System.out.println(JsonUtil.toJson(resultBean));
//            }
//
//            @Override
//            public void onFail(Call call, IOException e) {
//
//            }
//        }, new ParamsProductSearchBeanBuilder().createParamsProductSearchBean(0, 1, 20, "1231"));


//        DTKManager.getUrlConvert(new SimpleCallBack<JsonUrlConvertBean>() {
//            @Override
//            public void onResponse(Call call, JsonUrlConvertBean resultBean, String resultString) throws IOException {
//                System.out.println(resultString);
//                System.out.println(JsonUtil.toJson(resultBean));
//            }
//
//            @Override
//            public void onFail(Call call, IOException e) {
//
//            }
//        }, new ParamsUrlConveterBeanBuilder().createParamsUrlConveterBean("577326998857"));
//
    }


}