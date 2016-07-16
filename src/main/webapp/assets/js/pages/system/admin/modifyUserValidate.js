/**
 * Created by QiKai on 2014/4/27.
 */
$(function () {
    var path = window.location.pathname.split("/")[0];

    $.validator.setDefaults({ ignore: ":hidden:not(select)" });
    $("#modifyUserForm").validate({
        rules: {
            username: {
                required: true
            },
            roleIds: {
                required: true
            }
        },
        messages: {
            username: {
                required: "&nbsp;* 用户名不能为空！"
            },
            roleIds: {
                required: "&nbsp;* 请选择角色！"
            }
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            $(element).closest('.col-sm-1').addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
            $(".chosen-choices").css('border-color', '#b5b5b5');
        },
        errorPlacement: function (error, element) {                             //错误信息位置设置方法
            if(element.attr("name")== 'roleIds'){
                $("#roleIds-error").html(error);
                $(".chosen-choices").css('border-color','#f09784');

            }else{
                error.insertAfter(element);                            //这里的element是录入数据的对象
            }
        },
        submitHandler: function (form) {
            var sendData = $(form).serializeArray();
            var ids = $("#roleIds").val().toString().split(',');
            for(var key in ids){
                var lastIndex = sendData.length;
                sendData[lastIndex] = new Object();
                sendData[lastIndex].name = "smRoleManages["+key+"].id";
                sendData[lastIndex].value = ids[key];
            }
            $.ajax({
                type: "POST",
                url: path+"/admin/systemSetting/admin/update",
                data: sendData,
                dataType: 'json',
                success: function (json) {//名称修改成功
                    bootbox.dialog({
                        message: "<span class='bigger-110'>"+json.msg+"</span>",
                        buttons:
                        {
                            "success" :
                            {
                                "label" : "<i class='icon-ok'></i> 确定",
                                "className" : "btn-sm btn-success",
                                "callback": function() {
                                    if(json.success){
                                        window.location.href = path+'/admin/systemSetting//admin/list';
                                    }
                                }
                            }
                        }
                    });

                },
                error: function (XMLHttpRequest, errorThrown) {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>错误代码"+XMLHttpRequest.status+"，请联系管理员！</span>",
                        buttons:
                        {
                            "success" :
                            {
                                "label" : "<i class='icon-ok'></i> 确定",
                                "className" : "btn-sm btn-success",
                                "callback": function() {
                                }
                            }
                        }
                    });
                }
            });
        }
    });


});