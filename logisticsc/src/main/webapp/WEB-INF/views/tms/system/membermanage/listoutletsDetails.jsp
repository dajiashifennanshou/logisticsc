<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员管理</title>
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
           	<h2>网点信息</h2>
        </div>
        <div class="panel-body">
	    	<form action="" class="form-horizontal">
	    		<div class="row">
					<div class="control-group span8">
				   		<label class="control-label">网点名称：</label>
						<div class="controls">
							<span class="control-text">${outlets.name }</span>
				       	</div>
				    </div>
			        <div class="control-group span8">
			          	<label class="control-label">网点编号：</label>
			          	<div class="controls">
			          		<span class="control-text">${outlets.outletsNumber }</span>
			        	</div>
			        </div>
			        <div class="control-group span8">
			          	<label class="control-label">网点类型：</label>
			          	<div class="controls">
			          		<span class="control-text">${outlets.typeValue }</span>
			        	</div>
			        </div>
			    </div>
			    <div class="row">
					<div class="control-group span8">
				   		<label class="control-label">网点性质：</label>
						<div class="controls">
							<span class="control-text">${outlets.natureValue }</span>
				       	</div>
				    </div>
			        <div class="control-group span8">
			          	<label class="control-label">区域：</label>
			          	<div class="controls">
			          		<span class="control-text">${outlets.provinceValue }&nbsp;&nbsp;${outlets.cityValue }&nbsp;&nbsp;${outlets.countyValue}</span>
			        	</div>
			        </div>
			        <div class="control-group span8">
			          	<label class="control-label">详细地址：</label>
			          	<div class="controls">
			          		<span class="control-text">${outlets.address }</span>
			        	</div>
			        </div>
			    </div>
			    <div class="row">
					<div class="control-group span8">
				   		<label class="control-label">联系人：</label>
						<div class="controls">
							<span class="control-text">${outlets.contactPerson }</span>
				       	</div>
				    </div>
			        <div class="control-group span8">
			          	<label class="control-label">手机号：</label>
			          	<div class="controls">
			          		<span class="control-text">${outlets.phone }</span>
			        	</div>
			        </div>
			        <div class="control-group span8">
			          	<label class="control-label">固定电话：</label>
			          	<div class="controls">
			          		<span class="control-text">${outlets.mobile }</span>
			        	</div>
			        </div>
			    </div>
			     <div class="row">
					<div class="control-group span8">
				   		<label class="control-label">邮箱地址：</label>
						<div class="controls">
							<span class="control-text">${outlets.email }</span>
				       	</div>
				    </div>
			    </div>
			</form>
       	</div>
       	<div class="panel-header">
           	<h2>线路信息</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_line_grid"></div>
	       	<div id="pagingbar_line"></div>
       	</div>
       	<div class="panel-header">
           	<h2>车辆信息</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_vehicle_grid"></div>
	       	<div id="pagingbar_vehicle"></div>
       	</div>
       	<div class="panel-header">
           	<h2>司机信息</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_driver_grid"></div>
	       	<div id="pagingbar_driver"></div>
       	</div>
       	<div class="panel-header">
           	<h2>客户信息</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_customer_grid"></div>
	       	<div id="pagingbar_customer"></div>
       	</div>
       	<div class="panel-header">
           	<h2>提送货信息</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
       	<div class="panel-header">
           	<h2>自定义面单信息</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
       	<div class="panel-header">
           	<h2>基本配置信息</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
       	<div class="panel-header">
           	<h2>短信模板信息</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select','bui/form'],
					function(Grid,Data,Toolbar,Format,Select,Form){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '发站网点', dataIndex : 'startOutletsName'},
				{title : '到站网点', dataIndex : 'endOutletsName'},
				{title : '运输方式',dataIndex :'transModeValue',},
				{title : '服务类型',dataIndex :'serverType'},
				{title : '重货价/元', dataIndex : 'heavyCargoPrice'},
				{title : '泡货价/元', dataIndex : 'bulkyCargoPrice'},
				{title : '最低价/元', dataIndex : 'lowestPrice'},
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
				url: '<%=request.getContextPath()%>/tms/sys/outlets/detail/gtlnnf.shtml?outletsId='+'${outletsId}',
				autoLoad:true,
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_line_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
			});
			
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar_line',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });
		});	
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select','bui/form'],
					function(Grid,Data,Toolbar,Format,Select,Form){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
						{title : '车牌号', dataIndex : 'plateNumber'},
						{title : '车型', dataIndex : 'vehicleTypeVal'},
						{title : '车长', dataIndex : 'vehicleLongVal'},
						{title : '核载重量',dataIndex :'vehicleWeight',},
						{title : '核载体积',dataIndex :'vehicleVolume'},
						{title : '生成时间',dataIndex :'createTime'}
					];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/sys/outlets/detail/gtvhclnf.shtml?outletsId='+'${outletsId}',
				autoLoad:true,
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_vehicle_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
			});
			
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar_vehicle',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });
		});
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select','bui/form'],
					function(Grid,Data,Toolbar,Format,Select,Form){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '姓名', dataIndex : 'driverName'},
				{title : '手机号', dataIndex : 'phoneNumber'},
				{title : '身份证',dataIndex :'idCard'},
				{title : '驾驶证类型',dataIndex :'driverLicenseTypeVal'},
				{title : '驾驶证号', dataIndex : 'driverLicenseNo'},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/sys/outlets/detail/gtdrvrnf.shtml?outletsId='+'${outletsId}',
				autoLoad:true,
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_driver_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
			});
			
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar_driver',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });
		});
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select','bui/form'],
					function(Grid,Data,Toolbar,Format,Select,Form){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '姓名', dataIndex : 'customerName'},
				{title : '手机号', dataIndex : 'phone'},
				{title : '固定电话',dataIndex :'mobile'},
				{title : '详细地址',dataIndex :'address'},
				{title : '公司名称', dataIndex : 'companyName'}
			],
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/sys/outlets/detail/gtcstmrnf.shtml?outletsId='+'${outletsId}',
				autoLoad:true,
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_customer_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
			});
			
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar_customer',
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