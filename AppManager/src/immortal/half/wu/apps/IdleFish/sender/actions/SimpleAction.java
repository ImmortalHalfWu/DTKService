package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

public class SimpleAction implements IAction {

    private static final String TAG = "SimpleAction";

    private static final int CHECK_TYPE_ACTIVITY_NAME = 0;
    private static final int CHECK_TYPE_XML = 1;

    private final boolean needXMLCache;
    private final AndroidIdleFishPagerName androidIdleFishPagerName;
    private final int checkType;
    private final String checkString;

    private ICheckSucAction checkSucAction;

    private ICheckFailAction checkFailAction;


    static SimpleAction newInstanceName(
            @NotNull AndroidIdleFishPagerName androidIdleFishPagerName
    ) {
        return new SimpleAction(false, androidIdleFishPagerName, CHECK_TYPE_ACTIVITY_NAME, androidIdleFishPagerName.NAME_ACTIVITY);
    }

    static SimpleAction newInstanceXML(
            AndroidIdleFishPagerName androidIdleFishPagerName,
            String checkString
    ) {
        return new SimpleAction(false, androidIdleFishPagerName, CHECK_TYPE_XML, checkString);
    }

    SimpleAction(boolean needXMLCache, AndroidIdleFishPagerName androidIdleFishPagerName, int checkType, String checkString) {
        this.needXMLCache = needXMLCache;
        this.androidIdleFishPagerName = androidIdleFishPagerName;
        this.checkType = checkType;
        this.checkString = checkString;
    }

    @Override
    public void action(IDevice iDevice, IADBBuilder adbBuilder) {
        action(iDevice, adbBuilder, androidIdleFishPagerName);
    }

    public void action(IDevice iDevice, IADBBuilder adbBuilder, AndroidIdleFishPagerName androidIdleFishPagerName) {
        if (checkSucAction != null) {
            checkSucAction.checkSucAction(iDevice, adbBuilder, androidIdleFishPagerName);
        }
    }

    @Override
    public boolean xmlCache(IDevice iDevice) {
        return needXMLCache;
    }

    @NotNull
    @Override
    public String xmlFileName(@NotNull IDevice iDevice) {
        return createFileName(iDevice, androidIdleFishPagerName);
    }

    @Override
    public boolean check(@NotNull IDevice iDevice, IADBBuilder adbBuilder, @NotNull String xml) {

        boolean checkResult = false;
        try {
            if (checkType == 0) {
                checkResult = ADBManager.getInstance().findTopActivityName(iDevice.getDeviceId()).equals(checkString);
            } else {
                checkResult = xml.contains(checkString);
            }
            if (!checkResult && checkFailAction != null) {
                return checkFailAction.checkFailAction(iDevice, adbBuilder, xml);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e);
        }

        return checkResult;
    }


    private static String createFileName(IDevice deviceId, AndroidIdleFishPagerName pagerName) {
        return deviceId.getDeviceId() + pagerName + deviceId.getDxSize().x + deviceId.getDxSize().y;
    }

    @NotNull
    public SimpleAction setCheckSucAction(ICheckSucAction checkSucAction) {
        this.checkSucAction = checkSucAction;
        return this;
    }

    @NotNull
    public SimpleAction setCheckFailAction(ICheckFailAction checkFailAction) {
        this.checkFailAction = checkFailAction;
        return this;
    }
}
