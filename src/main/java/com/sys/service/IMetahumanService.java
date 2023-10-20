package com.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.entity.Metahuman;
import com.sys.vo.MetahumanDetailVo;
import com.sys.vo.MetahumanInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * metahuman表 服务类
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-16
 */
public interface IMetahumanService extends IService<Metahuman> {

//    /**
//     * 根据提供的条件查询符合条件的Metahuman实体列表。
//     *
//     * @param metahumanInfo 包含查询条件的对象，可能包括性别、名称、状态、性格和声音。
//     * @return 返回符合条件的Metahuman实体列表。
//     */
//    List<MetahumanDetailVo> findMetahumanBycondition(MetahumanInfo metahumanInfo);

    List<MetahumanDetailVo> findMetahumanByCondition(MetahumanInfo metahumanInfo);

//    /**
//     * 创建一个新的Metahuman实体，并将其保存到数据库中。
//     *
//     * @param metahumanInfo 包含新Metahuman信息的对象。
//     * @return 如果创建成功并保存到数据库中，则返回true；否则返回false。
//     */
//    boolean createMetahuman(MetahumanInfo metahumanInfo);

    boolean createMetahuman(MetahumanDetailVo metahumanDetail);

//    /**
//     * 更新具有指定ID的Metahuman实体的信息。
//     *
//     * @param mid           要更新的Metahuman实体的ID。
//     * @param metahumanInfo 包含更新信息的对象。
//     * @return 如果更新成功，则返回true；否则返回false（例如，如果没有找到指定ID的Metahuman实体）。
//     */
//    boolean updateMetahuman(Long mid, MetahumanInfo metahumanInfo);

    @Transactional
    boolean updateMetahuman(Long mid, MetahumanDetailVo metahumanDetail);

    /**
     * 删除具有指定ID的Metahuman实体。
     *
     * @param mid 要删除的Metahuman实体的ID。
     * @return 如果删除成功，则返回true；否则返回false（例如，如果没有找到指定ID的Metahuman实体）。
     */
    boolean deleteMetahuman(Long mid);

    MetahumanDetailVo findMetahumanDetailVoById(long mid);
}
