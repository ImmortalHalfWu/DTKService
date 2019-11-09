package immortal.half.wu.apps.IdleFish.pagers;

import immortal.half.wu.FileUtils;
import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.apps.interfaces.IDevice;
import immortal.half.wu.apps.interfaces.ProcessPostedProductCallBack;
import immortal.half.wu.ui.PointFilterBean;
import immortal.half.wu.ui.PointFilterBuilder;
import immortal.half.wu.ui.UIProcessor;
import immortal.half.wu.ui.XMLUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidIdleFishPagerFactory {

    private static AndroidIdleFishPagerFactory instance;

    private Map<String, IAndroidPager> androidPagerMap;

    private AndroidIdleFishPagerFactory() {
        androidPagerMap = new HashMap<>();
    }

    public static AndroidIdleFishPagerFactory instance() {
        if (instance == null) {
            synchronized (AndroidIdleFishPagerFactory.class) {
                if (instance == null) {
                    instance = new AndroidIdleFishPagerFactory();
                }
            }
        }
        return instance;
    }


    public final static String PAGE_POINT_KEY_ADVERT_CLOSE = "关闭广告";

    @NotNull
    public IAndroidPager getAdvertActivity(@NotNull IDevice deviceId) {

        String advertActivity = AndroidIdleFishPagerName.PAGER_NAME_ADVERT.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_ADVERT));

        return iAndroidPager != null ? iAndroidPager : createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_ADVERT,
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

    @NotNull
    public IAndroidPager getHomeActivity(@NotNull IDevice deviceId) {

        String mainActivity = AndroidIdleFishPagerName.PAGER_NAME_MAIN.NAME_ACTIVITY;
//        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN));

        return createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN,
                mainActivity,
                new PointFilterBuilder()
                        .addResourceId("com.taobao.idlefish:id/post_click")
                        .next(PAGE_POINT_KEY_HOME_POST)
                        .addContentDesc("我的，")
                        .next(PAGE_POINT_KEY_HOME_MY)
                        .addText(PAGE_POINT_KEY_HOME_UPDATE)
                        .addResourceId("com.taobao.idlefish:id/left")
                        .next(PAGE_POINT_KEY_HOME_UPDATE)
                        .create()
        );
    }


    public final static String PAGE_POINT_KEY_HOME_MY_POSTED = "我发布的";

    @NotNull
    public IAndroidPager getMyActivity(@NotNull IDevice deviceId) {

        String mainPostedActivity = AndroidIdleFishPagerName.PAGER_NAME_MAIN_MY.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN_MY));

        return iAndroidPager != null ? iAndroidPager : createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN_MY,
                mainPostedActivity,
                new PointFilterBuilder()
                        .addText(PAGE_POINT_KEY_HOME_MY_POSTED)
                        .next(PAGE_POINT_KEY_HOME_MY_POSTED)
                        .create()
        );
    }


    public final static String PAGE_POINT_KEY_HOME_MY_POSTED_NUM = "我发布的商品数量";
    public final static String PAGE_POINT_KEY_HOME_MY_USER_NAME = "用户名称";

    @NotNull
    public Map<String, String> getMyInfoActivity(@NotNull IDevice deviceId) {

        return UIProcessor.getTextByUIXML(
                new PointFilterBuilder()
                        .addResourceId("com.taobao.idlefish:id/entry_title")
                        .addIndex("0")
                        .next(PAGE_POINT_KEY_HOME_MY_USER_NAME)
                        .addIndex("2")
                        .addResourceId("com.taobao.idlefish:id/entry_sub_title")
                        .next(PAGE_POINT_KEY_HOME_MY_POSTED_NUM)
                        .create(),
                UIProcessor.androidUIXMLNoCache(deviceId.getDeviceId(), deviceId.getDeviceId())
        );

    }


