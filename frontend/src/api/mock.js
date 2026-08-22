/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
export const assets=[
 {code:'MTR-EL-018',name:'一号车间总进线电表',category:'电力计量',location:'一号工厂 · 高压配电室',custodian:'许亦',health:'在线',status:'采集中',utilization:87,value:'18,642 kWh'},
 {code:'MTR-AC-006',name:'中央空调冷量计',category:'暖通计量',location:'一号工厂 · 屋顶机房',custodian:'陈序',health:'需关注',status:'采集中',utilization:93,value:'3,860 kWh'},
 {code:'MTR-AIR-012',name:'空压站流量计',category:'压缩空气',location:'公用工程站',custodian:'梁川',health:'在线',status:'采集中',utilization:78,value:'12,480 Nm³'},
 {code:'MTR-WAT-027',name:'生产用水总表',category:'水计量',location:'一号工厂 · 动力入口',custodian:'周岚',health:'需关注',status:'数据中断',utilization:64,value:'286 m³'}]
export const workOrders=[
 {no:'ET-20260731-014',asset:'MTR-WAT-027',title:'生产用水夜间基线持续偏高',type:'异常分析',priority:'特急',assignee:'宋启',due:'今天 14:30',status:'处理中'},
 {no:'ET-20260731-009',asset:'MTR-AC-006',title:'空调系统单位冷量电耗超基准 12%',type:'节能诊断',priority:'紧急',assignee:'陈序',due:'今天 18:00',status:'待接单'},
 {no:'ET-20260730-026',asset:'MTR-EL-018',title:'总装线峰谷用电策略优化复核',type:'策略优化',priority:'一般',assignee:'许亦',due:'08-02 10:00',status:'待验收'},
 {no:'ET-20260729-018',asset:'MTR-AIR-012',title:'空压机群控压力下调试运行',type:'节能措施',priority:'一般',assignee:'梁川',due:'昨天 16:20',status:'已完成'}]
export const plans=[
 {no:'EP-EL-018-M',asset:'MTR-EL-018',name:'车间月度用电基线复核',cycle:'每月',next:'08-02',team:'能源管理组',progress:72,status:'待执行'},
 {no:'EP-AC-006-Q',asset:'MTR-AC-006',name:'空调系统季度能效诊断',cycle:'每季度',next:'08-05',team:'公用工程组',progress:36,status:'待准备'},
 {no:'EP-AIR-012-M',asset:'MTR-AIR-012',name:'空压站泄漏专项排查',cycle:'每 45 天',next:'08-09',team:'动力能源组',progress:18,status:'计划中'}]
export const inspections=[
 {no:'ER-20260731-031',asset:'MTR-WAT-027',inspector:'周岚',time:'09:18',result:'异常',finding:'00:00—05:00 基线用水高于阈值 26%'},
 {no:'ER-20260731-025',asset:'MTR-EL-018',inspector:'许亦',time:'07:42',result:'正常',finding:'三相电流、电压与功率因数正常'},
 {no:'ER-20260730-086',asset:'MTR-AC-006',inspector:'陈序',time:'昨天 16:05',result:'异常',finding:'冷冻水供回温差仅 3.2℃'},
 {no:'ER-20260730-071',asset:'MTR-AIR-012',inspector:'梁川',time:'昨天 14:36',result:'正常',finding:'流量与管网压力稳定'}]
export const spareParts=[
 {code:'MED-ELECTRIC',name:'市电',spec:'10kV / 分时计价',stock:18642,safety:16000,unit:'kWh',warehouse:'一号工厂'},
 {code:'MED-WATER',name:'生产用水',spec:'市政供水',stock:286,safety:240,unit:'m³',warehouse:'一号工厂'},
 {code:'MED-AIR',name:'压缩空气',spec:'0.65MPa',stock:12480,safety:10000,unit:'Nm³',warehouse:'公用工程站'},
 {code:'MED-GAS',name:'天然气',spec:'工业天然气',stock:860,safety:920,unit:'Nm³',warehouse:'热处理区'}]
