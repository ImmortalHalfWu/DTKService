package immortal.half.wu.apps.interfaces;

public interface IAndroidApp<T> {

    String getDeviceId();
    IDevice getDevice();
    String getPackageName();
    String getMainActivityPath();
    boolean startApp();
    boolean toMainActivity();
    boolean isLogin();
    void postProduct(T product);
    String[] getPostedProductsName();
    String getUserName();
    void refreshPostedProduct();


}
