<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加物流资讯</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
<script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
<script src="http://g.alicdn.com/bui/seajs/2.3.0/sea.js"></script>
<script src="http://g.alicdn.com/bui/bui/1.1.21/config.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
</head>
<body>
<div class="demo-content">
	    <form id="J_Form" action="" class="form-horizontal" enctype="multipart/form-data">
	      <div class="control-group">
	        <label class="control-label"><s>*</s>物流标题：</label>
	        <div class="controls">
	          <input name="newsTitle" type="text" class="input-large" placeholder="请输入物流标题" value="${news.newsTitle}">
	          <input name="newsType" value="1" style="display: none">
	          <input name="id" id="id" type="hidden" type="text" value="${news.id}"/>
	           <input name="newsUrl" type="hidden" type="text" value="${news.newsUrl}"/>
	            <input id="titlePicture" type="hidden" type="text" value="${news.titlePicture}"/>
	        </div>
	      </div>
	      <div class="control-group">
	        <label class="control-label"><s>*</s>标题图片：</label>
	        <div class="controls">
	        <input type="file"  name="fileName" id="fileName" onchange="javascript:setImagePreview();" >
	        </div>
	      </div>
	      	<div class="control-group">
	   			<div class="control-label">&nbsp;</div>
	   			<div class="controls control-row-auto">
					<img id="preview"/>
	   			</div>
	   		</div>
	   		<div class="control-group">
    			  <label class="control-label"><s>*</s>新闻内容：</label>
    			<div class="controls control-row-auto">
					<textarea name="newsContent" id="introduce" class="control-row4 input-large">${news.newsContent}</textarea>
    			</div>
    		</div>
	      <div class="row actions-bar">       
	          <div class="form-actions span13 offset3">
	           <input type="button" onclick="news()" class="button button-primary" value="确认发布"/>
			   <button type="reset" class="button" style="margin-left:10px;">重置</button>
	          </div>
	      </div>       
	    </form>
    </div>
    <script type="text/javascript">
     $(function(){
    	 	if($("#id").val() != null && $("#id").val() != ""){
    	 		src();
    	 	}
    	})
     function src(){
    	 var a = $("#titlePicture").val();
    	 var src = "<%=request.getContextPath()%>/img/retrive.shtml?resource="+a;
    	 $("#preview").attr("src",src);
     }
     function news(){
  		    $("#J_Form").ajaxSubmit({
  			  	url:'<%=request.getContextPath()%>/system/news/doGetNews.shtml',
  			  	type:'post',
  			  	dataType:'json',
  			  	headers : {"ClientCallMode" : "ajax"},
  				success:function(data){
  					if (data.result == true) {
  						BUI.Message.Alert("操作成功！！！",function(){
  							window.location.href="${configProps['project']}/system/news/jumpNewsInformation.shtml";
  						},"success");
				}else{
					BUI.Message.Alert("操作失败！！！","success");
				}
        	},
        	error:function(){
        		BUI.Message.Alert("操作失败！！！","error");
        	}
    			});    
     }
     var kindEditor; // kindEditor 编辑器对象
 	 var options = {
 				filterMode : false,
 				uploadJson : "${configProps['project']}/system/help/upload.shtml",
 				allowUpload : true,
 				width: "900", 
 			    height: "400",
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