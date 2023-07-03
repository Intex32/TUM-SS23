package de.tum.in.ase.eist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseTest {

    // TODO 1: Test getCourseTitle()
    @Test
    public void testGetCourseTitle() {
        var title = "meso";
        var course = new Course(title);
        assertEquals(course.getTitle(), title);
    }

    // TODO 2: Test getNumberOfAttendees()
    @Test
    public void testNoAttendees() {
        var course = new Course("");
        assertEquals(0, course.getNumberOfAttendees());
    }

    @Test
    public void testThreeAttendees() {
        var course = new Course("");
        course.addAttendee(new Student("Ana", "A", "", "", ""));
        course.addAttendee(new Student("Bob", "B", "", "", ""));
        course.addAttendee(new Student("Chris", "C", "", "", ""));
        assertEquals(3, course.getNumberOfAttendees());
    }

}
