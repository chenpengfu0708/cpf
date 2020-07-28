var Mylist = new Array();

//token
var token = "";

//是否已经提示过完成
var finish = 0;

$(document).ready(function () {

    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = $chars.length;
    for (i = 0; i < 32; i++) {
        //0~32的整数
        token += $chars.charAt(Math.floor(Math.random() * (maxPos+1)));
    }
    change(-1, -1, 0);
});

function test(a){
    var td = document.getElementById(a);
    var tdFirst = a.substring(0,1);
    var tdSecond = a.substring(1,2);

    if (finish === 0) {
        change(tdFirst, tdSecond, 1);
    } else {
        alert("已经完成当前关卡啦！");
    }

};

function change(x, y, f) {
    var adata = {
        "x":x,
        "y":y,
        "token" : token
    };
    var data = JSON.stringify(adata);
    $.ajax({
        type : "POST",
        dataType: "json",
        contentType : "application/json",
        data : data,
        async: false ,
        url : "localhost:8888/ew/con/hbdd",
        success : function(data) {
            if (data.code === 0){
                for (let i = 0; i < 8; i++) {
                    for (let j = 0; j < 8; j++) {
                        var inx = ""+i+""+j;
                        var td = document.getElementById(inx);
                        if (data.data[inx] === "1") {
                            td.innerHTML = '<img src="../js/img//black.png" style="width: 50px;height: 50px" />';
                        } else {
                            td.innerHTML = '<img src="../js/img//white.png" style="width: 50px;height: 50px" />';
                        }
                    }
                }
                if (f === 1) {
                    checkStatus();
                }
            }
        },
        error : function(e) {
            console.log(e);
        }
    });
}

function checkStatus() {
    var adata = {
        "token" : token
    };
    var data = JSON.stringify(adata);
    $.ajax({
        type : "POST",
        dataType: "json",
        contentType : "application/json",
        data : data,
        async: true ,
        url : "localhost:8888/ew/con/checkStatus",
        success : function(data) {
            if (data.code === 0){
                if (data.finish === 1) {
                    finish = 1;
                    alert("wow~完成啦！！");
                }
            }
        },
        error : function(e) {
            console.log(e);
        }
    });
}