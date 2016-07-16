/**
 * Created by QiKai on 2014/4/27.
 */
$(function () {
    var path = window.location.pathname.split("/")[0];

    $("#createUserForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            },
            tel: {
                number: true
            },
            roleIds: {
                required: true
            },
            orgName: {
                required: true
            }
        },
        messages: {
            username: {
                required: "&nbsp;* 用户名不能为空！"
            },
            password: {
                required: "&nbsp;* 密码不能为空！"
            },
            tel: {
                number: "&nbsp;* 请输入正确的联系方式！"
            },
            roleIds: {
                required: "&nbsp;* 请选择角色！"
            },
            orgName: {
                required: "&nbsp;* 请选择部门！"
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
            form.submit();
        }
    });
});