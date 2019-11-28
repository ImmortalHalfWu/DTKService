package cn;

import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.executor.ExecutorManager;
import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import immotal.half.wu.appManager.AppFactory;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.beans.IdleFishProductBean;
import immotal.half.wu.appManager.controls.JobPagerControl;
import immotal.half.wu.appManager.interfaces.IApp;
import immotal.half.wu.appManager.interfaces.IAppCallBack;
import immotal.half.wu.appManager.pagers.IPagerFactory;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.UserInfoBean;
import immotal.half.wu.appManager.pagers.impls.DefaultPager;
import immotal.half.wu.appManager.pagers.processs.idlefish.*;
import immotal.half.wu.appManager.pagers.processs.idlefish.posted.PostedPageProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.posted.deletes.PostedDeleteOkPageProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.posted.deletes.PostedDeleteSelectPageProcess;

import java.io.File;
import java.util.ArrayList;

public class App2 {

    private final static String IDLE_FISH_PACKAGE_NAME = "com.taobao.idlefish";
    private final static String IDLE_FISH_MAIN_PATH = "com.taobao.fleamarket.home.activity.MainActivity";
    private final static String IDLE_FISH_POSTED_PATH = ".webview.WeexWebViewActivity";
    private final static String IDLE_FISH_POSTED_CHOICE = ".post.activity.publishEntry.PublishEntryActivity";
    private final static String IDLE_FISH_POSTED_CHOICE_IMG = ".mms.activitys.MultiMediaStudioActivity";
    private final static String IDLE_FISH_POSTED_IMG_PROCESS = ".mms.activitys.MultiMediaEditorActivity";
    private final static String IDLE_FISH_POSTED_IMG_TAG = "com.idlefish.flutterbridge.flutterboost.FishFlutterActivity";
    private final static String IDLE_FISH_POSTED_PRODUCT_INFO = "com.idlefish.flutterbridge.flutterboost.FishFlutterActivity";

    // 设备信息
    static String deviceId = "5ENDU19214004179";
    static DeviceInfoBean deviceInfoBean = new DeviceInfoBean(deviceId, ADBManager.getInstance().getDxSize(deviceId));

    public static void test() {

        IApp<IdleFishProductBean, UserInfoBean> fishIdleApp = AppFactory.createFishIdleApp(deviceId, ExecutorManager.createTimeOutExecutorService("App2.test"));
        ArrayList<File> files = new ArrayList<>(9);
        files.add(new File("123"));files.add(new File("123"));files.add(new File("123"));files.add(new File("123"));files.add(new File("123"));files.add(new File("123"));files.add(new File("123"));files.add(new File("123"));files.add(new File("123"));
        ArrayList<String> tags = new ArrayList<>(4);
        tags.add("tag1");tags.add("tag2");tags.add("tag3");tags.add("tag4");

        IdleFishProductBean idleFishProductBean = new IdleFishProductBean(
                "title", "info",
                files, tags, "12.4", "50.11"
        );

        fishIdleApp.postProduct(idleFishProductBean, new IAppCallBack<Boolean>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onComplete(Boolean result) {

            }
        });
        fishIdleApp.startApp(new IAppCallBack<Boolean>() {
            @Override
            public void onError(Exception e) {
                LogUtil.i(AppManagerUtil.TAG, e.getMessage());
            }

            @Override
            public void onComplete(Boolean result) {
                LogUtil.i(AppManagerUtil.TAG, result);
            }
        });

        fishIdleApp.isLogin(new IAppCallBack<Boolean>() {
            @Override
            public void onError(Exception e) {
                LogUtil.i(AppManagerUtil.TAG, e.getMessage());
            }

            @Override
            public void onComplete(Boolean result) {
                LogUtil.i(AppManagerUtil.TAG, result);
            }
        });

        fishIdleApp.getUserName(new IAppCallBack<UserInfoBean>() {
            @Override
            public void onError(Exception e) {
                LogUtil.i(AppManagerUtil.TAG, e.getMessage());
            }

            @Override
            public void onComplete(UserInfoBean result) {
                LogUtil.i(AppManagerUtil.TAG, result);
            }
        });

        fishIdleApp.getPostedProductsName(new IAppCallBack<ArrayList<String>>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onComplete(ArrayList<String> result) {
                LogUtil.i(AppManagerUtil.TAG, result);
            }
        });

        fishIdleApp.refreshPostedProduct(new IAppCallBack<Boolean>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onComplete(Boolean result) {

            }
        });

        fishIdleApp.deleteProduct("google开发者大会入场牌", new IAppCallBack<Boolean>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onComplete(Boolean result) {

            }
        });




