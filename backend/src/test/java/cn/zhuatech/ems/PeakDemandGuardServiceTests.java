/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.ems;

import cn.zhuatech.ems.service.PeakDemandGuardService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeakDemandGuardServiceTests {
    private final PeakDemandGuardService service = new PeakDemandGuardService();

    @Test
    void quantifiesRequiredPeakLoadShedding() {
        var result = service.evaluate(new PeakDemandGuardService.Request(
            "PLANT-SH-01", new BigDecimal("1000"), new BigDecimal("850"),
            new BigDecimal("300"), new BigDecimal("80"), new BigDecimal("20"),
            new BigDecimal("45")));

        assertEquals(new BigDecimal("1050.00"), result.projectedDemandKw());
        assertEquals(new BigDecimal("50.00"), result.exceedDemandKw());
        assertEquals(new BigDecimal("2250.00"), result.avoidableDemandCharge());
        assertEquals("SHED_LOAD", result.decision());
    }

    @Test
    void keepsNormalSiteWithinDemandContract() {
        var result = service.evaluate(new PeakDemandGuardService.Request(
            "OFFICE-SH-02", new BigDecimal("500"), new BigDecimal("300"),
            new BigDecimal("50"), new BigDecimal("20"), new BigDecimal("0"),
            new BigDecimal("40")));

        assertEquals("NORMAL", result.decision());
    }
}
