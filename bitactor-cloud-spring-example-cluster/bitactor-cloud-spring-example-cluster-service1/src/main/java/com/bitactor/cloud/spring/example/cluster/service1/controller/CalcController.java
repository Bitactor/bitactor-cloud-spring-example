package com.bitactor.cloud.spring.example.cluster.service1.controller;

import com.bitactor.cloud.spring.example.proto.CalcAddReq;
import com.bitactor.cloud.spring.example.proto.CalcAddResp;
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
     * 加法计算
     *
     * @param addReq
     * @param session
     * @return
     */
    @BitactorRequestMapping(protocol = ProtocolType.JSON)
    public CalcAddResp add(@ProtocolBody CalcAddReq addReq, ClientNetSession session) {
        int result = addReq.getA() + addReq.getB();
        log.info("Do add result: {}", result);
        return new CalcAddResp(result);
    }

}
