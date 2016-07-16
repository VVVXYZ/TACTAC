<%@ tag import="com.trio.breakFast.util.PrettyTimeUtil" %>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="date" type="java.util.Date" required="true" description="时间" %>
<%--
  ~ Copyright 2013-2014 the original author or authors.
  ~ Licensed under the Apache License, Version 2.0 (the "License").
  ~
  ~ Re-developed by Sima Studio. All rights reserved.
  --%>

<%=PrettyTimeUtil.prettyTime(date)%>