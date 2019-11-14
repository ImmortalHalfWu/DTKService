package immortal.half.wu.executor.interfaces;


import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;

public interface ITimeOutExecutorService extends ExecutorService {

    <T> TimeOutRunnable<T> executeTimeOut60s(TimeOutRunnable<T> run);

    <T> TimeOutRunnable<T> executeTimeOut120s(TimeOutRunnable<T> run);

    <T> TimeOutRunnable<T> executeTimeOut(TimeOutRunnable<T> run, long outTimeMs);

    interface TimeOutRunnable<T> extends IRunnableListener<T> {
        @NotNull T run() throws Exception;
    }
}
