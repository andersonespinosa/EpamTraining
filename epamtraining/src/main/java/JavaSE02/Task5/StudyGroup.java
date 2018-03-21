package JavaSE02.Task5;

import java.util.ArrayList;
import java.util.Optional;

public class StudyGroup<T extends Number> {
    private Subjects subject;

    private static final ArrayList<Student> students = new ArrayList<>();

    public StudyGroup(Subjects subject) {
        this.subject = subject;
    }

    public void addStudentToGroup(Student student) {
        students.add(student);
    }

    public void removeStudentFromGroup(Student student) {
        students.remove(student);
    }

    public Optional<Student> getStudentById(int id){
        for(Student x:students){
            if(id == x.getId()){
                return Optional.ofNullable(x);
            }
        }
        return Optional.empty();
    }
}
