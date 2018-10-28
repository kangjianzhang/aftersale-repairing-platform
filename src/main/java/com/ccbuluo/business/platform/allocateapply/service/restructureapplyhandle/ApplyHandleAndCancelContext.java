package com.ccbuluo.business.platform.allocateapply.service.restructureapplyhandle;

import com.ccbuluo.ServicePlatformApplication;
import com.ccbuluo.business.constants.Constants;
import com.ccbuluo.business.constants.InstockTypeEnum;
import com.ccbuluo.business.constants.OutstockTypeEnum;
import com.ccbuluo.business.entity.BizAllocateApply;
import com.ccbuluo.business.entity.BizAllocateApply.AllocateApplyTypeEnum;
import com.ccbuluo.business.platform.allocateapply.dao.BizAllocateApplyDao;
import com.ccbuluo.business.platform.order.dao.BizAllocateTradeorderDao;
import com.ccbuluo.core.exception.CommonException;
import com.ccbuluo.core.thrift.spring.SpringUtil;
import com.ccbuluo.http.StatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 申请处理入口
 *
 * @author weijb
 * @version v1.0.0
 * @date 2018-08-13 19:26:33
 */
public class ApplyHandleAndCancelContext {

    private ApplyHandle applyHandle;
    ConfigurableApplicationContext ctx = SpringApplication.run(ServicePlatformApplication.class);


    /**
     * 申请处理
     * @param allocateApplyTypeEnum 申请的类型
     * @return StatusDto
     * @author weijb
     * @date 2018-08-18 17:47:52
     */
    public ApplyHandleAndCancelContext(AllocateApplyTypeEnum allocateApplyTypeEnum) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ConfigurableEnvironment environment = ctx.getEnvironment();// 获取 边境变量
        String property = environment.getProperty(allocateApplyTypeEnum.toString().toLowerCase());
        Class<?> aClass = Class.forName(property);
        applyHandle = (ApplyHandle)SpringUtil.getBean(aClass);
        /*switch (allocateApplyTypeEnum) {
                case PURCHASE:    // 采购
                    this.applyHandle = SpringUtil.getBean(PurchaseApplyHandle.class);
                    break;
                case SAMELEVEL:    // 调拨
                    this.applyHandle = SpringUtil.getBean(SameLevelApplyHandle.class);
                    break;
                case BARTER:    // 商品换货
                    this.applyHandle = SpringUtil.getBean(BarterApplyHandle.class);
                    break;
                case REFUND:    //  退货
                    this.applyHandle = SpringUtil.getBean(RefundApplyHandle.class);
                    break;
                default:
                    throw new CommonException(Constants.ERROR_CODE, "出现了未知处理类型！");
            }*/
    }

    public StatusDto getApplyHandleResult(String applyNo){
        return applyHandle.applyHandle(applyNo);
    }

    public StatusDto getCancelApplyResult(String applyNo){
        return applyHandle.cancelApply(applyNo);
    }


}
