<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<link href="${configProps['resources']}/platform/css/my-insurance.css" rel="stylesheet">
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap日期-->
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap-datetimepicker.min.css" rel="stylesheet">

<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.js"></script>
<!-- bootstrap日期中文-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
</head>
<script>
var com_tag = '',
	type_tag = '',
	status;
$(function(){
	//初始加载数据
	search();
	//渲染时间插件
	$("#start_time,#end_time").datetimepicker({
		format: 'yyyy-mm-dd',
		language: 'zh-CN', 
		minView:2,
		autoclose:true //选择日期后自动关闭 
	});
	//默认查询条件勾选"所有"
	$(".bd_pp_ul li").find(".tb[rel='']").addClass('hover');
	
	//点击保险品牌
	$("#bd_pp_ul li").click(function(){
        var insComTag=$(this).find('.tb').attr('rel');
        com_tag = insComTag;
        $("#bd_pp_ul li span").removeClass("hover");
        var find = ".tb[rel='"+insComTag+"']";
        $(this).find(find).addClass('hover');
        search();
    })
    
    //点击保险险种
	$("#bd_xz_ul li").click(function(){
        var insType=$(this).find('.tb').attr('rel');
        type_tag = insType;
        $("#bd_xz_ul li span").removeClass("hover");
        var find = ".tb[rel='"+insType+"']";
        $(this).find(find).addClass('hover');
	    search();
	    })
    //点击保险状态
	$("#bd_statu_ul li").click(function(){
        var insStatus=$(this).find('.tb').attr('rel');
        status = insStatus;
        $("#bd_statu_ul li span").removeClass("hover");
        var find = ".tb[rel='"+insStatus+"']";
        $(this).find(find).addClass('hover');
	    search();
    })
})

//跳转保单详情
function jmpDetails(val){
	window.location.href="/logisticsc/insurance/insDetails.shtml?insId="+val;
}