//获取我的页面用户名称与已发布数量findTextByAttrKeyValues

    public final static String PAGE_POINT_KEY_POST_CHOICE_POST = "发布闲置";
    public final static String PAGE_POINT_KEY_POST_CHOICE_FREE = "免费送";

    @NotNull
    public IAndroidPager getPostChoiceActivity(@NotNull IDevice deviceId) {

        String publishEntryActivity = AndroidIdleFishPagerName.PAGER_NAME_POST_CHOICE.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_CHOICE));

        return iAndroidPager != null ? iAndroidPager : createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_CHOICE,
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

    @NotNull
    public IAndroidPager getImgChoiceActivity(@NotNull IDevice deviceId) {

        String multiMediaStudioActivity = AndroidIdleFishPagerName.PAGER_NAME_IMAGE_CHOICE.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_IMAGE_CHOICE));
        Point imageChoiceSucButtonPoint = null;

        if (iAndroidPager != null) {
            imageChoiceSucButtonPoint = iAndroidPager.getUIPoint(PAGE_POINT_KEY_IMG_CHOICE_OK);
        }

        String xmlString = UIProcessor.androidUIXMLNoCache(deviceId.getDeviceId(), deviceId.getDeviceId());

        if (!FileUtils.isEmpty(xmlString)) {

            try {

                List<Point> allImgPoint = XMLUtil.findAllPointByAttrKeyValue(
                        XMLUtil.findRootElement(xmlString),
                        new ArrayList<>(),
                        "content-desc",
                        "图片未选中");

                Map<String, Point> pointMap = new HashMap<>();

                if (allImgPoint.size() > 0) {

                    if (imageChoiceSucButtonPoint == null) {

                        new ADBBuilder()
                                .addClick(allImgPoint.get(allImgPoint.size() - 1))
                                .send(deviceId.getDeviceId());

                        IAndroidPager imageChoiceSucButton = createNoCache(
                                deviceId,
                                AndroidIdleFishPagerName.PAGER_NAME_IMAGE_CHOICE,
                                multiMediaStudioActivity,
                                new PointFilterBuilder()
                                        .addText(PAGE_POINT_KEY_IMG_CHOICE_OK)
                                        .next(PAGE_POINT_KEY_IMG_CHOICE_OK)
                                        .create()
                        );

                        imageChoiceSucButtonPoint = imageChoiceSucButton.getUIPoint(PAGE_POINT_KEY_IMG_CHOICE_OK);

//                        new ADBBuilder()
//                                .addClick(allImgPoint.get(allImgPoint.size() - 1))
//                                .send(deviceId.getDeviceId());
                    }

                    pointMap.put(
                            PAGE_POINT_KEY_IMG_CHOICE_OK,
                            imageChoiceSucButtonPoint);

                    for (int i = 0, size = allImgPoint.size(); i < size; i++) {
                        String s = String.valueOf(i);
                        PAGE_POINT_KEY_IMG_CHOICE.add(s);
                        pointMap.put(s, allImgPoint.get(i));
                    }

                    ActivityPager activityPager = new ActivityPager(
                            deviceId,
                            multiMediaStudioActivity,
                            new PointFilterBuilder().create(),
                            pointMap);
                    androidPagerMap.put(
                            createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_IMAGE_CHOICE),
                            activityPager);

                    return activityPager;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return new ActivityPager(
                deviceId,
                multiMediaStudioActivity,
                new PointFilterBuilder().create(),
                new HashMap<>());

    }


    public final static String PAGE_POINT_KEY_IMG_PROCESS_TAG = "添加说明标签可被更多人看见";
    public final static String PAGE_POINT_KEY_IMG_PROCESS_OK = "图像及价签处理完成";

    @NotNull
    public IAndroidPager getImagProcessActivity(@NotNull IDevice deviceId) {

        String multiMediaEditorActivity = AndroidIdleFishPagerName.PAGER_NAME_IMAGE_PROCESS.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_IMAGE_PROCESS));

        return iAndroidPager != null ? iAndroidPager : createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_IMAGE_PROCESS,
                multiMediaEditorActivity,
                new PointFilterBuilder()
                        .addResourceId("com.taobao.idlefish:id/ftv_lable")
                        .next(PAGE_POINT_KEY_IMG_PROCESS_TAG)
                        .addResourceId("com.taobao.idlefish:id/effect_next")
                        .next(PAGE_POINT_KEY_IMG_PROCESS_OK)
                        .create()
        );
    }


    public final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_OK = "发布";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_INFO = "商品信息啥的";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY = "一口价¥0.00";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER = "选项";

    @NotNull
    public IAndroidPager getPostProductInfoActivity(@NotNull IDevice deviceId) {

        String fishFlutterActivity = AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_INFO.NAME_ACTIVITY;
//        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_INFO));

        return createNoCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_INFO,
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


    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY = "入手价¥";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL = "一口价¥";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL = "包邮";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK = "确认";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_KEY_OK = "确定";

    public IAndroidPager getPostProductMoneyActivity(@NotNull IDevice deviceId) {

        String fishFlutterActivity = AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY));

        if (iAndroidPager != null) {
            return iAndroidPager;
        }

        iAndroidPager = createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY,
                fishFlutterActivity,
                new PointFilterBuilder()
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                        .create()
        );

        new ADBBuilder()
                .addClick(iAndroidPager.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL))
                .send(deviceId.getDeviceId());

        PointFilterBuilder pointFilterBuilder = new PointFilterBuilder()
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK)
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL)
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY)
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL)
                .addText(PAGE_POINT_KEY_POST_PRODUCT_MONEY_KEY_OK)
                .next(PAGE_POINT_KEY_POST_PRODUCT_MONEY_KEY_OK);


        for (int i = 0; i < 10; i++) {
            String keyText = String.valueOf(i);
            pointFilterBuilder.addText(keyText).next(keyText);
        }

        iAndroidPager = createNoCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY,
                fishFlutterActivity,
                pointFilterBuilder.addText(".").next(".")
                        .create()
        );
        androidPagerMap.put(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY), iAndroidPager);

        return iAndroidPager;
    }


    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW = "全新";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY = "不讲价";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK = "确认";

    @NotNull
    public IAndroidPager getPostProductOtherActivity(@NotNull IDevice deviceId) {

        String mainPostedActivity = AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_OTHER.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_OTHER));

        return iAndroidPager != null ? iAndroidPager : createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_OTHER,
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
    public final static String PAGE_POINT_KEY_POST_PRODUCT_TAG_CANCLE = "取消";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_TAG_FIRST_LINE = "第一行";

    public IAndroidPager getPostProductTagActivity(@NotNull IDevice deviceId) {

        String mainPostedActivity = AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_TAG.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_TAG));

        if (iAndroidPager != null) {
            return iAndroidPager;
        }

        iAndroidPager = createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_TAG,
                mainPostedActivity,
                new PointFilterBuilder()
                        .addClass("android.widget.EditText")
                        .next(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT)
                        .addText(PAGE_POINT_KEY_POST_PRODUCT_TAG_CANCLE)
                        .next(PAGE_POINT_KEY_POST_PRODUCT_TAG_CANCLE)
                        .create()
        );

        Point editPoint = iAndroidPager.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT);

        new ADBBuilder().addClick(editPoint).addText("text").send(deviceId.getDeviceId());

        try {
            Point firstLinePoint = XMLUtil.findPointByAttrKeyValueEndWith(
                    XMLUtil.findRootElement(UIProcessor.androidUIXMLNoCache(deviceId.getDeviceId(), deviceId.getDeviceId())),
                    "text",
                    "自定义标签");

            iAndroidPager.getUIPoint().put(PAGE_POINT_KEY_POST_PRODUCT_TAG_FIRST_LINE, firstLinePoint);
            return iAndroidPager;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return iAndroidPager;
    }


    @NotNull
    public IAndroidPager getLoginActivity(@NotNull IDevice deviceId) {
        String loginActivity = AndroidIdleFishPagerName.PAGER_NAME_LOGIN.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_LOGIN));

        return iAndroidPager != null ? iAndroidPager : createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY,
                loginActivity,
                new PointFilterBuilder().create()
        );
    }


    @NotNull
    public IAndroidPager getPostedActivity(@NotNull IDevice deviceId) {

        String postedActivity = AndroidIdleFishPagerName.PAGER_NAME_POSTED.NAME_ACTIVITY;

        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POSTED));

        return iAndroidPager != null ? iAndroidPager : createHaveCache(deviceId, AndroidIdleFishPagerName.PAGER_NAME_POSTED,
                postedActivity,
                new PointFilterBuilder().create()
        );
    }


    public void processPostedProduct(@NotNull IDevice deviceId, @Nullable ProcessPostedProductCallBack callBack) {

        if (callBack == null) {
            return;
        }

        try {

            String uiXmlString = UIProcessor.androidUIXMLNoCache(deviceId.getDeviceId(), deviceId.getDeviceId());
            Element recyclerView = XMLUtil.findElementByNodeKeyValue(
                    XMLUtil.findRootElement(uiXmlString),
                    "class",
                    "android.support.v7.widget.RecyclerView"
            );

            if (recyclerView == null) {
                callBack.notFound();
                return;
            }

            List<Element> items = XMLUtil.findAllElementByTagName(
                    recyclerView,
                    "node",
                    new ArrayList<>()
            );

            ArrayList<String> objects = new ArrayList<>();
            objects.add("曝光");
            objects.add("游戏");
            objects.add("我的问答");
            objects.add("宝贝图片");
            objects.add("¥");
            objects.add("留言");
            objects.add("浏览");
            objects.add("划重点");
            objects.add("+");
            objects.add("降价");
            objects.add("编辑");

            List<Element> elements = XMLUtil.removeElementByAttrTextWithNull(
                    items,
                    objects
            );

            if (elements.size() <= 1) {
                callBack.notFound();
                return;
            }

            Element firstElement = elements.get(0);

            try {
                Integer.valueOf(firstElement.attributeValue(QName.get("content-desc")));
                elements.remove(0);
            } catch (Exception ignored) {
            }


            String nameElement = "";
            int priceElement = 0;
            Element refreshElement = null, moreElement;

            for (Element element : elements) {

                String value = element.attributeValue(QName.get("content-desc"));

                if (value.equals("更多") && !FileUtils.isEmpty(nameElement) && priceElement != 0) {
                    moreElement = element;
                    if (callBack.process(nameElement, priceElement, refreshElement, moreElement)) {
                        callBack.over();
                        return;
                    }
                    nameElement = "";
                    priceElement = 0;
                    continue;
                }

                if (value.equals("擦亮") && !FileUtils.isEmpty(nameElement) && priceElement != 0) {
                    refreshElement = element;
                    if (callBack.process(nameElement, priceElement, refreshElement)) {
                        callBack.over();
                        return;
                    }
                    continue;
                }

                try {
                    priceElement = Integer.valueOf(value);
                    if (!FileUtils.isEmpty(nameElement) && callBack.process(nameElement, priceElement)) {
                        callBack.over();
                        return;
                    }
                    continue;
                } catch (Exception ignored) {
                }

                nameElement = value;
                if (callBack.process(nameElement)) {
                    callBack.over();
                    return;
                }

            }

            callBack.over();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public final static String PAGE_POINT_KEY_POSTED_DELETE = "删除";

    @NotNull
    public IAndroidPager getPostedMoreActivity(@NotNull IDevice deviceId) {
        return getPostedDeleteActivity(deviceId, PAGE_POINT_KEY_POSTED_DELETE, AndroidIdleFishPagerName.PAGER_NAME_POSTED_MORE);
    }


    public final static String PAGE_POINT_KEY_POSTED_DELETE_OK = "确认";

    @NotNull
    public IAndroidPager getPostedDeleteOkActivity(@NotNull IDevice deviceId) {
        return getPostedDeleteActivity(deviceId, PAGE_POINT_KEY_POSTED_DELETE_OK, AndroidIdleFishPagerName.PAGER_NAME_POSTED_DELETE);
    }


    @NotNull
    private IAndroidPager getPostedDeleteActivity(@NotNull IDevice deviceId, String key, AndroidIdleFishPagerName pagerName) {
        String postedActivity = pagerName.NAME_ACTIVITY;
        IAndroidPager iAndroidPager = androidPagerMap.get(createPagerMapKey(deviceId, pagerName));

        return iAndroidPager != null ? iAndroidPager : createHaveCache(deviceId, pagerName,
                postedActivity,
                new PointFilterBuilder()
                        .addContentDesc(key)
                        .next(key)
                        .create()
        );
    }


    public IAndroidPager getAndroidPager(@NotNull IDevice deviceId, @NotNull AndroidIdleFishPagerName pagerName) {
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_POSTED_DELETE)) {
            return getPostedDeleteOkActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_INFO)) {
            return getPostProductInfoActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_ADVERT)) {
            return getAdvertActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_IMAGE_CHOICE)) {
            return getImgChoiceActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_IMAGE_PROCESS)) {
            return getImagProcessActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_LOGIN)) {
            return getLoginActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_MAIN)) {
            return getHomeActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_MAIN_MY)) {
            return getMyActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_POST_CHOICE)) {
            return getPostChoiceActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY)) {
            return getPostProductMoneyActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_OTHER)) {
            return getPostProductOtherActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_TAG)) {
            return getPostProductTagActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_POSTED)) {
            return getPostedActivity(deviceId);
        }
        if (pagerName.equals(AndroidIdleFishPagerName.PAGER_NAME_POSTED_MORE)) {
            return getPostedMoreActivity(deviceId);
        }

        throw new IllegalStateException(pagerName.toString() + " not found IAndroidPager");
    }


    @NotNull
    private IAndroidPager createHaveCache(
            @NotNull IDevice deviceId,
            AndroidIdleFishPagerName pagerName,
            String activityName,
            @NotNull PointFilterBean pointFilterBean) {
        return create(deviceId, pagerName, activityName, pointFilterBean, true);
    }

    @NotNull
    private IAndroidPager createNoCache(
            @NotNull IDevice deviceId,
            AndroidIdleFishPagerName pagerName,
            String activityName,
            @NotNull PointFilterBean pointFilterBean) {
        return create(deviceId, pagerName, activityName, pointFilterBean, false);
    }

    @NotNull
    private IAndroidPager create(
            @NotNull IDevice deviceId,
            AndroidIdleFishPagerName pagerName,
            String activityName,
            @NotNull PointFilterBean pointFilterBean,
            boolean needCache) {

        String pagerMapKey = createPagerMapKey(deviceId, pagerName);

        ActivityPager activityPager = new ActivityPager(
                deviceId,
                activityName,
                pointFilterBean,
                needCache ?
                        UIProcessor.getPointByUIXML(deviceId.getDeviceId(), pagerMapKey, pointFilterBean) :
                        UIProcessor.getPointByUIXMLNoCache(deviceId.getDeviceId(), pagerMapKey, pointFilterBean)
        );
        androidPagerMap.put(pagerMapKey, activityPager);
        return activityPager;
    }


    private String createPagerMapKey(IDevice deviceId, AndroidIdleFishPagerName pagerName) {
        return deviceId.getDeviceId() + pagerName + deviceId.getDxSize().x + deviceId.getDxSize().y;
    }


    private final static class ActivityPager implements IAndroidPager<Point> {

        private final IDevice deviceId;
        private final String activityName;
        private final PointFilterBean pointFilterBean;
        private final Map<String, Point> uiPoint;

        private ActivityPager(IDevice deviceId, String activityName, PointFilterBean pointFilterBean, Map<String, Point> uiPoint) {
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

        @NotNull
        @Override
        public Point getUIPoint(String pointKey) {
            Point point = uiPoint.get(pointKey);
            return point == null ? new Point(0, 0) : point;
        }

        @Override
        public boolean isResume() {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                    if (UIProcessor.getTopActivity(deviceId.getDeviceId()).equals(activityName)) {
                        return true;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return false;

        }

        public String getDeviceId() {
            return deviceId.getDeviceId();
        }
    }


}
