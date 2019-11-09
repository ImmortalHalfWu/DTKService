package immortal.half.wu.apps.IdleFish.pagers;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AndroidIdleFishPagerName {

    public final static AndroidIdleFishPagerName PAGER_NAME_LOGIN = new AndroidIdleFishPagerName(0, "UserLoginActivity", "登录");
    public final static AndroidIdleFishPagerName PAGER_NAME_ADVERT = new AndroidIdleFishPagerName(1, "AdvertActivity", "广告");
    public final static AndroidIdleFishPagerName PAGER_NAME_MAIN = new AndroidIdleFishPagerName(2, "MainActivity", "主页");
    public final static AndroidIdleFishPagerName PAGER_NAME_MAIN_MY = new AndroidIdleFishPagerName(3, "MainActivity", "主页我的");
    public final static AndroidIdleFishPagerName PAGER_NAME_POST_CHOICE = new AndroidIdleFishPagerName(4, "PublishEntryActivity", "选择发布方式页");
    public final static AndroidIdleFishPagerName PAGER_NAME_IMAGE_CHOICE = new AndroidIdleFishPagerName(5, "MultiMediaStudioActivity", "发布商品图片选择");
    public final static AndroidIdleFishPagerName PAGER_NAME_IMAGE_PROCESS = new AndroidIdleFishPagerName(6, "MultiMediaEditorActivity", "发布商品图片处理");
    public final static AndroidIdleFishPagerName PAGER_NAME_POST_PRODUCT_INFO = new AndroidIdleFishPagerName(7, "FishFlutterActivity", "发布商品设置商品信息");
    public final static AndroidIdleFishPagerName PAGER_NAME_POST_PRODUCT_MONEY = new AndroidIdleFishPagerName(8, "FishFlutterActivity", "发布商品设置商品价格");
    public final static AndroidIdleFishPagerName PAGER_NAME_POST_PRODUCT_OTHER = new AndroidIdleFishPagerName(9, "FishFlutterActivity", "发布商品的其他设置");
    public final static AndroidIdleFishPagerName PAGER_NAME_POST_PRODUCT_TAG = new AndroidIdleFishPagerName(10, "FishFlutterActivity", "发布商品设置商品标签");
    public final static AndroidIdleFishPagerName PAGER_NAME_POSTED = new AndroidIdleFishPagerName(11, "WeexWebViewActivity", "已发布商品页");
    public final static AndroidIdleFishPagerName PAGER_NAME_POSTED_MORE = new AndroidIdleFishPagerName(12, "WeexWebViewActivity", "已发布商品更多选项");
    public final static AndroidIdleFishPagerName PAGER_NAME_POSTED_DELETE = new AndroidIdleFishPagerName(13, "WeexWebViewActivity", "已发布商品更多选项删除");

    private final int tag;
    public final String NAME_ACTIVITY;
    public final String NAME;

    private AndroidIdleFishPagerName(int tag, String activityName, String pageName) {
        this.tag = tag;
        this.NAME_ACTIVITY = activityName;
        this.NAME = pageName;
    }

    public int getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AndroidIdleFishPagerName)) return false;
        AndroidIdleFishPagerName that = (AndroidIdleFishPagerName) o;
        return tag == that.tag &&
                Objects.equals(NAME, that.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, NAME);
    }

    @NotNull
    @Override
    public String toString() {
        return NAME_ACTIVITY + NAME;
    }
}
