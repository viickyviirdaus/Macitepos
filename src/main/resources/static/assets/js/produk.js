$(document).ready (function() {
    var table = $('#produkTable').DataTable({
        "sAjaxSource": "/api/produk",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_produk",
                "render": function (mData) {
                    return '<img style="width: 100px" src="image/product/'+mData+'"/>';
                }},
            { "mData": "id_produk"},
            { "mData": "nama_produk" },
            { "mData": "harga_penjualan" },
            { "mData": "stok_total" },
            { "mData": "kategori" },
            { "mData": "last_updated",
                "render": function (mData) {
                    var date = new Date(mData);
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "updated_by"},
            { "mData": "created_at",
                "render": function (mData) {
                    var date1 = new Date(mData);
                    return date1.getDate() + "/" + ( date1.getMonth() + 1) + "/" + date1.getFullYear() +" "+ date1.getHours()+":"+ date1.getMinutes()+":"+ date1.getSeconds() ;
                }},
        ]
    })

    var tablea = $('#productTableA').DataTable({
        destroy: true,
        "sAjaxSource": "/api/produkApproved",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_produk",
                 "render": function (mData) {
                    return '<img style="width: 100px" src="image/product/'+mData+'"/>';
                }},
            { "mData": "id_produk"},
            { "mData": "nama_produk" },
            { "mData": "harga_penjualan" },
            { "mData": "stok_toko" },
            { "mData": "stok_gudang" },
            { "mData": "stok_ulang" },
            { "mData": "kategori" },
            { "mData": "no_rak_toko" },
            { "mData": "harga_beli" },
            { "mData": "harga_penjualan" },
            { "mData": "status_produk" },
            { "mData": "last_updated",
                "render": function (mData) {
                    var date = new Date(mData);
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "updated_by"},
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }},
            {
                "targets": -1,
                "data": null,
                "defaultContent": "<button>Disapproved!</button>"
            }
        ]
    });

    $('#productTableA tbody').on( 'click', 'tr', function () {

        var dataa = tablea.row(this).data().id_produk;
        console.log(dataa);

        ubahStatus(dataa);
        $('#productTableA').DataTable().ajax.reload();
        $('#productTableD').DataTable().ajax.reload();
    });




    var tabled = $('#productTableD').DataTable({
        destroy: true,
        "sAjaxSource": "/api/produkDissapproved",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_produk",
                "render": function (mData) {
                    return '<img style="width: 100px" src="image/product/'+mData+'"/>';
                }},
            { "mData": "id_produk"},
            { "mData": "nama_produk" },
            { "mData": "harga_penjualan" },
            { "mData": "stok_toko" },
            { "mData": "stok_gudang" },
            { "mData": "stok_ulang" },
            { "mData": "kategori" },
            { "mData": "no_rak_toko" },
            { "mData": "harga_beli" },
            { "mData": "harga_penjualan" },
            { "mData": "status_produk" },
            { "mData": "last_updated",
                "render": function (mData) {
                    var date = new Date(mData);
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "updated_by"},
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }},
            {
                "targets": -1,
                "data": null,
                "defaultContent": "<button>Approved!</button>"
            }
        ]
    });

    $('#productTableD tbody').on( 'click', 'tr', function () {

        var datad = tabled.row(this).data().id_produk;
        console.log(datad);
        ubahStatus(datad);

        $('#productTableD').DataTable().ajax.reload();
        $('#productTableA').DataTable().ajax.reload();
    } );



    function ubahStatus(datadis) {
        $.ajax({
            url: "/api/ubahStatus/"+datadis,
            type: 'POST',
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(datadis)
        });
    }

});
