$(document).ready(function () {

    // load-success.bs.table
    $('#providerlist').on('load-success.bs.table', megerTable);

    function megerTable(data) {
        $('#providerlist').bootstrapTable('mergeCells',{index:-2,field:"class",colspan:2});
    }
});

/**
 *
 * @param value
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
    return joinSpan(classes,value);
}

function stateFormatter(value,row,index,field){
    var classes = "label ";
    if(value == 1){
    	value = "on";
        classes += "label-success";
    }else if(value == 2){
        classes += "label-danger";
        value = "off";
    }
    return joinSpan(classes,value);
}

function joinSpan(classes,text) {
    return "<span class='"+classes+"' >"+text+"</span>";
}