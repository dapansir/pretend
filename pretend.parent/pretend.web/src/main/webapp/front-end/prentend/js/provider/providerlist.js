$(document).ready(function () {

    $("#providerlist").bootstrapTable({
        url:"data.json",
        columns:[
            {field:"no"},
            {field:"class"},
            {field:"ip"},
            {field:"state","class":"label label-success"},
            {field:"op1","class":"btn btn-default btn-xs"},
            {field:"op2","class":"btn btn-success btn-xs"},
            {field:"op3","class":"btn btn-danger btn-xs"}
        ]
    })
});
