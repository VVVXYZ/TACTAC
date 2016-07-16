/**
 * Created by lw on 2016/5/5.
 */

function paging(page) {
    var pageSelector = "#pagination";
    var projectListSelector = "#loan-list";
    $.ajax({
        type: "GET",
        url: path + "/client/MyLoan/webPlConditionQuery",
        dataType: "json",
        data: {
            page: page,
            rows: 10,
            filed: "id",
            sortOp: "ASC",
            loanStatus: 5
        },
        success: function (json) {
            $(projectListSelector).empty();
            var data = json.dataObj;
            for (var index in data) {
                $(projectListSelector).append(getHtml(data[index]));
            }

            if (json.totalpages < json.currentpage) {
                json.totalpages = json.currentpage = 1;
            }

            $(pageSelector).bootstrapPaginator("setOptions", {
                totalPages: json.totalpages, //总页数
                currentPage: json.currentpage//当前页面
            });
        }
    });
}
function hxPaging(page) {
    var pageSelector = "#hxPagination";
    var projectListSelector = "#hxLoan-list";
    $.ajax({
        type: "GET",
        url: path + "/client/MyLoan/webHxConditionQuery",
        dataType: "json",
        data: {
            page: page,
            rows: 10,
            filed: "id",
            sortOp: "ASC",
            loanStatus: 5
        },
        success: function (json) {
            $(projectListSelector).empty();
            var data = json.dataObj;
            for (var index in data) {
                $(projectListSelector).append(hxgetHtml(data[index]));
            }

            if (json.totalpages < json.currentpage) {
                json.totalpages = json.currentpage = 1;
            }

            $(pageSelector).bootstrapPaginator("setOptions", {
                totalPages: json.totalpages, //总页数
                currentPage: json.currentpage//当前页面
            });
        }
    });
}

function getHtml(loanRecord) {
    return ' <li class="list-group-item clearfix">' +
        '<div class="col-md-4">' +
        '<h5>[ 项目名称 ] ' + loanRecord.loanName + '</h5>' +
        '<h5>[ 借款金额 ]' + loanRecord.financingAmount + '元</h5>' +
        '<h5>[ 放款时间 ]' + loanRecord.loanTime + '</h5>' +
        '</div>' +
        '<div class="col-md-4">' +
        '<h5>[ 项目编号 ]' + loanRecord.loanNo + '</h5>' +
        '<h5>[ 还款方式 ] ' + loanRecord.repayType + '</h5>' +
        '<h5>[ 下一还款日 ]' + loanRecord.nextDayLoan + '</h5>' +
        '</div>' +
        '<div class="col-md-4">' +
        '<h5>[ 计息方式 ]' + loanRecord.calculateInterestMode + '</h5>' +
        '<h5>[ 标的种类 ]' + loanRecord.bidType + '</h5>' +
        '<input type="hidden" id="">' +
        '<h5><a href="#" class="text-link loan-detail" data-loanName="' + loanRecord.loanName + '" data-financingAmount="' + loanRecord.financingAmount + '" data-availableBalance="' + loanRecord.availableBalance + '"' +
        'data-loanTime="' + loanRecord.loanTime + '"  data-loanNo="' + loanRecord.loanNo + '" data-repayType="' + loanRecord.repayType + '" data-id="' + loanRecord.loadRecordId + '" ' +
        'data-nextDayLoan="' + loanRecord.nextDayLoan + '" data-calculateInterestMode="' + loanRecord.calculateInterestMode + '" data-bidType="' + loanRecord.bidType + '"  data-totalRepayMoney="' + loanRecord.totalRepayment + '" >还款详情</a></h5>' +
        '</div>' +
        '</li>';
}

function hxgetHtml(loanRecord) {
    return ' <li class="list-group-item clearfix">' +
        '<div class="col-md-4">' +
        '<h5>[ 项目名称 ] ' + loanRecord.loanName + '</h5>' +
        '<h5>[ 借款金额 ]' + loanRecord.financingAmount + '元</h5>' +
        '<h5>[ 放款时间 ]' + loanRecord.loanTime + '</h5>' +
        '</div>' +
        '<div class="col-md-4">' +
        '<h5>[ 项目编号 ]' + loanRecord.loanNo + '</h5>' +
        '<h5>[ 还款方式 ] ' + loanRecord.repayType + '</h5>' +
        '<h5>[ 下一还款日 ]' + loanRecord.nextDayLoan + '</h5>' +
        '</div>' +
        '<div class="col-md-4">' +
        '<h5>[ 计息方式 ]' + loanRecord.calculateInterestMode + '</h5>' +
        '<h5>[ 标的种类 ]' + loanRecord.bidType + '</h5>' +
        '<input type="hidden" id="">' +
        '<h5><a href="#" class="text-link hxLoan_detail" data-loanName="' + loanRecord.loanName + '" data-financingAmount="' + loanRecord.financingAmount + '" data-availableBalance="' + loanRecord.availableBalance + '"' +
        'data-loanTime="' + loanRecord.loanTime + '"  data-loanNo="' + loanRecord.loanNo + '" data-repayType="' + loanRecord.repayType + '" data-id="' + loanRecord.loadRecordId + '" ' +
        'data-nextDayLoan="' + loanRecord.nextDayLoan + '" data-calculateInterestMode="' + loanRecord.calculateInterestMode + '" data-bidType="' + loanRecord.bidType + '"  data-totalRepayMoney="' + loanRecord.totalRepayment + '" >还款详情</a></h5>' +
        '</div>' +
        '</li>';
}