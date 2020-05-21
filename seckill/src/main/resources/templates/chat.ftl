<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>


    <link rel="stylesheet" href="/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/build.css"/>
    <link rel="stylesheet" type="text/css" href="/css/qq.css"/>

</head>
<body>

<div class="qqBox">
    <div class="context">
        <div class="conLeft">
            <ul id="userList">

            </ul>
        </div>
        <div class="conRight">
            <div class="Righthead">
                <div class="headName">${username}</div>
            </div>
            <div class="RightCont">
                <ul class="newsList" id="message">

                </ul>
            </div>
            <div class="RightFoot">


                    <textarea id="dope"
                              style="width: 100%;height: 100%; border: none;outline: none;padding:20px 0 0 3%;" name=""
                              rows="" cols=""></textarea>
                    <button class="sendBtn" onclick="send()">发送(s)</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    var webSocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        webSocket = new WebSocket('ws://localhost:8087/webSocket?username=' + '${username}');
    } else {
        alert("当前浏览器不支持WebSocket");
    }

    //连接发生错误的回调方法
    webSocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误！");
    }

    webSocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功！")
    }

    webSocket.onmessage = function (event) {

        $("#userList").html("");
        eval("var msg=" + event.data + ";");

        if (undefined != msg.content)
            setMessageInnerHTML(msg.content);

        if (undefined != msg.names) {
            $.each(msg.names, function (key, value) {
                var htmlstr = '<li>'
                        + '<div class="checkbox checkbox-success checkbox-inline">'
                        + '<input type="checkbox" class="styled" id="'+key+'" value="'+key+'" checked>'
                        + '<label for="'+key+'"></label>'
                        + '</div>'
                        + '<div class="liLeft"><img src="img/2.png"/></div>'
                        + '<div class="liRight">'
                        + '<span class="intername">'+value+'</span>'
                        + '</div>'
                        + '</li>'

                $("#userList").append(htmlstr);
            })
        }
    }

    webSocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    window.onbeforeunload = function () {
        closeWebSocket();
    }

    function closeWebSocket() {
        webSocket.close();
    }

    function send() {

        var time=new Date().toLocaleString();
        var message = $("#dope").val();
        $("#dope").val("");
        var htmlstr='<li><div class="answerHead"><img src="img/2.png"></div><div class="answers">'
            +'${username}'+'  '+time+'<br/>'+message+'</div></li>';
        $("#message").append(htmlstr);

        var ss = $("#userList :checked");
        var to = "";
        $.each(ss, function (key, value) {
            to += value.getAttribute("value") + "-";
        })
        console.info(to);

        if (ss.size() == 0) {
            var obj = {
                msg: message,
                type: 1
            }
        } else {
            var obj = {
                to: to,
                msg: message,
                type: 2
            }
        }

        var msg = JSON.stringify(obj);

        webSocket.send(msg);

    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {

    var msg='<li>'
        +'<div class="nesHead">'
        +'<img src="img/2.png">'
        +' </div>'
        +'<div class="news">'
        + innerHTML
        +'</div>'
        +'</li>';

    $("#message").append(msg);

    }
</script>

</body>
</html>