package senla.exception;

public class PsychologistNotFoundException extends RuntimeException {
    public PsychologistNotFoundException(Long id) {super(String.format("Psychologist %s", id));}
}
