package com.shop.dao;
import com.shop.entity.Region;
import com.shop.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;

public class RegionDao {
    private QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
    public List<Region> findByParentId(int parentId) throws Exception {
        String sql = "SELECT * FROM region WHERE parent_id = ?";
        return qr.query(sql, new BeanListHandler<>(Region.class), parentId);
    }
}