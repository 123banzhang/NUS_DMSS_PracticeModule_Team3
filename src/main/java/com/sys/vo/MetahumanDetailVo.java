package com.sys.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 主要用于返回
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MetahumanDetailVo {

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * Status (online/offline)
     */
    private String status;

    /**
     * speaker
     */
    private String speaker;

    /**
     * pitch
     */
    private Float pitch;

    /**
     * speed
     */
    private Float speed;

    /**
     * emotion
     */
    private String emotion;

    /**
     * voice链接
     */
    private String voicesource;

    private String avatarid;

}
