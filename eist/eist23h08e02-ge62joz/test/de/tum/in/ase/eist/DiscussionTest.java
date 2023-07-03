package de.tum.in.ase.eist;

import org.easymock.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
class DiscussionTest {

    @TestSubject
    private Discussion discussion;
    @Mock
    private Course courseMock;
    @Mock
    private Comment commentMock;

    // TODO implement the tests
    @Test
    public void testComment() {
        var count = discussion.getNumberOfComments();

        expect(commentMock.save()).andReturn(true);
        replay(commentMock);

        discussion.addComment(commentMock);
        assertEquals(count + 1, discussion.getNumberOfComments());

        verify(commentMock);
    }

    @Test
    public void testCommentIfSavingFails() {
        var count = discussion.getNumberOfComments();

        expect(commentMock.save()).andReturn(false);
        replay(commentMock);

        discussion.addComment(commentMock);
        assertEquals(count, discussion.getNumberOfComments());

        verify(commentMock);
    }

    @Test
    public void testStartCourseDiscussion() {
        var topic = "TOPICO";
        var person = new Student("", "", LocalDate.now(), "", "");

        expect(courseMock.isDiscussionAllowed(person)).andReturn(true);
        replay(courseMock);

        var allowed = courseMock.isDiscussionAllowed(person);
        assertTrue(allowed);

        verify(courseMock);

        reset(courseMock);
        expect(courseMock.isDiscussionAllowed(person)).andReturn(true);
        replay(courseMock);

        var result = discussion.startCourseDiscussion(courseMock, person, topic);
        assertTrue(result);
        assertEquals(courseMock, discussion.getCourse());
        assertEquals(topic, discussion.getTopic());

        verify(courseMock);
    }

}
