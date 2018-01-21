/**
 * created by : wasimSheh@qq.com
 */
(function(){
    /******** seajs基本配置 ********/
    seajs.config({

        // 调试模式 *重要*
        debug : true, // 为false时,'bui' 目录下 所有 'xx.js' 替换为 'xx-min.js'

        // 是否模拟数据
        mock : true,

        // 文件编码
        charset : 'utf-8',

        // 变量配置
        vars : {
            locale : 'zh-cn'
        },

        // 别名配置
        alias : {},

        // 路径配置
        paths : {},

        // 映射配置
        map : [],

        // seajs 的路径配置（这里下面会自动配置）
        // 其他 js 的载入相对于此路径
        base : ''
    });

    var BUI = window.BUI = window.BUI || {};
        BUI.use = seajs.use;
        BUI.config = seajs.config;

    //********* 自动设置加载器 seajs 参数***********/
    (function( seajs ){

        // 获取当前页面路径
        function getCurrentPath(){
            var url = location.href.replace(/\?.*$/,'').replace(/\#.*$/,'');
            return url.substring( 0, url.lastIndexOf('/') + 1 );
        }

        function getScriptAbsoluteSrc( node ) {
            var src = node.src;
            // ie6-7 指定的是相对路径需自己拼接
            return /:/.test(src) ? src : getCurrentPath() + src;
        }

        var scripts = document.getElementsByTagName('script'),
            loaderScript = scripts[scripts.length - 1],
            src = getScriptAbsoluteSrc(loaderScript);

        var loaderPath = src.substring(0, src.lastIndexOf('/')),
            buiPath = loaderPath + '/bui';

        // ** 设置 'sea.js' 的路径 **
        seajs.config({  base : loaderPath });

        //  如果是 debug 模式，bui框架下的js 启用压缩版本
        if ( !seajs.data.debug ) {// !前面设置的 'debug'参数 存在于data里面
            var regexp = new RegExp('^(' + buiPath + '\\S*).js$');
            seajs.config({
                map: [
                    [regexp, '$1-min.js']
                ]
            });
            // TODO :
        }

        //********** 定义一个 jq模块 以便bui框架使用 ************/
        if ( typeof define === "function" && define.cmd ) {
            if ( !jQuery ) console.log(' bui need jQuery! ');
            define( "jquery", [], function () { return jQuery; } );
        }

        // 载入数据模拟及其依赖模块
        if ( seajs.data.mock ) {
            // TODO :
        }
    })( seajs );
})();