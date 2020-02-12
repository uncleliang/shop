package com.niit.model;

import java.io.Serializable;

/**
 * @program: shop
 * @description: 购物车单行数据
 * @author: hanliang
 * @create: 2020-02-11 15:11
 **/
public class CartItem implements Serializable {

    // 商品信息
    private Product product;

    // 商品数量
    private Integer quantity=0;

    // 小记金额
    private Double subTotal=0d;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return product.getShopPrice()*quantity;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
