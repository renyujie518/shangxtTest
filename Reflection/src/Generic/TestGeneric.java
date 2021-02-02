package Generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestGeneric.java
 * @Description 反射与泛型
 * @createTime 2021年01月29日 14:35:00
 */
public class TestGeneric {
    public void  method1(Map<Integer,Student> map, List<Student> list,String str){}
    public Map<Integer,Student> method2(){
        return  null;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取当前类的类对象
        Class<TestGeneric> clazz = TestGeneric.class;

        //获取输入参数的泛型
        Method m1 = clazz.getMethod("method1", Map.class, List.class, String.class);
        Class<?>[] parameterTypes = m1.getParameterTypes();
        System.out.println(parameterTypes.length);
        for (int i = 0; i < parameterTypes.length; i++) {
            System.out.println(parameterTypes[i]);  //不带泛型
        }

        System.out.println("===============");

        //带泛型的
        Type[] genericParameterTypes = m1.getGenericParameterTypes();
        for (Type param : genericParameterTypes) {
            System.out.println(param);
            //再想进一步获取泛型
            if (param instanceof ParameterizedType) {//参数化类型
                Type[] actualTypeArguments = ((ParameterizedType) param).getActualTypeArguments();
                for (Type ata : actualTypeArguments) {
                    System.out.println("      " + ata);
                }
            }
        }

        //获取输入参数的泛型
        System.out.println("=============");
        Method m2 = clazz.getMethod("method2");
        Class<?> returnType = m2.getReturnType();
        System.out.println(returnType);//只得到Map,没有泛型

        Type genericReturnType = m2.getGenericReturnType();
        System.out.println(genericReturnType);//得到带泛型的Map

        Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();//强转后得到类型变量
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println("\t" + actualTypeArgument);
        }

        System.out.println("+++++++++++++++++");
        //反射可以突破泛型的限制，比如整形的list不能添加布尔和String
        ArrayList<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add(321);
        System.out.println(list);

        Class<? extends ArrayList> clazz2 = list.getClass();
        Method addmethod = clazz2.getMethod("add", Object.class);//public boolean add(E e),不加泛型当object处理
        addmethod.invoke(list, true);
        addmethod.invoke(list, 3.12);
        addmethod.invoke(list, "wqwqwq");
        System.out.println(list);

        int[] arr = new int[10];
        //Student[] arr = new Student[10];
        Class<? extends int[]> clazz3 =arr.getClass();
        Class<?> componentType = clazz3.getComponentType();
        System.out.println(componentType);


    }
}
