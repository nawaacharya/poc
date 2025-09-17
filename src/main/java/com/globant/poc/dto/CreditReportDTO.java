package com.globant.poc.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditReportDTO implements Serializable {
    private String bureu;
    private Integer creditScrore;
    private String reportDate;

}
