package immortal.half.wu.executor;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

class TimeOutThreadPool implements ITimeOutExecutorService {

    @org.jetbrains.annotations.NotNull
    private final ExecutorService postExecutor;// 定义一个线程池
    @org.jetbrains.annotations.NotNull
    private final ExecutorService pullExecutor;// 定义一个线程池

    TimeOutThreadPool(Object tag) {
        postExecutor = Executors.newSingleThreadExecutor(new CustomThreadFactory("post:" + tag + ":"));
        pullExecutor = Executors.newSingleThreadExecutor(new CustomThreadFactory("pull:" + tag + ":"));
    }

    @Override
    public void shutdown() {
        postExecutor.shutdown();
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public List<Runnable> shutdownNow() {
        return postExecutor.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return postExecutor.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return postExecutor.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, @org.jetbrains.annotations.NotNull @NotNull TimeUnit unit) throws InterruptedException {
        return postExecutor.awaitTermination(timeout, unit);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public <T> Future<T> submit(@org.jetbrains.annotations.NotNull Callable<T> task) {
        return postExecutor.submit(task);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public <T> Future<T> submit(@org.jetbrains.annotations.NotNull Runnable task, T result) {
        return postExecutor.submit(task, result);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public Future<?> submit(@org.jetbrains.annotations.NotNull Runnable task) {
        return postExecutor.submit(task);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public <T> List<Future<T>> invokeAll(@org.jetbrains.annotations.NotNull Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return postExecutor.invokeAll(tasks);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public <T> List<Future<T>> invokeAll(@org.jetbrains.annotations.NotNull Collection<? extends Callable<T>> tasks, long timeout, @org.jetbrains.annotations.NotNull TimeUnit unit) throws InterruptedException {
        return postExecutor.invokeAll(tasks, timeout, unit);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public <T> T invokeAny(@org.jetbrains.annotations.NotNull Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return postExecutor.invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(@org.jetbrains.annotations.NotNull Collection<? extends Callable<T>> tasks, long timeout, @org.jetbrains.annotations.NotNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return postExecutor.invokeAny(tasks, timeout, unit);
    }

    @Override
    public void execute(@org.jetbrains.annotations.NotNull Runnable command) {
        postExecutor.execute(command);
    }

    @Override
    public <T> TimeOutRunnable<T> executeTimeOut60s(TimeOutRunnable<T> run) {
        return executeTimeOut(run, 60 * 1000);
    }

    @Override
    public <T> TimeOutRunnable<T> executeTimeOut120s(TimeOutRunnable<T> run) {
        return executeTimeOut(run, 2 * 60 * 1000);
    }

    @Override
    public <T> TimeOutRunnable<T> executeTimeOut(TimeOutRunnable<T> run, long outTimeMs) {
//        System.out.println("executeTimeOut : " + Thread.currentThread().getName());
        execute(new TimeOutAdapter<>(pullExecutor, run, outTimeMs));
        return run;
    }

}
