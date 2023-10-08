package com.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.entity.Personality;
import com.sys.mapper.PersonalityMapper;
import com.sys.service.IPersonalityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * personality表 服务实现类
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
@Service
public class PersonalityServiceImpl extends ServiceImpl<PersonalityMapper, Personality> implements IPersonalityService {

}
