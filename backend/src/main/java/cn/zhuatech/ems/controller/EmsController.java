/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.controller;
import cn.zhuatech.ems.common.ApiResponse; import cn.zhuatech.ems.dto.EmsDto.*; import cn.zhuatech.ems.service.EmsService; import jakarta.validation.Valid; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/ems") public class EmsController {
    private final EmsService service; public EmsController(EmsService service){this.service=service;}
    @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.dashboard());}
    @GetMapping("/assets") public ApiResponse<List<AssetView>> assets(){return ApiResponse.ok(service.assets());}
    @GetMapping("/spare-parts") public ApiResponse<List<SparePartView>> spareParts(){return ApiResponse.ok(service.spareParts());}
    @GetMapping("/maintenance-plans") public ApiResponse<List<PlanView>> plans(){return ApiResponse.ok(service.plans());}
    @GetMapping("/work-orders") public ApiResponse<List<WorkOrderView>> workOrders(){return ApiResponse.ok(service.workOrders());}
    @GetMapping("/inspections") public ApiResponse<List<InspectionView>> inspections(){return ApiResponse.ok(service.inspections());}
    @PostMapping("/assets") @PreAuthorize("hasAnyRole('ADMIN','ASSET_MANAGER')") public ApiResponse<AssetView> createAsset(@Valid @RequestBody CreateAssetRequest r){return ApiResponse.ok("资产创建成功",service.createAsset(r));}
    @PostMapping("/work-orders") @PreAuthorize("hasAnyRole('ADMIN','ASSET_MANAGER')") public ApiResponse<WorkOrderView> createWorkOrder(@Valid @RequestBody CreateWorkOrderRequest r){return ApiResponse.ok("节能任务已创建",service.createWorkOrder(r));}
    @PatchMapping("/work-orders/{id}/advance") @PreAuthorize("hasAnyRole('ADMIN','ASSET_MANAGER','TECHNICIAN')") public ApiResponse<WorkOrderView> advance(@PathVariable Long id){return ApiResponse.ok("节能任务状态已推进",service.advanceWorkOrder(id));}
}
