<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>基础数据管理</title>
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
           	<h2>基础数据管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
			        	<label>选择区域：</label>
				      	<select id="province" class="input-small" name="province"><option>省份</option></select>&nbsp;&nbsp;
				      	<select class="input-small" name="city"><option>城市</option></select>&nbsp;&nbsp;
				       	<select class="input-small" name="county"><option>区</option></select>
				       	<input name="content" type="text" placeholder="专线">
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
				startTime:$("#start_time").val(),
				endTime:$("#end_time").val(),
				condition:$("#condition").val()
			}
			store.load(params);
		}
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format'],
					function(Grid,Data,Toolbar,Format){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '线路',renderer:function(val,obj){
					return '从：'+obj.startOutletsName+'</br>到：'+obj.endOutletsName
				}},
				{title : '专线',renderer:function(val,obj){
					return obj.platformUserCompany.companyName;
				}},
				{title : '网点',renderer:function(val,obj){
					return obj.outletsInfo.name;
				}},
				{title : '运输方式',dataIndex :'transModeValue',},
				{title : '服务类型',dataIndex :'serverType'},
				{title : '包提货',dataIndex :'isTakeCargo',renderer:function(val){
					return val==0?"否":"是";
				}},
				{title : '包送货',dataIndex :'isGiveCargo',renderer:function(val){
					return val==0?"否":"是";
				}},
				{title : '干线运价', renderer:function(val,obj){
					return "重货价："+obj.heavyCargoPrice+"</br>泡货价："+obj.bulkyCargoPrice+"</br>最低价："+obj.lowestPrice;
				}},
			 	{title : '时效/时',dataIndex : 'timeLong'},
			 	{title : '对外发布', dataIndex : 'releaseState',renderer:function(val){
			 		if(val==0){
			 			return "否";
			 		}else if(val==1){
			 			return "是";
			 		}
			 	}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/tms/member/searchLine.shtml',
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
	</script>
</body>
</html>