<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
</head>
<body>
	<div class="container">
		<div class="content-border">
			<form class="form-horizontal" id="J_Form">
				<fieldset>
					<legend>
						<h1>新增/编辑</h1>
						<small>填写以下资料,<s style="color: red;">*</s>为必填项
						</small> 
					</legend>
					<input type="hidden" name="admininfoId" value="1" />
					<div class="control-group">
						<label class="control-label"><s>*</s>登录帐号：</label>
						<div class="controls">
							<input class="input-large control-text" readonly="readonly" type="text" name="username" value="${sysUser.username}" data-rules="{required:true}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">默认初始密码：</label>
						<div class="controls">
							<input class="input-large control-text" id="tip" type="password" name="password" value="{sysUser.password}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><s>*</s>真是姓名：</label>
						<div class="controls">
							<input class="input-large control-text" type="text" name="realname" value="张三" data-rules="{required:true}" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label"><s>*</s>组织机构：</label>
						<div class="controls">
							<input class="input-large control-text" id="show" type="text" />
						</div>
						<input type="hidden" id="hide"  name="organizationId" value="1" >
					</div>
					

					<div class="row actions-bar">
						<div class="form-actions span13 offset3">
							<button type="submit" class="button button-primary">保存</button>
							<button id="back" type="reset" class="button button-primary">返回</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>