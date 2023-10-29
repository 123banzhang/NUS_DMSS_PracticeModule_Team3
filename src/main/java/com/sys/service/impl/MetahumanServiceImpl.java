package com.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.entity.Metahuman;
import com.sys.entity.Voice;
import com.sys.mapper.MetahumanMapper;
import com.sys.mapper.VoiceMapper;
import com.sys.service.IMetahumanService;
import com.sys.vo.MetahumanDetailVo;
import com.sys.vo.MetahumanInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetahumanServiceImpl extends ServiceImpl<MetahumanMapper, Metahuman> implements IMetahumanService {

    @Autowired
    private MetahumanMapper metahumanMapper;

    @Autowired
    private VoiceMapper voiceMapper;

    @Override
    public List<MetahumanDetailVo> findMetahumanByCondition(MetahumanInfo metahumanInfo) {
        QueryWrapper<Metahuman> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(metahumanInfo.getDescription() != null, "description", metahumanInfo.getDescription())
                .like(metahumanInfo.getGender() != null, "gender", metahumanInfo.getGender())
                .like(metahumanInfo.getName() != null, "name", metahumanInfo.getName())
                .like(metahumanInfo.getSubname() != null, "subname", metahumanInfo.getSubname())
                .like(metahumanInfo.getCategory() != null, "category", metahumanInfo.getCategory())
                .like(metahumanInfo.getStatus() != null, "status", metahumanInfo.getStatus());

        List<Metahuman> metahumans = metahumanMapper.selectList(queryWrapper);

        List<MetahumanDetailVo> vos = new ArrayList<>();
        for (Metahuman metahuman : metahumans) {
            MetahumanDetailVo vo = new MetahumanDetailVo();
            vo.setMid(metahuman.getMid())
                    .setDescription(metahuman.getDescription())
                    .setGender(metahuman.getGender())
                    .setName(metahuman.getName())
                    .setSubname(metahuman.getSubname())
                    .setCategory(metahuman.getCategory())
                    .setStatus(metahuman.getStatus())
                    .setAvatarid(metahuman.getAvatarid());

            Voice voice = voiceMapper.selectById(metahuman.getVid());
            if (voice != null) {
                vo.setSpeaker(voice.getSpeaker())
                        .setPitch(voice.getPitch())
                        .setSpeed(voice.getSpeed())
                        .setEmotion(voice.getEmotion())
                        .setVoicesource(voice.getVoicesource());
            }

            vos.add(vo);
        }

        return vos;
    }

    @Override
    @Transactional
    public boolean createMetahuman(MetahumanDetailVo metahumanDetail) {
        // Creating a Voice entity and inserting it into the voice table
        Voice voice = new Voice();
        voice.setSpeaker(metahumanDetail.getSpeaker())
                .setPitch(metahumanDetail.getPitch())
                .setSpeed(metahumanDetail.getSpeed())
                .setEmotion(metahumanDetail.getEmotion())
                .setVoicesource(metahumanDetail.getVoicesource());

        int voiceResult = voiceMapper.insert(voice);

        // Checking if the voice entry is created successfully
        if (voiceResult <= 0) {
            return false;
        }

        // Creating a Metahuman entity and setting the voiceId to the generated voice id
        LocalDateTime now = LocalDateTime.now(); // get current time
        Metahuman metahuman = new Metahuman();
        metahuman.setGender(metahumanDetail.getGender())
                .setName(metahumanDetail.getName())
                .setStatus(metahumanDetail.getStatus())
                .setCreateTime(now)
                .setUpdateTime(now)
                .setVid(voice.getVid())
                .setAvatarid(metahumanDetail.getAvatarid()); // Setting the generated voiceId

        int metahumanResult = metahumanMapper.insert(metahuman);

        return metahumanResult > 0;
    }

    @Override
    @Transactional
    public boolean updateMetahuman(Long mid, MetahumanDetailVo metahumanDetail) {
        Metahuman metahuman = metahumanMapper.selectById(mid);

        if (metahuman != null) {
            LocalDateTime now = LocalDateTime.now(); // get current time

            // Update Metahuman attributes
            metahuman.setGender(metahumanDetail.getGender())
                    .setName(metahumanDetail.getName())
                    .setStatus(metahumanDetail.getStatus())
                    .setUpdateTime(now)
                    .setDescription(metahumanDetail.getDescription())
                    .setAvatarid(metahumanDetail.getAvatarid())
                    .setCategory(metahumanDetail.getCategory())
                    .setSubname(metahumanDetail.getSubname());

            int metahumanResult = metahumanMapper.updateById(metahuman);

            // Check if the Metahuman update was successful
            if (metahumanResult <= 0) {
                return false;
            }

            // Update Voice attributes if vid is not null
            if (metahuman.getVid() != null) {
                Voice voice = voiceMapper.selectById(metahuman.getVid());

                if (voice != null) {
                    voice.setSpeaker(metahumanDetail.getSpeaker())
                            .setPitch(metahumanDetail.getPitch())
                            .setSpeed(metahumanDetail.getSpeed())
                            .setEmotion(metahumanDetail.getEmotion())
                            .setVoicesource(metahumanDetail.getVoicesource());

                    int voiceResult = voiceMapper.updateById(voice);

                    // Check if the Voice update was successful
                    return voiceResult > 0;
                }
            }
        }

        return false;
    }

    @Override
    @Transactional
    public boolean deleteMetahuman(Long mid) {
        Metahuman metahuman = metahumanMapper.selectById(mid);

        if (metahuman != null && metahuman.getVid() != null) {
            // Deleting the associated Voice entity first
            int voiceResult = voiceMapper.deleteById(metahuman.getVid());

            // Check if the Voice deletion was successful
            if (voiceResult <= 0) {
                return false;
            }
        }

        // Deleting the Metahuman entity
        int metahumanResult = metahumanMapper.deleteById(mid);

        return metahumanResult > 0;
    }

    @Override
    public MetahumanDetailVo findMetahumanDetailVoById(long mid) {
        MetahumanDetailVo detailVo = new MetahumanDetailVo();

        // Fetching the Metahuman entity based on the mid
        Metahuman metahuman = metahumanMapper.selectById(mid);

        if (metahuman != null) {
            // Setting Metahuman attributes to the MetahumanDetailVo object
            detailVo.setDescription(metahuman.getDescription())
                    .setGender(metahuman.getGender())
                    .setName(metahuman.getName())
                    .setSubname(metahuman.getSubname())
                    .setCategory(metahuman.getCategory())
                    .setStatus(metahuman.getStatus())
                    .setCreateTime(metahuman.getCreateTime())
                    .setUpdateTime(metahuman.getUpdateTime())
                    .setAvatarid(metahuman.getAvatarid());

            // If the Metahuman has an associated Voice entity, fetch and set Voice attributes
            if (metahuman.getVid() != null) {
                Voice voice = voiceMapper.selectById(metahuman.getVid());

                if (voice != null) {
                    detailVo.setSpeaker(voice.getSpeaker())
                            .setPitch(voice.getPitch())
                            .setSpeed(voice.getSpeed())
                            .setEmotion(voice.getEmotion())
                            .setVoicesource(voice.getVoicesource());
                }
            }

            return detailVo;
        }

        // Returning null if no Metahuman was found for the given mid
        return null;
    }

}
