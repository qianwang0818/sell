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
                            <th>类目ID</th>
                            <th>类名</th>
                            <th>类目Type</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productCategoryPage.getContent() as category>
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>${category.createTime}</td>
                            <td>${category.updateTime}</td>
                            <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
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
                        <li><a href="/sell/seller/category/list?size=${size}&page=${currentPage-1}">Prev</a></li>
                        <li><a href="/sell/seller/category/list?size=${size}&page=1">1</a></li>
                    </#if>

                    <#--页码索引-->
                    <#if (productCategoryPage.totalPages>=2)>
                        <#list 2..<productCategoryPage.totalPages as index>
                            <#if (index<=10)>    <#--分页条只显示前10页-->
                                <#if currentPage==index>
                                    <li class="disabled"><a>${index}</a></li>
                                <#else>
                                    <li><a href="/sell/seller/category/list?size=${size}&page=${index}">${index}</a></li>
                                </#if>
                            </#if>
                        </#list>
                        <#if (productCategoryPage.totalPages>=10+2)>    <#--总页数有10+2页时显示点点点,因为下面还有个最后一页的页码可以作为10+1页-->
                            <li class="disabled"><a>...</a></li>
                        </#if>
                    </#if>

                    <#--最后一页和下一页-->
                    <#if currentPage==productCategoryPage.totalPages>
                        <#if (productCategoryPage.totalPages>=2)>
                            <li class="disabled"><a>${productCategoryPage.totalPages}</a></li>
                        </#if>
                        <li class="disabled"><a>Next</a></li>
                    <#else>
                        <li><a href="/sell/seller/category/list?size=${size}&page=${productCategoryPage.totalPages}">${productCategoryPage.totalPages}</a></li>
                        <li><a href="/sell/seller/category/list?size=${size}&page=${currentPage+1}">Next</a></li>
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
