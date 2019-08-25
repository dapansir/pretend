$(document).ready(function () {

    // load-success.bs.table
    $('#providerlist').on('load-success.bs.table', megerTable);

    function megerTable(data) {
        $('#providerlist').bootstrapTable('mergeCells',{index:-2,field:"class",colspan:2});
    }
});

