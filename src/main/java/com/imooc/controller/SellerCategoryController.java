package com.imooc.controller;

import com.imooc.domain.ProductCategory;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.CategoryForm;
import com.imooc.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    /**类目列表*/
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page", defaultValue="1") Integer page ,
                             @RequestParam(value="size", defaultValue="10") Integer size,
                             Map<String,Object> map){
        Page<ProductCategory> productCategoryPage = categoryService.findAll(new PageRequest(page - 1, size));
        map.put("productCategoryPage",productCategoryPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("category/list",map);
    }

    /**类目数据回显,相当于editUI*/
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value="categoryId", required=false) Integer categoryId,
                                Map<String,Object> map){
        if(categoryId!=null){
            ProductCategory category = categoryService.findOne(categoryId);
            map.put("category",category);
        }
        return new ModelAndView("category/index",map);
    }

    /**保存or修改类目*/
    @PostMapping("/save")
    public ModelAndView save (@Valid CategoryForm categoryForm,
                                BindingResult bindingResult,
                                Map<String,Object> map) {
        if (bindingResult.hasErrors()) {
            log.error("【类目保存/修改】表单验证有误,错误信息:{}", bindingResult.getFieldError().getDefaultMessage());
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        try {
            Integer categoryId = categoryForm.getCategoryId();
            ProductCategory productCategory = null;
            if (categoryId == null) {      //新增类目
                productCategory = new ProductCategory();
            } else {
                productCategory = categoryService.findOne(categoryId);
                if (productCategory == null) {
                    log.error("【类目保存/修改】需要修改的类目ID有误,categoryId:{}", categoryId);
                    throw new SellException(ResultEnum.CATEGORY_NOT_EXIST);
                }
                productCategory.setUpdateTime(null);
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        } catch (BeansException e) {
            log.error("【类目保存/修改】发生异常,错误信息:{}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg","保存/修改成功");
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }

}
