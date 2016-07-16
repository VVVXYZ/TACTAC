//放在function paging(page) { ... }函数声明后

$(function(){
    $('#pagination').bootstrapPaginator({
        listContainerClass: "pagination custom",
        //currentPage: getUrlVars(window.location.href,"page"), //当前页数
        onPageClicked: function (event, originalEvent, type, page) {
            var currentTarget = $(event.currentTarget);

            switch (type) {
                case "first":
                    currentTarget.bootstrapPaginator("showFirst");
                    paging(page);
                    break;
                //上一页
                case "prev":
                    currentTarget.bootstrapPaginator("showPrevious");
                    paging(page);
                    break;
                case "next":
                    currentTarget.bootstrapPaginator("showNext");
                    paging(page);
                    break;
                case "last":
                    currentTarget.bootstrapPaginator("showLast");
                    paging(page);
                    break;
                case "page":
                    currentTarget.bootstrapPaginator("show", page);
                    paging(page);
                    break;
            }
        },
        tooltipTitles: function (type, page, current) {
            return "";
        }
    });
    $('#hxPagination').bootstrapPaginator({
        listContainerClass: "pagination custom",
        //currentPage: getUrlVars(window.location.href,"page"), //当前页数
        onPageClicked: function (event, originalEvent, type, page) {
            var currentTarget = $(event.currentTarget);

            switch (type) {
                case "first":
                    currentTarget.bootstrapPaginator("showFirst");
                    paging(page);
                    break;
                //上一页
                case "prev":
                    currentTarget.bootstrapPaginator("showPrevious");
                    paging(page);
                    break;
                case "next":
                    currentTarget.bootstrapPaginator("showNext");
                    paging(page);
                    break;
                case "last":
                    currentTarget.bootstrapPaginator("showLast");
                    paging(page);
                    break;
                case "page":
                    currentTarget.bootstrapPaginator("show", page);
                    paging(page);
                    break;
            }
        },
        tooltipTitles: function (type, page, current) {
            return "";
        }
    });

    var init_page = getUrlVars(window.location.href,"page")==null?1:getUrlVars(window.location.href,"page");
    var init_rows = getUrlVars(window.location.href,"rows")==null?8:getUrlVars(window.location.href,"rows");
    $('#notice_pagination').bootstrapPaginator({//type：当前按钮类型，page：当前按钮表示页码，current：整个控件当前页码
        listContainerClass: "pagination custom",
        numberOfPages: init_rows,//显示的页码数，默认取8
        currentPage: init_page, //当前页码，默认取1
        totalPages: paging(init_page, init_rows), //共多少页 by limer2
        itemTexts: function (type, page, current) {//设置该按钮的显示文字
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {//注册该按钮的点击事件，用于通过Ajax来刷新整个list列表
            var currentTarget = $(event.currentTarget);
            var rowsInUrl = getUrlVars(window.location.href,"rows");
            var curRows = currentTarget.bootstrapPaginator("getOptions")["numberOfPages"];
            if (rowsInUrl!=null&&rowsInUrl!=curRows) {
                currentTarget.bootstrapPaginator("getOptions")["numberOfPages"] = rowsInUrl;
                currentTarget.bootstrapPaginator("getOptions")["totalPages"] = paging(page, rowsInUrl);
                newOptions = currentTarget.bootstrapPaginator("getOptions");
                currentTarget.bootstrapPaginator("setOptions", newOptions);//会重新绘制分页控件，但需自己paging
                return;
            }

            switch (type) {
                case "first":
                    currentTarget.bootstrapPaginator("showFirst");
                    paging(page, curRows);
                    break;
                case "prev":
                    currentTarget.bootstrapPaginator("showPrevious");
                    paging(page, curRows);
                    break;
                case "next":
                    currentTarget.bootstrapPaginator("showNext");
                    paging(page, curRows);
                    break;
                case "last":
                    currentTarget.bootstrapPaginator("showLast");
                    paging(page, curRows);
                    break;
                case "page":
                    currentTarget.bootstrapPaginator("show", page);
                    paging(page, curRows);
                    break;
            }
        },
        onPageChanged: function (event, oldPage, newPage) {//注册该按钮的页码改变事件
        },
        //pageUrl: function (type, page, current) {//为每个操作按钮（即控件）动态设置链接地址
        //    return path + "/client/CMS/announcement?page=" + page + "&rows=" + this.options.numberOfPages;
        //},
        shouldShowPage: function (type, page, current) {//设置该按钮是否显示
            return true;
        },
        tooltipTitles: function (type, page, current) {//设置该按钮title属性
            return "";
        }
    });
});
function getUrlVars(url,name) {
    var hash;
    var hashes = url.slice(url.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        if(hash[0] == name){
            return hash[1];
        }
    }
    return null;
}
