package com.ccbuluo.business.constants;

/**
 *
 * @author liupengfei
 * @date 2018-07-03 17:27:05
 */
public enum CodePrefixEnum {
    FW("服务中心"),
    FC("仓库"),
    FP("零配件"),
    FK("零配件分类"),
    FM("零配件模板"),
    FS("员工"),
    FA("物料"),
    FL("工时"),
    FG("供应商"),
    FR("维修车"),
    FO("客户经理组织架构"),
    FD("车型标签"),
    FB("车品牌"),
    FN("车系"),
    FH("车型"),
    FJ("车辆"),
    SW("申请单号"),
    R("入库单号"),
    C("出库单号"),
    PK("盘库单号"),
    TH("退换单号");


    CodePrefixEnum(String label){
        this.label = label;
    }

    private String label;

    public String getLabel(){
        return label;
    }
}
