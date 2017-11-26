var selectedProducts=[];
var jumlahBeli = [];
var ind = 0 ;
var allProduct;
var discount = 0;
var RpDiscount = 0;
var subTotal = 0;
var grandTotal = 0;
var recievedAmount = 0;
var cash = 0;
var totalPerProduk = [];
$(document).ready(function () {
    productList();
});

//call produk API
function productList() {
    $.ajax({
        url: '/api/produk/',
        type: 'GET',
        dataType: 'json',
        success: function (products) {
            allProduct = products;
            productListSuccess(allProduct);
        },
    });
}

function productListSuccess(allProduct) {
    $.each(allProduct, function (index, product) {
        productAddList(product);
    });
}

function productAddList(product) {
    $("#showAvailbleProduct").append(
        productListShow(product));
}

function productListShow(product) {
    var productList =
            '<a selected id="'+ product.id_produk +'" >' +
                '<div class="col-sm-3">' +
                    '<div class="panel panel-default">' +
                        '<div class="panel-body">' +
                            '<div>' +
                                '<img style="width: 100%; padding-bottom: 5px;" src="assets/image/'+ product.foto_produk +'"/>' +
                            '</div>'+
                            '<div class="text-center">' +
                                '<span>'+product.nama_produk+'</span>'+
                                '<br/>' +
                                '<span style="font-size: 12px">' + 'Rp '+product.harga_penjualan+'</span>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</a>';
    return productList;
}

$(document).on("click", "[deleteButton]", function () {
    idSelected = $(this).attr('id');
    var deleteTarget;
    $.each(selectedProducts, function (index, product) {
        if(product.id_produk == idSelected){
            deleteTarget = product;
        }
    });
    selectedProducts = jQuery.grep(selectedProducts, function (value) {
        return value != deleteTarget;
    });

    // jumlahBeli  = jQuery.grep(jumlahBeli, function (value) {
    //     return value =
    // })

    listProduct();
    calculate();
    alert(selectedProducts.length);

});

$(document).on( "click", "[selected]", function(){
    idSelected = $(this).attr('id');
    var panjang = selectedProducts.length;
    var cek = false;
    var select;

    if (panjang == 0){
        $.each(allProduct, function (index, product) {
            if (product.id_produk == idSelected){
                selectedProducts.push(product);
                jumlahBeli[ind] = 1;
                ind++;
            }
        });
    } else {
        $.each(allProduct, function (index, product) {
            if (product.id_produk == idSelected){
                $.each(selectedProducts, function (index, selectedProduct) {
                    if(selectedProduct.id_produk == idSelected ){
                        cek = false;
                        alert("Produk telah berada di Cart")
                        return false;
                    } else {
                        cek = true;
                        select = product;
                    }
                });
            }
        });
        if (cek == true){
            selectedProducts.push(select);
            jumlahBeli[ind] = 1;
            ind++;
        }
    }

    listProduct();
    calculate();


});

function listProduct() {
    var id = '';
    var no ='', image='',name='',price='',count='',action='', recordMain ='', recordReciept = '';
    $.each(selectedProducts, function (index, product) {
            no = index+1;
            id = product.id_produk;
            image = product.foto_produk;
            name = product.nama_produk;
            price = product.harga_penjualan;
            count = jumlahBeli[index];
        recordMain+= showTableRow(no,id,image,name,price,count);
        recordReciept += showTableRowReciept(no,id,name,price,count)
    });
    $("#productTable tbody").html(recordMain);
    $("#RecieptTable tbody").html(recordReciept);

}

function showTableRow(no,id,image,name,price,count) {
    // $("#listTableProductCustumer").html(id+image+name+price+count);
    var row =
        '<tr>' +
            '<td>' + no + '</td>' +
            '<td><img src="assets/image/'+ image +'" style="width: 40px; padding-bottom: 5px;" alt=""/></td>' +
            '<td>' + name + '</td>' +
            '<td>' + price + '</td>' +
            '<td>' + count + '</td>' +
            '<td> <button deleteButton class="btn btn-danger" id="'+ id +'">Delete</button></td>' +
        '</tr>';
    return row;
}

function showTableRowReciept(no,id,name,price,count) {
    totalPerProduk[no] = price*count;
    var row =
        '<tr>' +
            '<td>' + no + '</td>' +
            '<td>' + name + '</td>' +
            '<td>' + price + '</td>' +
            '<td>' + count + '</td>' +
            '<td>'+"Rp." + totalPerProduk[no] + '</td>' +
        '</tr>';
    return row;
}

function calculate() {
    calculateSubTotal();
    calculateGrandTotal();
    calculateRpDiscount();
    calculateRecievedAmount();
}
$(function(){
    $('#submitButton').click(function(){
        calculateRecievedAmount();
        calculateCash();
    });
});
function calculateSubTotal() {
    subTotal = 0;
    $.each(selectedProducts, function (index,product) {
        subTotal+= (product.harga_penjualan * jumlahBeli[index]);
    });
    $("#subTotal").attr("placeholder", "Rp. "+ subTotal +"");
    $("#SubTotalReciept").text("Rp. "+ subTotal +"");
}

function calculateGrandTotal() {
    grandTotal = 0;
    grandTotal += (subTotal - (subTotal *discount));
    $("#grandTotal").attr("placeholder", "Rp. "+ grandTotal +"");
    $("#TotalReciept").text("Rp. "+ grandTotal +"");

}

function calculateRpDiscount() {
    RpDiscount = 0;
    RpDiscount += subTotal*discount;
    $("#DiscountReciept").text("Rp. "+ RpDiscount +"");
}

function calculateRecievedAmount() {
    recievedAmount = $('#RecievedAmount').val();
    $("#AmountReciept").text("Rp. "+recievedAmount+"");
}

function calculateCash() {
    cash = 0;
    cash += recievedAmount - grandTotal;
    $("#CashReciept").text("Rp. "+ cash +"")
}

