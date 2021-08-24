package com.bitactor.cloud.spring.example.cluster.gateway.controller;

import com.bitactor.cloud.spring.example.proto.*;
import com.bitactor.framework.cloud.spring.controller.annotation.BitactorController;
import com.bitactor.framework.cloud.spring.controller.annotation.BitactorRequestMapping;
import com.bitactor.framework.cloud.spring.controller.annotation.ProtocolBody;
import com.bitactor.framework.cloud.spring.controller.session.ClientNetSession;
import com.bitactor.framework.cloud.spring.model.constants.ProtocolType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WXH
 */
@Slf4j
@BitactorController(connector = "gateway")
public class CalcController {
    /**
     * 乘法计算
     *
     * @param mulReq
     * @param session
     * @return
     */
    @BitactorRequestMapping(protocol = ProtocolType.JSON)
    public CalcMulResp mul(@ProtocolBody CalcMulReq mulReq, ClientNetSession session) {
        int result = mulReq.getA() * mulReq.getB();
        log.info("Do mul result: {}", result);
        return new CalcMulResp(result);
    }
}
