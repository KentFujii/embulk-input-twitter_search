package org.embulk.input.twitter_search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class TestToDoBusiness {
    @Mock
    private ToDoService doService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<String> combinedlist = Arrays.asList(" Use Core Java ", " Use Spring Core ", " Use w3eHibernate ", " Use Spring MVC ");
        Mockito.when(doService.getTodos("dummy")).thenReturn(combinedlist);
    }

    @Test
    public void TestGetTodosforHibernate() {
        ToDoBusiness business = new ToDoBusiness(doService);
        List<String> alltd = business.getTodosforHibernate("dummy");
        System.out.println(alltd);
        Assert.assertEquals(1, alltd.size());
    }
}
