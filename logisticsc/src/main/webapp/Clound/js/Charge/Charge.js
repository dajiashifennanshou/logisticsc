var grid = null;
//instal类
var deliveryCharge_={
		newGrid:function(tabId,pagerId){
			grid = $(tabId).jqGrid({
				url:"getInstallerCharge.yc",
				datatype: "json",
				autowidth: true,
				height: '100%',
				colNames:['网点编号','配送单号','配送费用','配送司机','安装费用','安装工','已支付金额','代收费用','总金额'],
				colModel:[
					/*{name:'id',index:'id',align:'center',sorttype:"int"},*/
					{name:'branchNo',align:'center'},
					{name:'deliveryNo',align:'center'},
					{name:'deliveryCost',align:'center'},
					{name:'driver',align:'center',formatter:function(val,opt,row){
						var result=null;
						yc_public.ajax({url:"getDriverInfo.yc",data:{deliveryNo:row.deliveryNo},success:function(data){
							var drivers=data.data;
							var driver=new Array();
							for(var i in drivers){
								driver.push(drivers[i].employeeName);
							}
							result= driver.toString() || '未找到';
						}});
						return result;
					}},
					{name:'installCost',align:'center'},
					{name:'installer',align:'center',formatter:function(val,opt,row){
						var result=null;
						yc_public.ajax({url:"getInstallerInfo.yc",data:{deliveryNo:row.deliveryNo},success:function(data){
							var installers=data.data;
							var installer=new Array();
							for(var i in installers){
								installer.push(installers[i].employeeName);
							}
							result= installer.toString() || '未找到';
						}});
						return result;
					}},
					{name:'paidCost',align:'center'},
					{name:'agentPaidCost',align:'center'},
					{name:'sumCost',align:'center'}
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
				add: false,
				addtitle:"添加",
				addfunc:toAdd,
				addicon:"ace-icon fa fa-plus-circle purple",
				edit: false,
				edittitle:"修改",
				editfunc:toEdit,
				editicon:"ace-icon fa fa-pencil blue",
				del: false,
				deltitle:"删除",
				delfunc:del,
				delicon:"ace-icon fa fa-trash-o red",
				refresh: true,
				refreshtitle:"刷新",
				refreshicon:"ace-icon fa fa-refresh green"
			});
		},
		init:function(){
			//div的总数
			this.pageSum=$(".step-pane").length;
			if(this.pageSum==1){
				$(".btn-next").html('提交<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
			}
		},
		initSelect:function(selector){
			$(selector).css('width','200px').select2({allowClear:true});
		},
		initList:function(){
			//列表初始化
			var demo1 = $('select[id="goodsId"]').bootstrapDualListbox({infoTextFiltered: '<span class="label label-purple label-lg">检索完成</span>'});
			var container1 = demo1.bootstrapDualListbox('getContainer');
			container1.find('.btn').addClass('btn-white btn-info btn-bold');
		},
		initGoods:function(selector){
			yc_public.ajax({url:"",success:function(data){
				var goods=data.data;
				var html="";
				for(var i in goods){
					var good=goods[i];
					html+='<option value='+good.id+'>'+good.goodsName+"重量："+good.weight+"体积："+goods.volume+'</option>';
				}
				$(selector).html(html);
			}});
		},
		initDate:function(){
			$('[name=subscribeTime]').datetimepicker({
				 icons: {
					time: 'fa fa-clock-o',
					date: 'fa fa-calendar',
					up: 'fa fa-chevron-up',
					down: 'fa fa-chevron-down',
					previous: 'fa fa-chevron-left',
					next: 'fa fa-chevron-right',
					today: 'fa fa-arrows ',
					clear: 'fa fa-trash',
					close: 'fa fa-times'
				 }
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
		},
		goodsArray:null,
		getAppiontData:function(obj,id){
			for(var o in obj){
				if(obj[o].id==id){
					return obj[o];
				}
			}
		},gotoNext:function(){
			$(".step-pane").removeClass("active");
			$("[data-step="+this.pageNext+"]").addClass("active");
			$(".btn-prev").removeAttr("disabled");
			if(this.pageSum==this.pageNext){
				$(".btn-next").html('提交<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
			}
		},
		selectNum_html:'<div class="col-xs-12 col-sm-4">{name}&nbsp;&nbsp;&nbsp;&nbsp;<select goodsNum="goodsNum" group="val" name="goods_{id}"  class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">{option}</select></div>',
		isSubmit:true,
		pageSum:1,
		pageNext:1,
		delivery_val:null,
		/**
		 * 选择货物的件数
		 */
		selectGnum:function(){
			//选择货物的件数
			var deliveryVal=$("#goodsId").val();
			if(yc_public.EqualsObjec(this.deliver_val,deliveryVal)){
				return;
			}
			this.deliver_val=deliveryVal;
			//selctNumHmtl
			var html="";
			var hiddenHtml='<input type="hidden" group="val" name="goodsIds" value="'+deliveryVal.toString()+'" />';
			
			for(var i in deliveryVal){
				//id集合
				var t=deliveryVal[i];
				//获取指定id的货物对象select_num_
				var tVal=this.getAppiontData(this.goodsArray,t);
				if(!tVal) return;
				var optionsHtml="";
				for(var j=1;j<=tVal.goodsNum*1;j++){
					optionsHtml+='<option value='+j+'>'+j+'件</option>';
				}
				html+=this.selectNum_html.replace(/{name}/, tVal.goodsName).replace(/{id}/, tVal.id).replace(/{option}/, optionsHtml);
			}
			//将生产好的html代码设置到页面中
			$("#select_num_").html(html);
			$("#select_num_").append(hiddenHtml);
			//初始化select框
			this.initSelect('[goodsNum="goodsNum"]');
		},
		joiner_id:null,
		getGoodsInfo:function(selector){
			var joinerId=$("#joinerId").val();
			if(this.joiner_id==joinerId){
				return;
			}
			this.joiner_id=joinerId;
			yc_public.ajax({url:"getGoodsByDealerId.yc",data:{"joinerId":joinerId,"branchNo":'YC-59'},success:function(data){
				var goods=data.data;
				if(!goods){
					yc_public.alert({msg:"此经销商未有货物.."});
					deliveryCharge_.initList();
					return;
				}
				deliveryCharge_.goodsArray=goods;
				//goods数据的html
				var html="";
				for(var i in goods){
					var good=goods[i];
					html+='<option value="'+good.id+'">'+good.goodsName+'</option>';
				}
				$(selector).html(html);
				deliveryCharge_.initList();
			}});
		},
		addSubmit:function(form){
			//验证重复提交
			if(this.isSubmit){
				this.isSubmit=false;
				var _form=$("#"+form);
				//_form.validate();
				var valids=_form.FengValid(this.pageNext);
				//return;
				//验证通过才能进行下一页或者提交
				if(valids){
					//下一个div
					this.pageNext+=1;
					//当前往第二页即选择货物数量时
					if(this.pageNext==3){
						//界面初始化构造
						this.selectGnum();
					}
					if(this.pageNext==2){
						//构造货物数据
						this.getGoodsInfo("#goodsId");
					}
					if(this.pageNext>this.pageSum){
						yc_public.confirm({msg:"请确认信息..",callback:function(){
							yc_public.ajax({"url":"addYcDeliveryOrder.yc","data":yc_public.getData(form),"result":"add"});
						}});
					}else{
						this.gotoNext();
					}
				}
				this.isSubmit=true;
			}
		},
		modSubmit:function(form){
			if(this.isSubmit){
				this.isSubmit=false;
				var _form=$("#"+form);
				//_form.validate();
				var valids=_form.FengValid(this.pageNext);
				//验证通过才能进行下一页或者提交
				if(valids){
					//当前往第二页即选择货物数量时
					//下一个div
					this.pageNext+=1;
					if(this.pageNext==3){
						//界面初始化构造
						this.selectGnum();
					}
					if(this.pageNext==2){
						//构造货物数据
						this.getGoodsInfo("#goodsId");
					}
					if(this.pageNext>this.pageSum){
						yc_public.confirm({msg:"请确认信息..",callback:function(){
							yc_public.ajax({"url":"modYcDeliveryOrder.yc","data":yc_public.getData(form),"result":"add"});
						}});
					}else{
						this.gotoNext();
					}
				}
				this.isSubmit=true;
			}
		},
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
			}
		},
		getModData:function(id){
			yc_public.ajax({"url":'getYcDeliveryOrderSingle.yc',data:{"id":id},success:function(data){
				if(data.code==0){
					yc_public.setData("mod_delivery_form",data.data);
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
		},
		findSelect:function(form){
			grid.setGridParam({"postData":yc_public.getData(form)}).trigger("reloadGrid");
		}
}
//前往添加页
function toAdd(){
	//window.parent.f_refreshTab("sd");
	window.parent.f_addTab("add_delivery_order","添加配送单","toAddYcDeliveryOrderPage.yc");
}
//前往修改页
function toEdit(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		//var Data = grid.getRowData(rowData);
		//获取id
		window.parent.f_addTab("mod_delivery_order","修改配送单","toModYcDeliveryOrderPage.yc?rowId="+rowData[0]);
    }else{
    	yc_public.alert({"msg":"只能选择一项进行修改操作！"});
    }
}
//删除
function del(){
	var rowData = grid.getGridParam('selarrrow');
    if(rowData.length) {
    	yc_public.confirm({"color":"red","msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		//yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcStowage.yc","result":"del"});
    	}});
    }else{
    	yc_public.alert({"msg":"只能选择一项进行删除操作！"});
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
