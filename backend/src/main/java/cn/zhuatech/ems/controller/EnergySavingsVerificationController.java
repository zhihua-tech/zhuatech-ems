/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.controller;

import cn.zhuatech.ems.common.ApiResponse;
import cn.zhuatech.ems.service.EnergySavingsVerificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/ems")
public class EnergySavingsVerificationController {
    private final EnergySavingsVerificationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public EnergySavingsVerificationController(EnergySavingsVerificationService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/energy-savings-verification")
    public ApiResponse<EnergySavingsVerificationService.Assessment> assess(
        @Valid @RequestBody EnergySavingsVerificationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
