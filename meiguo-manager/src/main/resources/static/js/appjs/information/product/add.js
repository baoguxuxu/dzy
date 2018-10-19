$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/information/product/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
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

function getSpec(){
	$.ajax({
		cache : true,
		type : "POST",
		url : "/information/product/getSpec",
		data : {id:$('#spec').val()},
		async : false,

		success : function(data) {
			var html="";
			for(var i = 0;i<data.length;i++) {
  				html+='<label class="col-sm-3 control-label">';
  				html+=data[i].name;
  				html+=':   </label>';
  				html+='<div class="col-sm-8">';
  				html+='<input name="list['+i+'].specId" class="form-control" type="hidden" value="'+data[i].id+'">'
  				html+='<input name="list['+i+'].remark" class="form-control" type="text"></div>';
  			}
  			$("#sppec").html(html);
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