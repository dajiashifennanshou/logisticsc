<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>保险信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap日期-->
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.js"></script>
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<style type="text/css">
body{ margin:0; padding:0; font-size:12px;  color:#666; background-color:#f2f2f2; overflow-x:hidden }
ul{ margin:0; padding:0; list-style:none;}
a{ color:#777777; text-decoration:none;}
img{ border:none;}
p{ margin:0; padding:0;}
a.m { color:#ababab;}
a:hover { color:#0f9ae2;}
.f {float: left;}
.r { float: right;}

.in-con {
	width: 1170px;
	height: 1278px;
	background: white;
	margin: 0px auto;
	padding-left: 30px;
}
.bd_pp_ul {
	margin-top: 20px;
}
.tb-fon {
	margin-left: 10px;
	line-height: 34px;
	color: rgb(221, 0, 0);
	font-size: 16px;
}
.deop {
	margin-top: 20px;
	margin-right: 20px;
	margin-right:220px;
}
.deop a {
    display: block;
    float: left;
    width: 80px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    color: #FFF;
    background: #a0a0a0;
    font-size: 14px;
    margin-right: 2px;
    margin-top: 10px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
}
.deop a:hover {
    background: #0078ce;
}
.tb_bot .box .input {
    display: block;
    float: left;
    width: 108px;
    height: 32px;
    position: relative;
}
.tb_row2  {
	width: 84%;
	margin-top: 10px;
}
.tb_row2 .input .txt {
    position: absolute;
    left: 7px;
    top: 9px;
    color: #333;
}
.tb_bot .box .lab {
    float: left;
    width: 60px;
    color: #000;
    font-weight: bold;
    font-size: 14px;
    margin-top: 6px;
}
.tb_tit {
    color: #5d86b9;
    font-weight: bold;
    font-size: 20px;
    margin-top: 10px;
}
.irt_tit {
	width:86%; 
	height:40px; 
	line-height:33px; 
	background:url(../img/tb6.gif) repeat-x bottom; 
	font-size:24px; color:#0f9ae2;
}
.tb_row ul li .lab {
    float: left;
    width: 110px;
    margin-top: 26px;
    font-weight: bold;
    line-height: 35px;
}
.tb_row ul li {
    float: left;
    width: 510px;
    height: 65px;
 }
 .tb_row ul li .box {
    float: left;
    width: 340px;
    position: relative;
}
.tb_row ul li .box .x {
    position: absolute;
    left: -5px;
    top: 28px;
    color: #cc0001;
    line-height: 29px;
}
.tb_row ul li .input {
    display: block;
    width: 320px;
    height: 32px;
    position: absolute;
    left: 10px;
    top: 18px;
}
.tb_row ul li .input input:focus {
	border: 1px solid #72a4e0;
    box-shadow: 0 0 5px #c0ccdb;
    outline: medium none;
    transition: box-shadow 0.25s linear 0s;
}
.tb_row ul li .input input {
    position: absolute;
	left: 0px;
	top: 5px;
	width: 290px;
	height: 34px;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	color: #333;
	padding-left: 10px;
}
.tb_row ul li .des {
    position: absolute;
    left: 10px;
    top: 0;
    color: #3cc474;
    line-height: 30px;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		if($("#ins_type").attr("rel") =="sz"){
			$(".sz_x_div").css("display","block");
		}
	})
	
	function downIns(obj){
		var url = $("#hide_file_url").val(),
			status = $("#hide_ins_status").val();
		if(status == 2){
			window.open(url,'_blank');
		}else{
			alert("保单未生效,不能下载保单");
		}
	}
	
</script>
</head>
<body>
<div id="container">
<jsp:include page="../top.jsp"></jsp:include>
	<div class="container-self">
		<div class="frame_left">
			<jsp:include page="peronal_center_left.jsp"></jsp:include>
		</div>
		<div class="demo-content platformStyle">
		<div class="in-con">
			<div class="bd_pp_ul f">
				<span class="f">
					<img src="<%=request.getContextPath()%>/img/retrive.shtml?resource=${platIns.insuranceCompany.insComLogoUrl }" class="f" />
				</span>
				<span class="f tb-fon">
					- <label id="ins_type" rel="${platIns.insType }">
						<c:if test="${platIns.insType == 'jb'}">
								基本险
							</c:if>
							<c:if test="${platIns.insType == 'xh'}">
								鲜活险
							</c:if>
							<c:if test="${platIns.insType == 'ys'}">
								易碎险
							</c:if>
							<c:if test="${platIns.insType == 'zh'}">
								综合险
							</c:if>
							<c:if test="${platIns.insType == 'lc'}">
								冷藏险
							</c:if>
							<c:if test="${platIns.insType == 'sz'}">
								失踪险
							</c:if>
					</label>[国内]
				</span>
			</div>
			<div class="deop r">
				<a href="/logisticsc/insurance/insDetails.shtml?insId=${platIns.id }">刷新</a>
				<a href="/logisticsc/insurance/toInsurance.shtml?insId=${platIns.id }">复制</a>
				<a href="javascript://" onclick="downIns(this);">下载保单</a>
				<a href="#">打印保单</a>
				<input id="hide_file_url" type="hidden" value="${platIns.insFileUrl }"/>
				<input id="hide_ins_status" type="hidden" value="${platIns.insStatus }"/>
			</div>
			<div class="tb_bot tb_row2 f">
				<p class="box" style="margin-left:0; margin-right:0;">
	            	<span class="lab" style="width:70px">提交时间：</span>
	                <span class="input" style="width: 163px;background:none;">
	                	<span class="txt">
	                	<fmt:formatDate value="${platIns.insCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                	</span>
	                </span>
	            </p>
				<p class="box" style=" margin-left:0; width:auto;">
	            	<span class="lab" style="width:70px;">投保单号：</span>
	                <span class="input" style="width:165px; background:none;"><span class="txt">${platIns.insOrderNum }</span></span>
	            </p>
	            <p class="box" style=" margin-left:0; width:auto;">
	            	<span class="lab" style="width:56px">保单号：</span>
	                <span class="input" style="width:165px; background:none;"><span class="txt">${platIns.insLastBaodan }</span></span>
	            </p>
	            <p class="box" style=" margin-left:0; width:auto;">
	            	<span class="lab" style="width:70px">保单状态：</span>
	                <span class="input" style="width:160px; background:none;">
	                	<span class="txt" style=" color:#454545 ">
						<c:if test="${platIns.insStatus == 0 }">
							已保存
						</c:if>
						<c:if test="${platIns.insStatus == 1 }">
							审核中
						</c:if>
						<c:if test="${platIns.insStatus == 2 }">
							已生效
						</c:if>
						<c:if test="${platIns.insStatus == 3 }">
							已作废
						</c:if>
						</span>
	                </span>
	            </p>
	            <p class="box" style="margin-left:0; width:auto;">
	            	<span class="lab" style="width:70px">保单保费：</span>
	                <span class="input" style="width:162px; background:none;"><span class="txt">${platIns.insFee }</span></span>
	            </p>
	            <!--<p class="box" style=" color:#474747; font-weight:bold;margin-left:0; margin-right:0;  width:auto;">
	            	<span class="lab" style="width:70px">是否收款：</span>
	                <span class="input" style="float:left; width:165px; background:none;">
	                	<span class="txt" style="font-weight:normal">已收</span>
	                </span>
	            </p>-->
	            <p class="box" style="width:auto; margin:0;">
	            	<span class="lab" style="width:70px">保单下载：</span>
	                <span class="input" style=" background:none; width:220px;">
	                	<span class="txt" id="bx_file_html">
	                		<c:if test="${empty platIns.insFileUrl }">
	                			<span id="bx_file_name">未上传</span>
	                		</c:if>
	                        &nbsp;
							<span style="display: none;" id="bx_file_del"></span>
	                    </span>
	                </span>
	            </p>
			</div>
			<div class="tb-bot f">
				<div class="irt_tit tb_tit">被保险人信息</div>
		        <div class="tb_row">
		        	<ul>
		            	<li style="margin-left:0;">
		                	<p class="lab">被保险人/单位：</p>
		                    <p class="box">
		                        <span class="input">
		                        	<input type="text"  maxlength="50" value="${platIns.insTrueName }" disabled/>
		                    </p>
		                </li>
		                <li>
		                	<p class="lab">联系电话：</p>
		                    <p class="box">
		                        <span class="input"><input type="text" name="bx_tel" id="bx_tel" value="${platIns.insTel }" maxlength="15" disabled/></span>
		                    </p>
		                </li>
		                <li style="margin-left:0;">
		                	<p class="lab">被保险人证件号：</p>
		                    <p class="box">
		                        <span class="input"><input type="text" name="bx_cardnum" id="bx_cardnum" maxlength="30" value="${platIns.insCardNum }" disabled/></span>
		                    </p>
		                </li>
		                <li>
		                	<p class="lab">联系地址：</p>
		                    <p class="box">
		                    	<span class="x"></span>
		                        <span class="input"><input type="text" name="bx_address" id="bx_address" maxlength="100" value="${platIns.insAddress }" disabled/></span>
		                        <span class="des"></span>
		                    </p>
		                </li>
		                <div style="clear:both;"></div>
		            </ul>
		        </div>
		        <div class="irt_tit tb_tit">车辆信息</div>
			        <div class="tb_row">
			        	<ul>
			            	<li style="margin-left:0;">
			                	<p class="lab">船/航/车牌号：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_chenum" id="bx_chenum" maxlength="100" value="${platIns.insCheNum }" disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">合同发票号：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insHeTongNum }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">起运时间：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_starttime" id="bx_starttime" maxlength="30" value='<fmt:formatDate value="${platIns.insStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">运输方式：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insTransType }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                 <li style="margin-left:0;">
			                	<p class="lab">装载方式：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_starttime" id="bx_starttime" maxlength="30" value="${platIns.insLoadType }"  disabled/></span>
			                    </p>
			                </li>
			                <div style="clear:both;"></div>
			            </ul>
			        </div>
			        <div class="irt_tit tb_tit sz_x_div" style="display:none">失踪险补充信息</div>
			        <div class="tb_row sz_x_div" style="display:none">
			        	<ul>
			            	<li style="margin-left:0;">
			                	<p class="lab">驾驶员姓名：：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_chenum" id="bx_chenum" maxlength="100" value="${platIns.insJsy }" disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">驾驶员手机号：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insJsySj }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">驾驶员身份证号：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_starttime" id="bx_starttime" maxlength="30" value="${platIns.insJsySfz }"  disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">行驶证号：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insXsNum }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                 <li style="margin-left:0;">
			                	<p class="lab">营运证号：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_starttime" id="bx_starttime" maxlength="30" value="${platIns.insYyNum }"  disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">发动机号：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insFdjNum }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                 <li style="margin-left:0;">
			                	<p class="lab">车架号：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_starttime" id="bx_starttime" maxlength="30" value="${platIns.insCjNum }" disabled /></span>
			                    </p>
			                </li>
			                <!-- <li>
			                	<p class="lab">运单号：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li> -->
			                <div style="clear:both;"></div>
			            </ul>
			        </div>
			         <div class="irt_tit tb_tit">货物信息</div>
			        <div class="tb_row">
			        	<ul>
			            	<li style="margin-left:0;">
			                	<p class="lab">货物分类：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_chenum" id="bx_chenum" maxlength="100" value="${platIns.insGoodsType }" disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">包装方式：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insBaoZhuang }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">货物名称：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_starttime" id="bx_starttime" maxlength="30" value="${platIns.insHuoWu }"  disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">货物数量：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insHuowuNum }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                 <li style="margin-left:0;">
			                	<p class="lab">保险金额：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_starttime" id="bx_starttime" maxlength="30" value="${platIns.insJine }"  disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">特殊类型：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input">
			                        	<c:if test="${empty platIns.insTsType }">
			                        		<input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="无" disabled/>
			                        	</c:if>
			                        	<c:if test="${platIns.insTsType == 'es'}">
			                        		<input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="二手货物" disabled/>
			                        	</c:if>
			                        	<c:if test="${platIns.insTsType == 'spc'}">
			                        		<input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="商品车" disabled/>
			                        	</c:if>
			                        	<c:if test="${platIns.insTsType == 'xz'}">
			                        		<input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="西藏地区" disabled/>
			                        	</c:if>
			                        </span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			              
			                <li>
			                	<p class="lab">起运地：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insStartAdd }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">到达地：</p>
			                    <p class="box">
			                        <span class="input"><input type="text" name="bx_starttime" id="bx_starttime" maxlength="30" value="${platIns.insEndAdd }"  disabled/></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">中转地：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input type="text" name="bx_hetongnum" id="bx_hetongnum" maxlength="50" value="${platIns.insMidAdd }" disabled/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <div style="clear:both;"></div>
			            </ul>
			        </div>
		    </div>
		</div>
    <!--右侧结束-->
    </div>   
</div>
<!--中间结束-->
</div>


<!-- <script>
	var lH=$("#left").height();
	var rH=$("#right").height()+63;
	var cH=0;
	if(lH<rH){
		$("#left").css('height',rH+40);
		cH=rH+146;
	}else{
		
		$("#right").css('height',lH-90);
		cH=lH+106;
	}
	$("#center").css("height",cH);
	
</script>

<div id="bigbg" style="width:100%; height:auto; position:fixed; z-index:1000; top:0; left:0; background:#474747;filter:alpha(opacity=70);-moz-opacity:0.7;-khtml-opacity: 0.7;opacity: 0.7; display:none;"></div>
<div id="alertBox" style=" width:612px; height:auto; position:absolute; z-index:1010; top:0; left:0; display:none;">
	<p style="width:100%; height:51px; line-height:51px; text-align:center; font-weight:bold; font-size:18px; color:#FFF; background:url(images/tb27.png) no-repeat;" id="alertName"></p>
    <div style="width:100%; height:auto; background:#FFF;" id="alertMainBox">
    	
        
    </div>
</div>





<script> -->
 <div style="clear: both;"></div>
 <div class="footer">	 
	<jsp:include page="../bottom.jsp"></jsp:include>
</div>	
</body>
</html>