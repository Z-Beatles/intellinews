package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.service.KeywordService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.HotKeywordVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-10-27 13:29
 */
@RestController
@Api(tags = "关键字资源接口")
@RequestMapping("/v1/keywords")
public class KeywordController extends BaseController {

    private KeywordService keywordService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取热门搜索关键字", produces = "application/json")
    public Result<PageInfo<HotKeywordVO>> listHotKeywords(
            @ApiParam(name = "type", value = "搜索类型(hot)")
            @RequestParam(name = "type", defaultValue = "hot") String type,
            @ApiParam(name = "pageNum", value = "搜索页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "搜索条数")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return ResultUtil.success(keywordService.listHotKeywords(pageNum, pageSize));
    }

    @Autowired
    public void setKeywordService(KeywordService keywordService) {
        this.keywordService = keywordService;
    }
}
