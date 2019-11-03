package com.prezydium;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CsvProcessorTest {

    @Test
    public void shouldLoadData() throws Exception {
        CsvProcessor csvProcessor = new CsvProcessor();
        List<String[]> s = csvProcessor.readAll();
        System.out.println(s);
    }

}