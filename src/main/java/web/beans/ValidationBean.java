package web.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import web.tables.Result;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

@Named("validationBean")
@ApplicationScoped
public class ValidationBean {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("locales/messages");

    private static final Set<Double> VALID_R_VALUES = Set.of(1.0, 1.5, 2.0, 2.5, 3.0);
    private static final Set<Double> VALID_X_VALUES = Set.of(
            -4.0, -3.5, -3.0, -2.5, -2.0, -1.5, -1.0, -0.5, 0.0,
            0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0);

    public void validateInput(Result result) {

        StringBuilder errors = new StringBuilder();

        String validXValues = VALID_X_VALUES.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        String validRValues = VALID_R_VALUES.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        if (!VALID_X_VALUES.contains(result.getX())) {
            errors.append(MessageFormat.format(
                    BUNDLE.getString("error.invalid_x"),
                    validXValues
            )).append("<br>");
        }

        if (!VALID_R_VALUES.contains(result.getR())) {
            errors.append(MessageFormat.format(
                    BUNDLE.getString("error.invalid_r"),
                    validRValues
            )).append("<br>");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}