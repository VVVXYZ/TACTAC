<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/18
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>接口首页</title>
  <style>
    li{
      line-height:33px;
    }

  </style>
</head>
<body>
<ol>
  <hr/>
  <li><a href='${ctx}/HomePage/getBulletinBoardList'>获取所有公告数据接口</a></li>
  <li><a href='${ctx}/userLogin.jhtml?param={"app_login_id":"18150389160","password":"123456","jpush_id":"123"}'>用户登陆[userLogin]</a></li>
  <li><a href='otherAddUser.jhtml?param={"app_login_id":"777888999","nick_name":"啦啦啦","user_header":"","type":"qq","jpush_id":"123"}'>三方登陆[otherAddUser]</a></li>
  <li><a href='getCode.jhtml?param={"app_login_id":"18150389160","type":"1"}'>获取验证码[getCode]</a></li>
  <li><a href='addUser.jhtml?param={"app_login_id":"18744033622","password":"123456","recommend_user_id":"100145"}'>用户信息注册[addUser]</a></li>
  <li><a href='updateUserPassword.jhtml?param={"app_login_id":"18744033622","password":"654321"}'>用户修改密码和微信第一次进入绑定密码[updateUserPassword]</a></li>

  <hr/>
  <li><a href='getUserData.jhtml?param={"user_id":"100145"}'>获取用户基本数据[getUserData]</a></li>
  <li><a href='updateUser.jhtml?param={"user_id":"100145","fieldName":"nick_name","fieldNameValue":"我是修改后的昵称","updateFieldNameNum":"1"}'>更新用户基本信息[updateUser]</a></li>
  <li><a href='getUserLevelInfoData.jhtml?param={"user_id":"100145"}'>获取用户等级数据[getUserData]</a></li>
  <li><a href='signIn.jhtml?param={"user_id":"100145"}'>签到[signIn]</a></li>
  <li><a href='getUserAddressListData.jhtml?param={"user_id":"100145"}'>获取用户地址列表[getUserAddressListData]</a></li>
  <li><a href='changeDefaultAddress.jhtml?param={"id":"96","user_id":"100145"}'>更改用户默认地址[changeDefaultAddress]</a></li>
  <li><a href='getCityByProvince.jhtml?param={"provinceId":"1"}'>根据省份id获取地市列表[getCityByProvince]</a></li>
  <li><a href='saveAddress.jhtml?param={"id":"","user_id":"100145","user_tel":"18150389160","user_name":"哈哈","province_id":"1","city_id":"1","detail_address":"哇哇哇","is_default":"y"}'>修改或新增地址[saveAddress]</a></li>
  <li><a href='delAddress.jhtml?param={"id":"1"}'>删除地址[delAddress]</a></li>
  <li><a href='getMessageInfoList.jhtml?param={"pageNum":"1","limitNum":"2"}'>获取系统消息[getMessageInfoList]</a></li>
  <li><a href='getFightRecordInfoList.jhtml?param={"user_id":"100145","status":"全部","pageNum":"1","limitNum":"2"}'>获取夺宝纪录[getFightRecordInfoList]</a></li>
  <li><a href='getFightWinRecordList.jhtml?param={"user_id":"100145","pageNum":"1","limitNum":"2"}'>获取中奖纪录[getFightWinRecordList]</a></li>
  <li><a href='saveOrderAddress.jhtml?param={"id":"1000000194","consignee_name":"哈哈哈","consignee_tel":"123123123","consignee_address":"我是地址"}'>修改订单地址[saveOrderAddress]</a></li>
  <li><a href='publishFightBask.jhtml?param={"user_id":"100145","goods_fight_id":"522860622338","title":"哇哇","content":"我是呢人","imgs":"123.jpg,234.jpg"}'>发布晒单[publishFightBask]</a></li>
  <li><a href='friendsInfo.jhtml?param={"user_id":"100145"}'>邀请好友基本数据[friendsInfo]</a></li>
  <li><a href='getExchangePoundage.jhtml'>获取积分提现所需手续费[getExchangePoundage]</a></li>
  <li><a href='getScoreOrMoneyHistoryList.jhtml?param={"user_id":"100145","type":"1","pageNum":"1","limitNum":"2"}'>获取积分或者夺宝币历史列表[getScoreOrMoneyHistoryList]</a></li>
  <li><a href='exchangeInMoney.jhtml?param={"user_id":"100145","exchange_type":"real","pay_score":"1000"}'>积分兑换申请[exchangeInMoney]</a></li>
  <li><a href='getMyTicketList.jhtml?param={"user_id":"100145","type":"1","pageNum":"1","limitNum":"2"}'>获取优惠券列表[getMyTicketList]</a></li>
  <li><a href='addTicketById.jhtml?param={"user_id":"100145","id":"1000000001"}'>兑换优惠券[addTicketById]</a></li>
  <li><a href='rotaryGame.jhtml?user_id=100145'>大转盘[rotaryGame]</a></li>
  <li><a href='getRotaryGameHistory.jhtml?param={"pageNum":"1","limitNum":"2"}'>获取大转盘获奖历史记录[getRotaryGameHistory]</a></li>
  <li><a href='taskIndex.jhtml'>获取任务大厅数据[taskIndex]</a></li>
  <hr/>
  <li><a href='getIndexData.jhtml'>获取首页数据[getIndexData]</a></li>
  <li><a href='getIndexGoodsList.jhtml?param={"order_by_name":"now_people","order_by_rule":"desc","pageNum":"1","limitNum":"2"}'>获取首页商品数据[getIndexGoodsList]</a></li>
  <li><a href='getGoodsTypeData.jhtml'>获取商品类别数据[getGoodsTypeData]</a></li>
  <li><a href='getGoodsTypeList.jhtml?param={"goodsTypeId":"100145"}'>获取商品类别下的夺宝数据[getGoodsTypeList]</a></li>
  <li><a href='getHotSearchData.jhtml'>获取商品热门搜索词数据[getHotSearchData]</a></li>
  <li><a href='getGoodsSearchList.jhtml?param={"searchKey":"争宝"}'>获取商品类别下的夺宝数据[getGoodsSearchList]</a></li>
  <li><a href='getGoodsInfoData.jhtml?param={"goods_fight_id":"522860622506","user_id":"100145"}'>获取夺宝商品的详细数据[getGoodsInfoData]</a></li>
  <li><a href='getFightRecordList.jhtml?param={"goods_fight_id":"522860622506","pageNum":"1","limitNum":"2"}'>获取夺宝商品购买纪录[getFightRecordList]</a></li>
  <li><a href='getGoodsFightIsGetLottery.jhtml?param={"goods_fight_id":"522860622506"}'>检查本期夺宝是否已经计算出中奖人[getGoodsFightIsGetLottery]</a></li>
  <li><a href='getMoreFightNum.jhtml?param={"goods_fight_id":"522860622506","user_id":"100145"}'>获取夺宝号码详情[getMoreFightNum]</a></li>
  <li><a href='countInfo.jhtml?goods_fight_id=522860622506'>获取计算详情页[countInfo]</a></li>
  <li><a href='getHistoryGoodsFightList.jhtml?param={"good_id":"522860622167","pageNum":"1","limitNum":"2"}'>获取往期揭晓数据[getHistoryGoodsFightList]</a></li>
  <li><a href='getGoodsFightIdByPeriod.jhtml?param={"good_id":"522860622167","good_period":"1"}'>查找获取是否有某期Id[getGoodsFightIdByPeriod]</a></li>
  <li><a href='getBaskList.jhtml?param={"goods_fight_id":"522860622506","target_user_id":"","pageNum":"1","limitNum":"2"}'>获取晒单数据[getBaskList]</a></li>
  <li><a href='getBaskContent.jhtml?param={"bask_id":"39"}'>获取晒单分享详情数据[getBaskContent]</a></li>
  <li><a href='commonProblemInfo.jhtml'>显示常见问题页面[commonProblemInfo]</a></li>
  <hr/>
  <li><a href='getOrderIndexData.jhtml?param={"goods_fight_ids":"522860622538","goods_buy_nums":"10","is_shop_cart":"n","user_id":"100145"}'>获取支付详情数据[getOrderIndexData]</a></li>
  <li><a href='paymentGoodsFight.jhtml?param={"payType":"money","goods_fight_ids":"522860622546","goods_buy_nums":"10","is_shop_cart":"n","user_id":"100145","ticket_send_id":""}'>支付夺宝[paymentGoodsFight]</a></li>
  <hr/>
  <li><a href='getWillDoGoodsList.jhtml?param={"pageNum":"1","limitNum":"2"}'>获取最新揭晓数据[getWillDoGoodsList]</a></li>
  <hr/>
  <li><a href='getShopCartList.jhtml?param={"user_id":"100145"}'>获取购物车数据[getShopCartList]</a></li>
  <li><a href='addShopCart.jhtml?param={"user_id":"100145","goods_ids":"522860622167,522860622168","goods_buy_nums":"1,1"}'>加入购物车[addShopCart]</a></li>
  <li><a href='updateShopCart.jhtml?param={"id":"100657","goods_buy_num":"10"}'>修改购物车[updateShopCart]</a></li>
  <li><a href='delShopCart.jhtml?param={"ids":"100657"}'>删除购物车[delShopCart]</a></li>
  <hr/>
  <li><a href='getNewsList.jhtml?param={"user_id":"100145","pageTab":"1","pageNum":"1","limitNum":"2"}'>获取赚钱列表[getNewsList]</a></li>
  <li><a href='newHelpIndexInfo.jhtml'>获取赚钱帮助页面[newHelpIndexInfo]</a></li>
  <li><a href='saveNewsShare.jhtml?param={"news_id":"105","user_id":"100145"}'>分享文章后回调[saveNewsShare]</a></li>
  <hr/>
  <li><a href='uploadImg.jhtml'>上传图片并获取返回图片地址[uploadImg]</a></li>
  <li><a href='showDownload.jhtml'>app区分下载[showDownload]</a></li>
</ol>

</body>
</html>
