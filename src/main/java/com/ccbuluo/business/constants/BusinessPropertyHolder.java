package com.ccbuluo.business.constants;

import com.ccbuluo.business.entity.BizAllocateApply;
import com.ccbuluo.business.platform.allocateapply.service.restructureapplyhandle.BarterApplyHandle;
import com.ccbuluo.business.platform.allocateapply.service.restructureapplyhandle.PurchaseApplyHandle;
import com.ccbuluo.business.platform.allocateapply.service.restructureapplyhandle.RefundApplyHandle;
import com.ccbuluo.business.platform.allocateapply.service.restructureapplyhandle.SameLevelApplyHandle;
import com.ccbuluo.core.exception.CommonException;
import com.ccbuluo.core.thrift.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 业务相关全局配置属性
 * @author liupengfei
 * @date 2018-05-08 10:25:37
 */
@Component

public class BusinessPropertyHolder {

    // 用户中心token key前缀名称
    public static String PROJECTCODE_REDIS_KEYPERFIX;
    @Value("${redis.perfix.projectcode}")
    public void setProjectcodeRedisKeyperfix(String projectcodeRedisKeyperfix) {
        PROJECTCODE_REDIS_KEYPERFIX = projectcodeRedisKeyperfix;
    }

    // 顶级服务中心code
    public static String ORGCODE_TOP_SERVICECENTER;
    @Value("${base.topservicecenter}")
    public void setTopStore(String topservicecenter) {
        ORGCODE_TOP_SERVICECENTER = topservicecenter;
    }

    // 客户经理组织机构
    public static String ORGCODE_TOP_CUSMANAGER;
    @Value("${base.custmanager}")
    public void setCustManager(String custManager) {
        BusinessPropertyHolder.ORGCODE_TOP_CUSMANAGER = custManager;
    }

    // custmanagerrolecode 客户经理角色
    public static String ROLECODE_CUSMANAGER;
    @Value("${base.custmanagerrolecode}")
    public void setCustManagerRoleCode(String custManagerRoleCode) {
        BusinessPropertyHolder.ROLECODE_CUSMANAGER = custManagerRoleCode;
    }


    public static String PURCHASE;
    @Value("${base.purchase}")
    public void setPurchase(String purchase) {
        BusinessPropertyHolder.PURCHASE = purchase;
    }

    public static String SAMELEVEL;
    @Value("${base.samelevel}")
    public void setSamelevel(String samelevel) {
        BusinessPropertyHolder.SAMELEVEL = samelevel;
    }

    public static String BARTER;
    @Value("${base.barter}")
    public void setBarter(String barter) {
        BusinessPropertyHolder.BARTER = barter;
    }

    public static String REFUND;
    @Value("${base.refund}")
    public void setRefund(String refund) {
        BusinessPropertyHolder.REFUND = refund;
    }

    // 售后顶级机构的编号
    public static String ORGCODE_AFTERSALE_PLATFORM;
    @Value("${base.topplatform}")
    public void setOrgcodeAftersalePlatform(String orgcodeAftersalePlatform) {
        ORGCODE_AFTERSALE_PLATFORM = orgcodeAftersalePlatform;
    }
}
