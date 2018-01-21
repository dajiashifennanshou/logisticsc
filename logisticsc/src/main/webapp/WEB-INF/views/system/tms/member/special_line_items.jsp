<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>普通专线管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body feedback_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>普通专线管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
				       	<input id="condition" name="condition" type="text" placeholder="公司名称，登陆账号">
			        	<button type="button" class="button button-normal" onclick="search()">查询</button>
			        </li>
	      		</ul>
	        </form>
	     	<div id="render_grid">
	  
	       	</div>
	       	<div id="pagingbar">
	       	
	       	</div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params={
				condition:$("#condition").val()
			}
			store.load(params);
		}
		
		var store;
		
		var message;
        BUI.use('bui/overlay',function(overlay){
           message = BUI.Message;
          
        });
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format'],
					function(Grid,Data,Toolbar,Format){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '公司名称',elCls:'center',dataIndex:'companyName'},
				{title : '组织代码',elCls:'center',dataIndex:'companyCode'},
				{title : '登陆账号',elCls:'center',dataIndex:'tmsLoginName'},
				{title : '法定人',elCls:'center',dataIndex :'legalPerson',},
				{title : '法定人电话',elCls:'center',dataIndex :'legalMobile'},
				{title : '公司地址',elCls:'center',dataIndex :'companyAddress'},
				{title : '联系人1',elCls:'center',dataIndex :'contacts1'},
				{title : '联系人1电话',elCls:'center',dataIndex :'contactsMobile1'},
				{title : '联系人2',elCls:'center',dataIndex :'contacts2'},
				{title : '联系人2电话',elCls:'center',dataIndex :'contacts2Mobile'},
				{title : '状态',elCls:'center',dataIndex :'tmsUserStatus',renderer:function(val){
					if(val==0){
						return '已禁用';
					}else if(val==1){
						return '已启用';
					}
				}},
				{title : '操作',elCls:'center',dataIndex :'tmsUserStatus',renderer:function(val,obj){
					var ennable = '',
						disEnnable = '';
					if(val==0){
						ennable += '<a href="javascript:void(0)" onclick="enable('+obj.tmsLoginName+')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;';
						disEnnable += '<span>禁用</span>';
					}else if(val==1){
						ennable += '<span>启用</span>&nbsp;&nbsp;&nbsp;&nbsp;';
						disEnnable += '<a href="javascript:void(0)" onclick="forbid('+obj.tmsLoginName+')">禁用</a>';
					}
					return ennable + disEnnable;
				}},
				
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/tms/member/searchLineCompany.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
				columns : columns,
				store : store,
				loadMask: true,
			});
			
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });
		});	
		
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
		
		
		
		function forbid(loginName){
			$.ajax({
				url:'<%=request.getContextPath()%>/sys/tms/member/disable.shtml',
				type:'post',
				data:{loginName:loginName},
				success:function(result){
					if(result.result){
						message.Alert("状态修改成功",function(){
							store.load();
						},'success');
					}
				}
			})
		}
		
		function enable(loginName){
			$.ajax({
				url:'<%=request.getContextPath()%>/sys/tms/member/enable.shtml',
				type:'post',
				data:{loginName:loginName},
				success:function(result){
					if(result.result){
						message.Alert("状态修改成功",function(){
							store.load();
						},'success');
					}
				}
			})
		}
	</script>
</body>
</html>