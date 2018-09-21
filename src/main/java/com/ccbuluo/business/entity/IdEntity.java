package com.ccbuluo.business.entity;

import java.io.Serializable;

/**
 * 实体类的基类
 * @author: guotao
 * @date: 2018-3-14 18:17:00
 */
public class IdEntity implements Serializable {

    private static final long serialVersionUID = -2716222356509348153L;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
