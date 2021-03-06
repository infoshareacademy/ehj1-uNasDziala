package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Address;
import com.isa.unasdziala.domain.Contact;
import com.isa.unasdziala.domain.Department;
import com.isa.unasdziala.domain.EmployeeCSV;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

class EmployeesRepositoryTest {

    private EmployeesRepository sut = new EmployeesRepository();

//    @Test
//    void shouldContainsOnlyTestEmployeesFromFile() {
//        // given
//        List<EmployeeCSV> referenceEmployees = getReferenceEmployees();
//        // when
//        List<EmployeeCSV> result = sut.importEmployees();
//        // then
//        assertThat(referenceEmployees).containsExactlyElementsOf(result);
//    }


    private List<EmployeeCSV> getReferenceEmployees() {

        Address address1 = new Address("Bylowo-Leśnictwo", "80-002", "Kartuzy", "", "5", "");
        Address address2 = new Address("Gdynia", "80-001", "Gdynia", "Starowiejska", "5", "33");
        Address address3 = new Address("Gdańsk", "80-003", "Gdańsk", "Długa", "44/45", "3");
        Address address4 = new Address("Kościerzyna", "80-004", "Kościerzyna", "Nowa", "5A", "");
        List<EmployeeCSV> testEmployees = new ArrayList<>();

        testEmployees.add(new EmployeeCSV("Adam", "Superclass", new Contact("111222333", "adam@company.com"), address1, Department.DEPARTAMENT_FINANSOW, 22.5f));
        testEmployees.add(new EmployeeCSV("Jan", "Maven", new Contact("222333444", "jan@company.com"), address2, Department.DEPARTAMENT_AKTUARIALNY, 44f));
        testEmployees.add(new EmployeeCSV("Monika", "Git", new Contact("444555666", "monika@company.com"), address3, Department.DEPARTAMENT_SPRZEDAZY, 0f));
        testEmployees.add(new EmployeeCSV("Karolina", "Pullrequest", new Contact("999888777", "karolina@company.com"), address4, Department.DEPARTAMENT_AKTUARIALNY, 0.5f));

        return testEmployees;
    }

}