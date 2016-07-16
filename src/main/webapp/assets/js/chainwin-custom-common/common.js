/**
 * Created by loser on 2016/4/27.
 */

function activeListForRadio(selector) {
    activeList(selector);
    activeRadio(selector);
    paging(1);
}

function activeList(selector) {
    var name = $(selector).attr("name");
    $("a").filter("[name='" + name + "']").removeClass("active");
    $(selector).addClass("active");
}

function activeRadio(selector) {
    var name = $(selector).attr("name");
    $(selector).siblings().filter("input:radio[name='" + name + "']").prop("checked", "checked");
}

function activeSortSelector(selector) {
    if ($(selector).hasClass("bg-light dk text-purple")) {
        $(selector).children("i").toggleClass("fa-sort-asc fa-sort-desc");
    } else {
        $(selector).addClass("bg-light dk text-purple");
        $(selector).siblings().not(selector).removeClass("bg-light dk text-purple");
    }
}

function picCheck(selector) {

    var fileName = $(selector).val();

    if (fileName != null && fileName != "") {
        var fileExt = fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
        var supportFile = new Array(".jpg",".gif",".bmp",".png",".jpeg");

        //lastIndexOf如果没有搜索到则返回为-1
        if ($.inArray(fileExt, supportFile) == -1) {
            var file = $(selector);
            file.after(file.clone().val(""));
            file.remove();
            bootbox.dialog({
                message: "<span class='bigger-110'>文件类型不合法,只能是 jpg、gif、bmp、png、jpeg 类型！</span>",
                buttons: {
                    "success": {
                        "label": "<i class='icon-ok'></i> 确定",
                        "className": "btn-sm btn-success",
                        "callback":function(){
                        }
                    }
                }
            });
        }
    }
}

function accAdd(arg1, arg2) {//两个小数的加法
    var r1, r2, m, c;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
}

function accSub(arg1, arg2) {//小数点的减法
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
        1
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}