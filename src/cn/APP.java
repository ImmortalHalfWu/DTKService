package cn;

import immortal.half.wu.AppManager;
import immortal.half.wu.FileUtils;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.adbs.ADBRunnable;
import immortal.half.wu.apps.IdleFish.beans.IdleFishProductBean;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.impls.PostedProductNames;
import immortal.half.wu.apps.impls.PostedProductRefresh;
import immortal.half.wu.apps.impls.PostedProductRemove;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.apps.interfaces.IDevice;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

public class APP {

    private static Point dxSize;

    public static void test2() {

        IAndroidApp<IdleFishProductBean> idleFishAndroidApp1 = AppManager.getInstance().createIdleFishAndroidApp(
                IDevice.create("5ENDU19214004179", ADBManager.getInstance().getDxSize("5ENDU19214004179"))
        );

        long l = System.currentTimeMillis();

        idleFishAndroidApp1.startApp();

        for (int i = 0; i < 2; i++) {
            ArrayList<File> objects = new ArrayList<>();
            objects.add(new File(""));objects.add(new File(""));objects.add(new File(""));
            objects.add(new File(""));objects.add(new File(""));objects.add(new File(""));
            objects.add(new File(""));objects.add(new File(""));objects.add(new File(""));
            ArrayList<String> tags = new ArrayList<>();
            tags.add("tag1");tags.add("tag2");tags.add("tag3");
//        键盘切换测试
            long l1 = System.currentTimeMillis();
            idleFishAndroidApp1.postProduct(new IdleFishProductBean(
                    "title","info", objects, tags, "12.3", "15.1"
            ));
            System.out.println("-----------------------------"+(System.currentTimeMillis() - l1) / 1000+"-------------------------------");
        }
//        idleFishAndroidApp1.isLogin(new PageActionHomeMy.IsLoginCallBack() {
//            @Override
//            public void isLogin(boolean isLogin) {
//                System.out.println(isLogin);
//            }
//        });
//        idleFishAndroidApp1.getUserName(new PageActionHomeMy.UserInfoCallBack() {
//            @Override
//            public void result(String name, String postedNum) {
//                System.out.println(name + " __ " + postedNum);
//            }
//        });
//        idleFishAndroidApp1.getPostedProductsName(new PostedProductNames.CallBack() {
//            @Override
//            public void names(Set<String> names) {
//                System.out.println(names);
//            }
//        });
//
//
////        idleFishAndroidApp1.startApp();
//        idleFishAndroidApp1.isLogin(new PageActionHomeMy.IsLoginCallBack() {
//            @Override
//            public void isLogin(boolean isLogin) {
//                System.out.println(isLogin);
//            }
//        });
//        idleFishAndroidApp1.getUserName(new PageActionHomeMy.UserInfoCallBack() {
//            @Override
//            public void result(String name, String postedNum) {
//                System.out.println(name + " __ " + postedNum);
//            }
//        });
//        idleFishAndroidApp1.getPostedProductsName(new PostedProductNames.CallBack() {
//            @Override
//            public void names(Set<String> names) {
//                System.out.println(names);
//            }
//        });
//        idleFishAndroidApp1.refreshPostedProduct();
//
//
//        idleFishAndroidApp1.getUserName(new PageActionHomeMy.UserInfoCallBack() {
//            @Override
//            public void result(String name, String postedNum) {
//                System.out.println(name + " __ " + postedNum);
//            }
//        });
//        idleFishAndroidApp1.refreshPostedProduct();
//        idleFishAndroidApp1.getPostedProductsName(new PostedProductNames.CallBack() {
//            @Override
//            public void names(Set<String> names) {
//                System.out.println(names);
//            }
//        });
//
//
//        idleFishAndroidApp1.isLogin(new PageActionHomeMy.IsLoginCallBack() {
//            @Override
//            public void isLogin(boolean isLogin) {
//                System.out.println(isLogin);
//            }
//        });
//        idleFishAndroidApp1.getUserName(new PageActionHomeMy.UserInfoCallBack() {
//            @Override
//            public void result(String name, String postedNum) {
//                System.out.println(name + " __ " + postedNum);
//            }
//        });

        System.out.println((System.currentTimeMillis() - l) / 1000);
    }

