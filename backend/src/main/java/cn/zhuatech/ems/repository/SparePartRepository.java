/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.ems.repository;
import cn.zhuatech.ems.model.SparePart; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface SparePartRepository extends JpaRepository<SparePart,Long>{List<SparePart> findAllByOrderByPartCodeAsc();}
