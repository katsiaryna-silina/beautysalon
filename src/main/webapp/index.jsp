<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale.pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title"/></title>
</head>

<body>
<c:choose>
    <c:when test="${role == 'ADMIN'}">
        <jsp:include page="jsp/fragment/header_admin_with_locale.jsp"/>
    </c:when>
    <c:when test="${role == 'CLIENT'}">
        <jsp:include page="jsp/fragment/header_client_with_locale.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="jsp/fragment/header_default.jsp"/>
    </c:otherwise>
</c:choose>
<br/>
<h3 class="text-center"><fmt:message key="description.salon.message"/></h3>
<div class="container">
    </br>
    <div class="row">
        <div class="col text-center">
            <a>${welcome_message}</a>
            <a>${login_needed_message}</a>
            <hr/>
        </div>
    </div>
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
                    aria-current="true" aria-label="Slide 4"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4"
                    aria-label="Slide 5"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="5"
                    aria-label="Slide 6"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="6"
                    aria-label="Slide 7"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="7" class="active"
                    aria-label="Slide 8"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="image/cat-combing.jpg" class="d-block w-100" alt="Combing">
                <div class="carousel-caption d-none d-md-block">
                    <h5><fmt:message key="services.combing"/></h5>
                    <p><fmt:message key="services.combing.description"/></p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="image/cat-nail-clipping.jpg" class="d-block w-100" alt="Nail clipping">
                <div class="carousel-caption d-none d-md-block">
                    <h5><fmt:message key="services.nail.clipping"/></h5>
                    <p><fmt:message key="services.nail.clipping.description"/></p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="image/cat-ear-cleaning.jpg" class="d-block w-100" alt="Ear cleaning">
                <div class="carousel-caption d-none d-md-block">
                    <h5><fmt:message key="services.ear.cleaning"/></h5>
                    <p><fmt:message key="services.ear.cleaning.description"/></p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="image/cat-bathing.jpg" class="d-block w-100" alt="Bathing">
                <div class="carousel-caption d-none d-md-block">
                    <h5><fmt:message key="services.bathing"/></h5>
                    <p><fmt:message key="services.bathing.description"/></p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="image/cat-drying.jpg" class="d-block w-100" alt="Drying">
                <div class="carousel-caption d-none d-md-block">
                    <h5 style="color: black"><fmt:message key="services.drying"/></h5>
                    <p style="color: black"><fmt:message key="services.drying.description"/></p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="image/cat-haircut.jpg" class="d-block w-100" alt="Haircut">
                <div class="carousel-caption d-none d-md-block">
                    <h5><fmt:message key="services.haircut"/></h5>
                    <p><fmt:message key="services.haircut.description"/></p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="image/cat-molting.jpg" class="d-block w-100" alt="Express molting">
                <div class="carousel-caption d-none d-md-block">
                    <h5 style="color: black"><fmt:message key="services.molting"/></h5>
                    <p style="color: black"><fmt:message key="services.molting.description"/></p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="image/cat-all-complex.jpg" class="d-block w-100" alt="All complex">
                <div class="carousel-caption d-none d-md-block">
                    <h5 style="color: black"><fmt:message key="services.all.complex"/></h5>
                    <p style="color: black"><fmt:message key="services.all.description"/></p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>
</body>
</html>