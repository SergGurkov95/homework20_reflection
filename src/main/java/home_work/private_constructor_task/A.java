package home_work.private_constructor_task;

import home_work.Service;

@Service
public class A {

    private String id = "123";
    private String name = "A";

    private A() {
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", name='" + name + '\'';
    }
}
