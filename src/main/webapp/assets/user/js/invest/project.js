/**
 * Created by xb on 2015/11/19.
 */
+function ($) {

  $(function(){
      //认购份数加减
      $(".btn-minus").click(function(){
          if(parseInt($("#invest_amount").val()) > 100){
              $("#invest_amount").val(parseInt($("#invest_amount").val())-100);
          }
      });
      $(".btn-plus").click(function(){
          $("#invest_amount").val(parseInt($("#invest_amount").val())+100);
      });
      //表格
      $("#refund_details_table").dataTable( {
          "oLanguage": { // 汉化
              "sProcessing": "正在加载数据...",
              "sLengthMenu": "显示 _MENU_ 条 ",
              "sZeroRecords": "没有您要搜索的内容",
              "sInfo": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
              "sInfoEmpty": "记录数为0",
              "sInfoFiltered": "(全部记录数 _MAX_  条)",
              "sInfoPostFix": "",
              "sSearch": "搜索 ",
              "sUrl": "",
              "oPaginate": {
                  "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                  "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                  "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                  "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
              }
          },
          "aLengthMenu": [[ 10, 15, 20], [ 10, 15, 20]],
          "bStateSave": false,
          "bProcessing": true,
          "bServerSide": true,
          //"sAjaxSource": url,//获取服务端数据配置URL
          "sAjaxSource": "/breakFast/assets/js/invest/refund_details.json",
          "sDom": "t<'row'<'col-sm-4'i><'col-sm-4'l><'col-sm-4'p>>",
          "sPaginationType": "full_numbers",
          //"aaSorting": [[ 1, "asc" ]],
          "aoColumns": [
              {"mData": "id", 'sClass': '',"bSortable": false},
              {"mData": "time", 'sClass': '',"bSortable": false},
              {"mData": "principal", 'sClass': '',"bSortable": false},
              {"mData": "interest", 'sClass': '',"bSortable": false},
              {"mData": "penalty", 'sClass': '',"bSortable": false},
              {
                  "mData": function(row, type, val){
                      var status_str ="";
                      switch (row.status){
                          case 1:
                              status_str="还款中";
                              break;
                          case 2:
                              status_str="待还款";
                              break;
                          default :
                              break;
                      }
                      return status_str;
                  },
                  "bSortable": false
              }
          ],
          "fnInitComplete":function(){
              //初始化完成回调
          },
          "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
          }
      });
      $("#invest_record_table").dataTable( {
          "oLanguage": { // 汉化
              "sProcessing": "正在加载数据...",
              "sLengthMenu": "显示 _MENU_ 条 ",
              "sZeroRecords": "没有您要搜索的内容",
              "sInfo": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
              "sInfoEmpty": "记录数为0",
              "sInfoFiltered": "(全部记录数 _MAX_  条)",
              "sInfoPostFix": "",
              "sSearch": "搜索 ",
              "sUrl": "",
              "oPaginate": {
                  "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                  "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                  "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                  "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
              }
          },
          "aLengthMenu": [[ 10, 15, 20], [ 10, 15, 20]],
          "bStateSave": false,
          "bProcessing": true,
          "bServerSide": true,
          //"sAjaxSource": url,//获取服务端数据配置URL
          "sAjaxSource": "/breakFast/assets/js/invest/invest_record.json",
          "sDom": "t<'row'<'col-sm-4'i><'col-sm-4'l><'col-sm-4'p>>",
          "sPaginationType": "full_numbers",
          //"aaSorting": [[ 1, "asc" ]],
          "aoColumns": [
              {"mData": "time", 'sClass': '',"bSortable": false},
              {"mData": "user_name", 'sClass': '',"bSortable": false},
              {"mData": "amount", 'sClass': '',"bSortable": false}
          ],
          "fnInitComplete":function(){
              //初始化完成回调
          },
          "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
          }
      });
      var refund_details;
      $("#show_refund_details").click(function(){
          $("#refund_details").show();
          if(refund_details == undefined){
              refund_details = $("#refund_details").dataTable( {
                  "oLanguage": { // 汉化
                      "sProcessing": "正在加载数据...",
                      "sLengthMenu": "显示 _MENU_ 条 ",
                      "sZeroRecords": "没有您要搜索的内容",
                      "sInfo": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
                      "sInfoEmpty": "记录数为0",
                      "sInfoFiltered": "(全部记录数 _MAX_  条)",
                      "sInfoPostFix": "",
                      "sSearch": "搜索 ",
                      "sUrl": "",
                      "oPaginate": {
                          "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
                          "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
                          "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
                          "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
                      }
                  },
                  "bStateSave": false,
                  "bProcessing": true,
                  "bServerSide": true,
                  //"sAjaxSource": url,//获取服务端数据配置URL
                  "sAjaxSource": "/breakFast/assets/js/invest/refund_details.json",
                  "sDom": "t",
                  "sPaginationType": "full_numbers",
                  //"aaSorting": [[ 1, "asc" ]],
                  "aoColumns": [
                      {"mData": "principal", 'sClass': '',"bSortable": false},
                      {"mData": "penalty", 'sClass': '',"bSortable": false},
                      {"mData": "interest", 'sClass': '',"bSortable": false},
                      {"mData": "time", 'sClass': '',"bSortable": false}
                  ],
                  "fnInitComplete":function(){
                      //初始化完成回调
                  },
                  "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
                  }
              });
          }else{
              refund_details.fnDraw();
          }
      });

  });
}(window.jQuery);
