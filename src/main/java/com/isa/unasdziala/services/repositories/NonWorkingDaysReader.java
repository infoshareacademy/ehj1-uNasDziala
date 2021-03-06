package com.isa.unasdziala.services.repositories;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.unasdziala.domain.Day;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Getter
public class NonWorkingDaysReader {
    public static final String HOLIDAYS_FILE_NAME = "non_working_days.json";

    private final static Logger log = LoggerFactory.getLogger(NonWorkingDaysReader.class);
    private List<Day> nonWorkingDays = new ArrayList<>();
    ClassLoader classLoader = getClass().getClassLoader();
    private final String NON_WORKING_DAYS_FILE_NAME = "non_working_days.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NonWorkingDaysReader() {
        setNonWorkingDays();
    }

    private void setNonWorkingDays() {
        try {
            log.info("Start read file: {}", NON_WORKING_DAYS_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(classLoader.getResourceAsStream(NON_WORKING_DAYS_FILE_NAME));
            log.info("Start load non working days from file");
            List<Day> result = objectMapper
                    .readerFor(new TypeReference<List<Day>>() {})
                    .readValue(inputStreamReader);
            nonWorkingDays.addAll(result);
            log.info("Have been loaded: {} day/s", result.size());

        } catch (StreamReadException e) {
            log.error("Error while reading non working days file, " + e.getMessage());
        } catch (DatabindException e) {
            log.error("Error while mapping non working days, " + e.getMessage());
        } catch (IOException e) {
            log.error("File read error, " + e.getMessage());
        }
    }
}
