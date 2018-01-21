<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>专线商铺管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<style>
   /**内容超出 出现滚动条 **/
   .bui-stdmod-body feedback_dia{
     overflow-x : hidden;
     overflow-y : auto;
   }
   .span8{
   margin-left: 20px;
   }
</style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           <h2>商铺管理</h2>
        </div>
        <div class="panel-body" >
	        <form id="search_form" class="well form-inline" enctype="multipart/form-data">
	        	创建日期：<input id="startTime"  type="text" class="calendar">-
        		<input id="endTime" type="text" class="calendar">
        		用户公司类型：
        		<select id="userType" style="width: 120px" class="form-control fcjselect" name="userType">
	          		<option value="">请选择</option>
	          		<option value="1">普通会员</option>
	          		<option value="2">企业会员</option>
	          		<option value="3">专线</option>
	          		<option value="4">物流提供商</option>
	          		<option value="5">网点管理员</option>
	          	</select>
        		<input type="text" id = "loginName"  style="width: 120px" class="input-normal" name="loginName" placeholder="用户账号">
	           	<input type="text" id = "companyName" style="width: 120px" class="input-normal" name="companyName" placeholder="商铺名称">
	           	<input type="text" id = "companyCode" style="width: 120px" class="input-normal" name="companyCode" placeholder="组织代码">
				<button type="button" id="btnSearch" class="button button-primary" onclick="search()">搜索</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
     <!-- 默认隐藏 -->
    <div id="details_dia" class="hide">
    	<form id="ad_form" class="form-horizontal" enctype="multipart/form-data">
	    	<div class="row detail-row">
	          <div class="span8">
	           <label>公司名称：</label><span class="detail-text" id="companyNameid" ></span> 
	          </div>
	            <div class="span8">
	           <label>公司地址：</label><span class="detail-text" id = "companyAddressid" /></span>
	          </div>
	            <div class="span8">
	          <label>公司电话：</label><span class="detail-text" id ="companyPhoneid" /></span> 
	          </div>
	           <div class="span8"> 
	            <label>公司传真：</label><span class="detail-text" id="companyFaxid" /></span>
	            </div>
	         	<div class="span8">
	          	<label>法定人：</label><span class="detail-text" id ="legalPersonid" /></span>
	        	</div> 
	         	 <div class="span8">
	          	<label>法定人电话：</label><span class="detail-text" id ="legalMobileid"  /></span>
	        	</div> 
	         	<div class="span8">
	            <label>联系 人:</label><span class="detail-text" id ="contactsid" /></span>
	            </div>
	            <div class="span8">
	            <label >联系人电话：</label><span class="detail-text" id ="contactsMobileid" /></span>
	            </div>
	           <div class="span8">   
	        	<label>组织代码：</label><span class="detail-text" id ="companyCodeid" /></span>
	            </div>
	           <!--  <div class="span8"> 
	            <label>营业执照：</label><span class="detail-text" id ="businessLicenseid" /></span>
	           </div> -->
	           <div class="span8"> 
	            <label>税务登记号：</label><span class="detail-text" id = "companyTaxNoid" /></span>
	       		</div>
	           <div class="span8">   
	           <label>财务邮箱：</label><span class="detail-text" id ="financeEmailid" /></span>
	           </div>
	           	<div class="span8">
	           <label>邮政编码：</label><span class="detail-text" id ="postCodeid" /></span>
	       	 	</div> 
	           <div class="span8">
	            <label>QQ：</label><span class="detail-text" id ="QQid" /></span>
	            </div>
	              <div class="span8"> 
	            <label>联系人一：</label><span class="detail-text" id="contacts1id" /></span>
	            </div>
	              <div class="span8"> 
	            <label>联系人一电话：</label><span class="detail-text" id="contacts1Mobileid" /></span>
	            </div>
	               <div class="span8"> 
	            <label>联系人二：</label><span class="detail-text" id="contacts2id" /></span>
	            </div>
	              <div class="span8"> 
	            <label>联系人二电话：</label><span class="detail-text" id="contacts2Mobileid" /></span>
	            </div>
	        </div> 
    	</form>
    </div>
    <script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
<!-- 专线商铺管理 -->
 <script type="text/javascript">
