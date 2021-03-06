package com.youlaiyouqu.boss.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 广告推广表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement implements Serializable {
    private static final long serialVersionUID = 1L;

    //广告申请Id
    private String id;
    //必填
    //用户id
    private String userId;
    //商家地址
    private String merchantAddr;
    //营业执照
    private String businessLicense;
    //法人身份证正面
    private String idCardZM;
    //法人身份证反面
    private String idCardFM;
    //机构代码
    private String agencyCode;
    //商家名称
    private String merchantName;
    //手机号码
    private String phone;


    //选填
    //产地
    private String produceAddr;
    //固定号码
    private String fixedPhone;
    //邮件
    private String email;
    //微信
    private String wx;
    //QQ
    private String qqNum;
    //商品链接
    private String merchandiseUrl;
    //联系电话
    private String telephone;
    //申请时间
    private String applicationTime;
    //推广时间
    private String spreadTime;
    //广告审核状态
    private String status;
    //商家信息
    private AppUser appUser;
    //买家的店面
    private List<MallShop> mallShops;


}
