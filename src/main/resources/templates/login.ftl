<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
</head>
<body>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <h3 class="text-center">
                    登陆页面
                </h3>
                <form role="form" method="get" action="/sell/wechat/qrUserInfo">
                    <input type="hidden" name="state" value="${returnUrl}">
                    <div class="form-group">
                        <label for="openid">openid</label>
                        <input type="text" class="form-control" id="openid" name="code" />
                    </div>
                    <hr/>
                    <div class="form-group">
                        <label for="username">username</label>
                        <input class="form-control" id="username" name="username" />
                    </div>
                    <div class="form-group">
                        <label for="password">password</label>
                        <input type="password" class="form-control" id="password" name="password" />
                    </div>
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>