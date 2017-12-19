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
            { "mData": "visitCount" },
            { "mData": "created_at",
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }}
        ]
    })
});