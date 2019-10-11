package com.yuyue.boss.api.mapper;


import com.yuyue.boss.api.domain.Commodity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommodityMapper extends MyBaseMapper<Commodity> {
    @Transactional
    @Insert("INSERT INTO yuyue_commodity (\n" +
            "\tCOMMODITY_ID,\n" +
            "\tORDER_ID,\n" +
            "\tPRICE_ID," +
            "\tCATEGORY,\n" +
            "\tCOMMODITY_NAME,\n" +
            "\tAD_WORD,\n" +
            "\tAD_IMAGE_URL,\n" +
            "\tCOMMODITY_PRICE,\n" +
            "\tPAY_URL,\n" +
            "\tADDR,\n" +
            "\tSTATUS,\n" +
            "\tSPOKESPERSON_ID,\n" +
            "\tMERCHANT_ID\n" +
            ")  VALUES \n" +
            "(#{commodityId},#{orderId},#{priceId},#{category},#{commodityName},#{adWord},#{adImageUrl},#{commodityPrice}," +
            "#{payUrl},#{addr},#{status},#{spokesPersonId},#{merchantId})")
    void commodityToSpread(Commodity commodity);



    List<Commodity> getCommodityInfo(@Param("commodityId") String commodityId,@Param("commodityName") String commodityName, @Param("category") String category, @Param(value = "status") String status,
                                     @Param("startTime") String startTime,@Param("endTime") String endTime);

    @Transactional
    @Update("UPDATE yuyue_commodity SET `STATUS`= #{status} WHERE COMMODITY_ID = #{commodityId}")
    void updateCommodityStatus(@Param("commodityId") String commodityId, @Param("status") String status);

    @Delete("DELETE FROM yuyue_commodity WHERE COMMODITY_ID =#{commodityId}")
    void deleteCommodityByPrimaryKey(@Param("commodityId") String commodityId);

    @Transactional
    void updateCommodityInfo(Commodity commodity);

}