import JavaSE02.Task5.Student;
import JavaSE02.Task5.StudyGroup;
import JavaSE02.Task5.Subjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;


class JavaSE02Task5Test {

    @Test
    void getStudentByIdTest(){
        Student Henry = new Student();
        Student Abram = new Student();
        Henry.setId(1);
        Henry.setName("Henry");
        Abram.setId(2);
        Abram.setName("Abram");

        StudyGroup<Double> mathGroup = new StudyGroup<>(Subjects.MATHEMATICS);
        mathGroup.addStudentToGroup(Henry);
        mathGroup.addStudentToGroup(Abram);

        if(!mathGroup.getStudentById(2).isPresent()){
            Assertions.fail("No such student");
        } else {
            Assertions.assertSame(Abram, mathGroup.getStudentById(2).get());
        }
    }

    @Test
    void setAndGetMarksBySubject(){
        Student Henry = new Student();
        Henry.setName("Henry");
        Henry.setId(1);
        StudyGroup<Double> mathGroup = new StudyGroup<>(Subjects.MATHEMATICS);
        mathGroup.addStudentToGroup(Henry);

        Student<Double> student = mathGroup.getStudentById(1).get();

        student.setMarkBySubject(Subjects.MATHEMATICS, 1.5);
        student.setMarkBySubject(Subjects.MATHEMATICS, 2.5);
        List studentMarks = student.getMarksBySubject(Subjects.MATHEMATICS);
        Double mark = (double) studentMarks.get(0);
        Double mark1 = (double) studentMarks.get(1);

        Assertions.assertTrue(1.5 == mark);
        Assertions.assertTrue(2.5 == mark1);
    }
}
