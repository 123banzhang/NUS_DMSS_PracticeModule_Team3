package com.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.entity.Npc;
import com.sys.mapper.NpcMapper;
import com.sys.service.INpcService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * npc表 服务实现类
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
@Service
public class NpcServiceImpl extends ServiceImpl<NpcMapper, Npc> implements INpcService {

}
