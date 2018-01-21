var grid = null;
var employee_={
	newGird:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcEmployeeList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['姓名','状态','性别','年龄','职位','工作年限','手机号','身份证号','地址','入职时间'],
			colModel:[
				{name:'employeeName',align:'center'},
				{name:'useStatus',align:'center',formatter:function(val, opt, row){
					return Constant.useStatus[val];
				}},
				{name:'gender',width:40,align:'center',formatter:function(val, opt, row){
					return Constant.sex[val];
				}},
				{name:'age',width:40,align:'center'},
				//{name:'jobNumber',align:'center'},
				{name:'postName',align:'center'},
				{name:'term',align:'center'},
				{name:'phone',align:'center'},
				{name:'cardNumber',align:'center'},
				{name:'address',align:'center'},
				{name:'entryTime',align:'center'}
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
				yc_public.ajax({"url":"addYcEmployee.yc",load:true,"data":yc_public.getData(form),"result":"add"});
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
				yc_public.ajax({"url":"modYcEmployee.yc",load:true,"data":yc_public.getData(form),"result":"mod"});
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcEmployeeSingle.yc","data":{"id":id},"success":function(data){
			if(data.code==0){
				yc_public.setData(from, data.data);
			}
		}});
	},//查询职位
	getPost:function(){
		yc_public.ajax({"url":"getPost.yc","success":function(data){
			if(data.code==0){
				$.each(data.data,function(i,item){
					$("select[name=position]").append("<option value='"+item.id+"'>"+item.postName+"</option>");
				});
			}
		}});
	},//条件查询
	condSearch:function(){
		grid.setGridParam({"postData":yc_public.getData("emp_form")}).trigger("reloadGrid");
	}	
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_employee","添加员工信息","toAddEmployee.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_employee","修改员工信息","toModEmployee.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行修改"});
    }
}
//删除
function del(){
    var rowData = grid.getGridParam('selarrrow');
   if(rowData.length==1) {
    	yc_public.confirm({"msg":"确定删除这个员工么？","callback":function(){
    		yc_public.ajax({"data":{"id":rowData.toString()},"url":"delYcEmployee.yc","result":"del"});
    	}});
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行删除"});
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
