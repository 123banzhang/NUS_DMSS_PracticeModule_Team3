package com.sys.controller;

import com.sys.entity.Metahuman;
import com.sys.service.IMetahumanService;
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
        List<Metahuman> metahumans = metahumanService.findMetahumanBycondition(metahumanInfo);
        return RespBean.success(metahumans);
    }

    @GetMapping("/{mid}")
    public RespBean getMetahuman(@PathVariable Long mid) {
        Metahuman metahuman = metahumanService.getById(mid);
        if (metahuman != null) {
            return RespBean.success(metahuman);
        } else {
            return RespBean.error(RespBeanEnum.METAHUMAN_NOT_FOUND);
        }
    }

    @PutMapping("/{mid}")
    public RespBean updateMetahuman(@PathVariable Long mid, @RequestBody MetahumanInfo metahumanInfo) {
        boolean updated = metahumanService.updateMetahuman(mid, metahumanInfo);
        if (updated) {
            return RespBean.success();
        } else {
            return RespBean.error(RespBeanEnum.METAHUMAN_UPDATE_FAILED);
        }
    }

}
