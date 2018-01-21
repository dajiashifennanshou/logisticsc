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
	function addFunction(){
		/* $.ajax({
    		type: "GET",
    		url:"${configProps['project']}/system/toAddUserView.shtml",
    		data:"",
    		error: function(request) {
    			alert("连接错误");
    		},
    		success: function(data) {
    			
	      		window.location="${configProps['project']}/system/pageJump.shtml?url="+data.msg;
    		}
    	});  */
		window.location="${configProps['project']}/system/toAddUserView.shtml";
		
		return false;
	}
	
	BUI.use(['bui/grid','bui/data'],function(Grid,Data){
		var Grid = Grid,
        Store = Data.Store,
		columns = [
		  	{title : '登录名',dataIndex :'username', width:100},
			{title : '真实姓名',dataIndex :'realname', width:100},
			{title : '状态',dataIndex : 'adminstatus',width:200,renderer : function (value,obj) {
				if(value==1){
					return "启用";
				}else if(value==0){
					return "禁用";
				}
        	}},
			{title : '操作',dataIndex : 'id',width:200,renderer : function (value,obj) {
	        		return "<a class='page-action' data-type='setTitle' title='编辑' href='${configProps['project']}/system/selectUserById/"+value+".shtml'>编辑</a>  ";
	        	}
	        }
		];

	    var store = new Store({
	        url : "${configProps['project']}/system/sys_user_list.shtml",
	        autoLoad:true, //自动加载数据
	        pageSize:10	// 配置分页数目
	      });
		var grid = new Grid.Grid({
		  render:'#grid',
		  width:'100%',
		  columns : columns,
		  loadMask: true, //加载数据时显示屏蔽层
		  emptyDataTpl : '<div class="centered"><h2>查询的数据不存在</h2></div>',
			tbar:{ //添加、删除
              items : [{
                btnCls : 'button button-small',
                text : '<i class="icon-plus"></i>添加',
                listeners : {
                  'click' : addFunction
                }
              }/* ,
              {
                btnCls : 'button button-small',
                text : '<i class="icon-remove"></i>删除',
                listeners : {
                  'click' : delFunction
                }
              }*/] 
          	},
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