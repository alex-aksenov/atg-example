package gd.exception;

import gd.validate.ValidationProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public class ValidationException extends Exception {

    private final List<ValidationProblem> problems;

    public ValidationException(ValidationProblem problem) {
        this.problems = new ArrayList<ValidationProblem>();
        problems.add(problem);
    }

    public ValidationException(List<ValidationProblem> problems) {
        this.problems = problems;
    }

    public List<ValidationProblem> getProblems() {
        return problems;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (ValidationProblem problem : problems) {
            sb.append(sep).append("Problem (level ").append(problem.getLevel())
                    .append("): ").append(problem.getMessage());
            sep = "\n";
        }

        return sb.toString();
    }
}