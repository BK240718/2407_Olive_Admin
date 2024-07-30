<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta content="Codescandy" name="author">
      <title>Dashboard eCommerce HTML Template - FreshCart</title>
      <!-- Favicon icon-->
<link rel="shortcut icon" type="image/x-icon" href="../assets/images/favicon/favicon.ico">


<!-- Libs CSS -->
<link href="../assets/libs/bootstrap-icons/font/bootstrap-icons.min.css" rel="stylesheet">
<link href="../assets/libs/feather-webfont/dist/feather-icons.css" rel="stylesheet">
<link href="../assets/libs/simplebar/dist/simplebar.min.css" rel="stylesheet">


<!-- Theme CSS -->
<link rel="stylesheet" href="../assets/css/theme.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <script>
            $(document).ready(function() {
                $('#addItemButton').on('click', function() {
                    var categoryId = $('#categorySelect').val();
                    var itemName = $('#itemName').val();

                    $.ajax({
                        type:   'POST',
                        url:    '/insertItem',
                        data: {
                            sectionId:categoryId,
                            itemName:itemName
                        },
                        dataType:'json',
                        success: function(response) {
                            if (response.status === 'success') {
                                alert('입력되었습니다');
                            } else {
                                alert('입력 실패: ' + response.message);
                            }
                        },
                        error: function() {
                            alert('서버 오류 발생');
                        }
                    });
                });
            });
        </script>
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
                      <h2>Add New Item</h2>

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
                      <div class="row mb-3">
                        <div class="mb-3 col-lg-6">
                            <!-- Category Name -->
                             <label class="form-label">Category Name</label>
                             <select class="form-select" id="categorySelect">
                                <option value="" selected>Category Name</option>
                                <c:forEach var="getSection" items="${getSection}">
                                    <option value="${getSection.sectionId}">${getSection.secName}</option>
                                </c:forEach>
                             </select>
                        </div>
                         <!-- input -->
                         <div class="mb-3 col-lg-6">
                            <label class="form-label">Item Name</label>
                            <input type="text" class="form-control" id="itemName" value="${item.itemName}" placeholder="Item Name" required />
                         </div>
                         <div class="mb-3 col-lg-6">
                            <button id="addItemButton" type="button" class="btn btn-primary mb-2">Add Item</button>
                         </div>
                      </div>
                   </div>
                </div>
             </div>
          </div>
       </div>
    </main>
 </div>
 <div id="alert" style="color: red;"></div>

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