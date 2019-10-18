package com.yuyue.boss.api.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuyue.boss.annotation.LoginRequired;
import com.yuyue.boss.api.domain.Advertisement;
import com.yuyue.boss.api.domain.AppUser;
import com.yuyue.boss.api.domain.Order;
import com.yuyue.boss.api.service.AdReviewService;
import com.yuyue.boss.api.service.AppUserService;
import com.yuyue.boss.api.service.OrderService;
import com.yuyue.boss.enums.CodeEnum;
import com.yuyue.boss.enums.ResponseData;
import com.yuyue.boss.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/order" ,produces = "application/json; charset=UTF-8")
@Slf4j
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;


    /**
     * 查询订单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getOrderList")
    @ResponseBody
    @RequiresPermissions("OrderPayment:menu")//具有 user:detail 权限的用户才能访问此方法
    @LoginRequired
    public ResponseData getAdReviewList(Order order, HttpServletRequest request, HttpServletResponse response){
        Map<String, String> parameterMap = getParameterMap(request,response);
        log.info("查询订单-------------->>/order/getOrderList");
        String page=request.getParameter("page");
        if (StringUtils.isEmpty(page) || !page.matches("[0-9]+"))
            page = "1";
       if (StringUtils.isNotEmpty(order.getId())){
           Order orderById = orderService.getOrderById(order.getId());
           return new ResponseData(orderById);
       }
        else {
           PageHelper.startPage(Integer.parseInt(page), 10);
//           List<String> typeList = new ArrayList<>();
//           StringBuffer sb = new StringBuffer();
//           if (StringUtils.isNotEmpty(order.getType())) {
//               String[] split = order.getType().split(",");
//               for (int i = 0; i < split.length; i++) {
//                   if (i == split.length-1) {
//                       sb.append(split[i]);
//                   } else {
//                       sb.append(split[i]).append(",");
//                   }
//               }
//           }
//           if (CollectionUtils.isNotEmpty(typeList)) {
//               order.setTypeList(typeList);
//           }
           List<Order> orderList = orderService.getOrderList(parameterMap.get("orderNo"),parameterMap.get("realName"),
                   parameterMap.get("mobile"),parameterMap.get("tradeType"),parameterMap.get("status"),
                   parameterMap.get("startTime"),parameterMap.get("endTime"),parameterMap.get("type"));
           PageInfo<Order> pageInfo=new PageInfo<>(orderList);
           long total = pageInfo.getTotal();
           int pages = pageInfo.getPages();
           int currentPage = Integer.parseInt(page);
           return new ResponseData(orderList, currentPage,(int) total,pages);
       }

    }

    /**
     * 修改订单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateOrder")
    @ResponseBody
    @RequiresPermissions("OrderPayment:save")//具有 user:detail 权限的用户才能访问此方法
    @LoginRequired
    public ResponseData updateAdReviewStatus( Order order, HttpServletRequest request, HttpServletResponse response){
        getParameterMap(request,response);
        log.info("修改订单-------------->>/order/updateAdReviewStatus");

        orderService.updateOrder(order);

        return new ResponseData(CodeEnum.SUCCESS);
    }


    @RequestMapping("/deleteOrderById")
    @ResponseBody
    @RequiresPermissions("OrderPayment:remove")//具有 user:detail 权限的用户才能访问此方法
    @LoginRequired
    public ResponseData deleteOrderById(HttpServletRequest request, HttpServletResponse response){
        getParameterMap(request,response);
        log.info("删除订单-------------->>/order/deleteOrderById");
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)){
            return new ResponseData(CodeEnum.PARAM_ERROR.getCode(),"参数id为空！！");
        }else
            orderService.deleteOrderById(id);
        return new ResponseData(CodeEnum.SUCCESS);
    }
}
