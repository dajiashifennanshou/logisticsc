<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>司机管理</title>
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
           	<h2>司机管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
				       	<input id="condition" name="condition" class="input-large" type="text" placeholder="姓名，手机号，身份证号">
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
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select'],
					function(Grid,Data,Toolbar,Format,Select){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '专线/物流商', elCls:'center',width:200,renderer:function(val,obj){
					if(obj.platformUserCompany){
						return obj.platformUserCompany.companyName;	
					}else{
						return '';
					}
					
				}}, 
				{title : '组织代码', elCls:'center',width:80,renderer:function(val,obj){
					if(obj.platformUserCompany){
						return obj.platformUserCompany.companyCode;	
					}else{
						return '';
					}
					
				}},
				{title : '网点', elCls:'center',width:100,renderer:function(val,obj){
					if(obj.outletsInfo){
						return obj.outletsInfo.name;	
					}else{
						return '';
					}
					
				}},
				{title : '姓名', elCls:'center',dataIndex : 'driverName'},
				{title : '驾龄', elCls:'center',dataIndex : 'driverAge'},
				{title : '手机号', elCls:'center',width:200,dataIndex : 'phoneNumber'},
				{title : '身份证',elCls:'center',width:200,dataIndex :'idCard'},
				{title : '驾驶证类型',elCls:'center',dataIndex :'driverLicenseTypeVal'},
				{title : '驾驶证号', elCls:'center',width:200,dataIndex : 'driverLicenseNo'},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/tms/member/searchDriver.shtml',
				autoLoad:true,
				pageSize:10,
				proxy:{
					method:'post',
				}
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
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
	</script>
</body>
</html>