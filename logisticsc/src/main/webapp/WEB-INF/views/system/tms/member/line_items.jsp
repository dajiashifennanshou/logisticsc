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
           	<h2>线路管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
			        	<div style="display:inline-block">
			        	<label>起始地：</label>
				          <select id="start_province" name="startProvince" class="input-small start_province" onchange="renderSelect(this)"></select>
				          <select id="start_city" name="startCity" class="input-small start_city" onchange="renderSelect(this)"></select>
				          <select id="start_county" name="startCounty" class="input-small start_county"></select>&nbsp;&nbsp;&nbsp;&nbsp;
			        	</div>
			        	<div style="display:inline-block">
			        		<label>目的地：</label>
				          	<select id="end_province" name="endProvince" class="input-small end_province" onchange="renderSelect(this)"></select>
				          	<select id="end_city" name="endCity" class="input-small end_city" onchange="renderSelect(this)" ></select>
				          	<select id="end_county" name="endCounty end_county" class="input-small"></select>&nbsp;&nbsp;&nbsp;&nbsp;
		        		</div>
		        		
				       	<input id="condition" name="condition" type="text" placeholder="专线/物流商">
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
				startProvince:$("#start_province").val(),
				startCity:$("#start_city").val(),
				startCounty:$("#start_county").val(),
				endProvince:$("#end_province").val(),
				endCity:$("#end_city").val(),
				endCounty:$("#end_county").val(),
				condition:$("#condition").val()
			}
			store.load(params);
		}
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/tooltip'],
					function(Grid,Data,Toolbar,Format,Tooltip){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '专线/物流商',elCls:'center',width:200,renderer:function(val,obj){
					var body = '';
					if(obj.platformUserCompany){
						body += obj.platformUserCompany.companyName; 
					}
					return body;
				}},
				{title : '组织代码',elCls:'center',width:100,renderer:function(val,obj){
					var body = '';
					if(obj.platformUserCompany){
						body += obj.platformUserCompany.companyCode; 
					}
					return body;
				}},
				{title : '网点',elCls:'center',width:100,renderer:function(val,obj){
					if(obj.outletsInfo){
						return obj.outletsInfo.name;s 
					}else{
						return '';
					}
					
				}},
				{title : '线路',elCls:'center',width:250,renderer:function(val,obj){
					var body = '';
					if(obj.startOutletsObj){
						body += '从：'+obj.startOutletsObj.provinceValue+'-'+obj.startOutletsObj.cityValue+'-'+obj.startOutletsObj.countyValue+'</br>'
					}
					if(obj.endOutletsObj){
						body += '到：'+obj.endOutletsObj.provinceValue+'-'+obj.endOutletsObj.cityValue+'-'+obj.endOutletsObj.countyValue+'</br>'
					}
					return body;
				}},
				{title : '运输方式',elCls:'center',dataIndex :'transModeValue',},
				{title : '服务类型',elCls:'center',dataIndex :'serverTypeValue'},
				{title : '干线运价',elCls:'center',width:200, renderer:function(val,obj){
					var objStr = BUI.JSON.stringify(obj).replace(/\"/g,"'");
					return '<div style="text-align:left;padding-left:20px;"><div><div>重货价：<span class="grid-command heavy_cargo" data-title="'+objStr+'">￥'+obj.heavyCargoPriceLow+'/吨</span></div><div>泡货价：<span class="grid-command bulky_cargo" data-title="'+objStr+'">￥'
							+obj.bulkyCargoPriceLow+'/立方</span></div><div>最低价：￥'
							+obj.lowestPrice +"/票</div></div></div>";
					
				}},
			 	{title : '时效/时',elCls:'center',dataIndex : 'timeLong'},
			 	{title : '对外发布',elCls:'center', dataIndex : 'releaseState',renderer:function(val){
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
			//不使用模板的，左侧显示
			   var tips = new Tooltip.Tips({
			    	tip : {
				          	trigger : '.heavy_cargo', //出现此样式的元素显示tip
				          	alignType : 'right', //默认方向
				          	elCls : 'panel',
				          	width: 200,
				          	titleTpl : '<div class="panel-body">\
			                        <p><=1：￥{heavyCargoPriceLow}/吨\
			                        <p>1~3：￥{heavyCargoPriceMid}/吨\
			                        <p>>=3：￥{heavyCargoPriceHigh}/吨\
			                      </div>',
			          		offset : 10,
			        	}
			    }).render();
			   var tips = new Tooltip.Tips({
			    	tip : {
				          	trigger : '.bulky_cargo', //出现此样式的元素显示tip
				          	alignType : 'right', //默认方向
				          	elCls : 'panel',
				          	width: 200,
				          	titleTpl : '<div class="panel-body">\
					          		<p><=3：￥{bulkyCargoPriceLow}/立方\
			                        <p>3~10：￥{bulkyCargoPriceMid}/立方\
			                        <p>>=10：￥{bulkyCargoPriceHigh}/立方\
			                      </div>',
			          		offset : 10,
			        	}
			    }).render();
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
		$(function(){
			renderSelect();
		})	
		//加载select
		function renderSelect(obj){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/outlets/ajax/xzqh.shtml',
				data:{pid:$(obj).val()},
				type:'post',
				success:function(result){
					if(result.result){
						var data = result.data;
						if(!obj){
							$(".start_province").append("<option value=''>请选择</option>");
							$(".end_province").append("<option value=''>请选择</option>");
							for(var i = 0;i<data.length;i++){
								$(".start_province").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
								$(".end_province").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
							}
						}else{
							if($(obj).val()!=null && $(obj).val()!=''){
								var nextEle = $(obj).next();						
								nextEle.empty();
								nextEle.next().empty();
								nextEle.append("<option value=''>请选择</option>");
								for(var i = 0;i<data.length;i++){
									nextEle.append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
								}	
							}else{
								var nextEle = $(obj).next();						
								nextEle.empty();
								nextEle.next().empty();
								nextEle.append("<option value=''></option>");
							}
						}
					}
				}
			})
		}
	</script>
</body>
</html>