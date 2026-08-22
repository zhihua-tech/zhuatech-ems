/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnergyAnomalyService {
    public Result analyze(Request request) {
        BigDecimal expected = request.baselineKwh().multiply(BigDecimal.ONE.add(
            BigDecimal.valueOf(request.productionChangePercent()).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)));
        BigDecimal excess = request.currentKwh().subtract(expected).max(BigDecimal.ZERO);
        BigDecimal deviation = expected.signum() == 0 ? BigDecimal.ZERO
            : request.currentKwh().subtract(expected).divide(expected, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        String status = deviation.compareTo(BigDecimal.valueOf(request.alertThresholdPercent() * 2)) >= 0 ? "CRITICAL"
            : deviation.compareTo(BigDecimal.valueOf(request.alertThresholdPercent())) >= 0 ? "ALERT" : "NORMAL";
        List<String> actions = new ArrayList<>();
        if (!"NORMAL".equals(status)) actions.add("按设备和时段下钻定位异常能耗来源");
        if ("CRITICAL".equals(status)) actions.add("通知能源负责人并检查空载运行与泄漏");
        if (actions.isEmpty()) actions.add("保持当前能耗监控频率");
        return new Result(request.meterName(), expected.setScale(2, RoundingMode.HALF_UP), excess.setScale(2, RoundingMode.HALF_UP),
            deviation.setScale(2, RoundingMode.HALF_UP), excess.multiply(request.tariff()).setScale(2, RoundingMode.HALF_UP),
            excess.multiply(request.carbonFactor()).setScale(2, RoundingMode.HALF_UP), status, actions);
    }

    public record Request(@NotBlank String meterName,
                          @DecimalMin("0") BigDecimal currentKwh,
                          @DecimalMin("0") BigDecimal baselineKwh,
                          double productionChangePercent,
                          @DecimalMin("0") BigDecimal tariff,
                          @DecimalMin("0") BigDecimal carbonFactor,
                          @Positive double alertThresholdPercent) {}
    public record Result(String meterName, BigDecimal expectedKwh, BigDecimal excessKwh,
                         BigDecimal deviationPercent, BigDecimal excessCost,
                         BigDecimal excessCarbonKg, String status, List<String> actions) {}
}
