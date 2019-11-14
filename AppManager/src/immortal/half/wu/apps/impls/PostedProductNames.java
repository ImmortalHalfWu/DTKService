package immortal.half.wu.apps.impls;

import immortal.half.wu.apps.SimpleProcessPostedProductCallBack;
import immortal.half.wu.apps.interfaces.IActionCallBack;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PostedProductNames extends SimpleProcessPostedProductCallBack {

    private final IActionCallBack<List<String>> callBack;

    public PostedProductNames(IDevice deviceId, IActionCallBack<List<String>> callBack) {
        super(deviceId);
        this.callBack = callBack;
    }

    @Override
    protected void over(boolean isOver, @NotNull Set<String> names) {
        if (isOver) {
            names.remove("擦亮");
            names.remove("更多");
            callBack.onComplete(new ArrayList<>(names));
        }
    }

    @Override
    public void notFound() {
        callBack.onComplete(new ArrayList<>(names));
    }


    public interface CallBack {
        void names(Set<String> names);
    }
}
