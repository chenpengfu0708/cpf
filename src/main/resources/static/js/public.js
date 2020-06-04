function IEVersion() {
    var userAgent = window.navigator.userAgent; //取得浏览器的userAgent字符串
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion == 7) {
            return 7;
        } else if(fIEVersion == 8) {
            return 8;
        } else if(fIEVersion == 9) {
            return 9;
        } else if(fIEVersion == 10) {
            return 10;
        } else {
            return 6;//IE版本<=7
        }
    } else if(isEdge) {
        return 'edge';//edge
    } else if(isIE11) {
        return 11; //IE11
    }else{
        return -1;//不是ie浏览器
    }
}

if(IEVersion()!=8 && IEVersion()!= 7){
    (function(designWidth, maxWidth) {
        var doc = document,
            win = window,
            docEl = doc.documentElement,
            remStyle = document.createElement("style"),
            tid;

        if (docEl.firstElementChild) {
            docEl.firstElementChild.appendChild(remStyle);
        } else {
            var wrap = doc.createElement("div");
            wrap.appendChild(remStyle);
            doc.write(wrap.innerHTML);
            wrap = null;
        }
        //要等 wiewport 设置好后才能执行 refreshRem，不然 refreshRem 会执行2次；
        refreshRem( designWidth ,maxWidth ,remStyle ,docEl)

        win.addEventListener("resize", function() {
            clearTimeout(tid); //防止执行两次
            tid = setTimeout(refreshRem, 300);
        }, false);

        win.addEventListener("pageshow", function(e) {
            if (e.persisted) { // 浏览器后退的时候重新计算
                clearTimeout(tid);
                tid = setTimeout(refreshRem, 300);
            }
        }, false);

        if (doc.readyState === "complete") {
            doc.body.style.fontSize = "16px";
        } else {
            doc.addEventListener("DOMContentLoaded", function(e) {
                doc.body.style.fontSize = "16px";
            }, false);
        }
    })(750, 750);
}

function refreshRem( designWidth ,maxWidth ,remStyle ,docEl) {
    var width = docEl.getBoundingClientRect().width;
    maxWidth = maxWidth || 540;
    width > maxWidth && (width = maxWidth);
    var rem = width * 100 / designWidth;
    remStyle.innerHTML = 'html{font-size:' + rem + 'px;}';
}

/*$(window).ready(function(){
    navAnimate();

});*/
/*let navAnimate = () => {
    let $offset_x = $(".main-wrap").offset().left;
    $(".header-nav-btn").click(function () {
        let $margin_x = $(".main-wrap").offset().left;

        if ($margin_x != 0){
            $(".main-wrap").stop().animate({
                "margin-left": 0,
            },500)
        }else{
            $(".main-wrap").stop().animate({
                "margin-left": $offset_x,
            },500)
        }
    })
}

let categoryNavCheck = (tab,item) => {
    let $tab = tab;
    let $item = item;
    $tab.click(function(){
        let index = $(this).index();
        $(this).addClass("current").siblings().removeClass("current");
        $item.eq(index).addClass("current").siblings().removeClass("current");
    });
}*/


function is_weixin() {
    if (/MicroMessenger/i.test(navigator.userAgent)) {
        return true
    } else {
        return false
    }
}
function is_alipay() {
    if (/AlipayClient/i.test(navigator.userAgent)) {
        return true
    } else {
        return false
    }
}

function is_not_wexin_alipay() {
    if (!is_alipay() && !is_weixin()) {
        return true
    }
    return false
}

function is_android() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/(Android|SymbianOS)/i)) {
        return true
    } else {
        return false
    }
}

function is_ios() {
    var ua = navigator.userAgent.toLowerCase();
    if (/iphone|ipad|ipod/.test(ua)) {
        return true
    } else {
        return false
    }
}

