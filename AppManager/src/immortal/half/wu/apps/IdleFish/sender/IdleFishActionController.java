package immortal.half.wu.apps.IdleFish.sender;


import immortal.half.wu.LogUtil;
import immortal.half.wu.apps.IdleFish.beans.IdleFishProductBean;
import immortal.half.wu.apps.IdleFish.sender.actions.*;
import immortal.half.wu.apps.impls.PostedProductNames;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class IdleFishActionController {
    private static final String TAG = "IdleFishActionControlle";
    private final IDevice deviceId;
    private final String packageName;
    private final String mainActivityPath;

    public IdleFishActionController(IDevice deviceId, String packageName, String mainActivityPath) {
        this.deviceId = deviceId;
        this.packageName = packageName;
        this.mainActivityPath = mainActivityPath;
    }

    public void init() {
        LogUtil.i(TAG, "初始化" + deviceId + "下IdleFishActionController");
        LogUtil.i(TAG, "开始" + deviceId + "下IdleFishActionController界面缓存");
        new ActionBuilder()

                // 发布闲置流程
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))

                .addAction(PageActionHome.newGoPostChoiceAction())

                .addAction(PageActionPostChoice.newGoPostAction())

                .addAction(PageActionImgChoice.newGoImgProcessAction())

                .addAction(PageActionImgProcess.newGoImgTAGAction())

                .addAction(PageActionTagChoice.newTagChoiceAction())

                .addAction(PageActionImgProcess.newGoPostProductInfoAction())

                .addAction(PageActionPostProductInfo.newPostProductInfoAction())

                .addAction(PageActionPostProductInfo.newGoPostProductMoneyAction())

                .addAction(PageActionPostProductMoney.newPostProductMoneyAction())

                .addAction(PageActionPostProductInfo.newGoPostProductOtherAction())

                .addAction(PageActionPostProductOther.newPostProductOtherAction())


                // 获取用户信息 + 已发送商品名称
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))

                .addAction(PageActionHome.newGoHomeMyAction())

                // 获取登录用户名称
                .addAction(PageActionHomeMy.newFindUserInfoAction((name, postedNum) -> System.out.println(postedNum + "__" + name)))

                .addAction(PageActionHomeMy.newGoPostedAction())

                // 获取已发布商品名称
                .addAction(PageActionPosted.newFindPostedProductNamesAction(new PostedProductNames.CallBack() {
                    @Override
                    public void names(Set<String> names) {
                        System.out.println(names);
                    }
                }))

                // 刷新  +  删除商品
//                .addAction(PageActionPosted.newRefreshPostedProductAction())
//                .addAction(PageActionPosted.newRemovePostedProductAction("123"))

                .build(deviceId, new IActionException() {
                    @Override
                    public void fail(Exception e) {
                        LogUtil.e(TAG, "开始" + deviceId + "下IdleFishActionController界面缓存失败", e);
                    }
                })
                .run()
        ;
        LogUtil.i(TAG, deviceId + "下IdleFishActionController界面缓存完成");

    }

    @NotNull
    private IActionException actionException = e -> {
        LogUtil.e(TAG, "", e);
    };


    public void postProduct(@NotNull IdleFishProductBean product) {

        LogUtil.i(TAG, "开始" + deviceId + "下商品发布：" + product);

        ActionBuilder actionBuilder = new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))

                .addAction(PageActionHome.newGoPostChoiceAction())

                .addAction(PageActionPostChoice.newGoPostAction())

                .addAction(PageActionImgChoice.newChoiceImgToProcessAction(product.getImageList().size()));

        product.getImgTag().forEach(s -> actionBuilder
                .addAction(PageActionImgProcess.newGoImgTAGAction())
                .addAction(PageActionTagChoice.newTagChoiceAction(s)));

        actionBuilder.addAction(PageActionImgProcess.newGoPostProductInfoAction())

                .addAction(PageActionPostProductInfo.newPostProductInfoAction(product.getInfo()))

                .addAction(PageActionPostProductInfo.newGoPostProductMoneyAction())

                .addAction(PageActionPostProductMoney.newPostProductMoneyAction(product.getPrice(), product.getOldPrice()))

                .addAction(PageActionPostProductInfo.newGoPostProductOtherAction())

                .addAction(PageActionPostProductOther.newChoiceOtherAction())
        ;
        actionBuilder.build(deviceId, new IActionException() {
            @Override
            public void fail(Exception e) {
                LogUtil.e(TAG, deviceId + "下商品发布失败", e);
            }
        }).run();

        LogUtil.i(TAG, deviceId + "下商品发布结束");
    }

    public void deleteProduct(String productName) {

        LogUtil.i(TAG, "开始" + deviceId + "删除商品：" + productName);

        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newGoPostedAction())
                .addAction(PageActionPosted.newRemovePostedProductAction(productName))
                .build(deviceId, new IActionException() {
                    @Override
                    public void fail(Exception e) {
                        LogUtil.e(TAG, deviceId + "删除商品失败：" + productName, e);
                    }
                }).run();

        LogUtil.i(TAG, deviceId + "删除商品结束");

    }

    public void getPostedProductsName(PostedProductNames.CallBack callBack) {
        LogUtil.i(TAG, "开始" + deviceId + "获取已发布商品");
        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newGoPostedAction())
                .addAction(PageActionPosted.newFindPostedProductNamesAction(callBack))
                .build(deviceId, new IActionException() {
                    @Override
                    public void fail(Exception e) {
                        LogUtil.e(TAG, deviceId + "获取已发布商品失败", e);
                    }
                }).run();
        LogUtil.i(TAG, deviceId + "获取已发布商品结束");

    }

    public void getUserName(PageActionHomeMy.UserInfoCallBack callBack) {
        LogUtil.i(TAG, "开始" + deviceId + "获取App登录用户信息");

        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newFindUserInfoAction(callBack))
                .build(deviceId, new IActionException() {
                    @Override
                    public void fail(Exception e) {
                        LogUtil.e(TAG, deviceId + "获取App登录用户信息失败", e);
                    }
                }).run();
        LogUtil.i(TAG, deviceId + "获取App登录用户信息结束");
    }

    public void refreshPostedProduct(@NotNull IDevice device) {
        LogUtil.i(TAG, "开始" + deviceId + "刷新闲鱼已发布商品");

        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newGoPostedAction())
                .addAction(PageActionPosted.newRefreshPostedProductAction())
                .build(device, new IActionException() {
                    @Override
                    public void fail(Exception e) {
                        LogUtil.e(TAG, deviceId + "刷新闲鱼已发布商品失败", e);
                    }
                }).run();
        LogUtil.i(TAG, "开始" + deviceId + "刷新闲鱼已发布商品完成");

    }


    public void isLogin(PageActionHomeMy.IsLoginCallBack callBack) {
        LogUtil.i(TAG, "开始" + deviceId + "查询是否已登录");

        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newIsLogin(callBack))
                .build(deviceId, new IActionException() {
                    @Override
                    public void fail(Exception e) {
                        LogUtil.e(TAG, "开始" + deviceId + "查询是否已登录失败", e);
                    }
                }).run();
        LogUtil.i(TAG, "开始" + deviceId + "查询是否已登录结束");

    }

    public void toMainActivity() {
        LogUtil.i(TAG, "开始" + deviceId + "启动主页面");

        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .build(deviceId, new IActionException() {
                    @Override
                    public void fail(Exception e) {
                        LogUtil.e(TAG, deviceId + "启动主页面失败", e);
                    }
                }).run();
        LogUtil.i(TAG, "开始" + deviceId + "启动主页面完成");

    }
}