    public static void test() {
        dxSize = ADBManager.getInstance().getDxSize("5ENDU19214004179");

        IDevice deviceId = new IDevice() {
            @Override
            public String getDeviceId() {
                return "5ENDU19214004179";
            }

            @Override
            public Point getDxSize() {
                return dxSize;
            }
        };
//        uiCutXML();
//        postedProductRefresh();
//        postedProductRemove();


//        String topActivity = ADBManager.getInstance().findTopActivity(s);
//
//        System.out.println(topActivity);
//        String fileName = s + "_" + topActivity+ ".xml";
//        FileUtils.writeText(FileUtils.DIR_PATH_XML + "111.txt", topActivity + "\n", true);
//
//        String saveFilePath = FileUtils.DIR_PATH_XML + fileName;
//
//        if (ADBManager
//                .getInstance()
//                .androidUIXMLHaveCache(s, fileName, saveFilePath)) {
//
//            String uiXmlString = FileUtils.readFile(saveFilePath);
//            System.out.println(uiXmlString);
//            FileUtils.writeText(FileUtils.DIR_PATH_XML + "111.txt", uiXmlString + "\n", true);
//
//        }

        new Thread(() -> {

//            AndroidPagerManager.getInstance().registerPagerListener(deviceId, new AndroidPagerListener() {
//                @Override
//                public boolean needProcess(String deviceId, String packageName, String activityPath, String activityName) {
//                    return AndroidIdleFishPagerName.PAGER_NAME_MAIN.NAME_ACTIVITY.equals(activityName);
//                }
//
//                @Override
//                public void onPagerResume(String deviceId, String packageName, String activityPath, String activityName) {
//                    IAndroidPager androidPager = AndroidIdleFishPagerFactory.instance().getAndroidPager(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN);
//                    new ADBBuilder()
//                            .addClick(androidPager.getUIPoint(PAGE_POINT_KEY_HOME_UPDATE))
//                            .send(deviceId);
//                    AppManager.getInstance().createIdleFishAndroidApp(deviceId).toMainActivity();
//                }
//
//                @Override
//                public void onPagerStop(String deviceId, String packageName, String activityPath, String activityName) {
//                    AndroidPagerManager.getInstance().unRegisterPagerListener(deviceId, this);
//                }
//            });

//            AndroidPagerManager.getInstance().registerPagerListener(deviceId, new AndroidPagerListener() {
//                @Override
//                public boolean needProcess(String deviceId, String packageName, String activityPath, String activityName) {
//                    return AndroidIdleFishPagerName.PAGER_NAME_MAIN.NAME_ACTIVITY.equals(activityName);
//                }
//
//                @Override
//                public void onPagerResume(String deviceId, String packageName, String activityPath, String activityName) {
//
//                    if (AndroidIdleFishPagerName.PAGER_NAME_MAIN.NAME_ACTIVITY.equals(activityName)) {
//                        IAndroidPager androidPager = AndroidIdleFishPagerFactory.instance().getAndroidPager(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN);
//                        new ADBBuilder()
//                                .addClick(androidPager.getUIPoint(PAGE_POINT_KEY_HOME_MY))
//                                .send(deviceId);
//                    }
//
////                    androidPager = AndroidIdleFishPagerFactory.instance().getAndroidPager(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN);
////                    Point uiPoint = androidPager.getUIPoint(PAGE_POINT_KEY_HOME_UPDATE);
////                    new ADBBuilder().addClick(uiPoint).send(deviceId);
//
//                    System.out.println("-----------------PagerResume---------------------");
//                    System.out.println(deviceId);
//                    System.out.println(packageName);
//                    System.out.println(activityPath);
//                    System.out.println(activityName);
//                }
//
//                @Override
//                public void onPagerStop(String deviceId, String packageName, String activityPath, String activityName) {
//                    System.out.println("-----------------PagerStop---------------------");
//                    System.out.println(deviceId);
//                    System.out.println(packageName);
//                    System.out.println(activityPath);
//                    System.out.println(activityName);
//                }
//
//            });

            long startTime = System.currentTimeMillis();

            IAndroidApp idleFishAndroidApp = AppManager.getInstance().createIdleFishAndroidApp(deviceId);
            idleFishAndroidApp.startApp();




            idleFishAndroidApp.toMainActivity();
            ADBManager.getInstance().createBuild()
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {

                            IAndroidPager homeActivity = instance().getHomeActivity(deviceId);
                            adbProcess.createBuild()
                                    .addClick(homeActivity.getUIPoint(PAGE_POINT_KEY_HOME_POST))
                                    .addClick(homeActivity.getUIPoint(PAGE_POINT_KEY_HOME_POST))
                                    .send(deviceId.getDeviceId());

                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            adbProcess.createBuild()
                                    .addClick(
                                            AndroidIdleFishPagerFactory.instance()
                                                    .getPostChoiceActivity(deviceId)
                                                    .getUIPoint(PAGE_POINT_KEY_POST_CHOICE_POST))
                                    .send(deviceId.getDeviceId());
                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            adbProcess.createBuild()
                                    .addClick(instance().getImgChoiceActivity(deviceId).getUIPoint(PAGE_POINT_KEY_IMG_CHOICE_OK))
                                    .send(deviceId.getDeviceId());
                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            IAndroidPager imagProcessActivity = instance().getImagProcessActivity(deviceId);
                            adbProcess.createBuild()
                                    .addClick(imagProcessActivity.getUIPoint(PAGE_POINT_KEY_IMG_PROCESS_TAG))
//                                    .addClick(imagProcessActivity.getUIPoint(PAGE_POINT_KEY_IMG_PROCESS_TAG))
                                    .send(deviceId.getDeviceId());
                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            IAndroidPager postProductTagActivity = instance().getPostProductTagActivity(deviceId);
                            adbProcess.createBuild()
                                    .addClick(postProductTagActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_TAG_CANCLE))
                                    .send(deviceId.getDeviceId());
                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            adbProcess.createBuild()
                                    .delayTime(500)
                                    .addClick(
                                            instance()
                                                    .getImagProcessActivity(deviceId)
                                                    .getUIPoint(PAGE_POINT_KEY_IMG_PROCESS_OK)
                                    ).send(deviceId.getDeviceId());
                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            IAndroidPager postProductInfoActivity = instance().getPostProductInfoActivity(deviceId);
                            adbProcess.createBuild()
                                    .addClick(postProductInfoActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY))
                                    .addCallBack(new ADBRunnable() {
                                        @Override
                                        public void run(ADBManager adbProcess) {
                                            instance().getPostProductMoneyActivity(deviceId);
                                        }
                                    })
                                    .addBackClick()
                                    .delayTime(300)
                                    .addClick(postProductInfoActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER))
                                    .addCallBack(new ADBRunnable() {
                                        @Override
                                        public void run(ADBManager adbProcess) {
                                            instance().getPostProductOtherActivity(deviceId);
                                        }
                                    })
                                    .send(deviceId.getDeviceId());
                        }
                    })
                    .send(deviceId.getDeviceId());

            idleFishAndroidApp.toMainActivity();

            ADBManager.getInstance().createBuild()
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            instance().getHomeActivity(deviceId);
                            adbProcess.createBuild()
                                    .addClick(instance().getAndroidPager(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN).getUIPoint(PAGE_POINT_KEY_HOME_MY))
                                    .send(deviceId.getDeviceId());
                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            Map<String, String> myInfoActivity = instance().getMyInfoActivity(deviceId);
                            System.out.println(myInfoActivity);
                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            instance().getMyActivity(deviceId);
                            adbProcess.createBuild()
                                    .addClick(instance().getMyActivity(deviceId).getUIPoint(PAGE_POINT_KEY_HOME_MY_POSTED))
                                    .send(deviceId.getDeviceId());
                        }
                    })
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            new PostedProductNames(deviceId, System.out::println).start();
                            AppManager.getInstance().createIdleFishAndroidApp(deviceId).toMainActivity();
                        }
                    })
                    .delayTime(2000)
                    .addCallBack(new ADBRunnable() {
                        @Override
                        public void run(ADBManager adbProcess) {
                            ADBManager.getInstance().closeApp(
                                    deviceId.getDeviceId(),
                                    idleFishAndroidApp.getPackageName());
                        }
                    })
                    .send(deviceId.getDeviceId());

            long l = System.currentTimeMillis() - startTime;
            System.out.println(l / 1000);

        }).start();


