var grid = null;
//instal类
var deliveryCharge_={
		newGrid:function(tabId,pagerId){
			grid = $(tabId).jqGrid({
				url:"getYcDeliveryChargeList.yc",
				datatype: "json",
				autowidth: true,
				height: '100%',
				colNames:['网点编号','配送费','车型'],
				colModel:[
					/*{name:'id',index:'id',align:'center',sorttype:"int"},*/
					{name:'branchNo',align:'center'},
					{name:'deliveryCost',align:'center'},
					{name:'carType',align:'center'}
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
		},initSelect:function(ele){

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
		//初始化车辆
		initCars:function(ele){
			yc_public.ajax({"url":"getYcCarTypeListBy.yc","success":function(data){
				var html="";
				var cars=data.data;
				for(var i in cars){
					var car=cars[i];
					html+='<option value='+car.id+'>'+car.typeName+'</option>';
				}
				$(ele).html(html);
				deliveryCharge_.initSelect(ele);
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
					yc_public.ajax({"url":"addYcDeliveryCharge.yc","data":yc_public.getData(form),"result":"add"});
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
					yc_public.ajax({"url":"modYcDeliveryCharge.yc","data":yc_public.getData(form),"result":"add"});
				}
				this.isSubmit=true;
			}
		},
		getModData:function(id){
			yc_public.ajax({"url":'getYcDeliveryChargeSingle.yc',data:{"id":id},success:function(data){
				if(data.code==0){
					yc_public.setData("mod_delivery_form",data.data);
				}
			}});
		},
		setSelect:function(seid){
			yc_public.ajax({"url":'getYcDeliveryChargeSingle.yc',data:{"id":id},success:function(data){
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
	window.parent.f_addTab("add_delivery","添加","toAddYcDeliveryChargePage.yc");
}
//前往修改页
function toEdit(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_delivery","修改","toModYcDeliveryChargePage.yc?rowId="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行修改"});
    }
}
//删除
function del(){
	var ids = grid.getGridParam('selarrrow');
    if(ids.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+ids.length+"项么？","callback":function(){
    		yc_public.ajax({"url":"delYcDeliveryCharge.yc","data":{"ids":ids.toString()},"result":"del"});
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
