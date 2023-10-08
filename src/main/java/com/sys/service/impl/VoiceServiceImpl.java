package com.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.entity.Voice;
import com.sys.mapper.VoiceMapper;
import com.sys.service.IVoiceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * voice表 服务实现类
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
@Service
public class VoiceServiceImpl extends ServiceImpl<VoiceMapper, Voice> implements IVoiceService {

}
