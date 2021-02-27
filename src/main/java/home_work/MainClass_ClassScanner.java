package home_work;

import home_work.private_constructor_task.A;

import java.io.File;
import java.lang.reflect.*;
import java.util.*;


public class MainClass_ClassScanner {

    private static final File projectFolderFile = new File(System.getProperty("user.dir"));
    private static List<File> list = Arrays.asList(projectFolderFile.listFiles());
    private static Map<String, Object> map = new TreeMap<>();
    private static Method methodWithInitAnnotation;
    private static Object objectWithMethod;

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        annotationCheck(classSearcher(list));

        setValues();

        printMap();

    }


    private static void setValues() throws InvocationTargetException, IllegalAccessException {
        List<String> keyList = new ArrayList<>(map.keySet());
        for (String key : keyList) {
            map.put(key, methodWithInitAnnotation.invoke(objectWithMethod, map.get(key)));
        }
    }


    private static void printMap() {
        if (!map.isEmpty()) {
            System.out.println("\nОбъекты с аннотацией @SEVICE:");
            List<String> keyList = new ArrayList<>(map.keySet());
            for (String key : keyList) {
                System.out.print(key + "\t\t");
                System.out.println(map.get(key));
            }
            System.out.println("\nЗначения приватных полей помеченных аннотацией @VALUE были выставленны при помощи метода с аннотацией @INIT.");

            System.out.println("Класс с приватным конструктором также помечен аннотацией @SEVICE, и его поля были установлены отдельно.");
        } else {
            System.out.println("Такие объекты не найдены");
        }
    }


    private static void annotationCheck(List<File> listOfPaths) {
        for (File pathToClass : listOfPaths) {

            String nameAndPackage = getPackageAndName(pathToClass);
            Class createdClass = null;
            try {
                createdClass = Class.forName(nameAndPackage);
            } catch (ClassNotFoundException exc) {
                exc.printStackTrace();
            }

            if (createdClass.isAnnotationPresent(Service.class)) {
                map.put(nameAndPackage + ".java", createObj(createdClass));
            }


            Method[] methodsInClass = createdClass.getDeclaredMethods();
            for (Method method : methodsInClass) {
                if (method.isAnnotationPresent(Init.class)) {
                    try {
                        objectWithMethod = createdClass.newInstance();
                        methodWithInitAnnotation = createdClass.getMethod(method.getName(), method.getParameterTypes());
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }


    private static Object createObj(Class createdClass) {

        Object obj = null;

        try {
            Constructor constructor = (Constructor) createdClass.getDeclaredConstructor();
            if (Modifier.isPrivate(constructor.getModifiers())) {
                constructor.setAccessible(true);
                obj = constructor.newInstance();
                constructor.setAccessible(false);

                Field fieldName = createdClass.getDeclaredField("name");
                Field fieldId = createdClass.getDeclaredField("id");

                fieldName.setAccessible(true);
                fieldName.set(obj, "changed name");
                fieldName.setAccessible(false);

                fieldId.setAccessible(true);
                fieldId.set(obj, "321");
                fieldId.setAccessible(false);
            } else {
                obj = createdClass.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException exc) {
            exc.printStackTrace();

        }

        return obj;
    }


    private static List<File> classSearcher(List<File> list) {
        List<File> searchResult = new ArrayList<>();

        for (File file : list) {
            if (file.isDirectory()) {
                List<File> nestFiles = Arrays.asList(file.listFiles());
                searchResult.addAll((classSearcher(nestFiles)));
            } else if (file.getName().contains(".java")) {
                searchResult.add(file);
            }
        }

        return searchResult;
    }


    private static String getPackageAndName(File pathToClass) {

        String wrongFormatOfName = pathToClass.getPath().replace(projectFolderFile.getPath(), "").replace("\\src\\main\\java\\", "");
        String rightFormatOfName = wrongFormatOfName.replace(".java", "").replace("\\", ".");

        return rightFormatOfName;
    }
}
