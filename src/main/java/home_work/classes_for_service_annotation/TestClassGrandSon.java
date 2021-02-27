package home_work.classes_for_service_annotation;

import home_work.Value;

public class TestClassGrandSon extends TestClassSecondSon {
    private String name = "grand son";
    @Value(value = "3")
    private String id;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", id='" + id + '\'';
    }
}

