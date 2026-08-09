/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.ems.controller;

import cn.zhuatech.ems.common.ApiResponse;
import cn.zhuatech.ems.service.PeakDemandGuardService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ems/insights")
public class PeakDemandGuardController {
    private final PeakDemandGuardService service;

    public PeakDemandGuardController(PeakDemandGuardService service) {
        this.service = service;
    }

    @PostMapping("/peak-demand-guard")
    public ApiResponse<PeakDemandGuardService.Result> evaluate(
        @Valid @RequestBody PeakDemandGuardService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
