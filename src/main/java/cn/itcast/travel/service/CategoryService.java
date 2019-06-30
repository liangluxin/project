package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Program: web
 * @Description:
 * @Author: xiaoxin
 * @Version: 1.0
 * @CreateDate: 2019-06-10 23:30
 */
public interface CategoryService {

    public List<Category> findAll();
}
