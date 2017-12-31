var total = 0;
var orders;
var ordersFix;
$(document).ready (function() {
    showTableProducts();
    monthlyGL();
});

$(function(){
    $('#weeklyGL').click(function(){
        reset();
        weeklyGL();
    });
});
$(function(){
    $('#monthlyGL').click(function(){
        reset();
        monthlyGL();
    });
});
$(function(){
    $('#yearlyGL').click(function(){
        reset();
        yearlyGL();
    });
});

function reset() {
    total = 0;
    $('#paymentsTable tbody').html("");
}

function showTableProducts() {
    var table = $('#produkTable').DataTable({
        "sAjaxSource": "/api/produk/",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_produk",
                "render": function (mData) {
                    return '<img style="width: 50px" src="assets/image/'+mData+'"/>';
                }},
            { "mData": "id_produk"},
            { "mData": "nama_produk" },
            { "mData": "stok_total" },
            { "mData": "kategori" },
            { "mData": "terjual"}
        ]
    });
}

function weeklyGL() {
    $.ajax({
        url: '/api/generalLedgerWeek',
        type: 'GET',
        dataType: 'json',
        success: function (order) {
            orders = order;
            orderFix(orders, "minggu");
            // orderListSuccess(orders);
        }
    });
}

function monthlyGL() {
    $.ajax({
        url: '/api/generalLedgerMonth',
        type: 'GET',
        dataType: 'json',
        success: function (order) {
            orders = order;
            orderFix(orders, "bulan");
            // orderListSuccess(orders);
        }
    });
}

function yearlyGL() {
    $.ajax({
        url: '/api/generalLedgerYear',
        type: 'GET',
        dataType: 'json',
        success: function (order) {
            orders = order;
            orderFix(orders, "tahun");
            // orderListSuccess(orders);
        }
    });
}

function orderFix(orders, waktu){
    var date = 0;
    $.each(orders, function (index,order) {
        if(date == 0){
            date = order.created_at;
            orderAddList(order);
            calculateTotal(order, waktu);
        } else {
            if (date == order.created_at){
                orderAddList(order);
                calculateTotal(order, waktu);
            }
        }
    });
}

function orderListSuccess(orders) {
    $.each(orders, function (index,order) {
        orderAddList(order);
    })
}

function orderAddList(order) {
    $('#paymentsTable').append(
        orderAddListShow(order)
    );
}

function orderAddListShow(order) {
    var row =
        '<tr>' +
        '<td>' + order.member.nama_member + '</td>' +
        '<td>' + order.id_penjualan + '</td>' +
        '<td>' + order.pengguna.nama_pengguna + '</td>' +
        '<td>' + order.diskon + '%</td>' +
        '<td>Rp. <span class="text-right">'+ order.total_penjualan + '</span></td>' +
        '</tr>';
    return row;
}

function calculateTotal(order, waktu) {
    total += order.total_penjualan;
    $("#ketWaktu").html(waktu);
    $("#totalShow").text('Rp. '+total);

}


// function showTablePayments () {
//     var table = $('#paymentsTable').DataTable({
//         "sAjaxSource": "/api/order/day",
//         "sAjaxDataProp": "",
//         "order": [[ 0, "asc" ]],
//         "columns": [
//             { "mData": "member.nama_member"},
//             { "mData": "id_penjualan"},
//             { "mData": "pengguna.nama_pengguna"},
//             { "mData": "created_at",
//                 "render": function (mData) {
//                     var date = new Date(mData);
//                     var month = date.getMonth() + 1;
//                     return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
//                 }},
//             { "mData": "total_penjualan"}
//         ]
//     });
// }
//
// function totalPayments(){
//     var total =0;
//     $.ajax({
//         url: '/api/order/day',
//         type: 'GET',
//         dataType: 'json',
//         success: function (transaksi) {
//             $.each(transaksi, function (index, t) {
//                 total += t.total_penjualan;
//             });
//             $("#totalShow").text('Rp. '+total);
//         }
//     });
// }