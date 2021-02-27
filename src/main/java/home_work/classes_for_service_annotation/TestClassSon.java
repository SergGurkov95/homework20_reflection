package home_work.classes_for_service_annotation;

import home_work.Value;

public class TestClassSon extends TestClassFather {

    @Value()
    private String name;
    private String id = "1";

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", id='" + id + '\'';
    }
}
