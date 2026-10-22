package workshop;

import java.util.ArrayList;
import java.util.List;
/*Edit*/
/**
 * Stores the grades of a student and calculates the results.
 */
class Student {
    /** Lowest grade allowed. */
    private static final double MIN_GRADE = 0;
    /** Highest grade allowed. */
    private static final double MAX_GRADE = 100;
    /** Minimum average for an A and for the honor roll. */
    private static final double A_GRADE = 90;
    /** Minimum average for a B. */
    private static final double B_GRADE = 80;
    /** Minimum average for a C. */
    private static final double C_GRADE = 70;
    /** Minimum average for a D and to pass. */
    private static final double D_GRADE = 60;

    /** Student ID. */
    private String id;
    /** Student name. */
    private String name;
    /** Grades of the student. */
    private List<Double> grades = new ArrayList<>();
    /** True if the student passed. */
    private boolean passed;
    /** True if the student is on the honor roll. */
    private boolean honor;

    /**
     * Creates a student. The ID and the name can't be empty.
     *
     * @param studentId the student ID
     * @param studentName the student name
     */
    Student(final String studentId, final String studentName) {
        if (studentId == null || studentId.isEmpty()
                || studentName == null || studentName.isEmpty()) {
            throw new IllegalArgumentException(
                    "the student ID and name can't be empty");
        }
        id = studentId;
        name = studentName;
    }

    /**
     * Adds a grade if it is a number between 0 and 100.
     *
     * @param grade the grade to add
     */
    public void addGrade(final Object grade) {
        if (!(grade instanceof Number)) {
            System.out.println("Error: the grade " + grade
                    + " is not a number");
            return;
        }
        double value = ((Number) grade).doubleValue();
        if (value < MIN_GRADE || value > MAX_GRADE) {
            System.out.println("Error: the grade " + value
                    + " must be between 0 and 100");
            return;
        }
        grades.add(value);
    }

    /**
     * Calculates the average of the grades.
     *
     * @return the average, or 0 if there are no grades
     */
    public double average() {
        if (grades.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        return total / grades.size();
    }

    /**
     * Converts the average into a letter grade.
     *
     * @return A, B, C, D or F
     */
    public String letterGrade() {
        double avg = average();
        if (avg >= A_GRADE) {
            return "A";
        } else if (avg >= B_GRADE) {
            return "B";
        } else if (avg >= C_GRADE) {
            return "C";
        } else if (avg >= D_GRADE) {
            return "D";
        }
        return "F";
    }

    /**
     * Marks the student as passed if the average is 60 or more.
     */
    public void checkPassStatus() {
        passed = average() >= D_GRADE;
    }

    /**
     * Marks the student for the honor roll if the average is 90 or more.
     */
    public void checkHonorStatus() {
        honor = average() >= A_GRADE;
    }

    /**
     * Removes the grade at the given index.
     *
     * @param index position of the grade
     */
    public void removeGrade(final int index) {
        if (index < 0 || index >= grades.size()) {
            System.out.println("Error: there is no grade at index " + index);
            return;
        }
        grades.remove(index);
    }

    /**
     * Removes the first grade with the given value.
     *
     * @param value the grade to remove
     */
    public void removeGradeByValue(final double value) {
        if (!grades.remove(Double.valueOf(value))) {
            System.out.println("Error: the grade " + value + " was not found");
        }
    }

    /**
     * Prints the summary report of the student.
     */
    public void reportCard() {
        checkPassStatus();
        checkHonorStatus();
        System.out.println("Student: " + name);
        System.out.println("ID: " + id);
        System.out.println("Grades #: " + grades.size());
        System.out.printf("Average: %.2f%n", average());
        System.out.println("Letter grade: " + letterGrade());
        System.out.println("Status: " + (passed ? "Passed" : "Failed"));
        System.out.println("Honor Roll: " + (honor ? "Yes" : "No"));
    }
}

/**
 * Runs an example of the Student Grade Management System.
 */
public final class Main {
    /** First grade of the example. */
    private static final int GRADE_1 = 100;
    /** Second grade of the example. */
    private static final int GRADE_2 = 85;
    /** Grade out of range. */
    private static final int WRONG_GRADE = 120;
    /** Third grade of the example. */
    private static final double GRADE_3 = 72.5;
    /** Index that does not exist. */
    private static final int WRONG_INDEX = 9;
    /** Grade that the student does not have. */
    private static final double MISSING_GRADE = 50;

    private Main() {
    }

    /**
     * Tests the program with valid and invalid data.
     *
     * @param args not used
     */
    public static void main(final String[] args) {
        try {
            new Student("abc", null);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        Student s = new Student("S001", "Maria Lopez");
        s.addGrade(GRADE_1);
        s.addGrade("Ninety");
        s.addGrade(GRADE_2);
        s.addGrade(WRONG_GRADE);
        s.addGrade(GRADE_3);
        s.removeGrade(WRONG_INDEX);
        s.removeGradeByValue(MISSING_GRADE);
        s.removeGradeByValue(GRADE_3);
        s.reportCard();
    }
}
