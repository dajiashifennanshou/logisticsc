<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广告管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body, #feedback_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>广告管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline" enctype="multipart/form-data">
	        	起始时间：<input id="start_time" name="startTime" type="text" class="calendar"/>-<input id="end_time" name="endTime" type="text" class="calendar"/>
        		<input id="condition" name="condition" type="text" placeholder="名称">
	        	<button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="render_grid">
	       	</div>
	       	<div id="pagingbar">
	       	</div>
       	</div>
    </div>
    <!-- 默认隐藏 -->
    <div id="add_edit_dia" class="hide">
    	<form id="ad_form" class="form-horizontal" enctype="multipart/form-data">
    		<div class="control-group">
    			<div class="control-label">广告名称：</div>
    			<div class="controls">
    				<input name="adName" type="text"/>
    				<input type="hidden" name="publishType" value="0"/>
    				<input type="hidden" name="id" value="${id}"/>
    				<input type="hidden" name="adFilePath" value="${adFilePath}"/>
    				<input type="hidden" name="createPersonId" value="${createPersonId}"/>
    				<input type="hidden" name="adOwner" value="${adOwner}"/>
    				<input type="hidden" name="state" value="${state}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">链接地址：</div>
    			<div class="controls">
    				<input name="adUrl" type="text" placeholder="请输入广告链接地址"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">发布者：</div>
    			<div class="controls">
    				<input name="adOwner" type="text"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">位置：</div>
    			<div class="controls control-row-auto">
         				<select id="ad_pst" name="adPositionId" class="input-small" onchange="verifyDate()">
    				</select>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">开始时间：</div>
    			<div class="controls">
    				<input id="start_time" name="startTime" type="text" class="calendar" onchange="verifyDate()"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">结束时间：</div>
    			<div class="controls">
    				<input id="end_time" name="endTime" type="text" class="calendar" onchange="verifyDate()"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">广告：</div>
    			<div class="controls control-row-auto">
					<input type="file"  name="fileName" id="fileName" onchange="javascript:setImagePreview();" >
    			</div>
    		</div>
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
	
		var adForm;
		//查询
		function search(){
			var params={
				startTime:$("#start_time").val(),
				endTime:$("#end_time").val(),
				condition:$("#condition").val()
			}
			store.load(params);
		}
		
		BUI.use('bui/form',function(Form){
			adForm = new Form.Form({
				srcNode : '#ad_form',
				callback : function(result){
					
			    }
			}).render();
		})
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select'],
					function(Grid,Data,Toolbar,Format,Select){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '名称', dataIndex : 'adName'},
				{title : '广告首页', dataIndex : 'adUrl'},
				{title : '位置', dataIndex : 'adPositionVal'},
				{title : '发布者',dataIndex :'adOwner'},
				{title : '开始时间', dataIndex : 'startTime',renderer:BUI.Grid.Format.datetimeRenderer},
				{title : '结束时间', dataIndex : 'endTime',renderer:BUI.Grid.Format.datetimeRenderer},
				
			];
			editing = new Grid.Plugins.DialogEditing({
		          contentId : 'add_edit_dia', //设置隐藏的Dialog内容
		          editor:{
		        	  title:'广告管理',
		        	  width:500,
		        	  height:400,
		        	  success:function(){
		        		  var editor = this;
		        		  	form = editor.get("form");
		        		  $("#ad_form").ajaxSubmit({
		        			  	url:'<%=request.getContextPath()%>/system/yypt/advert/saveadvert.shtml',
		        			  	type:'post',
		        			  	dataType:'json',
		        			  	headers : {"ClientCallMode" : "ajax"},
		        				success:function(data){
		        					if (data.result == true) {
		        						BUI.Message.Alert("操作成功！！！","success");
		        						editor.close();
		        						store.load();
									}
					        	},
					        	error:function(){
					        		BUI.Message.Alert("操作失败！！！","error");
					        	}
		          			});
		        	  }
		          }
		    });
			editing.on('editorready',function(e){
				renderAdPst();
			})
			store = new Store({
				url: '<%=request.getContextPath()%>/system/yypt/advert/listdata.shtml',
				autoLoad:true,
				pageSize:10,
			});

			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection,editing],
				tbar:{
					items : [{
	                  	btnCls : 'button button-small',
	                  	text : '<i class="icon-plus"></i>添加',
	                  	listeners : {
	                    	"click":addAd
	                  	}
	                },{
	                  	btnCls : 'button button-small',
	                  	text : '<i class="icon-plus"></i>编辑',
	                  	listeners : {
	                    	'click':editAd
	                  	}
	                },{
	                	 btnCls : 'button button-small',
	                     text : '<i class="icon-remove"></i>删除',
	                     listeners : {
	                       'click' : delFunction
	                     }
	                }
	                  	]
				}
				
			});
			function addAd(){
				editing.add();
			}
			function editAd(){
				var selects = grid.getSelection();
				var len = selects.length;
				if(len==1){
					var src = "<%=request.getContextPath()%>/img/retrive.shtml?resource="+selects[0].adFilePath;
					$("#preview").attr("src",src);
					editing.edit(selects[0]);
				}else{
					BUI.Message.Alert("请选择一条记录");
				}
			}
			
			//删除
			function delFunction(){
				 var selections = grid.getSelection();
				 var ids = new Array();
				 var len = selections.length;
				 for (var i = 0; i < len; i++) {
					 ids[i] = selections[i].id
				}
				if(len > 0){
					 $.ajax({
	        			  	url:'<%=request.getContextPath()%>/system/yypt/advert/deletes.shtml?',
	        			  	type:'post',
	        			  	dataType:'json',
	        			  	data:{"idss":JSON.stringify(ids)},
	        				success:function(data){
	        					if(data.result == true){
	        						BUI.Message.Alert("删除成功！！！","success");
	        						store.load();
	        					}
				        	},
				        	error:function(){
				        		BUI.Message.Alert("删除失败！！！","error");
				        	}
	          			});
					
				}else{
					BUI.Message.Alert("请选择一条记录");
				}
			}
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
		function verifyDate(){
			var startTime = adForm.getFieldValue("startTimeStr"),
				endTime = adForm.getFieldValue("endTimeStr"),
				adPosition = $("#ad_pst");	
			if(adPosition&&endTime&&startTime){
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/sys/ad/vrfydt.shtml',
					data:{startTime:startTime,endTime:endTime,adPosition:adPosition.val()},
					type:'post',
					success:function(data){
						if(!data.result){
							BUI.Message.Alert("该时间段不可用或者已被占用");
							return false;
						}
					}
				})
			}
		}
		
		function renderAdPst(){
			$.ajax({
				url:'<%=request.getContextPath()%>/dic/ad_position.shtml',
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.result == true){
						var rows = data.rows,
						ad = $("#ad_pst");
						ad.empty();
						ad.append("<option value=''>请选择</option>");
						for(var i=0;i<rows.length;i++){
							ad.append("<option value='"+rows[i].id+"'>"+rows[i].name+"</option>");
						}
					}
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