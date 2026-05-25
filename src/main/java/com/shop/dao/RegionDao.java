package com.shop.dao;

import com.shop.entity.Region;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;

public class RegionDao {
    private final QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());

    public List<Region> findByParentId(int parentId) throws Exception {
        String sql = "SELECT id, name, parent_id AS parentId FROM region WHERE parent_id = ? ORDER BY id";
        return runner.query(sql, new BeanListHandler<>(Region.class), parentId);
    }
}
