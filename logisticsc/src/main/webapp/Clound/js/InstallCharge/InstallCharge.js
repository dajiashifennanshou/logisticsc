var grid = null;
//instal类
var installCharge_={
		newGrid:function(tabId,pagerId){
			grid = $(tabId).jqGrid({
				url:"getYcInstallChargeList.yc",
				datatype: "json",
				autowidth: true,
				height: '100%',
				colNames:['安装费','配送费','类型名称'],
				colModel:[
					/*{name:'id',index:'id',align:'center',sorttype:"int"},名称*/
					{name:'installCharge',align:'center'},
					{name:'deliverCharge',align:'center'},
					{name:'towLvName',align:'center'}
				], 
				viewrecords : true,
				page:1,
				rowNum:10,
				rowList:[10,20,30],
				pager : pagerId,
				altRows: true,
				multiselect: true,
				loadComplete : function() {
					var table = this;
					setTimeout(function(){
						pagerIcons(table);
					}, 0);
				}
			});

			$(tabId).jqGrid("navGrid",pagerId,{search: false,view: false,
				add: true,
				addtitle:"添加",
				addfunc:toAdd,
				addicon:"ace-icon fa fa-plus-circle purple",
				edit: true,
				edittitle:"修改",
				editfunc:toEdit,
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
		//上一页
		prevSubmit:function(){
			
			//如果有上一页，才能前往
			if(this.pageNext-1>=1){
				this.pageNext-=1;
				$(".btn-prev").removeAttr("disabled");
				$(".step-pane").removeClass("active");
				$("[data-step="+this.pageNext+"]").addClass("active");
				$(".btn-next").html('下页<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
				//如果已经是第一页了，则将上页按钮不可用
				if(this.pageNext==1){
					$(".btn-prev").attr("disabled","disabled");
				}
				$(".btn-next").removeAttr('disabled');
			}
		},//下一页
		gotoNext:function(){
			//如果只是详情翻页的话，当在最后一页时，就不做任何操作
			if(this.pageSum==this.pageNext)return;
			//+1
			this.pageNext+=1;
			//前往下一页
			$(".step-pane").removeClass("active");
			$("[data-step="+this.pageNext+"]").addClass("active");
			$(".btn-prev").removeAttr("disabled");
			//更改按钮信息
			if(this.pageSum==this.pageNext){
				if(this.isDetail){
					$(".btn-next").html('下页<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
					$(".btn-next").attr('disabled','disabled');
				}else{
					$(".btn-next").html('提交<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
				}
			}
		},
		initSelect:function(ele){

			$(ele).multiselect({
				 enableFiltering: true,
				 enableHTML: true,
				 buttonClass: 'btn btn-white btn-primary',
				 templates: {
					button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
					ul: '<ul class="multiselect-container dropdown-menu"></ul>',
					filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
					filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
					li: '<li><a tabindex="0"><label></label></a></li>',
			        divider: '<li class="multiselect-item divider"></li>',
			        liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
				 }
			});
		},
		initFirstLv:function(ele){
			yc_public.ajax({url:'getYcGoodsTypeListBy.yc',data:{parentSoft:0},success:function(res){
				if(res.code!=0) return;
				var data=res.data;
				var html="";
				for(var i in data){
					var td=data[i];
					html+='<option value='+td.id+'>'+td.softName+'</option>';
				}
				$(ele).html(html);
			}});
			$(ele).change(function(){
				installCharge_.initTwoLv('#twoLvCode');
				installCharge_.initSelect2('#twoLvCode');
			});
		},
		initTwoLv:function(ele){
			var parentId=$('#firstLvCode').val();
			yc_public.ajax({url:'getYcGoodsTypeListBy.yc',data:{parentSoft:parentId},success:function(res){
				if(res.code!=0) return;
				var data=res.data;
				var html="";
				for(var i in data){
					var td=data[i];
					html+='<option value='+td.id+'>'+td.softName+'</option>';
				}
				$(ele).html(html);
			}});
		},
		initSelect2:function(ele,width){
			$(ele).css('width','41.7%').select2({allowClear:true});
		},
		initBranchNo:function(ele){
			yc_public.ajax({url:'getYcStorageBranch.yc',success:function(res){
				if(res.code!=0) return;
				var data=res.data;
				var html='<option value="">请选择网点</option>';
				for(var i in data){
					var td=data[i];
					html+='<option value='+td.branchNo+'>'+td.branchName+'</option>';
				}
				$(ele).html(html);
			}});
		},
		init:function(){
			//div的总数
			this.pageSum=$(".step-pane").length;
		},
		isSubmit:true,
		pageSum:1,
		pageNext:1,
		addSubmit:function(form){
			if(this.isSubmit){
				this.isSubmit=false;
				var _form=$("#"+form);
				//_form.validate();
				var valids=_form.FengValid(this.pageNext);
				if(valids){
					yc_public.ajax({"url":"addYcInstallCharge.yc","data":yc_public.getData(form),"result":"add"});
				}
				this.isSubmit=true;
			}
		},
		modSubmit:function(form){
			if(this.isSubmit){
				this.isSubmit=false;
				var _form=$("#"+form);
				var valids=_form.FengValid(this.pageNext);
				if(valids){
					yc_public.ajax({"url":"modYcInstallCharge.yc","data":yc_public.getData(form),"result":"add"});
				}
				this.isSubmit=true;
			}
		},
		getModData:function(id){
			yc_public.ajax({"url":'getYcInstallChargeSingle.yc',data:{"id":id},success:function(data){
				if(data.code==0){
					yc_public.setData("mod_install_form",data.data);
				}
			}});
		},
		setSelect:function(seid){
			yc_public.ajax({"url":'getYcInstallChargeSingle.yc',data:{"id":id},success:function(data){
				if(data.code==0){
					var cd=data.data;
					var html="";
					for(var i=0;i<cd.length;i++){
						html+='<option value='+i+'>'+i+'</option>';
					}
					$("#"+seid).html(html);
				}
			}});
		}
}
//前往添加页
function toAdd(){
	//window.parent.f_refreshTab("sd");
	window.parent.f_addTab("add_install","添加","toAddYcInstallChargePage.yc");
}
//前往修改页
function toEdit(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		//var Data = grid.getRowData(rowData);
		//获取id
		window.parent.f_addTab("mod_install","修改","toModYcInstallChargePage.yc?rowId="+rowData[0]);
    }else{
    	bootbox.dialog({
			message: "只能选择一行进行修改!", 
			buttons: {
				"success" : {
				"label" : "确定"
				}
			}
    	});
    }
}
//删除
function del(){
	var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
	    	yc_public.ajax({"url":"delYcInstallCharge.yc","data":{"ids":rowData.toString()},"success":function(data){
	    		if(data.code==0){
	    			yc_public.dialog({"msg":"删除成功"});
	    			grid.trigger("reloadGrid");//刷新
	    		}else{
	    			yc_public.dialog({"msg":"删除失败"});
	    		}
	    	}});
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
