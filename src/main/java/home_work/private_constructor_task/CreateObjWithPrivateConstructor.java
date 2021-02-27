package home_work.private_constructor_task;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CreateObjWithPrivateConstructor {
    public static void main(String[] args) {
        A objectWithPrivateConstructor = null;

        try {

            Constructor constructor = (Constructor) A.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            objectWithPrivateConstructor = (A) constructor.newInstance();
            constructor.setAccessible(false);

            Field fieldName = objectWithPrivateConstructor.getClass().getDeclaredField("name");
            Field fieldId = objectWithPrivateConstructor.getClass().getDeclaredField("id");

            fieldName.setAccessible(true);
            fieldName.set(objectWithPrivateConstructor, "changed name");
            fieldName.setAccessible(false);

            fieldId.setAccessible(true);
            fieldId.set(objectWithPrivateConstructor, "321");
            fieldId.setAccessible(false);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }


        System.out.println(objectWithPrivateConstructor);

    }
}
