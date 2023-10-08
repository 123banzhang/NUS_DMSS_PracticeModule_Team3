package com.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * personality表
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Personality implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * personalityID
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    /**
     * 描述
     */
    private String description;

    /**
     * 特征
     */
    private String characteristics;

    /**
     * 禁用词
     */
    private String forbiddenWords;

    /**
     * knowledgeID
     */
    private Long kid;


}
