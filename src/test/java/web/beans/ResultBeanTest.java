package web.beans;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import web.tables.Result;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResultBeanTest {

    @Mock
    private ResultListBean resultListBean;

    @InjectMocks
    private ResultBean resultBean;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        resultBean.setResult(new Result());
    }

    @Test
    public void testHitCheckInFirstQuadrant() {
        // Arrange
        Result result = new Result();
        result.setX(1.0);
        result.setY(1.0);
        result.setR(3.0);
        resultBean.setResult(result);

        resultBean.checkHit();

        assertTrue(result.isHit());
        verify(resultListBean, times(1)).addResult(result);
    }

    @Test
    public void testHitCheckInSecondQuadrant() {
        Result result = new Result();
        result.setX(-1.0);
        result.setY(-1.0);
        result.setR(2.0);
        resultBean.setResult(result);

        resultBean.checkHit();

        assertTrue(result.isHit());
        verify(resultListBean, times(1)).addResult(result);
    }

    @Test
    public void testHitCheckInThirdQuadrant() {
        Result result = new Result();
        result.setX(2.0);
        result.setY(-1.0);
        result.setR(4.0);
        resultBean.setResult(result);

        resultBean.checkHit();

        assertTrue(result.isHit());
        verify(resultListBean, times(1)).addResult(result);
    }

    @Test
    public void testMissCheck() {
        Result result = new Result();
        result.setX(4.0);
        result.setY(4.0);
        result.setR(2.0);
        resultBean.setResult(result);

        resultBean.checkHit();

        assertFalse(result.isHit());
        verify(resultListBean, times(1)).addResult(result);
    }
}