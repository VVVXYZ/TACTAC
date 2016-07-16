/**
 * Created by loser on 2016/2/26
 */
var jqgrid_postData_rule_ops = new Array("eq", "ne", "bn", "lt", "ew", "le", "wn", "gt", "bw", "ge", "in", "ni", "cn", "nc", "nu", "nn");
function simpleSearchRegister(grid_selector, form_selector) {
    $(form_selector).each(function () {
        this.onsubmit = function (e) {
            e.preventDefault();

            var postData = $(grid_selector).jqGrid("getGridParam", "postData");
            postData["filters"] = getFiltersString(form_selector);
            $(grid_selector).jqGrid("setGridParam", {postData: postData}).trigger("reloadGrid");
        };
    });

    $(form_selector).each(function () {
        this.onreset = function (e) {
            var postData = $(grid_selector).jqGrid("getGridParam", "postData");
            delete postData.filters;
            $(grid_selector).jqGrid("setGridParam", {postData: postData}).trigger("reloadGrid");
        };
    });
}

function getFiltersString(form_selector){
    var filters = createGroup("AND");

    $(form_selector).find("select,[type=text]").each(function () {
        if (this.value != "") {
            var key = this.name;
            var op = $(this).attr("op")
            if (op != null) {
                op=op.toString().toLowerCase();
            }
            if ($.inArray(op, jqgrid_postData_rule_ops) == -1) {
                op = "eq";
            }
            var value = this.value;

            appendRule(filters, createRule(key, op, value));
        }
    });
    $(form_selector).find("[type=radio]:checked").each(function () {
        if (this.value != "") {
            var key = this.name;
            var op = $(this).attr("op");
            if(op == undefined) {
                var op = "eq";
            }
            var value = this.value;

            appendRule(filters, createRule(key, op, value));
        }
    });
    var checkboxMap = new Object();
    $(form_selector + " :checkbox:checked").each(function () {

        if (checkboxMap.hasOwnProperty(this.name)) {
            checkboxMap[this.name] += "," + this.value;
        } else {
            checkboxMap[this.name] = this.value;
        }
    });
    for (var key in checkboxMap) {
        appendRule(filters, createRule(key, "in", checkboxMap[key]));
    }

    return JSON.stringify(filters);
}

function createGroup(groupOp) {

    var group = new Object();

    groupOp = groupOp.toUpperCase();

    if (groupOp != "AND" && groupOp != "OR") {
        groupOp = "AND";
    }

    group["groupOp"] = groupOp;

    return group;
}

function appendGroup(group, innerGroup) {

    var groups;

    if (!group.hasOwnProperty("groups") || group["groups"].length == 0) {
        groups = new Array();
    } else {
        groups = group["groups"];
    }

    groups.push(innerGroup);
    group["groups"] = groups;
}

function createRule(key, op, value) {
    var rule = new Object();

    rule["field"] = key;
    rule["op"] = op;
    rule["data"] = value;

    return rule;
}

function appendRule(group, rule) {

    var rules;

    if (!group.hasOwnProperty("rules") || group["rules"].length == 0) {
        rules = new Array();
    } else {
        rules = group["rules"];
    }

    rules.push(rule);
    group["rules"] = rules;
}