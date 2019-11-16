package immortal.half.wu.executor;

import immortal.half.wu.executor.interfaces.IRunnableListener;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import org.jetbrains.annotations.NotNull;

public class ExecutorManager {

    public static ITimeOutExecutorService createTimeOutExecutorService(Object tag) {
        return new TimeOutThreadPool(tag);
    }

    public static void test() {
        TimeOutThreadPool timeOutThreadPool = new TimeOutThreadPool("TEST");

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {

                for (int i1 = 0; i1 < 100; i1++) {

                    timeOutThreadPool.executeTimeOut(
                            new ITimeOutExecutorService.TimeOutRunnable<Boolean>() {
                                int i = 0;

                                @NotNull
                                @Override
                                public Boolean run() {
                                    for (int j = 0; i < 1; i++) {
                                        try {
                                            Thread.sleep(149);
                                        } catch (Exception e) {
                                            return false;
                                        }
                                        System.out.println("job 运行完");
                                    }
                                    return true;
                                }
                            },
                            new IRunnableListener<Boolean>() {
                                @Override
                                public void onError(@NotNull Exception e) {
                                    System.out.println("job 运行异常" + e.getClass().getSimpleName());
                                }

                                @Override
                                public void onComplete(Boolean result) {
                                    System.out.println("job 运行结束" + result);
                                }
                            },
                            150
                    );

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

}
