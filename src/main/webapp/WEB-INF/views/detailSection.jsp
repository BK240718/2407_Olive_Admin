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
    <!-- main -->
             <main class="main-content-wrapper">
                <!-- container -->
                <div class="container">
                   <!-- row -->
                   <div class="row mb-8">
                      <div class="col-md-12">
                         <div class="d-md-flex justify-content-between align-items-center">
                            <!-- page header -->
                            <div>
                               <h2>Detail Category</h2>
                               <!-- breacrumb -->
                               <nav aria-label="breadcrumb">
                                  <ol class="breadcrumb mb-0">
                                     <li class="breadcrumb-item"><a href="#" class="text-inherit">Dashboard</a></li>
                                     <li class="breadcrumb-item"><a href="#" class="text-inherit">Categories</a></li>
                                     <li class="breadcrumb-item active" aria-current="page">Detail Category</li>
                                  </ol>
                               </nav>
                            </div>
                            <div>
                               <a href="listSection" class="btn btn-light">Back to Categories</a>
                            </div>
                         </div>
                      </div>
                   </div>
                   <div class="row">
                      <div class="col-lg-12 col-12">
                         <!-- card -->
                         <div class="card mb-6 shadow border-0">
                            <!-- card body -->
                            <div class="card-body p-6">


                               <h2 class="mb-0">Category ID: ${section.sectionId}</h2>

                               <div class="mt-8">
                                 <div class="row">
                                    <!-- address -->
                                    <div class="col-lg-4 col-md-4 col-12">
                                       <div class="mb-6">
                                          <h6>Category Name</h6>
                                          <p class="mb-1 lh-lg">
                                             ${section.secName}
                                          </p>

                                       </div>
                                    </div>

                                 </div>
                              </div>
                            </div>
                         </div>
                      </div>
                   </div>
                </div>
             </main>
          </div>

          <script src="../assets/libs/flatpickr/dist/flatpickr.min.js"></script>
          <!-- Libs JS -->
    <!-- <script src="../assets/libs/jquery/dist/jquery.min.js"></script> -->
    <script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../assets/libs/simplebar/dist/simplebar.min.js"></script>

    <!-- Theme JS -->
    <script src="../assets/js/theme.min.js"></script>

          <script src="../assets/libs/quill/dist/quill.min.js"></script>
          <script src="../assets/js/vendors/editor.js"></script>
          <script src="../assets/libs/dropzone/dist/min/dropzone.min.js"></script>
       </body>
    </html>