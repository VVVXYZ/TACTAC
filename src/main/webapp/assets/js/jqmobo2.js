var curField = null;
var relationHT = new Array();
var relationQs = new Object();
var relationNotDisplayQ = new Object();
function setCookie(b, d, a, f, c, e) {
    document.cookie = b + "=" + escape(d) + ((a) ? "; expires=" + a : "") + ((f) ? "; path=" + f : "") + ((c) ? "; domain=" + c : "") + ((e) ? "; secure" : "");
}
var spChars = ["$", "}", "^", "|", "<"];
var spToChars = ["ξ", "｝", "ˆ", "¦", "&lt;"];
function replace_specialChar(c) {
    for (var a = 0; a < spChars.length; a++) {
        var b = new RegExp("(\\" + spChars[a] + ")", "g");
        c = c.replace(b, spToChars[a]);
    }
    c = c.replace(/[^\x09\x0A\x0D\x20-\uD7FF\uE000-\uFFFD\u10000-\u10FFFF]/ig, "");
    return c;
}
String.prototype.format = function () {
    var a = arguments;
    return this.replace(/\{(\d+)\}/g, function (b, c) {
        return a[c];
    });
};
var curfilediv = null;
var isUploadingFile = false;
var cur_page = 0;
var hasSkipPage = false;
var prevControl = null;
var pageHolder = null;
var curMatrixFill = null;
var curMatrixError = null;
var imgVerify = null;
var questionsObject = new Object();
function setMatrixFill() {
    if (curMatrixError && !curMatrixFill.fillvalue) {
        return;
    }
    $("#divMatrixRel").hide();
}
function setChoice(a) {
    $(a).prev("input").val(a.value);
}
function showMatrixFill(e, b) {
    if (b) {
        if (curMatrixError) {
            return;
        }
        curMatrixError = e;
    }
    curMatrixFill = e;
    if (e.holder) {
        $("#matrixinput").attr("placeholder", "请注明选择[" + e.holder + "]的原因...");
    }
    var d = e.fillvalue || "";
    $("#matrixinput").val(d);
    var a = $(e).attr("req");
    var f = $(e).offset();
    var c = f.top - $(e).height() - 15;
    $("#divMatrixRel").css("top", c + "px").css("left", "0").show();
}
function refresh_validate() {
    if (imgCode && tCode.style.display != "none" && imgCode.style.display != "none") {
        imgCode.src = "/AntiSpamImageGen.aspx?q=" + activityId + "&t=" + (new Date()).valueOf();
    }
    if (submit_text) {
        submit_text.value = "";
    }
    if (imgVerify) {
        imgVerify.onclick();
    }
}
$(function () {
    pageHolder = $("fieldset.fieldset");
    for (var s = 0; s < pageHolder.length; s++) {
        var w = $(pageHolder[s]).attr("skip") == "true";
        if (w) {
            pageHolder[s].skipPage = true;
            hasSkipPage = true;
        }
        var a = $(".field", pageHolder[s]);
        var p = 0;
        for (var t = 0; t < a.length; t++) {
            a[t].indexInPage = p;
            if (hasSkipPage) {
                a[t].pageParent = pageHolder[s];
            }
            p++;
        }
    }
    $("#divMatrixRel").bind("click", function (k) {
        k.stopPropagation();
    });
    $(document).bind("click", function () {
        setMatrixFill();
        postHeight();
    });
    $("#matrixinput").on("keyup blur focus", function () {
        if (curMatrixFill) {
            var k = $("#matrixinput").val();
            curMatrixFill.fillvalue = k;
        }
    });
    var b = false;
    var v = new Array();
    $(".field").each(function () {
        var A = $(this);
        A.bind("click", function () {
            if (window.scrollup) {
                scrollup.Stop();
            }
        });
        var I = A.attr("type");
        var H = getTopic(A);
        questionsObject[H] = A;
        var q = A.attr("relation");
        if (q && q != "0") {
            var k = q.split(",");
            var C = k[0];
            var K = k[1].split(";");
            for (var E = 0; E < K.length; E++) {
                var J = C + "," + K[E];
                if (!relationHT[J]) {
                    relationHT[J] = new Array();
                }
                relationHT[J].push(this);
            }
            if (!relationQs[C]) {
                relationQs[C] = new Array();
            }
            relationQs[C].push(this);
            relationNotDisplayQ[H] = "1";
        } else {
            if (q == "0") {
                relationNotDisplayQ[H] = "1";
            }
        }
        if (A.attr("hrq") == "1") {
            return true;
        }
        if (I == "1") {
            var G = $("input", A);
            G.on("keyup blur click", function () {
                verifyTxt(A, G);
                window.hasAnswer = true;
                jump(A, this);
            });
            G.focus(function () {
                $(this.parentNode).addClass("ui-focus");
            });
            G.blur(function () {
                $(this.parentNode).removeClass("ui-focus");
                checkOnly(A, G);
            });
            var D = $("textarea", A);
            if (D[0]) {
                var B = D.prev("a")[0];
                B.par = A[0];
                D[0].par = A[0];
                A[0].needsms = true;
                var F = D.parents("div").find(".phonemsg")[0];
                A[0].mobileinput = G[0];
                A[0].verifycodeinput = D[0];
                B.onclick = function () {
                    if (this.disabled) {
                        return;
                    }
                    var P = this.par;
                    if (P.issmsvalid && P.mobile == P.mobileinput.value) {
                        return;
                    }
                    if (this.isSending) {
                        return;
                    }
                    this.isSending = true;
                    this.disabled = true;
                    var O = "/Handler/AnswerSmsHandler.ashx?q=" + activityId + "&mob=" + escape(P.mobileinput.value) + "&t=" + (new Date()).valueOf();
                    $.ajax({
                        type: "GET", url: O, async: false, success: function (R) {
                            var S = "";
                            if (R == "true") {
                                S = "成功发送验证码，每天最多发送5次！";
                            } else {
                                if (R == "fast") {
                                    S = "发送频率过快";
                                } else {
                                    if (R == "many") {
                                        S = "发送验证码次数过多";
                                    } else {
                                        if (R == "no") {
                                            S = "发布者短信数量不够";
                                        } else {
                                            if (R == "fail") {
                                                S = "短信发送失败，每天最多发送5次！";
                                            } else {
                                                if (R == "error") {
                                                    S = "手机号码不正确";
                                                } else {
                                                    if (R == "nopub") {
                                                        S = "问卷还未发布";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            F.innerHTML = S;
                            this.isSending = false;
                        }
                    });
                    var N = this;
                    var M = 60;
                    var Q = setInterval(function () {
                        M--;
                        if (M < 57) {
                            N.isSending = false;
                        }
                        if (M > 0) {
                            N.innerHTML = "重新发送(" + M + ")";
                        } else {
                            N.innerHTML = "发送验证短信";
                            N.disabled = false;
                            clearInterval(Q);
                        }
                    }, 1000);
                };
                D[0].onchange = D[0].onblur = function () {
                    var O = this.value;
                    if (O.length != 6) {
                        return;
                    }
                    if (!/^(\-)?\d+$/.exec(O)) {
                        return;
                    }
                    var N = this.par;
                    if (N.issmsvalid && N.mobile == N.mobileinput.value) {
                        return;
                    }
                    if (N.prevcode == O) {
                        return;
                    }
                    N.prevcode = O;
                    var M = "/Handler/AnswerSmsValidateHandler.ashx?q=" + activityId + "&mob=" + escape(N.mobileinput.value) + "&code=" + escape(O) + "&t=" + (new Date()).valueOf();
                    $.ajax({
                        type: "GET", url: M, async: false, success: function (P) {
                            N.issmsvalid = false;
                            var Q = "";
                            if (P == "true") {
                                N.issmsvalid = true;
                                N.mobile = N.mobileinput.value;
                                Q = "成功通过验证";
                                writeError(N, "", 1000);
                            } else {
                                if (P == "send") {
                                    Q = "请先发送验证码，每天最多发送5次！";
                                } else {
                                    if (P == "no") {
                                        Q = "验证码输入错误超过5次，无法再提交";
                                    } else {
                                        if (P == "error") {
                                            Q = "验证码输入错误";
                                        }
                                    }
                                }
                            }
                            F.innerHTML = Q;
                        }
                    });
                };
            }
        } else {
            if (I == "2") {
                var G = $("textarea", A);
                G.on("keyup blur click", function () {
                    verifyTxt(A, G);
                    window.hasAnswer = true;
                    jump(A, this);
                });
                G.blur(function () {
                    checkOnly(A, G);
                });
            } else {
                if (I == "9") {
                    $("input", A).on("keyup blur", function () {
                        var M = $(this);
                        msg = verifyTxt(A, $(this), true);
                        jump(A, this);
                    });
                } else {
                    if (I == "8") {
                        $("input", A).change(function () {
                            jump(A, this);
                        });
                    } else {
                        if (I == "12") {
                            $("input", A).change(function () {
                                var N = null;
                                var Q = $(A).attr("total");
                                var R = $("input:visible", A);
                                var O = count = R.length;
                                var S = Q;
                                R.each(function (T) {
                                    if (T == O - 1) {
                                        N = this;
                                    }
                                    if ($(this).val()) {
                                        count--;
                                        S = S - $(this).val();
                                    }
                                });
                                if (count == 1 && N && S > 0) {
                                    $(N).val(S).change();
                                    S = 0;
                                }
                                msg = "";
                                if (S != 0 && count == 0) {
                                    var P = parseInt($(N).val()) + S;
                                    if (P >= 0) {
                                        if (N != this) {
                                            $(N).val(P).change();
                                            S = 0;
                                        } else {
                                            if (R.length == 2) {
                                                var M = Q - $(N).val();
                                                $(R[0]).val(M).change();
                                                S = 0;
                                            }
                                        }
                                    } else {
                                        msg = "，<span style='color:red;'>" + sum_warn + "</span>";
                                    }
                                }
                                if (S == 0) {
                                    R.each(function (T) {
                                        if (!$(this).val()) {
                                            $(this).val("0").change();
                                        }
                                    });
                                }
                                $(".relsum", A).html(sum_total + "<b>" + Q + "</b>" + sum_left + "<span style='color:red;font-bold:true;'>" + (Q - S) + "</span>" + msg);
                                jump(A, this);
                            });
                        } else {
                            if (I == "13") {
                                b = true;
                            } else {
                                if (I == "3") {
                                    $("div.ui-radio", A).bind("click", function (O) {
                                        var M = $(this);
                                        var N = M.find("input[type='radio']")[0];
                                        if (N.disabled) {
                                            return;
                                        }
                                        window.hasAnswer = true;
                                        $(A).find("div.ui-radio").each(function () {
                                            var P = $(this);
                                            P.find("input[type='radio']")[0].checked = false;
                                            P.find("a.jqradio").removeClass("jqchecked");
                                            P.removeClass("focuschoice");
                                        });
                                        N.checked = true;
                                        M.find("a.jqradio").addClass("jqchecked");
                                        M.addClass("focuschoice");
                                        displayRelationByType(A, "input[type=radio]", 1);
                                        jump(A, N);
                                        if (M.attr("desc") != 1) {
                                            O.preventDefault();
                                        }
                                    });
                                    var L = A.attr("qingjing");
                                    if (L) {
                                        v.push(A);
                                    }
                                    $("input.OtherRadioText").bind("click", function (Q) {
                                        $(this).parents("div.ui-controlgroup").find("div.ui-radio").each(function () {
                                            $(this).find("input[type='radio']")[0].checked = false;
                                            $(this).find("a.jqradio").removeClass("jqchecked");
                                        });
                                        var M = $(this).attr("rel");
                                        var O = $("#" + M)[0];
                                        O.checked = true;
                                        var N = $("#" + M).parents(".ui-radio");
                                        N.find("a.jqradio").addClass("jqchecked");
                                        N.addClass("focuschoice");
                                        var P = $(this).parents("div.field");
                                        displayRelationByType(P, "input[type=radio]", 1);
                                        jump(P, O);
                                        Q.stopPropagation();
                                        Q.preventDefault();
                                    });
                                } else {
                                    if (I == "7") {
                                        $("select", A).bind("change", function (M) {
                                            $(this).parents(".ui-select").find("span").html(this.options[this.selectedIndex].text);
                                            displayRelationByType(A, "option", 5);
                                            jump(A, this.options[this.selectedIndex]);
                                            M.preventDefault();
                                        });
                                    } else {
                                        if (I == "10") {
                                            var z = A.attr("select") == "1";
                                            if (z) {
                                                $("select", A).bind("change", function () {
                                                    $(this).parents(".ui-select").find("span").html(this.options[this.selectedIndex].text);
                                                    jump(A, this);
                                                });
                                            }
                                            $("input", A).bind("change blur", function () {
                                                var Q = $(this);
                                                var P = Q.val();
                                                var O = Q.attr("isdigit") == "1";
                                                if (O) {
                                                    if (parseFloat(P) != P) {
                                                        Q.val("");
                                                    } else {
                                                        var N = Q.attr("min");
                                                        if (N && P - N < 0) {
                                                            Q.val("");
                                                        }
                                                        var M = Q.attr("max");
                                                        if (M && P - M > 0) {
                                                            Q.val("");
                                                        }
                                                    }
                                                } else {
                                                    msg = verifyTxt(A, $(this), true);
                                                }
                                                jump(A, this);
                                            });
                                        } else {
                                            if (I == "5") {
                                                initRate(A);
                                                $("a.rate-off", A).bind("click", function () {
                                                    displayRelationByType(A, "a.rate-off", 4);
                                                    jump(A, this);
                                                });
                                            } else {
                                                if (I == "6") {
                                                    initRate(A);
                                                    $("a.rate-off", A).bind("click", function () {
                                                        jump(A, this);
                                                    });
                                                } else {
                                                    if (I == "4") {
                                                        $("div.ui-checkbox", A).bind("click", function (N) {
                                                            var O = $(this);
                                                            var M = O.find("input[type='checkbox']")[0];
                                                            if (M.disabled) {
                                                                return;
                                                            }
                                                            M.checked = !M.checked;
                                                            window.hasAnswer = true;
                                                            if (M.checked) {
                                                                O.find("a.jqcheck").addClass("jqchecked");
                                                                O.addClass("focuschoice");
                                                            } else {
                                                                O.find("a.jqcheck").removeClass("jqchecked");
                                                                O.removeClass("focuschoice");
                                                            }
                                                            checkHuChi(A, this);
                                                            displayRelationByType(A, "input[type='checkbox']", 2);
                                                            verifyCheckMinMax(A, false, false, this);
                                                            jump(A, M);
                                                            if (window.createItem) {
                                                                createItem(A);
                                                            }
                                                            N.preventDefault();
                                                        });
                                                        $("input.OtherText", A).bind("click", function (Q) {
                                                            var M = $(this).attr("rel");
                                                            var N = $("#" + M)[0];
                                                            N.checked = true;
                                                            var O = $("#" + M).parents(".ui-checkbox");
                                                            O.find("a.jqcheck").addClass("jqchecked");
                                                            O.addClass("focuschoice");
                                                            var P = $(this).parents("div.field");
                                                            displayRelationByType(P, "input[type=checkbox]", 2);
                                                            jump(P, N);
                                                            verifyCheckMinMax(P, false);
                                                            if (window.createItem) {
                                                                createItem(P);
                                                            }
                                                            Q.stopPropagation();
                                                            Q.preventDefault();
                                                        });
                                                    } else {
                                                        if (I == "21") {
                                                            $(".shop-item", A).each(function () {
                                                                var M = $(".itemnum", this);
                                                                var N = $(".item_left", this);
                                                                $(".add", this).bind("click", function (P) {
                                                                    var S = false;
                                                                    var O = 0;
                                                                    if (N[0]) {
                                                                        S = true;
                                                                        O = parseInt(N.attr("num"));
                                                                    }
                                                                    var R = parseInt(M.val());
                                                                    if (S && R >= O) {
                                                                        var Q = "库存只剩" + O + "件，不能再增加！";
                                                                        if (O <= 0) {
                                                                            Q = "已售完，无法添加";
                                                                        }
                                                                        alert(Q);
                                                                    } else {
                                                                        M.val(R + 1);
                                                                        updateCart();
                                                                    }
                                                                    P.preventDefault();
                                                                });
                                                                M.bind("focus", function (O) {
                                                                    if (M.val() == "0") {
                                                                        M.val("");
                                                                    }
                                                                });
                                                                M.bind("blur change", function (Q) {
                                                                    if (!M.val()) {
                                                                        M.val("0");
                                                                    }
                                                                    var T = parseInt(M.val());
                                                                    if (!T || T < 0) {
                                                                        M.val("0");
                                                                        updateCart();
                                                                        return;
                                                                    }
                                                                    var S = false;
                                                                    var P = 0;
                                                                    if (N[0]) {
                                                                        S = true;
                                                                        P = parseInt(N.attr("num"));
                                                                    }
                                                                    if (S) {
                                                                        if (T > P) {
                                                                            var R = "库存只剩" + P + "件，不能超过库存！";
                                                                            if (P <= 0) {
                                                                                R = "已售完，无法添加";
                                                                            }
                                                                            alert(R);
                                                                            var O = P;
                                                                            if (O < 0) {
                                                                                O = 0;
                                                                            }
                                                                            M.val(O);
                                                                        }
                                                                    }
                                                                    updateCart();
                                                                    Q.preventDefault();
                                                                });
                                                                $(".remove", this).bind("click", function (O) {
                                                                    var P = parseInt(M.val());
                                                                    if (P > 0) {
                                                                        M.val(P - 1);
                                                                        updateCart();
                                                                    }
                                                                    O.preventDefault();
                                                                });
                                                            });
                                                        } else {
                                                            if (I == "11") {
                                                                $("li.ui-li-static", A).bind("click", function (N) {
                                                                    if (!$(this).attr("check")) {
                                                                        var M = $(this).parents("ul.ui-listview").find("li[check='1']").length + 1;
                                                                        $(this).find("span.sortnum").html(M).addClass("sortnum-sel");
                                                                        $(this).attr("check", "1");
                                                                    } else {
                                                                        var M = $(this).find("span").html();
                                                                        $(this).parents("ul.ui-listview").find("li[check='1']").each(function () {
                                                                            var O = $(this).find("span.sortnum").html();
                                                                            if (O - M > 0) {
                                                                                $(this).find("span.sortnum").html(O - 1);
                                                                            }
                                                                        });
                                                                        $(this).find("span.sortnum").html("").removeClass("sortnum-sel");
                                                                        $(this).attr("check", "");
                                                                    }
                                                                    displayRelationByType(A, "li.ui-li-static", 3);
                                                                    verifyCheckMinMax(A, false, true, this);
                                                                    jump(A, this);
                                                                    if (window.createItem) {
                                                                        createItem(A, true);
                                                                    }
                                                                    N.preventDefault();
                                                                });
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    });
    for (var g = 0; g < v.length; g++) {
        var m = v[g];
        displayRelationByType(m, "input[type=radio]", 1);
    }
    $("#ctlNext") != null && $("#ctlNext").on("click", function () {
        if (this.disabled) {
            return;
        }
        if (window.divTip) {
            alert(divTip.innerHTML);
            return;
        }
        $("#action").val("1");
        var k = validate();
        if (!k) {
            return;
        }
        if (window.isDianChu) {
            if (!is_dianchecked) {
                $(".ValError").html("请按提示点击验证码");
                tou_submit.call(this);
                return;
            }
        } else {
            if (tCode && tCode.style.display != "none" && (submit_text.value == "" || submit_text.value == validate_info_submit_title3)) {
                try {
                    submit_text.focus();
                    submit_text.click();
                } catch (q) {
                }
                alert(validate_info_submit1);
                return;
            }
        }
        groupAnswer(1);
    });
    setVerifyCode();
    initSlider();
    if (totalPage > 1) {
        $("#divSubmit").hide();
        $("#divNext")[0].style.display = "";
        showProgress();
    } else {
        $("#divSubmit").show();
    }
    if (window.totalCut && window.totalCut > 0) {
        for (var t = 0; t < window.totalCut; t++) {
            var f = "divCut" + (t + 1);
            var x = $("#" + f);
            var h = x.attr("relation");
            if (h && h != "0") {
                var d = h.split(",");
                var y = d[0];
                var n = d[1].split(";");
                for (var l = 0; l < n.length; l++) {
                    var e = y + "," + n[l];
                    if (!relationHT[e]) {
                        relationHT[e] = new Array();
                    }
                    relationHT[e].push(x[0]);
                }
                if (!relationQs[y]) {
                    relationQs[y] = new Array();
                }
                relationQs[y].push(x[0]);
            }
        }
    }
    if (window.hasPageTime) {
        processMinMax();
    }
    fixBottom();
    $(window).load(function () {
        fixBottom();
    });
    if (window.cepingCandidate) {
        var o = cepingCandidate.split(",");
        var r = new Object();
        for (var u = 0; u < o.length; u++) {
            var c = o[u].replace(/(\s*)/g, "").replace(/&/g, "");
            r[c] = "1";
        }
        var j = $("#div1");
        $("input[type=checkbox]", j).each(function () {
            var z = $(this).parents(".ui-checkbox");
            var q = z.find("label")[0];
            if (!q) {
                return true;
            }
            var k = q.innerHTML;
            k = k.replace(/(\s*)/g, "").replace(/&amp;/g, "");
            if (r[k]) {
                z.trigger("click");
            }
        });
        j[0].style.display = "none";
        j[0].isCepingQ = "1";
    }
    processAward();
});
function hideAward() {
    if (!confirm("确认不再领取吗？")) {
        return;
    }
    if (window.localStorage) {
        vkey = "award_" + activityId;
        localStorage.removeItem(vkey);
        localStorage.removeItem(vkey + "name");
        localStorage.removeItem(vkey + "tip");
    }
    $("#divContent").show().prev().hide();
    initSlider();
}
function processAward() {
    var b = "join_" + activityId;
    if (!document.cookie || document.cookie.indexOf(b + "=") == -1) {
        return;
    }
    b = "award_" + activityId;
    var d = "";
    var a = "";
    if (window.localStorage) {
        d = localStorage[b];
        a = localStorage[b + "name"];
    }
    if (d && d.indexOf("http") == 0) {
        var c = localStorage[b + "tip"];
        var f = "";
        if (c) {
            f = " onclick='alert(\"" + c + "\");return true;' ";
        }
        var e = "<div style='margin:10px 12px;'>恭喜您抽中了" + a + "，如已领取请忽略！<br/><div style='text-align:center;'><a href='" + d + "'" + f + " class='button white' target='_blank' style='color:#fff; background:#e87814;'>立即领取</a></div><div style='margin-top:18px;text-align:center;'><a href='javascript:' onclick='hideAward();' style='color:#666;font-size:14px;'>不领取，重新填写问卷</a></div></div>";
        $("#divContent").before(e);
        $("#divContent").hide();
    }
}
function postHeight() {
    if (window == window.top) {
        return;
    }
    try {
        var c = parent.postMessage ? parent : parent.document.postMessage ? parent.document : null;
        if (c != null) {
            var a = $("body").height();
            return c.postMessage("heightChanged," + a, "*");
        }
    } catch (b) {
    }
}
function initRate(a) {
    $("a.rate-off", a).bind("click", function (k) {
        var n = $(this).parents("div.field");
        var b = $(n).attr("ischeck");
        if (b) {
            var h = true;
            var m = $(a).attr("maxvalue");
            if (m && !$(this).hasClass("rate-on")) {
                var l = $("a.rate-on", $(this).parents("tr"));
                if (m - l.length <= 0) {
                    h = false;
                }
            }
            if (h) {
                $(this).toggleClass("rate-on");
                $(this).trigger("change");
            }
        } else {
            $(this).parents("tr").find("a.rate-off").removeClass("rate-on");
            $(this).toggleClass("rate-on");
            $(this).trigger("change");
        }
        if ($(this).hasClass("rate-on")) {
            var j = $(this).attr("needfill");
            if (j) {
                if (!this.holder) {
                    var g = $(".matrix-rating tr", n)[0];
                    var c = $("th", $(g));
                    var f = $(this).attr("dval");
                    if (f && c[f - 1]) {
                        this.holder = $(c[f - 1]).html();
                    }
                }
                showMatrixFill(this);
                k.stopPropagation();
            }
        }
        $("span.error", $(n)).is(":visible") && validateQ(n);
        k.preventDefault();
    });
}
function updateCart() {
    var d = $("#divQuestion");
    var b = "";
    var c = 0;
    var a = 0;
    $(".shop-item", d).each(function () {
        var f = $(".itemnum", this);
        var j = parseInt(f.val());
        if (j == 0) {
            return true;
        }
        var k = $(".item_name", this).html();
        var g = $(".item_price", this).attr("price");
        var h = j * parseFloat(g).toFixed(2);
        var e = '<li class="productitem"><span class="fpname">' + k + '</span><span class="fpnum">' + j + '</span><span class="fpprice">￥' + h + "</span></li>";
        b += e;
        c += h;
        a += j;
    });
    b = "<ul class='productslist'>" + b + '<li class="productitem"><span class="fpname"></span><span class="fpnum" style="font-weight:bold;">' + a + '</span><span class="fpprice" style="font-weight:bold;">￥' + c.toFixed(2) + "</span></li></ul>";
    $("#shopcart").html(b);
    if (c > 0) {
        $("#shopcart").show();
    } else {
        $("#shopcart").hide();
    }
}
function setVerifyCode() {
    if (tCode && tCode.style.display != "none") {
        submit_text.value = validate_info_submit_title3;
        submit_text.onblur = function () {
            if (submit_text.value == "") {
                submit_text.value = validate_info_submit_title3;
            }
        };
        submit_text.onfocus = function () {
            if (submit_text.value == validate_info_submit_title3) {
                submit_text.value = "";
            }
        };
        imgCode.style.display = "none";
        if (window.isDianChu) {
            tCode.style.display = "none";
            return;
        }
        submit_text.onclick = function () {
            if (!needAvoidCrack && imgCode.style.display == "none") {
                imgCode.style.display = "";
                imgCode.onclick = refresh_validate;
                imgCode.onclick();
                imgCode.title = validate_info_submit_title1;
            } else {
                if (needAvoidCrack && !imgVerify) {
                    var c = $("#divCaptcha")[0];
                    c.style.display = "";
                    imgVerify = c.getElementsByTagName("img")[0];
                    imgVerify.style.cursor = "pointer";
                    imgVerify.onclick = function () {
                        var h = new Date();
                        var e = h.getTime() + (h.getTimezoneOffset() * 60000);
                        var f = window.location.host || "www.sojump.com";
                        var g = "http://" + f + "/BotDetectCaptcha.ashx?activity=" + activityId + "&get=image&c=" + this.captchaId + "&t=" + this.instanceId + "&d=" + e;
                        this.src = g;
                    };
                    var a = imgVerify.getAttribute("captchaid");
                    var b = imgVerify.getAttribute("instanceid");
                    imgVerify.captchaId = a;
                    imgVerify.instanceId = b;
                    imgVerify.onclick();
                }
            }
        };
    }
}
function fixBottom() {
    postHeight();
    var a = $("body").height() - $(window).height();
    if (a < 0) {
        $(".logofooter").addClass("fixedbottom");
    } else {
        $(".logofooter").removeClass("fixedbottom");
    }
}
var firstError = null;
var firstMatrixError = null;
function validate() {
    var b = true;
    firstError = null;
    firstMatrixError = null;
    curMatrixError = null;
    $(".field:visible").each(function () {
        var e = pageHolder[cur_page].hasExceedTime;
        if (e) {
            return true;
        }
        var d = $(this), a = validateQ(d);
        if (!a) {
            b = false;
        }
    });
    if (!b) {
        if (firstError) {
            $("html, body").animate({scrollTop: $(firstError).offset().top}, 600);
            $(".scrolltop").show();
            $(".scrolltop").click(function () {
                $("html, body").animate({scrollTop: $(document).height()}, 600);
                $(".scrolltop").hide();
            });
        }
    } else {
    }
    return b;
}
var txtCurCity = null;
function openCityBox(f, e, c, g) {
    txtCurCity = f;
    var d = "";
    g = g || "";
    if (e == 3) {
        var a = f.getAttribute("province");
        var b = "";
        if (a) {
            b = "&pv=" + encodeURIComponent(a);
        }
        d = "/wjx/design/setcitycountymobo2.aspx?activityid=" + activityId + "&ct=" + e + b + "&pos=" + g;
    } else {
        if (e == 5) {
            d = "/wjx/design/setmenusel.aspx?activityid=" + activityId + "&ct=" + e + "&pos=" + g;
        } else {
            d = "/wjx/design/setcitymobo2.aspx?activityid=" + activityId + "&ct=" + e + "&pos=" + g;
        }
    }
    openDialogByIframe(400, 400, d);
}
function setCityBox(a) {
    txtCurCity.value = a;
    $("#yz_popTanChuClose").click();
}
var startAge = 0;
var endAge = 0;
var rName = "";
function getRname(c, b) {
    if (rName) {
        return;
    }
    if (c != "1") {
        return;
    }
    var d = $("div.field-label", b).html();
    if (d.indexOf("姓名") == -1) {
        return;
    }
    rName = $("input:text", b).val();
}
function getAge(c, b) {
    if (c != "3" && c != "7") {
        return;
    }
    var h = $("div.field-label", b).html();
    if (h.indexOf("年龄") == -1) {
        return;
    }
    var e = "";
    var g = 0;
    if (c == 3) {
        $("input[type='radio']", b).each(function (a) {
            if (this.checked) {
                e = $(this).parents("div.ui-radio").find("label").html();
                g = a;
                return false;
            }
        });
    } else {
        if (c == 7) {
            var d = $("select", b)[0];
            e = d.options[d.selectedIndex].text;
            g = d.selectedIndex - 1;
        }
    }
    if (!e) {
        return;
    }
    var f = /[1-9][0-9]*/g;
    var j = e.match(f);
    if (!j || j.length == 0) {
        return;
    }
    if (j.length > 2) {
        return;
    }
    if (j.length == 2) {
        startAge = j[0];
        endAge = j[1];
    } else {
        if (j.length == 1) {
            if (g == 0) {
                endAge = j[0];
            } else {
                startAge = j[0];
            }
        }
    }
}
function groupAnswer(l) {
    var f = new Array();
    var k = 0;
    $(".field").each(function () {
        var s = $(this);
        var A = new Object();
        var x = s.attr("type");
        var t = this.style.display != "none";
        if (t && hasSkipPage) {
            if (this.pageParent && this.pageParent.skipPage) {
                t = false;
            }
        }
        if (this.isCepingQ) {
            t = true;
        }
        A._value = "";
        A._topic = getTopic(s);
        f[k++] = A;
        try {
            getAge(x, s);
            getRname(x, s);
        } catch (v) {
        }
        var y = 0;
        switch (x) {
            case"1":
                if (!t) {
                    A._value = "(跳过)";
                    if (s.attr("hrq") == "1") {
                        A._value = "Ⅳ";
                    }
                    break;
                }
                A._value = replace_specialChar($("input", s).val());
                break;
            case"2":
                if (!t) {
                    A._value = "(跳过)";
                    if (s.attr("hrq") == "1") {
                        A._value = "Ⅳ";
                    }
                    break;
                }
                A._value = replace_specialChar($("textarea", s).val());
                break;
            case"3":
                if (!t) {
                    A._value = "-3";
                    if (s.attr("hrq") == "1") {
                        A._value = "-4";
                    }
                    break;
                }
                $("input[type='radio']:checked", s).each(function (C) {
                    A._value = $(this).val();
                    var B = $(this).attr("rel");
                    if (B && $("#" + B).val().length > 0) {
                        A._value += spChars[2] + replace_specialChar($("#" + B).val().substring(0, 3000));
                    }
                    return false;
                });
                break;
            case"4":
                if (!t) {
                    A._value = "-3";
                    if (s.attr("hrq") == "1") {
                        A._value = "-4";
                    }
                    break;
                }
                var w = 0;
                $("input:checked", s).each(function () {
                    var C = $(this).parents(".ui-checkbox")[0].style.display == "none";
                    if (!C) {
                        if (w > 0) {
                            A._value += spChars[3];
                        }
                        A._value += $(this).val();
                        var B = $(this).attr("rel");
                        if (B && $("#" + B).val().length > 0) {
                            A._value += spChars[2] + replace_specialChar($("#" + B).val().substring(0, 3000));
                        }
                        w++;
                    }
                });
                if (w == 0) {
                    A._value = "-2";
                }
                break;
            case"21":
                if (!t) {
                    A._value = "-3";
                    break;
                }
                var w = 0;
                $(".shop-item .itemnum", s).each(function (B) {
                    var C = $(this).val();
                    if (C != "0") {
                        if (w > 0) {
                            A._value += spChars[3];
                        }
                        A._value += (B + 1);
                        A._value += spChars[2] + C;
                        w++;
                    }
                });
                if (w == 0) {
                    A._value = "-2";
                }
                break;
            case"11":
                var e = new Array();
                $("li.ui-li-static", s).each(function () {
                    var B = $(this).find("span.sortnum").html();
                    var C = new Object();
                    C.sIndex = B;
                    var D = $(this).find("input:hidden").val();
                    if (!t) {
                        D = "-3";
                    } else {
                        if (!B) {
                            D = "-2";
                        }
                    }
                    C.val = D;
                    if (!C.sIndex) {
                        C.sIndex = 10000;
                    }
                    e.push(C);
                });
                e.sort(function (C, B) {
                    return C.sIndex - B.sIndex;
                });
                for (var r = 0; r < e.length; r++) {
                    if (r > 0) {
                        A._value += ",";
                    }
                    A._value += e[r].val;
                }
                break;
            case"5":
                if (!t) {
                    A._value = "-3";
                    break;
                }
                A._value = $("input:hidden", s).val();
                break;
            case"6":
                y = 0;
                $("input:hidden", s).each(function (B) {
                    if (y > 0) {
                        A._value += ",";
                    }
                    var C = false;
                    if (window.hasReferClient) {
                        var D = $("tr[rv=" + (B + 1) + "]", s)[0];
                        if (D && D.style.display == "none") {
                            C = true;
                        }
                    }
                    if (!t) {
                        A._value += "-3";
                    } else {
                        var E = $(this).val();
                        if (!E) {
                            E = "-2";
                        }
                        if (C) {
                            E = "-4";
                        }
                        A._value += E;
                    }
                    y++;
                });
                break;
            case"7":
                if (!t) {
                    A._value = "-3";
                    break;
                }
                A._value = $("select", s).val();
                break;
            case"8":
                if (!t) {
                    A._value = "(跳过)";
                    break;
                }
                A._value = $("input.ui-slider-input", s).val();
                break;
            case"9":
                y = 0;
                if (!t && s.attr("hrq") == "1") {
                    A._value = "Ⅳ";
                    break;
                }
                var z = $("input", s);
                if (s.attr("randomrow") == "1") {
                    var u = s.attr("topic");
                    z = z.toArray().sort(function (D, C) {
                        var B = $(D).attr("id").replace("q" + u + "_", "");
                        var E = $(C).attr("id").replace("q" + u + "_", "");
                        return B - E;
                    });
                }
                $(z).each(function () {
                    if (y > 0) {
                        A._value += spChars[2];
                    }
                    var D = $(this).val();
                    var B = false;
                    if (window.hasReferClient) {
                        var C = $(this).parents("tr")[0];
                        if (C && C.style.display == "none") {
                            B = true;
                        }
                    }
                    if (!t) {
                        D = "(跳过)";
                    } else {
                        if (B) {
                            D = "Ⅳ";
                        }
                    }
                    A._value += replace_specialChar(D);
                    y++;
                });
                break;
            case"12":
                y = 0;
                $("input", s).each(function () {
                    if (y > 0) {
                        A._value += spChars[2];
                    }
                    var B = false;
                    if (window.hasReferClient) {
                        var D = $(this).parents("tr")[0];
                        if (D && D.style.display == "none") {
                            B = true;
                        }
                    }
                    var C = $(this).val();
                    if (!t) {
                        C = "(跳过)";
                    } else {
                        if (B) {
                            C = "Ⅳ";
                        }
                    }
                    A._value += C;
                    y++;
                });
                break;
            case"13":
                if (!t) {
                    A._value = "(跳过)";
                    break;
                }
                A._value = this.fileName || "";
                break;
            case"10":
                y = 0;
                $("table", s).each(function () {
                    if (y > 0) {
                        A._value += spChars[2];
                    }
                    var B = 0;
                    var E = "input";
                    var D = "(跳过)";
                    if (s.attr("select") == "1") {
                        E = "select";
                        D = "-3";
                    }
                    var C = false;
                    if (window.hasReferClient) {
                        var F = $(this).parents(".mdivtable")[0];
                        if (F && F.style.display == "none") {
                            C = true;
                        }
                    }
                    $(E, this).each(function () {
                        if (B > 0) {
                            A._value += spChars[3];
                        }
                        var G = $(this).val();
                        if (!t) {
                            G = D;
                        } else {
                            if (C) {
                                G = "Ⅳ";
                            }
                        }
                        A._value += replace_specialChar(G);
                        B++;
                    });
                    y++;
                });
                break;
        }
    });
    if (f.length == 0) {
        //alert("提示：此问卷没有添加题目，不能提交！");
        return;
    }
    f.sort(function (r, e) {
        return r._topic - e._topic;
    });
    var p = "";
    for (i = 0; i < f.length; i++) {
        if (i > 0) {
            p += spChars[1];
        }
        p += f[i]._topic;
        p += spChars[0];
        p += f[i]._value;
    }
    var j = $("#form1").attr("action");
    if (j.indexOf("aliyun.sojump.com") > -1 || j.indexOf("temp.sojump.com") > -1) {
        j = j.replace("aliyun.sojump.com", window.location.host).replace("temp.sojump.com", window.location.host);
    }
    var g = j + "&starttime=" + encodeURIComponent($("#starttime").val());
    if (window.sojumpParm) {
        g += "&sojumpparm=" + encodeURIComponent(window.sojumpParm);
    }
    if (window.tparam) {
        g += "&tparam=1&sojumpparmext=" + encodeURIComponent(window.sojumpparmext);
    }
    if (window.Password) {
        g += "&psd=" + encodeURIComponent(Password);
    }
    if (window.hasMaxtime) {
        g += "&hmt=1";
    }
    if (tCode && tCode.style.display != "none" && submit_text.value != "") {
        g += "&validate_text=" + encodeURIComponent(submit_text.value);
    }
    if (window.isDianChu) {
        g += "&check_key=" + encodeURIComponent(check_key) + "&check_address=" + encodeURIComponent(check_address) + "&validate_text=dian";
    }
    if (window.cpid) {
        g += "&cpid=" + cpid;
    }
    if (window.guid) {
        g += "&emailguid=" + guid;
    }
    if (window.udsid) {
        g += "&udsid=" + window.udsid;
    }
    if (nvvv) {
        g += "&nvvv=1";
    }
    if (window.sjUser) {
        g += "&sjUser=" + encodeURIComponent(sjUser);
    }
    if (window.sourceurl) {
        g += "&source=" + encodeURIComponent(sourceurl);
    } else {
        g += "&source=directphone";
    }
    var n = window.alipayAccount || window.cAlipayAccount;
    if (n) {
        g += "&alac=" + encodeURIComponent(n);
    }
    if (window.SJBack) {
        g += "&sjback=1";
    }
    if (window.jiFen && jiFen > 0) {
        g += "&jf=" + jiFen;
    }
    if (l) {
        g += "&submittype=" + l;
    }
    if (l == 3) {
        g += "&zbp=" + (cur_page + 1);
    }
    if (window.rndnum) {
        g += "&rn=" + encodeURIComponent(rndnum);
    }
    if (imgVerify) {
        g += "&btuserinput=" + encodeURIComponent(submit_text.value);
        g += "&btcaptchaId=" + encodeURIComponent(imgVerify.captchaId);
        g += "&btinstanceId=" + encodeURIComponent(imgVerify.instanceId);
    }
    if (window.inviteid) {
        g += "&inviteid=" + encodeURIComponent(inviteid);
    }
    if (window.access_token) {
        g += "&access_token=" + encodeURIComponent(access_token) + "&openid=" + encodeURIComponent(openid);
    }
    if (window.wxthird) {
        g += "&wxthird=1";
    }
    if (window.isWeiXin) {
        g += "&iwx=1";
    }
    g += "&t=" + new Date().valueOf();
    if (window.cProvince) {
        g += "&cp=" + encodeURIComponent(cProvince.replace("'", "")) + "&cc=" + encodeURIComponent(cCity.replace("'", "")) + "&ci=" + escape(cIp);
        var c = cProvince + "," + cCity;
        var o = window.location.host || "sojump.com";
        try {
            setCookie("ip_" + cIp, c, null, "/", "", null);
        } catch (d) {
        }
    }
    $("#ctlNext").hide();
    var q = "处理中......";
    if (langVer == 1) {
        q = "Submiting......";
    }

    $(".ValError").html(q);
    if (l == 3) {
        $(".ValError").html("正在验证，请稍候...");
    }
    var m = {submitdata: p};
    var h = false;
    var b = window.getMaxWidth || 1800;
    var a = encodeURIComponent(p);
    if (window.submitWithGet && a.length <= b) {
        h = true;
    }
    if (h) {
        g += "&submitdata=" + a;
        g += "&useget=1";
    } else {
        if (window.submitWithGet) {
            window.postIframe = 1;
        }
    }
    if (window.postIframe) {
        postWithIframe(g, p);
    } else {
        if (h) {
            $.ajax({
                type: "GET", url: g, success: function (e) {
                    afterSubmit(e, l);
                }, error: function () {
                    $(".ValError").html("很抱歉，网络连接异常，请重新尝试提交！");
                    $("#ctlNext").show();
                    return;
                }
            });
        } else {
            $.ajax({
                type: "POST", url: g, data: m, dataType: "text", success: function (e) {
                    afterSubmit(e, l);
                }
            });
        }
    }
}
function postWithIframe(b, c) {
    var a = document.createElement("div");
    a.style.display = "none";
    a.innerHTML = "<iframe id='mainframe' name='mainframe' style='display:none;' > </iframe><form target='mainframe' data-ajax='false' id='frameform' action='' method='post' enctype='application/x-www-form-urlencoded'><input  value='' id='submitdata' name='submitdata' type='hidden'><input type='submit' value='提交' ></form>";
    document.body.appendChild(a);
    document.getElementById("submitdata").value = c;
    var d = document.getElementById("frameform");
    d.action = b + "&iframe=1";
    d.submit();
}
var havereturn = false;
var timeoutTimer = null;
function processError(c, b, a) {
    if (!havereturn) {
        havereturn = true;
        $(".ValError").html("提交超时，请检查网络是否异常！");
        $("#ctlNext").show();
    }
    if (timeoutTimer) {
        clearTimeout(timeoutTimer);
    }
}
var nvvv = 0;
function afterSubmit(s, k) {
    $(".ValError").html("");
    havereturn = true;
    var m = s.split("〒");
    var g = m[0];
    if (g == 10) {
        var f = m[1];
        var n = f.replace("complete.aspx", "completemobile2.aspx").replace("?q=", "?activity=").replace("&joinid=", "&joinactivity=").replace("&JoinID=", "&joinactivity=");
        if (startAge) {
            n += "&sa=" + encodeURIComponent(startAge);
        }
        if (endAge) {
            n += "&ea=" + encodeURIComponent(endAge);
        }
        if (rName) {
            n += "&rname=" + encodeURIComponent(rName);
        }
        if (inviteid) {
            n += "&inviteid=" + encodeURIComponent(inviteid);
        }
        if (window.sourceurl) {
            n += "&source=" + encodeURIComponent(sourceurl);
        }
        if (window.sjUser) {
            n += "&sjUser=" + encodeURIComponent(sjUser);
        }
        if (window.needHideShare) {
            n += "&nhs=1";
        }
        if (!window.wxthird && window.access_token && window.hashb) {
            n += "&access_token=" + encodeURIComponent(access_token) + "&openid=" + encodeURIComponent(openid);
        }
        if (window.isWeiXin) {
            var p = new Date();
            p.setTime(p.getTime() + (30 * 60 * 1000));
            setCookie("join_" + activityId, "1", p.toUTCString(), "/", "", null);
        }
        if ($("#shopcart")[0] && $("#shopcart")[0].style.display != "none") {
            n += "&ishop=1";
        }
        location.replace(n);
        return;
    } else {
        if (g == 11) {
            var l = m[1];
            if (!l) {
                l = window.location.href;
            } else {
                if (l.toLowerCase().indexOf("http://") == -1 && l.toLowerCase().indexOf("https://") == -1) {
                    l = "http://" + l;
                }
            }
            var u = m[3] || "";
            var j = m[4] || "";
            var r = false;
            if (l.indexOf("{output}") > -1) {
                if (window.sojumpParm) {
                    l = l.replace("{output}", window.sojumpParm);
                } else {
                    if (j) {
                        l = l.replace("{output}", j);
                    }
                }
                r = true;
            }
            if (window.sojumpParm || j) {
                var h = u.split(",");
                var a = "sojumpindex=" + h[0];
                if (l.indexOf("?") > -1) {
                    a = "&" + a;
                } else {
                    a = "?" + a;
                }
                if (h[1]) {
                    a += "&totalvalue=" + h[1];
                }
                if (l.toLowerCase().indexOf("sojumpparm=") == -1 && !r && window.sojumpParm) {
                    a += "&sojumpparm=" + window.sojumpParm;
                }
                if (l.toLowerCase().indexOf("pingzheng=") == -1 && !r && j) {
                    a += "&pingzheng=" + j;
                }
                l += a;
            }
            if (window.wxthird && window.openid) {
                if (l.indexOf("?") > -1) {
                    l += "&openid=" + encodeURIComponent(openid);
                } else {
                    l += "?openid=" + encodeURIComponent(openid);
                }
            }
            if (l.indexOf("www.sojump.com") > -1) {
                l = l.replace("/jq/", "/m/");
            }
            var q = m[2];
            if (q && window.jiFenBao == 0 && q != "不提示" && !window.sojumpParm) {
                $(".ValError").html(q);
            }
            setTimeout(function () {
                location.replace(l);
            }, 1000);
            return;
        } else {
            if (k == 3) {
                if (g == 12) {
                    to_next_page();
                    $("#ctlNext").show();
                    return;
                } else {
                    if (g == 13) {
                        var d = m[1];
                        var t = m[2] || "0";
                        var f = "/wjx/join/completemobile2.aspx?activity=" + activityId + "&joinactivity=" + d;
                        f += "&v=" + t;
                        if (window.isWeiXin) {
                            setCookie("join_" + activityId, "1", null, "/", "", null);
                        }
                        if (window.sjUser) {
                            f += "&sjUser=" + encodeURIComponent(sjUser);
                        }
                        if (window.sourceurl) {
                            f += "&source=" + encodeURIComponent(sourceurl);
                        }
                        location.replace(f);
                        return;
                    } else {
                        if (g == 11) {
                            return;
                        } else {
                            if (g == 5) {
                                alert(m[1]);
                                return;
                            }
                        }
                    }
                }
            } else {
                if (g == 9 || g == 16 || g == 23) {
                    var o = parseInt(m[1]);
                    var c = (o + 1) + "";
                    var e = m[2] || "您提交的数据有误，请检查！";
                    if (questionsObject[c]) {
                        writeError(questionsObject[c], e, 3000);
                        $(questionsObject[c])[0].scrollIntoView();
                    }
                    alert(e);
                    $("#ctlNext").show();
                } else {
                    if (g == 2 || g == 21) {
                        alert(m[1]);
                        window.submitWithGet = 1;
                        $("#ctlNext").show();
                    } else {
                        if (g == 4) {
                            alert(m[1]);
                            $("#ctlNext").show();
                            return;
                        } else {
                            if (g == 19 || g == 5) {
                                alert(m[1]);
                                $("#ctlNext").show();
                                return;
                            } else {
                                if (g == 17) {
                                    alert("密码已经被其他人使用过！");
                                    return;
                                } else {
                                    if (g == 22) {
                                        alert("提交有误，请输入验证码重新提交！");
                                        if (!needAvoidCrack) {
                                            tCode.style.display = "";
                                            imgCode.style.display = "";
                                            imgCode.onclick = refresh_validate;
                                            imgCode.onclick();
                                        }
                                        nvvv = 1;
                                        $("#ctlNext").show();
                                        return;
                                    } else {
                                        if (g == 7) {
                                            alert(m[1]);
                                            if (!needAvoidCrack) {
                                                tCode.style.display = "";
                                                if (!imgCode.onclick) {
                                                    imgCode.style.display = "";
                                                    imgCode.onclick = refresh_validate;
                                                }
                                                imgCode.onclick();
                                            } else {
                                                refresh_validate();
                                            }
                                            $("#ctlNext").show();
                                            return;
                                        } else {
                                            var b = m[1] || s;
                                            alert(b);
                                            $("#ctlNext").show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    refresh_validate();
}
function clearFieldValue(c) {
    var d = $(c).attr("type");
    if (d == "3") {
        $("input[type='radio']:checked", $(c)).each(function () {
            this.checked = false;
            $(this).parents(".ui-radio").find("a.jqradio").removeClass("jqchecked");
        });
        $("input.OtherRadioText", $(c)).each(function () {
            $(this).val("").blur();
        });
    } else {
        if (d == "4") {
            $("input:checked", $(c)).each(function () {
                this.checked = false;
                $(this).parents(".ui-checkbox").find("a.jqcheck").removeClass("jqchecked");
            });
        } else {
            if (d == "6") {
                $("a.rate-off", $(c)).each(function () {
                    $(this).removeClass("rate-on");
                });
            } else {
                if (d == "5") {
                    $("a.rate-off", $(c)).each(function () {
                        $(this).removeClass("rate-on");
                    });
                } else {
                    if (d == "7") {
                        if ($("select", $(c)).val() != "-2") {
                            $("select", $(c)).val("-2").trigger("change");
                        }
                    } else {
                        if (d == "8") {
                            $("input", $(c)).val("").change();
                        } else {
                            if (d == "9") {
                                $("input.ui-slider-input", $(c)).each(function () {
                                    $(this).val("").change();
                                });
                            } else {
                                if (d == "11") {
                                    $("li.ui-li-static", $(c)).each(function () {
                                        $(this).find("span.sortnum").html("").removeClass("sortnum-sel");
                                        $(this).attr("check", "");
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
function validateQ(n) {
    var g = $(n).attr("req"), k = $(n).attr("type"), m = true;
    var j = $(n)[0];
    var f = "";
    var e = $(n).attr("hasjump");
    if (k == "1") {
        var h = $("input", $(n));
        var d = $.trim(h.val());
        m = d.length == 0 ? false : true;
        f = verifyTxt(n, h);
    } else {
        if (k == "2") {
            var h = $("textarea", $(n));
            var d = $.trim(h.val());
            m = d.length == 0 ? false : true;
            f = verifyTxt(n, h);
        } else {
            if (k == "3") {
                m = false;
                $(n).find("input:checked").each(function () {
                    m = true;
                    var a = $(this).attr("rel");
                    if (a) {
                        var b = $("#" + a);
                        if (b.attr("required") && b.val().length == 0) {
                            f = "文本框内容必须填写！";
                            writeError(n, f, 3000);
                            return false;
                        }
                    }
                });
            } else {
                if (k == "4") {
                    m = false;
                    var o = false;
                    $(n).find("input:checked").each(function () {
                        m = true;
                        var a = $(this).attr("rel");
                        if (a) {
                            var b = $("#" + a);
                            if (b.attr("required") && b.val().length == 0) {
                                f = "文本框内容必须填写！";
                                b.focus();
                                writeError(n, f, 3000);
                                o = true;
                                return false;
                            }
                        }
                    });
                    if (!o) {
                        f = verifyCheckMinMax($(n), true);
                    }
                } else {
                    if (k == "11") {
                        m = $("li.ui-li-static[check='1']", $(n)).length == 0 ? false : true;
                        f = verifyCheckMinMax($(n), true, true);
                    } else {
                        if (k == "5") {
                            m = validateScaleRating($(n));
                        } else {
                            if (k == "6") {
                                f = validateMatrix($(n), g);
                                if (f) {
                                    writeError(n, f, 1000);
                                    return false;
                                }
                            } else {
                                if (k == "7") {
                                    m = $("select", $(n))[0].selectedIndex == 0 ? false : true;
                                } else {
                                    if (k == "8") {
                                        m = $("input", $(n)).val().length == 0 ? false : true;
                                    } else {
                                        if (k == "9") {
                                            $("input", $(n)).each(function () {
                                                var a = $(this);
                                                var c = a.val();
                                                if (window.hasReferClient) {
                                                    var b = a.parents("tr")[0];
                                                    if (b && b.style.display == "none") {
                                                        return true;
                                                    }
                                                }
                                                if (c.length == 0) {
                                                    m = false;
                                                }
                                                f = verifyTxt(n, a, true);
                                                if (f) {
                                                    return false;
                                                }
                                            });
                                        } else {
                                            if (k == "12") {
                                                var l = $(n).attr("total");
                                                var p = l;
                                                $("input", $(n)).each(function () {
                                                    var a = $(this);
                                                    if (window.hasReferClient) {
                                                        var c = a.parents("tr")[0];
                                                        if (c && c.style.display == "none") {
                                                            return true;
                                                        }
                                                    }
                                                    var b = a.val();
                                                    if (b.length == 0) {
                                                        m = false;
                                                    }
                                                    if (b) {
                                                        p = p - b;
                                                    }
                                                });
                                                if (p != 0) {
                                                    writeError(n, "", 3000);
                                                    return false;
                                                }
                                            } else {
                                                if (k == "13") {
                                                    if (!$(n)[0].fileName) {
                                                        m = false;
                                                    }
                                                } else {
                                                    if (k == "10") {
                                                        var r = "input";
                                                        if ($(n).attr("select") == "1") {
                                                            r = "select";
                                                        }
                                                        var q = true;
                                                        $("table", $(n)).each(function () {
                                                            var a = $(this);
                                                            if (window.hasReferClient) {
                                                                var b = a.parents(".mdivtable")[0];
                                                                if (b && b.style.display == "none") {
                                                                    return true;
                                                                }
                                                            }
                                                            $(r, a).each(function () {
                                                                var s = $(this);
                                                                var t = s.val();
                                                                var c = s.parents("td")[0];
                                                                if (c && c.style.display != "none") {
                                                                    if (t.length == 0 || (r == "select" && t == "-2")) {
                                                                        m = false;
                                                                        n.errorControl = this;
                                                                        return false;
                                                                    }
                                                                    f = verifyTxt(n, s, true);
                                                                    if (f) {
                                                                        n.errorControl = this;
                                                                        q = false;
                                                                        return false;
                                                                    }
                                                                }
                                                            });
                                                            if (!q) {
                                                                return false;
                                                            }
                                                        });
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if (!m && g == "1") {
        f = "此项必填";
        if (k == "6" && $(n)[0].isMatrixFillError) {
            f = "请注明原因";
        }
        if (langVer == 1) {
            f = "required";
        }
        writeError(n, f, 1000);
    } else {
        $("span.error", $(n)).hide();
        $("div.field-label", $(n)).css("background", "");
    }
    if (f) {
        return false;
    }
    if (j.removeError) {
        j.removeError();
    }
    return true;
}
function show_prev_page() {
    if (cur_page > 0 && pageHolder[cur_page - 1].hasExceedTime) {
        alert("上一页填写超时，不能返回上一页");
        return;
    }
    var e = $("#divNext")[0];
    var g = $("#divPrev")[0];
    pageHolder[cur_page].style.display = "none";
    e.style.display = "";
    $("#divSubmit").hide();
    cur_page--;
    for (var c = cur_page; c >= 0; c--) {
        if (pageHolder[c].skipPage) {
            cur_page--;
        } else {
            break;
        }
    }
    for (var c = cur_page; c >= 0; c--) {
        var a = $(".field", pageHolder[c]);
        if (a.length == 0) {
            break;
        }
        var f = false;
        for (var b = 0; b < a.length; b++) {
            var d = a[b];
            if (d.style.display != "none") {
                f = true;
                break;
            }
        }
        if (!f && cur_page > 0) {
            cur_page--;
        } else {
            break;
        }
    }
    if (cur_page == 0) {
        g.style.display = "none";
    }
    pageHolder[cur_page].style.display = "";
    pageHolder[cur_page].scrollIntoView();
    showProgress();
}
function show_next_page() {
    var a = $("#divNext a")[0];
    if (a && a.disabled) {
        return;
    }
    if (!validate()) {
        return;
    }
    var b = $(pageHolder[cur_page]).attr("iszhenbie") == "true";
    if (b && window.isRunning) {
        groupAnswer(3);
    } else {
        to_next_page();
    }
}
function to_next_page() {
    var h = $("#divNext")[0];
    var b = $("#divPrev")[0];
    b.style.display = displayPrevPage;
    pageHolder[cur_page].style.display = "none";
    cur_page++;
    if (cur_page == 1) {
        $("#divDesc").hide();
    }
    for (var f = cur_page; f < pageHolder.length; f++) {
        if (pageHolder[f].skipPage) {
            cur_page++;
        } else {
            break;
        }
    }
    var l = false;
    for (var f = cur_page; f < pageHolder.length; f++) {
        var k = $(".field", pageHolder[f]);
        if (k.length == 0 && !l) {
            break;
        }
        var e = false;
        for (var c = 0; c < k.length; c++) {
            var a = k[c];
            if (a.style.display != "none") {
                e = true;
                break;
            }
        }
        if (!e && cur_page < pageHolder.length - 1) {
            cur_page++;
            l = true;
        } else {
            break;
        }
    }
    var d = true;
    for (var f = cur_page + 1; f < pageHolder.length; f++) {
        if (!pageHolder[f].skipPage) {
            d = false;
        }
    }
    if (cur_page >= pageHolder.length - 1 || d) {
        h.style.display = "none";
        $("#divSubmit").show();
    }
    if (cur_page < pageHolder.length - 1) {
        h.style.display = "";
    }
    pageHolder[cur_page].style.display = "";
    initSlider();
    var g = document.getElementById("divMaxTime");
    if (g && g.style.display == "") {
        $("body,html").animate({scrollTop: 0}, 100);
    } else {
        pageHolder[cur_page].scrollIntoView();
    }
    showProgress();
    if (window.hasPageTime) {
        processMinMax();
    }
    fixBottom();
}
function initSlider() {
    if (window.hasSlider) {
        $(".field", pageHolder[cur_page]).each(function () {
            var b = $(this);
            var a = b.attr("type");
            if (a == "8" || a == "12" || a == "9" || a == "10") {
                setTimeout(function () {
                    var c = $("input.ui-slider-input:visible", b);
                    c.rangeslider({polyfill: false});
                }, 10);
            }
        });
    }
}
function initqSlider(a) {
    if (!window.hasSlider) {
        return;
    }
    if (a.hasInitSlider) {
        return;
    }
    a.hasInitSlider = true;
    var b = $("input.ui-slider-input:visible", a);
    b.rangeslider({polyfill: false});
}
function showProgress() {
    if (totalPage == 1) {
        return;
    }
    var c = cur_page + 1;
    if (c > totalPage) {
        c = totalPage;
    }
    var b = c + "/" + totalPage;
    $(".pagepercent").html(b + "页");
    var a = c * 100 / totalPage;
    $(".pagebar").width(a + "%");
}
function verifyCheckMinMax(a, c, k, e) {
    var d = a.attr("minvalue");
    var h = a.attr("maxvalue");
    var g = a[0];
    if (d == 0 && h == 0) {
        return "";
    }
    var f = 0;
    if (k) {
        f = $("li.ui-li-static[check='1']", a).length;
    } else {
        f = $("input:checked", a).length;
    }
    if (f == 0 && !a.attr("req")) {
        return;
    }
    var b = "";
    if (langVer == 0) {
        b = "&nbsp;&nbsp;&nbsp;您已经选择了" + f + "项";
    }
    var j = true;
    if (h > 0 && f > h) {
        if (e) {
            if (langVer == 0) {
                alert("此题最多只能选择" + h + "项");
            }
            $(e).trigger("click");
            return "";
        }
        if (langVer == 0) {
            b += ",<span style='color:red;'>多选择了" + (f - h) + "项</span>";
        } else {
            b = validate_info + validate_info_check4 + h + type_check_limit5;
        }
        j = false;
    } else {
        if (d > 0 && f < d) {
            if (langVer == 0) {
                b += ",<span style='color:red;'>少选择了" + (d - f) + "项</span>";
            } else {
                b = validate_info + validate_info_check5 + d + type_check_limit5;
            }
            j = false;
            if (!k && f == 1 && $("input:checked", a).parents(".ui-checkbox").hasClass("huchi")) {
                j = true;
            }
        }
    }
    if (!g.errorMessage) {
        g.errorMessage = $(".errorMessage", a)[0];
    }
    if (!j) {
        if (!c) {
            g.errorMessage.innerHTML = b;
        } else {
            writeError(a[0], b, 3000);
        }
        return b;
    } else {
        g.errorMessage.innerHTML = "";
    }
    return "";
}
function checkOnly(c, e) {
    var d = $(e).attr("needonly");
    if (!d) {
        return;
    }
    var g = $(e).val();
    if (!g) {
        return;
    }
    if (g.length > 50) {
        return;
    }
    var b = "/Handler/AnswerOnlyHandler.ashx?q=" + activityId + "&at=" + g + "&qI=" + getTopic(c) + "&o=true&t=" + (new Date()).valueOf();
    var a = $(c)[0];
    var f = "";
    if (!e.errorOnly) {
        e.errorOnly = new Object();
    }
    if (e.errorOnly[g]) {
        f = validate_only;
        if (a.verifycodeinput) {
            a.verifycodeinput.parentNode.style.display = "none";
        }
        writeError(a, f, 3000);
        return;
    }
    $.ajax({
        type: "GET", url: b, async: false, success: function (h) {
            if (h == "false1") {
                f = validate_only;
                e.errorOnly[g] = 1;
                if (a.verifycodeinput) {
                    a.verifycodeinput.parentNode.style.display = "none";
                }
                writeError(a, f, 3000);
            }
        }
    });
}
function verifyTxt(a, e, d) {
    //不验证
    return;
    var c = $(e).val();
    var h = $(e).attr("verify");
    var j = $(e).attr("minword");
    var f = $(e).attr("maxword");
    var g = $(a)[0];
    var b = "";
    if (!c) {
        return b;
    }
    if (g.removeError) {
        g.removeError();
    }
    b = verifyMinMax(c, h, j, f);
    if (!b) {
        b = verifydata(c, h);
    }
    if (b) {
        if (!g.errorControl && d) {
            g.errorControl = $(e)[0];
        }
        writeError(g, b, 3000);
        return b;
    }
    if (!b && g.needsms && !g.issmsvalid) {
        b = "提示：您的手机号码没有通过验证，请先验证";
        writeError(g, b, 3000);
    }
    return b;
}
function validateMatrix(g, d) {
    var e = $("table.matrix-rating", $(g)), b;
    var f = "";
    $(g)[0].isMatrixFillError = false;
    $("tr[tp='d']", e).each(function () {
        var l = $(this).attr("fid"), m = $("a.rate-on", $(this));
        b = "";
        if (window.hasReferClient && this.style.display == "none") {
            return true;
        }
        if (m.length == 0) {
            if (d == "1") {
                f = "此项必填";
                if (langVer == 1) {
                    f = "required";
                }
                $(g)[0].errorControl = $(this).prev("tr")[0];
                return false;
            } else {
                return true;
            }
        } else {
            b = $(m).attr("dval");
            var c = $(g).attr("ischeck");
            if (c) {
                b = "";
                var h = $(g).attr("minvalue");
                var a = $(g).attr("maxvalue");
                if (h && m.length - h < 0) {
                    f = validate_info + validate_info_check5 + h + type_check_limit5;
                    $(g)[0].errorControl = $(this).prev("tr")[0];
                    return false;
                } else {
                    if (a && m.length - a > 0) {
                        f = validate_info + validate_info_check4 + a + type_check_limit5;
                        $(g)[0].errorControl = $(this).prev("tr")[0];
                        return false;
                    }
                }
                $(m).each(function () {
                    if (b) {
                        b += ";";
                    }
                    b += $(this).attr("dval");
                    var p = $(this).attr("needfill");
                    if (p) {
                        var q = this.fillvalue || "";
                        q = replace_specialChar(q).replace(/;/g, "；").replace(/,/g, "，");
                        b += spChars[2] + q;
                        var o = $(this).attr("req");
                        if (o && !q) {
                            f = "此项必填";
                            if (langVer == 1) {
                                f = "required";
                            }
                            $(g)[0].isMatrixFillError = true;
                            showMatrixFill(this, 1);
                            return false;
                        }
                    }
                });
            } else {
                var k = $(m).attr("needfill");
                if (k) {
                    var n = $(m)[0].fillvalue || "";
                    n = replace_specialChar(n).replace(/;/g, "；").replace(/,/g, "，");
                    b += spChars[2] + n;
                    var j = $(m).attr("req");
                    if (j && !n) {
                        f = "此项必填";
                        if (langVer == 1) {
                            f = "required";
                        }
                        $(g)[0].isMatrixFillError = true;
                        showMatrixFill($(m)[0], 1);
                        return false;
                    }
                }
            }
            $("#" + l, $(g)).attr("value", b);
        }
    });
    return f;
}
function validateScaleRating(d) {
    var e = true, f = $("table.scale-rating", $(d));
    $("tr[tp='d']", f).each(function () {
        var a = $("a.rate-on", $(this));
        if (a.length == 0) {
            e = false;
        } else {
            $("input:hidden", $(d)).attr("value", $(a).attr("val"));
        }
    });
    return e;
}
function jump(c, e) {
    var d = $(c);
    var b = d.attr("type");
    var f = d.attr("hasjump");
    var a = d.attr("anyjump");
    if (f) {
        if (a > 0) {
            jumpAnyChoice(c);
        } else {
            if (a == 0 && b != "3" && b != "5" && b != "7") {
                jumpAnyChoice(c);
            } else {
                jumpByChoice(c, e);
            }
        }
    }
}
function jumpAnyChoice(c, f) {
    var d = $(c);
    var b = d.attr("type");
    var a = false;
    if (b == "1") {
        a = $("input", d).val().length > 0;
    } else {
        if (b == "2") {
            a = $("textarea", d).val().length > 0;
        } else {
            if (b == "3") {
                a = $("input[type='radio']:checked", d).length > 0;
            } else {
                if (b == "4") {
                    a = $("input[type='checkbox']:checked", d).length > 0;
                } else {
                    if (b == "5") {
                        a = $("a.rate-on", d).length > 0;
                    } else {
                        if (b == "6") {
                            a = $("a.rate-on", d).length > 0;
                        } else {
                            if (b == "7") {
                                a = $("select", d).val() != -2;
                            } else {
                                if (b == "8") {
                                    a = $("input", d).val().length > 0;
                                } else {
                                    if (b == "9" || b == "12") {
                                        $("input", d).each(function () {
                                            var g = $(this).val();
                                            if (g.length > 0) {
                                                a = true;
                                            }
                                        });
                                    } else {
                                        if (b == "10") {
                                            var e = d.attr("select") == "1";
                                            if (e) {
                                                $("select", d).each(function () {
                                                    var g = $(this).val();
                                                    if (g != -2) {
                                                        a = true;
                                                    }
                                                });
                                            } else {
                                                $("input", d).each(function () {
                                                    var g = $(this).val();
                                                    if (g.length > 0) {
                                                        a = true;
                                                    }
                                                });
                                            }
                                        } else {
                                            if (b == "11") {
                                                a = $("li[check='1']", d).length > 0;
                                            } else {
                                                if (b == "13") {
                                                    a = d[0].fileName ? true : false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    jumpAny(a, c, f);
}
function jumpByChoice(d, e) {
    var c = $(d).attr("type");
    var b = $(d)[0];
    if (e.value == "-2") {
        processJ(b.indexInPage - 0, 0);
    } else {
        if (e.value == "-1" || e.value == "") {
            processJ(b.indexInPage - 0, 0);
        } else {
            if ((c == "3" || c == "5" || c == "7")) {
                var f = e.value || $(e).attr("val");
                if (parseInt(f) == f) {
                    var a = $(e).attr("jumpto") - 0;
                    processJ(b.indexInPage - 0, a);
                }
            }
        }
    }
}
function jumpAny(c, e, g) {
    var f = $(e);
    var d = f.attr("type");
    var h = f.attr("hasjump");
    var a = f.attr("anyjump") - 0;
    var b = f[0];
    if (h) {
        if (c) {
            processJ(b.indexInPage - 0, a, g);
        } else {
            processJ(b.indexInPage - 0, 0, g);
        }
    }
}
function processJ(m, d, e) {
    var a = m + 1;
    var b = cur_page;
    for (var h = cur_page; h < pageHolder.length; h++) {
        var l = $(".field", pageHolder[h]);
        if (d == 1) {
            b = h;
        }
        for (var g = a; g < l.length; g++) {
            var k = getTopic(l[g]);
            if (k == d || d == 1) {
                b = h;
            }
            if (k < d || d == 1) {
                l[g].style.display = "none";
            } else {
                if (relationNotDisplayQ[k]) {
                    var f = 1;
                } else {
                    l[g].style.display = "";
                }
                var c = $(l[g]).attr("hasjump");
                if (c && !e) {
                    clearFieldValue(l[g]);
                }
            }
        }
        a = 0;
    }
    fixBottom();
}
function GetBacktoServer() {
    str = window.location.pathname;
    index = str.lastIndexOf("/");
    page = str.substr(index + 1, str.length - index);
    data = readCookie("history");
    if (data != null && data.toLowerCase() != page.toLowerCase()) {
        window.location.href = window.location.href;
    }
}
function readCookie(h) {
    for (var k = h + "=", j = document.cookie.split(";"), f = 0; f < j.length; f++) {
        var g = j[f];
        while (g.charAt(0) == " ") {
            g = g.substring(1, g.length);
        }
        if (g.indexOf(k) == 0) {
            return g.substring(k.length, g.length);
        }
    }
    return null;
}
function removeError() {
    if (this.errorMessage) {
        this.errorMessage.innerHTML = "";
        this.removeError = null;
        this.style.border = "solid 2px #f7f7f7";
        if (this.errorControl) {
            this.errorControl.style.background = "white";
            this.errorControl = null;
        }
    }
}
function writeError(a, c, b) {
    a = $(a)[0];
    a.style.border = "solid 2px #ff9900";
    if (a.errorMessage) {
        a.errorMessage.innerHTML = c;
    } else {
        a.errorMessage = $(".errorMessage", $(a))[0];
        a.errorMessage.innerHTML = c;
    }
    a.removeError = removeError;
    if (a.errorControl) {
        a.errorControl.style.background = "#FBD5B5";
    }
    if (!firstError) {
        firstError = a;
    }
    return false;
}
function verifydata(d, c) {
    if (!c) {
        return "";
    }
    var a = null;
    if (c.toLowerCase() == "email" || c.toLowerCase() == "msn") {
        a = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/i;
        if (!a.exec(d)) {
            return validate_email;
        } else {
            return "";
        }
    } else {
        if (c == "日期" || c == "生日" || c == "入学时间") {
            return "";
        } else {
            if (c == "固话") {
                a = /^((\d{4}-\d{7})|(\d{3,4}-\d{8}))(-\d{1,4})?$/;
                if (!a.exec(d)) {
                    return validate_phone.replace("，请注意使用英文字符格式", "");
                } else {
                    return "";
                }
            } else {
                if (c == "手机") {
                    a = /^\d{11}$/;
                    if (!a.exec(d)) {
                        return validate_mobile.replace("，请注意使用英文字符格式", "");
                    } else {
                        return "";
                    }
                } else {
                    if (c == "电话") {
                        a = /(^\d{11}$)|(^((\d{4}-\d{7})|(\d{3,4}-\d{8}))(-\d{1,4})?$)/;
                        if (!a.exec(d)) {
                            return validate_mo_phone.replace("，请注意使用英文字符格式", "");
                        } else {
                            return "";
                        }
                    } else {
                        if (c == "汉字") {
                            a = /^[\u4e00-\u9fa5]+$/;
                            if (!a.exec(d)) {
                                return validate_chinese;
                            } else {
                                return "";
                            }
                        } else {
                            if (c == "英文") {
                                a = /^[A-Za-z]+$/;
                                if (!a.exec(d)) {
                                    return validate_english;
                                } else {
                                    return "";
                                }
                            } else {
                                if (c == "网址" || c == "公司网址") {
                                    a = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
                                    if (!a.exec(d)) {
                                        return validate_reticulation;
                                    } else {
                                        return "";
                                    }
                                } else {
                                    if (c == "身份证号") {
                                        a = /^\d{15}(\d{2}[A-Za-z0-9])?$/;
                                        if (!a.exec(d)) {
                                            return validate_idcardNum;
                                        } else {
                                            return "";
                                        }
                                    } else {
                                        if (c == "数字") {
                                            a = /^(\-)?\d+$/;
                                            if (!a.exec(d)) {
                                                return validate_num.replace("，请注意使用英文字符格式", "");
                                            }
                                        } else {
                                            if (c == "小数") {
                                                a = /^(\-)?\d+(\.\d+)?$/;
                                                if (!a.exec(d)) {
                                                    return validate_decnum;
                                                }
                                            } else {
                                                if (c.toLowerCase() == "qq") {
                                                    a = /^\d+$/;
                                                    var b = /^\w+([-+.]\w+)*@\w+([-.]\\w+)*\.\w+([-.]\w+)*$/;
                                                    if (!a.exec(d) && !b.exec(d)) {
                                                        return validate_qq;
                                                    } else {
                                                        return "";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return "";
}
function verifyMinMax(e, d, c, a) {
    if (d == "数字" || d == "小数") {
        var b = /^(\-)?\d+$/;
        if (d == "小数") {
            b = /^(\-)?\d+(\.\d+)?$/;
        }
        if (!b.exec(e)) {
            if (d == "小数") {
                return validate_decnum;
            } else {
                return validate_num.replace("，请注意使用英文字符格式", "");
            }
        }
        if (e != 0) {
            e = e.replace(/^0+/, "");
        }
        if (c != "") {
            if (d == "数字" && parseInt(e) - parseInt(c) < 0) {
                return validate_num2 + c;
            } else {
                if (d == "小数" && parseFloat(e) - parseFloat(c) < 0) {
                    return validate_num2 + c;
                }
            }
        }
        if (a != "") {
            if (d == "数字" && parseInt(e) - parseInt(a) > 0) {
                return validate_num1 + a;
            } else {
                if (d == "小数" && parseFloat(e) - parseFloat(a) > 0) {
                    return validate_num1 + a;
                }
            }
        }
    } else {
        if (a != "" && e.length - a > 0) {
            return validate_info_wd3.format(a, e.length);
        }
        if (c != "" && e.length - c < 0) {
            return validate_info_wd4.format(c, e.length);
        }
    }
    return "";
}
function getTopic(a) {
    return $(a).attr("topic");
}
function displayRelationByType(d, c, b) {
    var a = getTopic(d);
    if (!relationQs[a]) {
        return;
    }
    d.hasDisplayByRelation = new Object();
    $(c, d).each(function () {
        var f = false;
        var g = "";
        if (b == 1 || b == 2 || b == 5) {
            g = this.value;
        } else {
            if (b == 3) {
                g = $("input[type=hidden]", this).val();
            } else {
                if (b == 4) {
                    g = $(this).attr("val");
                }
            }
        }
        var h = a + "," + g;
        if (b == 3 && $(this).attr("check")) {
            f = true;
        } else {
            if (b == 4 && $(this).hasClass("rate-on")) {
                f = true;
            } else {
                if ((b == 1 || b == 2) && this.checked) {
                    f = true;
                } else {
                    if (b == 5 && this.selected) {
                        f = true;
                    }
                }
            }
        }
        displayByRelation(d, h, f);
        var e = a + ",-" + g;
        if (relationHT[e]) {
            displayByRelationNotSelect(d, e, f);
        }
    });
    fixBottom();
}
function displayByRelation(c, f, b) {
    var d = relationHT[f];
    if (!d) {
        return;
    }
    for (var a = 0; a < d.length; a++) {
        var e = getTopic(d[a]);
        if (c.hasDisplayByRelation[e]) {
            continue;
        }
        if (!b && d[a].style.display != "none") {
            loopHideRelation(d[a]);
        } else {
            if (b) {
                d[a].style.display = "";
                initqSlider(d[a]);
                c.hasDisplayByRelation[e] = "1";
                if (relationNotDisplayQ[e]) {
                    relationNotDisplayQ[e] = "";
                }
            }
        }
    }
}
function displayByRelationNotSelect(c, f, b) {
    var d = relationHT[f];
    if (!d) {
        return;
    }
    for (var a = 0; a < d.length; a++) {
        var e = getTopic(d[a]);
        if (c.hasDisplayByRelation[e]) {
            continue;
        }
        if (b && d[a].style.display != "none") {
            loopHideRelation(d[a]);
        } else {
            if (!b) {
                d[a].style.display = "";
                initqSlider(d[a]);
                c.hasDisplayByRelation[e] = "1";
                if (relationNotDisplayQ[e]) {
                    relationNotDisplayQ[e] = "";
                }
            }
        }
    }
}
function loopHideRelation(a) {
    var c = getTopic(a);
    var b = relationQs[c];
    if (b) {
        for (var d = 0; d < b.length; d++) {
            loopHideRelation(b[d], false);
        }
    }
    clearFieldValue(a);
    $(a)[0].style.display = "none";
    if (relationNotDisplayQ[c] == "") {
        relationNotDisplayQ[c] = "1";
    }
}
function checkHuChi(c, e) {
    var b = $(".huchi", c)[0];
    if (!b) {
        return;
    }
    var f = $(e);
    if (!$("input:checked", f)[0]) {
        return;
    }
    var a = $(".ui-checkbox", c);
    var d = f.hasClass("huchi");
    a.each(function () {
        if (this == e) {
            return true;
        }
        var g = $(this);
        if (!$("input:checked", g)[0]) {
            return true;
        }
        if (d) {
            g.trigger("click");
        } else {
            var h = g.hasClass("huchi");
            if (h) {
                g.trigger("click");
            }
        }
    });
}