/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class EnergySavingsVerificationServiceTest {
    private final EnergySavingsVerificationService service = new EnergySavingsVerificationService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void certifiesAuditableEnergySavings() {
        var result = service.assess(new EnergySavingsVerificationService.Request(
            "ES-001", 100, 99, true, 90, true, true, true));
        assertThat(result.decision()).isEqualTo(EnergySavingsVerificationService.Decision.CERTIFY);
        assertThat(result.certified()).isTrue();
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksWeakMeasurementBaseline() {
        var result = service.assess(new EnergySavingsVerificationService.Request(
            "ES-002", 80, 70, false, 10, false, true, true));
        assertThat(result.decision()).isEqualTo(EnergySavingsVerificationService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(5);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void reviewsExternalFactorsAndApproval() {
        var result = service.assess(new EnergySavingsVerificationService.Request(
            "ES-003", 99, 99, true, 60, true, false, false));
        assertThat(result.decision()).isEqualTo(EnergySavingsVerificationService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(2);
    }
}
