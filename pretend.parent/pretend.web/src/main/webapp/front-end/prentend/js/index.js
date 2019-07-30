/**
 * 
 */

var left = window.location.href;

$.post("../menu/left.do",null,function(res){
	$("span[name='user_panel']").html(res.userPanel);
	$.each(res.menuSideBar,function(index,item){
		$(".sidebar-menu").append(parentMenu(index,item.label,item.childBar));
	})
});

var first = false;
function parentMenu(index,label,childBar){
	var li = '<li  class="';
	if(index == 0){
		first = true;
		li += 'active ';
	}
	li += 'treeview">';
	li += '	<a href="#"> ';
	li += '		<i class="fa fa-dashboard"></i> ';
	li += '		<span name="label">'+label+'</span>';
	li += '	 	<span class="pull-right-container">';
	li += '      	    <i class="fa fa-angle-left pull-right"></i>';
	li += '       </span>';
	li += '  </a>';
	li += ' <ul class="treeview-menu" name="treeview-menu">';
	$.each(childBar,function(index,bar){
		li += menuBar(index, bar);
	});
	li += ' </ul>';
	li += '</li>';
	return li;
}

function menuBar(index,item){
	var li = '<li ';
	if(index == 0 && first == true){
		 li += ' class="active"';
		 first = false;
	}
	li += '><a href="'+item.href+'"><i class="fa fa-circle-o"></i> '+item.name+'</a></li>';
	return li;
}