//查询
function search(){
	var condition = $("#condition").val(),
	startTime = $("#start_time").val(),
	endTime = $("#end_time").val(),
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1 ||pageindex == undefined){
		pageindex=1;
	};
	var tbData = {
			insComTag:com_tag,
			insType:type_tag,
			insStatus:status,
			condition:condition,
			startTime:startTime,
			endTime:endTime,
			pageIndex:pageindex
	}
	pageindex = $("#videoDiv").parent().find(".currentPage").val();
	if(pageindex<1 ||pageindex == undefined){
		pageindex=1;
	}
	$.ajax({
		url:'/logisticsc/insurance/search.shtml',
		type:'post',
		data:tbData,
		success:function(result){
			var rows = result.data.params.rows;
			var page = result.data;
				body = '';
			if(result.result){
				if(null == rows || '' == rows || 'undefined' == rows || rows.length <= 0) {
					body+="<tr>"
						+"<td style='color:red;' colspan='16' >没有数据</td>"
						+"</tr>";
				}else{
					for (var i = 0; i < rows.length; i++) {
						var insType = '',
							insStatus = '',
							insLastBaoDan = '';
						if(rows[i].insType == 'jb'){
							insType = "基本险";
						}else if(rows[i].insType == 'xh'){
							insType = "鲜活险";
						}else if(rows[i].insType == 'ys'){
							insType = "易碎险";
						}else if(rows[i].insType == 'zh'){
							insType = "综合险";
						}else if(rows[i].insType == 'lc'){
							insType = "冷藏险";
						}else if(rows[i].insType == 'sz'){
							insType = "失踪险";
						}
						if(rows[i].insStatus == 0){
							insStatus = "已保存";
						}else if(rows[i].insStatus ==1 ){
							insStatus  = "审核中";
						}else if(rows[i].insStatus ==2 ){
							insStatus  = "已生效";
						}else if(rows[i].insStatus ==3 ){
							insStatus  = "已作废";
						}
						if(rows[i].insLastBaodan == null || rows[i].insLastBaodan == ''){
							insLastBaoDan = '~';
						}else{
							insLastBaoDan = rows[i].insLastBaodan;
						}
						var zf = "";
						if(rows[i].insStatus == 0){
							zf += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void()' onclick='payIns("+rows[i].insFee+",\""+rows[i].insOrderNum+"\")'>支付</a>";
						}
						body+="<tr>"
							   +"<td>"+insType+"</td>"
							   +"<td>"+insLastBaoDan+"</td>"
							   +"<td>"+rows[i].insTrueName+"</td>"
							   +"<td>"+rows[i].insHuoWu+"</td>"
							   +"<td>"+rows[i].insJine+"</td>"
							   +"<td>"+rows[i].insEndAdd+"</td>"
							   +"<td>"+insStatus+"</td>"
							   +"<td><a href='javascript:void()' onclick='jmpDetails("+rows[i].id+")'>详情</a>"+zf+"</td>"
							   +"</tr>";
					}
				}
				$("#getListorderBill").html(body);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='videoDiv' class='page'></div></div>";
				$("#pageList").html(foot);
				setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,search);
			}
		}
	})
}
</script>
<body>
<jsp:include page="../top.jsp"></jsp:include>
<div id="#container">
<div class="container-self">
	
	<div class="frame_left">
		<jsp:include page="peronal_center_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle">
		 <!--右侧开始-->
        <div class="bd_pp">
            <p class="lab">保险品牌：</p>
            <ul class="bd_pp_ul" id="bd_pp_ul">
				<li><span class="tb" rel=""></span><span style="margin-top:10px;">所有</span></li>
				<c:forEach var="insBrand" items="${insurance }">
					<li><span class="tb" rel="${insBrand.insComTag }"></span><span><img src="<%=request.getContextPath()%>/img/retrive.shtml?resource=${insBrand.insComLogoUrl }" width="66" height="35" alt="" /></span></li>
				</c:forEach>
            </ul>
        </div>
        <div class="bd_pp bd_xz">
            <p class="lab">险种：</p>
            <ul class="bd_pp_ul bd_xz_ul" id="bd_xz_ul">
            	<li><span class="tb" rel=""></span><span>所有</span></li>
				<c:forEach var="insType" items="${insTypeList }">
					<li><span class="tb" rel="${insType.insTypeTag }"></span><span>${insType.insTypeName }</span></li>
				</c:forEach>
            </ul>
        </div>
        <div class="bd_pp bd_xz">
            <p class="lab">保单状态：</p>
            <ul class="bd_pp_ul bd_xz_ul" id="bd_statu_ul">
                <li><span class="tb" rel=""></span><span>所有</span></li>
                <c:forEach var="insStatus" items="${insuranceStatus }">
					<label>
						<li><span class="tb" rel="${insStatus.key }"></span><span>${insStatus.value }</span></li>
					</label>
				</c:forEach>
            </ul>
        </div>
        <div class="bd_pp bd_search">
            <p class="lab" style="margin-left:0; width:100px;">输入关键字：</p>
            <p class="box"><input  id="condition" type="text" name="keyword" id="keyword" value=""  /></p>
            <p class="lab">起始时间：</p>
            <p class="box"><input  id="start_time" type="text" name="startdate"/></p>
            <p class="lab">截止时间：</p>
            <p class="box"><input  id="end_time" type="text" name="endTime"/></p>
            <p style="float:left; margin-top:2px; margin-left:40px;" onclick="search()"><a href="javascript:void(0)"  class="subbtn" style=" width:80px">搜索</a></p>
            <p style="float:left; margin-top:2px; margin-left:10px;"><a href="op.php?action=exportIns&&bx_type=ys&bx_status=-2&bx_company=taipingyang" target="_blank" class="subbtn" onclick="return exportIns();">数据导出</a></p>
        </div>
        <p class="jg20"></p>
        <div style="margin-top:10px;">
			<table  id="t_ins " class="table table-striped dingdan-zt">
				<thead>
					<tr>
					  <th width="10%">险种</th>
					  <th width="8%">保单号</th>
					  <th width="8%">被保险人</th>
					  <th width="15%">货物信息</th>
					  <th width="12%">保额</th>
					  <th width="8%">到达地</th>
					  <th width="5%">状态</th>
					  <th width="8%">操作</th>
					</tr>
				</thead>
				<tbody id="getListorderBill">
				</tbody>
			</table>
			<div id="pageList">
			</div>
		</div>
	</div> 
	<div id="yunChongzhi" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="yun_chongzhi" target="_blank" action="/logisticsc/orderCenter/doOrderMoney.shtml" method="post">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">支付</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" style="margin-top: 28px;">
								<label class="control-label col-sm-4">订单类型：</label>
								<div class="col-sm-8">
									<label class="login-sjh" id=""></label>
									<input type="text" readonly="readonly" style="display:inline-block;width:250px" id="dingdanleixing" class="form-control required"/>
									<input type="text" id="orderType" name="orderType" style="display: none;">
								</div>
						</div>		
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">支付金额：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="yun_moneyMsg"></label>
								<input type="text" readonly="readonly" name="amount" id="zhifujine" style="display:inline-block;width:250px" class="form-control required"/>
								<input type="text" id="zhifuOrderNumber" name="orderNumber" style="display: none;">
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><input type="radio" style="width:10px;height:7px;" checked="checked">易宝支付：</label>
							<div class="col-sm-8"  style="padding-top: 8px;">
								<label class="login-sjh" id="yun_moneyMsg"></label>
								<span style="font-size: 14px;">(通过易宝支付完成网银支付)</span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<!-- <button type="button" class="btn btn-success btn-save" id="zhifu">确认支付</button> -->
						<input type="submit" class="btn-qr" value="确认支付" onclick="wancheng()"/>
					</div>
			</form>
		</div>
	</div>
