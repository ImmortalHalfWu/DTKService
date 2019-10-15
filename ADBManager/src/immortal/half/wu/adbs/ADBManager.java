package immortal.half.wu.adbs;

import immortal.half.wu.FileUtils;
import immortal.half.wu.OSInfo;
import immortal.half.wu.utils.ZIPUtil;

import java.io.File;

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

    public String runInCmd(String cmd) {
        return ADBProcess.getInstance().runInCmd(cmd);
    }

    public String findTopActivity(String deviceAddr) {
        return ADBProcess.getInstance().findTopActivity(deviceAddr);
    }

    public String[] adbFindAllDevice() {
        return ADBProcess.getInstance().adbFindAllDevice();
    }

    public IADBBuilder createBuild() {
        return new ADBBuilder();
    }

}
