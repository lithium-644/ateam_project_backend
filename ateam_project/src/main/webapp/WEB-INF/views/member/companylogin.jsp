<!-- 작성자: 김태현
	 작성일: 2023-10-26
	 내용: 로그인 화면을 표시하는 파일입니다. -->

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 로그인</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
<!-- <link rel="stylesheet" href="/css/style.css"> -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<script src="/js/lib/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="/css/style.css">
<style>
  *:lang(en) {
      font-family: 'Open Sans', sans-serif;
  }
  *:lang(ko-KR) {
      font-family: 'Noto Sans KR', sans-serif;
  }
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  
  body {
    width: 550px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .login_container {
    border-radius: 10px;
    overflow: hidden;
  }

  .login_container > .login_tab {
    display: flex;
    justify-content: column;
  }

  .login_container > .login_tab > button {
    border: 0px;
    width: 350px;
    height: 40px;
    background-color: var(--hashtag-blue);
    font-weight: bold;
    cursor: pointer;
  }

  .login_container > .login_tab > button.active {
    background-color: var(--blue);
    color: var(--light-gray);
  }

  .login_tab > div {
    text-align: center;
    padding: 10px 0;
  }


  .login_container > form {
    border-right: 1px solid var(--gray);
    border-left: 1px solid var(--gray);
    border-bottom: 1px solid var(--gray);
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
  }

  .input_container {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    padding-top: 20px;
    margin: 0 20px 20px 20px;
  }

  .input_container > .id_container,
  .input_container > .pw_container {
    display: flex;
    margin: 3px;
    padding: 1px;
    align-items: center;
    width: 300px;
  }

  .input_container .label {
    display: inline-block;
    width: 80px;
  }

  .input_container input[type="text"],
  .input_container input[type="password"] {
    outline: none;
    border: 0;
    height: 30px;
    padding: 3px 10px;
    background-color: var(--light-gray);
  }

  .input_container > .remember_id {
    margin: 0 0 5px 181px;
  }

  .input_container > .login_btn {
    margin-left: 225px;
    margin-bottom: 5px;
  }

  .input_container > .login_btn > input {
    background-color: var(--hashtag-blue);
    padding: 5px 10px;
    border-radius: 5px;
    border: 0;
    outline: none;
  }

  .input_container > .login_btn > input:hover {
    background-color: var(--light-blue);
  }

  .input_container > .login_btn > input:active {
    background-color: var(--blue);
  }

  .line_container {
    display: flex;
    justify-content: space-around;
    width: 90%;
    position: relative;
  }

  .line_container > .line {
    border-top: 1px solid #888;
    display: inline-block;
    width: 200px;
    position: absolute;
  }

  .line_container > .line:first-child {
    top: 10px;
    left: 0;
  }
    
  .line_container > .line:last-child {
    top: 10px;
    right: 0;
  }

  .button_container {
    display: flex;
    justify-content: space-around;
    height: 70px;
    margin: 20px 0;
  }

  .button_container > button {
    margin: 0px 20px;
    width: 60px;
    height: 60px;
    border-radius: 50%;
    border: 0px;
    font-weight: bold;
  }

  .button_container > button:first-child {
    background-color: #F7E600;
    color: #3A1D1D; 
  }

  .button_container > button:nth-child(2) {
    background-color: #1EC800;
    color: #333; 
  }

  .button_container > button:last-child {
    background-color: #EA4335;
    color: #333; 
  }

  .signup_container {
    display: flex;
  }

  .signup_container a {
    color: var(--deep-blue);
  }

  .signup_container > div {
    margin-right: 10px;
  }

  .notice_container {
    display: flex;
    margin-top: 5px;
    margin-bottom: 20px;
    justify-content: center;
  }

  .notice_container > a {
    margin-right: 10px; 
  }

  .notice_container .link_to_voc {
    padding-right: 10px;
    border-right: 1px solid var(--gray);
    color: var(--gray);
  }

  .notice_container .link_to_pw {
    color: var(--blue);
  }

  .message {
    width: 250px;
    margin: 20px 0;
    height: 70px;
    display: none;
  }

  .tab_content {
    margin: 31px 0;
  }

  .errors {
  text-align: center;
  color: var(--red);
  }
</style>
</head>
<body>
  <section class="login_container">
    <div class="login_tab">
        <button id="personal_btn" class="personal_tab">개인</button>
        <button id="company_btn" class="personal_tab active">기업</button>
    </div>
    <form:form class="login_form" method="post" action="/company/auth">
      <div class="input_container">
      <div>
        <form:errors path="email" element="div" cssClass="errors"/>
        <form:errors path="pw" element="div" cssClass="errors"/>
        <c:if test="${not empty message}">
          <div class="errors">
            ${message}
          </div>
        </c:if> 
      </div>
        <div class="id_container">
          <input type="hidden" name ="next" value="${next}" />
          <label for="email" class="label">이메일</label>
          <input type="text" name="email" id="email" />
        </div>
        <div class="pw_container">
          <label for="pw" class="label">비밀번호</label>
          <input type="password" name="pw" id="pw" />
        </div>
        <div class="remember_id">
          <input type="checkbox"id="id_save"/><label for="id_save">아이디 저장</label> 
        </div>
        <div class="login_btn">
          <input type="submit" value="로그인" />
        </div>
        <div class="line_container">
          <div class="line"></div>
          <div class="textinline">또는</div>
          <div class="line"></div>
        </div>
        <div class="message button_container tab_content" id="tab1">
          <button class="not_btn">카카오톡</button>
          <button class="not_btn">네이버</button>
          <button class="not_btn">구글</button>
        </div>
        <div class="tab_content" id="tab2">
          기업회원으로 가입하시면 다양한<br> 혜택을 이용하실 수 있습니다.
        </div>
        <div class="signup_container">
          <div>아직 회원이 아니신가요?</div>
          <a href="/member/signup" target="_blank">가입하기</a>
        </div>
        <div class="notice_container">
          <a href="#" class="link_to_voc">고객센터</a>
          <span><a href="#" class="link_to_pw">비밀번호</a>를 잊으셨나요?</span>
        </div>
      </div>
    </form:form>
  </section>
  <script>
    $('#personal_btn').click(function() {
    //  console.log('개인버튼')
    //  $(this).addClass('active')
    //  $('#company_btn').removeClass('active')
    //  $('#tab1').css('display', 'flex')
    //  $('#tab2').css('display', 'none')
      window.location.href='/member/auth'
    })

    $('#company_btn').click(function() {
    //  console.log('회사버튼')
    //  $(this).addClass('active')
    //  $('#personal_btn').removeClass('active')
    //  $('#tab2').css('display', 'block')
    //  $('#tab1').css('display', 'none')
      window.location.href='/company/auth'
    })

    $('.not_btn').click(function(e) {
      e.preventDefault()
    })
  </script>
</body>
 </html>