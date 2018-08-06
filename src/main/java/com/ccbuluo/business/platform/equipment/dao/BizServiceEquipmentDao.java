package com.ccbuluo.business.platform.equipment.dao;

import com.ccbuluo.business.constants.Constants;
import com.ccbuluo.business.entity.BizServiceEquipment;
import com.ccbuluo.business.platform.equipment.dto.DetailBizServiceEquipmentDTO;
import com.ccbuluo.business.platform.equipment.dto.SaveBizServiceEquipmentDTO;
import com.ccbuluo.business.platform.equipment.dto.SaveBizServiceEquiptypeDTO;
import com.ccbuluo.dao.BaseDao;
import com.ccbuluo.db.Page;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 物料 dao
 * @author liuduo
 * @date 2018-07-17 13:57:53
 * @version V1.0.0
 */
@Repository
public class BizServiceEquipmentDao extends BaseDao<BizServiceEquipment> {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
    return namedParameterJdbcTemplate;
    }

    /**
     * 保存 物料实体
     * @param entity 物料实体
     * @return int 影响条数
     * @author liuduo
     * @date 2018-07-17 13:57:53
     */
    public int saveEntity(BizServiceEquipment entity) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO biz_service_equipment ( equip_code,equip_name,equiptype_id,equip_unit,")
            .append("remark,creator,create_time,operator,operate_time,delete_flag")
            .append(" ) VALUES (  :equipCode, :equipName, :equiptypeId, :equipUnit, :remark, :creator,")
            .append(" :createTime, :operator, :operateTime, :deleteFlag )");
        return super.save(sql.toString(), entity);
    }

    /**
     * 编辑 物料实体
     * @param entity 物料实体
     * @return 影响条数
     * @author liuduo
     * @date 2018-07-17 13:57:53
     */
    public int update(BizServiceEquipment entity) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE biz_service_equipment SET ")
            .append("equip_name = :equipName,equiptype_id = :equiptypeId,equip_unit = :equipUnit,remark = :remark,operator = :operator,")
            .append(" operate_time = :operateTime WHERE id= :id");
        return super.updateForBean(sql.toString(), entity);
    }

    /**
     * 获取物料详情
     * @param id  id
     * @author liuduo
     * @date 2018-07-17 13:57:53
     */
    public DetailBizServiceEquipmentDTO getById(long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT bse.id,bse.equip_name,bse.remark,bse.equip_unit,bse.equip_code,bse.equiptype_id,bsee.type_name AS equiptypeName ")
            .append(" FROM biz_service_equipment AS bse LEFT JOIN biz_service_equiptype AS bsee ON bsee.id = bse.equiptype_id")
            .append(" WHERE bse.id = :id");
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        return super.findForBean(DetailBizServiceEquipmentDTO.class, sql.toString(), params);
    }

    /**
     * 检查物料类型下是否有物料
     * @param id 物料类型id
     * @return 是否有数据
     * @author liuduo
     * @date 2018-07-17 18:47:06
     */
    public Boolean checkEquipmentByTypeId(Long id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);

        String sql = "SELECT COUNT(id) > 0 FROM biz_service_equipment WHERE equiptype_id = :id";

        return findForObject(sql, params, Boolean.class);
    }


    /**
     * 检查物料是否重复(新增用)
     * @param equipName 名字
     * @return 名字的是否重复
     * @author liuduo
     * @date 2018-07-17 19:01:42
     */
    public Boolean checkName(String equipName) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("equipName", equipName);

        String sql = "SELECT COUNT(id) > 0 FROM biz_service_equipment WHERE equip_name = :equipName";

        return findForObject(sql, params, Boolean.class);
    }


    /**
     * 检查物料是否重复（编辑用）
     * @param saveBizServiceEquipmentDTO 物料类型实体
     * @return 名字的是否重复
     * @author liuduo
     * @date 2018-07-17 14:40:42
     */
    public Boolean editCheckName(SaveBizServiceEquipmentDTO saveBizServiceEquipmentDTO) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("equipName", saveBizServiceEquipmentDTO.getEquipName());
        params.put("id", saveBizServiceEquipmentDTO.getId());

        String sql = "SELECT COUNT(id) > 0 FROM biz_service_equipment WHERE id <> :id AND equip_name = :equipName";

        return findForObject(sql, params, Boolean.class);
    }

    /**
     * 查询物料列表
     * @param equiptypeId 物料类型id
     * @param keyword 关键字
     * @param offset 起始数
     * @param pagesize 每页数
     * @return 物料列表
     * @author liuduo
     * @date 2018-07-17 20:10:35
     */
    public Page<DetailBizServiceEquipmentDTO> queryList(Long equiptypeId, String keyword, Integer offset, Integer pagesize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("deleteFlag", Constants.DELETE_FLAG_NORMAL);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT bse.id,bse.equip_code,bse.equip_unit,bse.equip_name,bsee.type_name,bse.equiptype_id,bsee.type_name AS equiptypeName ")
            .append("  FROM biz_service_equipment AS bse LEFT JOIN biz_service_equiptype AS bsee ON bsee.id = bse.equiptype_id WHERE 1=1");
        if (equiptypeId != null) {
            params.put("equiptypeId", equiptypeId);
            sql.append(" AND bse.equiptype_id = :equiptypeId");
        }
        if (StringUtils.isNotBlank(keyword)) {
            params.put("keywork", keyword);
            sql.append(" AND bse.equip_name LIKE CONCAT('%',:keywork,'%')");
        }
        sql.append(" AND bse.delete_flag = :deleteFlag ORDER BY bse.operate_time DESC");

        return queryPageForBean(DetailBizServiceEquipmentDTO.class, sql.toString(), params, offset, pagesize);
    }

    /**
     * 根据物料类型id查询物料
     * @param equiptypeId 物料类型id
     * @return 物料
     * @author liuduo
     * @date 2018-08-02 10:41:20
     */
    public List<DetailBizServiceEquipmentDTO> queryEqupmentByEquiptype(Long equiptypeId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("equiptypeId", equiptypeId);

        String sql = "SELECT equip_code,equip_name FROM biz_service_equipment WHERE equiptype_id = :equiptypeId";

        return queryListBean(DetailBizServiceEquipmentDTO.class, sql, params);
    }
}
