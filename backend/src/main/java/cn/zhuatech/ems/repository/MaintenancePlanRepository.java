/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.ems.repository;
import cn.zhuatech.ems.model.MaintenancePlan; import org.springframework.data.jpa.repository.JpaRepository; import java.time.LocalDate; import java.util.List;
public interface MaintenancePlanRepository extends JpaRepository<MaintenancePlan,Long>{List<MaintenancePlan> findAllByOrderByNextExecutionDateAsc(); long countByNextExecutionDateLessThanEqual(LocalDate date);}
