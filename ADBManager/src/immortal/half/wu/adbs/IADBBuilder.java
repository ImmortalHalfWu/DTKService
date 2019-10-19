package immortal.half.wu.adbs;

import java.awt.*;

public interface IADBBuilder {

    IADBBuilder addClick(Point point);
    IADBBuilder addClick(Point point, int offsetX, int offsetY);
    IADBBuilder addClick(Point[] point);
    IADBBuilder addSwipe(Point start, Point end, int time);
    IADBBuilder addText(String text);
    IADBBuilder addBackClick();
    IADBBuilder addCallBack(ADBRunnable runnable);
    IADBBuilder delayTime(long ms);
    void send(String deviceId);

}
