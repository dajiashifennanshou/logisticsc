<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>货运交易系统-后台管理系统</title>
<style type="text/css">

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
</head>
<body>
	<!-- grid -->
	<div>
		<form id="searchForm" action="">
			<table class="table-query" >
				<tr >
					<td>订单编号：<input style="width: 100px" name="condition" type="text"></input></td>
					<td>开始日期：<input name="startTime" style="width: 80px" readonly="readonly" type="text" class="calendar" /></td>
					<td>结束日期：<input name="endTime" style="width: 80px" readonly="readonly" type="text" class="calendar" /></td>
					<td><button  type="submit" id="btnSearch" class="button button-primary">搜索</button></td>
				</tr>
				
			</table>
		</form>
		<div class="demo-content">
		       <div id="grid">
		         
		       </div>
		</div>
    </div>
	<script type="text/javascript">
	
		function getEvaluate(id){
			
			window.location.href = "${configProps['project']}/systemOrder/doGetEvaluationReply.shtml?id="+id;
	//		window.location.href = "javascript:$.post('${configProps['project']}/systemOrder/doGetEvaluationReply.shtml',{id:1})";
			
			/* $.ajax({
				url:"${configProps['project']}/systemOrder/doGetEvaluationReply.shtml",
                type : 'POST',
                cache : false,
                data : {'id':id},
                success:function(data){
                	if (data.result) {
                    	BUI.Message.Alert('修改成功','success');
                    	store.load();
                    } else {
                    	BUI.Message.Alert('修改失败','error');
                    }
                }
            }); */
		
			return false;
		}
	
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	          trigger:'.calendar',
	          autoRender : true
	        });
	      });	
	
		BUI.use(['bui/grid','bui/data'],function(Grid,Data){
			var Grid = Grid,
	        Store = Data.Store,
			columns = [
			  	{title : '运单号',dataIndex :'wayBillNumber', width:15},
				{title : '评论时间',dataIndex :'evaluateTimeStr', width:12},
				{title : '订单编号',dataIndex :'orderNumber', width:12},
				{title : '物流商',dataIndex :'companyName', width:10},
				{title : '物流商/线路',dataIndex : 'companyName',width:30,renderer : function (value,obj) {
	        		return "从："+obj.startProvince+"-"+obj.startCity+"-"+obj.startCounty
	        				+"<br/>到："
	        				+obj.endProvince+"-"+obj.endCity+"-"+obj.endCounty;
	        	}},
				{title : '订单状态',dataIndex : 'state',width:8,renderer : function (value,obj) {
	        		return "停用";  
	        	}}, 
				{title : '操作',dataIndex : 'id',width:12,renderer : function (value,obj) {
		        		//return "<a class='page-action' data-type='setTitle' href=\"javascript:$.post('${configProps['project']}/systemOrder/doGetEvaluationReply.shtml',{id:1})\">处理</a>  ";
		        		return "<a href='#' onclick='getEvaluate("+obj.evaId+")'>处理</a>  ";
		        	}
		        }
			];
	
		    var store = new Store({
		        url : "${configProps['project']}/systemOrder/getExceptionOrderList.shtml",
		        autoLoad:true, //自动加载数据
		        proxy : { //设置起始页码
		        	  method : 'post',
		              pageStart : 1
		            },
		        pageSize:10	// 配置分页数目
		      });
			var grid = new Grid.Grid({
			  render:'#grid',
			  columns : columns,
			  forceFit: true,
			  loadMask: true, //加载数据时显示屏蔽层
			  store: store,
			  // 底部工具栏
			  bbar:{
			      // pagingBar:表明包含分页栏
			      pagingBar:true
			  }
			});
			
		    grid.render();
			//创建表单，表单中的日历，不需要单独初始化
			var form = new BUI.Form.HForm({
				srcNode : '#searchForm'
			}).render();
	
			form.on('beforesubmit', function(ev) {
				//序列化成对象
				var obj = form.serializeToObject();
				obj.start = 0; //返回第一页
				store.load(obj);
				return false;
			});
	  	});
    </script>
</body>