//        MyPager();
//        isLogin();
//        postProduct();

    }

    private static void isLogin() {
        DefaultPager<Boolean> 去主页面 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "去主页面",
                new StartMainPageProcess(IDLE_FISH_PACKAGE_NAME, IDLE_FISH_MAIN_PATH));

        DefaultPager<Boolean> 取消升级 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "取消升级",
                new RemoveUpdatePageProcess());

        DefaultPager<Boolean> 前往首页我的 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "前往首页我的",
                MainPageProcess.createToMyPageProcess());

        DefaultPager<Boolean> 是否登录fishIdle = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "是否登录fishIdle",
                MyPagerProcess.createGetIsLoginPageProcess()
        );

        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(去主页面);
        control.addPager(取消升级);
        control.addPager(前往首页我的);
        control.addPager(是否登录fishIdle);

        // 运行在超时检测线程池
        ITimeOutExecutorService timeOutExecutorService = ExecutorManager.createTimeOutExecutorService(deviceId);
        timeOutExecutorService.executeTimeOut(
                control,
                new IJobListener<Boolean>() {
                    @Override
                    public void onError(Exception e) {
                        System.out.println("运行异常1" + e.toString());
                    }

                    @Override
                    public void onComplete(Boolean result) {
                        System.out.println("运行结束1" + result);
                    }
                }
        );
    }

    private static void postProduct() {

        DefaultPager<Boolean> 去主页面 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "去主页面",
                new StartMainPageProcess(IDLE_FISH_PACKAGE_NAME, IDLE_FISH_MAIN_PATH));

        DefaultPager<Boolean> 取消升级 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "取消升级",
                new RemoveUpdatePageProcess());

        DefaultPager<Boolean> 前往发布类型选择页 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "前往发布类型选择页",
                MainPageProcess.createToPostPageProcess());

        DefaultPager<Boolean> 前往发布闲置 = IPagerFactory.create(
                IDLE_FISH_POSTED_CHOICE,
                "前往发布闲置",
                PostedChoicePagerProcess.createToPost());

        DefaultPager<Boolean> 发布闲置图片选择 = IPagerFactory.create(
                IDLE_FISH_POSTED_CHOICE_IMG,
                "发布闲置图片选择",
                ImagePagerProcess.createChoiceImgPageProcess(9));

        DefaultPager<Boolean> 前往图片编辑页 = IPagerFactory.create(
                IDLE_FISH_POSTED_CHOICE_IMG,
                "前往图片编辑页",
                ImagePagerProcess.createToImgPageProcess());

        DefaultPager<Boolean> 前往图片标签选择页 = IPagerFactory.create(
                IDLE_FISH_POSTED_IMG_PROCESS,
                "前往图片标签选择页",
                ImageControlPageProcess.createToImageTag());

        DefaultPager<Boolean> 输入商品tag = IPagerFactory.create(
                IDLE_FISH_POSTED_IMG_TAG,
                "输入商品tag",
                ImageTagPageProcess.createInputTag("tag"));

        DefaultPager<Boolean> 输入商品tag2 = IPagerFactory.create(
                IDLE_FISH_POSTED_IMG_TAG,
                "输入商品tag",
                ImageTagPageProcess.createInputTag("tag2"));

        DefaultPager<Boolean> 选择第一个imgTAG = IPagerFactory.create(
                IDLE_FISH_POSTED_IMG_TAG,
                "选择第一个img TAG",
                ImageTagPageProcess.createChoiceTag("tag2"));

        DefaultPager<Boolean> 前往发布商品信息页 = IPagerFactory.create(
                IDLE_FISH_POSTED_IMG_PROCESS,
                "前往发布商品信息页",
                ImageControlPageProcess.createToProductInfo());

        DefaultPager<Boolean> 商品信息页输入商品信息 = IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "商品信息页输入商品信息",
                ProductInfoPagerProcess.createInputInfo("123"));

        DefaultPager<Boolean> 商品信息页前往金额设置 = IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "商品信息页前往金额设置",
                ProductInfoPagerProcess.createGoProductMoney());

        DefaultPager<Boolean> 商品信息页设置包邮 = IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "商品信息页设置包邮",
                ProductMoneyPagerProcess.createClickMill());

        DefaultPager<Boolean> 商品信息页设置入手价 = IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "商品信息页设置入手价",
                ProductMoneyPagerProcess.createClickBuyMoney());

        DefaultPager<Boolean> 商品信息页设置一口价 = IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "商品信息页设置一口价",
                ProductMoneyPagerProcess.createClickSellMoney());

        DefaultPager<Boolean> 商品信息页完成金额设置 = IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "商品信息页完成金额设置",
                ProductMoneyPagerProcess.createClickSuccess());

        DefaultPager<Boolean> 商品信息页前往其他选项 = IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "商品信息页前往其他选项",
                ProductInfoPagerProcess.createGoProductOther());

        DefaultPager<Boolean> 商品信息页设置其他选项 = IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "商品信息页设置其他选项",
                new PostProductOtherPagerProcess());



        // 添加入页面控制器
        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(去主页面);
        control.addPager(取消升级);
        control.addPager(前往发布类型选择页);
        control.addPager(前往发布闲置);
        control.addPager(发布闲置图片选择);
        control.addPager(前往图片编辑页);
        control.addPager(前往图片标签选择页);
        control.addPager(输入商品tag);
        control.addPager(选择第一个imgTAG);
        control.addPager(前往图片标签选择页);
        control.addPager(输入商品tag2);
        control.addPager(选择第一个imgTAG);
        control.addPager(前往发布商品信息页);
        control.addPager(商品信息页输入商品信息);
        control.addPager(商品信息页前往金额设置);
        control.addPager(商品信息页设置包邮);
        control.addPager(商品信息页设置入手价);
        control.addPager(IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "调用键盘输入价格",
                PostProductNumKeyProcess.create("13.2")));
        control.addPager(商品信息页设置一口价);
        control.addPager(IPagerFactory.create(
                IDLE_FISH_POSTED_PRODUCT_INFO,
                "调用键盘输入价格",
                PostProductNumKeyProcess.create("113.2")));
        control.addPager(商品信息页完成金额设置);
        control.addPager(商品信息页前往其他选项);
        control.addPager(商品信息页设置其他选项);



        // 运行在超时检测线程池
        ITimeOutExecutorService timeOutExecutorService = ExecutorManager.createTimeOutExecutorService(deviceId);
        timeOutExecutorService.executeTimeOut(
                control,
                new IJobListener<Boolean>() {
                    @Override
                    public void onError(Exception e) {
                        System.out.println("运行异常1" + e.toString());
                    }

                    @Override
                    public void onComplete(Boolean result) {
                        System.out.println("运行结束1" + result);
                    }
                }
        );
        timeOutExecutorService.executeTimeOut(
                control,
                new IJobListener<Boolean>() {
                    @Override
                    public void onError(Exception e) {
                        System.out.println("运行异常2" + e.toString());
                    }

                    @Override
                    public void onComplete(Boolean result) {
                        System.out.println("运行结束2" + result);
                        ADBManager.getInstance().closeApp(deviceId, IDLE_FISH_PACKAGE_NAME);
                    }
                }
        );
    }

    private static void MyPager() {

        DefaultPager<Boolean> 去主页面 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "去主页面",
                new StartMainPageProcess(IDLE_FISH_PACKAGE_NAME, IDLE_FISH_MAIN_PATH));

        DefaultPager<Boolean> 取消升级 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "取消升级",
                new RemoveUpdatePageProcess());

        DefaultPager<Boolean> 前往发布类型选择页 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "前往发布类型选择页",
                MainPageProcess.createToPostPageProcess());

        DefaultPager<Boolean> 前往首页我的 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "前往首页我的",
                MainPageProcess.createToMyPageProcess());

        DefaultPager<UserInfoBean> 获取登录信息 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "获取登录信息",
                MyPagerProcess.createGetUserInfoPageProcess()
        );

        DefaultPager<Boolean> 前往已发布页 = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "前往已发布页",
                MyPagerProcess.createToPostedPageProcess()
        );

        DefaultPager<ArrayList<String>> 获取已发布商品 = IPagerFactory.create(
                IDLE_FISH_POSTED_PATH,
                "获取已发布商品",
                PostedPageProcess.createGetPostedNamesPageProcess()
        );

        DefaultPager<Boolean> 刷新所有商品 = IPagerFactory.create(
                IDLE_FISH_POSTED_PATH,
                "刷新所有商品",
                PostedPageProcess.createRefreshProductPageProcess()
        );

        DefaultPager<Boolean> 选择删除指定商品 = IPagerFactory.create(
                IDLE_FISH_POSTED_PATH,
                "选择删除指定商品",
                PostedPageProcess.createDeleteProductPageProcess("积木玩具猫")
        );

        DefaultPager<Boolean> 进入更多列表 = IPagerFactory.create(
                IDLE_FISH_POSTED_PATH,
                "进入更多列表",
                new PostedDeleteSelectPageProcess()
        );

        DefaultPager<Boolean> 确定删除商品 = IPagerFactory.create(
                IDLE_FISH_POSTED_PATH,
                "确定删除商品",
                new PostedDeleteOkPageProcess()
        );





        // 添加入页面控制器
        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(去主页面);
        control.addPager(取消升级);
//        control.addPager(startPostChoice);
        control.addPager(前往首页我的);
        control.addPager(获取登录信息);
        control.addPager(前往已发布页);
        control.addPager(获取已发布商品);
        control.addPager(刷新所有商品);
        control.addPager(选择删除指定商品);
        control.addPager(进入更多列表);
        control.addPager(确定删除商品);

        // 运行在超时检测线程池
        ITimeOutExecutorService timeOutExecutorService = ExecutorManager.createTimeOutExecutorService(deviceId);
        timeOutExecutorService.executeTimeOut(
                control,
                new IJobListener<Boolean>() {
                    @Override
                    public void onError(Exception e) {
                        System.out.println("运行异常" + e.toString());
                    }

                    @Override
                    public void onComplete(Boolean result) {
                        System.out.println("运行结束" + result);
                    }
                }
        );
    }

}
