<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<html>
<head>
    <title>Login</title>
</head>
<body style="background-color:#1b1c1e;">
<%--<h4 style="color: red">${errorMsg}</h4>--%>
<form method="post" id="login" action="${pageContext.request.contextPath}/login"></form>
<div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
            <div class="card bg-dark text-white" style="border-radius: 1rem;">
                <div class="card-body p-5 text-center">
                    <div class="mb-md-5 mt-md-4 pb-5">
                        <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                        <p class="text-white-50 mb-10">Please enter your login and password!</p>
                        <p class="text-danger mb-10">${errorMsg}</p>
                        <div class="form-outline form-white mb-4">
                            <input type="text" id="typeLogin" class="form-control form-control-lg"
                                   name="login" form="login"/>
                            <label class="form-label" for="typeLogin">Login</label>
                        </div>
                        <div class="form-outline form-white mb-4">
                            <input type="password" id="typePassword" class="form-control form-control-lg"
                                   name="password" form="login"/>
                            <label class="form-label" for="typePassword">Password</label>
                        </div>
                        <button class="btn btn-outline-light btn-lg px-5" type="submit" form="login">Login</button>
                    </div>
                    <div>
                        <p class="mb-0">Don't have an account? <a href="${pageContext.request.contextPath}/renters/add"
                                                                  class="text-white-50 fw-bold">Sign Up</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
