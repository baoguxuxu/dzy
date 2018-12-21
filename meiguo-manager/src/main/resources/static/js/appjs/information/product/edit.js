$().ready(function() {
	
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var formData = new FormData(document.getElementById("signupForm"));
	$.ajax({
		cache : true,
		type : "POST",
		url : "/information/product/update",
		data : formData,// 你的formid
		processData:false,
		contentType:false,
		async : false,
		error : function(request) {
			parent.layer.alert("网络超时");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			productNumber : {
				required : true
			},
			cid : {
				required : true
			},
			categoryId : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入产品名称"
			},
			productNumber : {
				required : icon + "请输入产品编号"
			},
			cid : {
				required : icon + "请选择产品规格"
			},
			name : {
				required : icon + "请输入产品名称"
			},
			categoryId : {
				required : icon + "请选择产品分类"
			}
		}
	})
}