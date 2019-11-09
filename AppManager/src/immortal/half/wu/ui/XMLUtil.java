package immortal.half.wu.ui;

import immortal.half.wu.FileUtils;
import org.dom4j.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.*;

public class XMLUtil {

    private final static String[] homePageUITextIndex = {
            "图片未选中",
    };


    public static Element findRootElement(@NotNull String xml) throws DocumentException {
        return DocumentHelper.parseText(xml).getRootElement();
    }

    /**
     * 获取文档中的所有指定元素
     *
     * @param rootElement 根元素
     * @param tagName     指定元素名称
     * @param elements    存放集合
     * @return 文档中的所有指定元素
     */
    @NotNull
    public static List<Element> findAllElementByTagName(Element rootElement, String tagName, @NotNull List<Element> elements) {

        List<Element> mElements = rootElement.elements(QName.get(tagName));
        for (Element element :
                mElements) {
            elements.add(element);
            findAllElementByTagName(element, tagName, elements);
        }

        return elements;
    }


    /**
     * 获取文档中的所有指定元素Point
     *
     * @param rootElement    根元素
     * @param filterString   text 文本过滤
     * @param resultElements 存放集合
     * @return 文档中的所有指定元素的Point
     */
    @NotNull
    public static List<Point> findElementPointByKeyValue(@NotNull Element rootElement, @NotNull List<String> filterString, @NotNull List<Point> resultElements) {


        List<Element> elementByStartWith = findElementByStartWith(rootElement, filterString, new ArrayList<>());

        for (Element element :
                elementByStartWith) {
            resultElements.add(getElementBoundsCenter(element));
        }

        return resultElements;
    }

    /**
     * 获取文档中的所有指定元素
     *
     * @param rootElement    根元素
     * @param filterString   text 文本过滤
     * @param resultElements 存放集合
     * @return 文档中的所有指定元素的
     */
    @NotNull
    public static List<Element> findElementByStartWith(Element rootElement, @NotNull List<String> filterString, @NotNull List<Element> resultElements) {

        List<Element> mElements = rootElement.elements(QName.get("node"));
        for (Element element :
                mElements) {

            if (!attrBoundsIsNull(element)) {

                for (String value : filterString) {
                    if (element.attribute("text").getValue().startsWith(value) || element.attribute("content-desc").getValue().startsWith(value)) {

                        resultElements.add(element);

                        break;
                    }
                }

            }

            findElementByStartWith(element, filterString, resultElements);
        }

        return resultElements;
    }

    /**
     * 获取文档中的所有text
     *
     * @param rootElement 根元素
     * @return 文档中的所有指定元素的
     */
    @NotNull
    public static List<String> findElementByTextNotNull(Element rootElement, @NotNull List<String> resultStrings) {

        List<Element> mElements = rootElement.elements(QName.get("node"));
        String string;
        for (Element element :
                mElements) {

            if (!FileUtils.isEmpty(string = element.attribute("text").getValue()) || !FileUtils.isEmpty(string = element.attribute("content-desc").getValue())) {
                resultStrings.add(string);
            }

            findElementByTextNotNull(element, resultStrings);
        }

        return resultStrings;
    }


    /**
     * @param rootElement 根元素
     * @param key         查找第一个，attr中包含指定键值对的元素
     * @param value       键值对
     * @return 查找第一个，attr中包含指定键值对的元素
     */
    public static Element findElementByNodeKeyValue(Element rootElement, String key, String value) {

        List<Element> elements = rootElement.elements();
        for (Element element :
                elements) {
            Attribute attribute = element.attribute(key);
            if (attribute != null && attribute.getValue() != null && attribute.getValue().equals(value)) {
                return element;
            }
            if ((element = findElementByNodeKeyValue(element, key, value)) != null) {
                return element;
            }
        }

        return null;
    }