</div>	
<div id="querenModal" class="modal  fade" tabindex="-1" data-backdrop="static">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-header modal-info modal-success">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">登录网上银行支付</h4>
           		<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="shuaxin()">×</span></a>
             </div>
             <div class="modal-body success-info">
                 <h5 style="color: #6f9fd9!important;font-weight:bold">温馨提示:<span id="querenMsg">请您在新打开的网上银行页面进行支付，支付完成前请不要关闭该窗口</span></h5>
             </div>
            <div class="modal-footer">
             	<button type='button' class='btn btn-success' style='margin-top:5px;' onclick="shuaxin()">完成支付</button>
             </div>
         </div>
     </div>
 </div>
<script>
    function goSearch(){
        var keyword=$("#keyword").val();
        var startdate=$("#startdate").val();
        var enddate=$("#enddate").val();
        if(startdate!='' && enddate!=''){
            var d1 = new Date(startdate.replace(/\-/g, "\/"));
            var d2 = new Date(enddate.replace(/\-/g, "\/"));
            if(d1>d2){
                alert('截止时间不能早于起始时间');
                return false;
            }
        }
        location.href='?bx_company=taipingyang&bx_type=ys&bx_status=-2&keyword='+keyword+'&startdate='+startdate+'&enddate='+enddate;
    }
    function insPrint(status){
        if(status!='4'){
            alert('保单未生效，禁止打印');
            return false;
        }
        /*var isExists='y';
         if(isExists=='y'){
         //window.open('print_'+company+'.php?id='+id,'newwindow', ' width=1020, scrollbars=yes, toolbar=no, menubar=no, top=0,left=0 ');
         parent.location.href='print_'+company+'.php?id='+id;
         }else{
         alert('打印模板不存在！');
         }*/
    }
    function exportIns(){
        var startdate=$("#startdate").val();
        var enddate=$("#enddate").val();
        if(startdate=='' || enddate==''){
            alert('请选择起始时间和截至时间！');
            return false;
        }
        if(!confirm('每次最多导出1000条记录，确认导出？')){
            return false;
        }

    }
    
    function payIns(insFee,orderNumber){
		
		$("#zhifujine").val(insFee);
		$("#dingdanleixing").val("保险费");
		$("#zhifuOrderNumber").val(orderNumber);
		$("#orderType").val(0);
		$("#yunChongzhi").modal({show : true});
	}
    
  //支付确认提示框
	function wancheng() {
		$("#yunChongzhi").modal('hide');
		$("#querenModal").modal('show');
	}
  
	//确认支付刷新跳转
	function shuaxin() {
		window.location.href="/logisticsc/orderCenter/toorderlistpage.shtml";
	}
</script>
</div>
</div>
<div class="footer">
<div style="clear: both;"></div>
<jsp:include page="../bottom.jsp"></jsp:include>
</div>
</body>
</html>