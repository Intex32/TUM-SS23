package de.tum.in.ase.eist;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class OnlineCourseTest {

    // TODO 3: Test setOnlineCourseUrl()
    @Test
    public void testSetOnlineCourseUrlWithValidUrl() {
        var course = new OnlineCourse("");
        var url = "https://tum.de";
        assertDoesNotThrow(() -> course.setUrl(url));
        assertEquals(url, course.getUrl().toString());
    }

    @Test
    public void testSetOnlineCourseUrlWithInvalidUrl() {
        var course = new OnlineCourse("");
        var url = "mesomerism";
        assertThrows(MalformedURLException.class, () -> course.setUrl(url));
    }

}
