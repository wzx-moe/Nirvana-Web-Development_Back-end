package com.wzx.wzx_test1.controller;

import com.auth0.jwt.JWT;
import com.wzx.wzx_test1.annotation.UserLoginToken;
import com.wzx.wzx_test1.mapper.ReviewMapper;
import com.wzx.wzx_test1.model.Review;
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
@RequestMapping("/review")
public class ReviewController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ReviewMapper reviewMapper;

    @RequestMapping("getOne")
    @ResponseBody
    public CommonResult<Review> getOneProduct(HttpServletRequest request, HttpServletResponse response, String id) {
        HttpSession session = request.getSession();
        return CommonResult.successReturn(reviewMapper.getOne(id));
    }

    @RequestMapping("getAll")
    @ResponseBody
    public CommonResult<List<Review>> getAllProduct(HttpServletRequest request, HttpServletResponse response ) {
        return CommonResult.successReturn(reviewMapper.getAll());
    }

    @UserLoginToken
    @RequestMapping("userGetOwn")
    @ResponseBody
    public CommonResult<List<Review>> getOwnReview(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        return CommonResult.successReturn(reviewMapper.userGetOwn(JWT.decode(token).getAudience().get(0)));
    }

    @UserLoginToken
    @RequestMapping("productGetOwn")
    @ResponseBody
    public CommonResult<List<Review>> getProductReview(HttpServletRequest request, HttpServletResponse response, String id) {
        String token = request.getHeader("token");
        return CommonResult.successReturn(reviewMapper.productGetOwn(id));
    }

    @UserLoginToken
    @RequestMapping("add")
    @ResponseBody
    public CommonResult<String> addProduct(HttpServletRequest request, HttpServletResponse response, Review review) {
        review.setId(review.getProductID() + new Random(System.currentTimeMillis()).nextInt());
        String token = request.getHeader("token");
        review.setUserID(JWT.decode(token).getAudience().get(0));
        reviewMapper.insert(review);
        return CommonResult.successReturn("添加成功");
    }

    @UserLoginToken
    @RequestMapping("update")
    @ResponseBody
    public CommonResult<String> updateProduct(HttpServletRequest request, HttpServletResponse response, Review review) {
        String token = request.getHeader("token");
        review.setUserID(JWT.decode(token).getAudience().get(0));
        reviewMapper.insert(review);
        return CommonResult.successReturn("添加成功");
    }
}
