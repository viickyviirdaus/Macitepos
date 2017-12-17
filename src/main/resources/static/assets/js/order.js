$(document).ready (function() {
    var table = $('#orderTable').DataTable({
        "sAjaxSource": "/api/order",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "id_penjualan"},
            { label : "member", "mData": "member.nama_member"},
            { "mData": "diskon" },
            { "mData": "total_barang_dijual" },
            { "mData": "total_penjualan" },
            { "mData": "pembayaran_penjualan" },
            { "mData": "kembalian_penjualan" },
            { label : "pengguna", "mData": "pengguna.nama_pengguna"},
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }},
        ]
    })
});
