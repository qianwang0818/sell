<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>订单ID</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>

                        <th>订单状态</th>
                        <th>支付方式</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2">操作</th>
                    </tr>
                </thead>
                <tbody>
                 <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>

                        <td>${orderDTO.orderStatusEnum.message}</td>
                        <td></td>
                        <td>${orderDTO.getPayStatusEnum().message}</td>
                        <td>${orderDTO.createTime}</td>
                        <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                        <td>
                            <#if (orderDTO.orderStatusEnum.message == "新订单")>
                                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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
                    <li><a href="/sell/seller/order/list?size=${size}&page=${currentPage-1}">Prev</a></li>
                    <li><a href="/sell/seller/order/list?size=${size}&page=1">1</a></li>
                </#if>

                <#--页码索引-->
                <#if (orderDTOPage.totalPages>=2)>
                    <#list 2..<orderDTOPage.totalPages as index>
                        <#if (index<=10)>    <#--分页条只显示前10页-->
                            <#if currentPage==index>
                                <li class="disabled"><a>${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?size=${size}&page=${index}">${index}</a></li>
                            </#if>
                        </#if>
                    </#list>
                    <#if (orderDTOPage.totalPages>=10+2)>    <#--总页数有10+2页时显示点点点,因为下面还有个最后一页的页码可以作为10+1页-->
                        <li class="disabled"><a>...</a></li>
                    </#if>
                </#if>

                <#--最后一页和下一页-->
                <#if currentPage==orderDTOPage.totalPages>
                    <#if (orderDTOPage.totalPages>=2)>
                        <li class="disabled"><a>${orderDTOPage.totalPages}</a></li>
                    </#if>
                    <li class="disabled"><a>Next</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?size=${size}&page=${orderDTOPage.totalPages}">${orderDTOPage.totalPages}</a></li>
                    <li><a href="/sell/seller/order/list?size=${size}&page=${currentPage+1}">Next</a></li>
                </#if>
            </ul>
        <#--==========分页结束==========-->

            <a id="modal-474464" href="#modal-container-474464" role="button" class="btn" data-toggle="modal">触发遮罩窗体</a>

            <div class="modal fade" id="modal-container-474464" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                标题
                            </h4>
                        </div>
                        <div class="modal-body">
                            内容...
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary">保存</button>
                        </div>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>



</body>
</html>
