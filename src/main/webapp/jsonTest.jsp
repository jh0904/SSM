<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>json交互测试</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>


    <script type="text/javascript">
        //请求json，输出是json
        function requestJson() {
            alert("requestJson");
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath }/requestJson',
                contentType: 'application/json;charset=utf-8',
                //数据格式是json串,商品信息
                data: '{"name":"手机","price":999}',
                success: function (data) {//返回json结果
                    alert(data);
                }
            });


        }

        //请求key/value，输出是json
        function responseJson() {
            alert("start");
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath }/responseJson',
                //请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
                //contentType:'application/json;charset=utf-8',
                //数据格式是json串，商品信息
                data: 'name=电脑&price=5000',
                success: function (data) {//返回json结果
                    alert("success");
                }

            });
            alert("over");
        }
    </script>
</head>
<body>
<input type="button" onclick="requestJson()" value="请求json，输出是json"/>
<input type="button" onclick="responseJson()" value="请求key/value，输出是json"/>


</body>
</html>