package com.isa.unasdziala.controller;


import com.isa.unasdziala.request.DeleteHolidaysRequest;
import com.isa.unasdziala.service.HolidayService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HolidayControllerTest {

    public static final String HOLIDAY_URL = "/api/employee/{employeeId}/holiday";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HolidayService holidayService;

    @Test
    void shouldGetAllUserHolidaysReturnOkStatus() throws Exception {
        // given
        when(holidayService.findAll(1L)).thenReturn(List.of());
        // when
        // then
        mockMvc.perform(get(HOLIDAY_URL, 1L))
                .andExpect(status().isOk());
    }


    @Test
    void shouldPostCanReturnNotFoundWhenUserNotExist() throws Exception {
        // given
        when(holidayService.addHoliday(eq(1L), any(AddHolidaysRequest.class))).thenThrow(new ResourceNotFoundException("Not Found"));
        // when
        String body = """
                {
                    "dates" : []
                }
                """;
        mockMvc.perform(post(HOLIDAY_URL, 1L).contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isNotFound());

        // then

    }

    @Test
    void shouldPostCanReturnBadRequestWhenWrongContent() throws Exception {
        // given
        when(holidayService.addHoliday(eq(1L), any(AddHolidaysRequest.class))).thenThrow(new ResourceNotFoundException("Not Found"));
        // when
        String body = "";
        mockMvc.perform(post(HOLIDAY_URL, 1L).contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest());

        // then

    }

    @Test
    void shouldGetUserHolidaysByIdReturnOkStatus() throws Exception {
        // given
        HolidayDto holidayDto = new HolidayDto();
        holidayDto.setId(1L);
        holidayDto.setDate(LocalDate.of(2022, 05, 20));
        Employee testEmployee = new Employee();
        testEmployee.setId(1L);
        holidayDto.setEmployees(Set.of(testEmployee));
        when(holidayService.findHolidayById(1L, 1L)).thenReturn(holidayDto);
        // when
        // then
        mockMvc.perform(get("/api/employee/{employeeId}/holiday/{holidayId}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.date", equalTo("2022-05-20")))
                .andExpect(jsonPath("$.employees.[0].id", equalTo(1)));
    }

    @Test
    void shouldDeleteReturnNoContent() throws Exception {
        // given
        // when
        String body = """
                {
                    "holidaysId": [1, 2, 3, 4, 5]
                }
                """;
        mockMvc.perform(
                        delete(HOLIDAY_URL, 1L)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}