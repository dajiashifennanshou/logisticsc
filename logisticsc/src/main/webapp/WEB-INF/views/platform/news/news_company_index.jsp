<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司新闻首页</title>
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<script src="/logisticsc/resources/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
</head>
<style type="text/css">
html,body,h1,h2,h3,h4,h5,h6,div,dl,dt,dd,ul,ol,li,p,blockquote,pre,hr,figure,table,caption,th,td,form,fieldset,legend,input,button,textarea,menu {
	margin: 0;
	padding: 0;
}
header,footer,section,article,aside,nav,hgroup,address,figure,figcaption,menu,details {
	display: block;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
caption,
th {
	text-align: left;
	font-weight: normal;
}
html,body,fieldset,img,iframe,abbr {
	border: 0;
}
i,cite,em,var,address,dfn {
	font-style: normal;
}
[hidefocus],summary {
	outline: 0;
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
.news-banner {
	width: 100%;
	height: 247px;
	position: relative;
	overflow: hidden;
}
.news-banner  img {
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -960px;
	margin-top: -124px;
}



.news-con {
	width: 1200px;
	margin: 0px auto;
	margin-top: 20px;
	/*background: #f2f2f2;*/
	
}
.news-con table{
	width: 100%;
	
}
.news-con table tr td {
	float: left;
	height: 284px;
	width: 32%;
	margin-right: 1%;
	outline: 1px solid #c6cdd5;
	margin-bottom: 20px;
}
.news-img {
	width: 384px;
	height: 200px;
	
}
.news-img img  {
	width: 100%;
	height: 100%;
}
.new-tit {
	color: #000000;
	font-size: 16px;
	display: inline-block;
	width: 80%;
	overflow: hidden;
	white-space:nowrap;
	text-overflow:ellipsis;
	margin-top: 20px;
	margin-left: 20px;
	color: #6f9fd9;
	cursor: pointer;
}
.new-tit a{
	text-decoration: none;
	font-size: 16px;
	font-weight: bold;
}
.new-tit a:hover {
	color: #8bc34a;
	text-decoration: underline;
}
.news-time {
	margin-left: 20px;
	margin-top: 5px;
	color: #999;
	font-size: 14px;
}
.news-nr {
	display: inline-block;
	width: 80%;
	height: 36px;
	overflow: hidden;
	/*white-space:nowrap;*/
	text-overflow:ellipsis;
	margin-top: 8px;
	margin-left: 20px;
	font-size: 14px;
	color: #999;
}
</style>
<script type="text/javascript">
$(function(){
	getNewsList();
});
function getNewsList() {
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1){
		pageindex=1;
	}
	var Conditions = {
			"newsType" : 0,
			"limit":9,
			"pageIndex":pageindex
		};
	$.ajax({
        url : "/logisticsc/homeCenter/getNewsList.shtml",
        type : 'POST',
        data:Conditions,
        dataType : 'json',
        success:function(data){
            if (data.result) {
            	var date = data.data.params.rows;
            	var page = data.data;
            	var body="<tr>";
            	for(var i=0; i<date.length; i++) {
            		var a =1+i;
            		body+="<td>"
            			+"<div class='news-img'>"
            			+"<a href='/logisticsc/homeCenter/getNewsInfo.shtml?id="+date[i].id+"'><img src='/logisticsc/img/retrive.shtml?resource="+date[i].titlePicture+"'/></a>"
            			+"</div>"
            			+"<div class='new-tit'>"
            			+"<a href='/logisticsc/homeCenter/getNewsInfo.shtml?id="+date[i].id+"'>"+date[i].newsTitle+"</a>"
            			+"</div>"
            			+"<div class='news-time'>"+formartDate(date[i].newsTime)+"</div>"
            			+"</td>"
            	}
            	body+="</tr>"
            	 $("#getNewsList").html(body);
            	var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageNewsList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getNewsList);
            }
        },
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html("系统错误");
		}	
    });
}
//时间戳转换
function formartDate(data){
	var date="";
	if(null==data){
		return date;
	}else{
		date=new Date(data).toLocaleString();
		return	date;
	}
}
</script>
<body>
<jsp:include page="../top.jsp"></jsp:include>
	<div class="new-banner">
			<img src="/logisticsc/resources/platform/img/news-banner.jpg" />
		</div>
		<div class="news-con">
			<table id="getNewsList">
			</table>
			<div id="pageNewsList">
			</div>
		</div>
<div id="errorModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: red !important;">操作失败！</h4>
                    <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: red !important;font-weight:bold">错误提示:<span id="errorModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
    <div style="clear: both;"></div>		
<jsp:include page="../bottom.jsp"></jsp:include>
</body>
</html>