package com.youlaiyouqu.boss.task;

import com.youlaiyouqu.boss.api.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;

@Slf4j
@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private PayService payService;
    @Autowired
    private YuYueSiteService yuYueSiteService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SendService sendService;
    @Autowired
    private MallShopService mallShopService;

    /**
     * 订单支付超时判断,25分钟
     */
//    @Scheduled(cron = "0 0/25 * * * *")
//    public void outTime() {
//        log.info("订单支付超时判断开始==================================>>>>>>>>>>>");
//        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
//        c.add(Calendar.MINUTE,-30);
//        String startTime = dateFormat.format(c.getTime());
//        log.info("========>>>"+startTime);
//        List<Order> list = payService.findOrderList(startTime);
//        if(CollectionUtils.isNotEmpty(list)){
//            for (Order order: list) {
//                log.info("订单"+order.getOrderNo()+"=====金额："+order.getMoney()+">>>>>>>>>>>已超时");
//                payService.updateOrderStatus("ERROR", "支付超时", "10D", order.getOrderNo());
//            }
//        }
//        log.info("订单支付超时判断结束==================================>>>>>>>>>>>");
//    }
//
//    /**
//     * 业务员推广奖励,3分钟
//     */
//    @Scheduled(cron = "0 0/3 * * * *")
//    public void toExtension() {
//        log.info("业务员推广奖励开始==================================>>>>>>>>>>>");
////        商户奖励
//        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
//        c.add(Calendar.MINUTE,-35);
//        String startTime = dateFormat.format(c.getTime());
//        log.info("========>>>"+startTime);
//        List<MallShop> mallShopList = sendService.findShopId("","",startTime);
//        if(CollectionUtils.isNotEmpty(mallShopList)){
//            for (MallShop mallShop: mallShopList) {
//                AppUser appUserMsg = appUserService.getAppUserMsg( mallShop.getMerchantId(),"");
//                if (StringUtils.isNotNull(appUserMsg) && "10A".equals(appUserMsg.getRewardStatus())
//                        && StringUtils.isNotEmpty(appUserMsg.getFatherPhone())) {
//
//                    addMoney(appUserMsg,new BigDecimal(3));
//                    appUserMsg.setRewardStatus("10B");
//                    appUserService.updateAppUser(appUserMsg);
//                }
//            }
//        }
////-----------------------------------------------------------------------------------------------
//        List<AppUser> list = appUserService.getAppUserFatherPhoneList();
//        for (AppUser appUser: list) {
////            实名
//            if ("10A".equals(appUser.getExtensionStatus()) && "10B".equals(appUser.getUserStatus())) {
//                log.info("实名奖励==========>>>>>>>>");
//                addMoney(appUser,new BigDecimal(1));
//                appUser.setExtensionStatus("10B");
//                appUserService.updateAppUser(appUser);
////            艺人奖励
//                if ("10A".equals(appUser.getYiStatus()) && "2".equals(appUser.getUserType())) {
//                    log.info("艺人奖励==========>>>>>>>>");
//                    addMoney(appUser,new BigDecimal(1));
//                    appUser.setYiStatus("10B");
//                    appUserService.updateAppUser(appUser);
//                }
//            }
//        }
//        log.info("业务员推广奖励结束==================================>>>>>>>>>>>");
//    }
//
//    public void addMoney(AppUser user,BigDecimal bigDecimal){
//        AppUser appUser = appUserService.getAppUserMsg("", user.getFatherPhone());
//        if (StringUtils.isNotNull(appUser)) {
//            log.info(appUser.getRealName()+"推荐的"+user.getRealName()+"=====成功！奖励前"+appUser.getIncome());
//            appUser.setIncome(ResultJSONUtils.updateTotalMoney(appUser, bigDecimal, "+"));
//            appUserService.updateAppUser(appUser);
//
//            ChangeMoney tGMoney = new ChangeMoney();
//            tGMoney.setChangeNo("YYTG" + RandomSaltUtil.randomNumber(14));
//            tGMoney.setId(RandomSaltUtil.generetRandomSaltCode(32));
//            tGMoney.setStatus("10B");
//            tGMoney.setMobile(appUser.getPhone());
//            tGMoney.setMerchantId(appUser.getId());
//            tGMoney.setSourceId(user.getId());
//            tGMoney.setNote("推广收益");
//            tGMoney.setTradeType("TG");
//            tGMoney.setMoney(bigDecimal);
//            tGMoney.setHistoryMoney(appUser.getIncome().subtract(bigDecimal));
//            payService.createShouMoney(tGMoney);
//        }
//    }
//
//    /**
//     * 6小时执行一次（执行娱悦现场定时任务）
//     */
//    @Scheduled(cron = "0 0 6 * * ?")
//    private void updateYuYueSiteStartStatus(){
//        log.info("执行娱悦现场定时任务：---------->");
//        List<YuYueSite> yuYueSites = yuYueSiteService.searchYuYueSiteInfo("","10A","","","","");
//        int times=0;
//        if (StringUtils.isEmpty(yuYueSites)) return;
//        log.info("查出的数据："+yuYueSites.size());
//        for (YuYueSite yuyueSite: yuYueSites) {
//            String startData = yuyueSite.getStartTime();
//            if (StringUtils.isEmpty(startData)) continue;
//            try {
//                log.info(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//                log.info(startData.split(" ")[0]);
//                if (new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(startData.split(" ")[0])){
//                    yuyueSite.setStatus("10B");
//                    times++;
//                    yuYueSiteService.updateYuYueSite(yuyueSite);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        log.info("已修改次数："+times);
//
//    }
//
//
//    /**
//     * 8小时执行一次（执行娱悦现场定时任务）
//     */
//    @Scheduled(cron = "0 0 7 * * ?")
//    private void updateYuYueSiteEndStatus(){
//        log.info("执行娱悦现场定时任务：---------->结束状态");
//        List<YuYueSite> yuYueSites = yuYueSiteService.searchYuYueSiteInfo("","10A","","","","");
//        int times=0;
//        if (StringUtils.isEmpty(yuYueSites)) return;
//        log.info("查出的数据："+yuYueSites.size());
//        for (YuYueSite yuyueSite: yuYueSites) {
//            String endTime = yuyueSite.getEndTime();
//            if (StringUtils.isEmpty(endTime)) continue;
//            try {
//                if (new Date().after(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime))){
//                    yuyueSite.setStatus("10C");
//                    times++;
//                    yuYueSiteService.updateYuYueSite(yuyueSite);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        log.info("已修改次数："+times);
//    }
//
//
//    /**
//     * 8小时执行一次（执行爆款定时任务）
//     */
//    @Scheduled(cron = "0 0 8 * * ?")
//    private void updateCommodityEndStatus(){
//        log.info("执行爆款定时任务：---------->结束状态");
//        List<Commodity> commodityInfo = commodityService.getCommodityInfo("", "", "", "10C", "", "");
//        if (StringUtils.isEmpty(commodityInfo)) return;
//        for (Commodity commodity: commodityInfo
//             ) {
//            String endTime = commodity.getEndDate();
//            if (StringUtils.isEmpty(endTime)) continue;
//            try {
//                if (new Date().after(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime))){
//                    commodity.setStatus("10D");
//                     commodityService.updateCommodityInfo(commodity);
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Scheduled(cron = "0 */10 * * * ?")
//    private void updateShopBusinessStatusIsOpen(){
//        log.info("执行商城定时任务：---------->营业状态111111");
//        List<MallShop> open = mallShopService.getShopByBusinessStatus("open");
//        String format = new SimpleDateFormat("hh:mm").format(new Date());
//        if (StringUtils.isEmpty(open))return;
//        for (MallShop mallShop:open) {
//            String[] split = mallShop.getBusinessTime().split("-");
//            if (format.compareTo(split[0]) >= 0 && format.compareTo(split[1]) < 0){
//                continue;
//            }else {
//                mallShopService.updateMyMallShopStatus("rest",mallShop.getShopId());
//            }
//
//        }
//    }
/*            01:00      02:00-07:00  open            16           */
//    @Scheduled(cron = "0 */30 * * * ?")
//    private void updateShopBusinessStatusIsRest(){
//        log.info("执行商城定时任务：---------->打烊状态000000");
//        List<MallShop> rest = mallShopService.getShopByBusinessStatus("rest");
//        String format = new SimpleDateFormat("HH:mm").format(new Date());
//        if (StringUtils.isEmpty(rest))return;
//        for (MallShop mallShop:rest) {
//            String[] split = mallShop.getBusinessTime().split("-");
//            if (format.compareTo(split[0]) >= 0 && format.compareTo(split[1]) < 0){
//                mallShopService.updateMyMallShopStatus("open",mallShop.getShopId());
//            }
//
//        }
//    }
}
