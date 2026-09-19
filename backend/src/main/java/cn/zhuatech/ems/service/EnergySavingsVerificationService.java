/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class EnergySavingsVerificationService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (request.meteringCoveragePercent() < 95) blockers.add("计量覆盖率低于 95%");
        if (request.dataCompletenessPercent() < 95) blockers.add("核算数据完整率低于 95%");
        if (!request.metersCalibrated()) blockers.add("关键计量表计未完成校准");
        if (request.baselineDays() < 30) blockers.add("节能基线周期不足 30 天");
        if (!request.productionNormalized()) blockers.add("节能量未按产量或业务量归一化");
        if (!blockers.isEmpty()) {
            actions.add("暂停节能量确认，补齐计量、数据和基线控制");
            return new Assessment(Decision.BLOCKED, false, blockers, actions);
        }
        if (!request.weatherAdjusted() || !request.independentReviewerApproved()) {
            if (!request.weatherAdjusted()) actions.add("评估并记录天气等外部变量影响");
            if (!request.independentReviewerApproved()) actions.add("由独立复核人批准测量与验证结果");
            return new Assessment(Decision.REVIEW, false, blockers, actions);
        }
        actions.add("认证节能量并冻结基线、计算参数和证据快照");
        return new Assessment(Decision.CERTIFY, true, blockers, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String projectCode, @Min(0) @Max(100) int meteringCoveragePercent,
                          @Min(0) @Max(100) int dataCompletenessPercent,
                          boolean metersCalibrated, @Min(1) int baselineDays,
                          boolean productionNormalized, boolean weatherAdjusted,
                          boolean independentReviewerApproved) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(Decision decision, boolean certified, List<String> blockers,
                             List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { CERTIFY, REVIEW, BLOCKED }
}
