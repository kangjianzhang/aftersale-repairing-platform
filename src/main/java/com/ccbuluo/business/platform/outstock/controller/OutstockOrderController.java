package com.ccbuluo.business.platform.outstock.controller;

import com.ccbuluo.business.entity.BizOutstockOrder;
import com.ccbuluo.business.entity.BizOutstockorderDetail;
import com.ccbuluo.business.entity.BizOutstockplanDetail;
import com.ccbuluo.business.platform.outstock.dto.BizOutstockOrderDTO;
import com.ccbuluo.business.platform.outstock.service.OutstockOrderService;
import com.ccbuluo.core.controller.BaseController;
import com.ccbuluo.db.Page;
import com.ccbuluo.http.StatusDto;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库单controller
 * @author liuduo
 * @version v1.0.0
 * @date 2018-08-09 11:33:02
 */
@Api(tags = "出库单")
@RestController
@RequestMapping("/platform/outstockorder")
public class OutstockOrderController extends BaseController {

    @Autowired
    private OutstockOrderService outstockOrderService;

    /**
     * 保存出库单
     * @param applyNo 申请单号
     * @param outRepositoryNo 出库仓库编号
     * @param transportorderNo 物流单号
     * @param bizOutstockorderDetailList 出库单详单
     * @return 是否保存成功
     * @author liuduo
     * @date 2018-08-07 15:15:07
     */
    @ApiOperation(value = "出库单新增", notes = "【刘铎】")
    @ApiImplicitParams({@ApiImplicitParam(name = "applyNo", value = "申请单号",  required = true, paramType = "query"),
        @ApiImplicitParam(name = "outRepositoryNo", value = "出库仓库编号",  required = true, paramType = "query"),
        @ApiImplicitParam(name = "transportorderNo", value = "物流单号",  required = false, paramType = "query")})
    @PostMapping("/save")
    public StatusDto<String> saveOutstockOrder(@RequestParam String applyNo,
                                               @RequestParam String outRepositoryNo,
                                               @RequestParam(required = false) String transportorderNo,
                                               @ApiParam(name = "出库单详单集合", value = "传入json格式", required = true)@RequestBody List<BizOutstockorderDetail> bizOutstockorderDetailList) {
        return outstockOrderService.saveOutstockOrder(applyNo, outRepositoryNo, transportorderNo, bizOutstockorderDetailList);
    }

    /**
     * 查询等待发货的申请单
     * @return 申请单号
     * @author liuduo
     * @date 2018-08-11 12:53:03
     */
    @ApiOperation(value = "查询申请单", notes = "【刘铎】")
    @GetMapping("/queryapplyno")
    public StatusDto<List<String>> queryApplyNo() {
        return StatusDto.buildDataSuccessStatusDto(outstockOrderService.queryApplyNo());
    }

    /**
     * 根据申请单号查询出库计划
     * @param applyNo 申请单号
     * @param productType 商品类型
     * @return 出库计划
     * @author liuduo
     * @date 2018-08-11 13:17:42
     */
    @ApiOperation(value = "根据申请单号查询出库计划", notes = "【刘铎】")
    @ApiImplicitParams({@ApiImplicitParam(name = "applyNo", value = "申请单号",  required = true, paramType = "query"),
        @ApiImplicitParam(name = "productType", value = "商品类型（物料或者备件）",  required = true, paramType = "query")})
    @GetMapping("/queryoutstockplan")
    public StatusDto<List<BizOutstockplanDetail>> queryOutstockplan(@RequestParam String applyNo,
                                                                    @RequestParam String productType) {
        return StatusDto.buildDataSuccessStatusDto(outstockOrderService.queryOutstockplan(applyNo, productType));
    }


    /**
     * 分页查询出库单列表
     * @param productType 商品类型
     * @param outstockType 入库类型
     * @param outstockNo 入库单号
     * @param offset 起始数
     * @param pagesize 每页数
     * @return 入库单列表
     * @author liuduo
     * @date 2018-08-11 16:43:19
     */
    @ApiOperation(value = "出库单列表", notes = "【刘铎】")
    @ApiImplicitParams({@ApiImplicitParam(name = "productType", value = "商品类型(物料或者备件)",  required = true, paramType = "query"),
        @ApiImplicitParam(name = "outstockType", value = "出库类型",  required = false, paramType = "query"),
        @ApiImplicitParam(name = "outstockNo", value = "出库单单号",  required = false, paramType = "query"),
        @ApiImplicitParam(name = "offset", value = "起始数",  required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pagesize", value = "每页数",  required = true, paramType = "query", dataType = "int")})
    @GetMapping("/queryinstocklist")
    public StatusDto<Page<BizOutstockOrder>> queryInstockList(@RequestParam String productType,
                                                              @RequestParam(required = false) String outstockType,
                                                              @RequestParam(required = false) String outstockNo,
                                                              @RequestParam(defaultValue = "0") Integer offset,
                                                              @RequestParam(defaultValue = "10") Integer pagesize) {
        return StatusDto.buildDataSuccessStatusDto(outstockOrderService.queryOutstockList(productType, outstockType, outstockNo, offset, pagesize));
    }


    /**
     * 根据出库单号查询出库单详情
     * @param outstockNo 出库单号
     * @return 出库单详情
     * @author liuduo
     * @date 2018-08-13 10:23:03
     */
    @ApiOperation(value = "出库单详情", notes = "【刘铎】")
    @ApiImplicitParam(name = "outstockNo", value = "出库单单号",  required = true, paramType = "query")
    @GetMapping("/getbyoutstockno")
    public StatusDto<BizOutstockOrderDTO> getByOutstockNo(@RequestParam String outstockNo) {
        return StatusDto.buildDataSuccessStatusDto(outstockOrderService.getByOutstockNo(outstockNo));
    }

}
