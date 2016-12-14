package gd.validate;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public class ValidationProblem {

    private Level level;

    private String message;

    public ValidationProblem(Level level, String message) {
        this.level = (level != null ? level : Level.ERROR);
        this.message = message;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Level {
        ERROR, WARNING
    }
}
