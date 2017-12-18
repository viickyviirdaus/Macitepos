var total = 0;
$(document).ready (function() {
    showTableProducts();
    // showTablePayments();
});

function showTableProducts() {
    var table = $('#produkTable').DataTable({
        "sAjaxSource": "/api/produk",
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
    var table = $('#produkTable').DataTable({
        "sAjaxSource": "/api/produk",
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
            { "mData": "terjual"},
        ]
    });
}