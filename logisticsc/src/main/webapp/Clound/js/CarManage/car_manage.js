var grid = null;
//车辆管理
var car_={
	newGrid:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcCarManageList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['车牌号','车辆状态','车型','载重量(㎏)','荷载体积(m³)','司机','备注'],
			colModel:[
				{name:'carNo',align:'center'},
				{name:'carStatus',align:'center',formatter:function(val,opt,row){
					return Constant.carStatus[val];
				}},
				{name:'carTypeName',align:'center'},
				{name:'weight',align:'center'},
				{name:'volume',align:'center'},
				{name:'dreverName',align:'center'},
				{name:'remark',align:'center'}
			], 
			viewrecords : true,
			page:1,
			rowNum:10,
			rowList:[10,20,30],
			pager : pageId,
			altRows: true,
			multiselect: true,
			loadComplete : function() {
				var table = this;
				setTimeout(function(){
					pagerIcons(table);
				}, 0);
			}
		});
		$(tabId).jqGrid("navGrid",pageId,{search: false,view: false,
			add: true,
			addtitle:"添加",
			addfunc:toAdd,
			addicon:"ace-icon fa fa-plus-circle purple",
			edit: true,
			edittitle:"修改",
			editfunc:toMod,
			editicon:"ace-icon fa fa-pencil blue",
			del: true,
			deltitle:"删除",
			delfunc:del,
			delicon:"ace-icon fa fa-trash-o red",
			refresh: true,
			refreshtitle:"刷新",
			refreshicon:"ace-icon fa fa-refresh green"
		});
	},
	isSubmit:true,
	addSubmit:function(form){
		if(this.isSubmit){
			this.isSubmit=false;
			var _form=$("#"+form);
			//表单验证
			var valids=_form.FengValid();
			if(valids){
				yc_public.ajax({"url":"addYcCarManage.yc","data":yc_public.getData(form),"result":"add"});
			}
			this.isSubmit=true;
		}
	},
	//初始化车辆
	initCars:function(ele){
		yc_public.ajax({"url":"getYcCarTypeListBy.yc","success":function(data){
			var cars=data.data;
			for(var i in cars){
				var car=cars[i];
				$(ele).append('<option value='+car.id+'>'+car.typeName+'</option>');
			}
			deliveryCharge_.initSelect(ele);
		}});
	},
	modSubmit:function(form){
		if(this.isSubmit){
			this.isSubmit=false;
			var _form=$("#"+form);
			//表单验证
			var valids=_form.FengValid();
			if(valids){
				yc_public.ajax({"url":"modYcCarManage.yc","data":yc_public.getData(form),"result":"mod"});
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcCarManageSingle.yc","data":{"id":id},"success":function(data){
			if(data.code==0){
				yc_public.setData(from, data.data);
				var empId = data.data.driverId.split(",");
				$("select[name=driverId]").val(empId)
			}
		}});
	},//获取司机信息
	getEmployee:function(){
		yc_public.ajax({"url":"getEmployee.yc","data":{"position":5},"success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=driverId]").append("<option value='"+item.id+"'>"+item.employeeName+"</option>");
				});
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	},//条件查询
	condSearch:function(){
		grid.setGridParam({"postData":yc_public.getData("car_form")}).trigger("reloadGrid");
	}
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_car","添加车辆信息","toAddCar.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		var data=grid.getRowData(rowData);
		if(data.carStatus=='正在使用'){
			yc_public.dialog({"msg":"车辆在使用中禁止修改"});
		}else{
			window.parent.f_addTab("mod_car","修改车辆信息","toModCar.yc?id="+rowData[0]);
		}
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行修改"});
    }
}
//删除
function del(){
    var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcCarManage.yc","result":"del"});
    	}});
    }else{
    	yc_public.dialog({"msg":"至少选择一行进行删除"});
    }
}
//分页样式
function pagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}
