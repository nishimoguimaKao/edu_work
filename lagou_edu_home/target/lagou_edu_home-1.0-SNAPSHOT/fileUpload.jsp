<%--
  Created by IntelliJ IDEA.
  User: 13664
  Date: 2021/3/2
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--
    文件上传三要素
        - 1.表单提交方式: **post** (get方式提交有大小限制,post没有)
        - 2.表单的enctype属性:必须设置为   **multipart/form-data.**
          - enctype就是encodetype就是编码类型的意思.
          - multipart/form-data是多部件文件上传 , 指表单数据有多部分构成，既有文本数据，又有文件等二进制数据的意思。
        - 3.表单必须有文件上传项: **file **,必须要有name属性和值
--%>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload">

    <input type="file" name="upload">
    <br>
    <input type="text" name="name">
    <input type="text" name="password">
    <input type="submit" value="文件上传">
</form>
<%--'/'就表示'webapps/'也就是在webapps目录下--%>
<img src="/upload/49aac17906ed481dadea6d87ae491b3a_0.png" alt="">

</body>
</html>
