$(function (){

    var path = window.location.pathname.split("/")[0];

    // Handle login
    $('#login-form').validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            },
            remember: {
                required: false
            }
        },
        messages: {
            username: {
                required: "用户名不能为空."
            },
            password: {
                required: "密码不能为空."
            }
        },
        invalidHandler: function (event, validator) { //display error alert on form submit
            $('.alert-danger', $('#login-form')).show();
            $("#usernamePwdNull").removeClass('display-hide');
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element.closest('.input-icon'));
        },
        submitHandler: function (form) {
            form.submit(); // form validation success, call ajax form submit
        }
    });
    $('#login-form').keypress(function (e) {
        if (e.which == 13) {
            if ($('#login-form').validate().form()) {
                $('#login-form').submit(); //form validation success, call ajax form submit
            }
            return false;
        }
    });

    // Handle forget password
    $('#forget-form').validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",
        rules: {
            email: {
                required: true,
                email: true
            }
        },

        messages: {
            email: {
                required: "Email is required."
            }
        },

        invalidHandler: function (event, validator) { //display error alert on form submit
        },

        highlight: function (element) { // hightlight error inputs
            $(element)
                .closest('.form-group').addClass('has-error'); // set error class to the control group
        },

        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },

        errorPlacement: function (error, element) {
            error.insertAfter(element.closest('.input-icon'));
        },

        submitHandler: function (form) {
            form.submit();
        }
    });
    $('#forget-form').keypress(function (e) {
        if (e.which == 13) {
            if ($('#forget-form').validate().form()) {
                $('#forget-form').submit();
            }
            return false;
        }
    });

});

// Show box
function show_box(id) {
    $('.widget-box.visible').removeClass('visible');
    $('#' + id).addClass('visible');
}

// Close error
function close_error() {
    $(".alert.alert-danger").addClass('display-hide');
}