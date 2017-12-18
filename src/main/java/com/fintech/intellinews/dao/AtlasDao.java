package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.AtlasEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-11-22 12:32
 */
public interface AtlasDao {

    /**
     * 根据条目id以及图谱类型获取图谱信息
     *
     * @param sectionId    条目id
     * @param relationType 图谱类型
     * @param limit        查询数量
     * @return 图谱信息
     */
    List<AtlasEntity> listBySectionIdAndType(@Param("sectionId") Long sectionId, @Param("relationType") String
            relationType, @Param("limit") int limit);
}
