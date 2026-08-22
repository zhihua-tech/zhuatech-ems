<!-- Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ -->
<script setup>
const groups=[
 {label:'能源运营',items:[['/admin/dashboard','能源驾驶舱','⌂'],['/admin/assets','计量点台账','▱'],['/admin/analytics','能耗分析','⌁']]},
 {label:'维护执行',items:[['/admin/work-orders','节能任务','▤'],['/admin/maintenance','能效计划','◫'],['/admin/inspections','巡检记录','✓'],['/admin/spares','能源介质','⌘']]},
 {label:'系统治理',items:[['/admin/settings','基础配置','⚙'],['/technician/workbench','能源巡检工作台','♙']]}
]
</script>
<template><div class="shell">
 <aside class="side"><div class="brand"><span>ZH</span><div><b>ZhuaTech EMS</b><small>ENERGY & CARBON</small></div></div><button class="workspace-pick"><i></i><span><b>知华智造一号工厂</b><small>园区能源运营中心</small></span><em>⌄</em></button>
 <section v-for="group in groups" :key="group.label"><p>{{group.label}}</p><nav><RouterLink v-for="i in group.items" :key="i[1]" :to="i[0]"><i>{{i[2]}}</i><span>{{i[1]}}</span><b v-if="i[1]==='节能任务'">3</b></RouterLink></nav></section>
 <div class="side-foot"><span>许</span><div><b>许亦</b><small>能源平台主管</small></div><button>•••</button></div>
 </aside>
 <main class="main"><header class="top"><div><small>能源与碳管理中心</small><b><slot name="title"/></b></div><label class="global-search">⌕ <input placeholder="搜索计量点、区域、任务或指标"/><kbd>⌘ K</kbd></label><div class="top-actions"><button>?</button><button>♢<i></i></button><span>2026 / 07 / 31</span></div></header><div class="content"><slot/></div><footer>© 2026 上海如静知华信息科技有限公司 · <a href="https://www.zhuatech.cn/">知华科技</a></footer></main>
 </div></template>
