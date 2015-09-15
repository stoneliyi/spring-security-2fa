<%@ page import="com.jasper.users.auth.TOTPAuthenticator" %>
<%--
  Created by IntelliJ IDEA.
  User: yuanhui.luo
  Date: 9/10/15
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>

<%
    TOTPAuthenticator totpAuthenticator = new TOTPAuthenticator();
    String shareSecret = "ORXXI4DTMVRXEZLU";
    long[] rst = totpAuthenticator.getCode(shareSecret);
%>

<script type="text/javascript">
    var myVar=setInterval(function () {myTimer()}, 1000);
    var counter=<%=rst[1]%>;
    function myTimer() {
        if (counter > 0) {
            counter--;
        }
        document.getElementById("demo").innerHTML = counter;
    }
</script>

<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td {
        padding: 5px;
        text-align: middle;
    }
    table#t01 {
        width: 300px;
        background-color: #f1f1c1;
    }


</style>
<body>
<br/>
<div align="center">
    <table cellspacing="0" cellpadding="0" border="0" style="border:0px">
        <tr>
            <td style="border:0px"><img src="res/images/logo.png"></td>
            <td style="border:0px" valign="bottom"><h2>TOTP 2FA Code</h2></td>
        </tr>
    </table>
    <table>
        <table id="t01", width="300">
            <tr>
                <th>
                    <%
                        out.println(rst[0]);
                    %>
                </th>
            </tr>
            <tr>
                <th>
                    <%
                        out.println("expired in ");
                    %>
                    <span id="demo"></span>
                    <%
                        out.println("second");
                    %>
                </th>
            </tr>
        </table>
    </table>
</div>


</body>
</html>

