package com.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * voice表
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Voice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * voiceID
     */
    @TableId(value = "vid", type = IdType.AUTO)
    private Long vid;

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

}
