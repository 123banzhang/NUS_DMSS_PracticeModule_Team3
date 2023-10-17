package com.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * metahuman表
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-16
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Metahuman implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * metahumanID
     */
    @TableId(value = "mid", type = IdType.AUTO)
    private Long mid;

    /**
     * 性别
     */
    private String gender;

    /**
     * 名字
     */
    private String name;

    /**
     * Status (online/offline)
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * personality
     */
    private String personality;

    /**
     * voice
     */
    private String voice;


}
