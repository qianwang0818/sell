package com.imooc.repository;

import com.imooc.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /**根据用户openid,分页查询*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid , Pageable pageable);

}
