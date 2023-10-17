package com.sys.vo;

import lombok.Data;

@Data
public class MetahumanInfo {
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
     * personality
     */
    private String personality;

    /**
     * voice
     */
    private String voice;

}
