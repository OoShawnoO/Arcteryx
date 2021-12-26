<%@ page import="swu.edu.hzd.SQLtool" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.xml.transform.Result" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.mysql.cj.xdevapi.StreamingSqlResultBuilder" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="swu.edu.hzd.Goods" %><%--
  Created by IntelliJ IDEA.
  User: 小胡
  Date: 2021/11/20
  Time: 14:15
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
        .sellbox
        {
            box-shadow: 0 0 16px rgb(0 0 0 / 10%);
            background: #fff;
            display:inline;
        }
    </style>

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js"></script>

    <script>
        function lazy(){
            var imgs = document.querySelectorAll('img');
            function getTop(e){
                return e.offsetTop;
            }
            function lazyload(imgs){
                var h = window.innerHeight;
                var s = document.documentElement.scrollTop || document.body.scrollTop;
                for(var i=0;i<imgs.length;i++){
                    if ((h+s)>getTop(imgs[i])) {
                        (function(i){
                            setTimeout(function(){
                                var temp = new Image();
                                temp.src = imgs[i].getAttribute('data-src');
                                temp.onload = function(){
                                    imgs[i].src = imgs[i].getAttribute('data-src')
                                }
                            },2000)
                        })(i)
                    }
                }
            }
            lazyload(imgs);

            window.onscroll =function(){
                lazyload(imgs);
            }
        }
        function doit(){
            setInterval(lazy,1000)
        }
        window.onload = doit;


        var page = 1;
        $(function (){
            AddRow(page);
            page = page + 1;
        });

        $(function (){
            window.addEventListener('scroll',function(){
                if(window.pageYOffset + window.innerHeight >= document.documentElement.scrollHeight){
                    if(page<50){
                        AddRow(page);
                        page = page+1;
                    }
                    $("#services>.container>.row").show();
                }
            });
        });

        function AddRow(page){
            $.ajax({
                type:'GET',
                url:"./AjaxResponse?page="+page,
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success:function(data){
                    var Row = $("#services>.container>.row");
                    var goods = data.goods;
                    if(goods.length<6){delete(doit())}
                    for(var i=0;i<goods.length;i++){
                        var good = goods[i];
                        Row.append("<div class=\"col-lg-4 col-md-6 icon-box\" data-aos=\"fade-up\"><img src=\"static/Loading.png\" data-src=\""+good.imgsrc+"\"><h4 class=\"title\"><a href=\"./More.jsp?id="+good.getid+"\">￥"+good.price+"</a></h4><p class=\"description\" style=\"font-family:'锐字真言体免费商用'\">"+good.intro+"</p></div>");
                    }
                }
                }).fail(function(xhr,status){
                console.log(status);

            })
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
            </ul>
        </nav><!-- .nav-menu -->

    </div>
</header><!-- End Header -->

<!-- ======= Our Team Section ======= -->
<section id="team" class="team">
    <div class="container">

        <div class="section-title">
            <h2>RecordSystemV1.03</h2>
            <p>应用Tomcat,Java,Servlet,MySQL实现</p>
        </div>

        <!-- ======= Counts Section ======= -->
        <section class="counts section-bg">
            <div class="container">

                <div class="row">

                    <div class="col-lg-3 col-md-6 text-center" data-aos="fade-up">
                        <a href="recordlist.jsp">
                            <div class="count-box">
                                <i class="icofont-simple-smile" style="color: #20b38e;"></i>
                                <span data-toggle="counter-up"><%
                                    SQLtool sqLtool = new SQLtool();
                                    Connection conn;
                                    conn = SQLtool.Connect();
                                    Statement statement = conn.createStatement();
                                    try {
                                        ResultSet resultSet = statement.executeQuery("select count(1) AS count from record;");
                                        while(resultSet.next()){
                                        out.print(resultSet.getInt("count"));
                                        }
                                        resultSet.close();
                                        statement.close();
                                        conn.close();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                %></span>
                                <p>产品</p>
                            </div>
                        </a>
                    </div>

                    <div class="col-lg-3 col-md-6 text-center" data-aos="fade-up" data-aos-delay="200">
                        <a href="recordlist.jsp">
                            <div class="count-box">
                                <i class="icofont-document-folder" style="color: #c042ff;"></i>
                                <span data-toggle="counter-up">1</span>
                                <p>数据库文件</p>
                            </div>
                        </a>
                    </div>

                    <div class="col-lg-3 col-md-6 text-center" data-aos="fade-up" data-aos-delay="400">
                        <a href="echarts.jsp">
                            <div class="count-box">
                                <i class="icofont-live-support" style="color: #46d1ff;"></i>
                                <span data-toggle="counter-up">1</span>
                                <p>货物图表</p>
                            </div>
                        </a>
                    </div>

                    <div class="col-lg-3 col-md-6 text-center" data-aos="fade-up" data-aos-delay="600">
                        <a href="/RecordSystem/team">
                            <div class="count-box">
                                <i class="icofont-users-alt-5" style="color: #ffb459;"></i>
                                <span data-toggle="counter-up">1</span>
                                <p>团队人数</p>
                            </div>
                        </a>
                    </div>

                </div>



            </div>
        </section><!-- End Counts Section -->

    </div>
</section><!-- End Our Team Section -->

<section id="services" class="services">
    <div class="container">

        <div class="section-title">
            <h2>Goods</h2>
        </div>

        <div class="row">
<%--            <%--%>
<%--                SQLtool sqLtool1 = new SQLtool();--%>
<%--                ArrayList<Goods> arrayList = new ArrayList<>();--%>
<%--                arrayList = sqLtool1.LimitSelect(60);--%>
<%--                for(Goods goods:arrayList){--%>
<%--                    //实现前后端交互下拉加载的网页：https://www.jb51.net/article/135139.htm--%>
<%--            %>--%>
<%--            <div class="col-lg-4 col-md-6 icon-box" data-aos="fade-up">--%>
<%--                <img src="static/Loading.png" data-src="<%=goods.getImgsrc()%>">--%>
<%--                <h4 class="title"><a href="">￥<%=goods.getPrice()%></a></h4>--%>
<%--                <p class="description" style="font-family:'锐字真言体免费商用'"><%=goods.getIntro()%></p>--%>
<%--            </div>--%>

<%--            <%--%>

<%--                }--%>
<%--            %>--%>
        </div>
        <form>
            <div id="more"></div>
        </form>
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

</body>

</html>