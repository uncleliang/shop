package com.niit.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: shop
 * @description: 购物车类 实现购物车的 添加，删除，清空等操作
 * @author: hanliang
 * @create: 2020-02-11 15:08
 **/
public class Cart {

    // 总金额
    private Double total;

    // 购物车中全部的商品信息  key是商品id
    private Map<Integer,CartItem> cartMap = new HashMap<>();


    /**
     *  添加购物车
     * @param cartItem
     */
    public void add(CartItem cartItem){

        // 新添加的商品id
        Integer pid = cartItem.getProduct().getPid();

        // 判断该商品在购物车中是否存在
        if(!cartMap.containsKey(pid)){ //不存在
            cartMap.put(pid,cartItem);

        }else{// 存在

            // 更新quantity即可
            CartItem ci = cartMap.get(pid);

            ci.setQuantity(ci.getQuantity()+cartItem.getQuantity());

            cartMap.put(pid,ci);
        }

        // 重新计算总金额
        total = total + cartItem.getSubTotal();
    }

    /**
     *  删除购物车
     * @param pid
     */
    public void delete(Integer pid){

        total = total - cartMap.get(pid).getSubTotal();

        cartMap.remove(pid);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        cartMap.clear();
        total=0d;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Map<Integer, CartItem> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<Integer, CartItem> cartMap) {
        this.cartMap = cartMap;
    }
}
