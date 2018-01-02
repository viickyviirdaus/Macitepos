

$(document).ready (function() {
    var tablet = $('#userTableTrue').DataTable({
        "sAjaxSource": "/api/penggunaTrue",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_pengguna",
                "render": function (mData) {

                    return '<img style="width: 100px" src="image/user/'+mData+'"/>';

                }},
            { "mData": "nama_pengguna" },
            { "mData": "alamat_pengguna" },
            { "mData": "tanggal_lahir" },
            { "mData": "email" },
            { "mData": "level" },
            { "mData": "status_pengguna" },
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "last_modified",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }},
            {
                "targets": -1,
                "data": null,
                "defaultContent": "<button>Nonactive!</button>"
            }
        ]
    });
    $('#userTableTrue tbody').on( 'click', 'tr', function () {

        var datat = tablet.row(this).data().id_pengguna;
        console.log(datat);
        ubahStatus(datat);

        $('#userTableFalse').DataTable().ajax.reload();
        $('#userTableTrue').DataTable().ajax.reload();
    } );


    var tablef = $('#userTableFalse').DataTable({
        "sAjaxSource": "/api/penggunaFalse",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_pengguna",
                "render": function (mData) {

                    return '<img style="width: 100px" src="image/user/'+mData+'"/>';

                }},
            { "mData": "nama_pengguna" },
            { "mData": "alamat_pengguna" },
            { "mData": "tanggal_lahir" },
            { "mData": "email" },
            { "mData": "level" },
            { "mData": "status_pengguna" },
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "last_modified",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return date.getDate() + "/" + ( date.getMonth() + 1) + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }},
            {
                "targets": -1,
                "data": null,
                "defaultContent": "<button>Active!</button>"
            }
        ]
    });
    $('#userTableFalse tbody').on( 'click', 'tr', function () {

        var dataf = tablef.row(this).data().id_pengguna;
        console.log(dataf);
        ubahStatus(dataf);

        $('#userTableFalse').DataTable().ajax.reload();
        $('#userTableTrue').DataTable().ajax.reload();
    } );


    function ubahStatus(datadis) {
        $.ajax({
            url: "/api/ubahStatusUser/"+datadis,
            type: 'POST',
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(datadis)
        });
    }
});
