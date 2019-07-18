package com.bigdata.railway.dao;

import com.bigdata.railway.entity.GoodsAmount;
import com.bigdata.railway.entity.GoodsAmountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsAmountMapper {
    /**
     * 批量插入货运品类_货运量数据
     * @param goodsList
     * @return
     */
    Integer batchInsert(@Param("goodsList") List<GoodsAmount> goodsList);

    long countByExample(GoodsAmountExample example);

    int deleteByExample(GoodsAmountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAmount record);

    int insertSelective(GoodsAmount record);

    List<GoodsAmount> selectByExample(GoodsAmountExample example);

    GoodsAmount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAmount record, @Param("example") GoodsAmountExample example);

    int updateByExample(@Param("record") GoodsAmount record, @Param("example") GoodsAmountExample example);

    int updateByPrimaryKeySelective(GoodsAmount record);

    int updateByPrimaryKey(GoodsAmount record);
}