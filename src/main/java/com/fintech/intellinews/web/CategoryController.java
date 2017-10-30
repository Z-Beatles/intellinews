package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.entity.CategoryEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-27 13:50
 */
@RestController
@Api(tags = "分类目录资源接口")
@RequestMapping("/v1/categories")
public class CategoryController extends BaseController {

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取所有分类目录", produces = "application/json")
    public Result<List<CategoryEntity>> listCategories() {
        return null;
    }

    @GetMapping("/{userId}")
    @ResponseBody
    @ApiOperation(value = "获取指定用户的目录列表", produces = "application/json")
    public Result<CategoryEntity> listUserCategories(@PathVariable(value = "userId") Long id) {


        return null;
    }
}
