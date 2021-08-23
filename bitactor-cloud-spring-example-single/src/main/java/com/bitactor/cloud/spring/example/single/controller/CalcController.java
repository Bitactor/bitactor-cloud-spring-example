package com.bitactor.cloud.spring.example.single.controller;

import com.bitactor.cloud.spring.example.proto.CalcAddReq;
import com.bitactor.cloud.spring.example.proto.CalcAddResp;
import com.bitactor.cloud.spring.example.proto.CalcSubReq;
import com.bitactor.cloud.spring.example.proto.CalcSubResp;
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
@BitactorController(connector = "single")
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

    /**
     * 减法计算
     *
     * @param subReq
     * @param session
     * @return
     */
    @BitactorRequestMapping(protocol = ProtocolType.JSON)
    public CalcSubResp sub(@ProtocolBody CalcSubReq subReq, ClientNetSession session) {
        int result = subReq.getA() - subReq.getB();
        log.info("Do sub result: {}", result);
        return new CalcSubResp(result);
    }
}
