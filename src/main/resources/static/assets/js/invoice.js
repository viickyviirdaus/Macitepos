var allOrder;

$(document).ready(function () {
    $.ajax({
        url: '/api/order/',
        type: 'GET',
        dataType: 'json',
        success: function (order) {
            allOrder = order;
            showInvoice(allOrder);
        }
    });

    window.print();
//           window.close();
});

function showInvoice(allOrder) {

}