/**
* jQuery ligerUI 1.2.3
* 
* http://ligerui.com
*  
* Author daomi 2014 [ gd_star@163.com ] 
* 
*/
( function ( $ )
{

    $.fn.ligerForm = function ()
    {
        return $.ligerui.run.call( this, "ligerForm", arguments );
    };

    $.ligerui.getConditions = function ( form, options )
    {
        if ( !form ) return null;
        form = liger.get( $( form ) );
        if ( form && form.toConditions ) return form.toConditions();
    };

    $.ligerDefaults = $.ligerDefaults || {};
    $.ligerDefaults.Form = {
        width : null,    // 表单的宽度
        //控件宽度
        inputWidth: 180,
        //标签宽度
        labelWidth: 90,
        //间隔宽度
        space: 40,
        rightToken: '：',
        //标签对齐方式
        labelAlign: 'left',
        //控件对齐方式
        align: 'left',
        //字段
        /*
        数组的集合,支持的类型包括在$.ligerDefaults.Form.editors,这个editors同Grid的editors继承于base.js中提供的编辑器集合,具体可以看liger.editors
        字段的参数参考 127行左右的 $.ligerDefaults.Form_fields,
        ui内置的编辑表单元素都会调用ui的表单插件集合,所以这些字段都有属于自己的"liger对象",可以同liger.get("[ID]")的方式获取，这里的[ID]获取方式优先级如下：
        1,定义了field.id 则取field.id 
        2,如果是下拉框和PopupEdit，并且定义了comboboxName，则取comboboxName(如果表单定义了prefixID,需要加上)
        3,默认取field.name(如果表单定义了prefixID,需要加上) 
        */
        fields: [],
        //创建的表单元素是否附加ID
        appendID: true,
        //生成表单元素ID、Name的前缀
        prefixID: null,
        //json解析函数
        toJSON: $.ligerui.toJSON,
        labelCss: null,
        fieldCss: null,
        spaceCss: null,
        onAfterSetFields: null,
        // 参数同 ligerButton
        buttons: null,              //按钮组
        readonly: false,              //是否只读
        editors: {},              //编辑器集合,使用同$.ligerDefaults.Grid.editors
        //验证
        validate: null,
        //不设置validate属性到inuput
        unSetValidateAttr: false
    };

    $.ligerDefaults.FormString = {
        invalidMessage: '存在{errorCount}个字段验证不通过，请检查!',
        detailMessage: '详细',
        okMessage: '确定'
    };

    $.ligerDefaults.Form.editors.textarea =
    {
        create: function ( container, editParm, p )
        {
            var editor = $( '<textarea class="l-textarea" />' );
            var id = ( p.prefixID || "" ) + editParm.field.name;
            if ( $( "#" + id ).length )
            {
                editor = $( "#" + id );
            }
            editor.attr( {
                id: id,
                name: id
            } );
            if ( p.readonly ) editor.attr( "readonly", true );
            container.append( editor );
            return editor;
        },
        getValue: function ( editor, editParm )
        {
            return editor.val();
        },
        setValue: function ( editor, value, editParm )
        {
            editor.val( value );
        },
        resize: function ( editor, width, height, editParm )
        {
            editor.css( {
                width: width - 2
            } ).parent().css( "width", "auto" );
        }
    };

    $.ligerDefaults.Form.editors.hidden =
    {
        create: function ( container, editParm, p )
        {
            var editor = $( '<input type = "hidden"  />' );
            var id = ( p.prefixID || "" ) + editParm.field.name;
            if ( $( "#" + id ).length )
            {
                editor = $( "#" + id );
            }
            editor.attr( {
                id: id,
                name: id
            } );
            container.append( editor );
            return editor;
        },
        getValue: function ( editor, editParm )
        {
            return editor.val();
        },
        setValue: function ( editor, value, editParm )
        {
            editor.val( value );
        }
    };

    $.ligerDefaults.Form_fields = {
        name: null,             //字段name
        textField: null,       //文本框name
        type: null,             //表单类型
        editor: null,           //编辑器扩展类型
        label: null,            //Label
        newline: true,          //换行显示
        op: null,               //操作符 附加到input
        vt: null,               //值类型 附加到input
        attr: null,             //属性列表 附加到input
        validate: null          //验证参数，比如required:true
    };

    $.ligerDefaults.Form_editor = {
    };

    //description 自动创建ligerui风格的表单-编辑器构造函数
    //editorBulider -> editorBuilder 命名错误 
    //param {jinput} 表单元素jQuery对象 比如input、select、textarea 
    $.ligerDefaults.Form.editorBulider = function ( jinput )
    {
        //这里this就是form的ligerui对象
        var g = this, p = this.options;
        var options = {}, ltype = jinput.attr( "ltype" ), field = {};
        if ( p.readonly ) options.readonly = true;
        options = $.extend( {
            width: ( field.width || p.inputWidth ) - 2
        }, field.options, field.editor, options );
        if ( ltype == "autocomplete" )
            options.autocomplete = true;
        if ( jinput.is( "select" ) )
        {
            jinput.ligerComboBox( options );
        }
        else if ( jinput.is( ":password" ) )
        {
            jinput.ligerTextBox( options );
        }
        else if ( jinput.is( ":text" ) )
        {
            switch ( ltype )
            {
                case "select":
                case "combobox":
                case "autocomplete":
                    jinput.ligerComboBox( options );
                    break;
                case "spinner":
                    jinput.ligerSpinner( options );
                    break;
                case "date":
                    jinput.ligerDateEditor( options );
                    break;
                case "popup":
                    jinput.ligerPopupEdit( options );
                    break;
                case "currency":
                    options.currency = true;
                case "float":
                case "number":
                    options.number = true;
                    jinput.ligerTextBox( options );
                    break;
                case "int":
                case "digits":
                    options.digits = true;
                default:
                    jinput.ligerTextBox( options );
                    break;
            }
        }
        else if ( jinput.is( ":radio" ) )
        {
            jinput.ligerRadio( options );
        }
        else if ( jinput.is( ":checkbox" ) )
        {
            jinput.ligerCheckBox( options );
        }
        else if ( jinput.is( "textarea" ) )
        {
            jinput.addClass( "l-textarea" );
        }
    }

    //表单组件
    $.ligerui.controls.Form = function ( element, options )
    {
        $.ligerui.controls.Form.base.constructor.call( this, element, options );
    };

    $.ligerui.controls.Form.ligerExtend( $.ligerui.core.UIComponent, {
        __getType: function ()
        {
            return 'Form'
        },
        __idPrev: function ()
        {
            return 'Form';
        },
        _init: function ()
        {
            var g = this, p = this.options;
            $.ligerui.controls.Form.base._init.call( this );
            //编辑构造器初始化
            for ( var type in liger.editors )
            {
                var editor = liger.editors[type];
                //如果没有默认的或者已经定义
                if ( !editor || type in p.editors ) continue;
                p.editors[type] = liger.getEditor( $.extend( {
                    type: type,
                    master: g
                }, editor ) );
            }
        },
        _render: function ()
        {
            var g = this, p = this.options;
            var jform = $( this.element );
            g.form = jform.is( "form" ) ? jform : jform.parents( "form:first" );
            //生成ligerui表单样式
            $( "input,select,textarea", jform ).each( function ()
            {
                p.editorBulider.call( g, $( this ) );
            } );
            g.set( p );
            if ( p.buttons )
            {
                var jbuttons = $( '<ul class="l-form-buttons"></ul>' ).appendTo( jform );
                $( p.buttons ).each( function ()
                {
                    var jbutton = $( '<li><div></div></li>' ).appendTo( jbuttons );
                    $( "div:first", jbutton ).ligerButton( this );
                } );
            }

            if ( !g.element.id ) g.element.id = g.id;
            //分组 收缩/展开
            $( "#" + g.element.id + " .togglebtn" ).live( 'click', function ()
            {
                if ( $( this ).hasClass( "togglebtn-down" ) ) $( this ).removeClass( "togglebtn-down" );
                else $( this ).addClass( "togglebtn-down" );
                var boxs = $( this ).parent().nextAll( "ul,div" );
                for ( var i = 0; i < boxs.length; i++ )
                {
                    var jbox = $( boxs[i] );
                    if ( jbox.hasClass( "l-group" ) ) break;
                    if ( $( this ).hasClass( "togglebtn-down" ) )
                    {
                        jbox.hide();
                    } else
                    {
                        jbox.show();
                    }

                }
            } );
        },
        _setWidth : function(value)
        {
            var g = this, p = this.options;
            if (value) g.form.width(value);
        },
        getEditor: function ( name )
        {
            var g = this, p = this.options;
            if ( !g.editors ) return;
            for ( var i = 0, l = p.fields.length; i < l; i++ )
            {
                var field = p.fields[i];
                if ( field.name == name && g.editors[i] )
                {
                    return g.editors[i].control;
                }
            }
        },
        getField: function ( index )
        {
            var g = this, p = this.options;
            if ( !p.fields ) return null;
            return p.fields[index];
        },
        toConditions: function ()
        {
            var g = this, p = this.options;
            var conditions = [];
            $( p.fields ).each( function ( fieldIndex, field )
            {
                var name = field.name, textField = field.textField, editor = g.editors[fieldIndex];
                if ( !editor || !name ) return;
                var value = editor.editor.getValue( editor.control )
                if ( value != null && value !== "")
                {
                    conditions.push( {
                        op: field.operator || "like",
                        field: name,
                        value: value,
                        type: field.type || "string"
                    } );
                }
            } );
            return conditions;
        },
        //预处理字段 , 排序和分组
        _preSetFields: function ( fields )
        {
            var g = this, p = this.options, lastVisitedGroup = null, lastVisitedGroupIcon = null;
            //分组： 先填充没有设置分组的字段，然后按照分组排序
            $( p.fields ).each( function ( i, field )
            {
                if ( p.readonly || field.readonly || ( field.editor && field.editor.readonly ) )
                    delete field.validate;
                if ( field.type == "hidden" ) return;
                field.type = field.type || "text";
                if ( field.newline == null ) field.newline = true;
                if ( lastVisitedGroup && !field.group )
                {
                    field.group = lastVisitedGroup;
                    field.groupicon = lastVisitedGroupIcon;
                }
                if ( field.group )
                {
                    //trim
                    field.group = field.group.toString().replace( /^\s\s*/, '' ).replace( /\s\s*$/, '' );
                    lastVisitedGroup = field.group;
                    lastVisitedGroupIcon = field.groupicon;
                }
            } );

        },
        _setReadonly: function ( readonly )
        {
            var g = this, p = this.options;
            if ( readonly && g.editors )
            {
                for ( var index in g.editors )
                {
                    var control = g.editors[index].control;
                    if ( control && control._setReadonly ) control._setReadonly( true );
                }
            }
        },
        _setFields: function ( fields )
        {
            var g = this, p = this.options;
            if ( $.isFunction( p.prefixID ) ) p.prefixID = p.prefixID( g );
            g.validate = {};
            var jform = $( this.element );
            $(".l-form-container", jform).remove();
            var lineWidth = 0, maxWidth = 0;
            //自动创建表单
            if ( fields && fields.length )
            {
                g._preSetFields( fields );
                if ( !jform.hasClass( "l-form" ) )
                    jform.addClass( "l-form" );
                var out = ['<div class="l-form-container">'];
                var appendULStartTag = false, lastVisitedGroup = null;
                var groups = [];
                $( fields ).each( function ( index, field )
                {
                    var name = field.name,
                    readonly = ( field.readonly || ( field.editor && field.editor.readonly ) ) ? true : false,
                    txtInputName = ( p.prefixID || "" ) + ( field.textField || field.id || field.name );
                    if ( field.validate && !readonly )
                    {
                        g.validate.rules = g.validate.rules || {};
                        g.validate.rules[txtInputName] = field.validate;
                        if ( field.validateMessage )
                        {
                            g.validate.messages = g.validate.messages || {};
                            g.validate.messages[txtInputName] = field.validateMessage;
                        }
                    }

                    if ( $.inArray( field.group, groups ) == -1 )
                        groups.push( field.group );
                } );
                $( groups ).each( function ( groupIndex, group )
                {
                    $( fields ).each( function ( i, field )
                    {
                        if ( field.group != group ) return;
                        var index = $.inArray( field, fields );
                        var name = field.id || field.name, newline = field.newline;
                        var inputName = ( p.prefixID || "" ) + ( field.id || field.name );
                        if ( !name ) return;
                        if ( field.type == "hidden" )
                        {
                            if ( !$( "#" + inputName ).length )
                                out.push( '<div style="display:none" id="' + ( g.id + "|" + i ) + '"></div>' );
                            return;
                        }
                        var toAppendGroupRow = field.group && field.group != lastVisitedGroup;
                        if ( index == 0 || toAppendGroupRow ) newline = true;
                        if ( newline )
                        {
                            lineWidth = 0;
                            if ( appendULStartTag )
                            {
                                out.push( '</ul>' );
                                appendULStartTag = false;
                            }
                            if ( toAppendGroupRow )
                            {
                                out.push( '<div class="l-group' );
                                if ( field.groupicon )
                                    out.push( ' l-group-hasicon' );
                                out.push( '">' );
                                if ( field.groupicon )
                                    out.push( '<img src="' + field.groupicon + '" />' );
                                out.push( '<span>' + field.group + '</span></div>' );
                                lastVisitedGroup = field.group;
                            } 
                            out.push( '<ul>' );
                            appendULStartTag = true;
                        }
                        out.push( '<li class="l-fieldcontainer' );
                        if ( newline )
                        {
                            out.push( ' l-fieldcontainer-first' );
                        }
                        out.push( '"' );
                        out.push( ' fieldindex=' + index );
                        out.push( '><ul>' );
                        //append label
                        out.push( g._buliderLabelContainer( field, index ) );
                        //append input 
                        out.push( g._buliderControlContainer( field, index ) );
                        //append space
                        out.push( g._buliderSpaceContainer( field, index ) );
                        out.push( '</ul></li>' );

                        lineWidth += (field.width || p.inputWidth || 0);
                        lineWidth += (field.space || p.space || 0);
                        lineWidth += (field.labelWidth || p.labelWidth || 0);
                        if (lineWidth > maxWidth) maxWidth = lineWidth;
                    } );
                } );
                if ( appendULStartTag )
                {
                    out.push( '</ul>' );
                    appendULStartTag = false;
                }
                out.push( '</div>' );
                jform.append(out.join(''));
                if (!p.width || maxWidth > p.width)
                {
                    jform.width(maxWidth + 10);
                }
                $( ".l-group .togglebtn", jform ).remove();
                $( ".l-group", jform ).width( jform.width() * 0.95 ).append( "<div class='togglebtn'></div>" );
            }
            ( function ()
            {
                g.editors = g.editors || {};
                $( fields ).each( function ( fieldIndex, field )
                {
                    var container = document.getElementById( g.id + "|" + fieldIndex ), editor = p.editors[field.type];
                    if ( !container ) return;
                    container = $( container );
                    var editorControl = g._createEditor( editor, container, {
                        field: field
                    }, container.width(), container.height() );
                    if ( g.editors[fieldIndex] && g.editors[fieldIndex].control && g.editors[fieldIndex].control.destroy )
                    {
                        g.editors[fieldIndex].control.destroy();
                    }
                    g.editors[fieldIndex] = {
                        control: editorControl,
                        editor: editor
                    };
                } );
                g.initValidate();
                g.trigger( 'afterSetFields' );
            } )();
            //}).ligerDefer(g, 10);
        },
        getData: function ()
        {
            var g = this, p = this.options;
            g.data = {};
            $( p.fields ).each( function ( fieldIndex, field )
            {
                var name = field.name, textField = field.textField, editor = g.editors[fieldIndex];
                if ( !editor ) return;
                if ( name )
                {
                    var value = editor.editor.getValue( editor.control )
                    g._setValueByName( g.data, name, value );
                }
                if ( textField )
                {
                    var value = editor.editor.getText( editor.control )
                    g._setValueByName( g.data, textField, value );
                }
            } );
            return g.data;
        },
        setData: function ( data )
        {
            var g = this, p = this.options;
            var fields = g.get( 'fields' );
            g.data = data || {};
            ( function ()
            {
                $( fields ).each( function ( fieldIndex, field )
                {
                    var name = field.name, textField = field.textField, editor = g.editors[fieldIndex];
                    if ( !editor ) return;
                    if ( name && ( name in g.data ) )
                    {
                        var value = g._getValueByName( g.data, name );
                        editor.editor.setValue( editor.control, value );
                    }
                    if ( textField && ( textField in g.data ) )
                    {
                        var text = g._getValueByName( g.data, textField );
                        editor.editor.setText( editor.control, text );
                    }
                } );
            } )();
            //}).ligerDefer(g, 20);
        },
        _setValueByName: function ( data, name, value )
        {
            if ( !data || !name ) return null;
            if ( name.indexOf( '.' ) == -1 )
            {
                data[name] = value;
            }
            else
            {
                try
                {
                    new Function( "data,value", "data." + name + "=value;" )( data, value );
                }
                catch ( e )
                {
                }
            }
        },
        _getValueByName: function ( data, name )
        {
            if ( !data || !name ) return null;
            if ( name.indexOf( '.' ) == -1 )
            {
                return data[name];
            }
            else
            {
                try
                {
                    return new Function( "data", "return data." + name + ";" )( data );
                }
                catch ( e )
                {
                    return null;
                }
            }
        },
        //验证
        valid: function ()
        {
            var g = this, p = this.options;
            if ( !g.form || !g.validator ) return;
            return g.form.valid();
        },
        //设置验证
        initValidate: function ()
        {
            var g = this, p = this.options;
            if ( !g.form || !p.validate || !g.form.validate )
            {
                g.validator = null;
                return;
            }
            var validate = p.validate == true ? {} : p.validate;
            var validateOptions = $.extend( {
                errorPlacement: function ( lable, element )
                {
                    if ( !element.attr( "id" ) )
                        element.attr( "id", new Date().getTime() );
                    if ( element.hasClass( "l-textarea" ) )
                    {
                        element.addClass( "l-textarea-invalid" );
                    }
                    else if ( element.hasClass( "l-text-field" ) )
                    {
                        element.parent().addClass( "l-text-invalid" );
                    }
                    $( element ).removeAttr( "title" ).ligerHideTip();
                    $( element ).attr( "title", lable.html() ).ligerTip( {
                        distanceX: 5,
                        distanceY: -3,
                        auto: true
                    } );
                },
                success: function ( lable )
                {
                    if ( !lable.attr( "for" ) ) return;
                    var element = $( "#" + lable.attr( "for" ) );

                    if ( element.hasClass( "l-textarea" ) )
                    {
                        element.removeClass( "l-textarea-invalid" );
                    }
                    else if ( element.hasClass( "l-text-field" ) )
                    {
                        element.parent().removeClass( "l-text-invalid" );
                    }
                    $( element ).removeAttr( "title" ).ligerHideTip();
                }
            }, validate, {
                rules: g.validate.rules,
                messages: g.validate.messages
            } );
            g.validator = g.form.validate( validateOptions );
        },
        //提示 验证错误信息
        showInvalid: function ( validator )
        {
            var g = this, p = this.options;
            if ( !g.validator ) return;
            var jmessage = $( '<div><div class="invalid">' + p.invalidMessage.replace( '{errorCount}', g.validator.errorList.length ) + '<a class="viewInvalidDetail" href="javascript:void(0)">' + p.detailMessage + '</a></div><div class="invalidDetail" style="display:none;">' + getInvalidInf( g.validator.errorList ) + '</div></div>' );
            jmessage.find( "a.viewInvalidDetail:first" ).bind( 'click', function ()
            {
                $( this ).parent().next( "div.invalidDetail" ).toggle();
            } );
            $.ligerDialog.open( {
                type: 'error',
                width: 350,
                showMax: false,
                showToggle: false,
                showMin: false,
                target: jmessage,
                buttons: [
                    {
                        text: p.okMessage, onclick: function ( item, dailog )
                        {
                            dailog.close();
                        }
                    }
                ]
            } );
        },
        _createEditor: function ( editorBuilder, container, editParm, width, height )
        {
            var g = this, p = this.options;
            var editor = editorBuilder.create.call( this, container, editParm, p );
            if ( editor && editorBuilder.resize )
                editorBuilder.resize.call( this, editor, width, height, editParm );
            return editor;
        },
        //标签部分
        _buliderLabelContainer: function ( field )
        {
            var g = this, p = this.options;
            var label = field.label || field.display;
            var labelWidth = field.labelWidth || field.labelwidth || p.labelWidth;
            var labelAlign = field.labelAlign || p.labelAlign;
            if ( label ) label += p.rightToken;
            var out = [];
            out.push( '<li' );
            if ( p.labelCss )
            {
                out.push( ' class="' + p.labelCss + '"' );
            }
            out.push( ' style="' );
            if ( /px$/i.test( labelWidth ) || /auto/i.test( labelWidth ) || /%$/i.test( labelWidth ) )
            {
                out.push( 'width:' + labelWidth + ';' );
            }
            else if ( labelWidth )
            {
                out.push( 'width:' + labelWidth + 'px;' );
            }
            if ( labelAlign )
            {
                out.push( 'text-align:' + labelAlign + ';' );
            }

            out.push( '">' );
            if ( label )
            {
                out.push( label );
            }
            out.push( '</li>' );
            return out.join( '' );
        },
        //控件部分
        _buliderControlContainer: function ( field, fieldIndex )
        {
            var g = this, p = this.options;
            var width = field.width || p.inputWidth;
            var align = field.align || field.textAlign || field.textalign || p.align;
            var out = [];
            out.push( '<li' );
            out.push( ' id="' + ( g.id + "|" + fieldIndex ) + '"' );
            if ( p.fieldCss )
            {
                out.push( ' class="' + p.fieldCss + '"' );
            }
            out.push( ' style="' );
            if ( width )
            {
                out.push( 'width:' + width + 'px;' );
            }
            if ( align )
            {
                out.push( 'text-align:' + align + ';' );
            }
            out.push( '">' );
            //out.push(g._buliderControl(field, fieldIndex));
            out.push( '</li>' );
            return out.join( '' );
        },
        //间隔部分
        _buliderSpaceContainer: function ( field )
        {
            var g = this, p = this.options;
            var spaceWidth = field.space || field.spaceWidth || p.space;
            if ( field.space === 0 || field.spaceWidth === 0 ) spaceWidth = 0;
            var out = [];
            out.push( '<li' );
            if ( p.spaceCss )
            {
                out.push( ' class="' + p.spaceCss + '"' );
            }
            out.push( ' style="' );
            if ( /px$/i.test( spaceWidth ) || /auto/i.test( spaceWidth ) || /%$/i.test( spaceWidth ) )
            {
                out.push( 'width:' + spaceWidth + ';' );
            }
            if ( spaceWidth )
            {
                out.push( 'width:' + spaceWidth + 'px;' );
            }
            out.push( '">' );
            if ( field.validate && field.validate.required )
            {
                out.push( "<span class='l-star'>*</span>" );
            }
            out.push( '</li>' );
            return out.join( '' );
        },
        _buliderControl: function ( field, fieldIndex )
        {
            var g = this, p = this.options;
            var width = field.width || p.inputWidth,
            name = field.name || field.id,
            type = ( field.type || "text" ).toLowerCase(),
            readonly = ( field.readonly || ( field.editor && field.editor.readonly ) ) ? true : false;
            var out = [];
            if ( type == "textarea" || type == "htmleditor" )
            {
                out.push( '<textarea ' );
            }
            else if ( $.inArray( type, ["checkbox", "radio", "password", "file"] ) != -1 )
            {
                out.push( '<input type="' + type + '" ' );
            }
            else if ( $.inArray( type, ["select", "combobox", "autocomplete", "popup", "radiolist", "checkboxlist", "listbox"] ) != -1 )
            {
                out.push( '<input type="hidden" ' );
            }
            else
            {
                out.push( '<input type="text" ' );
            }
            out.push( 'name="' + name + '" ' );
            out.push( 'fieldindex="' + fieldIndex + '" ' );
            field.cssClass && out.push( 'class="' + field.cssClass + '" ' );
            p.appendID && out.push( ' id="' + name + '" ' );
            out.push( g._getInputAttrHtml( field ) );
            if ( field.validate && !readonly )
            {
                out.push( " validate='" + p.toJSON( field.validate ) + "' " );
                g.validate = g.validate || {};
                g.validate.rules = g.validate.rules || {};
                g.validate.rules[name] = field.validate;
                if ( field.validateMessage )
                {
                    g.validate.messages = g.validate.messages || {};
                    g.validate.messages[name] = field.validateMessage;
                }
            }
            out.push( ' />' );
            return out.join( '' );
        },
        _getInputAttrHtml: function ( field )
        {
            var out = [], type = ( field.type || "text" ).toLowerCase();
            if ( type == "textarea" )
            {
                field.cols && out.push( 'cols="' + field.cols + '" ' );
                field.rows && out.push( 'rows="' + field.rows + '" ' );
            }
            out.push( 'ltype="' + type + '" ' );
            field.op && out.push( 'op="' + field.op + '" ' );
            field.vt && out.push( 'vt="' + field.vt + '" ' );
            if ( field.attr )
            {
                for ( var attrp in field.attr )
                {
                    out.push( attrp + '="' + field.attr[attrp] + '" ' );
                }
            }
            return out.join( '' );
        }
    } );


    function getInvalidInf( errorList )
    {
        var out = [];
        $( errorList ).each( function ( i, error )
        {
            var label = $( error.element ).parents( "li:first" ).prev( "li:first" ).html();
            var message = error.message;
            out.push( '<div>' + label + ' ' + message + '</div>' );
        } );
        return out.join( '' );
    }

} )( jQuery );