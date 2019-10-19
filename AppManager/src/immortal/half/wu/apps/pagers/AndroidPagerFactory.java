package immortal.half.wu.apps.pagers;

import immortal.half.wu.FileUtils;
import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.ui.PointFilterBean;
import immortal.half.wu.ui.PointFilterBuilder;
import immortal.half.wu.ui.UIProcessor;
import immortal.half.wu.ui.XMLUtil;
import org.dom4j.DocumentException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidPagerFactory {

    private static AndroidPagerFactory instance;

    private Map<String, IAndroidPager> androidPagerMap;

    private AndroidPagerFactory() {
        androidPagerMap = new HashMap<>();
    }

    public static AndroidPagerFactory instance() {
        if (instance == null) {
            synchronized (AndroidPagerFactory.class) {
                if (instance == null) {
                    instance = new AndroidPagerFactory();
                }
            }
        }
        return instance;
    }



    public final static String PAGE_POINT_KEY_ADVERT_CLOSE = "关闭广告";

    public IAndroidPager getAdvertActivity(String deviceId) {

        String advertActivity = "AdvertActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, advertActivity));

        return iAndroidPager != null ? iAndroidPager : create(deviceId,
                advertActivity,
                new PointFilterBuilder()
                        .addResourceId("com.taobao.idlefish:id/advert_close_text")
                        .next(PAGE_POINT_KEY_ADVERT_CLOSE)
                        .create()
        );
    }


    public final static String PAGE_POINT_KEY_HOME_POST = "发布";
    public final static String PAGE_POINT_KEY_HOME_UPDATE = "暂不升级";
    public final static String PAGE_POINT_KEY_HOME_MY = "我的";

    public IAndroidPager getHomeActivity(String deviceId) {

        String mainActivity = "MainActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, mainActivity));

        return iAndroidPager != null ? iAndroidPager : create(deviceId,
                mainActivity,
                new PointFilterBuilder()
                        .addContentDesc("发布")
                        .addResourceId("com.taobao.idlefish:id/post_click")
                        .next(PAGE_POINT_KEY_HOME_POST)
                        .addContentDesc("我的，未选中状态")
                        .next(PAGE_POINT_KEY_HOME_MY)
                        .addText("暂不升级")
                        .addResourceId("com.taobao.idlefish:id/left")
                        .next(PAGE_POINT_KEY_HOME_UPDATE)
                        .create()
        );
    }



    public final static String PAGE_POINT_KEY_HOME_POSTED = "我发布的";

    public IAndroidPager getMyActivity(String deviceId) {

        String mainPostedActivity = "MainActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, mainPostedActivity + PAGE_POINT_KEY_HOME_POSTED));

        return iAndroidPager != null ? iAndroidPager : create(deviceId,
                mainPostedActivity,
                new PointFilterBuilder()
                        .addText(PAGE_POINT_KEY_HOME_POSTED)
                        .next(PAGE_POINT_KEY_HOME_POSTED)
                        .create()
        );
    }



    public final static String PAGE_POINT_KEY_POST_CHOICE_POST = "发布闲置";
    public final static String PAGE_POINT_KEY_POST_CHOICE_FREE = "免费送";

    public IAndroidPager getPostActivity(String deviceId) {

        String publishEntryActivity = "PublishEntryActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, publishEntryActivity));

        return iAndroidPager != null ? iAndroidPager : create(deviceId,
                publishEntryActivity,
                new PointFilterBuilder()
                        .addResourceId("com.taobao.idlefish:id/top_entry_second")
                        .next(PAGE_POINT_KEY_POST_CHOICE_FREE)
                        .addText(PAGE_POINT_KEY_POST_CHOICE_POST)
                        .next(PAGE_POINT_KEY_POST_CHOICE_POST)
                        .create()
        );
    }



    public final static ArrayList<String> PAGE_POINT_KEY_IMG_CHOICE = new ArrayList<>();
    public final static String PAGE_POINT_KEY_IMG_CHOICE_OK = "完成(1)";

    public IAndroidPager getImgChoiceActivity(String deviceId) {

        String multiMediaStudioActivity = "MultiMediaStudioActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, multiMediaStudioActivity));

        if (iAndroidPager != null) {
            return iAndroidPager;
        }

        String xmlString = UIProcessor.androidUIXML(deviceId);

        if (!FileUtils.isEmpty(xmlString)) {

            try {
                
                List<Point> allImgPoint = XMLUtil.findAllPointByAttrKeyValue(
                        XMLUtil.findRootElement(xmlString),
                        new ArrayList<>(),
                        "content-desc",
                        "图片未选中");

                Map<String, Point> pointMap = new HashMap<>();

                if (allImgPoint.size() > 0) {

                    new ADBBuilder()
                            .addClick(allImgPoint.get(allImgPoint.size() - 1))
                            .send(deviceId);

                    IAndroidPager imageChoiceSucButton = create(
                            deviceId,
                            multiMediaStudioActivity,
                            new PointFilterBuilder().addText(PAGE_POINT_KEY_IMG_CHOICE_OK).next(PAGE_POINT_KEY_IMG_CHOICE_OK).create()
                    );

                    pointMap.put(
                            PAGE_POINT_KEY_IMG_CHOICE_OK,
                            imageChoiceSucButton.getUIPoint(PAGE_POINT_KEY_IMG_CHOICE_OK));

                    for (int i = 0, size = allImgPoint.size(); i < size; i++) {
                        String s = String.valueOf(i);
                        PAGE_POINT_KEY_IMG_CHOICE.add(s);
                        pointMap.put(s, allImgPoint.get(i));
                    }

                    ActivityPager activityPager = new ActivityPager(deviceId, multiMediaStudioActivity, new PointFilterBuilder().create(), pointMap);
                    androidPagerMap.put(
                            createPagerMapKey(deviceId, multiMediaStudioActivity),
                            activityPager);

                    return activityPager;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

        return new ActivityPager(deviceId, multiMediaStudioActivity, new PointFilterBuilder().create(), new HashMap<>());

    }


    public final static String PAGE_POINT_KEY_IMG_PROCESS_TAG_CHOICE = "标签";
    public final static String PAGE_POINT_KEY_IMG_PROCESS_OK = "图像及价签处理完成";

    public IAndroidPager getImagProcessActivity(String deviceId) {

        String multiMediaEditorActivity = "MultiMediaEditorActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, multiMediaEditorActivity));

        return iAndroidPager != null ? iAndroidPager : create(deviceId,
                multiMediaEditorActivity,
                new PointFilterBuilder()
                        .addText(PAGE_POINT_KEY_IMG_PROCESS_TAG_CHOICE)
                        .next(PAGE_POINT_KEY_IMG_PROCESS_TAG_CHOICE)
                        .addResourceId("com.taobao.idlefish:id/effect_next")
                        .next(PAGE_POINT_KEY_IMG_PROCESS_OK)
                        .create()
        );
    }


    public final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_OK = "发布";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_INFO = "商品信息啥的";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY = "一口价¥0.00";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER = "选项";

    public IAndroidPager getPostProductInfoActivity(String deviceId) {

        String fishFlutterActivity = "FishFlutterActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, fishFlutterActivity));

        return iAndroidPager != null ? iAndroidPager : create(deviceId,
                fishFlutterActivity,
                new PointFilterBuilder()
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_INFO_OK)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_INFO_OK)
                        .addClass("android.widget.EditText")
                        .next(PAGE_POINT_KEY_POST_PRODUCT_INFO_INFO)
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY)
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER)
                        .create()
        );
    }


    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY = "一口价¥";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL = "入手价¥";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL = "包邮";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK = "确认";

    public IAndroidPager getPostProductMoneyActivity(String deviceId) {

        String fishFlutterActivity = "FishFlutterActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, fishFlutterActivity + "价钱选择"));

        if (iAndroidPager != null) {
            return iAndroidPager;
        }

        iAndroidPager = create(deviceId,
                fishFlutterActivity,
                new PointFilterBuilder()
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                        .create()
        );

        new ADBBuilder()
                .addClick(iAndroidPager.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL))
                .send(deviceId);

        PointFilterBuilder pointFilterBuilder = new PointFilterBuilder()
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK)
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY)
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL);


        for (int i = 0; i < 10; i++) {
            String keyText = String.valueOf(i);
            pointFilterBuilder.addText(keyText).next(keyText);
        }

        iAndroidPager = create(deviceId,
                fishFlutterActivity,
                pointFilterBuilder.addText(".").next(".")
                        .create()
        );

        return iAndroidPager;
    }




    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW = "全新";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY = "不讲价";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK = "确认";

    public IAndroidPager getPostProductOtherActivity(String deviceId) {

        String mainPostedActivity = "FishFlutterActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, mainPostedActivity + "其他选项"));

        return iAndroidPager != null ? iAndroidPager : create(deviceId,
                mainPostedActivity,
                new PointFilterBuilder()
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW)
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY)
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK)
                        .create()
        );
    }


    public final static String PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT = "输入框";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_TAG_FIRST_LINE = "第一行";

    public IAndroidPager getPostProductTagActivity(String deviceId) {

        String mainPostedActivity = "FishFlutterActivity";
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, mainPostedActivity + "价签选择"));

        if (iAndroidPager != null) {
            return iAndroidPager;
        }

        iAndroidPager = create(deviceId,
                mainPostedActivity,
                new PointFilterBuilder()
                        .addClass("android.widget.EditText")
                        .next(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT)
                        .create()
        );

        Point editPoint = iAndroidPager.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT);

        new ADBBuilder().addClick(editPoint).addText("text").send(deviceId);

        try {
            Point firstLinePoint = XMLUtil.findPointByAttrKeyValueEndWith(
                    XMLUtil.findRootElement(UIProcessor.androidUIXML(deviceId)),
                    "text",
                    "自定义标签");

            iAndroidPager.getUIPoint().put(PAGE_POINT_KEY_POST_PRODUCT_TAG_FIRST_LINE, firstLinePoint);
            return iAndroidPager;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return iAndroidPager;
    }







    private IAndroidPager create(String deviceId, String activityName, PointFilterBean pointFilterBean) {
        ActivityPager activityPager = new ActivityPager(
                deviceId,
                activityName,
                pointFilterBean,
                UIProcessor.getPointByUIXML(deviceId, pointFilterBean)
        );
        androidPagerMap.put(createPagerMapKey(deviceId, activityName), activityPager);
        return activityPager;
    }

    private String createPagerMapKey(String deviceId, String activityName) {
        return deviceId + activityName;
    }





    private final static class ActivityPager implements IAndroidPager {

        private final String deviceId;
        private final String activityName;
        private final PointFilterBean pointFilterBean;
        private final Map<String, Point> uiPoint;

        private ActivityPager(String deviceId, String activityName, PointFilterBean pointFilterBean, Map<String, Point> uiPoint) {
            this.deviceId = deviceId;
            this.activityName = activityName;
            this.pointFilterBean = pointFilterBean;
            this.uiPoint = uiPoint;
        }

        @Override
        public String getActivityName() {
            return activityName;
        }

        @Override
        public PointFilterBean getPointFilter() {
            return pointFilterBean;
        }

        @Override
        public Map<String, Point> getUIPoint() {
            return uiPoint;
        }

        @Override
        public Point getUIPoint(String pointKey) {
            Point point = uiPoint.get(pointKey);
            return point == null ? new Point(0, 0) : point;
        }

        @Override
        public boolean isResume() {
            return UIProcessor.getTopActivity(deviceId).equals(activityName);
        }

        public String getDeviceId() {
            return deviceId;
        }
    }


}
