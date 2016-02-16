<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/2/14
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>picture</title>
  </head>
  <body>
    picture
    <p>Upload test</p>
    <form action="picture/save" method="post" enctype="multipart/form-data">
      <input type="file" name="file" value="点择文件">
      <input type="text" name="userId" value="1">
      <input type="text" name="name" value="photo">
      <input type="submit" value="提交">
    </form>
      <br/>
    <p>Login test</p>
    <form action="user/login" method="post">
      <input type="text" name="userName" value="daibing">
      <input type="text" name="passWord" value="daibing">
      <input type="submit" value="提交">
    </form>
      <br/>
    <p>Register test</p>
    <form action="user/register" method="post">
      <input type="text" name="userName" value="daibing3">
      <input type="text" name="passWord" value="daibing3">
      <input type="submit" value="提交">
    </form>
  </body>
</html>
