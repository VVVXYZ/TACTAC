$(function () {
    var path = window.location.pathname.split("/")[0];
    $("#delNodeBtn").on('click', function (e) {
        e.preventDefault();
        var simaTree = $.fn.zTree.getZTreeObj("simaTree");
        var nodes = simaTree.getSelectedNodes();
        $.ajax({
            type: "POST",
            url: path + "/department/delete/" + nodes[0].id,
            data: {
                id: nodes[0].id,
                name: nodes[0].name,
                parentId: nodes[0].parentId,
                parentIds: nodes[0].parentIds,
                available: nodes[0].available
            },
            dataType: 'json',
            success: function (data) {
                alert(data.msg);
                simaTree.removeNode(nodes[0]);
            },
            error: function (XMLHttpRequest, errorThrown) {
                alert(XMLHttpRequest.status);
            }
        });
    });

    $("#targetNodeName").on('click', function (e) {
        e.preventDefault();
        var simaTree = $.fn.zTree.getZTreeObj("simaTree");
        var nodes = simaTree.getSelectedNodes();
        var setting = {
            view: {
                dblClickExpand: false,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: function (e, treeId, treeNode) {
                    var selectTree = $.fn.zTree.getZTreeObj("selectTree"),
                        nodes = selectTree.getSelectedNodes(),
                        id = "",
                        name = "";
                    nodes.sort(function compare(a, b) {
                        return a.id - b.id;
                    });
                    for (var i = 0, l = nodes.length; i < l; i++) {
                        id += nodes[i].id + ",";
                        name += nodes[i].name + ",";
                    }
                    if (id.length > 0) id = id.substring(0, id.length - 1);
                    if (name.length > 0) name = name.substring(0, name.length - 1);
                    $("#targetNodeId").val(id);
                    $("#targetNodeName").val(name);
                    hideMenu();
                }
            }
        };
        var zNodes;
        $.ajax({
            type: "GET",
            url: path + "/department/targetList/" + nodes[0].id,
            data: {
                id: nodes[0].id,
                name: nodes[0].name,
                parentId: nodes[0].parentId,
                parentIds: nodes[0].parentIds,
                available: nodes[0].available
            },
            dataType: 'json',
            success: function (data) {
                zNodes = data;
                $.fn.zTree.init($("#selectTree"), setting, zNodes);
                showMenu();
            },
            error: function (XMLHttpRequest, errorThrown) {
                alert(XMLHttpRequest.status);
            }
        });
    });

    function showMenu() {
        //$("#contentForTree").removeClass("display-hide");
        $("#contentForTree").slideDown();
        $("body").bind("mousedown", onBodyDown);
    }

    function hideMenu() {
        //$("#contentForTree").addClass("display-hide");
        $("#contentForTree").slideUp();
        $("body").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == "moveNodebtn" || event.target.id == "contentForTree" || $(event.target).parents("#contentForTree").length > 0)) {
            hideMenu();
        }
    }
});