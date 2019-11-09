package immortal.half.wu.apps.IdleFish.sender;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

public interface IAction {
    void action(IDevice iDevice, IADBBuilder adbBuilder);

    boolean xmlCache(IDevice iDevice);

    @NotNull String xmlFileName(IDevice iDevice);

    boolean check(IDevice iDevice, IADBBuilder adbBuilder, String xml);
}
