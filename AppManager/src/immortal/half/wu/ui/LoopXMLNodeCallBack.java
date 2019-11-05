package immortal.half.wu.ui;

import org.dom4j.Element;

public interface LoopXMLNodeCallBack {

    /**
     * @param element 遍历中，当前节点
     * @return 是否结束， true则停止遍历
     */
    boolean node(Element element);


    /**
     * 遍历结束
     */
    void over();

}
