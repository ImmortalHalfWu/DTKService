package immortal.half.wu.apps.IdleFish.sender;


import immortal.half.wu.apps.IdleFish.beans.IdleFishProductBean;
import immortal.half.wu.apps.IdleFish.sender.actions.*;
import immortal.half.wu.apps.impls.PostedProductNames;
import immortal.half.wu.apps.interfaces.IDevice;

import java.util.Set;

public class IdleFishActionController {

    private final IDevice deviceId;
    private final String packageName;
    private final String mainActivityPath;

    public IdleFishActionController(IDevice deviceId, String packageName, String mainActivityPath) {
        this.deviceId = deviceId;
        this.packageName = packageName;
        this.mainActivityPath = mainActivityPath;

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

                .build(deviceId, e -> System.out.println(e.getMessage()))
//                .run()
        ;

    }

    private IActionException actionException = e -> {
        e.printStackTrace();
        System.out.println(e.getMessage());
    };



    public void postProduct(IdleFishProductBean product) {

        new ActionBuilder()
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
                .build(deviceId, actionException).run();
    }

    public void deleteProduct(String productName) {
        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newGoPostedAction())
                .addAction(PageActionPosted.newRemovePostedProductAction(productName))
                .build(deviceId, actionException).run();
    }

    public void getPostedProductsName(PostedProductNames.CallBack callBack) {
        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newGoPostedAction())
                .addAction(PageActionPosted.newFindPostedProductNamesAction(callBack))
                .build(deviceId, actionException).run();
    }

    public void getUserName(PageActionHomeMy.UserInfoCallBack callBack) {
        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newFindUserInfoAction(callBack))
                .build(deviceId, actionException).run();
    }

    public void refreshPostedProduct(IDevice device) {
        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newGoPostedAction())
                .addAction(PageActionPosted.newRefreshPostedProductAction())
                .build(device, actionException).run();
    }


    public void isLogin(PageActionHomeMy.IsLoginCallBack callBack) {
        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .addAction(PageActionHome.newGoHomeMyAction())
                .addAction(PageActionHomeMy.newIsLogin(callBack))
                .build(deviceId, actionException).run();
    }

    public void toMainActivity() {
        new ActionBuilder()
                .addAction(PageActionHome.newGoHomeAction(packageName, mainActivityPath))
                .build(deviceId, actionException).run();
    }
}
