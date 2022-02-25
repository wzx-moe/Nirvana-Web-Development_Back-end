package com.wzx.wzx_test1.controller;

import com.auth0.jwt.JWT;
import com.wzx.wzx_test1.annotation.UserLoginToken;
import com.wzx.wzx_test1.mapper.ProductMapper;
import com.wzx.wzx_test1.model.Product;
import com.wzx.wzx_test1.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductMapper productMapper;


    @RequestMapping("getOne")
    @ResponseBody
    public CommonResult<Product> getOneProduct(HttpServletRequest request, HttpServletResponse response, String id) {
        HttpSession session = request.getSession();
        return CommonResult.successReturn(productMapper.getOne(id));
    }

    @RequestMapping("getAll")
    @ResponseBody
    public CommonResult<List<Product>> getAllProduct(HttpServletRequest request, HttpServletResponse response ) {
        return CommonResult.successReturn(productMapper.getAll());
    }

    @UserLoginToken
    @RequestMapping("getOwn")
    @ResponseBody
    public CommonResult<List<Product>> getOwnProduct(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        return CommonResult.successReturn(productMapper.getOwn(JWT.decode(token).getAudience().get(0)));
    }

    @UserLoginToken
    @RequestMapping("add")
    @ResponseBody
    public CommonResult<String> addProduct(HttpServletRequest request, HttpServletResponse response, Product product) {
        product.setId(product.getName() + new Random(System.currentTimeMillis()).nextInt());
        String token = request.getHeader("token");
        product.setOwner(JWT.decode(token).getAudience().get(0));
        productMapper.insert(product);
        return CommonResult.successReturn("添加成功");
    }

    @UserLoginToken
    @RequestMapping("update")
    @ResponseBody
    public CommonResult<String> updateProduct(HttpServletRequest request, HttpServletResponse response, Product product) {
        String token = request.getHeader("token");
        product.setOwner(JWT.decode(token).getAudience().get(0));
        productMapper.insert(product);
        return CommonResult.successReturn("添加成功");
    }


}
