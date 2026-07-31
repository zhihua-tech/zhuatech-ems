/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.ems.repository;
import cn.zhuatech.ems.model.InspectionRecord; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface InspectionRecordRepository extends JpaRepository<InspectionRecord,Long>{List<InspectionRecord> findAllByOrderByInspectedAtDesc(); long countByResult(String result);}
