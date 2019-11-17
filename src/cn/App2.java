package cn;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.executor.ExecutorManager;
import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import immotal.half.wu.appManager.controls.JobPagerControl;
import immotal.half.wu.appManager.pagers.IPagerFactory;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.impls.DefaultPager;
import immotal.half.wu.appManager.pagers.processs.idlefish.RemoveUpdatePageProcess;
import immotal.half.wu.appManager.pagers.processs.idlefish.StartMainPageProcess;

public class App2 {

    public final static String IDLE_FISH_PACKAGE_NAME = "com.taobao.idlefish";
    private final static String IDLE_FISH_MAIN_PATH = "com.taobao.fleamarket.home.activity.MainActivity";

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

        DefaultPager<Boolean> removeUpdata = IPagerFactory.create(
                IDLE_FISH_MAIN_PATH,
                "取消升级",
                new RemoveUpdatePageProcess());
//        removeUpdata.addUiFilter()



        // 添加入页面控制器
        JobPagerControl<Boolean> control = new JobPagerControl<>(ADBManager.getInstance(), deviceInfoBean);
        control.addPager(startMain);
        control.addPager(removeUpdata);

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
