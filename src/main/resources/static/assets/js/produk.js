$(document).ready (function() {
    var table = $('#produkTable').DataTable({
        "sAjaxSource": "/api/produk",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "id_produk"},
            { "mData": "nama_produk" },
            { "mData": "harga_penjualan" },
            { "mData": "stok_total" },
            { "mData": "kategori" },
            { "mData": "last_updated" },
            { "mData": "updated_by" },
            { "mData": "created_at" }

        ]
    })
});