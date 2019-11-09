package immortal.half.wu.apps.interfaces;

import immortal.half.wu.apps.IdleFish.sender.actions.PageActionHomeMy;
import immortal.half.wu.apps.impls.PostedProductNames;
import org.jetbrains.annotations.NotNull;

public interface IAndroidApp<T> {

    String getDeviceId();

    @NotNull IDevice getDevice();

    String getPackageName();

    String getMainActivityPath();

    void refreshConnect();

    boolean startApp();

    boolean toMainActivity();

    void isLogin(PageActionHomeMy.IsLoginCallBack callBack);

    void postProduct(T product);

    void deleteProduct(String name);

    void getPostedProductsName(PostedProductNames.CallBack callBack);

    void getUserName(PageActionHomeMy.UserInfoCallBack callBack);

    void refreshPostedProduct();


}
