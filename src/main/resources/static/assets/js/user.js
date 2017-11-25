

$(document).ready (function() {
    var table = $('#userTable').DataTable({
        "sAjaxSource": "/api/pengguna",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "columns": [

            { "mData": 0,
                "render": function (mData) {
                    return '<img style="width: 100%" src="assets/image/'+mData+'"/>';
                }},
            { "mData": 1},
            { "mData": 2 },
            { "mData": 3 },
            { "mData": 4 },
            { "mData": 5 },
            { "mData": 6 },
            { "mData": 7,
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds();
                }},
            { "mData": 8,
                "render": function (mData) {
                    var date = new Date(mData);
                    var month = date.getMonth() + 1;
                    return (month.length > 1 ? month : + month) + "/" + date.getMonth() + "/" + date.getFullYear() +" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds() ;
                }}
        ]
    })
});
