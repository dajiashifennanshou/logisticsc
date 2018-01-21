/**
 * jQuery EnPlaceholder plug
 * EnPlaceholder鏄竴涓法娴忚鍣ㄥ疄鐜皃laceholder鏁堟灉鐨刯Query鎻掍欢
 * version 1.0
 * by Frans.Lee <dmon@foxmail.com>  http://www.ifrans.cn
 */
;(function ($) {
    $.fn.extend({
        "placeholder":function (options) {
            options = $.extend({
                placeholderColor:'#ACA899',
                isUseSpan:false, //鏄惁浣跨敤鎻掑叆span鏍囩妯℃嫙placeholder鐨勬柟寮�,榛樿false,榛樿浣跨敤value妯℃嫙
                onInput:true  //浣跨敤鏍囩妯℃嫙(isUseSpan涓簍rue)鏃讹紝鏄惁缁戝畾onInput浜嬩欢鍙栦唬focus/blur浜嬩欢
            }, options);
			
            $(this).each(function () {
                var _this = this;
                var supportPlaceholder = 'placeholder' in document.createElement('input');
                if (!supportPlaceholder) {
                    var defaultValue = $(_this).attr('placeholder');
                    var defaultColor = $(_this).css('color');
                    if (options.isUseSpan == false) {
                        $(_this).focus(function () {
                            var pattern = new RegExp("^" + defaultValue + "$|^$");
                            pattern.test($(_this).val()) && $(_this).val('').css('color', defaultColor);
                        }).blur(function () {
                                if ($(_this).val() == defaultValue) {
                                    $(_this).css('color', defaultColor);
                                } else if ($(_this).val().length == 0) {
                                    $(_this).val(defaultValue).css('color', options.placeholderColor)
                                }
                            }).trigger('blur');
                    } else {
                        var $imitate = $('<span class="wrap-placeholder" style="position:absolute; display:inline-block; overflow:hidden; color:'+options.placeholderColor+'; width:'+$(_this).outerWidth()+'px; height:'+$(_this).outerHeight()+'px;">' + defaultValue + '</span>');
                        $imitate.css({
                            'margin-left':$(_this).css('margin-left'),
                            'margin-top':$(_this).css('margin-top'),
                            'font-size':$(_this).css('font-size'),
                            'font-family':$(_this).css('font-family'),
                            'font-weight':$(_this).css('font-weight'),
                            'padding-left':parseInt($(_this).css('padding-left')) + 2 + 'px',
                            'line-height':_this.nodeName.toLowerCase() == 'textarea' ? $(_this).css('line-weight') : $(_this).outerHeight() + 'px',
                            'padding-top':_this.nodeName.toLowerCase() == 'textarea' ? parseInt($(_this).css('padding-top')) + 2 : 0
                        });
                        $(_this).before($imitate.click(function () {
                            $(_this).trigger('focus');
                        }));

                        $(_this).val().length != 0 && $imitate.hide();

                        if (options.onInput) {
                            //缁戝畾oninput/onpropertychange浜嬩欢
                            var inputChangeEvent = typeof(_this.oninput) == 'object' ? 'input' : 'propertychange';
                            $(_this).bind(inputChangeEvent, function () {
                                $imitate[0].style.display = $(_this).val().length != 0 ? 'none' : 'inline-block';
                            });
                        } else {
                            $(_this).focus(function () {
                                $imitate.hide();
                            }).blur(function () {
                                    /^$/.test($(_this).val()) && $imitate.show();
                                });
                        }
                    }
                }
            });
            return this;
        }
    });
})(jQuery);