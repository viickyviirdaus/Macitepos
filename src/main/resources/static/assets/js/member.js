$(document).ready (function() {
    var table = $('#memberTable').DataTable({
        "sAjaxSource": "/api/customer",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": "id_member"},
            { "mData": "nama_member" },
            { "mData": "tanggal_lahir" },
            { "mData": "jenis_kelamin" },
            { "mData": "alamat" },
            { "mData": "count" },
            { "mData": "created_at" }
        ]
    })
});