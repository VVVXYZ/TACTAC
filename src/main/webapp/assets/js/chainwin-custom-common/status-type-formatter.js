/**
 * Created by Geo on 2016/2/27.
 * 在jqGrid中需要显示特定的种类或者状态
 **/

//格式化账户类型
function styleAccType(cellvalue, options, rowObject) {
    if(cellvalue==""||cellvalue==null)
        return "未知";

    switch (cellvalue){
        case 1:
            return "IPS个人账户";
            break;
        default :
            return "类型出错"
    }
}

//格式化充值状态
function styleRecStatus(cellvalue, options, rowObject) {
    switch (cellvalue){
        case 2:
            return "拒绝收款";
            break;
        case 1:
            return "已收款";
            break;
        default :
            return "待收款";
    }
}

//格式化环迅充值状态
function styleHxRecStatus(cellvalue, options, rowObject) {
    switch (cellvalue){
        case "MG00000F":
            return "充值成功";
            break;
        case "MG00008F":
            return "正在处理";
            break;
        default :
            return "充值失败";
    }
}

//格式化充值渠道种类
function stylePchannelType(cellvalue, options, rowObject) {
    switch (cellvalue){
        case 1:
            return "网银充值";
            break;
        default :
            return "其他方式";
    }
}

//格式化ips手续费收取方
function stylePipsFeeType(cellvalue, options, rowObject) {
    switch (cellvalue){
        case 1:
            return "平台支付";
            break;
        case 2:
            return "用户支付";
            break;
        default :
            return "其他"
    }
}