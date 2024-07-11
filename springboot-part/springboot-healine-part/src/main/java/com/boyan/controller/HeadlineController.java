package com.boyan.controller;

import com.boyan.pojo.Headline;
import com.boyan.pojo.vo.HeadlineInsertVo;
import com.boyan.service.HeadlineService;
import com.boyan.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    // todo. 头条发布实现

    /**
     * url地址：headline/publish
     * 请求方式：post
     * 请求头: token
     * 请求参数: JSON   // 两个都有
     * {
     *     "title":"尚硅谷宣布 ... ...",   // 文章标题
     *     "article":"... ...",          // 文章内容
     *     "type":"1"                    // 文章类别
     * }
     * 响应数据：
     * 未登录
     * {
     *     "code":"504",
     *     "message":"loginExpired",
     *     "data":{}
     * }
     * 成功
     * {
     *     "code":"200",
     *     "message":"success",
     *     "data":{}
     * }
     * 实现步骤:
     *  1. token 获取 userId [无需校验,拦截器会校验]
     *  2. 封装 headline 数据
     *  3. 插入数据即可
     */
    @PostMapping("publish")
    public Result publish(@RequestHeader String token, @RequestBody HeadlineInsertVo headlineInsertVo) {
        // 在执行这个方法前会调用拦截器通过 token 校验当前 user 的登录情况的，所以到这里了一定是已经通过校验了，是已登录的状态
        Result result = headlineService.publish(token, headlineInsertVo);
        return result;
    }


}
