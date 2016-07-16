<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="sidebar-shortcuts" id="sidebar-shortcuts">
  <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
    <shiro:hasPermission name="user:*">
      <button class="btn btn-success no-border" id="userBtn" title="<spring:message code='global.navMenu.user' />"
              onclick="javascript:window.location.href='${ctx}/user'">
        <i class="icon-user"></i>
      </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="role:*">
      <button class="btn btn-info no-border" id="roleBtn" title="角色管理"
              onclick="javascript:window.location.href='${ctx}/role'">
        <i class="icon-link" ></i>
      </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="resource:*">
      <button class="btn btn-warning no-border" id="resourceBtn" title="资源管理"
              onclick="javascript:window.location.href='${ctx}/resource'">
        <i class="icon-cogs"></i>
      </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="organization:*">
      <button class="btn btn-danger no-border" id="orgBtn" title="组织管理"
              onclick="javascript:window.location.href='${ctx}/organization'">
        <i class="icon-sitemap"></i>
      </button>
    </shiro:hasPermission>
  </div>

  <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
    <shiro:hasPermission name="user:*">
      <span class="btn btn-success"></span>
    </shiro:hasPermission>
    <shiro:hasPermission name="role:*">
      <span class="btn btn-info"></span>
    </shiro:hasPermission>
    <shiro:hasPermission name="resource:*">
      <span class="btn btn-warning"></span>
    </shiro:hasPermission>
    <shiro:hasPermission name="organization:*">
      <span class="btn btn-danger"></span>
    </shiro:hasPermission>
  </div>
</div>

<!-- #sidebar-shortcuts -->
<div class="sidebar-collapse" id="sidebar-collapse">
  <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
</div>