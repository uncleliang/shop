package com.niit.controller;

import com.alibaba.fastjson.JSON;
import com.niit.model.*;
import com.niit.service.ICategorySecondService;
import com.niit.service.IProductService;
import com.niit.service.impl.RedisService;
import com.niit.util.ConsUtil;
import com.niit.util.CookieUtil;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * @program: shop
 * @description: 商品控制器
 * @author: hanliang
 * @create: 2020-02-07 15:28
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategorySecondService categorySecondService;

    @Autowired
    RedisService redisService;

    /**
     * 查询热门商品
     * @return
     */
    @RequestMapping("/getNewProducts")
    @ResponseBody
    public ResultBean<List<Product>> getNewProducts(){

        ResultBean<List<Product>> resultBean = new ResultBean<>();

        resultBean.setData(productService.getNewProducts());

        return resultBean;
    }

    /**
     * 按照id查询商品信息
     * @return
     */
    @RequestMapping("/details/{cid}/{pid}")
    public String details(@PathVariable("pid")Integer pid,
                          @PathVariable("cid")Integer cid,
                          ModelMap map){
        // 1.查询 左边的二级菜单列表
        map.addAttribute("csList",categorySecondService.getByCategoryId(cid));
        // 2. 查询 商品信息
        map.addAttribute("product",productService.selectByPrimaryKey(pid));

        return "product";
    }

    /**
     * 添加购物车
     * 未登陆 存入cookie
     * 已登陆 存入redis
     * @param pid
     * @param quantity
     * @return
     */
    @RequestMapping("/addCart")
    @ResponseBody
    public ResultBean addCart(Integer pid, Integer quantity,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              HttpSession session){
        Cart cart =null;

        // 1.根据pid查询商品信息
        Product product = productService.selectByPrimaryKey(pid);

        // 2.组装 cartItem
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setProduct(product);

        // 判断用户是否登陆
        User user = (User)session.getAttribute("USER");
        if(user!=null){//已登陆
            // 从redis里面获取购物车
            cart = JSON.parseObject(redisService.get(ConsUtil.KEY_CART+":"+user.getUid()),Cart.class);

            if(cart==null){
                // 新建购物车
                cart= new Cart();
            }
            // 添加购物车
            cart.add(cartItem);

            redisService.set(ConsUtil.KEY_CART+":"+user.getUid(),JSON.toJSONString(cart),ConsUtil.TIME_WEEK);

        }else{ // 未登陆
            if (!addCookieCart(request, response, cartItem)) {
                return ResultBean.ERROR;
            }
        }


        // 5. 返回结果
        return ResultBean.SUCCESS;
    }

    /**
     *  将数据添加到Cookie里面
     *
     * @param request
     * @param response
     * @param cartItem
     * @return
     */
    private boolean addCookieCart(HttpServletRequest request, HttpServletResponse response, CartItem cartItem) {
        Cart cart;// 3.获取当前购物车的最新状态
        Cookie cookie = CookieUtil.getCookie(request,ConsUtil.COOKIE_CART_NAME);

        // 4.将cartItem添加到购物车
        if(cookie==null){ // 第一次添加，没有保存过cookie
            // 新建购物车
            cart= new Cart();
            // 添加购物车
            cart.add(cartItem);

        }else{
            String value = "";
            try {
                // 解码
                value = URLDecoder.decode(cookie.getValue(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            }
            // 转换成购物车对象
            cart = JSON.parseObject(value,Cart.class);
            if(cart==null){
                cart= new Cart();
            }
            // 添加购物车
            cart.add(cartItem);
        }
        // 写入cookie
        CookieUtil.setCookie(response,ConsUtil.COOKIE_CART_NAME,JSON.toJSONString(cart));
        return true;
    }

    @RequestMapping("/showCart")
    public String showCart(HttpServletRequest request,HttpSession session,ModelMap map){
        Cart cart=null;
        // 判断用户是否登陆
        User user = (User)session.getAttribute("USER");

        if(user!=null){//已登陆，从redis中取购物车
            String data = redisService.get(ConsUtil.KEY_CART+":"+user.getUid());
            if(data!=null){
                // 转换成购物车对象
                cart = JSON.parseObject(data,Cart.class);
                map.addAttribute("cart",cart);
            }
        }else{//未登陆，从cookie中取购物车
            Cookie cookie = CookieUtil.getCookie(request,ConsUtil.COOKIE_CART_NAME);
            if(cookie!=null){
                String value = "";
                try {
                    if(cookie.getValue()!=null){
                        value = URLDecoder.decode(cookie.getValue(),"utf-8");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if(value!=null){
                    cart = JSON.parseObject(value,Cart.class);
                    map.addAttribute("cart",cart);
                }
            }
        }


        return "cart";
    }
}
