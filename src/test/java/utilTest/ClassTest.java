package utilTest;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.smart.framework.annocation.Inject;
import org.smart.framework.helper.BeanHelper;
import org.smart.framework.util.CollectionUtil;
import org.smart.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public class ClassTest {



    @Test
    public void testGetClass(){

        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        System.out.println(beanMap.size());
        if(CollectionUtil.isNotEmpty(beanMap)){

            for(Map.Entry<Class<?>,Object>entry: beanMap.entrySet()){
                Class<?> t = entry.getKey();
                Object value = entry.getValue();

                Field[] fields = t.getFields();
                System.out.println(t.getName());
                if(ArrayUtils.isNotEmpty(fields)){
                    for(Field f:fields){
                        if(f.isAnnotationPresent(Inject.class)){
                            Class<?> beanType = f.getType();
                            Object instance = beanMap.get(beanType);
                            if(instance != null){
                                ReflectionUtil.setFiled(value,f,instance);
                            }
                        }
                    }
                }
            }
        }

    }
}
