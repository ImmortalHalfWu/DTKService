package immortal.half.wu.apps;

public class SimpleAndroidApp extends BaseAndroidApp<Object> {

    private static SimpleAndroidApp simpleAndroidApp;

    public static SimpleAndroidApp getInstance() {
        if (simpleAndroidApp == null) {
            synchronized (SimpleAndroidApp.class) {
                if (simpleAndroidApp == null) {
                    simpleAndroidApp = new SimpleAndroidApp();
                }
            }
        }
        return simpleAndroidApp;
    }

    private SimpleAndroidApp() {
        super(SimpleAndroidApp.class.getName(), SimpleAndroidApp.class.getName(), SimpleAndroidApp.class.getName());
    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void postProduct(Object product) {

    }

    @Override
    public String[] getPostedProductsName() {
        return new String[0];
    }

    @Override
    public String getUserName() {
        return "";
    }

    @Override
    public void refreshPostedProduct() {

    }
}
