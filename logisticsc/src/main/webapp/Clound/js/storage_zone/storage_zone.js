var grid = null;
//库区管理
var storageZone_={
	newGrid:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcStorageZoneList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['网点名称','库区编号','库区状态','库区面积(M²)','备注'],
			colModel:[
				{name:'branchName',align:'center'},
				{name:'zoneNo', align:'center'},
				{name:'zoneStatus',align:'center',formatter:function(val,opt,row){
					return Constant.StorageZoneStatus[val];
				}},
				{name:'zoneArea',align:'center'},
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
				yc_public.ajax({"url":"addYcStorageZone.yc","data":yc_public.getData(form),"result":"add"});
			}
			this.isSubmit=true;
		}
	},
	modSubmit:function(form){
		if(this.isSubmit){
			this.isSubmit=false;
			var _form=$("#"+form);
			//表单验证
			var valids=_form.FengValid();
			if(valids){
				yc_public.ajax({"url":"modYcStorageZone.yc","data":yc_public.getData(form),"result":"mod"});
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcStorageZoneSingle.yc","data":{"id":id},"success":function(data){
			if(data.code==0){
				yc_public.setData(from, data.data);
			}
		}});
	},//获取云仓网点信息
	getYcStorageBranch:function(){
		yc_public.ajax({"url":"getYcStorageBranch.yc","success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=storageNo]").append("<option value='"+item.branchNo+"'>"+item.branchName+"</option>");
				});
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	},//条件查询
	condSearch:function(){
		grid.setGridParam({"postData":yc_public.getData("sz_form")}).trigger("reloadGrid");
	}
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_storage_zone","添加库区信息","toAddStorageZone.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_storage_zone","修改网点信息","toModStorageZone.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行删除"});
    }
}
//删除
function del(){
    var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcStorageZone.yc","result":"del"});
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
