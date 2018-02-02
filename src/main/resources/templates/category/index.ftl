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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <input type="hidden" name="categoryId" value="${(category.categoryId)!''}">
                        <div class="form-group">
                            <label for="categoryName">类名</label>
                            <input class="form-control" id="categoryName" name="categoryName" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">类目Type</label>
                            <input class="form-control" id="categoryType" name="categoryType" value="${(category.categoryType)!''}" type="number" />
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
