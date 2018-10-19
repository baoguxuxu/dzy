$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function save() {
	var formData = new FormData(document.getElementById("signupForm"));
	$.ajax({
		cache : true,
		type : "POST",
		url : "/information/goods/save",
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
			productId : {
				required : true
			},
			tgoodsNumber : {
				required : true
			},
			goodsNumber : {
				required : true
			},
			discountPrice : {
				required : true,
				number:true
			},
			payPrice : {
				required : true,
				number:true
			},
			vipPrice : {
				required : true,
				number:true
			},
			promotionPrice : {
				required : true,
				number:true
			}
		},
		messages : {
			name : {
				required : icon + "请输入货品名称"
			},
			productId : {
				required : icon + "请选择产品类"
			},
			tgoodsNumber : {
				required : icon + "请输入货品条形码号"
			},
			goodsNumber : {
				required : icon + "请输入货品编号"
			},
			discountPrice : {
				required : icon + "请输入折扣价"
			},
			payPrice : {
				required : icon + "请输入售价"
			},
			vipPrice : {
				required : icon + "请输入会员价"
			},
			promotionPrice : {
				required : icon + "请输入促销价"
			}
		}
	})
}