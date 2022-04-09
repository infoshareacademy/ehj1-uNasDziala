package com.isa.unasdziala.services;

import com.isa.unasdziala.domain.entity.Holiday;
import com.isa.unasdziala.repository.HolidayRepository;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class HolidayService {

    private final HolidayRepository holidayRepository;
    Logger log = LoggerFactory.getLogger(NonWorkingDaysReader.class);

    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    public Optional<Holiday> addHoliday(Holiday holiday) {
        return holidayRepository.add(holiday);
    }
}