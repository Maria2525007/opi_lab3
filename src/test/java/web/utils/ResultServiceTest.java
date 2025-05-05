package web.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import web.tables.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {

    @Mock
    private EntityManager manager;

    @Mock
    private TypedQuery<Result> query;

    @InjectMocks
    private ResultService service;

    @Test
    public void testResultSaving() {
        Result result = new Result(2.0, 3.0, 3.0, true);

        when(manager.createQuery(anyString(), eq(Result.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(result));

        service.save(result);
        List<Result> results = service.findAll();

        assertEquals(1, results.size());
        assertEquals(result, results.get(0));
        verify(manager).persist(result);
        verify(query).getResultList();
    }
}