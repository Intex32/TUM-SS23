# Testing

The EIST team wants to implement a system to manage student absences for the practical course. For that we want to write
several Unit Tests.
We want to test functionalities that are independent of other services. Additionally, we also want to make sure to test
if the correct methods of other services are called.
In this exercise both students and instructors are modeled as `Person` entities and depending on if the
flag `is_instructor` is set, the `Person` is either a student or an instructor.

### Part 1: Unit Tests

Your job is to write tests for the `AbsenceService` class in `AbsenceServiceTest`

**You have the following tasks:**

1. **Implement testSaveAbsence():**
   We want to test the `saveAbsence(Absence absence)` method which stores an Absence into the database:
   Add a new test case `testSaveAbsence()` in the `AbsenceServiceTest` class. For that instantiate an `Absence` Object
   and save it. Afterwards, check whether that Object was saved correctly into the database. **Hint:** Utilize
   the `personBuilder` to instantiate and save a `Person` to the database.

2. **Implement testSaveAbsenceOnSameDate():**
   Write a test to make sure that we throw an `InvalidDataException` if we try to save an invalid Absence. That
   exception is triggered by saving a duplicate `Absence` object to the database. An `Absence` Object is a duplicate if
   the `date` of the absence is the same as an existing absence in the database AND it belongs to the same person. Add a
   new test case `testSaveAbsenceOnSameDate()` and check if the exception is thrown correctly.

### Part 2: Mock Pattern

We also want to test if the `MailService` class is correctly utilized to alert the instructor of the course with an
Email when an Absence is submitted. To receive an alert about a submitted absence the instructor needs to have selected
in the Settings that they want to be alerted. We want to test the functionality of correctly triggering the email alert
by mocking the `sendMail(String text)` method. For this we utilize the `EasyMock` framework.

**You have the following tasks:**

1. **Implement testSubmitAbsenceAlertInstructor():**
   Test the `submitAbsence(Absence absence, boolean alertInstructor)` method and mock the `sendMail(…)` method to
   return `true` when called and executed successfully.
   Write a new test case `testSubmitAbsenceAlertInstructor()` and verify that `sendMail(...)` is called when
   invoking `submitAbsence(absence, true)`. For that initialize two `Person` objects which correspond to a student and
   instructor each.

2. **Implement testSubmitAbsenceDoesNotAlertInstructor():**
   We also want to ensure that instructors are not alerted if they muted the alerts. Write a new test
   case `testSubmitAbsenceDoesNotAlertInstructor()` and verify that `sendMail(…)` is not called when
   invoking `submitAbsence(absence, false)`.

### Important

- You can run the tests via gradles `test` task.
- Please name the test methods exactly as specified in the text below! Otherwise, the automated correction of your
  solution will fail and you might not get full points.
- You can find the test cases in the test folder. You only need to add code in the test methods. Do not change code in
  the src folder!
- All the services used in the test are set up correctly. Please DO NOT change them.
