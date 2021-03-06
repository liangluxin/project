package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Program: web
 * @Description:
 * @Author: xiaoxin
 * @Version: 1.0
 * @CreateDate: 2019-06-10 23:31
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        //1.从redis中查询
            //获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
            //使用sortedset排序
        Set<String> categorys = jedis.zrange("category", 0, -1);
        List<Category> cs =null;
        //2.判断集合是否为空
        if(categorys == null || categorys.size()==0){
            System.out.println("从数据库中查询...");
            //3.如果为空，需要从数据库中查询，在将数据存入redis
             cs = categoryDao.findAll();
            for (int i = 0; i <cs.size() ; i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }

        }else{
            System.out.println("从reids中查询...");
            //4.如果不为空，将set的数据存入list中
            cs = new ArrayList<Category>();
            for (String name : categorys) {
                Category category = new Category();
                category.setCname(name);
                cs.add(category);

            }
        }
        return cs;
    }
}
