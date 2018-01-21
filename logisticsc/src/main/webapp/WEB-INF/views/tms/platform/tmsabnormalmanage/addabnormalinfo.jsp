<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>异常登记</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
	.form-horizontal .control-label{width: 80px;}
	.form-horizontal select{width: 166px;}
</style>
</head>
<body>
	<form class="form-horizontal" id="form_abnormal" enctype="multipart/form-data" onSubmit="return false;">
		<div class="panel">
	        <div class="panel-header">
	           	<h2>基本运单信息</h2>
	        </div>
	        <div class="panel-body">
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label"><s>*</s>运单号</label>
						<div class="controls">
							<c:choose>
								<c:when test="${empty wayBillOrder.wayBillNumber }">
									<input id="way_bill_number" type="text" name="wayBillNumber" data-rules="{required : true}" class="input-normal" onchange="renderItems()">
								</c:when>
								<c:otherwise>
									<input id="way_bill_number" type="text" name="wayBillNumber" data-rules="{required : true}" class="input-normal" value="${wayBillOrder.wayBillNumber }" readonly>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">发站网点</label>
						<div class="controls">
							<input type="text" id="start_outlets" class="input-normal" value="${wayBillOrder.startOutletsName }" readonly>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">到站网点</label>
						<div class="controls">
							<input type="text" id="end_outlets" class="input-normal" value="${wayBillOrder.targetOutletsName }" readonly>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">收货人</label>
						<div class="controls">
							<input type="text" id="consignee" value="${wayBillOrder.consignee }" readonly>
						</div>
					</div>
				</div>
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label">托运人</label>
						<div class="controls">
							<input type="text" id="consignor" class="input-normal" value="${wayBillOrder.consignor }" readonly>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运人电话</label>
						<div class="controls">
							<input type="text" id="consignor_phone" class="input-normal" value="${wayBillOrder.consignorMobile }" readonly>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">托运日期</label>
						<div class="controls">
							<input type="text" id="consignor_date" value="<fmt:formatDate value="${wayBillOrder.wayBillOrderTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">保险金额</label>
						<div class="controls">
							<input type="text" id="money" value="${wayBillOrder.insuranceMoney }" readonly>
						</div>
					</div>
				</div>
			</div>
	    </div>
	    <div class="panel">
	        <div class="panel-header">
	           	<h2>异常信息</h2>
	        </div>
	        <div class="panel-body">
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label"><s>*</s>发生日期</label>
						<div class="controls">
							<input type="text" name="abnormalDate" data-rules="{required : true}" class="calendar calendar-time" style="width:90%">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>发现人</label>
						<div class="controls">
							<input type="text" name="foundPerson" data-rules="{required : true}" class="input-normal">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>异常环节</label>
						<div class="controls">
							<select name="abnormalNode" data-rules="{required:true}">
								<option value="">请选择</option>
								<c:forEach var="abnormalNode" items="${abnormalNodes }">
									<option value="${abnormalNode.id }">${abnormalNode.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>异常类型</label>
						<div class="controls">
							<select name="abnormalType" data-rules="{required:true}">
								<option value="">请选择</option>
								<c:forEach var="abnormalType" items="${abnormalTypes }">
									<option value="${abnormalType.id }">${abnormalType.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label"><s>*</s>商品名称</label>
						<div class="controls">
							<input type="text" name="cargoName" data-rules="{required:true}" class="input-normal">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>包装</label>
						<div class="controls">
							<input type="text" name="cargoPackage" data-rules="{required:true}" class="input-normal">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>件数</label>
						<div class="controls">
							<input type="text" name="cargoPiece" data-rules="{required:true,number:true}"  class="input-normal">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">货差数量</label>
						<div class="controls">
							<input type="text" name="cargoDiffNumber" data-rules="{number:true}" class="input-normal" value="${orderNumber}">
						</div>
					</div>
				</div>
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group span6">
						<label class="control-label">货损数量</label>
						<div class="controls">
							<input type="text" name="cargoDamageNumber" class="input-normal" data-rules="{number:true}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>责任站点</label>
						<div class="controls">
							<select name="dutySite" data-rules="{required:true}">
								<option value="">请选择</option>
								<c:forEach var="outlets" items="${outletsList }">
									<option value="${outlets.id }">${outlets.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label"><s>*</s>登记人</label>
						<div class="controls">
							<input type="text" name="operationPerson" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">货损情况</label>
					<div class="controls control-row-auto">
		              	<textarea name="content" class="control-row4 input-large"></textarea>
		            </div>
				</div>
			</div>
	    </div>
	    <div class="panel">
	        <div class="panel-header">
	           	<h2>图片信息</h2>
	        </div>
	        <div class="panel-body">
				<div class="row-fluid" style="margin-top: 10px;">
					<div class="control-group">
						<label class="control-label">图片上传</label>
						<div class="controls control-row-auto">
							<input type="file" name="fileName" id="fileName" onchange="javascript:setImagePreview();">
		    			</div>
					</div>
					<div class="control-group">
		    			<div class="control-label">&nbsp;</div>
		    			<div class="controls" style="height:200px">
							<img id="preview" height="200px" />
		    			</div>
		    		</div>
				</div>
			</div>
	    </div>
	    <div class="actions-bar">
        	<div class="row ">
            <div class="span13 offset3 ">
              <button type="submit" class="button button-primary" onclick="submitAbnormal()">保存</button>
              <button type="reset" class="button">重置</button>
            </div>
          </div>
        </div>
    </form>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
		var form_abnormal;
		function submitAbnormal(){
			form_abnormal.valid();
			if(!form_abnormal.isValid()){
				return;
			}
			$("#form_abnormal").ajaxSubmit({
				url:'<%=request.getContextPath()%>/tms/abnormal/insert.shtml',
				type:'post',
				dataType:'json',
				headers : {"ClientCallMode" : "ajax"},
				success:function(data){
					if(data.result){
						BUI.Message.Alert("添加成功",function(){
							window.location.href="<%=request.getContextPath()%>/tms/abnormal/list.shtml";
						},'success');
					}
				},
				error:function(){
					BUI.Message.Alert("系统错误",'error')
				}
			})
		}
		function renderItems(){
			var wayBillNumber = $("#way_bill_number").val();
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/abnormal/gtwybll.shtml',
				type:'post',
				data:{wayBillNumber:wayBillNumber},
				success:function(data){
					if(data.result){
						if(data.data){
							var exceptionStatus = data.data.exceptionStatus;
							if(exceptionStatus != 0){
								BUI.Message.Alert('该运单已登记异常','warning');
								return;
							}
							$("#start_outlets").val(data.data.startOutletsName);
							$("#end_outlets").val(data.data.targetOutletsName);
							$("#consignee").val(data.data.consignee);
							$("#consignor").val(data.data.consignor);
							$("#consignor_phone").val(data.data.consignorMobile);
							$("#consignor_date").val(data.data.wayBillOrderTime);
							$("#money").val(data.data.insuranceMoney);
						}else{
							$("#start_outlets").val('');
							$("#end_outlets").val('');
							$("#consignee").val('');
							$("#consignor").val('');
							$("#consignor_phone").val('');
							$("#consignor_date").val('');
							$("#money").val('');
							alert("未查询到该运单号信息，请重新输入");
						}
					}else{
						$("#start_outlets").val('');
						$("#end_outlets").val('');
						$("#consignee").val('');
						$("#consignor").val('');
						$("#consignor_phone").val('');
						$("#consignor_date").val('');
						$("#money").val('');
					}
				}
			})
		}
		//日期渲染
		BUI.use('bui/calendar',function(Calendar){
            var datepicker = new Calendar.DatePicker({
              trigger:'.calendar-time',
              showTime:true,
              autoRender : true
            });
        });
		/* BUI.use(['bui/calendar'],function(Calendar){
			//日期加载
			
			var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
			
		}); */
		loadForm();
		function loadForm(){
			var errorTpl = '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>';
			BUI.use('bui/form',function(Form){
				form_abnormal = new Form.Form({
					srcNode : '#form_abnormal',
					errorTpl : errorTpl
				}).render();
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
		
		/* var time = 0;
		function addAttach(){
			var input = document.createElement("input"),
				a = document.createElement("a");
			input.name = "file"+time;
			time++;
			input.type= "file";
			a.innerHTML = "删除";
			a.href = "javascript:void(0)";
			a.onclick = "rmvAttach(file_group"+time+")";
			document.getElementById("file_group").appendChild(input);
			document.getElementById("file_group").appendChild(a);
		}
		
		function rmvAttach(group){
			var item = document.getElementById(group);
			item.parentNode.remove();
		}
		 */
	</script>
</body>
</html>