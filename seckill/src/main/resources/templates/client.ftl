<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WebSocket</title>

</head>
<body>
<div id="userList"></div>
    <input id="text" type="text"/>
    <button onclick="send()">发送消息</button>
    <hr/>
<button onclick="closeWebSocket()">关闭WebSockeet连接</button>
<hr/>
<div id="message"></div>

</body>
</html>
<script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    var  webSocket=null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window){
        webSocket=new WebSocket('ws://localhost:8087/webSocket?username='+'${username}');
    } else{
        alert("当前浏览器不支持WebSocket");
    }

    //连接发生错误的回调方法
    webSocket.onerror=function () {
        setMessageInnerHTML("WebSocket连接发生错误！");
    }

    webSocket.onopen=function () {
        setMessageInnerHTML("WebSocket连接成功！")
    }

    webSocket.onmessage=function (event) {

        $("#userList").html("");
        eval("var msg="+event.data+";");

        if (undefined!=msg.content)
        setMessageInnerHTML(msg.content);
        
        if (undefined!=msg.names) {
            $.each(msg.names,function (key,value) {
                $("#userList").append("<input type='checkbox' value='"+key+"'  />"+value+"<br/>");
            })
        }
    }

    webSocket.onclose=function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    window.onbeforeunload=function () {
        closeWebSocket();
    }

    function closeWebSocket() {
        webSocket.close();
    }

    function send() {
        var ss=$("#userList :checked");
        var to="";
        $.each(ss,function (key,value) {
            to+=value.getAttribute("value")+"-";
        })
        console.info(to);
        var message=$("#text").val();
        if (ss.size()==0){
            var obj={
                msg:message,
                type:1
            }
        }else {
            var obj={
                to:to,
                msg:message,
                type:2
            }
        }

       var msg =JSON.stringify(obj);

        webSocket.send(msg);
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML+=innerHTML+'<br/>';
    }
</script>