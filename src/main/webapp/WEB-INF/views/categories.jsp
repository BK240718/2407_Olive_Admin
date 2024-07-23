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
      				<div class="container">
      					<!-- row -->
      					<div class="row mb-8">
      						<div class="col-md-12">
      							<div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-4">
      								<!-- pageheader -->
      								<div>
      									<h2>Categories</h2>
      									<!-- breacrumb -->
      									<nav aria-label="breadcrumb">
      										<ol class="breadcrumb mb-0">
      											<li class="breadcrumb-item"><a href="#" class="text-inherit">Dashboard</a></li>
      											<li class="breadcrumb-item active" aria-current="page">Categories</li>
      										</ol>
      									</nav>
      								</div>
      								<!-- button -->
      								<div>
      									<a href="writeFormSection" class="btn btn-primary">Add New Category</a>
      								</div>
      							</div>
      						</div>
      					</div>
      					<div class="row">
      						<div class="col-xl-12 col-12 mb-5">
      							<!-- card -->
      							<div class="card h-100 card-lg">
      								<div class="px-6 py-6">
      									<div class="row justify-content-between">
      										<div class="col-lg-4 col-md-6 col-12 mb-2 mb-md-0">
      											<!-- form -->
      											<form class="d-flex" role="search">
      												<input class="form-control" type="search" placeholder="Search Category" aria-label="Search" />
      											</form>
      										</div>
      										<!-- select option -->
      										<div class="col-xl-2 col-md-4 col-12">
      											<select class="form-select">
      												<option selected>Status</option>
      												<option value="Published">Published</option>
      												<option value="Unpublished">Unpublished</option>
      											</select>
      										</div>
      									</div>
      								</div>
      								<!-- card body -->
      								<div class="card-body p-0">
      									<!-- table -->
      									<div class="table-responsive">
      									    <c:set var="num" value="${page.total-page.start+1}"></c:set>
      										<table class="table table-centered table-hover mb-0 text-nowrap table-borderless table-with-checkbox">
      											<thead class="bg-light">
      												<tr>
      													<th>
      														<div class="form-check">
      															<input class="form-check-input" type="checkbox" value="" id="checkAll" />
      															<label class="form-check-label" for="checkAll"></label>
      														</div>
      													</th>
      													<th>No.</th>
      													<th>Section ID</th>
      													<th>Name</th>
      													<th></th>
      												</tr>
      											</thead>
      											<tbody>
      											    <c:forEach var="section" items="${listSection}">
                                                        <tr>
                                                            <td>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="checkbox" value="" id="categoryOne" />
                                                                    <label class="form-check-label" for="categoryOne"></label>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                ${num}
                                                            </td>
                                                            <td>
                                                                ${section.sectionId}
                                                            </td>
                                                            <td>
                                                                <a href="detailSection?sectionId=${section.sectionId}" class="text-reset">${section.secName}</a>
                                                            </td>


                                                            <td>
                                                                <div class="dropdown">
                                                                    <a href="#" class="text-reset" data-bs-toggle="dropdown" aria-expanded="false">
                                                                        <i class="feather-icon icon-more-vertical fs-5"></i>
                                                                    </a>
                                                                    <ul class="dropdown-menu">
                                                                        <li>
                                                                            <a class="dropdown-item" href="#">
                                                                                <i class="bi bi-trash me-3"></i>
                                                                                Delete
                                                                            </a>
                                                                        </li>
                                                                        <li>
                                                                            <a class="dropdown-item" href="updateFormSection?sectionId=${section.sectionId}">
                                                                                <i class="bi bi-pencil-square me-3"></i>
                                                                                Edit
                                                                            </a>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <c:set var="num" value="${num-1}"></c:set>
      											    </c:forEach>

      											</tbody>
      										</table>
      									</div>
      								</div>
      								<div class="border-top d-flex justify-content-between align-items-md-center px-6 py-6 flex-md-row flex-column gap-4">
      									<span>Showing 1 to 8 of 12 entries</span>
      									<nav>
      										<ul class="pagination mb-0">
      										    <c:if test="${page.startPage > page.pageBlock}">
                                                    <li class="page-item disabled">
                                                        <a class="page-link" href="listSection?currentPage=${page.startPage-page.pageBlock}">Previous</a>
                                                    </li>
                                                </c:if>
                                                <c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">
                                                    <li class="page-item">
                                                        <a class="page-link active" href="listSection?currentPage=${i}">${i}</a>
                                                    </li>
                                                </c:forEach>
                                                <c:if test="${page.endPage < page.totalPage}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="listSection?currentPage=${page.startPage+page.pageBlock}">Next</a>
                                                    </li>
                                                </c:if>
      										</ul>
      									</nav>
      								</div>
      							</div>
      						</div>
      					</div>
      				</div>
      			</main>
      		</div>
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