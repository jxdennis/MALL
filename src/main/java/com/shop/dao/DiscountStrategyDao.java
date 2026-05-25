package com.shop.dao;

import com.shop.entity.DiscountStrategyEntity;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;

public class DiscountStrategyDao {
    private final QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());

    public List<DiscountStrategyEntity> findAll() throws Exception {
        String sql = "SELECT id, strategy_name AS strategyName, strategy_class AS strategyClass, CAST(discount_value AS DECIMAL(10,2)) AS discountValue FROM discount_strategy ORDER BY id";
        return runner.query(sql, new BeanListHandler<>(DiscountStrategyEntity.class));
    }
}
