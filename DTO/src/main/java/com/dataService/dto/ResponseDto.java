package com.dataService.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseDto implements Serializable {

    private boolean success;
    private String result;

}
