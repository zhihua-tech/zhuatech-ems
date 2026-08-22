/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.controller;

import cn.zhuatech.ems.common.ApiResponse;
import cn.zhuatech.ems.service.EnergyAnomalyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ems")
public class EnergyAnomalyController {
    private final EnergyAnomalyService service;
    public EnergyAnomalyController(EnergyAnomalyService service) { this.service = service; }
    @PostMapping("/energy-anomaly")
    public ApiResponse<EnergyAnomalyService.Result> analyze(@Valid @RequestBody EnergyAnomalyService.Request request) {
        return ApiResponse.ok(service.analyze(request));
    }
}
