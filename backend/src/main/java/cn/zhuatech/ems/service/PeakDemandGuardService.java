/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeakDemandGuardService {
    public Result evaluate(Request request) {
        BigDecimal projectedDemand = request.currentDemandKw().add(request.forecastAdditionalKw())
            .subtract(request.flexibleLoadKw()).subtract(request.batteryDischargeKw())
            .max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        BigDecimal utilization = projectedDemand.divide(request.contractedDemandKw(), 4, RoundingMode.HALF_UP);
        BigDecimal exceedDemand = projectedDemand.subtract(request.contractedDemandKw())
            .max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        BigDecimal avoidableDemandCharge = exceedDemand.multiply(request.peakTariffPerKw())
            .setScale(2, RoundingMode.HALF_UP);
        String decision = exceedDemand.signum() > 0 ? "SHED_LOAD"
            : utilization.compareTo(new BigDecimal("0.90")) >= 0 ? "WATCH" : "NORMAL";

        List<String> actions = new ArrayList<>();
        if (exceedDemand.signum() > 0) actions.add("追加削减至少 " + exceedDemand.toPlainString() + " kW 负荷");
        if (request.flexibleLoadKw().signum() == 0) actions.add("识别可错峰设备并建立自动需量响应策略");
        if (request.batteryDischargeKw().signum() == 0) actions.add("评估储能放电或备用能源调度");
        if ("NORMAL".equals(decision)) actions.add("保持当前运行计划并持续监测 15 分钟需量");
        return new Result(request.siteCode(), projectedDemand, utilization, exceedDemand,
            avoidableDemandCharge, decision, actions);
    }

    public record Request(@NotBlank String siteCode,
                          @DecimalMin("0.01") BigDecimal contractedDemandKw,
                          @DecimalMin("0") BigDecimal currentDemandKw,
                          @DecimalMin("0") BigDecimal forecastAdditionalKw,
                          @DecimalMin("0") BigDecimal flexibleLoadKw,
                          @DecimalMin("0") BigDecimal batteryDischargeKw,
                          @DecimalMin("0") BigDecimal peakTariffPerKw) {}

    public record Result(String siteCode, BigDecimal projectedDemandKw,
                         BigDecimal contractedUtilization, BigDecimal exceedDemandKw,
                         BigDecimal avoidableDemandCharge, String decision,
                         List<String> actions) {}
}
