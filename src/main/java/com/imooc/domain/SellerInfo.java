package com.imooc.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "seller_info", schema = "sell", catalog = "")
@Data
@DynamicUpdate
public class SellerInfo {

    @Id
    @Column(name = "seller_id")
    @GeneratedValue(generator = "mytableGenerator")
    @GenericGenerator(name = "mytableGenerator", strategy = "uuid")
    private String sellerId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "openid")
    private String openid;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;


}
