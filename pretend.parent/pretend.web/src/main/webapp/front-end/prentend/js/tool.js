var successState = [1,true];
var failState = [2,false];
var dangerState = [3,"danger"];

function contains(arr,value) {

    var temp = arr.find(function (element) {
        if(element == value){
            return true;
        }
        return  false;
    });

    if(undefined === temp){
        return false;
    }

    return true;
}

function labelFormatter(value,row,index,field) {
    var classes = "label ";
    var result = parseValue(value,field);
    var temp = "off";
    if(result.isSuccess() || result.isDanger()){
        temp = "on";
    }

    return spanFormatter(temp,getClasses(value,field,"label"));
}

function buttonFormatter(value,row,index,field) {
    var classes = "btn ";
    var result = parseValue(value,field);
    value = "on";
    if(result.isSuccess()){
        classes += "btn-success";
    }else{
        if("fail" == result.getState()){
            classes += "btn-danger";
        }else if("danger" == result.getState()){
            classes += "btn-warnning";
        }else{
            classes += "btn-default";
        }
    }

    return spanFormatter(value,classes);
}

function getClasses(value,field,ele) {
    var prefix = getClassesPrefix(ele);
    var result = parseValue(value,field);
    if(result.isSuccess()){
        prefix += prefix+"-success";
    }else{
        if("fail" == result.getState()){
            prefix += " "+prefix+"-danger";
        }else if("danger" == result.getState()){
            prefix += " "+prefix+"-warnning";
        }else{
            prefix += " "+prefix+"-default";
        }
    }
    return prefix;
}

function getClassesPrefix(ele) {
    if("label" ==ele){
       return  "label";
    }
    if("button" == ele){
        return "btn";
    }
}

function parseValue(value,field) {
    var result = new ParseResult();
    result.setName(field);
    result.setSuccess(false);
    if(contains(successState,value)){
        result.setSuccess(true);
        result.setState("success");
    }else if(contains(failState,value)){
        result.setState("fail");
    }else if(contains(dangerState,value)){
        result.setState("danger");
    }
    return result;
}

function ParseResult() {
    this.name = "";
    this.succcess = false;
    this.state = "";

    this.setName = function (name) {
        this.name = name;
    }

    this.setSuccess = function (success) {
        this.succcess = success;
    }

    this.setState = function (state) {
        this.state = state;
    }

    this.isSuccess = function () {
        return this.succcess;
    }

    this.getState = function () {
        return this.state;
    }

    this.getName = function () {
        return this.name;
    }
    this.isDanger = function () {
        return this.isSuccess() && "danger" == this.getState();
    }
}

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
    return "<span class='"+classes+"' >"+text+"</span>";
}

function simpleFormatter(text) {
    return "<span>"+text+"</span>";
}


function toMethodList(value,row,index,field){
	
	var classes = "btn btn-xs btn-success";
	
	return "<a class='btn btn-xs btn-success' href='methodList.html?name="+value+"'>查看</a>";
}

function toMethodDetail(value,row,index,field){
	
	var classes = "btn btn-xs btn-success";
	
	return "<a class='btn btn-xs btn-success' href='invoke_detail.html?id="+value+"'>查看</a>";
}


function findParameter(location,key){
	var index = location.indexOf("?");
	var parameter = location.substring(index+1);
	var params = parameter.split("&");
	var temp = null;
	var tempArr = null;
	for(var i=0;i<params.length;i++){
		var temp = params[i].split("=");
		if(temp.length == 2 && key == temp[0]){
			return temp[1];
		}
	}
	return null;
}




if(!$.fn.serializeObject){
	$.fn.serializeObject = function(){
		var result = {};
	      var a = this.serializeArray();
	      $.each(a, function() {
	          if (result[this.name] !== undefined) {
	              if (!result[this.name].push) {
	            	  result[this.name] = [result[this.name]];
	              }
	              result[this.name].push(this.value || '');
	          } else {
	        	  result[this.name] = this.value || '';
	          }
	      });
	      return result;
	}
}

