package senla.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {super(String.format("Course %s not found", id));}
}
