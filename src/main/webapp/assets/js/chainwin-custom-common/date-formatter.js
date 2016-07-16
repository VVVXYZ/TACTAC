/**
 * Created by Geo on 2016/2/27.
 * 在jqGrid中需要显示特定的日期格式时引入改文件
 * styleDate ： 格式化日期至秒
 * styleDateMin ： styleDateMin
 * styleDateDay ： 格式化至天
 **/

//格式化两位
function styleTwoBit(cellvalue){
    if(cellvalue<10)
        return "0" + cellvalue;
    else
        return cellvalue;
}

//格式化日期至秒
function styleDate(cellvalue, options, rowObject) {
    if(cellvalue==""||cellvalue==null)
        return "";

    var myDate = new Date(cellvalue);

    var year = myDate.getFullYear();
    var month = styleTwoBit(myDate.getMonth()+1);
    var day = styleTwoBit(myDate.getDate());
    var hours = styleTwoBit(myDate.getHours());
    var minutes = styleTwoBit(myDate.getMinutes());
    var seconds = styleTwoBit(myDate.getSeconds());

    return year+'-'+month+'-'+day + ' ' + hours + ':' + minutes + ':' + seconds;
}

//格式化日期至分钟
function styleDateMin(cellvalue, options, rowObject) {
    if(cellvalue==""||cellvalue==null)
        return "";

    var myDate = new Date(cellvalue);

    var year = myDate.getFullYear();
    var month = styleTwoBit(myDate.getMonth()+1);
    var day = styleTwoBit(myDate.getDate());
    var hours = styleTwoBit(myDate.getHours());
    var minutes = styleTwoBit(myDate.getMinutes());

    return year + "-" + month + "-" + day + ' ' + hours + ':' + minutes;
}

//格式化至天
function styleDateDay(cellvalue, options, rowObject) {
    if(cellvalue==""||cellvalue==null)
        return "";

    var myDate = new Date(cellvalue);

    var year = myDate.getFullYear();
    var month = styleTwoBit(myDate.getMonth()+1);
    var day = styleTwoBit(myDate.getDate());

    return year+'-'+month+'-'+day ;
}