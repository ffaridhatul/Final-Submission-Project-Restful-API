package com.warungmakansamudra.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchResponse {

    private Integer status;
    private String message;
    private Object data;
}
