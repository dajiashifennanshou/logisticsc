<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>险种管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body, #hidden_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>险种管理</h2>
           	
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="保险公司，标签">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    <!-- 默认隐藏 -->
    <div id="dia_hide" class="hide">
    	<form id="ins_ts_type_form" class="form-horizontal" enctype="multipart/form-data">
    		<div class="control-group">
    			<label class="control-label"><s>*</s>保险公司：</label>
    			<div class="controls">
    				 <input id="ins_com_name" name="insComName" type="text" class="input-normal" data-rules="{required:true}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>保险公司标签：</label>
    			<div class="controls">
    				<input id="ins_com_tag" name="insComTag" type="text" class="input-normal" data-rules="{required:true,v_comTag:true}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>购买险种：</label>
    			<div class="controls"  id="ins_type">
    				<c:forEach var="insType" items="${map.typeList }">
    					<label class="checkbox"><input type="checkbox"  name="insTypes" value="${insType.id }"/>${insType.insTypeName }</label>
    				</c:forEach>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>特殊类型：</label>
    			<div class="controls"  id="ins_ts_type">
    				<c:forEach var="insTsType" items="${map.tsTypeList }">
    					<label class="checkbox"><input type="checkbox" name="insTsTypes" value="${insTsType.id }"/>${insTsType.insTsTypeName }</label>
    				</c:forEach>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>保险公司LOGO：</label>
    			<div class="controls">
    				<input id="fileName" name="mulFile" type="file" class="input-normal" data-rules="{v_pic:true}" onchange="javascript:setImagePreview();"/>
    			</div>
    		</div>
    		<input type="hidden" id="hid_id" name="id">
    		<div class="control-group">
    			<div class="control-label">&nbsp;</div>
    			<div class="controls control-row-auto">
					<img id="preview"/>
    			</div>
    		</div>
    	</form>
    	
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params = {
					condition:$("#condition").val()
			};
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form','bui/overlay'],
					function(Grid,Data,Toolbar,Format,Form,Overlay){
			Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '保险公司',elCls:'center',dataIndex :'insComName'},
				{title : '保险公司标签',elCls:'center',dataIndex :'insComTag'},
				{title : '可购买险种',elCls:'center',renderer:function(val,obj){
					var list = obj.insuranceTypeList,
						cnt = '';
					for(var i=0;i<list.length;i++){
						cnt += "["+list[i].insTypeName+"]";
					}
					return cnt;
				}},
				{title : '可购买特殊类型',elCls:'center',renderer:function(val,obj){
					var list = obj.insuranceTsTypeList,
						cnt = '';
					for(var i=0;i<list.length;i++){
						cnt += "["+list[i].insTsTypeName+"]";
					}
					return cnt;
				}},
				{title : '创建时间',elCls:'center',dataIndex :'createTime',renderer:Grid.Format.datetimeRenderer},
			];
			
			store = new Store({
				url: '<%=request.getContextPath()%>/system/ins/searchInsCom.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
			  	forceFit:true,
				columns : columns,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection],
			      tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		handler:addInsCom
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler:editInsCom
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-del"></i>删除',
			           		handler:delInsCom
			          	}
		          	],
				}
			});
			function addInsCom(){
				//editing.add();
				dialog.set("editType","add");
				dialog.show();
			}
			
			function editInsCom(){
				var selects = grid.getSelection();
				if(selects.length==1){
					var src = "<%=request.getContextPath()%>/img/retrive.shtml?resource="+selects[0].insComLogoUrl;
					$("#preview").attr("src",src);
					//$("#fileName").val($("#preview"). );
					$("#ins_com_name").val(selects[0].insComName);
					$("#ins_com_tag").val(selects[0].insComTag);
					$("#ins_type input").each(function(){
						for(var i=0;i<selects[0].insuranceTypeList.length;i++){
							if($(this).val()==selects[0].insuranceTypeList[i].id){
								$(this).prop("checked",true);
							}
						}
					})
					$("#ins_ts_type input").each(function(){
						for(var i=0;i<selects[0].insuranceTsTypeList.length;i++){
							if($(this).val()==selects[0].insuranceTsTypeList[i].id){
								$(this).prop("checked",true);
							}
						}
					})
					$("#hid_id").val(selects[0].id);
					dialog.set("editType","edit");
					dialog.show();
					
				}else{
					BUI.Message.Alert("请选择一条记录","warning");
				}
			}

			function delInsCom(){
				var selects = grid.getSelection();
				var length = selects.length;
				if(length>0){
					var array = [];
					for(var i=0;i<length;i++){
						array.push(selects[i].id);
					}
					$.ajax({
						url:'<%=request.getContextPath()%>/system/ins/delCom.shtml',
						type:'post',
						data:{comIds:array},
						dataType:'json',
						success:function(data){
							if(data.result == true){
								BUI.Message.Alert("操作成功",function(){
									store.load();	
								},"success");
							}
						}
					})
				}
			}
			
			//验证标签是否存在
	    	Form.Rules.add({
	      		name : 'v_comTag',  //规则名称
	            msg : '该标签已存在',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	            	if(dialog.get("editType")=='add'){
	            		if(value != null && value != ''){
		              		if(tagExist(value)){
			            		return formatMsg;
			            	} 
		              	}
	            	}
	            }
	        }); 
			
			//图片验证
	    	Form.Rules.add({
	      		name : 'v_pic',  //规则名称
	            msg : '不能为空',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	            	if(dialog.get("editType")=='add'){
	            		if(value == null || value == ''){
		              		return '请选择文件';
		              	}
	            	}
	            }
	        }); 
			
	        var form = new Form.Form({
	        	srcNode:"#ins_ts_type_form"
	        }).render();
	        
	        var dialog = new Overlay.Dialog({
	            title:'保险公司管理',
	            width:600,
	            height:450,
	            //配置DOM容器的编号
	            contentId:'dia_hide',
	            success:function () {
    		  		var editType = this.get("editType"),
	    		  		form_t = $("#ins_ts_type_form").serialize(),
	    		  		url = "";
	    		  	if(editType=="edit"){
	    		  		url = "<%=request.getContextPath()%>/system/ins/addInsCom.shtml";
	    		  	}else if(editType == "add"){
	    		  		url = "<%=request.getContextPath()%>/system/ins/addInsCom.shtml";
	    		  	}
	    		  	form.valid();
	    		  	if(form.isValid()){
	    		  		$("#ins_ts_type_form").ajaxSubmit({
	        			  	url:url,
	        			  	type:'post',
	        			  	dataType:'json',
	        			  	headers : {"ClientCallMode" : "ajax"},
	        			  	async:false,
	        				success:function(data){
	        					if(data.result){
	        						BUI.Message.Alert("信息添加成功",function(){
	        							dialog.close();
	        							store.load();
		        						$("#ins_com_tag").removeAttr("readonly");
	        						},"success");
	        					}
				        	},
				        	error:function(){
				        		BUI.Message.Alert("网络异常","error");
				        	}
	          			});
	    		  	}
	            }
	        });
	       dialog.on("closeclick",function(){
	    	    $("#preview").removeAttr("src");
				$("#ins_com_name").val("");
				$("#ins_com_tag").val("");
				$("#hid_id").val("");
				$("#ins_type input").each(function(){
						$(this).removeAttr("checked");
				})
				$("#ins_ts_type input").each(function(){
						$(this).removeAttr("checked");
				}) 
	       })
	       dialog.on("closed",function(){
				$("#preview").removeAttr("src");
				$("#ins_com_name").val("");
				$("#ins_com_tag").val("");
				$("#hid_id").val("");
				$("#ins_type input").each(function(){
						$(this).removeAttr("checked");
				})
				$("#ins_ts_type input").each(function(){
						$(this).removeAttr("checked");
				}) 
	       })
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store,
		    });
		});	
		
		
		
		
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
		
		function tagExist(tag){
			var flag = false;
			$.ajax({
				url:'<%=request.getContextPath()%>/system/ins/existCom.shtml',
				type:'post',
				data:{comTag:tag},
				async:false,
				success:function(result){
					if(result.result){
						flag = result.data;
					}
				},
			})
			return flag;
		}
		
		function getTsAndT(){
			$.ajax({
				url:'<%=request.getContextPath()%>/system/ins/getTsAndT.shtml',
				type:'post',
				success:function(result){
					
				}
			})
		}
		
		function setImagePreview() { 
			var docObj = document.getElementById("fileName"); 
			var imgObjPreview = document.getElementById("preview"); 
			if (docObj.files && docObj.files[0]) { 
				/*火狐下，直接设img属性*/
				imgObjPreview.style.display = 'block'; 
				imgObjPreview.style.width = '250px'; 
				imgObjPreview.style.height = '120px'; 
				/*imgObjPreview.src = docObj.files[0].getAsDataURL();*/ 
				/*火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式*/ 
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]); 
			} else { 
				/*IE下，使用滤镜*/ 
				docObj.select(); 
				var imgSrc = document.selection.createRange().text; 
				var localImagId = document.getElementById("localImag"); 
				/*必须设置初始大小*/ 
				localImagId.style.width = "250px"; 
				localImagId.style.height = "120px"; 
				/*图片异常的捕捉，防止用户修改后缀来伪造图片*/ 
			try { 
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)"; localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc; 
			} catch (e) { 
				alert("您上传的图片格式不正确，请重新选择!"); 
			return false; 
			} 
				imgObjPreview.style.display = 'none'; 
				document.selection.empty(); 
			} 
				return true; 
			}	
	</script>
</body>
</html>