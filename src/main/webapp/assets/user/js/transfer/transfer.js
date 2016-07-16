function paging(page){
    var pageSelector = "#pagination";
    var formSelector = "#searchForm";
    var projectListSelector = ".project-list";
    $.ajax({
        type: "GET",
        url:path+"/client/DebtAss/getDebtAssList",
        dataType:"json",
        data:{
            isHx:$("[name=bidType]").filter(".active").attr("isHx"),
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
                if(data[index].hasOwnProperty("lmHxBidRecord")){
                    $(projectListSelector).append(getHxHtml(data[index]));
                }else{
                    $(projectListSelector).append(getHtml(data[index]));
                }

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

function getHxHtml(lmDebtAss) {
    return '<li class="list-group-item clearfix">'
        +'<h4>'
        +'<a class="text-purple" href="'+path+'/client/DebtAss/projectDetail?isHx=true&debtAssId='+lmDebtAss.id+'">'+lmDebtAss.lmHxBidRecord.lmHxLoanRecord.loanName+"转让"+'</a>'
        +'&nbsp;&nbsp;<span class="badge bg-danger font-normal">'+"托管标" +'</span>'
        +'<small>[ 项目编号 ] '+lmDebtAss.lmHxBidRecord.lmHxLoanRecord.loanNo+'</small>'
        +'</h4>'
        +'<div class="clearfix m-t">'
        +'<div class="col-lg-9">'
        +'<div class="col-md-3 no-padder text-center b-r">'
        +'<h3 class="m-t-sm text-orange">¥ '+lmDebtAss.assAmount+'</h3>'
        +'<span class="inline m-b">转让金额</span>'
        +'</div>'
        +'<div class="col-md-3 no-padder text-center">'
        +'<h3 class="m-t-sm text-orange">¥ '+lmDebtAss.resIncome+'</h3>'
        +'<span class="inline m-b">剩余收益</span>'
        +'</div>'
        +'<div class="col-md-3 no-padder">'
        +'<h5>[年利率] '+lmDebtAss.lmHxBidRecord.lmHxLoanRecord.annualInterestRate+'%</h5>'
        +'<h5>[还款方式] '+styleRepayType(lmDebtAss.lmHxBidRecord.lmHxLoanRecord.repayType)+'</h5>'
        +'<h5>[项目投资金额] '+lmDebtAss.lmHxBidRecord.investAmount+' 元</h5>'
        +'</div>'
        +'<div class="col-md-3 no-padder">'
        +'<h5>[融资期限] '+ (lmDebtAss.lmHxBidRecord.lmHxLoanRecord.projectDeadlinePeriod == 1 ? lmDebtAss.lmHxBidRecord.lmHxLoanRecord.projectDate +"天" : lmDebtAss.lmHxBidRecord.lmHxLoanRecord.projectDate/30 +"个月")+'</h5>'
        +'<h5>[转让方] '+lmDebtAss.lmHxBidRecord.mmMemberInfo.username+'</h5>'
        +'<h5>[转让时间] '+lmDebtAss.assTime+'</h5>'
        +'</div>'
        +'</div>'
        +'<div class="col-lg-3 text-center">'
        +'<div class="m-t-md">'
        +(lmDebtAss.assStatus==1?'<a href="'+path+'/client/DebtAss/buy?isHx=true&debtAssId='+lmDebtAss.id+'" class="btn btn-s-lg btn-purple">立 即 购 买</a>':'')
        +'</div>'
        +'</div>'
        +'</div>'
        +'</li>';
}

function getHtml(lmDebtAss){
        return '<li class="list-group-item clearfix">'
            +'<h4>'
            +'<a class="text-purple" href="'+path+'/client/DebtAss/projectDetail?isHx=false&debtAssId='+lmDebtAss.id+'">'+lmDebtAss.lmBidRecord.lmLoanRecord.loanName+"转让"+'</a>'
            +'&nbsp;&nbsp;<span class="badge bg-danger font-normal">'+"平台标"+'</span>'
            +'<small>[ 项目编号 ] '+lmDebtAss.lmBidRecord.lmLoanRecord.loanNo+'</small>'
            +'</h4>'
            +'<div class="clearfix m-t">'
            +'<div class="col-lg-9">'
            +'<div class="col-md-3 no-padder text-center b-r">'
            +'<h3 class="m-t-sm text-orange">¥ '+lmDebtAss.assAmount+'</h3>'
            +'<span class="inline m-b">转让金额</span>'
            +'</div>'
            +'<div class="col-md-3 no-padder text-center">'
            +'<h3 class="m-t-sm text-orange">¥ '+lmDebtAss.resIncome+'</h3>'
            +'<span class="inline m-b">剩余收益</span>'
            +'</div>'
            +'<div class="col-md-3 no-padder">'
            +'<h5>[年利率] '+lmDebtAss.lmBidRecord.lmLoanRecord.annualInterestRate+'%</h5>'
            +'<h5>[还款方式] '+styleRepayType(lmDebtAss.lmBidRecord.lmLoanRecord.repayType)+'</h5>'
            +'<h5>[项目投资金额] '+lmDebtAss.lmBidRecord.investAmount+' 元</h5>'
            +'</div>'
            +'<div class="col-md-3 no-padder">'
            +'<h5>[融资期限] '+ (lmDebtAss.lmBidRecord.lmLoanRecord.projectDeadlinePeriod == 1 ? lmDebtAss.lmBidRecord.lmLoanRecord.projectDate +"天" : lmDebtAss.lmBidRecord.lmLoanRecord.projectDate/30 +"个月")+'</h5>'
            +'<h5>[转让方] '+lmDebtAss.lmBidRecord.mmMemberInfo.username+'</h5>'
            //+'<h5>[转让时间] '+lmDebtAss.assTime+'</h5>'
            +'</div>'
            +'</div>'
            +'<div class="col-lg-3 text-center">'
            +'<div class="m-t-md">'
            +(lmDebtAss.assStatus==1?'<a href="'+path+'/client/DebtAss/buy?isHx=false&debtAssId='+lmDebtAss.id+'" class="btn btn-s-lg btn-purple">立 即 购 买</a>':'')
            +'</div>'
            +'</div>'
            +'</div>'
            +'</li>';



}