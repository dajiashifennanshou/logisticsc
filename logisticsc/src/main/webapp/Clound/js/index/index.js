var tab = null;
var tabItems = [];
$(function (){
	//tab页
    tab = $("#framecenter").ligerTab({
    	height:yc_public.height(),
        showSwitchInTab : true,
        showSwitch: true,
        onAfterAddTabItem: function (tabdata) {
            tabItems.push(tabdata);
        },
        onAfterRemoveTabItem: function (tabid){ 
            for (var i = 0; i < tabItems.length; i++){
                var o = tabItems[i];
                if (o.tabid == tabid){
                    tabItems.splice(i, 1);
                    break;
                }
            }
        },
        onReload: function (tabdata){
            var tabid = tabdata.tabid;
        }
    });
    tab = liger.get("framecenter");
    loadMeau("#loadMeau_1",'yc-getUserMeau.yc');
});
/**
 * 更改选中样式
 * @param t
 */
function meauClick(t){
	$('.active').removeClass('active');
	var index=$(t).index();
	$(t).parent('li').eq(index).addClass('active');
}
/**
 * 更改选中样式
 * @param t
 */
function PliClick(t){
	alert(1);
	$(t).addClass('active');
}
/**
 * 加载菜单
 */
var loadMeau=function(ele,url){
	//功能块html代码结构
	var lvli='<li><a href="javascript:void(0);" class="dropdown-toggle">'+
					'<i class="menu-icon fa fa-list"></i>'+
					'<span class="menu-text">{{modelName}}</span>'+
					'<b class="arrow fa fa-angle-down"></b>'+
				'</a>'+
				'<b class="arrow"></b>'+

				'<ul class="submenu">'+
						'{{meau}}'+
				'</ul></li>';
	//子模块html代码结构
	var meau='<li class="">'+
				'<a href="javascript:void(0);" onclick="meauClick(this),{{click}}">'+
					'<i class="menu-icon fa fa-caret-right"></i>'+
					'{{meauName}}'+
				'</a>'+
				'<b class="arrow"></b>'+
			'</li>';
	//ajax请求
	$.ajax({
		type:"post",
		url:url,
		async:false,
		success:function(data){
			data=yc_public.encode(data);
			if(data.code==1){
				yc_public.alert({msg:data.msg|| '加载失败'});
				return;
			}
			var d=data.data;
			//当前功能模块
			var cLv="";
			for(var i=0;i<d.length;i++){
				var p=d[i];
				
				//如果是总目录，则开始遍历他的子节点
				if(p.parentId==0){
					for(var p2=0;p2<d.length;p2++){
						var o=d[p2];
						//如果是总目录的子节点,就意味着他是一级菜单
						if(o.parentId==p.id){
							//临时功能模块
							var tLv=lvli;
							//替换一级菜单
							tLv=tLv.replace(/{{modelName}}/g,o.menuname);
							//当前子模块
							var cMeau="";
							for(var j=0;j<d.length;j++){
								var c=d[j];
								//以为是二级菜单
								if(c.parentId==o.id){
									//临时子模块
									var tMeau=meau;
									tMeau=tMeau.replace(/{{meauName}}/g,c.menuname).replace(/{{click}}/g,c.menuUrl);
									cMeau+=tMeau;
								}
							}
							//执行替换，将子模块放入功能模块下
							tLv=tLv.replace(/{{meau}}/g, cMeau);
							//拼接功能模块
							cLv+=tLv;
						}
					}
				}
			}
			//遍历结束，获取所有的html代码cLv
			$(ele).append(cLv);
		},error:function(){
			alert("菜单加载失败..");
		}
	});
	
}
function f_removeTab(tabid){
	tab.removeTabItem(tabid);
}
function f_refreshTab(tabid){
	var iframe=window.parent.document.getElementById(tabid);
	var src=$(iframe).attr("src");
	$(iframe).attr("src","");
	$(iframe).attr("src",src);
}
function f_showTab(tabid){
	tab.selectTabItem(tabid);
}
function f_addTab(tabid,text,url){
    tab.addTabItem({
        tabid: tabid,
        text: text,
        url: url
    });
}