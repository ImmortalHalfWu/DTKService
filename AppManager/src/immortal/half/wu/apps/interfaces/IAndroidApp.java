package immortal.half.wu.apps.interfaces;

public interface IAndroidApp<T> {

    String getDeviceId();
    String getPackageName();
    String getMainActivityPath();
    String getShowingActivity();
    void startApp();
    boolean isLogin();
    void postProduct(T product);
    String[] getPostedProductsName();
    String getUserName();


}
