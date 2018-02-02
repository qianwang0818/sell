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
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <input type="hidden" name="productId" value="${(productInfo.productId)!''}">
                        <div class="form-group">
                            <label for="productName">名称</label>
                            <input class="form-control" id="productName" name="productName" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productPrice">价格</label>
                            <input class="form-control" id="productPrice" name="productPrice" value="${(productInfo.productPrice)!''}" />
                        </div>
                        <div class="form-group">
                            <label for="productStock">库存</label>
                            <input class="form-control" id="productStock" name="productStock" value="${(productInfo.productStock)!''}" type="number"/>
                        </div>
                        <div class="form-group">
                            <label for="productDescription">描述</label>
                            <input class="form-control" id="productDescription" name="productDescription" value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productImg">图片</label>
                            <img id="productImg" height="200px" src="${(productInfo.productIcon)!''}"/>
                            <br/>
<script src="https://code.jquery.com/jquery-1.8.3.min.js"></script>
                            <label for="productIcon">图片url</label>
                            <input id="productIcon" name="productIcon" class="form-control" value="${(productInfo.productIcon)!''}"
                                   onblur="javascript:  $('#productImg').attr('src',this.value)">

                        </div>
                        <div class="form-group">
                            <label for="categoryType">类目</label>
                            <select id="categoryType" name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${(category.categoryType)!""}"
                                        <#if (productInfo.categoryType)?? && productInfo.categoryType==category.categoryType>selected</#if> >
                                        ${(category.categoryName)!""}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>




    </div>
</div>
</body>
</html>
