+function ($) {
    $(function () {
        //$("#system_notice_table").dataTable( {
        //    "oLanguage": { // 汉化
        //        "sProcessing": "正在加载数据...",
        //        "sLengthMenu": "显示 _MENU_ 条 ",
        //        "sZeroRecords": "没有您要搜索的内容",
        //        "sInfo": "显示第_START_ 到第 _END_ 条记录，总记录数为 _TOTAL_ 条",
        //        "sInfoEmpty": "记录数为0",
        //        "sInfoFiltered": "(全部记录数 _MAX_  条)",
        //        "sInfoPostFix": "",
        //        "sSearch": "搜索 ",
        //        "sUrl": "",
        //        "oPaginate": {
        //            "sFirst": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",
        //            "sPrevious": " <i class=\"fa fa-angle-left\"></i> 上一页 ",
        //            "sNext": " 下一页 <i class=\"fa fa-angle-right\"></i> ",
        //            "sLast": " 末页 <i class=\"fa fa-angle-double-right\"></i> "
        //        }
        //    },
        //    "aLengthMenu": [[ 10, 15, 20], [ 10, 15, 20]],
        //    "bStateSave": false,
        //    "bProcessing": true,
        //    "bServerSide": true,
        //    //"sAjaxSource": url,//获取服务端数据配置URL
        //    "sAjaxSource": "/assets/user/js/user/system_message.json",
        //    "sDom": "t<'row'<'col-sm-5'i><'col-sm-7'p>>",
        //    "sPaginationType": "full_numbers",
        //    //"aaSorting": [[ 1, "asc" ]],
        //    "aoColumns": [
        //        {
        //            "mData": function(row, type, val){
        //
        //                return '<span class="text-purple">'+row.title+'</span>';
        //            },
        //            "bSortable": false
        //        },
        //        {"mData": "time", 'sClass': '',"bSortable": false},
        //        {
        //            "mData": function(row, type, val){
        //                var type_str ="";
        //                switch (row.type){
        //                    case 1:
        //                        type_str="开户消息";
        //                        break;
        //                    case 2:
        //                        type_str="其他消息";
        //                        break;
        //                    default :
        //                        break;
        //                }
        //                return type_str;
        //            },
        //            "bSortable": false
        //        }
        //    ],
        //    "fnInitComplete":function(){
        //        //初始化完成回调
        //    },
        //    "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
        //    }
        //});

        $("#email_notice_table").dataTable( {
            "autoWidth": false,//是否自适应列宽，默认true，相关选项：columns.width
            "info": true,//是否显示表格信息（默认位于左下角），默认true，相关选项：dom
            "processing": true,//是否显示dataTable正在处理状态，默认false
            "dom": "tr<'row'<'col-sm-5'i><'col-sm-3'l><'col-sm-4'p>>",//定义表格控制元素的位置，默认"lfrtip"
            "lengthChange": true,//是否可改变每页显示记录数，默认true，相关选项：lengthMenu,dom,paging
            "lengthMenu": [[ 10, 15, 20], [ 10, 15, 20]],//设置每页记录可选总数下拉列表，默认[ 10, 25, 50, 100 ]
            "ordering": true,//是否开启字段排序，默认true，相关选项：columns.orderable
            "orderClasses": false,//是否高亮显示排序的列，默认true
            "orderMulti": true,//是否开启多列排序，默认true
            "paging": true,//是否开启分页，默认true
            "pageLength": 10,//设置初始每页记录数，默认10
            "displayStart": 0,//当开启分页时，设置从第几条记录开始加载，默认0
            "pagingType": "full",//full_numbers//分页按钮的显示方式，默认"simple_numbers"
            "searching": true,//是否开启本地搜索（过滤），默认true，相关选项：dom
            "serverSide": true,//是否启用服务器模式（需配置选项ajax，过滤、分页、排序等处理放在服务端），默认false
            "stateSave": false,//是否开启状态储存（如分页位置、每页记录数、过滤条件等），默认false，相关选项：stateSaveCallback,stateLoadCallback
            "retrieve": false,//检索已经存在的Datatables实例，若启用该项则直接返回现有实例，默认false，相关选项：destroy
            "destroy": false,//若已存在Datatables实例则更新并替换其配置和数据，否则新建Datatables实例，默认false
            //"stripeClasses": [ 'odd', 'even' ],//设置斑马条的css class，值类型为array
            "tabIndex": 0,//设置Tab键浏览顺序，默认0，值可为0|-1|integer
            "search": {//设置全局初始过滤条件，值可为string|object
                "caseInsensitive": true,//搜索时是否大小写敏感，默认true
                "regex": false,//是否支持正则搜索，默认false
                "smart": true//是否启用智能关键字搜索，默认true，当选项search.regex启用时会相互冲突
                //"search": "condition ..."//过滤条件
            },
            //"searchCols": [//设置每列初始过滤条件
            //    null,
            //    { "search": "My filter" },
            //    { "search": "^[0-9]", "escapeRegex": false }
            //],
            "ajax": {//从一个数据源获取数据给DataTable显示，值可以为string|object|function(data, callback, settings)，分别表示：请求url|类似jQuery ajax请求的参数设置|可自定义从本地或从服务端取数据源,参数分别表示发给服务端请求数据和function(dataSrc)和DataTable状态
                "url": path + "/client/messageCenter/findMailRecordListWithDraw",//请求url
                "type": "POST",
                "data": function ( data ) {//[object|function(data)]，添加/设置通过Ajax提交到服务端的请求数据data（DataTable内部构建好对象）参数
                    var postData = new Object();
                    postData["draw"] = data.draw;
                    postData["page"] = data.start/data.length + 1;
                    postData["rows"] = data.length;
                    postData["field"] = data.columns[data.order[0]["column"]]["name"];
                    postData["sortOp"] = data.order[0]["dir"];
                    return postData;
                },
                "dataSrc": "dataObj"//定义从数据源获取那个属性（默认data属性）给DataTable显示（不用jQuery ajax的success方法，因为方法是DataTable内部调用的），值可以为""|<property_name>|function(data)三种
            },
            "language": {//（国际化）汉化
                "emptyTable": "没有数据在表格中",//表格没有数据时显示的信息，优先于选项language.zeroRecords显示，默认"No data available in table"
                "zeroRecords": "没有您要搜索的内容",//搜索结果为空时显示的信息，默认"No matching records found"
                "info": "显示第 _START_ 至 _END_ 条记录，共 _TOTAL_ 条记录",//表格信息显示字符串，默认"Showing _START_ to _END_ of _TOTAL_ entries"，占位符有_START_ _END_ _TOTAL_ _MAX_ _PAGE_ _PAGES_
                "infoEmpty": "记录数为0",//没有数据时表格信息显示字符串，默认"Showing 0 to 0 of 0 entries"
                "infoFiltered": "",//搜索后在表格信息尾添加的内容，默认"(filtered from _MAX_ total entries)"
                "infoPostFix": "(全部记录数 _MAX_  条)",//始终添加在表格信息尾部的内容（在选项language.infoFiltered后），默认""
                "lengthMenu": "每页 _MENU_ 条 ",//默认"Show _MENU_ entries"
                "loadingRecords": "加载中...",//Ajax加载数据时的提示信息，默认"Loading..."，在server-side模式下该选项无效
                "processing": "正在处理中...",//处理时显示信息，默认"Processing..."
                "search": "搜索 ",//搜索控件显示内容，默认"Search:"，占位符：_INPUT_
                "paginate": {//分页控件显示内容
                    "first": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",//默认"First"
                    "previous": " <i class=\"fa fa-angle-left\"></i> 上一页 ",//默认"Previous"
                    "next": " 下一页 <i class=\"fa fa-angle-right\"></i> ",//默认"Next"
                    "last": " 末页 <i class=\"fa fa-angle-double-right\"></i> "//默认"Last"
                },
                aria: {//WAI-ARIA(无障碍应用）
                    "sortAscending": "点击使该列升序排序",//默认": activate to sort column ascending"
                    "sortDescending": "点击使该列升序排序",//默认": activate to sort column descending"
                    paginate: {
                        first: "首页",
                        previous: "上一页",
                        next: "下一页",
                        last: "末页"
                    }
                }
            },
            "columnDefs": [{//设置一或多列，作用等价于选项columns
                //"targets": 0,//指定要设定的列，值可为array|string(css class或_all)|integer
                //"cellType": "td",//设置单元格类型，默认为"td"，值可为td|th
                //"name": "operateTime",//为该列取个别名
                //"data": "operateTime",//（getter、setter）指定该列数据从数据源的哪一属性取值，三种情况：undefined|null|function(1.10.1+)（1.10.3+中还可使用DOM数据源），值类型有：integer|string(.[]())|null|object|function(row, type, set, meta)，相关选项：columns.render、columns.defaultContent
                //"render": "operateTime",//（getter，和columns.data不可同时有效使用）定义单元格显示内容，值类型有：integer|string(.[]())|object|function(data, type, row, meta)——数组使用：arr[, ].name和arr.0.name——Datatables把不同数据的不同操作叫做 orthogonal-data
                //"defaultContent": "<i>Not set</i>",//设置单元格默认值
                //"className": "",//设置单元格css class
                //"title": "My column title",//设置该列标题（即<th>内容）（否则从DOM中读取）
                //"type": "string",//设置该列类型（用于排序、搜索中），client-side processing mode下起作用，默认自动检测原数据类型，值有：date|num|num-fmt|html-num|html-num-fmt|html|string
                //"createdCell": function (cell, cellData, rowData, rowIndex, colIndex) {},//单元格生成后的回调，可用于修改DOM，其中参数cellData值是还未经选项columns.render处理，故是原数据
                //"contentPadding": "mmm",//为列中最长text单元格添上该字符串长度padding，已解决运用autoWidth时如"mmm"比"iiii"宽却比它短的bug
                //"visible": true,//设置该列是否可见，默认true
                //"width": "20%",//自定义该列宽度，值可为20%|30px|3em，相关选项autoWidth
                //"orderable": true,//该列是否允许排序
                //"orderData": [ 0, 1 ],//定义多个列的默认排序条件，值类型为：integer|array
                //"orderDataType": "dom-select",//指定该列单元格DOM类型（否则一般直接从数据源读取来排序），用以排序（需引入相关排序plug-in）
                //"orderSequence": [ "desc", "asc", "asc" ],//指定该列可以使用的排序方式，默认[ "desc", "asc" ]
                //"searchable": true//是否允许在该列使用过滤，默认true
            }],
            "columns": [//子选项见columnDefs中
                {"data": "id", "name": "id", "width": "0%", "className": "", "orderable": true, "searchable": true, "visible": false},
                {"data": function (row, type, val) {return '<span class="text-purple">'+row.title+'</span>';}, "name": "title", "width": "15%", "className": "", "orderable": false},
                {"data": "email", "name": "email", "width": "15%", "className": "", "orderable": false},
                {"data": "senderName", "name": "senderName", "width": "10%", "className": "", "orderable": false},
                {"data": function (row, type, val) {return styleDate(row.sendingTime, null, null);}, "name": "sendingTime", "width": "15%", "className": "", "orderable": true},
                {"data": "content", "name": "content", "width": "35%", "className": "", "orderable": false},
                {"data": function (row, type, val) {return styleMessageSendingStatue(row.sendingStatue);}, "name": "sendingStatue", "width": "10%", "className": "", "orderable": true}
            ],
            "rowCallback": function( row, data, index ) {},//在记录创建（即createdRow）后但在渲染前调用. Be used for setting the row class name or otherwise manipulating the row's TR element
            //"headerCallback": function( thead, data, start, end, display ) {},//每次显示表头（也是draw事件）后调用
            //"footerCallback": function( tfoot, data, start, end, display ) {},//同headerCallback
            //"formatNumber": function ( toFormat ) {},//格式化（表格信息、表分页数等）数值，使更具可读性，否则默认使用选项language.thousands
            //"infoCallback": function( settings, start, end, max, total, pre ) {},//表格信息显示后调用（若选项info值为false则该回调不起作用）
            "preDrawCallback": function( settings ) {},//每次执行绘制（即draw事件）前调用，返回false时可取消绘制，也可用来更新清理显示
            "drawCallback": function( settings ) {},//每次执行绘制（即draw事件）后调用
            "initComplete": function( settings, json ) {}//表格初始化（即完成加载绘制）后调用，可以用来得知数据源何时加载完毕
        });

        $("#message_notice_table").dataTable( {
            "autoWidth": false,//是否自适应列宽，默认true，相关选项：columns.width
            "info": true,//是否显示表格信息（默认位于左下角），默认true，相关选项：dom
            "processing": true,//是否显示dataTable正在处理状态，默认false
            "dom": "tr<'row'<'col-sm-5'i><'col-sm-3'l><'col-sm-4'p>>",//定义表格控制元素的位置，默认"lfrtip"
            "lengthChange": true,//是否可改变每页显示记录数，默认true，相关选项：lengthMenu,dom,paging
            "lengthMenu": [[ 10, 15, 20], [ 10, 15, 20]],//设置每页记录可选总数下拉列表，默认[ 10, 25, 50, 100 ]
            "ordering": true,//是否开启字段排序，默认true，相关选项：columns.orderable
            "orderClasses": false,//是否高亮显示排序的列，默认true
            "orderMulti": true,//是否开启多列排序，默认true
            "paging": true,//是否开启分页，默认true
            "pageLength": 10,//设置初始每页记录数，默认10
            "displayStart": 0,//当开启分页时，设置从第几条记录开始加载，默认0
            "pagingType": "full",//full_numbers//分页按钮的显示方式，默认"simple_numbers"
            "searching": true,//是否开启本地搜索（过滤），默认true，相关选项：dom
            "serverSide": true,//是否启用服务器模式（需配置选项ajax，过滤、分页、排序等处理放在服务端），默认false
            "stateSave": false,//是否开启状态储存（如分页位置、每页记录数、过滤条件等），默认false，相关选项：stateSaveCallback,stateLoadCallback
            "retrieve": false,//检索已经存在的Datatables实例，若启用该项则直接返回现有实例，默认false，相关选项：destroy
            "destroy": false,//若已存在Datatables实例则更新并替换其配置和数据，否则新建Datatables实例，默认false
            //"stripeClasses": [ 'odd', 'even' ],//设置斑马条的css class，值类型为array
            "tabIndex": 0,//设置Tab键浏览顺序，默认0，值可为0|-1|integer
            "search": {//设置全局初始过滤条件，值可为string|object
                "caseInsensitive": true,//搜索时是否大小写敏感，默认true
                "regex": false,//是否支持正则搜索，默认false
                "smart": true//是否启用智能关键字搜索，默认true，当选项search.regex启用时会相互冲突
                //"search": "condition ..."//过滤条件
            },
            //"searchCols": [//设置每列初始过滤条件
            //    null,
            //    { "search": "My filter" },
            //    { "search": "^[0-9]", "escapeRegex": false }
            //],
            "ajax": {//从一个数据源获取数据给DataTable显示，值可以为string|object|function(data, callback, settings)，分别表示：请求url|类似jQuery ajax请求的参数设置|可自定义从本地或从服务端取数据源,参数分别表示发给服务端请求数据和function(dataSrc)和DataTable状态
                "url": path + "/client/messageCenter/findMessageRecorderListWithDraw",//请求url
                "type": "POST",
                "data": function ( data ) {//[object|function(data)]，添加/设置通过Ajax提交到服务端的请求数据data（DataTable内部构建好对象）参数
                    var postData = new Object();
                    postData["draw"] = data.draw;
                    postData["page"] = data.start/data.length + 1;
                    postData["rows"] = data.length;
                    postData["field"] = data.columns[data.order[0]["column"]]["name"];
                    postData["sortOp"] = data.order[0]["dir"];
                    return postData;
                },
                "dataSrc": "dataObj"//定义从数据源获取那个属性（默认data属性）给DataTable显示（不用jQuery ajax的success方法，因为方法是DataTable内部调用的），值可以为""|<property_name>|function(data)三种
            },
            "language": {//（国际化）汉化
                "emptyTable": "没有数据在表格中",//表格没有数据时显示的信息，优先于选项language.zeroRecords显示，默认"No data available in table"
                "zeroRecords": "没有您要搜索的内容",//搜索结果为空时显示的信息，默认"No matching records found"
                "info": "显示第 _START_ 至 _END_ 条记录，共 _TOTAL_ 条记录",//表格信息显示字符串，默认"Showing _START_ to _END_ of _TOTAL_ entries"，占位符有_START_ _END_ _TOTAL_ _MAX_ _PAGE_ _PAGES_
                "infoEmpty": "记录数为0",//没有数据时表格信息显示字符串，默认"Showing 0 to 0 of 0 entries"
                "infoFiltered": "",//搜索后在表格信息尾添加的内容，默认"(filtered from _MAX_ total entries)"
                "infoPostFix": "(全部记录数 _MAX_  条)",//始终添加在表格信息尾部的内容（在选项language.infoFiltered后），默认""
                "lengthMenu": "每页 _MENU_ 条 ",//默认"Show _MENU_ entries"
                "loadingRecords": "加载中...",//Ajax加载数据时的提示信息，默认"Loading..."，在server-side模式下该选项无效
                "processing": "正在处理中...",//处理时显示信息，默认"Processing..."
                "search": "搜索 ",//搜索控件显示内容，默认"Search:"，占位符：_INPUT_
                "paginate": {//分页控件显示内容
                    "first": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",//默认"First"
                    "previous": " <i class=\"fa fa-angle-left\"></i> 上一页 ",//默认"Previous"
                    "next": " 下一页 <i class=\"fa fa-angle-right\"></i> ",//默认"Next"
                    "last": " 末页 <i class=\"fa fa-angle-double-right\"></i> "//默认"Last"
                },
                aria: {//WAI-ARIA(无障碍应用）
                    "sortAscending": "点击使该列升序排序",//默认": activate to sort column ascending"
                    "sortDescending": "点击使该列升序排序",//默认": activate to sort column descending"
                    paginate: {
                        first: "首页",
                        previous: "上一页",
                        next: "下一页",
                        last: "末页"
                    }
                }
            },
            "columnDefs": [{//设置一或多列，作用等价于选项columns
                //"targets": 0,//指定要设定的列，值可为array|string(css class或_all)|integer
                //"cellType": "td",//设置单元格类型，默认为"td"，值可为td|th
                //"name": "operateTime",//为该列取个别名
                //"data": "operateTime",//（getter、setter）指定该列数据从数据源的哪一属性取值，三种情况：undefined|null|function(1.10.1+)（1.10.3+中还可使用DOM数据源），值类型有：integer|string(.[]())|null|object|function(row, type, set, meta)，相关选项：columns.render、columns.defaultContent
                //"render": "operateTime",//（getter，和columns.data不可同时有效使用）定义单元格显示内容，值类型有：integer|string(.[]())|object|function(data, type, row, meta)——数组使用：arr[, ].name和arr.0.name——Datatables把不同数据的不同操作叫做 orthogonal-data
                //"defaultContent": "<i>Not set</i>",//设置单元格默认值
                //"className": "",//设置单元格css class
                //"title": "My column title",//设置该列标题（即<th>内容）（否则从DOM中读取）
                //"type": "string",//设置该列类型（用于排序、搜索中），client-side processing mode下起作用，默认自动检测原数据类型，值有：date|num|num-fmt|html-num|html-num-fmt|html|string
                //"createdCell": function (cell, cellData, rowData, rowIndex, colIndex) {},//单元格生成后的回调，可用于修改DOM，其中参数cellData值是还未经选项columns.render处理，故是原数据
                //"contentPadding": "mmm",//为列中最长text单元格添上该字符串长度padding，已解决运用autoWidth时如"mmm"比"iiii"宽却比它短的bug
                //"visible": true,//设置该列是否可见，默认true
                //"width": "20%",//自定义该列宽度，值可为20%|30px|3em，相关选项autoWidth
                //"orderable": true,//该列是否允许排序
                //"orderData": [ 0, 1 ],//定义多个列的默认排序条件，值类型为：integer|array
                //"orderDataType": "dom-select",//指定该列单元格DOM类型（否则一般直接从数据源读取来排序），用以排序（需引入相关排序plug-in）
                //"orderSequence": [ "desc", "asc", "asc" ],//指定该列可以使用的排序方式，默认[ "desc", "asc" ]
                //"searchable": true//是否允许在该列使用过滤，默认true
            }],
            "columns": [//子选项见columnDefs中
                {"data": "id", "name": "id", "width": "0%", "className": "", "orderable": true, "searchable": true, "visible": false},
                {"data": function (row, type, val) {return '<span class="text-purple">'+row.title+'</span>';}, "name": "title", "width": "15%", "className": "", "orderable": false},
                {"data": "phoneNumber", "name": "phoneNumber", "width": "10%", "className": "", "orderable": false},
                {"data": "senderName", "name": "senderName", "width": "15%", "className": "", "orderable": false},
                {"data": function (row, type, val) {return styleDate(row.sendingTime, null, null);}, "name": "sendingTime", "width": "15%", "className": "", "orderable": true},
                {"data": "messageContent", "name": "messageContent", "width": "35%", "className": "", "orderable": false},
                {"data": function (row, type, val) {return styleMessageSendingStatue(row.sendingStatue);}, "name": "sendingStatue", "width": "10%", "className": "", "orderable": true}
            ],
            "rowCallback": function( row, data, index ) {},//在记录创建（即createdRow）后但在渲染前调用. Be used for setting the row class name or otherwise manipulating the row's TR element
            //"headerCallback": function( thead, data, start, end, display ) {},//每次显示表头（也是draw事件）后调用
            //"footerCallback": function( tfoot, data, start, end, display ) {},//同headerCallback
            //"formatNumber": function ( toFormat ) {},//格式化（表格信息、表分页数等）数值，使更具可读性，否则默认使用选项language.thousands
            //"infoCallback": function( settings, start, end, max, total, pre ) {},//表格信息显示后调用（若选项info值为false则该回调不起作用）
            "preDrawCallback": function( settings ) {},//每次执行绘制（即draw事件）前调用，返回false时可取消绘制，也可用来更新清理显示
            "drawCallback": function( settings ) {},//每次执行绘制（即draw事件）后调用
            "initComplete": function( settings, json ) {}//表格初始化（即完成加载绘制）后调用，可以用来得知数据源何时加载完毕
        });

        $("#private_message_table").dataTable( {
            "autoWidth": false,//是否自适应列宽，默认true，相关选项：columns.width
            "info": true,//是否显示表格信息（默认位于左下角），默认true，相关选项：dom
            "processing": true,//是否显示dataTable正在处理状态，默认false
            "dom": "tr<'row'<'col-sm-5'i><'col-sm-3'l><'col-sm-4'p>>",//定义表格控制元素的位置，默认"lfrtip"
            "lengthChange": true,//是否可改变每页显示记录数，默认true，相关选项：lengthMenu,dom,paging
            "lengthMenu": [[ 10, 15, 20], [ 10, 15, 20]],//设置每页记录可选总数下拉列表，默认[ 10, 25, 50, 100 ]
            "ordering": true,//是否开启字段排序，默认true，相关选项：columns.orderable
            "orderClasses": false,//是否高亮显示排序的列，默认true
            "orderMulti": true,//是否开启多列排序，默认true
            "paging": true,//是否开启分页，默认true
            "pageLength": 10,//设置初始每页记录数，默认10
            "displayStart": 0,//当开启分页时，设置从第几条记录开始加载，默认0
            "pagingType": "full",//full_numbers//分页按钮的显示方式，默认"simple_numbers"
            "searching": true,//是否开启本地搜索（过滤），默认true，相关选项：dom
            "serverSide": true,//是否启用服务器模式（需配置选项ajax，过滤、分页、排序等处理放在服务端），默认false
            "stateSave": false,//是否开启状态储存（如分页位置、每页记录数、过滤条件等），默认false，相关选项：stateSaveCallback,stateLoadCallback
            "retrieve": false,//检索已经存在的Datatables实例，若启用该项则直接返回现有实例，默认false，相关选项：destroy
            "destroy": false,//若已存在Datatables实例则更新并替换其配置和数据，否则新建Datatables实例，默认false
            //"stripeClasses": [ 'odd', 'even' ],//设置斑马条的css class，值类型为array
            "tabIndex": 0,//设置Tab键浏览顺序，默认0，值可为0|-1|integer
            "search": {//设置全局初始过滤条件，值可为string|object
                "caseInsensitive": true,//搜索时是否大小写敏感，默认true
                "regex": false,//是否支持正则搜索，默认false
                "smart": true//是否启用智能关键字搜索，默认true，当选项search.regex启用时会相互冲突
                //"search": "condition ..."//过滤条件
            },
            //"searchCols": [//设置每列初始过滤条件
            //    null,
            //    { "search": "My filter" },
            //    { "search": "^[0-9]", "escapeRegex": false }
            //],
            "ajax": {//从一个数据源获取数据给DataTable显示，值可以为string|object|function(data, callback, settings)，分别表示：请求url|类似jQuery ajax请求的参数设置|可自定义从本地或从服务端取数据源,参数分别表示发给服务端请求数据和function(dataSrc)和DataTable状态
                "url": path + "/client/messageCenter/findPrivateMessageListWithDraw",//请求url
                "type": "POST",
                "data": function ( data ) {//[object|function(data)]，添加/设置通过Ajax提交到服务端的请求数据data（DataTable内部构建好对象）参数
                    var postData = new Object();
                    postData["draw"] = data.draw;
                    postData["page"] = data.start/data.length + 1;
                    postData["rows"] = data.length;
                    postData["field"] = data.columns[data.order[0]["column"]]["name"];
                    postData["sortOp"] = data.order[0]["dir"];
                    return postData;
                },
                "dataSrc": "dataObj"//定义从数据源获取那个属性（默认data属性）给DataTable显示（不用jQuery ajax的success方法，因为方法是DataTable内部调用的），值可以为""|<property_name>|function(data)三种
            },
            "language": {//（国际化）汉化
                "emptyTable": "没有数据在表格中",//表格没有数据时显示的信息，优先于选项language.zeroRecords显示，默认"No data available in table"
                "zeroRecords": "没有您要搜索的内容",//搜索结果为空时显示的信息，默认"No matching records found"
                "info": "显示第 _START_ 至 _END_ 条记录，共 _TOTAL_ 条记录",//表格信息显示字符串，默认"Showing _START_ to _END_ of _TOTAL_ entries"，占位符有_START_ _END_ _TOTAL_ _MAX_ _PAGE_ _PAGES_
                "infoEmpty": "记录数为0",//没有数据时表格信息显示字符串，默认"Showing 0 to 0 of 0 entries"
                "infoFiltered": "",//搜索后在表格信息尾添加的内容，默认"(filtered from _MAX_ total entries)"
                "infoPostFix": "(全部记录数 _MAX_  条)",//始终添加在表格信息尾部的内容（在选项language.infoFiltered后），默认""
                "lengthMenu": "每页 _MENU_ 条 ",//默认"Show _MENU_ entries"
                "loadingRecords": "加载中...",//Ajax加载数据时的提示信息，默认"Loading..."，在server-side模式下该选项无效
                "processing": "正在处理中...",//处理时显示信息，默认"Processing..."
                "search": "搜索 ",//搜索控件显示内容，默认"Search:"，占位符：_INPUT_
                "paginate": {//分页控件显示内容
                    "first": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",//默认"First"
                    "previous": " <i class=\"fa fa-angle-left\"></i> 上一页 ",//默认"Previous"
                    "next": " 下一页 <i class=\"fa fa-angle-right\"></i> ",//默认"Next"
                    "last": " 末页 <i class=\"fa fa-angle-double-right\"></i> "//默认"Last"
                },
                aria: {//WAI-ARIA(无障碍应用）
                    "sortAscending": "点击使该列升序排序",//默认": activate to sort column ascending"
                    "sortDescending": "点击使该列升序排序",//默认": activate to sort column descending"
                    paginate: {
                        first: "首页",
                        previous: "上一页",
                        next: "下一页",
                        last: "末页"
                    }
                }
            },
            "columnDefs": [{//设置一或多列，作用等价于选项columns
                //"targets": 0,//指定要设定的列，值可为array|string(css class或_all)|integer
                //"cellType": "td",//设置单元格类型，默认为"td"，值可为td|th
                //"name": "operateTime",//为该列取个别名
                //"data": "operateTime",//（getter、setter）指定该列数据从数据源的哪一属性取值，三种情况：undefined|null|function(1.10.1+)（1.10.3+中还可使用DOM数据源），值类型有：integer|string(.[]())|null|object|function(row, type, set, meta)，相关选项：columns.render、columns.defaultContent
                //"render": "operateTime",//（getter，和columns.data不可同时有效使用）定义单元格显示内容，值类型有：integer|string(.[]())|object|function(data, type, row, meta)——数组使用：arr[, ].name和arr.0.name——Datatables把不同数据的不同操作叫做 orthogonal-data
                //"defaultContent": "<i>Not set</i>",//设置单元格默认值
                //"className": "",//设置单元格css class
                //"title": "My column title",//设置该列标题（即<th>内容）（否则从DOM中读取）
                //"type": "string",//设置该列类型（用于排序、搜索中），client-side processing mode下起作用，默认自动检测原数据类型，值有：date|num|num-fmt|html-num|html-num-fmt|html|string
                //"createdCell": function (cell, cellData, rowData, rowIndex, colIndex) {},//单元格生成后的回调，可用于修改DOM，其中参数cellData值是还未经选项columns.render处理，故是原数据
                //"contentPadding": "mmm",//为列中最长text单元格添上该字符串长度padding，已解决运用autoWidth时如"mmm"比"iiii"宽却比它短的bug
                //"visible": true,//设置该列是否可见，默认true
                //"width": "20%",//自定义该列宽度，值可为20%|30px|3em，相关选项autoWidth
                //"orderable": true,//该列是否允许排序
                //"orderData": [ 0, 1 ],//定义多个列的默认排序条件，值类型为：integer|array
                //"orderDataType": "dom-select",//指定该列单元格DOM类型（否则一般直接从数据源读取来排序），用以排序（需引入相关排序plug-in）
                //"orderSequence": [ "desc", "asc", "asc" ],//指定该列可以使用的排序方式，默认[ "desc", "asc" ]
                //"searchable": true//是否允许在该列使用过滤，默认true
            }],
            "columns": [//子选项见columnDefs中
                {"data": "id", "name": "id", "width": "0%", "className": "", "orderable": true, "searchable": true, "visible": false},
                {"data": "senderName", "name": "senderName", "width": "10%", "className": "", "orderable": false},
                {"data": function (row, type, val) {return styleDate(row.sendingTime, null, null);}, "name": "sendingTime", "width": "20%", "className": "", "orderable": true},
                {"data": "content", "name": "content", "width": "35%", "className": "", "orderable": false},
                {"data": function (row, type, val) {return styleDate(row.readTime, null, null);}, "name": "readTime", "width": "15%", "className": "", "orderable": true},
                {"data": function (row, type, val) {return '<span class="text-purple">'+stylePrivateMessageReadStatus(row.readStatus)+'</span>';}, "name": "readStatus", "width": "10%", "className": "", "orderable": true},
                {"data": function (row, type, val) {
                    if (stylePrivateMessageReadStatus(row.readStatus).indexOf("已") == -1)
                        return '<a href="#mark-private-message-read-modal" title="记为已读" id="mark-private-message" data-id="' + row.id + '" class="text-link m-l-sm" data-toggle="modal">[记为已读]</a>';
                    else
                        return "";
                }, "width": "10%", "orderable": false, "searchable": false}
            ],
            "rowCallback": function( row, data, index ) {},//在记录创建（即createdRow）后但在渲染前调用. Be used for setting the row class name or otherwise manipulating the row's TR element
            //"headerCallback": function( thead, data, start, end, display ) {},//每次显示表头（也是draw事件）后调用
            //"footerCallback": function( tfoot, data, start, end, display ) {},//同headerCallback
            //"formatNumber": function ( toFormat ) {},//格式化（表格信息、表分页数等）数值，使更具可读性，否则默认使用选项language.thousands
            //"infoCallback": function( settings, start, end, max, total, pre ) {},//表格信息显示后调用（若选项info值为false则该回调不起作用）
            "preDrawCallback": function( settings ) {},//每次执行绘制（即draw事件）前调用，返回false时可取消绘制，也可用来更新清理显示
            "drawCallback": function( settings ) {},//每次执行绘制（即draw事件）后调用
            "initComplete": function( settings, json ) {}//表格初始化（即完成加载绘制）后调用，可以用来得知数据源何时加载完毕
        });

        $(document).on("click", "#mark-private-message", function() {
            $("#privateMessageId").val($(this).attr("data-id"));
        });
        $("#mark-private-message-revoke").on("click", function() {
            $.ajax({
                url: path + "/client/messageCenter/markPrivateMessageRead",
                type: "POST",
                dataType: 'json',
                data: {
                    id: $('#privateMessageId').val()
                },
                success: function (json) {
                    if (json.success == true) {
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-success'><strong>标记成功，请刷新</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/message";
                                    }
                                }
                            }
                        });

                    } else {
                        bootbox.dialog({
                            closeButton: false,
                            title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                            message: "<div class='alert alert-danger'><strong>" + json.msg + "</strong><br/></div>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/user/index/message";
                                    }
                                }
                            }
                        });
                    }
                },
                error: function (XMLHttpRequest, errorThrown) {
                    messageBox("联系管理员，错误代码：" + XMLHttpRequest.status);
                    location.href = path + "/user/index/message";
                }
            })
        });

        $("#consult_and_feedback_table").dataTable( {
            "autoWidth": false,//是否自适应列宽，默认true，相关选项：columns.width
            "info": true,//是否显示表格信息（默认位于左下角），默认true，相关选项：dom
            "processing": true,//是否显示dataTable正在处理状态，默认false
            "dom": "tr<'row'<'col-sm-5'i><'col-sm-3'l><'col-sm-4'p>>",//定义表格控制元素的位置，默认"lfrtip"
            "lengthChange": true,//是否可改变每页显示记录数，默认true，相关选项：lengthMenu,dom,paging
            "lengthMenu": [[ 10, 15, 20], [ 10, 15, 20]],//设置每页记录可选总数下拉列表，默认[ 10, 25, 50, 100 ]
            "ordering": true,//是否开启字段排序，默认true，相关选项：columns.orderable
            "orderClasses": false,//是否高亮显示排序的列，默认true
            "orderMulti": true,//是否开启多列排序，默认true
            "paging": true,//是否开启分页，默认true
            "pageLength": 10,//设置初始每页记录数，默认10
            "displayStart": 0,//当开启分页时，设置从第几条记录开始加载，默认0
            "pagingType": "full",//full_numbers//分页按钮的显示方式，默认"simple_numbers"
            "searching": true,//是否开启本地搜索（过滤），默认true，相关选项：dom
            "serverSide": true,//是否启用服务器模式（需配置选项ajax，过滤、分页、排序等处理放在服务端），默认false
            "stateSave": false,//是否开启状态储存（如分页位置、每页记录数、过滤条件等），默认false，相关选项：stateSaveCallback,stateLoadCallback
            "retrieve": false,//检索已经存在的Datatables实例，若启用该项则直接返回现有实例，默认false，相关选项：destroy
            "destroy": false,//若已存在Datatables实例则更新并替换其配置和数据，否则新建Datatables实例，默认false
            //"stripeClasses": [ 'odd', 'even' ],//设置斑马条的css class，值类型为array
            "tabIndex": 0,//设置Tab键浏览顺序，默认0，值可为0|-1|integer
            "search": {//设置全局初始过滤条件，值可为string|object
                "caseInsensitive": true,//搜索时是否大小写敏感，默认true
                "regex": false,//是否支持正则搜索，默认false
                "smart": true//是否启用智能关键字搜索，默认true，当选项search.regex启用时会相互冲突
                //"search": "condition ..."//过滤条件
            },
            //"searchCols": [//设置每列初始过滤条件
            //    null,
            //    { "search": "My filter" },
            //    { "search": "^[0-9]", "escapeRegex": false }
            //],
            "ajax": {//从一个数据源获取数据给DataTable显示，值可以为string|object|function(data, callback, settings)，分别表示：请求url|类似jQuery ajax请求的参数设置|可自定义从本地或从服务端取数据源,参数分别表示发给服务端请求数据和function(dataSrc)和DataTable状态
                "url": path + "/client/messageCenter/findConsultFeedbackManageListWithDraw",//请求url
                "type": "POST",
                "data": function ( data ) {//[object|function(data)]，添加/设置通过Ajax提交到服务端的请求数据data（DataTable内部构建好对象）参数
                    var postData = new Object();
                    postData["draw"] = data.draw;
                    postData["page"] = data.start/data.length + 1;
                    postData["rows"] = data.length;
                    postData["field"] = data.columns[data.order[0]["column"]]["name"];
                    postData["sortOp"] = data.order[0]["dir"];
                    return postData;
                },
                "dataSrc": "dataObj"//定义从数据源获取那个属性（默认data属性）给DataTable显示（不用jQuery ajax的success方法，因为方法是DataTable内部调用的），值可以为""|<property_name>|function(data)三种
            },
            "language": {//（国际化）汉化
                "emptyTable": "没有数据在表格中",//表格没有数据时显示的信息，优先于选项language.zeroRecords显示，默认"No data available in table"
                "zeroRecords": "没有您要搜索的内容",//搜索结果为空时显示的信息，默认"No matching records found"
                "info": "显示第 _START_ 至 _END_ 条记录，共 _TOTAL_ 条记录",//表格信息显示字符串，默认"Showing _START_ to _END_ of _TOTAL_ entries"，占位符有_START_ _END_ _TOTAL_ _MAX_ _PAGE_ _PAGES_
                "infoEmpty": "记录数为0",//没有数据时表格信息显示字符串，默认"Showing 0 to 0 of 0 entries"
                "infoFiltered": "",//搜索后在表格信息尾添加的内容，默认"(filtered from _MAX_ total entries)"
                "infoPostFix": "(全部记录数 _MAX_  条)",//始终添加在表格信息尾部的内容（在选项language.infoFiltered后），默认""
                "lengthMenu": "每页 _MENU_ 条 ",//默认"Show _MENU_ entries"
                "loadingRecords": "加载中...",//Ajax加载数据时的提示信息，默认"Loading..."，在server-side模式下该选项无效
                "processing": "正在处理中...",//处理时显示信息，默认"Processing..."
                "search": "搜索 ",//搜索控件显示内容，默认"Search:"，占位符：_INPUT_
                "paginate": {//分页控件显示内容
                    "first": " <i class=\"fa fa-angle-double-left\"></i> 首页 ",//默认"First"
                    "previous": " <i class=\"fa fa-angle-left\"></i> 上一页 ",//默认"Previous"
                    "next": " 下一页 <i class=\"fa fa-angle-right\"></i> ",//默认"Next"
                    "last": " 末页 <i class=\"fa fa-angle-double-right\"></i> "//默认"Last"
                },
                aria: {//WAI-ARIA(无障碍应用）
                    "sortAscending": "点击使该列升序排序",//默认": activate to sort column ascending"
                    "sortDescending": "点击使该列升序排序",//默认": activate to sort column descending"
                    paginate: {
                        first: "首页",
                        previous: "上一页",
                        next: "下一页",
                        last: "末页"
                    }
                }
            },
            "columnDefs": [{//设置一或多列，作用等价于选项columns
                //"targets": 0,//指定要设定的列，值可为array|string(css class或_all)|integer
                //"cellType": "td",//设置单元格类型，默认为"td"，值可为td|th
                //"name": "operateTime",//为该列取个别名
                //"data": "operateTime",//（getter、setter）指定该列数据从数据源的哪一属性取值，三种情况：undefined|null|function(1.10.1+)（1.10.3+中还可使用DOM数据源），值类型有：integer|string(.[]())|null|object|function(row, type, set, meta)，相关选项：columns.render、columns.defaultContent
                //"render": "operateTime",//（getter，和columns.data不可同时有效使用）定义单元格显示内容，值类型有：integer|string(.[]())|object|function(data, type, row, meta)——数组使用：arr[, ].name和arr.0.name——Datatables把不同数据的不同操作叫做 orthogonal-data
                //"defaultContent": "<i>Not set</i>",//设置单元格默认值
                //"className": "",//设置单元格css class
                //"title": "My column title",//设置该列标题（即<th>内容）（否则从DOM中读取）
                //"type": "string",//设置该列类型（用于排序、搜索中），client-side processing mode下起作用，默认自动检测原数据类型，值有：date|num|num-fmt|html-num|html-num-fmt|html|string
                //"createdCell": function (cell, cellData, rowData, rowIndex, colIndex) {},//单元格生成后的回调，可用于修改DOM，其中参数cellData值是还未经选项columns.render处理，故是原数据
                //"contentPadding": "mmm",//为列中最长text单元格添上该字符串长度padding，已解决运用autoWidth时如"mmm"比"iiii"宽却比它短的bug
                //"visible": true,//设置该列是否可见，默认true
                //"width": "20%",//自定义该列宽度，值可为20%|30px|3em，相关选项autoWidth
                //"orderable": true,//该列是否允许排序
                //"orderData": [ 0, 1 ],//定义多个列的默认排序条件，值类型为：integer|array
                //"orderDataType": "dom-select",//指定该列单元格DOM类型（否则一般直接从数据源读取来排序），用以排序（需引入相关排序plug-in）
                //"orderSequence": [ "desc", "asc", "asc" ],//指定该列可以使用的排序方式，默认[ "desc", "asc" ]
                //"searchable": true//是否允许在该列使用过滤，默认true
            }],
            "columns": [//子选项见columnDefs中
                {"data": "id", "name": "id", "width": "0%", "className": "", "orderable": true, "searchable": true, "visible": false},
                {"data": "contactPerson", "name": "contactPerson", "width": "10%", "className": "", "orderable": false},
                {"data": "contactWay", "name": "contactWay", "width": "20%", "className": "", "orderable": false},
                {"data": function (row, type, val) {return styleDate(row.feedbackTime, null, null);}, "name": "feedbackTime", "width": "20%", "className": "", "orderable": true},
                {"data": function (row, type, val) {
                    if (row.smUserInfo == null) {
                        row.smUserInfo = {username: ""};
                    }
                    return row.smUserInfo.username;
                }, "name": "smUserInfo.username", "width": "10%", "className": "", "orderable": false},
                {"data": function (row, type, val) {return styleDate(row.handlingTime, null, null);}, "name": "handlingTime", "width": "25%", "className": "", "orderable": true},
                {"data": "description", "name": "description", "width": "0%", "className": "", "orderable": false, "visible": false},
                {"data": "replyContent", "name": "replyContent", "width": "0%", "className": "", "orderable": false, "visible": false},
                {"data": function (row, type, val) {
                    return '<a href="#consult-and-feedback-details-modal" title="详情" id="consult-and-feedback-details" ' +
                        'data-id="' +row.id+ '" data-contactPerson="' +row.contactPerson+ '" data-contactWay="' +row.contactWay+ '" data-feedbackTime="' +row.feedbackTime+ '" data-smUserInfo-username="' +row.smUserInfo.username+ '" data-handlingTime="' +row.handlingTime+
                        '" data-description="' +row.description+ '" data-replyContent="' +row.replyContent+ '" ' +
                        'class="text-link m-l-sm" data-toggle="modal">[详情]</a>' +
                        '<a href="#send-consult-and-feedback-modal" title="反馈" class="text-link m-l-sm" data-toggle="modal">[反馈]</a>';
                }, "width": "20%", "orderable": false, "searchable": false}
            ],
            "rowCallback": function( row, data, index ) {},//在记录创建（即createdRow）后但在渲染前调用. Be used for setting the row class name or otherwise manipulating the row's TR element
            //"headerCallback": function( thead, data, start, end, display ) {},//每次显示表头（也是draw事件）后调用
            //"footerCallback": function( tfoot, data, start, end, display ) {},//同headerCallback
            //"formatNumber": function ( toFormat ) {},//格式化（表格信息、表分页数等）数值，使更具可读性，否则默认使用选项language.thousands
            //"infoCallback": function( settings, start, end, max, total, pre ) {},//表格信息显示后调用（若选项info值为false则该回调不起作用）
            "preDrawCallback": function( settings ) {},//每次执行绘制（即draw事件）前调用，返回false时可取消绘制，也可用来更新清理显示
            "drawCallback": function( settings ) {},//每次执行绘制（即draw事件）后调用
            "initComplete": function( settings, json ) {}//表格初始化（即完成加载绘制）后调用，可以用来得知数据源何时加载完毕
        });

        $(document).on("click", "#consult-and-feedback-details", function() {
            $("#contactPerson_show").val($(this).attr("data-contactPerson")=="null"?"":$(this).attr("data-contactPerson"));
            $("#contactWay_show").val($(this).attr("data-contactWay")=="null"?"":$(this).attr("data-contactWay"));
            var feedbackTimeStr = $(this).attr("data-feedbackTime")=="null"?"":styleDate(parseInt($(this).attr("data-feedbackTime")),null,null)
            $("#feedbackTime_show").val(feedbackTimeStr);
            $("#handPerson_show").val($(this).attr("data-smUserInfo-username")=="null"?"":$(this).attr("data-smUserInfo-username"));
            var handlingTimeStr = $(this).attr("data-handlingTime")=="null"?"":styleDate(parseInt($(this).attr("data-handlingTime")),null,null)
            $("#handlingTime_show").val(handlingTimeStr);
            $("#description_show").val($(this).attr("data-description")=="null"?"":$(this).attr("data-description"));
            $("#replyContent_show").val($(this).attr("data-replyContent")=="null"?"":$(this).attr("data-replyContent"));
        });

        //$("#sendConsultAndFeedbackBtn").on("click", function() {
        //    $("#send-consult-and-feedback-form").submit();
        //});
        $(function () {
            $('#send-consult-and-feedback-form').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    contactPerson: {
                        required: true
                    },
                    contactWay: {
                        required: true
                    },
                    description: {
                        required: true
                    }
                },
                messages: {
                    contactPerson: {
                        required: "必填"
                    },
                    contactWay: {
                        required: "必填"
                    },
                    description: {
                        required: "必填"
                    }

                },
                highlight: function (element) { // hightlight error inputs
                    $(element).closest(".form-group").addClass('has-error'); // set error class to the control group
                },
                success: function (label) {
                    label.closest(".form-group").removeClass('has-error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    error.insertAfter(element);
                },
                submitHandler: function (form) {
                    $.ajax({
                        url: path + "/client/messageCenter/sendConsultFeedbackManage",
                        type: "POST",
                        dataType: 'json',
                        data: {
                            contactPerson: $('#contactPerson').html(),
                            contactWay: $('#contactWay').val(),
                            description: $('#description').val()
                        },
                        success: function (json) {
                            if (json.success == true) {
                                bootbox.dialog({
                                    closeButton: false,
                                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                    message: "<div class='alert alert-success'><strong>发送反馈成功</strong><br/></div>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                location.href = path + "/user/index/message";
                                            }
                                        }
                                    }
                                });

                            } else {
                                bootbox.dialog({
                                    closeButton: false,
                                    title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                    message: "<div class='alert alert-danger'><strong>" + json.msg + "</strong><br/></div>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                location.href = path + "/user/index/message";
                                            }
                                        }
                                    }
                                });
                            }
                        },
                        error: function (XMLHttpRequest, errorThrown) {
                            var msg = "联系管理员，错误代码：" + XMLHttpRequest.status
                            bootbox.dialog({
                                closeButton: false,
                                title: "<strong class='blue'><span class='glyphicon glyphicon-info-sign'></span>提示消息</strong>",
                                message: "<div class='alert alert-danger'><strong>" + msg + "</strong><br/></div>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            location.href = path + "/user/index/message";
                                        }
                                    }
                                }
                            });
                        }

                    });
                    $("#send-consult-and-feedback-modal").modal("hide");
                }
            });
        });

    });
}(window.jQuery);