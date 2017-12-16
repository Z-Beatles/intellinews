package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.ArticleVO;
import com.fintech.intellinews.vo.CommentVO;
import com.fintech.intellinews.vo.DetailsArticleVO;
import com.fintech.intellinews.vo.SearchArticleVO;
import com.github.pagehelper.PageInfo;

/**
 * @author waynechu
 * Created 2017-10-30 17:29
 */
public interface ArticleService {

    /**
     * 根据频道id获取文章
     *
     * @param channelId 频道id
     * @param pageNum   页数
     * @param pageSize  页大小
     * @return 分页后的文章
     */
    PageInfo<ArticleVO> listArticlesByChannelId(Long channelId, int pageNum, int pageSize);

    /**
     * 初始化文章统计信息
     *
     * @param articleId 文章id
     */
    void initArticleCount(Long articleId);

    /**
     * 通过关键字获取文章列表
     *
     * @param keyword  关键字
     * @param pageNum  分页页数
     * @param pageSize 分页条数
     * @return 分页文章列表
     */
    PageInfo<SearchArticleVO> listArticlesByKeyword(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据id获取文章详情
     *
     * @param id 文章id
     * @return 文章详情实体
     */
    DetailsArticleVO getDetailsArticleById(Long id);

    /**
     * 获取指定文章的评论信息
     *
     * @param id       文章id
     * @param pageNum  分页索引
     * @param pageSize 分页椰页容
     * @return 评论的分页信息
     */
    PageInfo<CommentVO> listArticleComments(Long id, Integer pageNum, Integer pageSize);

}
