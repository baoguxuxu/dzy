$(function(){
	getProduct();
})



//box : 切换主体盒子ID
			//boxW : 切换主体盒子 的宽度
			//liLen ： 每页几个li
			//delay : 定时切换页面时间
			function ListChange(box , boxW , liLen , delay){
				var $box = $('#' + box);
				var $ul  = $('#' + box + ' ul');
				var $li  = $('#' + box + ' li');
				$('#' + box + ' li:nth-child(' + liLen + 'n)').css('margin-right',0);
				
				var page = parseInt($li.length / liLen);
				page = $li.length % liLen > 0 ? page + 1 : page;
				$ul.css('width',boxW * page);
				
				if(page > 1){
					for(var i = 0 ; i < page ; i++){
						if(i==0){
							$('#setPage').append('<a class="setPageIn" href="javascript:void(0)"></a>');
						}else{
							$('#setPage').append('<a href="javascript:void(0)"></a>');
						}
					}
					$('#showPage').on('click','#setPage a',function(){
						clearTimeout(curTimer);
						var $this = $(this);
						curpage = $this.index();
						$ul.stop().animate({'margin-left':-curpage * boxW},'slow',function(){
							curTimer = setTimeout(changePage,delay);
						});
						$this.addClass('setPageIn').siblings().removeClass('setPageIn');
					});
					var curpage = 0;
					var curTimer = setTimeout(changePage,delay);
					function changePage(){
						curpage++;
						if(curpage >= page){
							curpage = 0;
						}
//						$('#setPage a').eq(curpage).trigger('click');
						curTimer = setTimeout(changePage,delay);
					}
				}
			}
			//一屏所放的个数，CSS设置在head部分有注释
			//一屏放5个
			//ListChange('showPage',1000,5,3000);
			//一屏放6个
			ListChange('showPage',750,3,3000);
		    
			//获取产品列表
			function getProduct(obj){
				var name = $(obj).children("span").text();
				if(name==='全部'){
					name='';
				}
				$.ajax({
					cache : true,
					type : "POST",
					url : "/information/goods/product",
					data : {"name":name},
					async : false,
					success : function(data) {
						$("#divProduct").html("");
						var tr="";
						if(data.length==0){
						    tr = "<tr><td>该类下没有产品</td></tr>";
							$("#divProduct").append(tr);
						}
						else{
							for(var i=0;i<data.length;i++){
								tr+="<tr><td><img ondblclick='getGoodsDetail(this)' width='200px;' name='"+data[i].id+"' src='"+data[i].url+"'></img></td>";
								tr+="<td>"+data[i].remarks+"</td></tr>";
								$("#divProduct").append(tr);
								tr="";
						  }
						}
					}
				});
			}
			
			//获取商品的列表图展示以及相关的商品数据
		/*	function getGoods(obj){
				var name = $(obj).attr("name");//获取id值
				
				$.ajax({
					cache : true,
					type : "POST",
					url : "/information/goods/goodsImg",
					data : {"product_id":name},
					async : false,
					success : function(data) {
						$("#divImg").html("");
						if(data.length==0){
							$("#divImg").html("没有货品");
						}
						else{
							var div="";
							for(var i=0;i<data.length;i++){
								div+="<div style='display: inline-block;margin: 20px;'><img width='300' height='300' name='"+data[i].id+"' src="+data[i].url+" ondblclick='getGoodsDetail(this)'>"
								div+="<br>"
								div+="<span>"+data[i].name+"&nbsp;售价 :￥"+data[i].payPrice+"</span></div>";
								$("#divImg").append(div);
								div="";
							}
						}
					}
				});
			}*/
			
			//获取货品详情
			function getGoodsDetail(obj) {
				var id = $(obj).attr("name");
				window.location.href="/information/goods/getGoodsDetail/"+id
			}
			