package immotal.half.wu.appManager;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import immotal.half.wu.appManager.beans.IdleFishProductBean;
import immotal.half.wu.appManager.impls.idlefishs.FishIdleApp;
import immotal.half.wu.appManager.interfaces.IApp;
import immotal.half.wu.appManager.pagers.beans.UserInfoBean;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;

import static immotal.half.wu.appManager.impls.idlefishs.IdleFishModel.IDLE_FISH_PACKAGE_NAME;

public class AppFactory {

    @org.jetbrains.annotations.NotNull
    @Contract("_, _ -> new")
    public static IApp<IdleFishProductBean, UserInfoBean> createFishIdleApp(@org.jetbrains.annotations.NotNull String deviceId, @org.jetbrains.annotations.NotNull ITimeOutExecutorService executorService) {
        @NotNull String[] strings = ADBManager.getInstance().adbAllAppPackage(deviceId);
        if (Arrays.toString(strings).contains(IDLE_FISH_PACKAGE_NAME)) {
            return new FishIdleApp(deviceId, executorService);
        }
        throw new IllegalStateException(deviceId + "并没有安装闲鱼");
    }

}