//        IAndroidPager advertActivity = AndroidIdleFishPagerFactory.instance().getAdvertActivity(s);
//
//        if (advertActivity.isResume()) {
//            try {
//                new ADBBuilder()
//                        .addClick(advertActivity.getUIPoint(PAGE_POINT_KEY_ADVERT_CLOSE))
//                        .send(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        IAndroidPager homeActivity = AndroidIdleFishPagerFactory.instance().getHomeActivity(s);
//
//        if (homeActivity.isResume()) {
//            try {
//                new ADBBuilder()
//                        .addClick(homeActivity.getUIPoint(PAGE_POINT_KEY_HOME_UPDATE))
//                        .addClick(homeActivity.getUIPoint(PAGE_POINT_KEY_HOME_MY))
//                        .send(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//        IAndroidPager postActivity = instance().getPostChoiceActivity(s);
//
//        if (postActivity.isResume()) {
//            try {
//                new ADBBuilder()
//                        .addClick(postActivity.getUIPoint(PAGE_POINT_KEY_POST_CHOICE_FREE))
//                        .addBackClick()
//                        .addClick(postActivity.getUIPoint(PAGE_POINT_KEY_POST_CHOICE_POST))
//                        .send(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//        IAndroidPager imgChoiceActivity = instance().getImgChoiceActivity(s);
//
//        if (imgChoiceActivity.isResume()) {
//            Map<String, Point> uiPoint = imgChoiceActivity.getUIPoint();
//            ADBBuilder adbBuilder = new ADBBuilder();
//            for (String key :
//                    PAGE_POINT_KEY_IMG_CHOICE) {
//                if (PAGE_POINT_KEY_IMG_CHOICE.indexOf(key) >= PAGE_POINT_KEY_IMG_CHOICE.size() - 9) {
//                    adbBuilder.addClick(uiPoint.get(key));
//                }
//            }
//
//            adbBuilder.addClick(imgChoiceActivity.getUIPoint(PAGE_POINT_KEY_IMG_CHOICE_OK));
//            adbBuilder.send(s);
//
//        }


