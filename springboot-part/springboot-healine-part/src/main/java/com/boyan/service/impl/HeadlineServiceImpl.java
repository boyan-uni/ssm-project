package com.boyan.service.impl;

import com.boyan.pojo.dto.HeadlineDTO;
import com.boyan.pojo.dto.HeadlineDetailDTO;
import com.boyan.pojo.vo.PortalVo;
import com.boyan.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boyan.pojo.Headline;
import com.boyan.service.HeadlineService;
import com.boyan.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author boyan
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-07-03 15:39:24
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline> implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    /**
     * 首页数据查询
     *
     *   1. 进行分页数据查询
     *   2. 分页数据，拼接到result即可
     *
     *   注意1： 查询需要自定义语句 自定义mapper的方法，携带分页
     *   注意2： 返回的结果List<Map>
     * @param portalVo
     * @return
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        Page<HeadlineDTO> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());

        IPage<HeadlineDTO> resultPage = headlineMapper.selectMyPage(page, portalVo);  // 自定义持久层查询方法

        // 封装分页信息到 Result 中
        Map<String, Object> data = new HashMap<>();
        data.put("pageData", resultPage.getRecords());    // 从 page 对象中取结果
        data.put("pageNum", resultPage.getCurrent());
        data.put("pageSize", resultPage.getSize());
        data.put("totalPage", resultPage.getPages());
        data.put("totalSize", resultPage.getTotal());

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", data);

        return Result.ok(pageInfoMap);
    }

    /**
     * 查询头条详情
     * - 用户点击"查看全文"时,向服务端发送新闻id
     * - 后端根据新闻id查询完整新闻文章信息并返回
     * - 后端要同时让新闻的浏览量+1
     * @param hid
     * @return
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        // 1.对比数据库表和接口所需信息发现是多表查询：type->typeName、计算pastHours、publisher(uid)->nick_name【自定义方法】
        HeadlineDetailDTO headlineDetailDTO = headlineMapper.selectHeadlineDetail(hid);

        // 2.调用 BaseMapper<Headline>.updateById(headline) 更新指定 headline（阅读量、version[乐观锁的字段]）
        // 封装一个新的 headline 对象用来 updateById, 只需要新配置需要更新的属性即可，其余的不用管。
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews(headlineDetailDTO.getPageViews() + 1); // 阅读量+1
        headline.setVersion(headlineDetailDTO.getVersion());         // 设置版本
        headlineMapper.updateById(headline);

        // 返回1查到的信息，封装到 Result 中返回上层
        return Result.ok(headlineDetailDTO);
    }


}




