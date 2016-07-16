
+function ($) {

    $(function () {
        var table_selector1 = "#transferable_project_table";
        $(table_selector1).dataTable({
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
                var table = $(table_selector1).DataTable();

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
            "sAjaxSource": path + "/client/myDebtAssignment/webTransferableDebts",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {"data": "bidRecordId", "name": "id", 'sClass': '', "bSortable": true, "visible": false},
                {
                    "data": function (row, type, val) {

                        return '<a href="' + path + '/client/investmentProjectDetails/projectDetail?isHx=' + (row.bidType == 0 ? 'false' : 'true') + '&loanId=' + row.lmLoanRecordId + '"title="' + row.loanName + '" class="text-purple">' + row.loanName + '</a>';
                    },
                    "name": "lmLoanRecord.loanName",
                    "bSortable": true
                },
                {"data": "repayType", "name": "lmLoanRecord.repayType", 'sClass': '', "bSortable": true},
                {"data": "loanTerm", "name": "lmLoanRecord.projectDate", 'sClass': '', "bSortable": true},
                {"data": "investAmount", "name": "investAmount", 'sClass': '', "bSortable": true},
                {"data": "incomes", "name": "incomes", 'sClass': '', "bSortable": false},
                {"data": "transferableAmount", "name": "investAmount", 'sClass': '', "bSortable": true},
                //{"data": "residualInterest", "name": "residualInterest", 'sClass': '', "bSortable": true,"visible":false},

                {
                    "data": function (row, type, val) {

                        return '<a href="#transfer-modal" title="转让" data-id="' + row.bidRecordId + '"  data-loanName="' + row.loanName + '"  data-repayType="' + row.repayType + '" data-loanTerm="' + row.loanTerm + '" ' +
                            'data-investAmount="' + row.investAmount + '" data-incomes="' + row.incomes + '" data-transferableAmount="' + row.transferableAmount + '" data-phone="' + row.phone + '"  data-lmLoanRecordId="' + row.lmLoanRecordId + '" ' +
                            'class="text-link m-l-sm transfer" data-toggle="modal">转让</a>';
                    },
                    "bSortable": false
                }
            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });

        $(document).on("click", ".transfer", function () {
            document.getElementById('bidRecordId').value = $(this).attr("data-id");
            document.getElementById('lmLoanRecordId').value = $(this).attr("data-lmLoanRecordId");
            document.getElementById('phone').value = $(this).attr("data-phone");
            $("#loanName").html($(this).attr("data-loanName"));
            $("#repayType").html($(this).attr("data-repayType"));
            $("#loanTerm").html($(this).attr("data-loanTerm"));
            $("#investAmount").html($(this).attr("data-investAmount"));
            $("#incomes").html($(this).attr("data-incomes"));
            $("#transferableAmount").html($(this).attr("data-transferableAmount"));
            getDateAndprofit();
        });

        function getDateAndprofit() {
            var data = new Date();
            data.setDate(data.getDate() + 7);
            var y = data.getFullYear();
            var m = data.getMonth() + 1;//获取当前月份的日期
            var d = data.getDate();
            var buyData = y + "-" + m + "-" + d;
            document.getElementById('assTime').value = buyData;
            $.ajax({
                type: "POST",
                url: path + "/client/myDebtAssignment/getResidualInterest",
                data: {
                    assTime: $('#assTime').val(),
                    lmLoanRecordId: $('#lmLoanRecordId').val(),
                    bidRecordId: $('#bidRecordId').val()

                },
                dataType: "json",
                success: function (json) {
                    if (json.success == true) {
                    var data = json.dataObj;
                        //console.log("data:"+data);
                    $("#residualInterest").html(data.residualIncome);
                }
                    else {
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + json.msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/myTransfer";
                                    }
                                }
                            }
                        });
                    }
                }
            });

        }

        $(document).on("click", "#transferVerificationCode", function () {
            var i = 0;
            var interval = setInterval(function () {
                i++;
                if (i == 60) {
                    $("#transferVerificationCode").attr("disabled", false);
                    $("#transferVerificationCode").text("获取短信验证码");
                    window.clearInterval(interval);
                } else {
                    $("#transferVerificationCode").attr("disabled", true);
                    $("#transferVerificationCode").text((60 - i) + "s后重新获取");
                }
            }, 1000);
            $.ajax({
                type: "post",
                url: path + "/client/sendIdentifyingCode",
                data: {
                    phoneNumber: $('#phone').val(),
                    identifyingType: 5
                },
                dataType: 'json',
                success: function (json) {

                    //$("#Message").html(json.msg);

                }
            });


        });

        $(function () {
            $('#debtsTransferConfirm').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    tradersPassword: {
                        required: true
                    },
                    verificationCode: {
                        required: true
                    }
                },
                messages: {
                    tradersPassword: {
                        required: "支付密码不能为空."
                    },
                    verificationCode: {
                        required: "验证码不能为空"
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
                        url: path + "/client/myDebtAssignment/webDebtsTransferConfirm",
                        type: "POST",
                        data: {
                            assAmount: $('#transferableAmount').html(),
                            assTime: $('#assTime').val(),
                            bidRecordId: $('#bidRecordId').val(),
                            tradersPassword: $('#tradersPassword').val(),
                            verificationCode: $('#verificationCode').val()


                        },
                        dataType: "json",

                        success: function (data) {

                            if (data.success == true) {
                                bootbox.dialog({
                                    closeButton: false,
                                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                    message: "<div class='alert alert-success'><strong>发布转让消息成功</strong><br/></div>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                location.href = path + "/user/index/myTransfer";
                                            }
                                        }
                                    }
                                });

                            } else {
                                console.log("data:=" + data);
                                bootbox.dialog({
                                    closeButton: false,
                                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                    message: "<div class='alert alert-danger'><strong>" + data.msg + "</strong><br/></div>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                location.href = path + "/user/index/myTransfer";
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
                                            location.href = path + "/user/index/myTransfer";
                                        }
                                    }
                                }
                            });
                        }


                    })
                }
            });
        });

        //$.validator.addMethod("compareDate", function (value, element) {
        //    var assTime = $("#assTime").val();
        //    var reg = new RegExp('-', 'g');
        //    assTime = assTime.replace(reg, '/');//正则替换
        //    assTime = new Date(parseInt(Date.parse(assTime), 10));
        //    var nowDate = new Date();
        //    return assTime > nowDate;
        //}, "<p color='#E47068'>转让时间必须大于今日</p>");


        var table_selector2 = "#transferring_project_table";
        $(table_selector2).dataTable({
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
                var table = $(table_selector2).DataTable();

                var params = new Object();
                params["assStatus"] = 1;
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
            "sAjaxSource": path + "/client/myDebtAssignment/webPlConditionQuery",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {"data": "lmDebtAssId", "name": "id", 'sClass': '', "bSortable": true, "visible": false},
                {
                    "data": function (row, type, val) {

                        return '<a href="' + path + '/client/investmentProjectDetails/projectDetail?isHx=' + (row.bidType == 0 ? 'false' : 'true') + '&loanId=' + row.lmLoanRecordId + '" title="' + row.lmDebtAssId + '" class="text-purple">' + row.loanName + '</a>';
                    },
                    "name": "lmBidRecord.lmLoanRecord.loanName",
                    "bSortable": true
                },
                {"data": "repayType", "name": "lmBidRecord.lmLoanRecord.repayType", 'sClass': '', "bSortable": true},
                {"data": "investAmount", "name": "lmBidRecord.investAmount", 'sClass': '', "bSortable": true},
                {"data": "assAmount", "name": "assAmount", 'sClass': '', "bSortable": true},
                {"data": "resIncome", "name": "resIncome", 'sClass': '', "bSortable": true},
                {"data": "issueTime", "name": "issueTime", 'sClass': '', "bSortable": true},
                {"data": "assTime", "name": "assTime", 'sClass': '', "bSortable": true},

                {
                    "data": function (row, type, val) {

                        return '<a href="#repeal-modal" title="撤销" data-id=" ' + row.lmDebtAssId + '" class="text-link m-l-sm revoke"  data-toggle="modal">撤销</a>';
                    },

                    "bSortable": false
                }
            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });

        $(document).on("click", ".revoke", function () {
            document.getElementById('lmDebtAssId').value = $(this).attr("data-id");
        });

        $("#debtsTransferRevoke").click(function () {
            $.ajax({
                url: path + "/client/myDebtAssignment/debtsTransferRevoke",
                type: "post",
                data: {
                    debtAssId: $('#lmDebtAssId').val()
                },
                dataType: 'json',

                success: function (data) {
                    if (data.success == true) {
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-success'><strong>撤销成功</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/myTransfer";
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
                                        location.href = path + "/user/index/myTransfer";
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
                                    location.href = path + "/user/index/myTransfer";
                                }
                            }
                        }
                    });
                }


            })

        });

        var table_selector3 = "#transfered_project_table";
        $(table_selector3).dataTable({
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
                var table = $(table_selector3).DataTable();

                var params = new Object();
                params["assStatus"] = 10;
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
            "sAjaxSource": path + "/client/myDebtAssignment/webPlConditionQuery",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {"data": "lmDebtAssId", "name": "id", 'sClass': '', "bSortable": true, "visible": false},
                {
                    "data": function (row, type, val) {

                        return '<a href="' + path + '/client/investmentProjectDetails/projectDetail?isHx=' + (row.bidType == 0 ? 'false' : 'true') + '&loanId=' + row.lmLoanRecordId + '" title="' + row.loanName + '" class="text-purple">' + row.loanName + '</a>';
                    },
                    "name": "lmBidRecord.lmLoanRecord.loanName",
                    "bSortable": true
                },
                {"data": "repayType", "name": "lmBidRecord.lmLoanRecord.repayType", 'sClass': '', "bSortable": true},
                {"data": "investAmount", "name": "lmBidRecord.investAmount", 'sClass': '', "bSortable": true},
                {"data": "assAmount", "name": "assAmount", 'sClass': '', "bSortable": true},
                {"data": "resIncome", "name": "resIncome", 'sClass': '', "bSortable": true},
                {"data": "issueTime", "name": "issueTime", 'sClass': '', "bSortable": true},
                {"data": "assTime", "name": "assTime", 'sClass': '', "bSortable": true},


            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });


    });

    $(function () {
        var table_selector1 = "#hxtransferable_project_table";
        $(table_selector1).dataTable({
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
                var table = $(table_selector1).DataTable();

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
            "sAjaxSource": path + "/client/myDebtAssignment/webHxTransferableDebts",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {"data": "bidRecordId", "name": "id", 'sClass': '', "bSortable": true, "visible": false},
                {
                    "data": function (row, type, val) {

                        return '<a href="' + path + '/client/investmentProjectDetails/projectDetail?isHx=' + (row.bidType == 0 ? 'false' : 'true') + '&loanId=' + row.lmLoanRecordId + '" title="' + row.loanName + '" class="text-purple">' + row.loanName + '</a>';
                    },
                    "name": "lmHxLoanRecord.loanName",
                    "bSortable": true
                },
                {"data": "repayType", "name": "lmHxLoanRecord.repayType", 'sClass': '', "bSortable": true},
                {"data": "loanTerm", "name": "lmHxLoanRecord.projectDate", 'sClass': '', "bSortable": true},
                {"data": "investAmount", "name": "investAmount", 'sClass': '', "bSortable": true},
                {"data": "incomes", "name": "incomes", 'sClass': '', "bSortable": false},
                {"data": "transferableAmount", "name": "investAmount", 'sClass': '', "bSortable": true},
                //{"data": "residualInterest", "name": "residualInterest", 'sClass': '', "bSortable": true,"visible":false},

                {
                    "data": function (row, type, val) {

                        return '<a href="#hxtransfer-modal" title="转让" data-id="' + row.bidRecordId + '"  data-loanName="' + row.loanName + '"  data-repayType="' + row.repayType + '" data-loanTerm="' + row.loanTerm + '" ' +
                            'data-investAmount="' + row.investAmount + '" data-incomes="' + row.incomes + '" data-transferableAmount="' + row.transferableAmount + '" data-phone="' + row.phone + '"  data-lmLoanRecordId="' + row.lmLoanRecordId + '" ' +
                            'class="text-link m-l-sm hxTransfer" data-toggle="modal">转让</a>';
                    },
                    "bSortable": false
                }
            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });

        $(document).on("click", ".hxTransfer", function () {
            document.getElementById('hxbidRecordId').value = $(this).attr("data-id");
            document.getElementById('hxlmLoanRecordId').value = $(this).attr("data-lmLoanRecordId");
            $("#hxloanName").html($(this).attr("data-loanName"));
            $("#hxrepayType").html($(this).attr("data-repayType"));
            $("#hxloanTerm").html($(this).attr("data-loanTerm"));
            $("#hxinvestAmount").html($(this).attr("data-investAmount"));
            $("#hxincomes").html($(this).attr("data-incomes"));
            $("#hxtransferableAmount").html($(this).attr("data-transferableAmount"))
            getHxDateAndprofit();
        });

        function getHxDateAndprofit() {
            var data = new Date();
            data.setDate(data.getDate() + 7);
            var y = data.getFullYear();
            var m = data.getMonth() + 1;//获取当前月份的日期
            var d = data.getDate();
            var buyData = y + "-" + m + "-" + d;
            document.getElementById('hxassTime').value = buyData;
            $.ajax({
                type: "POST",
                url: path + "/client/myDebtAssignment/getHxResidualInterest",
                data: {
                    assTime: $('#hxassTime').val(),
                    lmLoanRecordId: $('#hxlmLoanRecordId').val(),
                    bidRecordId: $('#hxbidRecordId').val()

                },
                dataType: "json",
                success: function (json) {
                    var data = json.dataObj;
                    $("#hxresidualInterest").html(data.residualIncome);
                }
            });

        }

        $(function () {
            $('#hxdebtsTransferConfirm').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    hxtradersPassword: {
                        required: true
                    }
                },
                messages: {
                    hxtradersPassword: {
                        required: "支付密码不能为空."
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
                        url: path + "/client/myDebtAssignment/webHxDebtsTransferConfirm",
                        type: "post",
                        data: {
                            assAmount: $('#hxtransferableAmount').html(),
                            assTime: $('#hxassTime').val(),
                            bidRecordId: $('#hxbidRecordId').val(),
                            tradersPassword: $('#hxtradersPassword').val()

                        },
                        dataType: 'json',

                        success: function (data) {
                            if (data.success == true) {
                                bootbox.dialog({
                                    closeButton: false,
                                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                    message: "<div class='alert alert-success'><strong>发布转让消息成功</strong><br/></div>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                location.href = path + "/user/index/myTransfer";
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
                                                location.href = path + "/user/index/myTransfer";
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
                                            location.href = path + "/user/index/myLoan";
                                        }
                                    }
                                }
                            });
                        }


                    })
                }
            });
        });

        //$.validator.addMethod("hxcompareDate", function (value, element) {
        //    var assTime = $("#hxassTime").val();
        //    var reg = new RegExp('-', 'g');
        //    assTime = assTime.replace(reg, '/');//正则替换
        //    assTime = new Date(parseInt(Date.parse(assTime), 10));
        //    var nowDate = new Date();
        //    return assTime > nowDate;
        //}, "<p color='#E47068'>转让时间必须大于今日</p>");


        var table_selector2 = "#hxtransferring_project_table";
        $(table_selector2).dataTable({
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
                var table = $(table_selector2).DataTable();

                var params = new Object();
                params["assStatus"] = 1;
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
            "sAjaxSource": path + "/client/myDebtAssignment/webHxPlConditionQuery",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {"data": "lmDebtAssId", "name": "id", 'sClass': '', "bSortable": true, "visible": false},
                {
                    "data": function (row, type, val) {

                        return '<a href="' + path + '/client/investmentProjectDetails/projectDetail?isHx=' + (row.bidType == 0 ? 'false' : 'true') + '&loanId=' + row.lmLoanRecordId + '" title="' + row.lmDebtAssId + '" class="text-purple">' + row.loanName + '</a>';
                    },
                    "name": "lmHxBidRecord.lmHxLoanRecord.loanName",
                    "bSortable": true
                },
                {
                    "data": "repayType",
                    "name": "lmHxBidRecord.lmHxLoanRecord.repayType",
                    'sClass': '',
                    "bSortable": true
                },
                {"data": "investAmount", "name": "lmHxBidRecord.investAmount", 'sClass': '', "bSortable": true},
                {"data": "assAmount", "name": "assAmount", 'sClass': '', "bSortable": true},
                {"data": "resIncome", "name": "resIncome", 'sClass': '', "bSortable": true},
                {"data": "issueTime", "name": "issueTime", 'sClass': '', "bSortable": true},
                {"data": "assTime", "name": "assTime", 'sClass': '', "bSortable": true},

                {
                    "data": function (row, type, val) {

                        return '<a href="#hxrepeal-modal" title="撤销" data-id=" ' + row.lmDebtAssId + '" class="text-link m-l-sm hxRevoke"  data-toggle="modal">撤销</a>';
                    },

                    "bSortable": false
                }
            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });

        $(document).on("click", ".hxRevoke", function () {
            document.getElementById('hxlmDebtAssId').value = $(this).attr("data-id");
        });

        $("#hxdebtsTransferRevoke").click(function () {
            $.ajax({
                url: path + "/client/myDebtAssignment/hxDebtsTransferRevoke",
                type: "post",
                data: {
                    debtAssId: $('#hxlmDebtAssId').val()
                },
                dataType: 'json',

                success: function (data) {
                    if (data.success == true) {
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-success'><strong>撤销成功</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/myTransfer";
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
                                        location.href = path + "/user/index/myTransfer";
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
                                    location.href = path + "/user/index/myTransfer";
                                }
                            }
                        }
                    });
                }


            })

        });

        var table_selector3 = "#hxtransfered_project_table";
        $(table_selector3).dataTable({
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
                var table = $(table_selector3).DataTable();

                var params = new Object();
                params["assStatus"] = 10;
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
            "sAjaxSource": path + "/client/myDebtAssignment/webHxPlConditionQuery",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {"data": "lmDebtAssId", "name": "id", 'sClass': '', "bSortable": true, "visible": false},
                {
                    "data": function (row, type, val) {

                        return '<a href="' + path + '/client/investmentProjectDetails/projectDetail?isHx=' + (row.bidType == 0 ? 'false' : 'true') + '&loanId=' + row.lmLoanRecordId + '" title="' + row.loanName + '" class="text-purple">' + row.loanName + '</a>';
                    },
                    "name": "lmHxBidRecord.lmHxLoanRecord.loanName",
                    "bSortable": true
                },
                {
                    "data": "repayType",
                    "name": "lmHxBidRecord.lmHxLoanRecord.repayType",
                    'sClass': '',
                    "bSortable": true
                },
                {"data": "investAmount", "name": "lmHxBidRecord.investAmount", 'sClass': '', "bSortable": true},
                {"data": "assAmount", "name": "assAmount", 'sClass': '', "bSortable": true},
                {"data": "resIncome", "name": "resIncome", 'sClass': '', "bSortable": true},
                {"data": "issueTime", "name": "issueTime", 'sClass': '', "bSortable": true},
                {"data": "assTime", "name": "assTime", 'sClass': '', "bSortable": true},

                {
                    "data": function (row, type, val) {
                        if (row.fundIsTransfer == 0) {

                            return '<a href="#" title="确认债权转让" data-id=" ' + row.lmDebtAssId + '" class="text-link m-l-sm transferConfirm"  data-toggle="modal">债权转让资金转让</a>';
                        }
                        else {
                            return '<a></a>';
                        }
                    },
                    "bSortable": false
                }
            ],
            //"aaSorting": [[ 1, "asc" ]],

            "initComplete": function () {
            },
            "rowCallback": function (nRow, aData, iDisplayIndex) {
            }
        });

        $(document).on("click", ".transferConfirm", function () {
            var lmDebtAssId = $(this).attr("data-id");
            $.ajax({
                url: path + "/client/DebtAss/hxDebtFundsTransfer",
                type: "post",
                data: {
                    lmHxDebtAssId: lmDebtAssId
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
                                        location.href = path + "/user/index/myTransfer";
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
                                        location.href = path + "/user/index/myTransfer";
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
                                    location.href = path + "/user/index/myTransfer";
                                }
                            }
                        }
                    });
                }


            })
        });


    });
}(window.jQuery);