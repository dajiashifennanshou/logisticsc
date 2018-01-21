/**
* jQuery ligerUI 1.2.3
* 
* http://ligerui.com
*  
* Author daomi 2014 [ gd_star@163.com ] 
* 
*/
(function ($)
{

    $.fn.ligerPanel = function (options)
    {
        return $.ligerui.run.call(this, "ligerPanel", arguments);
    };

    $.ligerDefaults.Panel = {
        width: 400,
        height : 300,
        title: 'Panel',
        content: null,      //内容
        url: null,          //远程内容Url
        frameName: null,     //创建iframe时 作为iframe的name和id 
        data: null,          //可用于传递到iframe的数据
        showClose: false,    //是否显示关闭按钮
        showToggle: true,    //是否显示收缩按钮 
        icon: null,          //左侧按钮
        onClose:null,       //关闭前事件
        onClosed:null,      //关闭事件
        onLoaded:null           //url模式 加载完事件
    };

    $.ligerMethos.Panel = {};

    $.ligerui.controls.Panel = function (element, options)
    {
        $.ligerui.controls.Panel.base.constructor.call(this, element, options);
    };
    $.ligerui.controls.Panel.ligerExtend($.ligerui.core.UIComponent, {
        __getType: function ()
        {
            return 'Panel';
        },
        __idPrev: function ()
        {
            return 'Panel';
        },
        _extendMethods: function ()
        {
            return $.ligerMethos.Panel;
        },
        _init: function ()
        {
            var g = this, p = this.options;
            $.ligerui.controls.Panel.base._init.call(this);
            p.content = p.content || $(g.element).html(); 
        },
        _render: function ()
        {
            var g = this, p = this.options; 
            g.panel = $(g.element).addClass("l-panel").html("");
            g.panel.append('<div class="l-panel-header"><span></span><div class="icons"></div></div><div class="l-panel-content"></div>');
             
            g.set(p);
 
            g.panel.bind("click.panel", function (e)
            { 
                var obj = (e.target || e.srcElement), jobj = $(obj);
                if (jobj.hasClass("l-panel-header-toggle"))
                {
                    g.toggle();
                } else if (jobj.hasClass("l-panel-header-close"))
                {
                    g.close();
                }
            });
        },
        collapse: function ()
        {
            var g = this, p = this.options;
            var toggle = g.panel.find(".l-panel-header .l-panel-header-toggle:first");
            if (toggle.hasClass("l-panel-header-toggle-hide")) return;
            g.toggle();
        },
        expand: function ()
        {
            var g = this, p = this.options;
            var toggle = g.panel.find(".l-panel-header .l-panel-header-toggle:first");
            if (!toggle.hasClass("l-panel-header-toggle-hide")) return;
            g.toggle();
        },
        toggle : function()
        {
            var g = this, p = this.options;
            var toggle = g.panel.find(".l-panel-header .l-panel-header-toggle:first");
            if (toggle.hasClass("l-panel-header-toggle-hide"))
            {
                toggle.removeClass("l-panel-header-toggle-hide");
            } else
            {
                toggle.addClass("l-panel-header-toggle-hide");
            }
            g.panel.find(".l-panel-content:first").toggle("normal");
        },
        _setShowToggle:function(v)
        {
            var g = this, p = this.options;
            var header = g.panel.find(".l-panel-header:first");
            if (v)
            {
                var toggle = $("<div class='l-panel-header-toggle'></div>"); 
                toggle.appendTo(header.find(".icons")); 
            } else
            {
                header.find(".l-panel-header-toggle").remove();
            }
        },
        _setContent: function (v)
        {
            var g = this, p = this.options;
            var content = g.panel.find(".l-panel-content:first");
            if (v)
            {
                content.html(v);
            }
        },
        _setUrl: function (url)
        {
            var g = this, p = this.options;
            var content = g.panel.find(".l-panel-content:first");
            if (url)
            {
                g.jiframe = $("<iframe frameborder='0'></iframe>");
                var framename = p.frameName ? p.frameName : "ligerpanel" + new Date().getTime();
                g.jiframe.attr("name", framename);
                g.jiframe.attr("id", framename);
                content.prepend(g.jiframe); 

                setTimeout(function ()
                {
                    if (content.find(".l-panel-loading:first").length == 0)
                        content.append("<div class='l-panel-loading' style='display:block;'></div>");
                    var iframeloading = $(".l-panel-loading:first", content);
                    g.jiframe[0].panel = g;//增加窗口对panel对象的引用
                    /*
                    可以在子窗口这样使用：
                    var panel = frameElement.panel;
                    var panelData = dialog.get('data');//获取data参数
                    panel.set('title','新标题'); //设置标题
                    panel.close();//关闭dialog 
                    */
                    g.jiframe.attr("src", p.url).bind('load.panel', function ()
                    {
                        iframeloading.hide();
                        g.trigger('loaded');
                    });
                    g.frame = window.frames[g.jiframe.attr("name")];
                }, 0); 
            }
        },
        _setShowClose: function (v)
        {
            var g = this, p = this.options;
            var header = g.panel.find(".l-panel-header:first");
            if (v)
            {
                var btn = $("<div class='l-panel-header-close'></div>"); 
                btn.appendTo(header.find(".icons"));
            } else
            {
                header.find(".l-panel-header-close").remove();
            }
        },
        close:function()
        {
            var g = this, p = this.options;
            if (g.trigger('close') == false) return;
            g.panel.remove();
            g.trigger('closed');
        }, 
        show: function ()
        {
            this.panel.show();
        },
        _setIcon : function(url)
        {
            var g = this;
            if (!url)
            {
                g.panel.removeClass("l-panel-hasicon");
                g.panel.find('img').remove();
            } else
            {
                g.panel.addClass("l-panel-hasicon");
                g.panel.append('<img src="' + url + '" />');
            }
        }, 
        _setWidth: function (value)
        { 
            value && this.panel.width(value);
        },
        _setHeight: function (value)
        { 
            var g = this, p = this.options;
            var header = g.panel.find(".l-panel-header:first");
            this.panel.find(".l-panel-content:first").height(value - header.height());
        },
        _setTitle: function (value)
        {
            this.panel.find(".l-panel-header span:first").text(value);
        } 
    }); 


})(jQuery);