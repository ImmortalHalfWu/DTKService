package cn;

import immortal.half.wu.FileUtils;
import immortal.half.wu.adbs.ADBManager;

public class APP {

    public static void test() {

        String s = "5ENDU19214004179";
//        asd();


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
//                .androidUIXML(s, fileName, saveFilePath)) {
//
//            String uiXmlString = FileUtils.readFile(saveFilePath);
//            System.out.println(uiXmlString);
//            FileUtils.writeText(FileUtils.DIR_PATH_XML + "111.txt", uiXmlString + "\n", true);
//
//        }

//        IAndroidPager advertActivity = AndroidPagerFactory.instance().getAdvertActivity(s);
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
//        IAndroidPager homeActivity = AndroidPagerFactory.instance().getHomeActivity(s);
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

//        IAndroidPager postActivity = instance().getPostActivity(s);
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
//                        .addClick(imagProcessActivity.getUIPoint(PAGE_POINT_KEY_IMG_PROCESS_TAG_CHOICE))
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
//            try {
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



    }

    private static String asd() {

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
