package com.isa.unasdziala.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @CsvBindByPosition(position = 2)
    String phoneNo;
    @CsvBindByPosition(position = 3)
    String emailAddress;

    @Override
    public String toString() {
        return "PhoneNo: " + phoneNo  + "\t mail: " + emailAddress;
    }
}
