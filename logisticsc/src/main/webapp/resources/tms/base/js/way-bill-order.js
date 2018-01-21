/** 编辑 table 的操作 */
// 构建编辑行
function buildEditRow(){
	return $('#editRowTable tbody').html();
}

//构建编辑完成的行
function buildEditFinishRow(){
	var cargoInfoData = getCargoInfoEditing();
	countCargoFreight(cargoInfoData);
	
	var html = '<tr>';
	html += '<td><span>'+cargoInfoData[0]+'</span><input type="hidden" value="'+cargoInfoData[0]+'"></td>';
	html += '<td><span>'+cargoInfoData[1]+'</span><input type="hidden" value="'+cargoInfoData[1]+'"></td>';
	html += '<td><span>'+cargoInfoData[2]+'</span><input type="hidden" value="'+cargoInfoData[2]+'"></td>';
	html += '<td><span>'+cargoInfoData[3]+'</span><input type="hidden" value="'+cargoInfoData[4]+'"></td>';
	html += '<td><span>'+cargoInfoData[5]+'</span><input type="hidden" value="'+cargoInfoData[5]+'"></td>';
	html += '<td><span>'+cargoInfoData[6]+'</span><input type="hidden" value="'+cargoInfoData[6]+'"></td>';
	html += '<td><span>'+cargoInfoData[7]+'</span><input type="hidden" value="'+cargoInfoData[7]+'"></td>';
	html += '<td><span>'+cargoInfoData[8]+'</span><input type="hidden" value="'+cargoInfoData[8]+'"></td>';
	html += '<td><span>'+cargoInfoData[9]+'</span><input type="hidden" value="'+cargoInfoData[10]+'"></td>';
	html += '<td><span>'+cargoInfoData[11]+'</span><input type="hidden" value="'+cargoInfoData[11]+'"></td>';
	html += '<td><a href="javascript://" onclick="operate(this)">删除</a></td>';
	html += '</tr>';
	return getFinishedRow() + html;
}

//获取编辑的货物信息
function getCargoInfoEditing(){
	var trs = $('#cargoTable tr');
	var lastTr = trs[trs.length - 1];
	var tds = $(lastTr).children();
	var cargoInfoData = [];
	cargoInfoData.push($(tds).children()[0].value);
	cargoInfoData.push($(tds).children()[1].value);
	cargoInfoData.push($(tds).children()[2].value);
	cargoInfoData.push($(tds).children()[3].options[$(tds).children()[3].options.selectedIndex].text);
	cargoInfoData.push($(tds).children()[3].value);
	cargoInfoData.push($(tds).children()[4].value);
	cargoInfoData.push($(tds).children()[5].value);
	cargoInfoData.push($(tds).children()[6].value);
	cargoInfoData.push($(tds).children()[7].value);
	cargoInfoData.push($(tds).children()[8].options[$(tds).children()[8].options.selectedIndex].text);
	cargoInfoData.push($(tds).children()[8].value);
	cargoInfoData.push($(tds).children()[9].value);
	return cargoInfoData;
}

// 获取 表头 和 已编辑完成的行
function getFinishedRow(){
	var html = '';
	var trs = $('#cargoTable tr');
	for(var i = 0; i < trs.length - 1; i++){
		html += '<tr>' + trs[i].innerHTML + '</tr>';
	}
	return html;
}

// ------ 货物价格计算
function countCargoFreight(row){
	var freight = new Number($('#freight').val());
	freight = freight == null || freight == '' ? 0 : freight;
	var number = row[5] == null || row[5] == '' ? 0 : row[5];
	var setNumber = row[6] == null || row[6] == '' ? 0 : row[6];
	var weight = row[7] == null || row[7] == '' ? 0 : row[7];
	var volume = row[8] == null || row[8] == '' ? 0 : row[8];
	var countCostMode = row[10];
	var price = row[11] == null || row[11] == '' ? 0 : row[11];
	if(countCostMode == 0){ // 按重量
		$('#freight').val(freight + new Number(weight * price));
	}else if(countCostMode == 1){ // 按体积
		$('#freight').val(freight + new Number(volume * price));
	}else if(countCostMode == 2){ // 按件数
		$('#freight').val(freight + new Number(number * price));
	}else if(countCostMode == 3){ // 按套数
		$('#freight').val(freight + new Number(setNumber * price));
	}
	countTotalMoney();
}