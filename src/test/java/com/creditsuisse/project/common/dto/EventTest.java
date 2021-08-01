package com.creditsuisse.project.common.dto;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class EventTest {
    @Test
    public void testEvent() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Event.class);
    }
}
