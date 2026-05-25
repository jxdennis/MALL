package com.shop.service;

import com.shop.dao.RegionDao;
import com.shop.entity.Region;
import java.util.List;

public class RegionService {
    private final RegionDao regionDao = new RegionDao();

    public List<Region> findChildren(int parentId) throws Exception {
        return regionDao.findByParentId(parentId);
    }
}
