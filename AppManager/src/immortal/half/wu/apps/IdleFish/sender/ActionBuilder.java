package immortal.half.wu.apps.IdleFish.sender;

import immortal.half.wu.apps.interfaces.IActionCallBack;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class ActionBuilder {

    private List<IAction> objects;

    ActionBuilder() {
        objects = new ArrayList<>();
    }

    @NotNull
    public ActionBuilder addAction(IAction action) {
        objects.add(action);
        return this;
    }

    @NotNull Runnable build(@NotNull IDevice iDevice, IActionCallBack actionException) {
        return new ActionRunner(iDevice, objects, actionException);
    }

}
