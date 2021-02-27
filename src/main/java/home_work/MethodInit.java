package home_work;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MethodInit {

    @Init()
    public Object methodInit(Object obj) {

      //  System.out.println(obj.toString());

        try {
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Value.class)) {

                    Value annotation = field.getAnnotation(Value.class);
                    String value = annotation.value();

                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                        field.set(obj, value);
                        field.setAccessible(false);
                    } else {
                        field.set(obj, value);
                    }
                }
            }
        } catch (IllegalAccessException exc) {
            exc.printStackTrace();
        }
        return obj;
    }
}
