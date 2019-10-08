package com.yuyue.boss.api.service;

import com.yuyue.boss.api.domain.YuYueSite;


import java.util.List;


public interface YuYueSiteService {




    List<YuYueSite> getYuYueSiteInfo(String id,String status,int begin,int limit);



    List<YuYueSite> searchYuYueSiteInfo(String siteAddr, String status,String jPushStatus,
                                        String mainPerson,String startTime,String endTime,int begin,int limit);


    void insertQRCodePath(String id ,String qrCodePath);


    void insertYuYueSite(YuYueSite yuYueSite);

    void updateYuYueSite(YuYueSite yuYueSite);

    void deleteYuYueSiteById(String id);

}