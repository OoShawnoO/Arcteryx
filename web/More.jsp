<%@ page import="swu.edu.hzd.SQLtool" %>
<%@ page import="swu.edu.hzd.Goods" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: 小胡
  Date: 2021/12/19
  Time: 11:25
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

</head>

<body>
<script>
    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return parseInt(pair[1]);}
        }
        return(false);
    }

    var Page = getQueryVariable("page");
    if(Page===false){Page = 1;}
    function turnP(obj) {
        if (obj === 1) {
            //下一页

            window.location.href = "echarts.jsp?page=" + (Page + 1);
        } else {
            //上一页
            if (Page === 1) {
                alert("已经是首页了~~");
            } else {
                window.location.href = "echarts.jsp?page=" + (Page - 1);
            }
        }
    }
</script>


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
            <h2>添加货物</h2>
            <p>应用Tomcat,Java,Servlet,MySQL实现</p>
        </div>
    </div>
</section>




<!-- ======= Footer ======= -->
<footer id="footer">


    <div class="container">
        <div class="copyright" style="height:100px">
            &copy; Copyright <strong><span>Huzhida</span></strong>. All Rights Reserved
        </div>

    </div>
</footer><!-- End Footer -->

<a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

<!-- Vendor JS Files -->
<script src="static/assets/js/echarts.js"></script>
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


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->

</body>

</html>