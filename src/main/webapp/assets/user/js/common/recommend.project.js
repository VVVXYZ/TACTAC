$(function(){
    $.ajax({
        type: "GET",
        url:path+"/client/homePage/projectRecommendLis",
        dataType: 'json',
        success: function (json) {
            var data = json.dataObj;
            var length = data.length <=3 ? data.length :3;
            for (var i = 0; i <length; i++) {
                var percent=data[i].raisedAmount / data[i].financingAmount * 100;
                percent = percent.toFixed(2);
                if (data[i].bidType == 0) {
                    data[i].bidType = '平台标';
                } else {
                    data[i].bidType = '托管标';
                }
                if (data[i].repayType == 2) {
                    data[i].repayType = '付息还本';
                }
                else {
                    data[i].repayType = '到期还本息';
                }

                if (data[i].projectDeadlinePeriod ==1) {
                    data[i].projectDeadlinePeriod = '天';
                    var projectDate=data[i].projectDate;
                } else {
                    var projectDate = data[i].projectDate / 30;
                    data[i].projectDeadlinePeriod = '个月';
                }
                $("#recommend").append('<li class="list-group-item clearfix">'
                +'<h4><a class="text-purple"  name="loanName"href="'+path+'/client/investmentProjectDetails/projectDetail?isHx='+(data[i].bidType=='平台标'?'false':'true')+'&loanId='+data[i].id+'">'+data[i].loanName+'</a>&nbsp;&nbsp;<span class="badge bg-danger font-normal"name="bidType">'+data[i].bidType+'</span></h4>'
                +'<div class="clearfix m-t">'
                +'<div class="col-lg-9">'
                +'<div class="col-md-3 no-padder text-center b-r">'
                +'<h3 class="m-t-sm text-orange"name="financingAmount">'+data[i].financingAmount+'</h3>'
                +'<span class="inline m-b">融资金额</span>'
                +'</div>'
                +'<div class="col-md-3 no-padder text-center b-r">'
                +'<h3 class="m-t-sm text-orange"name="annualInterestRate">'+data[i].annualInterestRate+'%'+'</h3>'
                +'<span class="inline m-b">年化率</span>'
                +'</div>'
                +'<div class="col-md-2 no-padder text-center">'
                +'<h3 class="m-t-sm text-orange"name="raiseBidDeadline">'+projectDate+ data[i].projectDeadlinePeriod+'</h3>'
                +'<span class="inline m-b">融资期限</span>'
                +'</div>'
                +'<div class="col-md-4 no-padder">'
                +'<h5 name="beginBidTime0">[开标时间]'+data[i].beginBidTime+'</h5>'
                +'<h5 name="enterprise0">[贷款方]'+data[i].enterpriseName+'</h5>'
                +'<h5 name="repayType0">[还款方式]'+data[i].repayType+'</h5>'
                +'</div>'
                +'</div>'
                +'<div class="col-lg-3 text-center">'
                +'<div class="m-t-md">'
                +(data[i].loanStatus==2?('<a href="'+path+'/client/investmentProjectDetails/invest?isHx='+(data[i].bidType=='平台标'?'false':'true')+'&loanId='+data[i].id+'" class="btn btn-s-lg btn-purple"name="rightInvest0">立 即 投 资</a>'):'')
                +'</div>'
                +'</div>'
                +'</div>'
                +'<div class="clearfix m-t">'
                +'<div class="col-lg-3">'
                +'已投金额（元） <span class="text-purple"name="raisedAmount0">'+data[i].raisedAmount+'</span>'
                +'</div>'
                +'<div class="col-lg-5">'
                +'<div class="progress progress-sm bg-light dker progress-striped active m-t-xs">'
                +'<div class="progress-bar bg-warning" data-toggle="tooltip"'
                +'data-original-title="'+percent+'"'
                +'style="width: '+percent+'%"></div>'
                +'</div>'
                +'</div>'
                +'<div class="col-lg-4" name="percent0">'+percent + '%'
                +'</div>'
                +'</div>'
                +'</li>');
            }
        },
        error: function (XMLHttpRequest, errorThrown) {
        }
    });
})