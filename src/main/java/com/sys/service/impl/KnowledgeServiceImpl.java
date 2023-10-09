package com.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.entity.Knowledge;
import com.sys.mapper.KnowledgeMapper;
import com.sys.service.IKnowledgeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * knowledge表 服务实现类
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements IKnowledgeService {

}
