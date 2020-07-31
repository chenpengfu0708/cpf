var Mylist = new Array();

//token
var token = "";

//是否已经提示过完成
var finish = 0;

var numb = 3;

$(document).ready(function () {

    var num = $("#num").val();
    if (num == "new") {
        numb = 1;
    } else if (num == "primary") {
        numb = 3;
    } else if (num == "middle") {
        numb = 5;
    } else if (num == "high") {
        numb = 8;
    }

    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = $chars.length;
    for (i = 0; i < 32; i++) {
        //0~32的整数
        token += $chars.charAt(Math.floor(Math.random() * (maxPos + 1)));
    }
    change(-1, -1, -1, 0);
});

function test(a) {
    var td = document.getElementById(a);
    var plane = a.substring(0, 1);
    var tdFirst = a.substring(1, 2);
    var tdSecond = a.substring(2, 3);

    if (finish === 0) {
        change(plane, tdFirst, tdSecond, 1);
    } else {
        alert("已经完成当前关卡啦！");
    }

};

function change(plane, x, y, f) {
    var adata = {
        "num":numb,
        "plane": plane,
        "x": x,
        "y": y,
        "token": token
    };
    var data = JSON.stringify(adata);
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: data,
        async: false,
        url: "con/swhbdd",     //请求的url。与后端@Request Mapping注解中的值一致。
        success: function (data) {		      //请求成功的回调函数
            if (data.code === 0) {
                for (let p = 0; p < 6; p++) {
                    for (let i = 0; i < 8; i++) {
                        for (let j = 0; j < 8; j++) {
                            var inx = "" + p + "" + i + "" + j;
                            var td = document.getElementById(inx);
                            console.log("inx = " + inx + ",td = " + td.id + ",value = " + data.data[inx]);
                            if (data.data[inx] == "1") {
                                td.innerHTML = '<img src="../js/img//black.png" style="width: 22px;height: 20px" />';
                            } else {
                                td.innerHTML = '<img src="../js/img//white.png" style="width: 22px;height: 20px" />';
                            }
                        }
                    }
                }
                if (f === 1) {
                    checkStatus();
                }
            }
        },
        error: function (e) {
            console.log(e);
        }
    });
}

function checkStatus() {
    var adata = {
        "token": token
    };
    var data = JSON.stringify(adata);
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: data,
        async: true,
        url: "con/swcheckStatus",     //请求的url。与后端@Request Mapping注解中的值一致。
        success: function (data) {		      //请求成功的回调函数
            if (data.code === 0) {
                if (data.finish === 1) {
                    finish = 1;
                    alert("wow~完成啦！！");
                }
            }
        },
        error: function (e) {
            console.log(e);
        }
    });
}