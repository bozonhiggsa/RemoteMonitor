<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">

    <title>MainApp</title>


    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/components/bootstrap/dist/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/components/angular-block-ui/dist/angular-block-ui.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/components/angularjs-toaster/toaster.min.css"/>" rel="stylesheet">

    <%--<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:light&subset=cyrillic,latin' rel='stylesheet' type='text/css'/>--%>

    <script type="text/javascript" src="<c:url value="/resources/components/jquery/dist/jquery.min.js"/>"></script>


</head>

<body>


<div id="home" ui-view autoscroll ></div>


<script type="text/javascript" src="<c:url value="/resources/components/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/components/underscore/underscore-min.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/components/angular/angular.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/components/angular-ui-router/release/angular-ui-router.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/components/angular-bootstrap/ui-bootstrap-tpls.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/components/angular-block-ui/dist/angular-block-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/components/angular-breadcrumb/release/angular-breadcrumb.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/components/angularjs-toaster/toaster.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/lib/sockjs-client/dist/sockjs.js"/>"></script>


<script type="text/javascript" src="<c:url value="/resources/js/app.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/routers.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/controllers/HomeController.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/controllers/MainController.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/controllers/LoginController.js"/>"></script>


<script type="text/javascript" src="<c:url value="/resources/js/services/HomeService.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/services/MainService.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/services/LoginService.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/services/WebSocketService.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/directives/Pagination.js"/>"></script>

<%--<script type="text/javascript" src="<c:url value="/resources/build/js/uglify.js"/>"></script>--%>
<%--<link href="<c:url value="/resources/build/css/index.css"/>" rel="stylesheet"/>--%>


<script type="text/javascript">

    angular.bootstrap(document, ['mainApp']);

</script>

</body>
</html>