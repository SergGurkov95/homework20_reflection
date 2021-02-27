package home_work.classes_for_service_annotation;

import home_work.Value;

public class TestClassSecondSon extends TestClassFather {
    @Value(value = "second son")
    private String name;
    @Value(value = "2")
    private String id;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", id='" + id + '\'';
    }
}
