package web.beans;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import web.tables.Result;

import java.util.Locale;

public class ValidationBeanTest {

    private ValidationBean validator;

    @Before
    public void setUp() {
        validator = new ValidationBean();
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidX() {
        Result result = new Result();
        result.setR(1.0);
        result.setX(7.0);
        validator.validateInput(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidR() {
        Result result = new Result();
        result.setX(2.0);
        result.setR(5.0);
        validator.validateInput(result);
    }
}