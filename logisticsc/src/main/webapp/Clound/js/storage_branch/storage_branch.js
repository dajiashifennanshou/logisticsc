var grid = null;
var storageBranch_={
	newGrid:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcStorageBranchList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['网点编号','网点名称','合作形式','仓库面积(M²)','使用面积(M²)','仓库类型','地址','负责人','手机号码'],
			colModel:[
				{name:'branchNo',align:'center',width:"70"},
				{name:'branchName',align:'center'},
				{name:'joinType',align:'center',width:"50",formatter:function(val, opt, row){
					return Constant.StorageBranchJoinType[val];
				}},
				{name:'branchArea',align:'center',width:"70"},
				{name:'useArea',align:'center',width:"70"},
				{name:'branchType',align:'center',width:"70",formatter:function(val, opt, row){
					return Constant.StorageBranchType[val];
				}},
				{name:'',align:'center',formatter:function(val, opt, row){
					if(row.province!=null){
						return row.province+row.city+row.county+row.town;
					}else{return "";}
				}},
				{name:'employeeName',align:'center',width:"50"},
				{name:'phone',align:'center',width:"70"},
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
				yc_public.ajax({"url":"addYcStorageBranch.yc","data":yc_public.getData(form),"result":"add"});
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
				yc_public.ajax({"url":"modYcStorageBranch.yc","data":yc_public.getData(form),"result":"mod"});
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcStorageBranchSingle.yc","data":{"id":id},"success":function(data){
			console.log(data);
			if(data.code==0){
				yc_public.setData(from, data.data);
			}
		}});
	},//获取员工信息
	getEmployee:function(){
		yc_public.ajax({"url":"getEmployee.yc","success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=masterId]").append("<option value='"+item.id+"'>"+item.employeeName+"</option>");
				});
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	},//条件查询
	condSearch:function(){
		grid.setGridParam({"postData":yc_public.getData("sb_form")}).trigger("reloadGrid");
	}
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_storage_branch","添加网点信息","toAddStorageBranch.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_storage_branch","修改网点信息","toModStorageBranch.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行修改"});
    }
}
//删除
function del(){
	var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcStorageBranch.yc","result":"del"});
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
