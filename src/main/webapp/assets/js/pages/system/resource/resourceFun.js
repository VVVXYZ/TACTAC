$(function () {
    //展开对应编号的节点
    $("#resourceTable").treetable({ expandable: true }).treetable("expandNode", 1);

    var appendResourceBtnList = $("#resourceTable #appendResourceBtn");
    var modifyResourceBtnList = $("#resourceTable #modifyResourceBtn");
    var deleteResourceBtnList = $("#resourceTable #deleteResourceBtn");

    for (var i = 0; i < appendResourceBtnList.length; i++) {
        appendResourceBtnList[i].onclick = function () {
            var parentTr = $(this).parent().parent();
            var parentTd = $(this).parent();
            //设置parentId为当前节点的Id
            $("#createForm [name=parentId]").attr("value", parentTr.attr("data-tt-id"));
            //设置parentIds
            if (parentTr.attr("data-tt-parent-id") == undefined) {
                $("#createForm [name=parentIds]").attr("value", '0/' + parentTr.attr("data-tt-id") + '/');
            } else {
                $("#createForm [name=parentIds]").attr("value", parentTr.attr("id") + parentTr.attr("data-tt-id") + '/');
            }
            $("#createAvailable").prop("checked", true);
            //设置parentNodeName
            $("#parentNodeName").attr("value", parentTr.attr("name"));
            ShowMask('appendResourceDiv');
        }
    }
    for (var i = 0; i < modifyResourceBtnList.length; i++) {
        modifyResourceBtnList[i].onclick = function () {
            var parentTr = $(this).parent().parent();
            var parentTd = $(this).parent();
            $("#modNodeId").attr("value", parentTr.attr("data-tt-id"));
            $("#modNodeParentId").attr("value", parentTr.attr("data-tt-parent-id"));
            $("#modNodeParentIds").attr("value", parentTr.attr("id"));
            $("#nodeName").attr("value", parentTr.attr("name"));
            if (parentTd.attr("resourcetype") == 'menu') {
                $("#nodeType").find("option[value='menu']").attr("selected", true);
            } else if(parentTd.attr("resourcetype") == 'button') {
                $("#nodeType").find("option[value='button']").attr("selected", true);
            } else{
                $("#nodeType").find("option[value='permission']").attr("selected", true);
            }
            if (parentTd.attr("nodeavai") == 'true') {
                $("#updateAvailable").prop("checked", true);
            } else {
                $("#updateAvailable").prop("checked", false);
            }
            $("#nodePermission").attr("value", parentTd.attr("nodeperm"));
            ShowMask('modifyResourceDiv');
        }
    }
    for (var i = 0; i < deleteResourceBtnList.length; i++) {
        deleteResourceBtnList[i].onclick = function () {
            var parentTr = $(this).parent().parent();
            var parentTd = $(this).parent();
            $("#delNodeId").attr("value", parentTr.attr("data-tt-id"));
            $("#delNodeName").attr("value", parentTr.attr("name"));
            if (parentTd.attr("resourcetype") == 'menu') {
                $("#delNodeType").find("option[value='menu']").attr("selected", true);
            } else if(parentTd.attr("resourcetype") == 'button') {
                $("#delNodeType").find("option[value='button']").attr("selected", true);
            } else{
                $("#delNodeType").find("option[value='permission']").attr("selected", true);
            }
            if (parentTd.attr("nodeavai") == 'true') {
                $("#delNodeAvailable").prop("checked", true);
            } else {
                $("#delNodeAvailable").prop("checked", false);
            }
            $("#delNodeParentIds").attr("value", parentTr.attr("id") + parentTr.attr("data-tt-id") + '/');
            $("#delNodePerm").attr("value", parentTd.attr("nodeperm"));
            ShowMask('deleteResourceDiv');
        }
    }

    $("#appendResourceSubmitBtn").on("click", function (e) {
        $("#createForm [name=id]").val(getNewId($("#addNodeParentId").val()));
        $("#createForm [name=available]").val($("#createAvailable").prop("checked"));
        var sendData = $("#createForm").serializeArray();

        e.preventDefault();
        $.ajax({
            type: "POST",
            url: path + "/admin/systemSetting/resource/create",
            data: sendData,
            dataType: 'json',
            success: function (json) {//名称修改成功
                HideMask('appendResourceDiv');
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
                                    document.location.reload();
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
    });

    $("#delResourceSubmitBtn").on("click", function (e) {
        e.preventDefault();
        var sendData = $("#deleteForm").serializeArray();
        $.ajax({
            type: "POST",
            url: path + "/admin/systemSetting/resource/delete",
            data: sendData,
            dataType: 'json',
            success: function (json) {//名称修改成功
                HideMask('deleteResourceDiv');
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
                                    document.location.reload();

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
    });

    $("#modResourceSubmitBtn").on("click", function (e) {
        $("#updateForm [name=available]").val($("#createAvailable").prop("checked"));
        var sendData = $("#updateForm").serializeArray();
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: path + "/admin/systemSetting/resource/update",
            data: sendData,
            dataType: 'json',
            success: function (json) {//名称修改成功
                HideMask('modifyResourceDiv');
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
                                    document.location.reload();
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
    });
});

function ShowMask(thisObjID) {
    $("#BgDiv").css({ display: "block", height: $(document).height() });
    var yscroll = document.documentElement.scrollTop;
    $("#" + thisObjID).css("top", "100px");
    $("#" + thisObjID).fadeIn("slow");
    document.documentElement.scrollTop = 0;
}

function HideMask(thisObjID) {
    $("#BgDiv").fadeOut("slow");
    $("#" + thisObjID).css("display", "none");
}

function getNewId(pid) {
    var tmp = pid, r;

    var divNum = {
        0: 10, 1: 10,
        2: 10, 3: 100,
        4: 10, 5: 10
    };

    var base = 1;
    for (var i = 0; tmp != 0; ++i) {
        r = tmp % divNum[i];
        if (r != 0) {
            break;
        }
        if(i != 0){
            base *= 10;
        }
        tmp /= divNum[i];
    }

    var id;
    for (i = 1; true; ++i) {
        id = (parseInt(pid) + parseInt(i * base));
        if (0 == $("tr[data-tt-id='" + id + "']").length) {
            break;
        }
    }
    return id;
}