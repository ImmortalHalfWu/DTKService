package immortal.half.wu;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.AndroidAppFactory;
import immortal.half.wu.apps.interfaces.IAndroidApp;

public class AppManager {

    private static AppManager appManager;

    private AppManager() {
        ADBManager.getInstance();
    }

    public static AppManager getInstance() {
        if (appManager == null) {
            synchronized (AppManager.class) {
                if (appManager == null) {
                    appManager = new AppManager();
                }
            }
        }
        return appManager;
    }

    public IAndroidApp createAndroidApp(String deviceID, String appPackageName) {
        return AndroidAppFactory.createAndroidApp(deviceID, appPackageName);
    }

}


/*

闲鱼几大流程

1，开启App： 启动应用  欢迎页     广告页     主页升级提示  主页
2，发布商品： 主页点击发布  发布选择页(选择发布商品)   选择照片    选择标签    商品详情    价格选择（卖价买价包邮）    添加选项（议价，分类，鱼塘）  鱼塘选择页
3，发布免费送： 主页点击发布  发布选择页（选择发布免费）   发布页（标题+描述+照片+价格+分类） 选择照片    价格选择


 */