package immortal.half.wu.apps.IdleFish.sender;

import immortal.half.wu.apps.interfaces.IDevice;

import java.util.ArrayList;
import java.util.List;

class ActionBuilder {

    private List<IAction> objects;

    ActionBuilder() {
        objects = new ArrayList<>();
    }

    public ActionBuilder addAction(IAction action) {
        objects.add(action);
        return this;
    }

    Runnable build(IDevice iDevice, IActionException actionException) {
        return new ActionRunner(iDevice, objects, actionException);
    }

}
