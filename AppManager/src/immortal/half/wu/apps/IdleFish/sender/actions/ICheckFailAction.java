package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.interfaces.IDevice;

public interface ICheckFailAction {
    boolean checkFailAction(IDevice iDevice, IADBBuilder adbBuilder, String xml);
}
