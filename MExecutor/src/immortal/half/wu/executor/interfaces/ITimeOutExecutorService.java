package immortal.half.wu.executor.interfaces;


import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;

public interface ITimeOutExecutorService extends ExecutorService {

    <T> void executeTimeOut60s(TimeOutRunnable<T> run, IRunnableListener<T> runResultListener);

    <T> void executeTimeOut120s(TimeOutRunnable<T> run, IRunnableListener<T> runResultListener);

    <T> void executeTimeOut(TimeOutRunnable<T> run, IRunnableListener<T> runResultListener, long outTimeMs);

    interface TimeOutRunnable<T> {
        @NotNull T run() throws Exception;
    }
}
