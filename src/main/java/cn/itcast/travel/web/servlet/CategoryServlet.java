package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service =new CategoryServiceImpl();
    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
       // System.out.println("CategoryServlet的add方法。。。");
        List<Category> cs = service.findAll();

       /* //序列化返回  后期使用过多，抽取到BaseServlet
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("applicaton/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);*/
        writeValue(cs,response);
         /*  try {
               writeValue(cs,response);
           } catch (IOException e) {
               e.printStackTrace();
       }*/
    }
}
