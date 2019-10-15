package immortal.half.wu.utils;

public class FinalString {

    private static FinalString instance;

    public final String PATH_RES_DIR;
    public final String PATH_KEY_BOARD_APK;
    public final String PATH_ADB_ZIP;

    private FinalString() {
        PATH_RES_DIR = FinalString.class.getResource("").getPath();
        PATH_KEY_BOARD_APK = FinalString.class.getResource("res/ADBKeyboard.apk").getPath();
        PATH_ADB_ZIP = FinalString.class.getResource("res/ADB_MAC.zip").getPath();
    }

    public static FinalString init() {
        if (instance == null) {
            synchronized (FinalString.class) {
                if (instance == null) {
                    instance = new FinalString();
                }
            }
        }
        return instance;
    }
}
