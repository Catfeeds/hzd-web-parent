function goTopEx(){function o(){return document.documentElement.scrollTop+document.body.scrollTop}function d(o){document.documentElement.scrollTop?document.documentElement.scrollTop=o:document.body.scrollTop=o}var n=document.getElementById("GotoTop");window.onscroll=function(){o()>0?n.style.display=n.style.display="block":n.style.display="none"},n.onclick=function(){function n(){d(o()/2),o()<1&&clearInterval(p)}var p=setInterval(n,1)}}function newhzdjrPopup(o){var d="<div class='hzdjrPopup'><div class='hzdjrPopupBg'></div><div class='hzdjrPopupCC'>"+o+"</div></div>";$("body").append(d);var n=parseInt($(".hzdjrPopupCC").css("padding-left")),p=parseInt($(".hzdjrPopupCC").css("padding-top"));$(".hzdjrPopup").width($(window).width()),$(".hzdjrPopup").height($(window).height()),$(".hzdjrPopupBg").width($(window).width()),$(".hzdjrPopupBg").height($(window).height()),$(".hzdjrPopupCC").css("margin-left",-($(".hzdjrPopupCC").width()/2)-n),$(".hzdjrPopupCC").css("margin-top",-($(".hzdjrPopupCC").height()/2)-p),setTimeout(function(){$(".hzdjrPopup").css("display","none"),$(".hzdjrPopup").remove()},2e3)}document.getElementById("GotoTop")&&goTopEx();