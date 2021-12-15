<%@ page import="swu.edu.hzd.SQLtool" %>
<%@ page import="swu.edu.hzd.Goods" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: 小胡
  Date: 2021/11/20
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>RecordSytemV1.0.3</title>
  <meta content="" name="descriptison">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="static/assets/img/favicon.png" rel="icon">
  <link href="static/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,600,600i,700,700i,900" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="static/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="static/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="static/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="static/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="static/assets/vendor/venobox/venobox.css" rel="stylesheet">
  <link href="static/assets/vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="static/assets/css/style.css" rel="stylesheet">

  <style type="text/css">
    .button{
      padding: 4px 8px 4px 8px;
      margin: 0 195px;
      border: 1px solid #fa8717;
      background: #fff;
      text-align: center;
      display: inline;
      font-size: 14px;
      color: #464646;
      border-radius: 4px;
    }

    .button:hover{
      background-color:#fa8717;
    }

    .page_list{
      margin:30px 5px 20px 5px;
    }

    .page_list_state{
      padding: 4px 8px 4px 8px;
      margin: 0 5px;
      border: 1px solid #fa8717;
      background: #fff;
      text-align: center;
      display: inline;
      font-size: 14px;
      color: #464646;
      border-radius: 4px;
    }

    .page_list_state:hover{
      background-color: #fa8717;
      color:white;
    }


  </style>

  <script type="text/javascript">
    function getQueryVariable(variable)
    {
      var query = window.location.search.substring(1);
      var vars = query.split("&");
      for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
      }
      return(false);
    }

    if(getQueryVariable("wrong")=="-1"){
      alert("输入数据类型错误！")
    }

    var Page = getQueryVariable("page");
    if(Page===false){Page = 1;}
    function turnP(obj) {
      if (obj === 1) {
        //下一页

        window.location.href = "Revise.jsp?page=" + (parseInt(Page) + 1);
      } else {
        //上一页
        if (Page === 1) {
          alert("已经是首页了~~");
        } else {
          window.location.href = "Revise.jsp?page=" + (Page - 1);
        }
      }
    }



  </script>
</head>

<body>



<!-- ======= Header ======= -->
<header id="header">
  <div class="container">

    <div class="logo float-left">
      <h1 class="text-light"><a href="index.jsp"><span>RS</span></a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="temp.html"><img src="static/assets/img/logo.png" alt="" class="img-fluid"></a>-->
    </div>

    <nav class="nav-menu float-right d-none d-lg-block">
      <ul>
        <li class="active"><a href="index.jsp"> 首页 <i class="la la-angle-down"></i></a></li>
        <li><a href="recordlist.jsp"> 统计 </a></li>
        <li><a href="echarts.jsp"> 图表 </a></li>
        <li><a href="Revise.jsp"> 数据修改 </a></li>
        <li><a href="Add.html">添加货物</a></li>


      </ul></nav><!-- .nav-menu -->

  </div>
</header><!-- End Header -->

<!-- ======= Our Team Section ======= -->
<section id="team" class="team">
  <div class="container">

    <div class="section-title">
      <h2>数据修改</h2>
      <p>应用Tomcat,Java,Servlet,MySQL实现</p>
    </div>

    <!-- ======= Counts Section ======= -->
    <section style="box-shadow: 0px 0 16px rgb(0 0 0 / 10%);" class="counts section-bg">
      <div class="container">
        <table class="table table-striped" style="text-align:center;">

          <tr>
            <td>序号</td>
            <td>货物名称</td>
            <td>货物价格（/斤）</td>
            <td>货物成本（/斤）</td>
            <td>提交</td>
          </tr>

          <%
            String search = request.getParameter("search");
            if(search==null){search="";}

            int Page;
            try {
              Page = Integer.valueOf(request.getParameter("page"));
            } catch (Exception e) {
              Page = 1;
            }
            SQLtool sqltool = new SQLtool();
            ArrayList<Goods> arrayList = new ArrayList<>();
            arrayList = sqltool.PrepareSelect(Page,search);

            int count = 0;
            try {
              count = sqltool.Select(search).size();
            } catch (SQLException e) {
              e.printStackTrace();
            }

            for(Goods goods:arrayList){

          %>
          <tr>
            <form name="revise" action="/RecordSystem/Update">
              <td><%out.print(goods.getId());%></td>
              <input type="hidden" name="id" value="<%out.print(goods.getId());%>">
              <td><input type="text" name="name" value="<%out.print(goods.getName());%>" style="text-align: center;border:0;background-color:#d9e8f4"></td>
              <td><input type="number" step="0.01" name="price" value="<%out.print(goods.getPrice());%>" style="text-align:center;"></td>
              <td><input type="number" step="0.01" name="cost" value="<%out.print(goods.getCost());%>" style="text-align:center;"></td>
              <td>
                <input type="submit" value="提交">
              </td>
            </form>
          </tr>

          <%
            }
          %>



        </table>
        <center><form action="Revise.jsp">
          <input type="text" name="search" style="border:solid 1px;border-color:#fa8717">
          <button class="button" style="margin:0;font-family: 'Microsoft JhengHei';font-size: 1em;;" type="submit" value="搜索">搜索</button></form></center>
        <center>
<%--        <center><button class="button" onclick="turnP(2)">上一页</button><button class="button" onclick="turnP(1)">下一页</button></center>--%>
        <div class="page_list">
          <span class="page_list_state" title="Total record">总数&nbsp;&nbsp;<%=count%></span>&nbsp;&nbsp;&nbsp;
          <a class="page_list_state" href="./Revise.jsp">首页</a>&nbsp;
          <button class="page_list_state" onclick="turnP(2)">上一页</button>&nbsp;
          <b class="page_list_state"><%=Page%></b>&nbsp;
          <%for(int number=1;Page+number<(count/7+2)&&number<9;number++)
          {
          %>
          <a class="page_list_state" href="./Revise.jsp?page=<%=(Page+number)%>"><%=(Page+number)%></a>&nbsp;
          <%}%>
          <button class="page_list_state" onclick="turnP(1)">下一页</button>&nbsp;
          <a class="page_list_state" href="./Revise.jsp?page=<%=Integer.valueOf(count/7+1)%>">尾页</a>
        </div>
      </div>
    </section><!-- End Counts Section -->
  </div></section><!-- End Our Team Section -->

<main id="main">



  <!-- ======= Footer ======= -->
  <footer id="footer">


    <div class="container">
      <div class="copyright" style="height:1000px">
        &copy; Copyright <strong><span>Huzhida</span></strong>. All Rights Reserved
      </div>

    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

  <!-- Vendor JS Files -->
  <script src="static/assets/vendor/jquery/jquery.min.js"></script>
  <script src="static/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="static/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
  <script src="static/assets/vendor/php-email-form/validate.js"></script>
  <script src="static/assets/vendor/jquery-sticky/jquery.sticky.js"></script>
  <script src="static/assets/vendor/venobox/venobox.min.js"></script>
  <script src="static/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
  <script src="static/assets/vendor/counterup/counterup.min.js"></script>
  <script src="static/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="static/assets/vendor/aos/aos.js"></script>

  <!-- Template Main JS File -->
  <script src="static/assets/js/main.js"></script>

</main></body>

</html>