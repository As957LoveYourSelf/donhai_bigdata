//输出访问的时间点
var nowDate;
var nowTime;
//输出用户使用的浏览器
var explorer = window.navigator.userAgent;
var browser = "";
//IE
if (explorer.indexOf("MSIE") >= 0) {
    browser = "IE";
}
//Edge
else if (explorer.indexOf("Edg") >= 0) {
    browser = "Edge";
}
//Firefox
else if (explorer.indexOf("Firefox") >= 0) {
    browser = "Firefox";
}
//Chrome
else if(explorer.indexOf("Chrome") >= 0){
    browser = "Chrome";
}
//Opera
else if(explorer.indexOf("Opera") >= 0){
    browser = "Opera";
}
//Safari
else if(explorer.indexOf("Safari") >= 0){
    browser = "Safari";
}
window.onload=function() {
    //获取新的时间点
    nowDate = new Date();
    nowTime=nowDate.toLocaleString('chinese',{hour12:false});
    $.ajax({
            type:"GET",
            url:"http://localhost:8080/user/getLogInfo",
            data:{"IP":returnCitySN["cip"],
                "address":returnCitySN["cname"],
                "accessTime":nowTime,
                "browser":browser},
            dataType:"json"
        }
    );
}