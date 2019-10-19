package immortal.half.wu.ui;

import java.util.Map;

public class PointFilterBean {

    private final Map<String, Map<String, String>> uiPagePointFilter;

    public PointFilterBean(Map<String, Map<String, String>> pointFilter) {
        this.uiPagePointFilter = pointFilter;
    }

    public Map<String, Map<String, String>> getPointFilter() {
        return uiPagePointFilter;
    }

}
