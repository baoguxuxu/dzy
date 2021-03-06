;(function($){

  var data = {}, dataAttr = $.fn.data, camelize = $.camelCase,

    exp = $.expando = 'Zepto' + (+new Date()), emptyArray = []



  // Get value from node:

  // 1. first try key as given,

  // 2. then try camelized key,

  // 3. fall back to reading "data-*" attribute.

  function getData(node, name) {

    var id = node[exp], store = id && data[id]

    if (name === undefined) return store || setData(node)

    else {

      if (store) {

        if (name in store) return store[name]

        var camelName = camelize(name)

        if (camelName in store) return store[camelName]

      }

      return dataAttr.call($(node), name)

    }

  }



  // Store value under camelized key on node

  function setData(node, name, value) {

    var id = node[exp] || (node[exp] = ++$.uuid),

      store = data[id] || (data[id] = attributeData(node))

    if (name !== undefined) store[camelize(name)] = value

    return store

  }



  // Read all "data-*" attributes from a node

  function attributeData(node) {

    var store = {}

    $.each(node.attributes || emptyArray, function(i, attr){

      if (attr.name.indexOf('data-') == 0)

        store[camelize(attr.name.replace('data-', ''))] =

          $.zepto.deserializeValue(attr.value)

    })

    return store

  }



  $.fn.data = function(name, value) {

    return value === undefined ?

      // set multiple values via object

      $.isPlainObject(name) ?

        this.each(function(i, node){

          $.each(name, function(key, value){ setData(node, key, value) })

        }) :

        // get value from first element

        (0 in this ? getData(this[0], name) : undefined) :

      // set value on all elements

      this.each(function(){ setData(this, name, value) })

  }



  $.fn.removeData = function(names) {

    if (typeof names == 'string') names = names.split(/\s+/)

    return this.each(function(){

      var id = this[exp], store = id && data[id]

      if (store) $.each(names || store, function(key){

        delete store[names ? camelize(this) : key]

      })

    })

  }



  // Generate extended `remove` and `empty` functions

  ;['remove', 'empty'].forEach(function(methodName){

    var origFn = $.fn[methodName]

    $.fn[methodName] = function() {

      var elements = this.find('*')

      if (methodName === 'remove') elements = elements.add(this)

      elements.removeData()

      return origFn.call(this)

    }

  })

})($);


