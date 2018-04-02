package javase02.Task5;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Student<T extends Number> {
    private String name;
    private int id;
    private EnumMap<Subjects, List<T>> subjectsToMarks = new EnumMap<>(Subjects.class);

    public List<T> getMarksBySubject(Subjects subject) {
        return subjectsToMarks.get(subject);
    }

    public void setMarkBySubject(Subjects subject, T mark) {
        if(!subjectsToMarks.containsKey(subject)){
            subjectsToMarks.put(subject, new ArrayList<>());
        }
        subjectsToMarks.get(subject).add(mark);
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
