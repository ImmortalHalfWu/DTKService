package cn;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.executor.ExecutorManager;
import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import immotal.half.wu.appManager.controls.JobPagerControl;
import immotal.half.wu.appManager.pagers.IPagerFactory;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.UserInfoBean;
import immotal.half.wu.appManager.pagers.impls.DefaultPager;
import immotal.half.wu.appManager.pagers.processs.idlefish.MainPageProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.MyPagerProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.RemoveUpdatePageProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.StartMainPageProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.posted.PostedPageProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.posted.deletes.PostedDeleteOkPageProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.posted.deletes.PostedDeleteSelectPageProcess;

import java.util.ArrayList;

public class App2 {

    private final static String IDLE_FISH_PACKAGE_NAME = "com.taobao.idlefish";
    private final static String IDLE_FISH_MAIN_PATH = "com.taobao.fleamarket.home.activity.MainActivity";
    private final static String IDLE_FISH_POSTED_PATH = ".webview.WeexWebViewActivity";

    public static void test() {

        // 设备信息
        String deviceId = "5ENDU19214004179";
        DeviceInfoBean deviceInfoBean = new DeviceInfoBean(deviceId, ADBManager.getInstance().getDxSize(deviceId));


//        // 页面信息及描述
//        PagerInfoBean 主页面 = new PagerInfoBean(
//                IDLE_FISH_MAIN_PATH,
//                "去主页面"
//        );
//
//        // 页面处理逻辑
//        StartMainPageProcess pageProcessStartMainActivity = new StartMainPageProcess(IDLE_FISH_PACKAGE_NAME, IDLE_FISH_MAIN_PATH);
//
//
//        // 页面对象由页面信息及处理逻辑组成
//        DefaultPager<Boolean> booleanBasePager = new DefaultPager<>(
//                主页面,
//                pageProcessStartMainActivity
//        );

        DefaultPager<Boolean> startMain = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "去主页面",
                new StartMainPageProcess(IDLE_FISH_PACKAGE_NAME, IDLE_FISH_MAIN_PATH));

        DefaultPager<Boolean> removeUpdate = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "取消升级",
                new RemoveUpdatePageProcess());

        DefaultPager<Boolean> startPostChoice = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "前往发布类型选择页",
                MainPageProcess.createToPostPageProcess());

        DefaultPager<Boolean> startMainMy = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "前往首页我的",
                MainPageProcess.createToMyPageProcess());

        DefaultPager<UserInfoBean> getUserInfo = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "获取登录信息",
                MyPagerProcess.createGetUserInfoPageProcess()
        );

        DefaultPager<Boolean> startPosted = IPagerFactory.create(
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
        control.addPager(startMain);
        control.addPager(removeUpdate);
//        control.addPager(startPostChoice);
        control.addPager(startMainMy);
        control.addPager(getUserInfo);
        control.addPager(startPosted);
//        control.addPager(获取已发布商品);
        control.addPager(刷新所有商品);
        control.addPager(选择删除指定商品);
        control.addPager(进入更多列表);
        control.addPager(确定删除商品);

        // 运行在超时检测线程池
        ITimeOutExecutorService timeOutExecutorService = ExecutorManager.createTimeOutExecutorService(deviceId);
        timeOutExecutorService.executeTimeOut60s(
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