//        IAndroidPager imagProcessActivity = instance().getImagProcessActivity(s);
//
//        if (imagProcessActivity.isResume()) {
//            try {
//                new ADBBuilder()
//                        .addClick(imagProcessActivity.getUIPoint(PAGE_POINT_KEY_IMG_PROCESS_TAG))
//                        .addBackClick()
//                        .addClick(imagProcessActivity.getUIPoint(PAGE_POINT_KEY_IMG_PROCESS_OK))
//                        .send(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        IAndroidPager imagProcessActivity = instance().getPostProductInfoActivity(s);
//
//        if (imagProcessActivity.isResume()) {
//            try {2308640000007
//                new ADBBuilder()
//                        .addClick(imagProcessActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_INFO_INFO))
//                        .addBackClick()
//                        .addClick(imagProcessActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY))
//                        .delayTime(300)
//                        .addBackClick()
//                        .addClick(imagProcessActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER))
//                        .delayTime(300)
//                        .addBackClick()
//                        .send(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        IAndroidPager productMoneyActivity = instance().getPostProductMoneyActivity(s);
//
//        if (productMoneyActivity.isResume()) {
//            try {
//                new ADBBuilder()
//                        .addClick(productMoneyActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY))
//                        .addClick(productMoneyActivity.getUIPoint("1"))
//                        .addClick(productMoneyActivity.getUIPoint("."))
//                        .addClick(productMoneyActivity.getUIPoint("4"))
//                        .addClick(productMoneyActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL))
//                        .addClick(productMoneyActivity.getUIPoint("2"))
//                        .addClick(productMoneyActivity.getUIPoint("."))
//                        .addClick(productMoneyActivity.getUIPoint("6"))
//                        .addClick(productMoneyActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL))
//                        .addClick(productMoneyActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK))
//                        .send(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        IAndroidPager productOtherActivity = instance().getPostProductOtherActivity(s);
//
//        if (productOtherActivity.isResume()) {
//            try {
//                new ADBBuilder()
//                        .addClick(productOtherActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW))
//                        .addClick(productOtherActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY))
//                        .addClick(productOtherActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK))
//                        .send(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        IAndroidPager productTagActivity = instance().getPostProductTagActivity(s);
//
//        if (productTagActivity.isResume()) {
//            try {
//                new ADBBuilder()
//                        .addClick(productTagActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT))
//                        .addClick(productTagActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_TAG_FIRST_LINE))
//                        .send(s);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        IAndroidPager homeActivity = AndroidIdleFishPagerFactory.instance().getHomeActivity(s);
//        if (homeActivity.isResume()) {
//            Point uiPoint = homeActivity.getUIPoint(AndroidIdleFishPagerFactory.PAGE_POINT_KEY_HOME_MY);
//            new ADBBuilder().addClick(uiPoint).send(s);
//
//            if (AndroidIdleFishPagerFactory.instance().getLoginActivity(s).isResume()) {
//                System.out.println("not login");
//            }
//
//        }


    }

    private static void postedProductRemove() {
        new PostedProductRemove(new IDevice() {
            @Override
            public String getDeviceId() {
                return "5ENDU19214004179";
            }

            @Override
            public Point getDxSize() {
                return dxSize;
            }
        }, "google开发者大会纪念品").start();
    }

    private static void postedProductRefresh() {
        new PostedProductRefresh(new IDevice() {
            @Override
            public String getDeviceId() {
                return "5ENDU19214004179";
            }

            @Override
            public Point getDxSize() {
                return dxSize;
            }
        }).start();
    }

    private static void postedProductName() {
        new PostedProductNames(new IDevice() {
            @Override
            public String getDeviceId() {
                return "5ENDU19214004179";
            }

            @Override
            public Point getDxSize() {
                return dxSize;
            }
        }, System.out::println).start();
    }


    private static String uiCutXML() {

        String s = "5ENDU19214004179";
        String topActivity = ADBManager.getInstance().findTopActivity(s);

        System.out.println(topActivity);
        String fileName = s + "_" + topActivity+ ".xml";
        FileUtils.writeText(FileUtils.DIR_PATH_XML + "111.txt", topActivity + "\n", true);

        String saveFilePath = FileUtils.DIR_PATH_XML + fileName;

        if (ADBManager
                .getInstance()
                .androidUIXML(s, fileName, saveFilePath)) {

            String uiXmlString = FileUtils.readFile(saveFilePath);
            System.out.println(uiXmlString);
            FileUtils.writeText(FileUtils.DIR_PATH_XML + "111.txt", uiXmlString + "\n", true);

        }

        return "";
    }

}
