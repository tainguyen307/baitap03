<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card shadow">
					<div class="card-body">
						<h2 class="text-center mb-4 text-muted">Đăng Nhập Vào Hệ
							Thống</h2>

						<!-- Hiển thị thông báo lỗi -->
						<c:if test="${alert != null}">
							<div class="alert alert-danger text-center">${alert}</div>
						</c:if>

						<!-- Form login -->
						<form action="login" method="post">
							<div class="mb-3 input-group">
								<span class="input-group-text"><i class="fa fa-user"></i></span>
								<input type="text" class="form-control" name="username"
									placeholder="Tài khoản" required>
							</div>

							<div class="mb-3 input-group">
								<span class="input-group-text"><i class="fa fa-lock"></i></span>
								<input type="password" class="form-control" name="password"
									placeholder="Mật khẩu" required>
							</div>

							<div
								class="d-flex justify-content-between align-items-center mb-3">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="rememberMe"
										name="remember"> <label class="form-check-label"
										for="rememberMe">Nhớ tôi</label>
								</div>
								<a href="forgetpass.jsp">Quên mật khẩu?</a>
							</div>

							<button type="submit" class="btn btn-primary w-100">Đăng
								nhập</button>
						</form>

						<p class="text-center mt-3">
							Nếu bạn chưa có tài khoản, hãy <a href="register.jsp">Đăng ký</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
