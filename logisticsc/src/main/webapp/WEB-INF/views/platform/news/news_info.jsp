<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻详情</title>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<style type="text/css">
	html,body,h1,h2,h3,h4,h5,h6,div,dl,dt,dd,ul,ol,li,p,blockquote,pre,hr,figure,table,caption,th,td,form,fieldset,legend,input,button,textarea,menu {
		margin: 0;
		padding: 0;
	}
	li {
		list-style: none;
	}
	* {
		font-family: "Microsoft YaHei", "Helvetica Neue", Helvetica, Arial, sans-serif; 
	}
	.f {
		float: left;
	}
	.clr
	{
		clear: both;
	}
	.news-cont {
		width: 1200px;
		margin: 0px auto;
		display: block;
	}
	.news-tit h1 {
		text-align: center;
	}
	.news-con1 {
		margin-top: 20px;
		line-height: 30px;
		font-size: 14px;
		color: #868686;
	}
	.news-con1 img {
		display: block;
	}
</style>
</head>
<body>
	<jsp:include page="../top.jsp"></jsp:include>
	<div class="news-cont">
		<div class="news-tit">
			<h1>${news.newsTitle}</h1>
		</div>
		<div class="news-con1">
			${news.newsContent}
		</div>
	</div>
	<jsp:include page="../bottom.jsp"></jsp:include>
</body>
</html>