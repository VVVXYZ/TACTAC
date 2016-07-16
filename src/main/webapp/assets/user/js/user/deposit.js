+function ($) {


    $(function () {
        var table_selector = "#deposit_log_table";
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
            "sAjaxSource": path + "/client/Recharge/platformRechargeLog",
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
                {"data": "payNo", "name": "payNo", 'sClass': '', "bSortable": true},
                {"data": "rechAmount", "name": "rechAmount", 'sClass': '', "bSortable": true},
                {"data": "submitTime", "name": "submitTime", 'sClass': '', "bSortable": true},
                {"data": "payType", "name": "payType", 'sClass': '', "bSortable": true},
                {"data": "payBillRemark", "name": "payBillRemark", 'sClass': '', "bSortable": true},
                {"data": "status", "name": "status", 'sClass': '', "bSortable": true},


            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });
    });
    $(function () {

        $('#platformRecharge').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                rechAmount: {
                    required: true,
                    number: true
                },
                rechFee: {
                    number: true
                }


            },
            messages: {
                rechAmount: {
                    required: "充值金额不能为空",
                    number: "必须输入合法的数字"
                },
                rechFee: {
                    number: "必须输入合法的数字"
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
                var smCreditCardId;
                var chkObjs = $(".active").filter("label").find("input:radio");
                //var chkObjs = $("label.active ");
                //console.log(chkObjs.length);
                smCreditCardId = chkObjs.val();
                console.log("smCreditCardId=" + smCreditCardId);
                document.getElementById('bankCard').value = smCreditCardId;
                $(form).ajaxSubmit({
                    //target:,
                    url: path + "/client/Recharge/platformRecharge",
                    type: "POST",
                    dataType: "json",
                    //resetForm:false,
                    clearForm: false,
                    beforeSubmit: function (formData, jqForm, options) {
                        return true;
                    },
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
                                            location.href = path + "/user/index/recharge";
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
                                            location.href = path + "/user/index/recharge";
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
                                        location.href = path + "/user/index/recharge";
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

        $('#hxRecharge').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                pTrdAmt: {
                    required: true,
                    number: true
                }


            },
            messages: {
                pTrdAmt: {
                    required: "充值金额不能为空",
                    number: "必须输入合法的数字"
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
                    url: path + "/client/Recharge/hxRecharge",
                    data: {
                        pTrdAmt: $('#pTrdAmt').val(),
                        pTrdBnkCode: $('#rechangeBankCard').val()

                    },
                    dataType: "json",
                    success: function (responseText, statusText) {
                        if (responseText.success == true) {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-success'><strong>" + responseText.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            window.open(path + '/client/Recharge/hxRecharge/' + responseText.dataObj.rechargeId);
                                            bootbox.dialog({
                                                closeButton: false,
                                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>支付情况</strong>",
                                                message: "<div class='alert '><strong>请您在新打开的的页面完成付款。<br/>付款完成前请不要关闭此窗口<br/>完成付款后请根据您的情况点击下面的按钮：</strong><br/></div>",
                                                buttons: {
                                                    "success": {
                                                        "label": "<i class='icon-ok'></i> 支付完成",
                                                        "className": "btn-sm btn-success",
                                                        "callback": function () {
                                                            location.href = path + "/user/index/recharge";
                                                        }
                                                    },
                                                    "fail": {
                                                        "label": "<i class='icon-ok'></i> 支付遇到问题,重新发起支付",
                                                        "className": "btn-sm btn-danger",
                                                        "callback": function () {

                                                        }
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            });
                        } else {
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-danger'><strong>" + responseText.msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {

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
                                        location.href = path + "/user/index/recharge";
                                    }
                                }
                            }
                        });
                    }
                    //success: function (data) {
                    //    //data = $.parseJSON(data);
                    //    console.log("data toString= " + data);
                    //    console.log("data success=" + " " + data.success + " " + data.msg);
                    //    $("#huanxun-pay-info").modal('show');
                    //
                    //},

                });
            }
        });
    });
    $(function () {
        var table_selector = "#hxdeposit_log_table";
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
            "sAjaxSource": path + "/client/Recharge/hxRechargeLog",
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
                {"data": "ptrdAmt", "name": "ptrdAmt", 'sClass': '', "bSortable": true},
                {"data": "ptrdDate", "name": "ptrdDate", 'sClass': '', "bSortable": true},
                {"data": "pchannelType", "name": "pchannelType", 'sClass': '', "bSortable": true},
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

