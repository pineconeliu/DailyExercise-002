package com.lss;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            // propertyDescriptor 允许添加属性编辑器 -PropertyEditor
            // 配合GUI页面text(String)->PropertyType
            // name->String
            // age->Integer
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String propertyName = propertyDescriptor.getName();
            if("age".equals(propertyName)){ //为"age" 字段/属性添加 PropertyEditor
                //
                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);

            }
        });

    }
    static class StringToIntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer integer = Integer.valueOf(text);
            setValue(text);
        }
    }
}
