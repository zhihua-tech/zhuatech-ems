/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.ems.repository;
import cn.zhuatech.ems.model.WorkOrder; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface WorkOrderRepository extends JpaRepository<WorkOrder,Long>{List<WorkOrder> findAllByOrderByDueAtAsc(); long countByStatusNot(String status); long countByPriorityAndStatusNot(String priority,String status);}