    /**
     * @param rootElement 根元素
     * @param key         查找第一个，attr中包含指定键值对的元素
     * @param value       键值对
     * @return 查找第一个，attr中包含指定键值对的元素
     */
    public static Element findElementByNodeStartWithKeyValue(Element rootElement, String key, @NotNull String value) {

        List<Element> elements = rootElement.elements();
        for (Element element :
                elements) {
            Attribute attribute = element.attribute(key);
            if (attribute != null && attribute.getValue() != null && attribute.getValue().startsWith(value)) {
                return element;
            }
            if ((element = findElementByNodeStartWithKeyValue(element, key, value)) != null) {
                return element;
            }
        }

        return null;
    }

    /**
     * @param elements 指定元素集合
     * @return 所有text字段的值
     */
    @NotNull
    public static List<String> findTextByElements(List<Element> elements) {

        ArrayList<String> strings = new ArrayList<>(elements.size());
        String string;
        for (Element element : elements
        ) {

            if (!FileUtils.isEmpty(string = element.attribute("text").getValue()) || !FileUtils.isEmpty(string = element.attribute("content-desc").getValue())) {
                strings.add(string);
            }
        }

        return strings;
    }

    /**
     * @param nodes 元素列表
     * @return 打印所有元素的text content-desc bounds的值
     */
    public static Element printElementByNodeText(List<Element> nodes) {
        for (int i = 0; i < nodes.size(); i++) {

            Element element = nodes.get(i);
            if (attrBoundsIsNull(element)) {
                continue;
            }

            Attribute text = element.attribute("text");
            Attribute contentDesc = element.attribute("content-desc");
            String contentText = text != null && text.getValue() != null && text.getValue().length() > 0 ? text.getValue() : "";
            contentText = contentText.length() != 0 ? contentText : contentDesc != null && contentDesc.getValue() != null && contentDesc.getValue().length() > 0 ? contentDesc.getValue() : "";

            if (contentText.length() == 0) {
                continue;
            }
//            System.out.println("\"" + contentText + "\"" + ",");

        }
        return nodes.get(0);
    }

    /**
     * @param nodes     指定元素列表
     * @param filterMap 指定文本内容
     * @return 删除元素列表中，content-desc 或 text 包含 指定string的元素 或者 为空
     */
    @NotNull
    public static List<Element> removeElementByAttrTextWithNull(List<Element> nodes, @NotNull List<String> filterMap) {

        List<Element> removeElement = new ArrayList<>();

        String string;
        for (Element element : nodes) {
            if (!FileUtils.isEmpty(string = element.attribute("text").getValue()) || !FileUtils.isEmpty(string = element.attribute("content-desc").getValue())) {

                for (String item : filterMap) {
                    if (string.contains(item)) {
                        removeElement.add(element);
                    }
                }

            } else {
                removeElement.add(element);
            }
        }
        nodes.removeAll(removeElement);
        return nodes;
    }

    /**
     * 获取文档中的所有指定元素
     *
     * @param rootElement 根元素
     * @return 文档中的所有指定元素
     */
    private static void loopXMLNodes(
            @NotNull Element rootElement,
            LoopXMLNodeCallBack callBack
    ) {

        callBack.node(rootElement);

        //递归遍历当前节点所有的子节点
        List<Element> listElement = rootElement.elements();//所有一级子节点的list
        for (Element e : listElement) {//遍历所有一级子节点
            loopXMLNodes(e, callBack);//递归
        }

    }


