<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>错误提示</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <script>
        var i = 3;
        setInterval("fun()", 1000);
        function fun() {
            if (i == 0) {
                window.location.href = "${url}";
                i=3;
                clearInterval(intervalid);
            }
            document.getElementById("secondId").innerHTML = i;
            i--;
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="alert alert-dismissable alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4>
                        错误!
                    </h4> <strong>${msg}</strong>&nbsp;&nbsp;&nbsp;
                    <span id="secondId">3</span>秒后自动跳转
                    <a href="${url}" class="alert-link">手动跳转</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
