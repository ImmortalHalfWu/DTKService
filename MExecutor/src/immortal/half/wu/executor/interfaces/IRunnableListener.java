package immortal.half.wu.executor.interfaces;

import com.sun.istack.internal.Nullable;

public interface IRunnableListener<resultType> {

    void onError(Exception e);

    void onComplete(@Nullable resultType result);

}