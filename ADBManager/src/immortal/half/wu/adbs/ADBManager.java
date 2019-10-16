package immortal.half.wu.adbs;

import immortal.half.wu.FileUtils;
import immortal.half.wu.OSInfo;
import immortal.half.wu.utils.ZIPUtil;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ADBManager {

    private static ADBManager instance;

    public static ADBManager getInstance() {
        if (instance == null) {
            synchronized (ADBManager.class) {
                if (instance == null) {
                    instance = new ADBManager();
                }
            }
        }
        return instance;
    }

    private ADBManager() {
        initFile();
    }

    private void initFile() {

        FileUtils.init();

        try {
            FileUtils.copy(new File(FileUtils.getKeyboardAPKPath()), new File(FileUtils.DIR_PATH_OTHER + FileUtils.FILE_NAME_KEY_BOARD_APK));

            if (OSInfo.isWindows()) {
                FileUtils.copy(new File(FileUtils.getADBZipPath()), new File(FileUtils.DIR_PATH_OTHER + FileUtils.FILE_NAME_ADB_WIN));
                ZIPUtil.unZip(new File(FileUtils.DIR_PATH_OTHER + FileUtils.FILE_NAME_ADB_WIN), FileUtils.DIR_PATH_ADB);
            }

            if (OSInfo.isMacOS() || OSInfo.isMacOSX()) {
                FileUtils.copy(new File(FileUtils.getADBZipPath()), new File(FileUtils.DIR_PATH_OTHER + FileUtils.FILE_NAME_ADB_MAC));
                ZIPUtil.unZip(new File(FileUtils.DIR_PATH_OTHER + FileUtils.FILE_NAME_ADB_MAC), FileUtils.DIR_PATH_ADB);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public IADBBuilder createBuild() {
        return new ADBBuilder();
    }

    public String runInCmd(String cmd) {
        return ADBUtils.runInCmd(cmd);
    }

    public String findTopActivity(String deviceAddr) {
        return ADBUtils.findTopActivity(deviceAddr);
    }

    public String[] adbFindAllDevice() {

        String s = ADBProcess.getInstance().adbFindAllDevice();
        s = s.replace("List of devices attached\n", "").replace("\n\n", "\n");
        String[] split = s.split("\n");

        ArrayList<String> returnStrings = new ArrayList<>(split.length);

        for (String nn :
                split) {
            if (nn.contains("offline")) {
                continue;
            }
            String address = nn.split("\t")[0];
            returnStrings.add(address);
        }

        return returnStrings.toArray(new String[0]);
    }

    public String[] adbAllAppPackage(String deviceAddr) {
        return ADBUtils.adbAllAppPackage(deviceAddr).split("\r\n");
    }

    public boolean isInstallApp(String deviceAddr, String appPackageName) {
        return ADBUtils.adbAllAppPackage(deviceAddr).contains(appPackageName);
    }

    public void installApp(String deviceId, String apkPath, String packageName) {
        ADBUtils.adbInstallApk(deviceId, apkPath, packageName);
    }

    public void uninstallApp(String deviceId, String appPackageName) {
        ADBUtils.adbUNInstallApk(deviceId, appPackageName);
    }

    public void choiceTextInputKeyBoard(String deviceId) {
        ADBUtils.adbChangeKeyBoard(deviceId);
    }

    public Point getDxSize(String deviceId) {
        String size = ADBUtils.adbWmSize(deviceId)
                .replace("Physical size: ", "")
                .replace("\r\n", "");
        String[] xes = size.split("x");
        return new Point(Integer.parseInt(xes[0]), Integer.parseInt(xes[1]));
    }
}
