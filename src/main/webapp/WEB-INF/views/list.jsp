<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
<!DOCTYPE html>
<html>
   <head>

      <meta charset="UTF-8">
      <title>Insert title here</title>
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

   </head>
   <body>


<!-- row -->
<!-- main wrapper -->
            <main class="main-content-wrapper">
               <section class="container">
                  <div class="row">
                     <div class="col-xl-12 col-lg-12 col-md-12 col-12 mb-6">
                        <div class="card h-100 card-lg">
                           <!-- heading -->
                           <div class="p-6">
                              <h3 class="mb-0 fs-5">Category</h3>
                           </div>
                           <div class="card-body p-0">
                              <!-- table -->
                              <div class="table-responsive">
                                 <table class="table table-centered table-borderless text-nowrap table-hover">
                                    <thead class="bg-light">
                                       <tr>
                                          <th scope="col">Section ID</th>
                                          <th scope="col">Section Name</th>

                                       </tr>
                                    </thead>
                                    <tbody id="sectionTableBody">


                                    </tbody>
                                 </table>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
              </section>
          </main>

        <script type="text/javascript">
             $(document).ready(function() {
                $.ajax({
                    url:"mainSection",  // 서버에서 받는 URL
                    dataType:"json",    // 받는 형식
                    type:"POST",        // HTTP 요청 방식
                    success:function(response) {

                        console.log("Status: 200 OK");
                        console.log("Server response:", JSON.stringify(response, null, 2));

                        var sectionList = response.sectionList;
                        $('#sectionTableBody').empty(); // 기존 테이블 행 제거

                        // 새로운 테이블 행 추가
                        $.each(sectionList, function(index, section) {
                            var row = "<tr>"+
                                "<td>"+section.sectionId+"</td>"+
                                "<td>"+section.secName+"</td>"+
                                "</tr>";
                            $('#sectionTableBody').append(row);  // 새 행을 테이블에 추가
                        });
                    },
                    error: function(request, status, error){
                        console.log("mainSection error code:"+request.status+"\n"+
                                    "message:"+request.responseText+"\n"+
                                    "error:"+error);
                    }
                });
             });
       </script>

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