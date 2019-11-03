package com.prezydium;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

public class CsvProcessorTest {

    @Test
    public void shouldLoadData() throws Exception {
        //given
        String testFilePath = Paths.get(ClassLoader.getSystemResource("inputtestpoints.csv").toURI()).toString();
        //when
        List<String[]> result = CsvProcessor.readAll(testFilePath);
        //then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("UL. TESTOWA", result.get(0)[3]);
        Assert.assertEquals(String.valueOf(88), result.get(1)[4]);
    }

}