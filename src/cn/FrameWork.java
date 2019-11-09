package cn;

import immortal.half.wu.LogUtil;
import org.jetbrains.annotations.NotNull;

public class FrameWork {
    private static final String TAG = "FrameWork";
    @NotNull
    static String sdf = "info__info__info__info__info__";
    public static void test() {
        for (int i = 0; i < 20000; i++) {
            LogUtil.i(TAG, sdf);
            LogUtil.d(TAG, sdf);
            LogUtil.e(TAG, sdf);
            LogUtil.w(TAG, sdf);
        }
    }

}
