+function ($) {
    $(function () {
        var table_selector = "#all_load_table";
        //$(table_selector).dataTable().fnClearTable();
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
                params["loanStatus"] = 0;
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
            "sAjaxSource": path + "/client/MyLoan/webPlConditionQuery",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {"data": "loadRecordId", "name": "id", 'sClass': '', "bSortable": true, "visible": false},
                {
                    "data": function (row, type, val) {

                        return '<a href="' + path + '/client/investmentProjectDetails/projectDetail?isHx=' + (row.bidType == '平台标' ? 'false' : 'true') + '&loanId=' + row.loadRecordId + '" title="' + row.loanName + '" class="text-purple">' + row.loanName + '</a>';
                    },
                    "name": "loanName",
                    "bSortable": true
                },
                {"data": "financingAmount", "name": "financingAmount", 'sClass': '', "bSortable": true},
                {"data": "loanTerm", "name": "projectDate", 'sClass': '', "bSortable": true},
                {"data": "annualInterestRate", "name": "annualInterestRate", 'sClass': '', "bSortable": true},
                {"data": "repayType", "name": "repayType", 'sClass': '', "bSortable": true},
                {"data": "loanStatus", "name": "loanStatus", 'sClass': '', "bSortable": true},

                //{
                //    "render": function (val, type, row) {
                //
                //        return '<a href="' + row.loanName + '" title="查看合同" class="text-link m-l-sm" data-toggle="modal">[查看合同]</a>';
                //    },
                //    "bSortable": false
                //
                //},
                {
                    "data": function (row, type, val) {
                        if (row.loanStatus == "还款中") {
                            return '<a href="#refund-plan-modal" title="查看还款计划" class="text-link m-l-sm loan-detail"  data-loanName="' + row.loanName + '" data-financingAmount="' + row.financingAmount + '"' +
                                'data-loanTime="' + row.loanTime + '" data-loanNo="' + row.loanNo + '" data-repayType="' + row.repayType + '" data-nextDayLoan="' + row.nextDayLoan + '" data-calculateInterestMode="' + row.calculateInterestMode + '"' +
                                'data-bidType="' + row.bidType + '" data-availableBalance="' + row.availableBalance + '" data-id="' + row.loadRecordId + '" data-totalRepayMoney="' + row.totalRepayment + '"  data-toggle="modal">还款</a>';
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
        $(document).on("click", ".loan-detail", function () {
            $(this).parents(".tab-pane").removeClass("active");
            $("[data-toggle='tab']").parents("li").removeClass("active");
            $("#loan_detail").show();
            $("#loanName").html($(this).attr("data-loanName"));
            $("#financingAmount").html($(this).attr("data-financingAmount"));
            $("#loanTime").html($(this).attr("data-loanTime"));
            $("#loanNo").html($(this).attr("data-loanNo"));
            $("#repayType").html($(this).attr("data-repayType"));
            $("#nextDayLoan").html($(this).attr("data-nextDayLoan"));
            $("#calculateInterestMode").html($(this).attr("data-calculateInterestMode"));
            $("#bidType").html($(this).attr("data-bidType"));
            $("#totalRepayMoney").html($(this).attr("data-totalRepayMoney"));
            $("#availableBalance").html($(this).attr("data-availableBalance"));
            document.getElementById('loadRecordId0').value = $(this).attr("data-id");

            $(function () {
                var table_selector = ".replayDetail";
                var loadRecordId = $('#loadRecordId0').val();
                $(table_selector).dataTable().fnClearTable(false);
                $(table_selector).dataTable().fnDestroy();
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
                        params["loanRecordId"] = loadRecordId;
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
                                //console.log("loadRecordId=" + params["loadRecordId"]);
                            }
                        });
                    },
                    "lengthChange": true, //改变每页显示数据数量
                    "paginate": true,//分页按钮
                    "stateSave": false,
                    "processing": true,
                    "serverSide": true,
                    "sAjaxSource": path + "/client/MyLoan/webPlRepaymentDetails",
                    "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
                    "paginationType": "full_numbers",
                    "columns": [
                        {"data": "period", "name": "period", 'sClass': '', "bSortable": true},
                        {"data": "repayDate", "name": "repayDate", 'sClass': '', "bSortable": true},
                        {"data": "repayPrincipal", "name": "repayPrincipal", 'sClass': '', "bSortable": true},
                        {"data": "repayInterest", "name": "repayInterest", 'sClass': '', "bSortable": true},
                        {"data": "lateCharge", "name": "lateCharge", 'sClass': '', "bSortable": true},
                        {"data": "repayStatus", "name": "repayStatus", 'sClass': '', "bSortable": true},
                        {

                            "data": function (row, type, val) {
                                if (row.repayStatus == "未还款") {
                                    return '<a href="#refund-modal" title="还款" class="text-link m-l-sm ordinaryRepayment"  data-id="' + row.repayID + '"  data-loanRecordId="' + row.loanRecordId + '"  data-period="' + row.period + '"  ' +
                                        'data-repayDate="' + row.repayDate + '" data-repayPrincipal="' + row.repayPrincipal + '" data-repayInterest="' + row.repayInterest + '" data-managementFee="' + row.managementFee + '" data-lateCharge="' + row.lateCharge + '" ' +
                                        'data-repayStatus="' + row.repayStatus + '"  data-repayTotal="' + row.repayTotal + '" data-availableBalance="' + row.availableBalance + '"  data-toggle="modal">还款</a>';
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
            });




        });

        $("[data-toggle='tab']").click(function () {
            $("#loan_detail").hide();
            $(this).trigger("toggle");
        });

        $(document).on("click", ".ordinaryRepayment", function () {
            var loadStatus = $(this).attr("data-repayStatus");
            document.getElementById('repayRecordId').value = $(this).attr("data-id");
            document.getElementById('loanRecordId1').value = $(this).attr("data-loanRecordId");
            $("#period").html($(this).attr("data-period"));
            $("#repayDate").html($(this).attr("data-repayDate"));
            $("#repayPrincipal").html($(this).attr("data-repayPrincipal"));
            $("#repayInterest").html($(this).attr("data-repayInterest"));
            $("#managementFee").html($(this).attr("data-managementFee"));
            $("#lateCharge").html($(this).attr("data-lateCharge"));
            $("#repayTotal").html($(this).attr("data-repayTotal"));
            $("#availableBalance2").html($(this).attr("data-availableBalance"));


        });

        $(function () {
            $('#Repaymentconfirm').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    tradePassword: {
                        required: true
                    }
                },
                messages: {
                    tradePassword: {
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
                        type: "POST",
                        url: path + "/client/MyLoan/ordinaryRepaymentconfirm",
                        data: {
                            repayRecordId: $('#repayRecordId').val(),
                            tradersPassword: $('#tradePassword').val(),
                            loanRecordId: $('#loanRecordId1').val()
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
                                                location.href = path + "/user/index/myLoan";
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
                                                location.href = path + "/user/index/myLoan";
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
                    });
                }
            });
        });

        $(document).on("click", ".earlyRepayment", function () {
            $.ajax({
                type: "post",
                url: path + "/client/MyLoan/earlyRepayment",
                data: {
                    loanRecordId: $('#loadRecordId0').val()
                },
                dataType: 'json',
                success: function (json) {
                    console.log("loanRecordId=" + $('#loadRecordId0').val());
                    var data = json.dataObj;
                    if (data.projectDeadlinePeriod == 1) {
                        data.projectDeadlinePeriod = '天';
                    }
                    else {
                        data.projectDeadlinePeriod = '个月';
                    }
                    document.getElementById('loanRecordId2').value = data.id;
                    $("#financingAmount1").html(data.financingAmount);
                    $("#loanTerm").html(data.projectDate + data.projectDeadlinePeriod);
                    $("#repayedPrincipal").html(data.repayedPrincipal);
                    $("#repayedInterest").html(data.repayedInterest);
                    $("#unpaidPrincipal").html(data.unpaidPrincipal);
                    $("#residualInterest").html(data.residualInterest);
                    $("#loanTime1").html(data.loanDataString);
                    $("#lastRepayTime").html(data.lastRepayTime);
                    $("#nowRepayTime").html(data.nowRepayTime);
                    $("#advancedDay").html(data.advancedDay);
                    $("#prepayCompensation").html(data.prepayCompensation);
                    $("#availableBalance3").html(data.availableBalance);
                    $("#totalRepayment").html(data.totalRepayment);
                    document.getElementById('actManagementFee').value = data.actManagementFee;
                }
            });
        });

        $(function () {
            $('#earlyRepaymentconfirm').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    tradePassword2: {
                        required: true
                    }
                },
                messages: {
                    tradePassword2: {
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
                        type: "POST",
                        url: path + "/client/MyLoan/earlyRepaymentconfirm",
                        data: {
                            loanRecordId: $('#loanRecordId2').val(),
                            tradersPassword: $('#tradePassword2').val(),
                            prepayCompensation: $('#prepayCompensation').html(),
                            residualInterest: $('#residualInterest').html(),
                            actManagementFee: $('#actManagementFee').val()

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
                                                location.href = path + "/user/index/myLoan";
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
                                                location.href = path + "/user/index/myLoan";
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
                    });
                }
            });
        });


    });

    $(function () {
        var table_selector = "#hxAll_load_table";
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
                params["loanStatus"] = 0;
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
            "sAjaxSource": path + "/client/MyLoan/webHxConditionQuery",
            "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "paginationType": "full_numbers",
            "columns": [
                {"data": "loadRecordId", "name": "id", 'sClass': '', "bSortable": true, "visible": false},
                {
                    "data": function (row, type, val) {

                        return '<a href="' + path + '/client/investmentProjectDetails/projectDetail?isHx=' + (row.bidType == '平台标' ? 'false' : 'true') + '&loanId=' + row.loadRecordId + '"title="' + row.loanName + '" class="text-purple">' + row.loanName + '</a>';
                    },
                    "name": "loanName",
                    "bSortable": true
                },
                {"data": "financingAmount", "name": "financingAmount", 'sClass': '', "bSortable": true},
                {"data": "loanTerm", "name": "projectDate", 'sClass': '', "bSortable": true},
                {"data": "annualInterestRate", "name": "annualInterestRate", 'sClass': '', "bSortable": true},
                {"data": "repayType", "name": "repayType", 'sClass': '', "bSortable": true},
                {"data": "loanStatus", "name": "loanStatus", 'sClass': '', "bSortable": true},

                //{
                //    "render": function (val, type, row) {
                //
                //        return '<a href="' + row.loanName + '" title="查看合同" class="text-link m-l-sm" data-toggle="modal">[查看合同]</a>';
                //    },
                //    "bSortable": false
                //
                //},
                {
                    "data": function (row, type, val) {
                        if (row.loanStatus == "还款中") {
                            return '<a href="#refund-plan-modal" title="查看还款计划" class="text-link m-l-sm hxLoan_detail"  data-loanName="' + row.loanName + '" data-financingAmount="' + row.financingAmount + '"' +
                                'data-loanTime="' + row.loanTime + '" data-loanNo="' + row.loanNo + '" data-repayType="' + row.repayType + '" data-nextDayLoan="' + row.nextDayLoan + '" data-calculateInterestMode="' + row.calculateInterestMode + '"' +
                                'data-bidType="' + row.bidType + '" data-availableBalance="' + row.availableBalance + '" data-id="' + row.loadRecordId + '" data-totalRepayMoney="' + row.totalRepayment + '" data-toggle="modal">还款</a>';
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
        $(document).on("click", ".hxLoan_detail", function () {
            $(this).parents(".tab-pane").removeClass("active");
            $("[data-toggle='tab']").parents("li").removeClass("active");
            $("#hxLoan_detail").show();
            $("#hxloanName").html($(this).attr("data-loanName"));
            $("#hxfinancingAmount").html($(this).attr("data-financingAmount"));
            $("#hxloanTime").html($(this).attr("data-loanTime"));
            $("#hxloanNo").html($(this).attr("data-loanNo"));
            $("#hxrepayType").html($(this).attr("data-repayType"));
            $("#hxnextDayLoan").html($(this).attr("data-nextDayLoan"));
            $("#hxcalculateInterestMode").html($(this).attr("data-calculateInterestMode"));
            $("#hxbidType").html($(this).attr("data-bidType"));
            $("#hxtotalRepayMoney").html($(this).attr("data-totalRepayMoney"));
            $("#hxavailableBalance").html($(this).attr("data-availableBalance"));
            document.getElementById('hxloadRecordId0').value = $(this).attr("data-id");
            $(function () {
                var table_selector = ".hxReplayDetail";
                var loadRecordId = $('#hxloadRecordId0').val();
                $(table_selector).dataTable().fnClearTable(false);
                $(table_selector).dataTable().fnDestroy();
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
                        params["loanRecordId"] = loadRecordId;
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
                                //console.log("loadRecordId=" + params["loadRecordId"]);
                            }
                        });
                    },
                    "lengthChange": true, //改变每页显示数据数量
                    "paginate": true,//分页按钮
                    "stateSave": false,
                    "processing": true,
                    "serverSide": true,
                    "sAjaxSource": path + "/client/MyLoan/webHxRepaymentDetails",
                    "dom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
                    "paginationType": "full_numbers",
                    "columns": [
                        {"data": "period", "name": "period", 'sClass': '', "bSortable": true},
                        {"data": "repayDate", "name": "repayDate", 'sClass': '', "bSortable": true},
                        {"data": "repayPrincipal", "name": "repayPrincipal", 'sClass': '', "bSortable": true},
                        {"data": "repayInterest", "name": "repayInterest", 'sClass': '', "bSortable": true},
                        {"data": "lateCharge", "name": "lateCharge", 'sClass': '', "bSortable": true},
                        {"data": "repayStatus", "name": "repayStatus", 'sClass': '', "bSortable": true},
                        {
                            "data": function (row, type, val) {

                                return '<a href="#hxrefund-modal" title="还款" class="text-link m-l-sm hxordinaryRepayment"  data-id="' + row.repayID + '" data-loanRecordId="' + row.loanRecordId + '" data-period="' + row.period + '"  ' +
                                    'data-repayDate="' + row.repayDate + '" data-repayPrincipal="' + row.repayPrincipal + '" data-repayInterest="' + row.repayInterest + '"data-managementFee="' + row.managementFee + '" data-lateCharge="' + row.lateCharge + '" ' +
                                    'data-repayStatus="' + row.repayStatus + '"  data-repayTotal="' + row.repayTotal + '" data-availableBalance="' + row.availableBalance + '"   data-toggle="modal">还款</a>';
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
            });


        });

        $("[data-toggle='tab']").click(function () {
            $("#hxLoan_detail").hide();
            $(this).trigger("toggle");
        });

        $(document).on("click", ".hxordinaryRepayment", function () {
            document.getElementById('hxrepayRecordId').value = $(this).attr("data-id");
            $("#hxperiod").html($(this).attr("data-period"));
            $("#hxrepayDate").html($(this).attr("data-repayDate"));
            $("#hxrepayPrincipal").html($(this).attr("data-repayPrincipal"));
            $("#hxrepayInterest").html($(this).attr("data-repayInterest"));
            $("#hxmanagementFee").html($(this).attr("data-managementFee"));
            $("#hxlateCharge").html($(this).attr("data-lateCharge"));
            $("#hxrepayTotal").html($(this).attr("data-repayTotal"));
            $("#hxavailableBalance2").html($(this).attr("data-availableBalance"));


        });

        $(function () {
            $('#hxRepaymentconfirm').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    hxrepayRecordId: {
                        required: true
                    }
                },
                messages: {},
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
                        url: path + "/client/MyLoan/hxOrdinaryRepaymentConfirm",
                        data: {
                            repayRecordId: $('#hxrepayRecordId').val()
                        },
                        dataType: "json",
                        success: function (responseText, statusText) {
                            console.log("data toString= " + responseText);
                            console.log("data success=" + " " + responseText.success + " " + responseText.msg);
                            if (responseText.success == true) {
                                //console.log("成功，草泥马");
                                bootbox.dialog({
                                    closeButton: false,
                                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                    message: "<div class='alert alert-success'><strong>" + responseText.msg + "</strong><br/></div>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                window.open(path + '/client/MyLoan/hxOrdinaryRepaymentConfirm/' + responseText.dataObj.repayRecordId);
                                            }
                                        }
                                    }
                                });

                            } else {
                                //console.log("失败，草泥马");
                                bootbox.dialog({
                                    closeButton: true,
                                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                    message: "<div class='alert alert-danger'><strong>" + responseText.msg + "</strong><br/></div>",
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
                    });
                }
            });
        });

        $(document).on("click", ".hxearlyRepayment", function () {
            $.ajax({
                type: "get",
                url: path + "/client/MyLoan/hxEarlyRepayment",
                data: {
                    loanRecordId: $('#hxloadRecordId0').val()
                },
                dataType: 'json',
                success: function (json) {
                    var data = json.dataObj;
                    if (data.projectDeadlinePeriod == 1) {
                        data.projectDeadlinePeriod = '天';
                    }
                    else {
                        data.projectDeadlinePeriod = '个月';
                    }
                    document.getElementById('hxloanRecordId2').value = data.id;
                    $("#hxfinancingAmount1").html(data.financingAmount);
                    $("#hxloanTerm").html(data.projectDate + data.projectDeadlinePeriod);
                    $("#hxrepayedPrincipal").html(data.repayedPrincipal);
                    $("#hxrepayedInterest").html(data.repayedInterest);
                    $("#hxunpaidPrincipal").html(data.unpaidPrincipal);
                    $("#hxresidualInterest").html(data.residualInterest);
                    $("#hxloanTime1").html(data.loanTime);
                    $("#hxlastRepayTime").html(data.lastRepayTime);
                    $("#hxnowRepayTime").html(data.nowRepayTime);
                    $("#hxadvancedDay").html(data.advancedDay);
                    $("#hxprepayCompensation").html(data.prepayCompensation);
                    $("#hxtotalRepayment").html(data.totalRepayment);
                    $("#hxavailableBalance3").html(data.availableBalance);
                    document.getElementById('hxactManagementFee').value = data.actManagementFee;
                }
            });
        });

        $(function () {
            $('#hxearlyRepaymentconfirm').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {

                },
                messages: {

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
                        url: path + "/client/MyLoan/hxEarlyRepaymentConfirm",
                        data: {
                            loanRecordId: $('#hxloanRecordId2').val(),
                            prepayCompensation: $('#hxprepayCompensation').html(),
                            residualInterest: $('#hxresidualInterest').html(),
                            actManagementFee: $('#hxactManagementFee').val()

                        },
                        dataType: 'json',
                        success: function (responseText, statusText) {
                            //data = $.parseJSON(data);
                            console.log("data toString= " + responseText);
                            console.log("data success=" + " " + responseText.success + " " + responseText.msg);
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
                                                window.open(path + '/client/MyLoan/hxOrdinaryRepaymentConfirm/' + responseText.dataObj.repayRecordId);
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
                                                location.href = path + "/user/index/myLoan";
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
                    });
                }
            });
        });







    });
}(window.jQuery);