    /**
     * 获取文档中的所有指定元素
     *
     * @param rootElement 根元素
     * @param resultMap   存放集合
     * @return 文档中的所有指定元素
     */
    @NotNull
    static Map<String, Point> findAllPointByAttrKeyValue(
            @NotNull Element rootElement,
            @NotNull Map<String, Point> resultMap,
            @NotNull Map<String, Map<String, String>> filterMap) {


        loopXMLNodes(rootElement, new LoopXMLNodeCallBack() {
            @Override
            public boolean node(@NotNull Element element) {
                //当前节点的名称、文本内容和属性
                Set<String> resultKeys = filterMap.keySet();

                for (String resultKey :
                        resultKeys) {

                    if (resultMap.get(resultKey) != null) {
                        continue;
                    }

                    Map<String, String> filterMaps = filterMap.get(resultKey);

                    for (Iterator<String> iterator = filterMaps.keySet().iterator(); ; ) {

                        String filterKey = iterator.next();

                        Attribute attribute = element.attribute(QName.get(filterKey));

//                if (attribute != null) {
//                    System.out.println(attribute.getValue());
//                }

                        if (attribute == null || !attribute.getValue().startsWith(filterMaps.get(filterKey))) {
                            break;
                        }


                        if (!iterator.hasNext()) {
                            resultMap.put(resultKey, getElementBoundsCenter(element));
                            if (resultMap.size() == filterMap.size()) {
                                return true;
                            }
                            break;
                        }

                    }
                }

                return resultMap.size() == filterMap.size();
            }

            @Override
            public void over() {
            }
        });


        return resultMap;
    }

    /**
     * 获取文档中的所有指定元素
     */
    @NotNull
    public static List<Point> findAllPointByAttrKeyValue(
            @NotNull Element rootElement,
            @NotNull List<Point> resultPoints,
            String key,
            String value) {

        loopXMLNodes(rootElement, new LoopXMLNodeCallBack() {
            @Override
            public boolean node(@NotNull Element element) {

                Attribute attribute = element.attribute(QName.get(key));

                if (attribute != null && attribute.getValue().equals(value)) {
                    resultPoints.add(getElementBoundsCenter(element));
                }

                return false;
            }

            @Override
            public void over() {

            }
        });

        return resultPoints;
    }

    /**
     * 获取文档中的所有指定元素
     */
    @NotNull
    public static Map<String, String> findTextByAttrKeyValues(
            @NotNull Element rootElement,
            @NotNull Map<String, String> resultMap,
            @NotNull Map<String, Map<String, String>> filterMap) {

        loopXMLNodes(rootElement, new LoopXMLNodeCallBack() {
            @Override
            public boolean node(@NotNull Element element) {

                //当前节点的名称、文本内容和属性
                Set<String> resultKeys = filterMap.keySet();

                for (String resultKey :
                        resultKeys) {

                    if (resultMap.get(resultKey) != null) {
                        continue;
                    }

                    Map<String, String> filterMaps = filterMap.get(resultKey);

                    for (Iterator<String> iterator = filterMaps.keySet().iterator(); ; ) {

                        String filterKey = iterator.next();

                        Attribute attribute = element.attribute(QName.get(filterKey));

                        if (attribute == null || !attribute.getValue().startsWith(filterMaps.get(filterKey))) {
                            break;
                        }


                        if (!iterator.hasNext()) {
                            resultMap.put(resultKey, element.attributeValue("text"));
                            if (resultMap.size() == filterMap.size()) {
                                return true;
                            }
                            break;
                        }

                    }
                }

                return resultMap.size() == filterMap.size();
            }

            @Override
            public void over() {

            }
        });

        return resultMap;
    }


    /**
     * 获取文档中的所有指定元素
     */
    public static Point findPointByAttrKeyValueEndWith(
            Element rootElement,
            String key,
            @NotNull String value) {

        //当前节点的名称、文本内容和属性

        Attribute attribute = rootElement.attribute(QName.get(key));
        if (attribute != null) {
//            System.out.println(attribute.getValue());
        }
        if (attribute != null && attribute.getValue().endsWith(value)) {
            return getElementBoundsCenter(rootElement);
        }

        //递归遍历当前节点所有的子节点
        List<Element> listElement = rootElement.elements();//所有一级子节点的list
        for (Element e : listElement) {//遍历所有一级子节点
            Point point = findPointByAttrKeyValueEndWith(e, key, value);//递归
            if (point.x != 0 && point.y != 0) {
                return point;
            }
        }

        return new Point(0, 0);
    }


