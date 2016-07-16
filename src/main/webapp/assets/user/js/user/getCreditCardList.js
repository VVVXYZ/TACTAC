/**
 * Created by lw on 2016/5/5.
 */

function getCreditCardList() {
    var CreditCardListSelector = ".banks";
    $.ajax({
        type: "GET",
        url: path + "/client/Recharge/getCreditCardList",
        dataType: "json",
        data: {
            page: 1,
            rows: 10,
            id: "id",
            sord: "ASC"
        },
        success: function (json) {
            $(CreditCardListSelector).empty();
            var data = json.dataObj;
            for (var index in data) {
                $(CreditCardListSelector).append(getHtml(data[index]));
            }

        }
    });
}

function getHtml(Bank) {
    return '    <label class="btn btn-sm btn-default wrapper-sm m-t-sm active">' +
        '   <input type="radio" name="banks" value="0" checked>' +
        ' <h5>线下存款&nbsp;&nbsp;&nbsp;&nbsp;收款人：' + Bank.payee + '&nbsp;&nbsp;&nbsp;&nbsp;收款账号：' + Bank.paymentAccount +
        '&nbsp;&nbsp;&nbsp;&nbsp;开户行：' + Bank.bankDeposit + '</h5>' +
        '</label>';
}