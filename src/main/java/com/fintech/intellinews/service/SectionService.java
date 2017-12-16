package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.DetailsSectionVO;
import com.fintech.intellinews.vo.ListSectionVO;
import com.fintech.intellinews.vo.SearchSectionVO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author wanghao
 * create 2017-11-08 15:06
 **/
public interface SectionService {

    /**
     * 获取所有条目列表
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 条目列表
     */
    PageInfo<ListSectionVO> listSections(Integer pageNum, Integer pageSize);

    /**
     * 根据关键字查询搜索条目列表
     *
     * @param keyword  关键字
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 搜索条目列表
     */
    PageInfo<SearchSectionVO> listByKeyword(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据拼音首字母查询导航条目列表（导航查询）
     *
     * @param startWith 拼音首字母
     * @param pageNum   页数
     * @param pageSize  页大小
     * @return 导航条目列表
     */
    PageInfo<ListSectionVO> listByStartWith(String startWith, Integer pageNum, Integer pageSize);

    /**
     * 根据id查询条目详情
     *
     * @param sectionId 条目id
     * @return 条目详情
     */
    DetailsSectionVO getSectionById(Long sectionId);

    /**
     * 根据条目id查询图谱信息
     *
     * @param sectionId 条目id
     * @return 图谱信息
     */
    Map<String, Object> listBySectionIdAndAtlasType(Long sectionId, String atlasType);
}
