package com.prezydium;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvProcessor {


    public List<String[]> readAll() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource("inputpoints.csv").toURI()));
        return readAll(reader);
    }

    public void csvWriterAll(List<String[]> stringArray, Path path) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
        writer.writeAll(stringArray);
        writer.close();
    }

    private List<String[]> readAll(Reader reader) throws Exception {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = csvReader.readAll();
        list.remove(0);
        reader.close();
        csvReader.close();
        return list;
    }
}
