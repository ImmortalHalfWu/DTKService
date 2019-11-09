package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

public class PageActionPostProductMoney {

    @NotNull
    public static IAction newPostProductMoneyAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY, "index=\"1\" text=\"包邮\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder
                                        .addCallBack(adbProcess -> instance().getPostProductMoneyActivity(iDevice))
                                        .addBackClick()
                                        .send(iDevice.getDeviceId())
                );
    }


    @NotNull
    public static IAction newPostProductMoneyAction(@NotNull String outMoney, @NotNull String inMoney) {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY, "index=\"1\" text=\"包邮\"")
                .setCheckSucAction(new ICheckSucAction() {
                                       @Override
                                       public void checkSucAction(@NotNull IDevice iDevice, @NotNull IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                                           /*

    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY = "一口价¥";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL = "入手价¥";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL = "包邮";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK = "确认";
                                            */
                                           char[] chars = outMoney.toCharArray();
                                           IAndroidPager postProductInfoActivity = instance().getPostProductMoneyActivity(iDevice);
                                           adbBuilder.addClick(postProductInfoActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_SELL));
                                           for (char aChar : chars) {
                                               adbBuilder.addClick(postProductInfoActivity.getUIPoint(String.valueOf(aChar)));
                                           }
                                           adbBuilder.addClick(postProductInfoActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_KEY_OK));

                                           chars = inMoney.toCharArray();
                                           adbBuilder.addClick(postProductInfoActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_BUY));
                                           for (char aChar : chars) {
                                               adbBuilder.addClick(postProductInfoActivity.getUIPoint(String.valueOf(aChar)));
                                           }
                                           adbBuilder.addClick(postProductInfoActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_KEY_OK))
                                                   .addClick(postProductInfoActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_MILL))
                                                   .addClick(postProductInfoActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_MONEY_OK))
                                                   .send(iDevice.getDeviceId());
                                       }
                                   }
                );
    }

}
