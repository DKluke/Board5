<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<style>
   input:not(input[type=submit]) { width:100%; }
   input[type=submit] { width : 100px; }
   #goList  { width : 80px; }
   
   td { 
      padding:10px;
      width: 700px;
      text-align: center;
   }
   td:nth-of-type(1) {
      width : 200px;
   }
   td:nth-of-type(2) {
      text-align : left;
   }
   
   td:not([colspan]):first-child {
      background: black;
      color : white;
      font-weight: bold;
   }
   
   input[readonly] {
   background: #A0A0A0;
   }

</style>
</head>
<body>
  <main>
	<h2>사용자 상세 정보</h2>
	<table>
	<c:forEach items="${boardView}" var="board">
	 <tr>
	   <td>제목</td>
	   <td>${ board.title }</td>
	 </tr>
	 <tr>
	   <td>작성자</td>
	   <td>${ board.writer }</td>
	 </tr>
	 <tr>
	   <td>내용</td>
	   <td>${ board.content }</td>
	 </tr>
	</c:forEach> 
	
	</table>	
	
  </main>
  
  <script>
  	const  goListEl  = document.getElementById('goList');
  	goListEl.addEventListener('click', function(e) {
  		location.href = '/Board/List';
  	})
  
  </script>
  
</body>
</html>

