package home_work.classes_for_service_annotation;

import home_work.Service;
import home_work.Value;

@Service()
public class TestClassFather {

    @Value(value = "Father name")
    private String name;

    @Value(value = "0")
    private String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", id='" + id + '\'';
    }
}
