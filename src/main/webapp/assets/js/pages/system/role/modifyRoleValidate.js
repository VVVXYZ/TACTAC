$(function(){
    $("#modifyRoleForm").validate({
        rules: {
            roleName: {
                required: true
            },
            modResourceName: {
                required: true
            }
        },
        messages: {
            roleName: {
                required: "&nbsp;* 角色名称不能为空！"
            },
            modResourceName: {
                required: "&nbsp;* 角色权限必须选择！"
            }
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            $(element).closest('.col-sm-1').addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {                             //错误信息位置设置方法
            error.insertAfter(element);                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            var sendData = $(form).serializeArray();
            var ids = $("#resourceIds").val().split(',');
            for(var key in ids){
                var lastIndex = sendData.length;
                sendData[lastIndex] = new Object();
                sendData[lastIndex].name = "smResources["+key+"].id";
                sendData[lastIndex].value = ids[key];
            }
            $.ajax({
                type: "POST",
                url: path+"/admin/systemSetting/role/update",
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
                                        window.location.href = path+'/admin/systemSetting//role/list';
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