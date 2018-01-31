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
            <#--订单概要-->
            <div class="col-md-4 column">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>订单ID</th>
                        <th>订单总金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.orderAmount}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <#--订单详情表数据-->
            <div class="col-md-12 column">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>商品ID</th>
                        <th>商品名称</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>总金额</th>
                    </tr>
                    </thead>

                    <tbody>
                    <#list orderDTO.orderDetailList as orderDetail>
                        <tr>
                            <td>${orderDetail.productId}</td>
                            <td>${orderDetail.productName}</td>
                            <td>${orderDetail.productPrice}</td>
                            <td>${orderDetail.productQuantity}</td>
                            <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

            <#--操作按钮-->
            <div class="col-md-12 column">
                <#if (orderDTO.orderStatusEnum.message == "新订单")>
                    <a type="button" class="btn btn-default btn-primary" href="/sell/seller/order/finnish?orderId=${orderDTO.orderId}">完结订单</a>
                    <a type="button" class="btn btn-warning btn-danger" href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a>
                </#if>
            </div>

        </div>
    </div>

</body>
</html>