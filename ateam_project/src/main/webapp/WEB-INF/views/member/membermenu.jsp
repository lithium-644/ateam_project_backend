<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<style type="text/css">
	a:link, a:hover, a:active, a:visited {
		color: #333;
		text-decoration: none;
	}
	div.right-align{
		text-align: right;
	}
	ul.horizontal-list {
		padding: 0px;
		margin: 0px;
	}
	ul.horizontal-list li {
	display: inline;
	}
</style>
<div class="right-align">
	<ul class="horizontal-list">
		<c:choose>
			<c:when test="${empty sessionScope._LOGIN_USER_}">
				<li>
					<a href="/member/signup">회원가입</a>
				</li>
				<li>
					<a href="/member/auth">로그인</a>
				</li>
			</c:when>
			<c:otherwise>
				<!-- 로그인 상태 -->
				<li style="margin-right: 15px;">
					${sessionScope._LOGIN_USER_.nickname}
					<a href="/member/withdraw">회원탈퇴</a>
					<a href="/member/logout">로그아웃</a>
				</li>
				<li>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>