package immortal.half.wu.apps.interfaces;

public interface IAndroidApp<T> {

    String getDeviceId();
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
