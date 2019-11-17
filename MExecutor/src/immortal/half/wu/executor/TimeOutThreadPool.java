package immortal.half.wu.executor;

import immortal.half.wu.executor.interfaces.IJobListener;
import immortal.half.wu.executor.interfaces.IJobWithTimeOut;
import immortal.half.wu.executor.interfaces.ITimeOutExecutorService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    public boolean awaitTermination(long timeout, @NotNull TimeUnit unit) throws InterruptedException {
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
    public <T> void executeTimeOut60s(@NotNull IJobWithTimeOut<T> run, @Nullable IJobListener<T> runResultListener) {
        executeTimeOut(run, runResultListener, 60 * 1000);
    }

    @Override
    public <T> void executeTimeOut120s(@NotNull IJobWithTimeOut<T> run, @Nullable IJobListener<T> runResultListener) {
        executeTimeOut(run, runResultListener, 2 * 60 * 1000);
    }

    @Override
    public <T> void executeTimeOut(@NotNull IJobWithTimeOut<T> run, @Nullable IJobListener<T> runResultListener, long outTimeMs) {
        execute(new TimeOutAdapter<T>(pullExecutor, run, runResultListener, outTimeMs));
    }

}
