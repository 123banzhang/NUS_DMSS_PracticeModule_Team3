package com.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * knowledge表
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Knowledge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * knowledgeID
     */
    @TableId(value = "kid", type = IdType.AUTO)
    private Long kid;

    /**
     * 内容
     */
    private String content;

    /**
     * personalityID
     */
    private Long pid;


}
