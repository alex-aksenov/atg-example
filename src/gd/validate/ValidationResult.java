package gd.validate;

import gd.utils.CollectionUtils;
import gd.utils.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public class ValidationResult {

    private List<ValidationProblem> problems;

    public ValidationResult(List<ValidationProblem> problems) {
        this.problems = problems;
    }

    public List<ValidationProblem> getProblems() {
        return problems;
    }

    public boolean hasProblems() {
        return CollectionUtils.isEmpty(problems);
    }

    public boolean hasErrors() {
        return hasSpecified(ValidationProblem.Level.ERROR);
    }

    public boolean hasWarnings() {
        return hasSpecified(ValidationProblem.Level.WARNING);
    }

    private boolean hasSpecified(final ValidationProblem.Level level) {
        if (!hasProblems()) {
            return false;
        } else {
            List<ValidationProblem> errors = new ArrayList<ValidationProblem>(problems);
            CollectionUtils.filter(errors, new Predicate<ValidationProblem>() {
                @Override
                public boolean evaluate(ValidationProblem next) {
                    return next.getLevel() == level;
                }
            });

            return !errors.isEmpty();
        }
    }

}
