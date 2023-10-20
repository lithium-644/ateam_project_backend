<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 푼 알고리즘 문제</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<style>
    .container {
        display: flex;
        width: 920px;
        flex-wrap: wrap;
    }

    .box {
        border: 1px solid #333;
        width: 400px;
        height: 200px;
        overflow: auto;
        margin: 10px;
    }
</style>
</head>
<body>
    <div>로그인 정보: ${sessionScope._LOGIN_USER_.email}</div>
    <form action="/home/myalgorithm/list" class="search_form" method="get">
        <div>
            <select name="searchType">
                <option value="subject" ${searchMyAlgorithmVO.searchType eq 'subject' ? 'selected' : ''}>제목</option>
                <option value="content" ${searchMyAlgorithmVO.searchType eq 'content' ? 'selected' : ''}>내용</option>
            </select>
            <input type="text" name="searchKeyword" value="${searchMyAlgorithmVO.searchKeyword}"/>
            <input type="hidden" name="pageNo" class="pageNo" />
            <button class="search_btn">검색</button>
        </div>
    </form>

    <div class="container">
        <c:forEach items="${myAlgorithmList.myAlgorithmList}" var="myAlgo">
            <div class="box">
                <div>제목: ${myAlgo.algorithmQuestionVO.algorithmTitle}</div>
                <div>제출회사: ${myAlgo.memberVO.nickname}</div>
                <p>내용: ${myAlgo.algorithmQuestionVO.algorithmContent}</p>
                <p>조회수: ${myAlgo.algorithmQuestionVO.viewCnt}</p>
                <p>좋아요 수: ${myAlgo.algorithmQuestionVO.likeCnt}</p>
                <p>정답여부: ${myAlgo.correctAnswerYn}</p>
                <p>제출한 답: ${myAlgo.myAnswer}</p>
            </div>
            <a href="/home/myalgorithm/delete/${myAlgo.myAlgorithmQuestionId}">삭제</a>
        </c:forEach>
        <div>
			<div>
				<ul class="page-nav">
				    <c:if test="${not empty myAlgorithmList.myAlgorithmList}">
					    <!-- 이전 페이지 그룹 -->
					    <c:if test="${searchMyAlgorithmVO.hasPrevGroup}">
					        <li>
					            <a href="javascript:void(0)" onclick="movePage(0)">처음</a>
					        </li>
					        <li>
					            <a href="javascript:void(0)" onclick="movePage(${searchMyAlgorithmVO.prevGroupStartPageNo})">이전</a>
					        </li>
					    </c:if>
					    
	                    <c:forEach begin="${searchMyAlgorithmVO.groupStartPageNo}"
	                               end="${searchMyAlgorithmVO.groupEndPageNo}"
	                               step="1"
	                               var="p">
							<li class="${searchMyAlgorithmVO.pageNo eq p ? "active": ""}">
								<a href="javascript:void(0)" onclick="movePage(${p})">${p + 1}</a>
							</li>
	                    </c:forEach>
	                    
	                    <!-- 다음 페이지 그룹 -->
	                    <c:if test="${searchMyAlgorithmVO.hasNextGroup}">
	                        <li>
	                            <a href="javascript:void(0)" onclick="movePage(${searchMyAlgorithmVO.nextGroupStartPageNo})">다음</a>
	                        </li>
	                        <li>
	                            <a href="javascript:void(0)" onclick="movePage(${searchMyAlgorithmVO.pageCount-1})">마지막</a>
	                        </li>
	                    </c:if>
                    </c:if>
				</ul>
			</div>
		</div>

    </div>
    <script>
        $().ready(function() {
            $('#search_btn').click(function() {
                movePage()
            })
        })

        function movePage(pageNo=0) {
            let pageNo=1
            $('.pageNo').val(pageNo)
            $('.search_form').attr({
                'method': 'get',
                'action': '/home/myalgorithm/list'
            }).submit()
        }
    </script>
</body>
</html>