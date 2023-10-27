<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
</head>
<style>
  nav ul {
    list-style: none;
    padding: 0;
    margin: 0;
    width: 120px;
    overflow: hidden;
  }

  nav .admin_mainmenu > li > button {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: rgb(141, 18, 18);
    color: #e5e5e5;
    width: 120px;
    height: 30px;
  }


  nav ul li button {
    background: none;
    border: none;
    padding: 0;
    cursor: pointer;
  }

  .admin_submenu > li > button {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: rgb(153, 87, 87);
    color: #e5e5e5;
    width: 120px;
    height: 30px;
  }

  .personal_modal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-80%, -50%) scale(0.7) ;
    z-index: 10;
    opacity: 0;
    transition: all 0.5s;
  }
  .personal_modal.active {
    opacity: 1;
    transform: translate(-50%, -50%) scale(1);
  }

  .personal_modal_content {
    display: flex;
    border-radius: 5px;
    overflow: hidden;
    background-color: #eee;
    box-shadow: 0 0 10px rgba(12, 12, 12, 0.178);
  }

  .personal_modal_content > div {
    padding: 20px;
  }

  .desc-header {
    float: right;
    margin: 15px 0;
  }

  .member_container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 400px;
    font-size: 9pt;
  }

  .profile_group {
    display: flex;
    align-items: center;
  }

  .profile_group > img {
    margin-right: 10px;
    width: 30px;
    height: 30px;
  }

  .btn_group {
    display: flex;
  }

  .btn_group button {
    margin-left: 10px;
  }

  .overlay {
    background-color: #00000034;
    position: fixed;
    width: 100%;
    height: 100vh;
    top: 0;
    left: 0;
    transition: 0.5s;
    opacity: 0;
    pointer-events: none;
  }

  .overlay.active {
    opacity: 1;
    pointer-events: all;
  }

  .btn-close {
    position: absolute;
    top: 10px;
    right: 10px;
    background-color: transparent;
    border: none;
    font-size: 18px;
    color: #888;
    cursor: pointer;
  }

  .desc-content button {
    display: block;
    width: 100%;
    border: none;
    background-color: crimson;
    color: #e5e5e5;
    padding: 8px;
  }
</style>
<body>
  <nav>
    <ul class="admin_mainmenu">
      <li><button class="mainmenu_btn">회원</button>
        <ul class="admin_submenu">
          <li><button class="admin_person_btn">개인</button></li>
          <li><button>기업</button></li>
        </ul>
      </li>
      <li><button class="mainmenu_btn">게시글</button>
        <ul class="admin_submenu">
          <li><button>자유 게시판</button></li>
          <li><button>질답 게시판</button></li>
          <li><button>기업소식 게시판</button></li>
          <li><button>알고리즘 게시판</button></li>
        </ul>
      </li>
      <li><button class="mainmenu_btn">통합</button>
        <ul class="admin_submenu">
          <li><button>공지</button></li>
          <li><button>신고</button></li>
          <li><button>해시태그</button></li>
        </ul>
      </li>
      <li><button class="mainmenu_btn">환경설정</button>
        <ul class="admin_submenu">
          <li><button>임시</button></li>
        </ul>
      </li>
    </ul>
  </nav>
  <div class="personal_modal">
    <div class="personal_modal_content">
      <div class="desc">
        <div class="desc-header">
          <input type="text" placeholder="홍길동"/>
          <button class="admin_general_member_search">검색</button>
          <button class="btn-close">&times;</button>
        </div>
          <div class="desc-content">
            <div class="member_container">
                <div class="profile_group">
                  <img src="#" alt="." /><div>이름</div>
                </div>
                <div class="btn_group">
                  <button>경고</button>
                  <button>탈퇴</button>
                </div>
            </div>
          </div>
      </div>
    </div>
  </div>
  <div class="company_modal">
    <div class="company_modal_content">
      <div class="desc">
        <div class="desc-header">
          <input type="text" placeholder="홍길동"/>
          <button class="admin_company_member_search">검색</button>
          <button class="btn-close">&times;</button>
        </div>
          <div class="desc-content">
            <div class="company_container">
                <div class="profile_group">
                  <img src="#" alt="." /><div>이름</div>
                </div>
                <div class="btn_group">
                  <button>경고</button>
                  <button>탈퇴</button>
                </div>
            </div>
          </div>
      </div>
    </div>
  </div>
  <div class="overlay"></div>
</body>
<script>
  $('.mainmenu_btn').next('.admin_submenu').slideToggle(200)
  $('.mainmenu_btn').click(function() {
    let submenu = $(this).next('.admin_submenu')
    submenu.slideToggle(200)
  })

  $('.btn-close, .overlay').click(function() {
    $('.personal_modal, .overlay').removeClass('active')
  })

  $('body').keyup(function(e) {
    if (e.key === 'Escape') {
      $('.personal_modal, .overlay').removeClass('active')
    }
  })
  
  $('.admin_person_btn').click(function() {
    $('.personal_modal, .overlay').addClass('active')
  })

  $.get('/home/admin/person', function(response) {
    for (let i = 0; i < response.length; i++) {
        let member = response[i]
        generalMemberTemplate = 
            `<div class="member_container">
                <div class="profile_group">
                <img src="\${member.profilePic}" alt="."><div>\${member.nickname}(\${member.email})</div>
                </div>
                <div class="btn_group">
                    <button>경고</button>
                    <button>탈퇴</button>
                </div>
            </div>`
        generalMemberTemplateDom = $(generalMemberTemplate)
        
        $('.desc-content').append(generalMemberTemplateDom)
    }
    })

</script>
</html>