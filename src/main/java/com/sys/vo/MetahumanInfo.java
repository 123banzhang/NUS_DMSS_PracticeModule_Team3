package com.sys.vo;

import lombok.Data;

/**
 * 主要用于查询
 */
@Data
public class MetahumanInfo {

    /**
     * description
     */
    private String description;

    /**
     * 性别
     */
    private String gender;

    /**
     * 名字
     */
    private String name;

    /**
     * subname
     */
    private String subname;

    /**
     * category
     */
    private String category;

    /**
     * Status (online/offline)
     */
    private String status;


}
