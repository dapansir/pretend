function hideUl(ele){
	$(ele).next().toggle(800);
    changeGlyphiconMenu(ele);
}

function changeGlyphiconMenu(ele){
    var temp = $(ele).find(".glyphicon-menu-down");
    if(temp.length == 0){
        removeLeft(ele);
    }else{
        removeDown(ele);
    }

}

function removeLeft(ele){
    $(ele).find(".glyphicon-menu-left").addClass("glyphicon-menu-down");
    $(ele).find(".glyphicon-menu-left").removeClass("glyphicon-menu-left");
}

function removeDown(ele){
    $(ele).find(".glyphicon-menu-down").addClass("glyphicon-menu-left");
    $(ele).find(".glyphicon-menu-down").removeClass("glyphicon-menu-down");
}

$(document).ready(function () {
    $("aside").find("[name='treenodecontrol']").on("click",function () {
        hideUl(this);
    });
    $("aside").find("ul.nav-sidebar").find("li").on("click",function () {
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
        $(this).siblings().each(function (index,item) {
            removeDown(item);
            $(item).find("[name='treenodecontrol']").next().hide(800);
        });
    });
   
});