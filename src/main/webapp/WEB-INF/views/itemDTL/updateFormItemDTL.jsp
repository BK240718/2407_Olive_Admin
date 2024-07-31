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
   </head>
   <body>
      <main class="main-content-wrapper">
      				<div class="container">
      					<div class="row mb-8">
      						<div class="col-md-12">
      							<div>
      								<h2>Create Item Detail</h2>
      								<!-- breacrumb -->
      								<nav aria-label="breadcrumb">
      									<ol class="breadcrumb mb-0">
      										<li class="breadcrumb-item"><a href="#" class="text-inherit">Dashboard</a></li>
      										<li class="breadcrumb-item active" aria-current="page">Create</li>
      									</ol>
      								</nav>
      							</div>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-12">
      							<div class="card shadow border-0">
      								<div class="card-body d-flex flex-column gap-8 p-7">
      								    <form action="updateItemDTL" method="post" name="updateItemDTLForm" class="row g-3 needs-validation" enctype="multipart/form-data" novalidate >
      								        <input type="hidden" name="itemDtlId" value="${itemDTL.itemDtlId}">
      								        <input type="hidden" name="thumbnail" value="${itemDTL.thumbnail}">
      								        <input type="hidden" name="detailImg" value="${itemDTL.detailImg}">
      								        <input type="hidden" name="colorImg" value="${itemDTL.colorImg}">
                                            <div class="d-flex flex-column flex-md-row align-items-center mb-4 file-input-wrapper gap-2">
                                                <div class="row w-100">
                                                    <!-- Thumbnail -->
                                                    <div class="col-md-4 d-flex align-items-center justify-content-center">
                                                        <div>
                                                            <img class="image avatar avatar-lg rounded-3" src="${itemDTL.thumbnail}" alt="Image" />
                                                        </div>
                                                        <div class="file-upload btn btn-light ms-md-4">
                                                            <input type="file" class="file-input opacity-0" name="thumbnailFile"/>
                                                            Upload Thumbnail
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4 d-flex align-items-center justify-content-center">
                                                        <div>
                                                            <img class="image avatar avatar-lg rounded-3" src="${detailImgList[0]}" alt="Image" />
                                                        </div>
                                                        <div class="file-upload btn btn-light ms-md-4">
                                                            <input type="file" class="file-input opacity-0" name="detailImgFile" multiple />
                                                            Upload Detail IMG
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4 d-flex align-items-center justify-content-center">
                                                        <div>
                                                            <img class="image avatar avatar-lg rounded-3" src="${itemDTL.colorImg}" alt="Image" />
                                                        </div>

                                                        <div class="file-upload btn btn-light ms-md-4">
                                                            <input type="file" class="file-input opacity-0" name="colorImgFile" />
                                                            Upload Color IMG
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-column gap-4">
                                                <h3 class="mb-0 h6">Item Detail Information</h3>

                                                <!-- input -->
                                                <div class="row mb-3">
                                                  <div class="col-lg-6">
                                                    <!-- Category Name -->
                                                     <label class="form-label">Category Name</label>
                                                     <select class="form-select" id="categorySelect" name="sectionId">
                                                        <option value="" selected>Category Name</option>
                                                        <c:forEach var="getSection" items="${getSection}">
                                                            <option value="${getSection.sectionId}" ${getSection.sectionId == itemDTL.sectionId ? "selected" : ""}>
                                                                ${getSection.secName}
                                                            </option>
                                                        </c:forEach>
                                                     </select>
                                                  </div>
                                                  <!-- Item Name -->
                                                  <div class="col-lg-6">
                                                    <label class="form-label">Item Name</label>
                                                    <div class="d-flex">
                                                        <!-- 선택 박스와 버튼을 같은 줄에 배치 -->
                                                        <select class="form-select flex-grow-1" id="itemSelect" name="itemId">
                                                            <!-- Items will be dynamically populated -->
                                                        </select>
                                                        <button type="button" class="btn btn-soft-primary ms-2" onclick="addItemForm()">Add</button>
                                                    </div>
                                                  </div>
                                                </div>
                                                <div class="row mb-3">
                                                    <div class="col-lg-6">
                                                        <!-- input -->
                                                        <!-- Color Name -->
                                                        <label for="creatCustomerName" class="form-label">
                                                            Color Name
                                                            <span class="text-danger">*</span>
                                                        </label>
                                                        <input type="text" class="form-control" name="colorName" id="creatCustomerName" value="${itemDTL.colorName}" required />
                                                        <div class="invalid-feedback">Please enter Color name</div>
                                                    </div>
                                                    <!-- Quantity -->
                                                    <div class="col-lg-6">
                                                        <!-- input -->
                                                        <label for="creatCustomerEmail" class="form-label">
                                                            Quantity
                                                            <span class="text-danger">*</span>
                                                        </label>
                                                        <input type="number" class="form-control" name="quantity" id="creatCustomerEmail" value="${itemDTL.quantity}" min="0" required />
                                                        <div class="invalid-feedback">Please enter quantity</div>
                                                    </div>
                                                </div>

                                                <div class="row mb-3">
                                                    <div class="col-lg-6">
                                                        <!-- input -->
                                                        <!-- Purchase Price -->
                                                        <label for="creatCustomerName" class="form-label">
                                                            Purchase Price
                                                            <span class="text-danger">*</span>
                                                        </label>
                                                        <input type="number" class="form-control" name="purchasePrice" id="creatPurchasePrice" min="0" value="${itemDTL.purchasePrice}" required />
                                                        <div class="invalid-feedback">Please enter Purchase Price</div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <!-- input -->
                                                        <!-- Sales Price -->
                                                        <label for="creatCustomerEmail" class="form-label">
                                                            Sales Price
                                                            <span class="text-danger">*</span>
                                                        </label>
                                                        <input type="number" class="form-control" name="salesPrice" id="creatSales Price" min="0" value="${itemDTL.salesPrice}"  required />
                                                        <div class="invalid-feedback">Please Sales Price</div>
                                                    </div>
                                                </div>

                                                <div class="row mb-3">
                                                    <div class="col-lg-6">
                                                        <!-- input -->
                                                        <!-- YearMonth -->
                                                        <label for="creatYearMonth" class="form-label">
                                                            YearMonth
                                                            <span class="text-danger">*</span>
                                                        </label>
                                                        <input type="text" class="form-control" name="yearMonth" id="creatYearMonth" value="${itemDTL.yearMonth}" required />
                                                        <div class="invalid-feedback">Please enter YearMonth</div>
                                                    </div>
                                                    <!-- InitialFinal -->
                                                    <div class="col-lg-6">
                                                         <label class="form-label">InitialFinal</label>
                                                         <select class="form-select" name="initialFinal">
                                                            <option value="" ${itemDTL.initialFinal == null ? 'selected' : ''}>Select InitialFinal</option>
                                                            <option value="0" ${itemDTL.initialFinal == 0 ? 'selected' : ''}>기초</option>
                                                            <option value="1" ${itemDTL.initialFinal == 1 ? 'selected' : ''}>기말</option>
                                                         </select>
                                                    </div>
                                                </div>

                                                <div class="row mb-3">
                                                    <div class="col-lg-6">
                                                        <!-- Staff -->
                                                         <label class="form-label">Staff</label>
                                                         <select class="form-select" name="staffId">
                                                            <option value="" ${itemDTL.staffId == null ? 'selected' : ''}>Staff Name</option>
                                                            <c:forEach var="getIdNameStaff" items="${getIdNameStaff}">
                                                                <option value="${getIdNameStaff.staffId}" ${itemDTL.staffId == getIdNameStaff.staffId ? 'selected' : ''}>
                                                                    ${getIdNameStaff.staffName}
                                                                </option>
                                                            </c:forEach>
                                                         </select>
                                                    </div>
                                                </div>

                                                <div>
                                                    <div class="col-12 mt-3">
                                                        <div class="d-flex flex-column flex-md-row gap-2">
                                                            <button class="btn btn-primary" type="submit">Create New Item Detail</button>
                                                            <button class="btn btn-secondary" type="submit">Cancel</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
      								</div>
      							</div>
      						</div>
      					</div>
      				</div>
      			</main>
