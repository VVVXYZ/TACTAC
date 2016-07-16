/**
 * Created by passerby on 2016/3/17.
 */
$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#updateEnterpriseForm").validate({
        errorElement: 'span',
        errorClass: 'has-error',
        focusInvalid: true,//提交表单后,未通过验证的表单(第一个或提交之前获得焦点的未通过验证的表单)会获得焦点
        rules: {
            'mmEnterpriseSts.stockholderName':{
                required:true
            },
            'mmEnterpriseSts.shareholdingRatio':{
                required:true
            }
        },
        messages: {
            'mmEnterpriseSts.stockholderName':{
                required:"股东名字必填"
            },
            'mmEnterpriseSts.shareholdingRatio':{
                required:"所占股份必填"
            }
        },

        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').removeClass('has-info').addClass('has-error');  // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {                             //错误信息位置设置方法
            // error.insertAfter( element.parent() );                            //这里的element是录入数据的对象
            if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                var controls = element.closest('div[class*="col-"]');
                if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
                else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            }
            else error.insertAfter(element.parent());
        },
        submitHandler: function (form) {
            // form validation success, call ajax form submit
            var shareholders = $(form).find("#mainShareholder").parents("tr").next().nextAll();
            shareholders.find("[name='mmEnterpriseSts.id'],[name='mmEnterpriseSts.stockholderName'],[name='mmEnterpriseSts.shareholdingRatio']").attr("disabled" ,"disabled");
            var sendData = $(form).serializeArray();
            shareholders.find("[name='mmEnterpriseSts.id'],[name='mmEnterpriseSts.stockholderName'],[name='mmEnterpriseSts.shareholdingRatio']").removeAttr("disabled");

            for(var i=0; i<shareholders.size(); i++){
                var lastIndex = sendData.length;
                sendData[lastIndex] = new Object();
                sendData[lastIndex].name = "mmEnterpriseSts["+i+"].stockholderName";
                sendData[lastIndex].value = $(shareholders[i]).find("[name='mmEnterpriseSts.stockholderName']").val();
                lastIndex = sendData.length;
                sendData[lastIndex] = new Object();
                sendData[lastIndex].name = "mmEnterpriseSts["+i+"].shareholdingRatio";
                sendData[lastIndex].value = $(shareholders[i]).find("[name='mmEnterpriseSts.shareholdingRatio']").val();

                if($(shareholders[i]).find("[name='mmEnterpriseSts.id']").size() != 0){
                    lastIndex = sendData.length;
                    sendData[lastIndex] = new Object();
                    sendData[lastIndex].name = "mmEnterpriseSts["+i+"].id";
                    sendData[lastIndex].value = $(shareholders[i]).find("[name='mmEnterpriseSts.id']").val();
                }
            }

            $.ajax({
                type: "POST",
                url: path+"/admin/memberman/enterpriseMember/updateEnterprise",
                data: sendData,
                dataType: 'json',
                success: function (json) {
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
                                        window.location.href = "/admin/memberman/enterpriseMember/memberList";
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