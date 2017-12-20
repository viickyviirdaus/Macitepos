$(document).ready (function() {
    var table = $('#produkTable').DataTable({
        "sAjaxSource": "/api/produk",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_produk",
                "render": function (mData) {
                    return '<img style="width: 100%" src="assets/image/product/'+mData+'"/>';
                }},
            { "mData": "id_produk"},
            { "mData": "nama_produk" },
            { "mData": "harga_penjualan" },
            { "mData": "stok_total" },
            { "mData": "kategori" },
            { "mData": "last_updated",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "updated_by"},
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }},
        ]
    })
});

$(document).ready (function() {
    var table = $('#productTable').DataTable({
        "sAjaxSource": "/api/produk",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_produk",
                 "render": function (mData) {
                    return '<img style="width: 100%" src="assets/image/product/'+mData+'"/>';
                }},
            { "mData": "id_produk"},
            { "mData": "nama_produk" },
            { "mData": "harga_penjualan" },
            { "mData": "stok_toko" },
            { "mData": "stok_total" },
            { "mData": "stok_ulang" },
            { "mData": "kategori" },
            { "mData": "no_rak_toko" },
            { "mData": "no_rak_gudang" },
            { "mData": "harga_beli" },
            { "mData": "harga_penjualan" },
            { "mData": "status_produk" },
            { "mData": "last_updated",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "updated_by"},
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }},
        ]
    })
});