//查询
	function search(){
		var params = {
				companyName:$("#companyName").val(),
				startTime:$("#startTime").val(),
				endTime:$("#endTime").val(),
				companyCode:$("#companyCode").val(),
				loginName:$("#loginName").val(),
				userType:$("#userType").val()
		};
		store.load(params);
	}
	//表格渲染
	var msg;
	BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form','bui/overlay'],
				function(Grid,Data,Toolbar,Format,Form,Overlay){
		var Grid = Grid,
		Store = Data.Store,
		msg = BUI.Message,
		columns = [
			{title : '创建时间',dataIndex :'createTime', width:150,renderer:BUI.Grid.Format.datetimeRenderer},
			{title : '用户账号',dataIndex :'loginName', width:200},
			{title:'公司名称',dataIndex:'',width:200,renderer : function(value,obj){
					if (obj.companyName == null) {
						obj.companyName = "";
				}
				return '<a href="<%=request.getContextPath()%>/system/yypt/info/lineshopment.shtml?id='+obj.id+'">'+obj.companyName+'</a>';
					}
			},
			{title : '公司地址',dataIndex :'companyAddress', width:200},
			{title : '组织代码',dataIndex :'companyCode'},
			{title:'操作',dataIndex:'',width:200,renderer : function(value,obj){
				return '<button  onclick="lineinfobyid('+obj.id+')"  class="button button-primary" >查看详情</button>';
			}
				///* <button  class="button button-primary" onclick="updatestatus('+1+','+obj.id+')">停用</button>&nbsp;&nbsp;&nbsp; */
			}
		];
		store = new Store({
			url: '<%=request.getContextPath()%>/system/yypt/info/listdata.shtml',
			autoLoad:true,
			proxy:{
				method:'post',
			},
			pageSize:10,
		});
		grid = new Grid.Grid({
		   	render:'#render_grid',
			autoRender:true, 
		  	forceFit:true,
			columns : columns,
			store : store,
			loadMask: true,//加载数据时显示屏蔽层
			plugins : [Grid.Plugins.CheckSelection],
		});
		//分页工具条
		var bar = new Toolbar.NumberPagingBar({
	          render : '#pagingbar',
	          autoRender: true,
	          elCls : 'pagination pull-right',
	          store : store,
	    });
	});	
	//日期加载
	BUI.use('bui/calendar',function(Calendar){
     var datepicker = new Calendar.DatePicker({
     	trigger:'.calendar',
     	autoRender : true
 	});
 });
	<%-- //修改0为推荐与1为不推荐
    function updatestatus(recommended,companyId){
    	$.ajax({
			url:'<%=request.getContextPath()%>/system/yypt/info/infoStatus.shtml',
			type:'post',
			data:{recommended:recommended,id:companyId},
			dataType:'json',
			success:function(data){
				if(data.result == true){
					BUI.Message.Alert('操作成功','success');
					store.load();
				}else{
					BUI.Message.Alert('操作成功','error');
					store.load();
				}
			}
		})
    } --%>
	
	//通过id查询
	 function lineinfobyid(id){
			var companyDetailDialog;
		 	BUI.use(['bui/overlay'],function(Overlay){
			 companyDetailDialog = new Overlay.Dialog({
			    	title:'专线商铺详情',
			        width:680,
			        height:360,
			        closeAction : 'destroy', //每次关闭dialog释放
			        contentId:'details_dia',
			        success:function() {
			        	this.close();
			        }
			    });
			 companyDetailDialog.set('effect',{effect : 'fade', duration : 400});
			});
		 	companyDetailDialog.show();
		 	loadCompanyDetail(id);
		}
		function loadCompanyDetail(id) {
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/system/yypt/info/infobyid.shtml',
				data : {'id' : id},
				success : function(result) {
					/* $('#companyNameid').text(result.companyName);
					$('#companyAddressid').text(result.companyAddress);
					$('#QQid').text(result.QQ);
					$('#legalPersonid').text(result.legalPerson );
					$('#legalMobileid').text(result.legalMobile );
					$('#postCodeid').text(result.postCode );
					$('#contactsid').text(result.contacts );
					$('#contactsMobileid').text(result.contactsMobile);
					$('#companyPhoneid').text(result.companyPhone );
					$('#companyFaxid').text(result.companyFax );
					$('#companyTaxNoid').text(result.companyTaxNo);
					$('#businessLicenseid').text(result.businessLicense );
					$('#financeEmailid').text(result.financeEmail );
					$('#contacts1id').text(result.contacts1);
					$('#contacts2id').text(result.contacts2);
					$('#contacts2Mobileid').text(result.contacts2Mobile);
					$('#contacts1Mobileid').text(result.contacts1Mobile); */
					$('#companyNameid').text(result.companyName == null ? "" :result.companyName);
					$('#companyAddressid').text(result.companyAddress == null ? "" :result.companyAddress);
					$('#QQid').text(result.QQ == null ? "" :result.QQ);
					$('#legalPersonid').text(result.legalPerson == null ? "" :result.legalPerson);
					$('#legalMobileid').text(result.legalMobile == null ? "" :result.legalMobile);
					$('#postCodeid').text(result.postCode == null ? "" :result.postCode);
					$('#contactsid').text(result.contacts == null ? "" :result.contacts);
					$('#contactsMobileid').text(result.contactsMobile == null ? "" :result.contactsMobile);
					$('#companyPhoneid').text(result.companyPhone == null ? "" :result.companyPhone);
					$('#companyFaxid').text(result.companyFax == null ? "" :result.companyFax);
					$('#companyTaxNoid').text(result.companyTaxNo== null ? "" :result.companyTaxNo);
					$('#businessLicenseid').text(result.businessLicense == null ? "" :result.businessLicense);
					$('#financeEmailid').text(result.financeEmail == null ? "" :result.financeEmail);
					$('#companyCodeid').text(result.companyCode == null ? "" :result.companyCode);
					$('#contacts1id').text(result.contacts1 == null ? "" :result.contacts1);
					$('#contacts2id').text(result.contacts2 == null ? "" :result.contacts2);
					$('#contacts2Mobileid').text(result.contacts2Mobile == null ? "" :result.contacts2Mobile);
					$('#contacts1Mobileid').text(result.contacts1Mobile == null ? "" :result.contacts1Mobile);
				}
			});
		}
 </script>
</body>
</html>