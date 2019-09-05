//var ColumnFormatter = window.ColumnFormatter || {};
function ColumnFormatter(){
	this.columnName = "";
	this.columnValue = "";
	this.row = {};
	this.rowIndex = 0;

	var ele ={classes:"label label-default",columnType:"label",text:""};
	
	this.config = function(config){
		ele.classes = config.classes;
		ele.columnType = config.columnType;
		ele.text = 	config.text;
	}
	
	
}
