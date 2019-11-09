package immortal.half.wu.apps.IdleFish.sender;


import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.interfaces.IDevice;
import immortal.half.wu.ui.UIProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class ActionRunner implements Runnable {

    @NotNull
    private final IDevice iDevice;
    private final String deviceId;
    private final List<IAction> actions;
    private final IActionException iActionException;

    ActionRunner(IDevice iDevice, List<IAction> actions, IActionException iActionException) {
        this.iDevice = iDevice;
        this.actions = actions;
        this.iActionException = iActionException;

        deviceId = iDevice.getDeviceId();
    }

    @Override
    public void run() {
        IADBBuilder adbBuilder;
        String xmlString;
        boolean doubleCheck = false;

        for (int i = 0; i < actions.size(); ) {

            adbBuilder = ADBManager.getInstance().createBuild();

            if (i < 0) {
                callBackException();
                return;
            }

            IAction iAction = actions.get(i);

            String fileName = iAction.xmlFileName(iDevice);

            xmlString = doubleCheck || !iAction.xmlCache(iDevice) ?
                    UIProcessor.androidUIXMLNoCache(deviceId, fileName) :
                    UIProcessor.androidUIXMLHaveCache(deviceId, fileName);

            if (!iAction.check(iDevice, adbBuilder, xmlString)) {
                i--;
                doubleCheck = true;
                continue;
            }

            iAction.action(iDevice, adbBuilder);

            doubleCheck = false;
            i++;
        }

    }

    private void callBackException() {

        if (iActionException != null) {
            iActionException.fail(new IllegalStateException("处理未完成，action回归检测到头部仍未处理"));
        }

    }

}