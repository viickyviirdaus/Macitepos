var dataProduk;
$(document).ready(function () {
    ambilProduk();
});
function ambilProduk() {
    $.ajax({
        url: 'http://localhost:8080/api/produk',
        type: 'GET',
        dataType: 'json',
        success: function (produk) {
            dataProduk = produk;
            addToRak(dataProduk);
            console.log(dataProduk);
            console.log(dataProduk[0].kategori + "-" + dataProduk[0].no_rak_toko);
        }
    });
}

function addToRak(products) {
    $.each(products, function (index,product) {
        // if(product.kategori =="food" ){
            addFoodToRak(product);
        // }

    })
}

function addFoodToRak(product){
    addListFoodInRak(product.nama_produk, product.no_rak_toko, product.stok_toko);
    updateSumOfFoodInRak(product.stok_toko, product.no_rak_toko);
}

function addListFoodInRak(namaProduk, noRak, stokToko) {
    $("#body"+noRak).append(appendToRak(namaProduk, stokToko));
}

function appendToRak(namaProduk, stokToko) {
    var r = '<div class="row">' +
                '<div class="col-md-12">' +
                    '<span>'+ namaProduk +' ('+ stokToko +')</span>'+
                '</div>\n' +
            '</div>';
    return r;
}

function updateSumOfFoodInRak(stokProduk, noRak) {
    jumlahProduk = $('#jumlah'+noRak).val();
    jumlahProduk = jumlahProduk * 1;
    jumlahProduk += stokProduk;
    $("#jumlah"+noRak).val(jumlahProduk);
}