package immortal.half.wu.apps.impls;

import immortal.half.wu.apps.SimpleProcessPostedProductCallBack;
import immortal.half.wu.apps.interfaces.IDevice;

import java.util.Set;

public class PostedProductNames extends SimpleProcessPostedProductCallBack {

    private final CallBack callBack;

    public PostedProductNames(IDevice deviceId, CallBack callBack) {
        super(deviceId);
        this.callBack = callBack;
    }

    @Override
    protected void over(boolean isOver, Set<String> names) {
        if (isOver) {
            callBack.names(names);
        }
    }

    @Override
    public void notFound() {
        callBack.names(names);
    }


    public interface CallBack {
        void names(Set<String> names);
    }
}
