package JavaSE02.Task5;

import java.util.EnumMap;

public class Student<T extends Number> {
    private String name;
    private int id;
    private EnumMap<Subjects, T> subjectsToMarks;

    public void getMarksBySubject(Subjects subject) {
        subjectsToMarks.get(subject);
    }

    public void setMarkBySubject(Subjects subject, T mark) {
        subjectsToMarks.put(subject, mark);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
}
