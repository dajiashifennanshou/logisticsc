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
					<td>登录名：<input name="params[username]" type="text"></input></td>
					<td>真是姓名：<input name="realname" type="text"></input></td>
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
	BUI.use(['bui/grid','bui/data'],function(Grid,Data){
		var Grid = Grid,
        Store = Data.Store,
		columns = [
		  	{title : '登录名',dataIndex :'username', width:100},
			{title : '真是姓名',dataIndex :'realname', width:100},
			{title : '状态',dataIndex : 'state',width:200,renderer : function (value,obj) {
        		return "停用";
        	}},
			{title : '操作',dataIndex : 'id',width:200,renderer : function (value,obj) {
	        		return "<a class='page-action' data-type='setTitle' title='编辑' href='${configProps['project']}/system/selectUserById/"+value+".shtml'>编辑</a>  ";
	        	}
	        }
		];

	    var store = new Store({
	        url : "${configProps['project']}/system/sys_user_list.shtml",
	        autoLoad:true, //自动加载数据
	        pageSize:2	// 配置分页数目
	      });
		var grid = new Grid.Grid({
		  render:'#grid',
		  columns : columns,
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