    /**
     * @param nodes     指定元素列表
     * @param filterMap 指定文本内容
     * @return 查找元素列表中，content-desc 或 text 包含 指定string的元素及坐标中心
     */
    @NotNull
    public static Map<String, Point> findElementByAttrTextStartWith(List<Element> nodes, List<String> filterMap) {
        List<String> filterStringKey = new ArrayList<>();
        filterStringKey.add("content-desc");
        filterStringKey.add("text");

        Map<String, Point> resultMap = new LinkedHashMap<>(filterMap.size());

        for (Element element : nodes) {

            if (attrBoundsIsNull(element)) {
                continue;
            }

            for (String key : filterStringKey) {
                for (String value :
                        filterMap) {
                    if (element.attribute(key).getValue().startsWith(value)) {

                        Point elementBoundsCenter = getElementBoundsCenter(element);
                        resultMap.put(key, elementBoundsCenter);

                        break;
                    }
                }
            }
        }

        return resultMap;
    }

    /**
     * @param element 指定元素
     * @return bounds属性是否为null
     */
    public static boolean attrBoundsIsNull(Element element) {
        Attribute bounds = element.attribute("bounds");
        return bounds == null ||
                bounds.getValue().length() == 0 ||
                bounds.getValue().equals("[0,0][0,0]");
    }

    /**
     * @param element 指定元素
     * @return 获取指定元素bounds属性记录的中心位置
     */
    public static Point getElementBoundsCenter(@NotNull Element element) {
        if (attrBoundsIsNull(element)) {
            return new Point(0, 0);
        }
        Point elementLeftTopPoint = getElementLeftTopPoint(element);
        Point elementRightBottomPoint = getElementRightBottomPoint(element);

        return getCenterPoint(
                elementLeftTopPoint.x,
                elementLeftTopPoint.y,
                elementRightBottomPoint.x,
                elementRightBottomPoint.y);
    }

    public static Point getElementLeftTopPoint(@NotNull Element element) {
        if (attrBoundsIsNull(element)) {
            return new Point(0, 0);
        }

        String[] lifeTop = getElementBoundsSplit(element)[0].split(",");
        int left = Integer.parseInt(lifeTop[0]);
        int top = Integer.parseInt(lifeTop[1]);

        return new Point(left, top);
    }

    public static Point getElementRightBottomPoint(@NotNull Element element) {
        if (attrBoundsIsNull(element)) {
            return new Point(0, 0);
        }

        String[] rightBottom = getElementBoundsSplit(element)[1].split(",");
        int right = Integer.parseInt(rightBottom[0]);
        int bottom = Integer.parseInt(rightBottom[1]);

        return new Point(right, bottom);

    }

    @NotNull
    private static String[] getElementBoundsSplit(Element element) {

        Attribute bounds = element.attribute("bounds");
        String value = bounds.getValue();
        value = value.replace("][", "@@");
        String[] split = value.split("@@");
        split[0] = split[0].replace("[", "");
        split[1] = split[1].replace("]", "");
        return split;
    }


    /**
     * @return 根据四边，计算中心点
     */
    public static Point getCenterPoint(int left, int top, int right, int bottom) {
        int x = left + (right - left) / 2;
        int y = top + (bottom - top) / 2;
        return new Point(x, y);
    }


    /**
     * @param price       数字
     * @param numList     数字对应Map的Key
     * @param numPointMap 通过key活的point
     * @return 指定数字对应point组合
     */
    @NotNull
    public static Point[] numKeyBroadPoint(String price, @NotNull String[] numList, @NotNull Map<String, Point> numPointMap) {
        Point[] pricePoints = new Point[price.length()];
        int length = price.length();
        for (int i = 0; i < length; i++) {
            int c = price.charAt(i);
            if (c > 47 && c < 58) {
                // 数字
                pricePoints[i] = numPointMap.get(numList[c - 48]);
            } else {
                pricePoints[i] = numPointMap.get(numList[numList.length - 1]);
            }
        }
        return pricePoints;
    }


}
