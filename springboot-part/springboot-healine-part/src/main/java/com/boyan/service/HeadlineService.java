package com.boyan.service;

import com.boyan.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boyan.pojo.vo.HeadlineInsertVo;
import com.boyan.pojo.vo.PortalVo;
import com.boyan.utils.Result;

/**
* @author boyan
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-07-03 15:39:24
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 首页 headline 按关键字分页查询
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 多表查询
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     * 发布
     * @param token
     * @param headlineInsertVo
     * @return
     */
    Result publish(String token, HeadlineInsertVo headlineInsertVo);

    /**
     * 根据 hid 查询
     * @param hid
     * @return
     */
    Result findHeadlineByHid(Integer hid);

    /**
     * 更新修改
     * @param headline
     * @return
     */
    Result updateHeadline(Headline headline);

}