!function($) {
	var _private = {};
	_private.cache = {};
	$.tpl = function(str, data, env) {
		// 判断str参数，如str为script标签的id，则取该标签的innerHTML，再递归调用自身
		// 如str为HTML文本，则分析文本并构造渲染函数
		var fn = !/[^\w\-\.:]/.test(str) ? _private.cache[str] = _private.cache[str]
				|| this.get(document.getElementById(str).innerHTML)
				: function(data, env) {
					var i, variable = [], value = []; // variable数组存放变量名，对应data结构的成员变量；value数组存放各变量的值
					for (i in data) {
						variable.push(i);
						value.push(data[i]);
					}
					return (new Function(variable, fn.code)).apply(env || data,
							value); // 此处的new
									// Function是由下面fn.code产生的渲染函数；执行后即返回渲染结果HTML
				};

		fn.code = fn.code || "var $parts=[]; $parts.push('"
				+ str.replace(/\\/g, '\\\\') // 处理模板中的\转义
				.replace(/[\r\t\n]/g, " ") // 去掉换行符和tab符，将模板合并为一行
				.split("<%").join("\t") // 将模板左标签<%替换为tab，起到分割作用
				.replace(/(^|%>)[^\t]*/g, function(str) {
					return str.replace(/'/g, "\\'");
				}) // 将模板中文本部分的单引号替换为\'
				.replace(/\t=(.*?)%>/g, "',$1,'") // 将模板中<%=
													// %>的直接数据引用（无逻辑代码）与两侧的文本用'和,隔开，同时去掉了左标签产生的tab符
				.split("\t").join("');") // 将tab符（上面替换左标签产生）替换为');
											// 由于上一步已经把<%=产生的tab符去掉，因此这里实际替换的只有逻辑代码的左标签
				.split("%>").join("$parts.push('") // 把剩下的右标签%>（逻辑代码的）替换为"$parts.push('"
				+ "'); return $parts.join('');"; // 最后得到的就是一段JS代码，保留模板中的逻辑，并依次把模板中的常量和变量压入$parts数组

		return data ? fn(data, env) : fn; // 如果传入了数据，则直接返回渲染结果HTML文本，否则返回一个渲染函数
	};
	$.adaptObject = function(element, defaults, option, template, plugin,
			pluginName) {
		var $this = element;
		if (typeof option != 'string') {

			// 获得配置信息
			var context = $.extend({}, defaults, typeof option == 'object'
					&& option);

			var isFromTpl = false;
			// 如果传入script标签的选择器
			if ($.isArray($this) && $this.length
					&& $($this)[0].nodeName.toLowerCase() == "script") {
				// 根据模板获得对象并插入到body中
				$this = $($.tpl($this[0].innerHTML, context)).appendTo("body");
				isFromTpl = true;
			}
			// 如果传入模板字符串
			else if ($.isArray($this) && $this.length && $this.selector == "") {
				// 根据模板获得对象并插入到body中
				$this = $($.tpl($this[0].outerHTML, context)).appendTo("body");
				isFromTpl = true;
			}
			// 如果通过$.dialog()的方式调用
			else if (!$.isArray($this)) {
				// 根据模板获得对象并插入到body中
				$this = $($.tpl(template, context)).appendTo("body");
				isFromTpl = true;
			}

		}
		return $this.each(function() {

			var el = $(this);
			// 读取对象缓存
			var data = el.data('fz.' + pluginName);
			if (!data)
				el.data('fz.' + pluginName, (data = new plugin(this, $.extend(
						{}, defaults, typeof option == 'object' && option),
						isFromTpl)

				));

			if (typeof option == 'string')
				data[option]();
		})
	}
}($);

!function($) {
	// 默认模板
	var _dialogTpl = '<div class="ui-dialog">'
			+ '<div class="ui-dialog-cnt">'
			+ '<div class="ui-dialog-bd">'
			+ '<div>'
			+ '<h4><%=title%></h4>'
			+ '<div><%=content%></div></div>'
			+ '</div>'
			+ '<div class="ui-dialog-ft ui-btn-group">'
			+ '<% for (var i = 0; i < button.length; i++) { %>'
			+ '<% if (i == select) { %>'
			+ '<button type="button" data-role="button"  class="select" id="dialogButton<%=i%>"><%=button[i]%></button>'
			+ '<% } else { %>'
			+ '<button type="button" data-role="button" id="dialogButton<%=i%>"><%=button[i]%></div>'
			+ '<% } %>' + '<% } %>' + '</div>' + '</div>' + '</div>';
	// 默认参数
	var defaults = {
		title : '',
		content : '',
		button : [ '确认' ],
		select : 0,
		allowScroll : false,
		callback : function() {
		}
	}
	// 构造函数
	var Dialog = function(el, option, isFromTpl) {
		this.option = $.extend(defaults, option);
		this.element = $(el);
		this._isFromTpl = isFromTpl;
		this.button = $(el).find('[data-role="button"]');
		this._bindEvent();
		this.toggle();
	}
	Dialog.prototype = {
		_bindEvent : function() {
			var self = this;
			self.button.on("click", function() {
				var index = $(self.button).index($(this));
				// self.option.callback("button",index);
				var e = $.Event("dialog:action");
				e.index = index;
				self.element.trigger(e);
				
				self.hide.apply(self);
			});
		},
		toggle : function() {
			if (this.element.hasClass("show")) {
				this.hide();
			} else {
				this.show();
			}
		},
		show : function() {
			var self = this;
			// self.option.callback("show");
			self.element.trigger($.Event("dialog:show"));
			self.element.addClass("show");
			this.option.allowScroll
					&& self.element.on("touchmove", _stopScroll);

		},
		hide : function() {
			var self = this;
			// self.option.callback("hide");
			self.element.trigger($.Event("dialog:hide"));
			self.element.off("touchmove", _stopScroll);
			self.element.removeClass("show");

			self._isFromTpl && self.element.remove();
		}
	}
	// 禁止冒泡
	function _stopScroll() {
		return false;
	}
	function Plugin(option) {
		return $.adaptObject(this, defaults, option, _dialogTpl, Dialog,
				"dialog");
	}
	$.fn.dialog = $.dialog = Plugin;
}($)


/**
 * User: jeakeyliang
 * Date: 14-11-07
 * Time: 下午9:20
 */

!function($){

	// 默认模板
	var _loadingTpl='<div class="ui-loading-block show">'+
		    '<div class="ui-loading-cnt">'+
		      '<i class="ui-loading-bright"></i>'+
		      '<p><%=content%></p>'+
		   '</div>'+
		 '</div>';
	
	// 默认参数
	var defaults={
		content:'加载中...'
	}
	// 构造函数
	var Loading   = function (el,option,isFromTpl) {
		var self=this;
		this.element=$(el);
		this._isFromTpl=isFromTpl;
		this.option=$.extend(defaults,option);
		this.show();
	}
	Loading.prototype={
		show:function(){
			var e=$.Event('loading:show');
			this.element.trigger(e);
			this.element.show();
			
		},
		hide :function () {
			var e=$.Event('loading:hide');
			this.element.trigger(e);
			this.element.remove();
		}
	}
	function Plugin(option) {

		return $.adaptObject(this, defaults, option,_loadingTpl,Loading,"loading");
	}
	$.fn.loading=$.loading= Plugin;
}($)