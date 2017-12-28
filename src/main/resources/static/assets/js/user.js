

$(document).ready (function() {
    var table = $('#userTable').DataTable({
        "sAjaxSource": "/api/pengguna",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "foto_pengguna",
                "render": function (mData) {

                    return '<img style="width: 100px" src="image/user/'+mData+'"/>';

                }},
            { "mData": "id_pengguna"},
            { "mData": "nama_pengguna" },
            { "mData": "alamat_pengguna" },


            { "mData": "tanggal_lahir" },
            { "mData": "email" },
            { "mData": "level" },
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": "last_modified",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }}
        ]
    })
});
