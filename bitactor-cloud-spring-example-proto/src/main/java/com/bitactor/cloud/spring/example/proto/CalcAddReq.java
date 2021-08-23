package com.bitactor.cloud.spring.example.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WXH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalcAddReq {
    private int a;
    private int b;
}
