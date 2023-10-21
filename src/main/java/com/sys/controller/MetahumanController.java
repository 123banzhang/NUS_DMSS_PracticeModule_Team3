package com.sys.controller;

import com.sys.service.IMetahumanService;
import com.sys.vo.MetahumanDetailVo;
import com.sys.vo.MetahumanInfo;
import com.sys.vo.RespBean;
import com.sys.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/metahuman")
public class MetahumanController {

    @Autowired
    private IMetahumanService metahumanService;

    @PostMapping("/list")
    public RespBean listMetahumans(@RequestBody MetahumanInfo metahumanInfo) {
        List<MetahumanDetailVo> metahumans = metahumanService.findMetahumanByCondition(metahumanInfo);
        return RespBean.success(metahumans);
    }

    @GetMapping("/{mid}")
    public RespBean getMetahumanDetail(@PathVariable Long mid) {
        MetahumanDetailVo vo = metahumanService.findMetahumanDetailVoById(mid);
        if (vo != null) {
            return RespBean.success(vo);
        } else {
            return RespBean.error(RespBeanEnum.METAHUMAN_NOT_FOUND);
        }
    }

    @PutMapping("/{mid}")
    public RespBean updateMetahumanDetailVo(@PathVariable Long mid, @RequestBody MetahumanDetailVo vo) {
        boolean updated = metahumanService.updateMetahuman(mid, vo);
        if (updated) {
            return RespBean.success();
        } else {
            return RespBean.error(RespBeanEnum.METAHUMAN_UPDATE_FAILED);
        }
    }

    /**
     * @param metahumanDetailVo
     * @return
     */
    @PostMapping("/create")
    public RespBean createMetahumanDetailVo(@RequestBody MetahumanDetailVo metahumanDetailVo) {
        boolean created = metahumanService.createMetahuman(metahumanDetailVo);
        if (created) {
            return RespBean.success("Metahuman created successfully.");
        } else {
            return RespBean.error(RespBeanEnum.METAHUMAN_CREATE_FAIL); // Using the enum here
        }
    }

    @DeleteMapping("/{mid}")
    public RespBean deleteMetahumanDetailVo(@PathVariable Long mid) {
        boolean deleted = metahumanService.deleteMetahuman(mid);
        if (deleted) {
            return RespBean.success("Metahuman deleted successfully.");
        } else {
            return RespBean.error(RespBeanEnum.METAHUMAN_DELETE_FAIL); // Using the enum here
        }
    }


}
