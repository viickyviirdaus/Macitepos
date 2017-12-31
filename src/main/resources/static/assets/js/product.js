var editor; // use a global for the submit and return data rendering in the examples

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
        ajax: "/api/produk",
        table: "#productTableApp",
        fields: [  {
            label: "Image:",
            name: "image",
            type: "upload",
            display: function ( file_id ) {
                return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
            },
            clearText: "Clear",
            noImageText: 'No image'
        },
        {
            label: "Name:",
            name: "nama_produk"
        }, {
            label: "Price:",
            name: "harga_penjualan"
        }, {
            label: "Category:",
            name: "kategori"
        }, {
            label: "Number Rack Shop:",
            name: "no_rak_toko"
        }, {
            label: "Price Buy:",
            name: "harga_beli"
        }, {
            label: "Price sell:",
            name: "harga_penjua"
        }
        ]
    } );

    // Activate an inline edit on click of a table cell
    $('#productTableApp').on( 'click', 'tbody td:not(:first-child)', function (e) {
        editor.inline( this );
    } );

    $('#productTableApp').DataTable( {
        dom: "Bfrtip",
        ajax: "/api/produk",
        order: [[ 1, 'asc' ]],
        columns: [
            {
                data: null,
                defaultContent: '',
                className: 'select-checkbox',
                orderable: false
            },
            {
                data: "image",
                render: function ( file_id ) {
                    return file_id ?
                        '<img src="'+editor.file( 'files', file_id ).web_path+'"/>' :
                        null;
                },
                defaultContent: "No image",
                title: "Image"
            },
            { data: "id_produk" },
            { data: "nama_produk" },
            { data: "harga_penjualan" },
            { data: "stok_toko" },
            { data: "stok_gudang" },
            { data: "stok_ulang" },
            { data: "kategori" },
            { data: "no_rak_toko" },
            { data: "harga_beli" },
            { data: "harga_penjualan" },
            { data: "status_produk" },
            { data: "last_updated" },
            { data: "updated_by" },
            { data: "created_at" }



        ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
} );