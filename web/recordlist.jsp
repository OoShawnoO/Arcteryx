<%@ page import="swu.edu.hzd.SQLtool" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="swu.edu.hzd.Goods" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>

<%--
  Created by IntelliJ IDEA.
  User: 小胡
  Date: 2021/11/19
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>记账系统V1.0</title>
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

  <script type="text/javascript">


  </script>
</head>

<body>



<!-- ======= Header ======= -->
<header id="header">
  <div class="container">

    <div class="logo float-left">
      <h1 class="text-light"><a href=""><span>Hu</span></a></h1>
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
      <h2>货物统计表</h2>
      <p>应用Tomcat,Java,Servlet,MySQL实现</p>
    </div>

    <!-- ======= Counts Section ======= -->
    <section class="counts section-bg">
      <div class="container">
        <table class="table table-striped" style="text-align:center;">

          <tr>
            <td>序号</td>
            <td>货物名称</td>
            <td>货物价格（/斤）</td>
            <td>货物成本（/斤）</td>
            <td>修改</td>

          </tr>
          <%
            SQLtool sqltool = new SQLtool();
            ArrayList<Goods> arrayList = new ArrayList<>();
            try {
              arrayList = sqltool.Select();
            } catch (SQLException e) {
              e.printStackTrace();
            }

            for(Goods goods:arrayList){

          %>
          <tr>
            <td><%out.print(goods.getId());%></td>
            <td><%out.print(goods.getName());%></td>
            <td><%out.print(goods.getPrice());%></td>
            <td><%out.print(goods.getCost());%></td>
            <td>
              <form name="delete" action="/RecordSystem/Delete">
                <input type="hidden" value="<%out.print(goods.getId());%>" name="delete" >
                <button style="border-radius: 10px;border:none;" type="submit">删除</button>
              </form>
            </td>
          </tr>
          <%
            }
          %>



        </table>
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
