package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

public class PageActionHomeMy {


    @NotNull
    public static IAction newFindUserInfoAction(@Nullable UserInfoCallBack callBack) {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_MAIN, "text=\"去炫耀\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> {
                            if (callBack != null) {
                                Map<String, String> myInfoActivity = instance().getMyInfoActivity(iDevice);

                                callBack.result(
                                        myInfoActivity.get(PAGE_POINT_KEY_HOME_MY_USER_NAME),
                                        myInfoActivity.get(PAGE_POINT_KEY_HOME_MY_POSTED_NUM)
                                );
                            }
                        }
                );
    }


    @NotNull
    public static IAction newGoPostedAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_MAIN, "text=\"我发布的\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> {
                            adbBuilder.addClick(
                                    instance().getMyActivity(iDevice)
                                            .getUIPoint(PAGE_POINT_KEY_HOME_MY_POSTED)
                            ).send(iDevice.getDeviceId());
                        }
                );
    }


    @NotNull
    public static IAction newIsLogin(IsLoginCallBack callBack) {
        LoginCheckSimpleCallBack loginCheckSimpleCallBack = new LoginCheckSimpleCallBack(callBack);
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_MAIN, "text=\"去炫耀\"")
                .setCheckSucAction(loginCheckSimpleCallBack)
                .setCheckFailAction(loginCheckSimpleCallBack);
    }

    public interface UserInfoCallBack {
        void result(String name, String postedNum);
    }

    public interface IsLoginCallBack {
        void isLogin(boolean isLogin);
    }

    private static final class LoginCheckSimpleCallBack implements ICheckSucAction, ICheckFailAction {

        @Nullable
        private IsLoginCallBack callBack;

        private LoginCheckSimpleCallBack(@Nullable IsLoginCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public boolean checkFailAction(IDevice iDevice, IADBBuilder adbBuilder, String xml) {
            if (callBack != null) {
                callBack.isLogin(false);
                callBack = null;
            }
            return true;
        }

        @Override
        public void checkSucAction(IDevice iDevice, IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
            if (callBack != null) {
                callBack.isLogin(true);
                callBack = null;
            }
        }
    }
}
