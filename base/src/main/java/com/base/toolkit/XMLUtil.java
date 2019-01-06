package com.base.toolkit;

import com.base.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: vincent
 * Date: 2018-12-04 17:21:00
 * Comment:
 */

public class XMLUtil {

    /**
     * 格式化掉微信返回的 XML 文档
     * @param document
     * @param namespace
     * @return
     */
    public static String wechatXMLFormat(String document, String namespace) {
        document = document.replaceAll("<xml>", "<" + namespace + ">").replaceAll("</xml>", "</" + namespace + ">");
        document = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + document;
        return document;
    }

    /**
     * 将 XML 转换为 JavaBean
     * @param <T>
     * @param document
     * @param clazz
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String document, Class<T> clazz) {
        try {
            StringReader reader = new StringReader(document);
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new BaseException("将XML转换为Java对象时出现异常：" + e.getMessage());
        }
    }

    /**
     * 将 xml 文档转换为 Map
     * @param documentString
     * @return
     */
    public static Map<String, Object> toMap(String documentString) {
        Map<String, Object> documentMap = new HashMap<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(documentString.getBytes()));
            document.getDocumentElement().normalize();
            readDocument(document, documentMap);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return documentMap;
    }

    /**
     * 递归读取 xml
     * @param node
     * @param documentMap
     * @return
     */
    private static void readDocument(Node node, Map<String, Object> documentMap) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node currentNode = node.getChildNodes().item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                readDocument(currentNode, documentMap);
            } else if (currentNode.getNodeType() == Node.TEXT_NODE) {
                if (StringUtils.isNotBlank(currentNode.getParentNode().getNodeName()) && StringUtils.isNotBlank(currentNode.getNodeValue()))
                    documentMap.put(currentNode.getParentNode().getNodeName(), currentNode.getNodeValue());
            }
        }
    }

}
