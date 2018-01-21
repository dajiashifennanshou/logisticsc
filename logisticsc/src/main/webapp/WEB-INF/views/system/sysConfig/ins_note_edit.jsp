<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投保注意事项设置</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
<script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
<script src="http://g.alicdn.com/bui/seajs/2.3.0/sea.js"></script>
<script src="http://g.alicdn.com/bui/bui/1.1.21/config.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/lang/zh_CN.js"></script>
</head>
 <style>
    .bui-tree-list{
      overflow: auto;
    }
  </style>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>投保注意事项设置</h2>
        </div>
        <div class="panel-body" style="min-height:600px;min-width:800px">
	     	<form id="ins_note" class="form-horizontal" style="heigth:500px">
	    		<div class="control-group">
	    			<label class="control-label"><s>*</s>保险公司：</label>
	    			<div class="controls">
	    				 <input type="text" name="itemName" class="input-large" value="${insNote.itemName }" readonly/>
	    			</div>
	    		</div>
	    		<div class="control-group" style="height:400px">
	    			<label class="control-label"><s>*</s>注意事项：</label>
	    			<div class="controls" style="width:85%;">
		           		<textarea name="itemContent" id="introduce">${insNote.itemContent }</textarea>
		          	</div>
	    		</div>
	    		<div class="control-group">
	    			<label class="control-label">&nbsp;</label>
	    			<div class="controls">
	    				 <button type="submit" class="button button-primary" onclick="saveTemp();return false;">保存</button>
	    			</div>
	    		</div>
	    		<input id="noteId" type="hidden" name="id" value="${insNote.id }"/>
	    	</form>
	    	
       	</div>
		           		
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
		var options = {
			filterMode : false,
			uploadJson : "${configProps['project']}/system/help/upload.shtml",
			allowUpload : true,
			width: "100%", 
		    height: "350",
			afterCreate : function() {
				var self = this;
				self.sync();
			},
			afterChange : function() {
				var self = this;
				self.sync();
			},
			afterBlur : function() {
				var self = this;
				self.sync();
			},
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'image', 'link']
		};
		 KindEditor.ready(function(K) {
			 kindEditor = K.create('#introduce',options);
		 });
		 

		function saveTemp(){
			$.ajax({
				url:'<%=request.getContextPath()%>/sys/config/updateInsNote.shtml',
				type:'post',
				data:$("#ins_note").serialize(),
				success:function(result){
					if(result.result){
						BUI.Message.Alert("操作成功","success");
					}
				}
			})
		} 
		
</script>    
</body>
</html>