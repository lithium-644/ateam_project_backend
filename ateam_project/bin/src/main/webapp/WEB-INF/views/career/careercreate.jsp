<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>근무 정보 입력</title>
    <!-- jQuery와 jQuery UI 스타일시트를 포함 -->
    <script src="/js/lib/jquery-3.7.1.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- jQuery UI DatePicker를 한국어로 설정 -->
   <script type="text/javascript">
    $().ready(function() {
        $.datepicker.regional['ko'] = {
            closeText: '닫기',
            prevText: '이전달',
            nextText: '다음달',
            currentText: '오늘',
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
            dayNames: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            weekHeader: '주',
            dateFormat: 'yy-mm-dd',
            firstDay: 0,
            isRTL: false,
            showMonthAfterYear: true,
            yearSuffix: '년'
        };
        $.datepicker.setDefaults($.datepicker.regional['ko']);
        
        // DatePicker를 사용할 input 필드에 클래스 추가
        $(".date-picker").datepicker({
            changeYear: true,
            showButtonPanel: true,
            yearRange: "1900:+0",
            dateFormat: "yy-mm-dd"
        });
        
        // 팝업 창을 생성할 버튼 클릭 시 동작
        $("#openPopupButton").click(function() {
            $("#popup").dialog({
                width: 400,
                modal: true
            });
        });
        
        // 입력한 입사일과 퇴사일을 비교하여 이전 날짜인지 확인
        $("#hireDate, #resignDate").change(function() {
            var hireDate = $("#hireDate").datepicker("getDate");
            var resignDate = $("#resignDate").datepicker("getDate");
            
            if (hireDate && resignDate && hireDate > resignDate) {
                alert("입사일이 퇴사일보다 이전일 수 없습니다.");
                // 입사일을 초기화 또는 수정할 수 있도록 처리
                $("#hireDate").val("");
            }
        });
    });
</script>
</head>
<body>
    <form method="post">
        <div>
            <label for="previousCompanyName">근무 회사명:</label>
            <input type="text" id="previousCompanyName" name="previousCompanyName" required>
        </div>
        <div>
            <label for="jobTitle">직무명:</label>
            <input type="text" id="jobTitle" name="jobTitle" required>
        </div>
        <div>
            <label for="hireDate">입사일:</label>
            <input type="text" id="hireDate" name="hireDate" class="date-picker" placeholder="YYYY-MM-DD" required>
        </div>
        <div>
            <label for="resignDate">퇴사일:</label>
            <input type="text" id="resignDate" name="resignDate" class="date-picker" placeholder="YYYY-MM-DD">
        </div>
        <input type="submit" value="저장">
    </form>
</body>
</html>