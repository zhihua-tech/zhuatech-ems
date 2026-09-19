/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.controller;

import cn.zhuatech.ems.common.ApiResponse;
import cn.zhuatech.ems.service.EnergyAnomalyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/ems")
public class EnergyAnomalyController {
    private final EnergyAnomalyService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public EnergyAnomalyController(EnergyAnomalyService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/energy-anomaly")
    public ApiResponse<EnergyAnomalyService.Result> analyze(@Valid @RequestBody EnergyAnomalyService.Request request) {
        return ApiResponse.ok(service.analyze(request));
    }
}
