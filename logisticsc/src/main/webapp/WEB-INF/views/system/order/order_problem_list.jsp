<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>发货订单排名</title>
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
					<td>真是姓名：<input name="params[realname]" type="text"></input></td>
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
		  	{title : '订单编号',dataIndex :'true_name', width:100},
			{title : '发货人',dataIndex :'num', width:100},
			{title : '承运公司',dataIndex :'company_name', width:200},
			{title : '异常类型',dataIndex :'price', width:100},
			{title : '查看',dataIndex : 'id',width:100,renderer : function (value,obj) {
	        		return "<a class='page-action' data-type='setTitle' title='编辑' href='${configProps['project']}/system/selectUserById/"+value+".shtml'>编辑</a>  ";
	        	}
	        }
		];

	    var store = new Store({
	        url : "${configProps['project']}/systemOrder/getOrderUserRanking.shtml",
	        autoLoad:true, //自动加载数据
	        proxy : { //设置起始页码
	        	  method : 'post',
	              pageStart : 1
	            },
	        pageSize:4	// 配置分页数目
	      });
		var grid = new Grid.Grid({
		  render:'#grid',
		  columns : columns,
		  forceFit: true,
		  loadMask: true, //加载数据时显示屏蔽层
		  store: store,
		  plugins : [Grid.Plugins.RowNumber,Grid.Plugins.AutoFit],  // 插件形式引入自适应宽度
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