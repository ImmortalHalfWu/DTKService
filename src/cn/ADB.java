package cn;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.adbs.ADBProcess;
import immortal.half.wu.adbs.ADBRunnable;


public class ADB {

    public static void test() {
        ADBManager.getInstance().createBuild().addCallBack(new ADBRunnable() {
            @Override
            public void run(ADBProcess adbProcess) {
                String strings = adbProcess.adbFindAllDevice();
            }
        });

    }

}
