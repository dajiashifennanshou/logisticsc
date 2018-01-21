// 动态构建FORM表单
	function buildForm(action, name, value){
		 var form = document.createElement("form"); 
		  form.id = "toForm"; 
		  form.name = "toForm"; 
		  // 添加到 body 中 
		  document.body.appendChild(form); 
		  for (var int = 0; int < name.length; int++) {
			// 创建一个输入 
			  var input = document.createElement("input"); 
			  // 设置相应参数 
			  input.type = "text"; 
			  input.name = name[int]; 
			  input.value = value[int]; 
			  // 将该输入框插入到 form 中 
			  form.appendChild(input); 
		  }
		  // form 的提交方式 
		  form.method = "POST"; 
		  // form 提交路径 
		  form.action =action; 
		  // 对该 form 执行提交 
		  form.submit(); 
		  // 删除该 form 
		  document.body.removeChild(form); 
	}