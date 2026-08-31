/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.controller;

import cn.zhuatech.ems.common.ApiResponse;
import cn.zhuatech.ems.service.EnergySavingsVerificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/ems")
public class EnergySavingsVerificationController {
    private final EnergySavingsVerificationService service;
    public EnergySavingsVerificationController(EnergySavingsVerificationService service) { this.service = service; }

    @PostMapping("/energy-savings-verification")
    public ApiResponse<EnergySavingsVerificationService.Assessment> assess(
        @Valid @RequestBody EnergySavingsVerificationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
