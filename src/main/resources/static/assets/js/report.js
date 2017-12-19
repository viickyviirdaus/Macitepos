var total = 0;
$(document).ready (function() {
    showTableProducts();
    showTablePayments();
    totalPayments()
});

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

function showTablePayments () {
    var table = $('#paymentsTable').DataTable({
        "sAjaxSource": "/api/order/day",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [
            { "mData": "member.nama_member"},
            { "mData": "id_penjualan" },
            { "mData": "pengguna.nama_pengguna" },
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "total_penjualan"},
        ]
    });
}

function totalPayments(){
    var total =0;
    $.ajax({
        url: '/api/order/day',
        type: 'GET',
        dataType: 'json',
        success: function (transaksi) {
            $.each(transaksi, function (index, t) {
                total += t.total_penjualan;
            });
            $("#totalShow").text('Rp. '+total);
        }
    });
}