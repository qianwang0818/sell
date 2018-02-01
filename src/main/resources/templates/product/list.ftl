<!DOCTYPE html>
<html lang="en">
    <#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--侧边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">




        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>商品ID</th>
                            <th>商品名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>

                            <th>商品描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><img height="100px" src="${productInfo.productIcon}"></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>

                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.categoryType}</td>
                            <td>${productInfo.createTime}</td>
                            <td>${productInfo.updateTime}</td>
                            <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                            <td>
                                <#if productInfo.productStatusEnum.message=="在架">
                                    <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                <#else>
                                    <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>


                        </tbody>
                    </table>


                <#--==========分页开始==========-->
                    <ul class="pagination">
                    <#--上一页和第一页-->
                    <#if currentPage==1>
                        <li class="disabled"><a>Prev</a></li>
                        <li class="disabled"><a>1</a></li>
                    <#else>
                        <li><a href="/sell/seller/product/list?size=${size}&page=${currentPage-1}">Prev</a></li>
                        <li><a href="/sell/seller/product/list?size=${size}&page=1">1</a></li>
                    </#if>

                    <#--页码索引-->
                    <#if (productInfoPage.totalPages>=2)>
                        <#list 2..<productInfoPage.totalPages as index>
                            <#if (index<=10)>    <#--分页条只显示前10页-->
                                <#if currentPage==index>
                                    <li class="disabled"><a>${index}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/product/list?size=${size}&page=${index}">${index}</a></li>
                                </#if>
                            </#if>
                        </#list>
                        <#if (productInfoPage.totalPages>=10+2)>    <#--总页数有10+2页时显示点点点,因为下面还有个最后一页的页码可以作为10+1页-->
                            <li class="disabled"><a>...</a></li>
                        </#if>
                    </#if>

                    <#--最后一页和下一页-->
                    <#if currentPage ==productInfoPage.totalPages>
                        <#if (productInfoPage.totalPages>=2)>
                            <li class="disabled"><a>${productInfoPage.totalPages}</a></li>
                        </#if>
                        <li class="disabled"><a>Next</a></li>
                    <#else>
                        <li><a href="/sell/seller/product/list?size=${size}&page=${productInfoPage.totalPages}">${productInfoPage.totalPages}</a></li>
                        <li><a href="/sell/seller/product/list?size=${size}&page=${currentPage+1}">Next</a></li>
                    </#if>
                    </ul>
                <#--==========分页结束==========-->

                </div>
            </div>
        </div>




    </div>
</div>
</body>
</html>
