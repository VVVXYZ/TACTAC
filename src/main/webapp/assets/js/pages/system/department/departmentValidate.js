/**
 * Created by QiKai on 2014/4/27.
 */
$(function () {
    var path = window.location.pathname.split("/")[0];
    $("#modifyNameForm").validate({
        rules: {
            newNodeName: {
                required: true
            }
        },
        messages: {
            newNodeName: {
                required: "&nbsp;* 新名称不能为空！"
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
            $.ajax({
                type: "POST",
                url: path+"/department/modifyName",
                data: {
                    id: nodes[0].id,
                    name: $("#newNodeName").val(),
                    parentId: nodes[0].parentId,
                    parentIds: nodes[0].parentIds,
                    available: nodes[0].available
                },
                dataType: 'json',
                success: function (data) {//名称修改成功
                    //更新节点名称
                    nodes[0].name = $("#newNodeName").val();
                    treeObj.updateNode(nodes[0]);
                    $("#newNodeName").attr("value", "");
                },
                error: function (XMLHttpRequest, errorThrown) {
                    alert(XMLHttpRequest.status);
                }
            });
        }
    });

    $("#addNodeForm").validate({
        rules: {
            childNodeName: {
                required: true
            }
        },
        messages: {
            childNodeName: {
                required: "&nbsp;* 子节点名称不能为空！"
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
            $.ajax({
                type: "POST",
                url: path+"/department/appendChild",
                data: {
                    name: $("#childNodeName").val(),
                    parentId: nodes[0].id,
                    parentIds: nodes[0].parentIds + nodes[0].id + "/",
                    available: false
                },
                dataType: 'json',
                success: function (data) {
                    var newNode = {id: data.dataObj, name: $("#childNodeName").val(), parentId: nodes[0].id,
                        parentIds: nodes[0].parentIds + nodes[0].id + "/", available: false};
                    newNode = simaTree.addNodes(nodes[0], newNode);
                },
                error: function (XMLHttpRequest, errorThrown) {
                    alert(XMLHttpRequest.status);
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
            $.ajax({
                type: "POST",
                url: path+"/department/move/" + simaTreeSelectedNodes[0].id + "/" + selectTreeSelectedNodes[0].id,
                dataType: 'json',
                success: function (data) {
                    //alert(selectTreeSelectedNodes[0]);
                    //simaTree.addNodes(selectTreeSelectedNodes[0], simaTreeSelectedNodes[0]);
                    var tmp = simaTree.getNodeByParam("id", selectTreeSelectedNodes[0].id);
                    var ret = simaTree.moveNode(tmp, simaTreeSelectedNodes[0], "inner");
                    //alert("here ret: " + ret);
                },
                error: function (XMLHttpRequest, errorThrown) {
                    alert(XMLHttpRequest.status);
                }
            });
        }
    });
});