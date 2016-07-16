/**
 * Created by QiKai on 2014/4/27.
 */
$(function () {
    var path = window.location.pathname.split("/")[0];

    $("#updateForm").validate({
        rules: {
            oldNodeName: {
                required: true
            },
            roleId: {
                required: true
            }
        },
        messages: {
            oldNodeName: {
                required: "&nbsp;* 新名称不能为空！"
            },
            roleId: {
                required: "&nbsp;* 角色不能为空！"
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
            error.insertAfter(element.parent());                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            var treeObj = $.fn.zTree.getZTreeObj("simaTree");
            var nodes = treeObj.getSelectedNodes();
            var val = $("#oldNodeName").val();
            //if(val==null || val=="" || val==undefined){
            //    $("#newNodeName").val(nodes[0].name);
            //}

            //if($("#oldNodeName").val() == nodes[0].name && $("#intro").val() == nodes[0].intro){
            //    messageBox("没有修改！");
            //    return false;
            //}
            //如果不是部门，不能进行操作
            if(nodes[0].type < 3){
                messageBox("只能对部门进行操作!");
                return false;
            }
            $.ajax({
                type: "POST",
                url: path+"/organization/update",
                data: {
                    id: nodes[0].id,
                    name: $("#oldNodeName").val(),
                    intro: $("#intro").val(),
                    parentId: nodes[0].parentId,
                    parentIds: nodes[0].parentIds,
                    available: nodes[0].available,
                    type: nodes[0].type,
                    typeId: nodes[0].typeId,
                    roleId: $('#roleId').val()
                },
                dataType: 'json',
                success: function (data) {//名称修改成功
                    if(data.success){
                        //更新节点名称
                        nodes[0].name = $("#oldNodeName").val();
                        nodes[0].intro = $("#intro").val();
                        treeObj.updateNode(nodes[0]);
                        messageBox("操作成功！");
                    } else{
                        messageBox("操作失败！请重试");
                    }

                },
                error: function (XMLHttpRequest, errorThrown) {
                    messageBox("操作失败！错误代码：" + XMLHttpRequest.status);
                }
            });
        }
    });

    $("#addNodeForm").validate({
        rules: {
            childNodeName: {
                required: true
            },
            childRoleId: {
                required: true
            }
        },
        messages: {
            childNodeName: {
                required: "&nbsp;* 子节点名称不能为空！"
            },
            childRoleId: {
                required: "&nbsp;* 角色不能为空！"
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
            error.insertAfter(element.parent());                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            var simaTree = $.fn.zTree.getZTreeObj("simaTree");
            var nodes = simaTree.getSelectedNodes();
            var fatherTypeId = nodes[0].typeId, grafaTypeId=0;
            if(nodes[0].type > 1){
                grafaTypeId = nodes[0].getParentNode().typeId;
                //alert("grafaTypeId: " + grafaTypeId);
            }
            //如果不是部门，不能进行操作
            if(nodes[0].type != 2){
                messageBox("只能添加部门!");
                return false;
            }
            $.ajax({
                type: "POST",
                url: path+"/organization/appendChild",
                data: {
                    name: $("#childNodeName").val(),
                    intro: $("#childNodeIntro").val(),
                    type: nodes[0].type + 1,
                    parentId: nodes[0].id,
                    parentIds: nodes[0].parentIds + nodes[0].id + "/",
                    available: true,
                    fatherTypeId: fatherTypeId,
                    grafaTypeId: grafaTypeId,
                    roleId: $('#childRoleId').val()
                },
                dataType: 'json',
                success: function (data) {
                    var ids = data.dataObj.split(",");
                    //alert("dataObj: " + data.dataObj);
                    var newNode = {
                        id: ids[0],
                        name: $("#childNodeName").val(),
                        intro: $("#childNodeTrio").val(),
                        type: nodes[0].type + 1,
                        //typeId: ids[1],
                        parentId: nodes[0].id,
                        parentIds: nodes[0].parentIds + nodes[0].id + "/",
                        available: true,
                        roleId: $("#childRoleId").val()
                    };
                    newNode = simaTree.addNodes(nodes[0], newNode);
                    messageBox("操作成功！");
                },
                error: function (XMLHttpRequest, errorThrown) {
                    messageBox("操作失败！错误代码：" + XMLHttpRequest.status);
                }
            });
        }
    });

    $("#moveNodeForm").validate({
        rules: {
            targetNodeName: {
                required: true
            }
        },
        messages: {
            targetNodeName: {
                required: "&nbsp;* 请选择目标节点！"
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
            error.insertAfter(element.parent());                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            var simaTree = $.fn.zTree.getZTreeObj("simaTree");
            var simaTreeSelectedNodes = simaTree.getSelectedNodes();
            var selectTree = $.fn.zTree.getZTreeObj("selectTree");
            var selectTreeSelectedNodes = selectTree.getSelectedNodes();
            var flag = selectTreeSelectedNodes[0].type + 1 == simaTreeSelectedNodes[0].type;
            //alert(selectTreeSelectedNodes[0].type + ", " + simaTreeSelectedNodes[0].type);
            if(flag == false){
                messageBox("非法操作！只能在同一级移动");
                return false;
            }

            //如果不是部门，不能进行操作
            if(simaTreeSelectedNodes[0].type < 3){
                messageBox("只能对部门进行操作!");
                return false;
            }

            $.ajax({
                type: "POST",
                url: path+"/organization/move/" + simaTreeSelectedNodes[0].id + "/" + selectTreeSelectedNodes[0].id,
                dataType: 'json',
                success: function (data) {
                    var tmp = simaTree.getNodeByParam("id", selectTreeSelectedNodes[0].id);
                    var ret = simaTree.moveNode(tmp, simaTreeSelectedNodes[0], "inner");
                    messageBox("操作成功！");
                },
                error: function (XMLHttpRequest, errorThrown) {
                    //alert(XMLHttpRequest.status);
                    messageBox("操作失败！错误代码：" + XMLHttpRequest.status);
                }
            });
        }
    });

    $("#deleteForm").validate({
        rules: {
            delNodeName: {
                required: true
            }
        },
        messages: {
            delNodeName: {
                required: "&nbsp;* 节点名称不能为空！"
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
            error.insertAfter(element.parent());                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            var treeObj = $.fn.zTree.getZTreeObj("simaTree");
            var nodes = treeObj.getSelectedNodes();
            //alert("nodes: " + nodes[0].children);
            if(nodes[0].children!=null && nodes[0].children!=undefined && nodes[0].children!=""){
                messageBox("存在子节点，无法删除！");
                return false;
            }
            if(nodes[0].id == 1){
                messageBox("科技园无法删除！");
                return false;
            }

            //如果不是部门，不能进行操作
            if(nodes[0].type < 3){
                messageBox("只能对部门进行操作!");
                return false;
            }

            $.ajax({
                type: "POST",
                url: path+"/organization/delete",
                data: {
                    id: nodes[0].id
                },
                dataType: 'json',
                success: function (data) {//名称修改成功
                    if(data.success){
                        treeObj.removeNode(nodes[0]);
                        messageBox("操作成功！");
                    } else{
                        messageBox("操作失败，部门下有员工，无法删除");
                    }

                },
                error: function (XMLHttpRequest, errorThrown) {
                    messageBox("操作失败！错误代码：" + XMLHttpRequest.status);
                }
            });
        }
    });

});

function messageBox(message){
    bootbox.dialog({
        message: "<span class='bigger-110'>" + message + "</span>",
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

function successBox(){
    bootbox.dialog({
        message: "<span class='bigger-110'>操作成功！</span>",
        buttons:
        {
            "success" :
            {
                "label" : "<i class='icon-ok'></i> 确定",
                "className" : "btn-sm btn-success",
                "callback": function() {
                    //Example.show("great success");
                    if($("#applicationStateName").text() == "未通过审核"){
                        $("#applicationStateName").text("待审核");
                    }
                }
            }
        }
    });
}