+function ($) {

    $(function () {

        $('#platformWithdraw').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                withdrAmount: {
                    required: true,
                    number: true
                },
                tradersPassword: {
                    required: true
                }
            },
            messages: {
                withdrAmount: {
                    required: "提现金额不能为空",
                    number: "必须输入合法的数字"
                },
                tradersPassword: {
                    required: "交易密码不能为空"
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit

            },
            highlight: function (element) { // hightlight error inputs
                $(element).closest(".form-group").addClass('has-error'); // set error class to the control group
            },
            success: function (label) {
                label.closest(".form-group").removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element);
            },
            submitHandler: function (form) {
                $.ajax({
                    type: "POST",
                    url: path + "/client/Withdraw/platformWithdraw",
                    data: {
                        withdrAmount: $('#withdrAmount').val(),
                        withdrFee: $('#withdrFee').text(),
                        memberBkCard: $('#withdrawBankCard').val(),
                        tradersPassword: $('#tradersPassword').val()

                    },
                    dataType: 'json',
                    success: function (data) {
                        //data = $.parseJSON(data);
                        console.log("data toString= " + data);
                        console.log("data success=" + " " + data.success + " " + data.msg);
                        if (data.success == true) {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-success'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/withdraw";
                                        }
                                    }
                                }
                            });


                        } else {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/withdraw";
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        var msg = "联系管理员，错误代码：" + XMLHttpRequest.status
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/withdraw";
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    });

    $(function () {
        //$("#confirm-withdraw").click(function(){
        //    $("#withdraw-tip-modal").modal('show');
        //});
        //$("#confirm-huanxun-withdraw").click(function(){
        //    $("#withdraw-tip-modal").modal('show');
        //});
        $(function () {
            var table_selector = "#withdraw_log_table";
            $(table_selector).dataTable({
                "language": { // 汉化
                    "processing": "正在加载数据...",
                    "lengthMenu": "每页显示 _MENU_ 条 ",
                    "zeroRecords": "没有您要搜索的记录",
                    "info": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
                    "infoEmpty": "记录数为0",
                    //"infoFiltered": "(全部记录数 _MAX_  条)",
                    "infoFiltered": "",
                    "infoPostFix": "",
                    "search": "搜索 ",
                    "url": "",
                    "paginate": {
                        "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                        "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                        "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                        "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
                    }
                },
                "orderMulti": false,
                "sort": true,
                "ajaxDataProp": "dataObj",
                "serverData": function (source, data, callback) {
                    var table = $(table_selector).DataTable();

                    var params = new Object();
                    params["rows"] = table.page.len();
                    params["page"] = table.page() + 1;
                    params["filed"] = table.settings()[0].aoColumns[table.order()[0][0]].name;
                    params["sortOp"] = table.settings()[0].aaSorting[0][1];
                    $.ajax({
                        "type": "GET",
                        "contentType": "application/json",
                        "url": source,
                        "dataType": "json",
                        "data": params, //以json格式传递
                        "success": function (resp) {
                            resp["recordsFiltered"] = resp["totalrecords"];
                            resp["recordsTotal"] = resp["totalrecords"];
                            callback(resp); //服务器端返回的对象的returnObject部分是要求的格式
                        }
                    });
                },
                "lengthChange": true, //改变每页显示数据数量
                "paginate": true,//分页按钮
                "stateSave": false,
                "processing": true,
                "serverSide": true,
                "sAjaxSource": path + "/client/Withdraw/platformWithdrawLog",
                "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
                "paginationType": "full_numbers",
                "columns": [
                    {
                        "data": "id",
                        "name": "id",
                        'sClass': '',
                        "bSortable": true,
                        "visible": false
                    },
                    {"data": "withdrAmount", "name": "withdrAmount", 'sClass': '', "bSortable": true},
                    {"data": "withdrFee", "name": "withdrFee", 'sClass': '', "bSortable": true},
                    {"data": "bankName", "name": "bankName", 'sClass': '', "bSortable": true},
                    {"data": "bankNumber", "name": "bankNumber", 'sClass': '', "bSortable": true},
                    {"data": "openingBankName", "name": "openingBankName", 'sClass': '', "bSortable": true},
                    {"data": "withdraStatus", "name": "withdraStatus", 'sClass': '', "bSortable": true},
                    {
                        "mData": function (row, type, val) {
                            if (row.withdraStatus == "待处理") {
                                var btn_str = '<a href="#cancel-modal" data-toggle="modal" class="btn btn-warning revoke" data-id="' + row.id + '"><i class="fa fa-undo"></i> 撤销</a>';
                                return btn_str;
                            }
                            else {
                                var btn_null = '<a></a>';
                                return btn_null;
                            }

                        },
                        "bSortable": false
                    },
                    {"data": "refuseReason", "name": "refuseReason", 'sClass': '', "bSortable": true},


                ],
                //"aaSorting": [[ 1, "asc" ]],

                "initComplete": function () {
                },
                "rowCallback": function (nRow, aData, iDisplayIndex) {
                }
            });
        });

    });

    $(document).on("click", ".revokeWithdraw", function () {
        $.ajax({
            type: "post",
            url: path + "/client/Withdraw/revoke",
            data: {
                wihtdrawRecordId: $('#withdrawId').val()
            },
            dataType: 'json',
            success: function (data) {
                //data = $.parseJSON(data);
                console.log("data toString= " + data);
                console.log("data success=" + " " + data.success + " " + data.msg);
                if (data.success == true) {
                    bootbox.dialog({
                        closeButton: false,
                        title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                        message: "<div class='alert alert-success'><strong>" + data.msg + "</strong><br/></div>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                    location.href = path + "/user/index/withdraw";
                                }
                            }
                        }
                    });

                } else {
                    bootbox.dialog({
                        closeButton: false,
                        title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                        message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                    location.href = path + "/user/index/withdraw";
                                }
                            }
                        }
                    });
                }
            },
            error: function (XMLHttpRequest, errorThrown) {
                var msg = "联系管理员，错误代码：" + XMLHttpRequest.status
                bootbox.dialog({
                    closeButton: false,
                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                    message: "<div class='alert alert-danger'><strong>" + msg + "</strong><br/></div>",
                    buttons: {
                        "success": {
                            "label": "<i class='icon-ok'></i> 确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                                location.href = path + "/user/index/withdraw";
                            }
                        }
                    }
                });
            }

        });
    });

    $(document).on("click", ".revoke", function () {
        document.getElementById('withdrawId').value = $(this).attr("data-id");

    });

    $(function () {

        $('#hxWithdrawConfirm').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                ptrdAmt: {
                    required: true,
                    number: true
                },
                hxTradersPassword: {
                    required: true
                }
            },
            messages: {
                ptrdAmt: {
                    required: "提现金额不能为空",
                    number: "只能合法的输入数字"
                },
                hxTradersPassword: {
                    required: "交易密码不能为空"
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit

            },
            highlight: function (element) { // hightlight error inputs
                $(element).closest(".form-group").addClass('has-error'); // set error class to the control group
            },
            success: function (label) {
                label.closest(".form-group").removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element);
            },
            submitHandler: function (form) {
                $.ajax({
                    type: "POST",
                    url: path + "/client/Withdraw/hxWithdraw",
                    data: {
                        pTrdAmt: $('#ptrdAmt').val()

                    },
                    dataType: 'json',

                    success: function (data) {
                        if (data.success == true) {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-success'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            window.open(path + '/client/Withdraw/hxWithdrawConfirm/' + data.dataObj.withdrawRecordId);
                                            bootbox.dialog({
                                                closeButton: false,
                                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提现申请情况</strong>",
                                                message: "<div class='alert '><strong>请您在新打开的的页面完成提现申请。<br/>提现申请完成前请不要关闭此窗口<br/>完成提现申请后请根据您的情况点击下面的按钮：</strong><br/></div>",
                                                buttons: {
                                                    "success": {
                                                        "label": "<i class='icon-ok'></i> 提现申请完成",
                                                        "className": "btn-sm btn-success",
                                                        "callback": function () {
                                                            location.href = path + "/user/index/withdraw";
                                                        }
                                                    },
                                                    "fail": {
                                                        "label": "<i class='icon-ok'></i> 提现申请遇到问题,重新发起申请",
                                                        "className": "btn-sm btn-danger",
                                                        "callback": function () {

                                                        }
                                                    }
                                                }
                                            });
                                            //location.href = path + "/user/index/withdraw";
                                        }
                                    }
                                }
                            });


                        } else {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/withdraw";
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        var msg = "联系管理员，错误代码：" + XMLHttpRequest.status
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/withdraw";
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });


    });

    $(function () {
        var table_selector = "#hxwithdraw_log_table";
        $(table_selector).dataTable({
            "language": { // 汉化
                "processing": "正在加载数据...",
                "lengthMenu": "每页显示 _MENU_ 条 ",
                "zeroRecords": "没有您要搜索的记录",
                "info": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
                "infoEmpty": "记录数为0",
                //"infoFiltered": "(全部记录数 _MAX_  条)",
                "infoFiltered": "",
                "infoPostFix": "",
                "search": "搜索 ",
                "url": "",
                "paginate": {
                    "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                    "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                    "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                    "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
                }
            },
            "orderMulti": false,
            "sort": true,
            "ajaxDataProp": "dataObj",
            "serverData": function (source, data, callback) {
                var table = $(table_selector).DataTable();

                var params = new Object();
                params["rows"] = table.page.len();
                params["page"] = table.page() + 1;
                params["filed"] = table.settings()[0].aoColumns[table.order()[0][0]].name;
                params["sortOp"] = table.settings()[0].aaSorting[0][1];
                $.ajax({
                    "type": "GET",
                    "contentType": "application/json",
                    "url": source,
                    "dataType": "json",
                    "data": params, //以json格式传递
                    "success": function (resp) {
                        resp["recordsFiltered"] = resp["totalrecords"];
                        resp["recordsTotal"] = resp["totalrecords"];
                        callback(resp); //服务器端返回的对象的returnObject部分是要求的格式
                    }
                });
            },
            "lengthChange": true, //改变每页显示数据数量
            "paginate": true,//分页按钮
            "stateSave": false,
            "processing": true,
            "serverSide": true,
            "sAjaxSource": path + "/client/Withdraw/HxWithdrawLog",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {
                    "data": "id",
                    "name": "id",
                    'sClass': '',
                    "bSortable": true,
                    "visible": false
                },
                {"data": "pmerBillNo", "name": "pmerBillNo", 'sClass': '', "bSortable": true},
                {"data": "pipsBillNo", "name": "pipsBillNo", 'sClass': '', "bSortable": true},
                {"data": "poutType", "name": "poutType", 'sClass': '', "bSortable": true},
                {"data": "ptrdAmt", "name": "ptrdAmt", 'sClass': '', "bSortable": true},
                {"data": "pdwDate", "name": "pdwDate", 'sClass': '', "bSortable": true},
                {"data": "perrMsg", "name": "perrMsg", 'sClass': '', "bSortable": true},


            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });
    });
}(window.jQuery);
