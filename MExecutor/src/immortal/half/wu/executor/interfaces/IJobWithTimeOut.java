package immortal.half.wu.executor.interfaces;

import org.jetbrains.annotations.Nullable;

public interface IJobWithTimeOut<T> {
    @Nullable T run() throws Exception;
}
