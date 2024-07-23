<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
   </head>
   <body>
      <h1>직원관리</h1>
      <h3>사원수: ${totalStaff}</h3>

      <c:out value="Hello World"/>

   </body>
         <!-- Libs JS -->
   <!-- <script src="../assets/libs/jquery/dist/jquery.min.js"></script> -->
   <script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
   <script src="../assets/libs/simplebar/dist/simplebar.min.js"></script>

   <!-- Theme JS -->
   <script src="../assets/js/theme.min.js"></script>

         <script src="../assets/libs/apexcharts/dist/apexcharts.min.js"></script>
         <script src="../assets/js/vendors/chart.js"></script>
</html>