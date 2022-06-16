package com.lss.factory;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.lss.pojo.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//工厂创建 bean
public class TestSpringFactory {

    //用于存放bean的容器，类似于spring容器
    public   final  Map<String,Object> map = new HashMap<>();
    public void create(){
        try {
            Document document =  XmlUtil.readXML("classpath:TestSpringIOC.xml");
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for(int i=0;i<nodeList.getLength();i++){
                //判断元素
                if(!(nodeList.item(i) instanceof  Element)) continue;
                //判断对象
                if(!"bean".equals(nodeList.item(i).getNodeName())) continue;
                //解析标签
                Element bean = (Element) nodeList.item(i);
                String id = bean.getAttribute("id");
                String name = bean.getAttribute("name");
                String className = bean.getAttribute("class");
                Class<?> aClass = Class.forName(className);
                // 优先级 id > name
                String beanName = StrUtil.isNotEmpty(id) ? id : name;
                if (StrUtil.isEmpty(beanName)) {
                    beanName = StrUtil.lowerFirst(aClass.getSimpleName());
                }

               User user = (User)aClass.newInstance();

                //读取属性并填充
                for(int j=0; j < bean.getChildNodes().getLength(); j++){
                    if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                    if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                    // 解析标签：property
                    Element property = (Element) bean.getChildNodes().item(i);
                    String attrName = property.getAttribute("name");
                    String attrValue = property.getAttribute("value");
                    String attrRef = property.getAttribute("ref");
                    Method[] methods = aClass.getMethods();
                    Arrays.stream(methods).forEach(method -> {
                        if(method.getName().contains("set")){
                            String param = method.getName().substring(3);
                            if(attrName.equalsIgnoreCase(param)){
                                method.setAccessible(true);
                                try {
                                    method.invoke(user,attrValue);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                }
                map.put(beanName,user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
