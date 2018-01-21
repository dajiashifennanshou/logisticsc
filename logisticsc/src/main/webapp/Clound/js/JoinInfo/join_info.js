var grid = null;
//车辆管理
//
//formatter:function(val,opt,row){
//	var name = "";
//	if(row.joinType==0){//经销商
//		yc_public.ajax({"url":"getYcDealerSingle.yc","data":{"id":row.joinerId},"success":function(data){
//			if(data.code==0){
//				name = data.data.dealerName;
//			}
//		}});
//	}else{
//		//专线
//		yc_public.ajax({"url":"getYcSpecialSingle.yc","data":{"id":row.joinerId},"success":function(data){
//			if(data.code==0){
//				name = data.data.companyName;
//			}
//		}});
//	}
//	return name;
//}
var join_={
	newGrid:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcJoinInfoList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['加盟商','加盟商类型','申请人姓名','申请人电话','网点编号','库区编号','使用时间(月)','使用时间起','使用时间截','申请状态'],
			colModel:[
				{name:'joinName',align:'center'},
				{name:'joinType',align:'center',formatter:function(val,opt,row){
					return Constant.JoinType[val];
				}},
				{name:'joiner',align:'center'},
				{name:'joinPhone',align:'center'},
				{name:'branchNo',align:'center'},
				{name:'zoneNo',align:'center'},
				{name:'days',align:'center'},
				{name:'startUseTime',align:'center',formatter:function(val,opt,row){
					if(val!=null){
						return DateUtil.RemoveTime(val);
					}else{
						return "";
					}
				}},
				{name:'endUseTime',align:'center',formatter:function(val,opt,row){
					if(val!=null){
						return DateUtil.RemoveTime(val);
					}else{
						return "";
					}
				}},
				{name:'applyStatus',align:'center',formatter:function(val,opt,row){
					if(val==0){
						return "<span style='cursor: pointer;' class='label label-info arrowed' onclick='spanToMod("+row.id+");'>待审核</span>";
					}
					else if(val==1){return "<span class='label label-success arrowed'>已通过</span>";}
					else{return "";}
				}},
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
			edittitle:"审核",
			editfunc:toMod,
			editicon:"ace-icon fa fa-arrow-up bule",
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
			var valids=_form.FengValid();
			if(valids){
				yc_public.ajax({"url":"addYcJoinInfo.yc","data":yc_public.getData(form),"result":"add"});
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
				yc_public.ajax({"url":"modYcJoinInfo.yc","data":yc_public.getData(form),"result":"mod"});
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcJoinInfoSingle.yc","data":{"id":id},"success":function(data){
			if(data.code==0){
				join_.getStorageZone(data.data.branchNo);
				yc_public.setData(from, data.data);
				yc_public.ajax({"url":"getYcDealerSingle.yc","data":{"id":data.data.joinerId},"success":function(data){
					if(data.code==0){
						$("input[name=joinerId]").val(data.data.id);
						$("input[name=joinerName]").val(data.data.dealerName);
					}
				}});
			}
		}});
	},//获取云仓网点信息
	getYcStorageBranch:function(){
		yc_public.ajax({"url":"getYcStorageBranch.yc","success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=branchNo]").append("<option value='"+item.branchNo+"'>"+item.branchName+"</option>");
				});
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	},//获取库区信息
	getStorageZone:function(storageNo){
		$("#select2-chosen-6").html("");//清空
		$("select[name=zoneNo]").empty();//清空 
		if(storageNo!=null){
			yc_public.ajax({"url":"getStorageZone.yc","data":{"storageNo":storageNo},"success":function(data){
				if(data.code==0){
					$.each(data.data, function(i, item){
						$("select[name=zoneNo]").append("<option value='"+item.zoneNo+"'>"+item.zoneNo+"</option>");
					});
				}else{
					yc_public.dialog(data.msg)
				}
			}});
		}
	},//条件查询
	condSearch:function(){
		grid.setGridParam({"postData":yc_public.getData("join_form")}).trigger("reloadGrid");
	},//查询申请人（根据加盟商类型查询）集合查询专线/经销商
	getUnRegister:function(type){
		$("#select2-chosen-2").html("");//清空
		$("select[name=joinerId]").empty();//清空 
		if(type==0){
			//经销商
			yc_public.ajax({"url":"getUnRegisterDealer.yc","success":function(data){
				console.log(data);
				$.each(data.data,function(i,item){
					$("select[name=joinerId]").append("<option value='"+item.id+"'>"+item.dealerName+"</option>");
				});
			}});
		}else{
			//专线
			yc_public.ajax({"url":"getUnRegisterSpecial.yc","success":function(data){
				console.log(data);
				$.each(data.data,function(i,item){
					$("select[name=joinerId]").append("<option value='"+item.id+"'>"+item.companyName+"</option>");
				});
			}});
		}
	}
	
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_join","添加加盟商信息","toAddJoin.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_join","修改加盟商信息","toModJoin.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行修改"});
    }
}

//前往修改页
function spanToMod(id){
	window.parent.f_addTab("mod_join","修改加盟商信息","toModJoin.yc?id="+id);
}

//删除
function del(){
	var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcJoinInfo.yc","result":"del"});
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
