package immortal.half.wu.executor.interfaces;

import com.sun.istack.internal.Nullable;

public interface IRunnableListener<resultType> {

    void onError(Exception e) throws Exception;

    void onComplete(@Nullable resultType result) throws Exception;

}