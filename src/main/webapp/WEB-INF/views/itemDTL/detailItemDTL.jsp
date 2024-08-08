<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
      <link href="../assets/libs/dropzone/dist/min/dropzone.min.css" rel="stylesheet" />
        <link href="../assets/libs/tiny-slider/dist/tiny-slider.css" rel="stylesheet" />
        <link href="../assets/libs/slick-carousel/slick/slick.css" rel="stylesheet" />
        <link href="../assets/libs/slick-carousel/slick/slick-theme.css" rel="stylesheet" />
        <!-- Favicon icon-->
        <link rel="shortcut icon" type="image/x-icon" href="../assets/images/favicon/favicon.ico" />

        <!-- Libs CSS -->
        <link href="../assets/libs/bootstrap-icons/font/bootstrap-icons.min.css" rel="stylesheet" />
        <link href="../assets/libs/feather-webfont/dist/feather-icons.css" rel="stylesheet" />
        <link href="../assets/libs/simplebar/dist/simplebar.min.css" rel="stylesheet" />

        <!-- Theme CSS -->
        <link rel="stylesheet" href="../assets/css/theme.min.css" />
   </head>
   <body>
      
   <main class="main-content-wrapper">
               <!-- container -->
               <div class="container">
                  <!-- row -->
                  <div class="row mb-8">
                     <div class="col-md-12">
                        <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-4">
                           <div>
                              <!-- page header -->
                              <h2>Detail Product</h2>
                              <!-- breacrumb -->
                              <nav aria-label="breadcrumb">
                                 <ol class="breadcrumb mb-0">
                                    <li class="breadcrumb-item"><a href="#" class="text-inherit">Dashboard</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Detail Product</li>
                                 </ol>
                              </nav>
                           </div>
                           <!-- button -->
                           <div>
                              <a href="#" class="btn btn-primary">Back to all products</a>
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- row -->
                  <div class="row">
                     <div class="col-xl-12 col-12 mb-5">
                        <!-- card -->
                        <div class="card h-100 card-lg">
                           <div class="card-body p-6">
                              <div class="d-md-flex justify-content-between">
                                 <div class="d-flex align-items-center mb-2 mb-md-0">
                                    <h2 class="mb-0">Product Detail ID: ${itemDTL.itemDtlId}</h2>
                                    <span class="badge bg-light-warning text-dark-warning ms-2">Pending</span>
                                 </div>
                                 <!-- select option -->
                                 <div class="d-md-flex">
                                    <div class="mb-2 mb-md-0">
                                       <select class="form-select">
                                          <option selected>Status</option>
                                          <option value="Success">Success</option>
                                          <option value="Pending">Pending</option>
                                          <option value="Cancel">Cancel</option>
                                       </select>
                                    </div>
                                    <!-- button -->
                                    <div class="ms-md-3">
                                       <a href="#" class="btn btn-primary">Save</a>
                                       <a href="#" class="btn btn-secondary">Download Invoice</a>
                                    </div>
                                 </div>
                              </div>

                              <section class="mt-8">
                                  <div class="container">
                                     <div class="row">
                                        <div class="col-md-5 col-xl-6">
                                           <div class="slider slider-for">
                                              <div>
                                                 <div class="zoom" onmousemove="zoom(event)" style="background-image: url(${itemDTL.thumbnail})">
                                                    <!-- img -->
                                                    <!-- img -->
                                                    <img src="${itemDTL.thumbnail}" alt="" />
                                                 </div>
                                              </div>
                                              <c:forEach var="detailImg" items="${detailImgList}">
                                                  <div>
                                                     <div class="zoom" onmousemove="zoom(event)" style="background-image: url(${detailImg})">
                                                        <!-- img -->
                                                        <img src="${detailImg}" alt="" />
                                                     </div>
                                                  </div>
                                              </c:forEach>
                                           </div>
                                           <div class="slider slider-nav mt-4">
                                              <div>
                                                 <img src="${itemDTL.thumbnail}" alt="" class="w-100 rounded" />
                                              </div>
                                              <c:forEach var="detailImg" items="${detailImgList}">
                                                  <div>
                                                     <img src="${detailImg}" alt="" class="w-100 rounded" />
                                                  </div>
                                              </c:forEach>
                                           </div>
                                        </div>

                                        <div class="col-md-7 col-xl-6">
                                           <div class="ps-lg-10 mt-6 mt-md-0">
                                              <!-- content -->
                                              <a href="#!" class="mb-4 d-block">${itemDTL.secName}</a>
                                              <!-- heading -->
                                              <h2 class="mb-3">${itemDTL.itemName}</h2>
                                              <h5 class="mb-1">${itemDTL.colorName}</h5>


                                              <!-- hr -->
                                              <hr class="my-6" />

                                              <div>
                                                 <!-- table -->
                                                 <table class="table table-borderless mb-0">
                                                    <tbody>
                                                       <tr>
                                                          <td>Purchase Price:</td>
                                                          <td><fmt:formatNumber type="currency" currencySymbol="₩" value="${itemDTL.purchasePrice}"/></td>
                                                       </tr>
                                                       <tr>
                                                          <td>Sales Price:</td>
                                                          <td><fmt:formatNumber type="currency" currencySymbol="₩" value="${itemDTL.salesPrice}"/></td>
                                                       </tr>
                                                       <tr>
                                                          <td>Quantity:</td>
                                                          <td>${itemDTL.itemDtlQty}</td>
                                                       </tr>
                                                       <tr>
                                                          <td>YearMonth:</td>
                                                          <td>${itemDTL.yearMonth}</td>
                                                       </tr>
                                                       <tr>
                                                          <td>InitialFinal:</td>
                                                          <td>${itemDTL.initialFinalType}</td>
                                                       </tr>
                                                       <tr>
                                                          <td>등록자:</td>
                                                          <td>${itemDTL.staffName}</td>
                                                       </tr>
                                                       <tr>
                                                          <td>등록일:</td>
                                                          <td><fmt:formatDate value="${itemDTL.regDateAsDate}" pattern="yyyy-MM-dd"/></td>
                                                       </tr>


                                                    </tbody>
                                                 </table>
                                              </div>

                                           </div>
                                        </div>
                                     </div>
                                  </div>
                               </section>
                           </div>

                        </div>
                     </div>
                  </div>
               </div>
            </main>

<!-- Javascript-->
  <script src="../assets/libs/rater-js/index.js"></script>
  <script src="../assets/libs/dropzone/dist/min/dropzone.min.js"></script>
  <!-- Libs JS -->
  <!-- <script src="../assets/libs/jquery/dist/jquery.min.js"></script> -->
  <script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="../assets/libs/simplebar/dist/simplebar.min.js"></script>

  <!-- Theme JS -->
  <script src="../assets/js/theme.min.js"></script>

  <script src="../assets/js/vendors/jquery.min.js"></script>
  <script src="../assets/libs/tiny-slider/dist/min/tiny-slider.js"></script>
  <script src="../assets/js/vendors/tns-slider.js"></script>
  <script src="../assets/js/vendors/zoom.js"></script>
  <script src="../assets/libs/slick-carousel/slick/slick.min.js"></script>
  <script src="../assets/js/vendors/slick-slider.js"></script>
  <script src="../assets/js/vendors/dropzone.js"></script>


   </body>
</html>