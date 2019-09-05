/**
 *
 * @param value 值
 * @param row
 * @param index
 * @param field
 * @returns {string}
 */
function operatorFormatter(value,row,index,field){

    var classes = "btn btn-xs ";
    if(field == 'op1'){
        classes += "btn-default";
    }else if(field == 'op2'){
        classes += "btn-success";
        if(row.state == 1){
            classes += " disabled";
        }
    }else if(field == 'op3'){
        classes += "btn-danger";
    }
    return spanFormatter(value,classes);
}

function stateFormatter(value,row,index,field){
    var classes = "label ";
    if(value == 1 || value==true){
    	value = "on";
        classes += "label-success";
    }else if(value == 2 || value== false){
        classes += "label-danger";
        value = "off";
    }
    if(field == "running"){
        value = "running";
    }
    return spanFormatter(value,classes);
}

function spanFormatter(text,classes) {
	if(undefined === classes || null == classes || "" == classes){
		return simpleSpanFormatter(text);
	}
    return "<span class='"+classes+"' >"+text+"</span>";
}

function simpleSpanFormatter(text) {
    return "<span>"+text+"</span>";
}

function textFormatter(text){
	return text;
}




