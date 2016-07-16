function paging(page){
    var pageSelector = "#pagination";
    var formSelector = "#searchForm";
    var projectListSelector = ".project-list";
    $.ajax({
        type: "GET",
        url:path+"/client/investment/getLoadRecordList",
        dataType:"json",
        data:{
            isHx : $("[name=bidType]").filter(".active").attr("isHx"),
            page: page,
            rows: 5,
            id : $(".bg-light.dk.text-purple").attr("name"),
            sord : $(".bg-light.dk.text-purple").children("i").hasClass("fa-sort-asc") ? "ASC" : "DESC",
            filters: getFiltersString(formSelector)
        },
        success:function(json){
            $(projectListSelector).empty();
            var data = json.dataObj;
            for(var index in data){
                $(projectListSelector).append(getHtml(data[index]));
            }

            if(json.totalpages < json.currentpage){
                json.totalpages = json.currentpage = 1;
            }

            $(pageSelector).bootstrapPaginator("setOptions",{
                totalPages:json.totalpages, //总页数
                currentPage: json.currentpage//当前页面
            });
        }
    });
}

function getHtml(loanRecord){
    var percent = loanRecord.raisedAmount/loanRecord.financingAmount*100;
    percent = percent.toFixed(2);
    return '<li class="list-group-item clearfix">'
        +'<h4>'
        +'<a class="text-purple" href="'+path+'/client/investmentProjectDetails/projectDetail?isHx='+(loanRecord.bidType==0?'false':'true')+'&loanId='+loanRecord.id+'">'+loanRecord.loanName+'</a>&nbsp;&nbsp;'
        +'<span class="badge bg-danger font-normal">'+( loanRecord.bidType == 0 ? "平台标" :"托管标" )+'</span>'
        +'</h4>'
        +'<div class="clearfix m-t">'
        +'<div class="col-lg-9">'
        +'<div class="col-md-3 no-padder text-center b-r">'
        +'<h3 class="m-t-sm text-orange">¥ '+loanRecord.financingAmount+'</h3>'
        +'<span class="inline m-b">融资金额</span>'
        +'</div>'
        +'<div class="col-md-3 no-padder text-center b-r">'
        +'<h3 class="m-t-sm text-orange">'+loanRecord.annualInterestRate+'%</h3>'
        +'<span class="inline m-b">年化率</span>'
        +'</div>'
        +'<div class="col-md-2 no-padder text-center">'
        +'<h3 class="m-t-sm text-orange">'+ (loanRecord.projectDeadlinePeriod == 1 ? loanRecord.projectDate +"天" : loanRecord.projectDate/30 +"个月")+'</h3>'
        +'<span class="inline m-b">融资期限</span>'
        +'</div>'
        +'<div class="col-md-4 no-padder">'
        +'<h5>[开标时间] '+ loanRecord.beginBidTime +'</h5>'
        +'<h5>[贷款方] '+loanRecord.enterpriseName+'</h5>'
        +'<h5>[还款方式] '+styleRepayType(loanRecord.repayType)+'</h5>'
        +'</div>'
        +'</div>'
        +'<div class="col-lg-3 text-center">'
        +'<div class="m-t-md">'
        +(loanRecord.loanStatus==2?('<a href="'+path+'/client/investmentProjectDetails/invest?isHx='+(loanRecord.bidType==0?"false":"true")+'&loanId='+loanRecord.id+'" class="btn btn-s-lg btn-purple">立 即 投 资</a>'):'')
        +'</div>'
        +'</div>'
        +'</div>'
        +'<div class="clearfix m-t">'
        +'<div class="col-lg-3">'
        +'已投金额（元） <span class="text-purple">'+loanRecord.raisedAmount+'</span>'
        +'</div>'
        +'<div class="col-lg-5">'
        +'<div class="progress progress-sm bg-light dker progress-striped active m-t-xs">'
        +'<div class="progress-bar bg-warning" data-toggle="tooltip"'
        +'data-original-title="'+percent+'"'
        +'style="width: '+percent+'%"></div>'
        +'</div>'
        +'</div>'
        +'<div class="col-lg-4">'
        +percent+'%'
        +'</div>'
        +'</div>'
        +'</li>';
}