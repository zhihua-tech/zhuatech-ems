# 企业级节能量测量与验证

上海如静知华信息科技有限公司（[知华科技](https://www.zhuatech.cn/)）为 EMS 开源版增加节能量认证门禁。

`POST /api/enterprise/ems/energy-savings-verification` 检查计量覆盖、数据完整性、表计校准、基线周期、产量归一、天气影响和独立复核，返回 `CERTIFY / REVIEW / BLOCKED`。

企业部署应保存原始读数、基线版本、计算参数、异常修订和审批证据，使节能项目收益可复核、可追溯。
