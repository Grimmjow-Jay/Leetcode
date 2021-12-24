package demo;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jay Yang
 * @date 2021/12/24
 */
public class GenericsReflectDemo {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Generics<String> generics = new Generics<>();
        generics.setData("abc");
        System.out.println(generics.getData());

        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("data", generics.getClass());
        Method readMethod = propertyDescriptor.getReadMethod();
        Object invoke = readMethod.invoke(generics);
        System.out.println(invoke);

        Method writeMethod = propertyDescriptor.getWriteMethod();
        writeMethod.invoke(generics, new GenericsReflectDemo());

        Object invoke2 = readMethod.invoke(generics);
        System.out.println(invoke2);

    }

    private static class Generics<T extends CharSequence> {

        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
