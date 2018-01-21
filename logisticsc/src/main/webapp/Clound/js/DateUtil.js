var DateUtil={
		RemoveZero:function(val){
			if(val){
				return val.substring(0,val.lastIndexOf('.'));
			}
			return val || '暂无';
		},
		RemoveTime:function(val){
			return val.substring(0,10);
		}
}