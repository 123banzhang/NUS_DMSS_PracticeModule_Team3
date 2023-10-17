package com.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.entity.Metahuman;
import com.sys.mapper.MetahumanMapper;
import com.sys.service.IMetahumanService;
import com.sys.vo.MetahumanInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetahumanServiceImpl extends ServiceImpl<MetahumanMapper, Metahuman> implements IMetahumanService {

    @Autowired
    private MetahumanMapper metahumanMapper;

    @Override
    public List<Metahuman> findMetahumanBycondition(MetahumanInfo metahumanInfo) {
        QueryWrapper<Metahuman> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(metahumanInfo.getGender() != null, "gender", metahumanInfo.getGender())
                .eq(metahumanInfo.getName() != null, "name", metahumanInfo.getName())
                .eq(metahumanInfo.getStatus() != null, "status", metahumanInfo.getStatus())
                .like(metahumanInfo.getPersonality() != null, "personality", metahumanInfo.getPersonality())
                .like(metahumanInfo.getVoice() != null, "voice", metahumanInfo.getVoice());
        return metahumanMapper.selectList(queryWrapper);
    }

    @Override
    public boolean createMetahuman(MetahumanInfo metahumanInfo) {
        Metahuman metahuman = new Metahuman();
        metahuman.setGender(metahumanInfo.getGender())
                .setName(metahumanInfo.getName())
                .setStatus(metahumanInfo.getStatus())
                .setPersonality(metahumanInfo.getPersonality())
                .setVoice(metahumanInfo.getVoice());
        int result = metahumanMapper.insert(metahuman);
        return result > 0;
    }

    @Override
    public boolean updateMetahuman(Long mid, MetahumanInfo metahumanInfo) {
        Metahuman metahuman = metahumanMapper.selectById(mid);
        if (metahuman != null) {
            metahuman.setGender(metahumanInfo.getGender())
                    .setName(metahumanInfo.getName())
                    .setStatus(metahumanInfo.getStatus())
                    .setPersonality(metahumanInfo.getPersonality())
                    .setVoice(metahumanInfo.getVoice());
            int result = metahumanMapper.updateById(metahuman);
            return result > 0;
        }
        return false;
    }

    @Override
    public boolean deleteMetahuman(Long mid) {
        int result = metahumanMapper.deleteById(mid);
        return result > 0;
    }
}
