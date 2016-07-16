$(function () {
    $("#msgbox").on("mouseover", function (e) {
        $("#msgbox").fadeOut("slow");
    });
    $("button").on("mouseover", function (e) {
        $("#msgbox").fadeOut("slow");
    });
    $("#addResourceName").on("click", function (e) {
        e.preventDefault();
        $("#resourceTreeDiv").toggleClass("display-hide");
    });
    $("#delRoleSubmitBtn").on("click", function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: path + "/admin/systemSetting/role/delete",
            data:{id:$("#delRoleId").val()},
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
                                    location.reload();
                                }
                            }
                        }
                    }
                });

                $("#delRoleDiv").toggleClass("display-hide");
                $("#roleListDiv").toggleClass("display-hide");

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

    var delRoleBtnList = $("#roleListDiv #delRoleBtn");
    for (var i = 0; i < delRoleBtnList.length; i++) {
        delRoleBtnList[i].onclick = function () {
            var parentDiv = $(this).parent();
            $("#delRoleDiv").toggleClass("display-hide");
            $("#roleListDiv").toggleClass("display-hide");
            $("#delRoleId").attr("value", parentDiv.attr("roleid"));
            $("#delRoleName").attr("value", parentDiv.attr("rolename"));
            $("#delRoleDesc").attr("value", parentDiv.attr("desc"));
        }
    }
});
function cancelAddRole() {
    $("#createRoleDiv").toggleClass("display-hide");
    $("#roleListDiv").toggleClass("display-hide");
}
function cancelDelRole() {
    $("#delRoleDiv").toggleClass("display-hide");
    $("#roleListDiv").toggleClass("display-hide");
}