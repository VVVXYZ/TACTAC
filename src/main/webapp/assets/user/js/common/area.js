$(function(){
    // 地区选择下拉联动
    var province_option = '<option value="">选择省</option>';
    for(var key in province){
        province_option += '<option value="'+key+'">'+province[key]+'</option>';
    }
    $("[name='province']").html(province_option);
    $(document).on("change", "[name='province']", function(){
        var city_option = '<option value="">选择市</option>';
        var province_code = $(this).val();
        if(province_code != undefined){
            for(var key in city[province_code]){
                city_option += '<option value="'+key+'">'+city[province_code][key]+'</option>';
            }
        }
        $(this).next("[name='city']").html(city_option);
    });
    $(document).on("change", "[name='city']", function(){
        var area_option = '<option value="">选择县（区）</option>';
        var city_code = $(this).val();
        if(city_code != undefined){
            for(var key in area[city_code]){
                area_option += '<option value="'+key+'">'+area[city_code][key]+'</option>';
            }
        }
        $(this).next("[name='area']").html(area_option);
    });
    //根据地区代码选择地址
    var area_code = "350102";
    var province_code=area_code.substr(0,2)+"0000";
    var city_code=area_code.substr(0,4)+"00";
    $("[name='province']").val(province_code).trigger("change");
    $("[name='city']").val(city_code).trigger("change");
    $("[name='area']").val(area_code);
});