<script src="../assets/libs/flatpickr/dist/flatpickr.min.js"></script>
<script src="../assets/libs/dropzone/dist/min/dropzone.min.js"></script>

<!-- Libs JS -->

<!-- <script src="../assets/libs/jquery/dist/jquery.min.js"></script> -->
<script src="../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="../assets/libs/simplebar/dist/simplebar.min.js"></script>

<!-- Theme JS -->
<script src="../assets/js/theme.min.js"></script>

    <script src="../assets/js/vendors/validation.js"></script>

<!-- AJAX and other custom scripts -->
<script>

    $(document).ready(function() {
        var selectedSectionId = '${itemDTL.sectionId}'; // 현재 카테고리 id
        var selectedItemId = '${itemDTL.itemId}';       // 현재 아이템 id

        // 아이템 로드 함수
        function loadItems(categoryId) {
            if (categoryId) {
                $.ajax({
                    url: 'getItemAjax',
                    type: 'GET',
                    data: {sectionId:categoryId},
                    dataType: 'json',
                    success: function(response) {

                        var status = response.status;

                        console.log("Status:", status);
                        console.log("Server response:", JSON.stringify(response, null, 2));

                        var items = response.data.items;
                        console.log("Items Array:", items);

                        var itemSelect = $('#itemSelect');
                                            itemSelect.empty();
                                            itemSelect.append('<option selected>Select Item</option>');
                        $.each(items, function(index, item) {
                            var isSelected = (item.itemId == selectedItemId) ? ' selected' : '';
                            itemSelect.append('<option value="' + item.itemId + '"' + isSelected + '>' + item.itemName + '</option>');
                        });
                    },
                    error: function(request, status, error) {
                        console.log("items error code:"+request.status+"\n"+
                                                        "message:"+request.responseText+"\n"+
                                                        "error:"+error);
                    }
                });
            } else {
                $('#itemSelect').empty().append('<option value="">Select Item</option>');
            }
        }

        // 페이지 로드 시 현재 카테고리 값으로 아이템 로드
        if (selectedSectionId) {
            $('#categorySelect').val(selectedSectionId);
            loadItems(selectedSectionId);
        }

        // 카테고리 선택 시 아이템 목록 업데이트
        $('#categorySelect').change(function() {
            var sectionId = $(this).val();
            loadItems(sectionId);
        });

    });


    function addItemForm() {
        window.open(
            '/showAddItemForm',
            'addItemForm',
            'width=600,height=500,scrollbars=yes,resizable=yes'
        );
    }
</script>
   </body>
</html>