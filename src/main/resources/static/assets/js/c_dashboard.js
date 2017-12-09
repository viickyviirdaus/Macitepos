var selectedProducts=[];
var pembeli=[];
var jumlahBeli = [];
var allProduct;
var discount = 0;
var RpDiscount = 0;
var subTotal = 0;
var grandTotal = 0;
var recievedAmount = 0;
var cash = 0;
var totalPerProduk = [];
var sendJSON=[];
var idMember = 1;
var visit_count =[];
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
            showCategoryDropDown();
        }
    });
}

$(document).on("click", "[category]", function () {
    idSelected = $(this).attr('id');
    emptyproductListShow();
        $.ajax({
            url: '/api/produk/'+idSelected,
            type: 'GET',
            dataType: 'json',
            success: function (products) {
                allProduct = products;
                productListSuccess(allProduct);
            }
        })
});

$(document).on("change","#serachProduk", function () {
    searchKey = $('#serachProduk').val();
    emptyproductListShow();
    if(searchKey!=''){
        $.ajax({
            url: '/api/produk/'+searchKey,
            type: 'GET',
            dataType: 'json',
            success: function (products) {
                allProduct = products;
                productListSuccess(allProduct);
            }
        })
    } else {
        $.ajax({
            url: '/api/produk/',
            type: 'GET',
            dataType: 'json',
            success: function (products) {
                allProduct = products;
                productListSuccess(allProduct);
            }
        });
    }
});

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
    productList =
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

function emptyproductListShow(){
    $("#showAvailbleProduct").html(
        '')
}

function showCategoryDropDown(){
    $('#categoryDropDown').append(
        categoryDropDown()
    );
}

function categoryDropDown() {
    cat =   '<li><a category id="all">All Product</a></li>' +
            '<li><a category id="makanan">Makanan</a></li>' +
            '<li><a category id="minuman">Minuman</a></li>' +
            '<li><a category id="obat">Obat</a></li>' +
            '<li role="separator" class="divider"></li>' +
            '<li><a category id="rumahTangga">Rumah Tangga</a></li>';
    return cat;
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

    listProduct();
    calculate();
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
                jumlahBeli[product.id_produk] = 1;

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
            jumlahBeli[select.id_produk] = 1;
        }
    }

    // alert("jumlah beli setelah di klik panjangnya"+jumlahBeli.length);

    listProduct();
    calculate();

});

function listProduct() {
    var id = '';
    var no ='', image='',name='',price='',count='',action='', recordMain ='', recordInvoice = '';
    $.each(selectedProducts, function (index, product) {
            no = index+1;
            id = product.id_produk;
            image = product.foto_produk;
            name = product.nama_produk;
            price = product.harga_penjualan;
            count = jumlahBeli[product.id_produk];
        recordMain+= showTableRow(no,id,image,name,price,count);
        recordInvoice += showTableRowInvoice(no,id,name,price,count)
    });
    $("#productTable tbody").html(recordMain);
    $("#InvoiceTable tbody").html(recordInvoice);

}

function showTableRow(no,id,image,name,price,count) {
    // $("#listTableProductCustumer").html(id+image+name+price+count);
    row =
        '<tr>' +
            '<td>' + no + '</td>' +
            '<td><img src="assets/image/'+ image +'" style="width: 40px; padding-bottom: 5px;" alt=""/></td>' +
            '<td>' + name + '</td>' +
            '<td>' + price + '</td>' +
            '<td>' +
                 '<input type="number" class="form-control" changeCount id="'+ id +'" value="'+ count +'" style="width: 60px"/>' +
            '</td>' +
            '<td> <button deleteButton class="btn btn-danger" id="'+ id +'">Delete</button></td>' +
        '</tr>';
    return row;
}

$(document).on("change","[changeCount]", function () {
    var no ='',name='',price='',count='', recordInvoice = '';
    var id = '';
    idSelected = $(this).attr('id');
    total = $('#'+ idSelected +'').val();
    if(total<=1){
        $('#'+ idSelected +'').attr("value", 1);
        calculate();
    } else {
        jumlahBeli[idSelected] = total;
        calculate();
    }
    $.each(selectedProducts, function (index, product) {
        no = index+1;
        id = product.id_produk;
        name = product.nama_produk;
        price = product.harga_penjualan;
        count = jumlahBeli[product.id_produk];
        recordInvoice += showTableRowInvoice(no,id,name,price,count)
    });
    $("#InvoiceTable tbody").html(recordInvoice);
});

function showTableRowInvoice(no,id,name,price,count) {
    totalPerProduk[id] = price*count;
    // alert("total per product setelah ditampilkan"+totalPerProduk.length);
    var row =
        '<tr>' +
            '<td>' + no + '</td>' +
            '<td>' + name + '</td>' +
            '<td>' + price + '</td>' +
            '<td>' + count + '</td>' +
            '<td>Rp. <span class="text-right">'+ totalPerProduk[id] + '</span></td>' +
        '</tr>';
    return row;
}

// Change Diskon By Input ID
$(function () {
    $('#searchIdMember').click(function () {
        idMember = $('#InputIdMember').val();
        idMember = idMember*1;
        if (idMember == 1){
            alert("Member tidak ditemukan");
            $.ajax({
                url: '/api/member/'+idMember,
                type: 'GET',
                dataType: 'json',
                success: function (member) {
                    pembeli = member;
                    setDiskon();
                    $.each(pembeli, function (index, p) {
                        visit_count[0] = p.visitCount;
                    });
                }
            })
        } else {
            $.ajax({
                url: '/api/member/'+idMember,
                type: 'GET',
                dataType: 'json',
                success: function (member) {
                    pembeli = member;
                    $.each(pembeli, function (index, p) {
                        visit_count[0] = p.visitCount;
                        alert(visit_count);
                    });
                    if(pembeli.length>0){
                        alert("Member ditemukan, Nama Member = "+ pembeli[0].nama_member)
                        setDiskon();
                    } else {
                        alert("Member tidak ditemukan")
                    }
                }
            })
        }
    })
});

function setDiskon() {
    $.each(pembeli, function (index, pem) {
       discount = pem.diskon;
    });
    $('#discountShow').attr("placeholder", discount+"%");
    calculate();
    recievedAmount = recievedAmount*1;
}

function calculate() {
    calculateSubTotal();
    calculateGrandTotal();
    calculateRpDiscount();
    // calculateRecievedAmount();
}
$(function(){
    $('#submitButton').click(function(){
        recievedAmount = $('#RecievedAmount').val();
        recievedAmount = recievedAmount*1;
        if(recievedAmount > 0 && recievedAmount >= grandTotal){
            $("#submitButton").attr("data-toggle", "modal");
            calculateRecievedAmount();
            calculateCash();
        } else {
            $("#submitButton").attr("data-toggle", "");
            alert("Pembayaran Belum Diterima / Kurang");
            $("#RecievedAmount").attr("class", "form-control active");
        }
    });
});
function calculateSubTotal() {
    subTotal = 0;
    $.each(selectedProducts, function (index,product) {
        subTotal+= (product.harga_penjualan * jumlahBeli[product.id_produk]);
    });
    $("#subTotal").attr("placeholder", "Rp. "+ subTotal +"");
    $("#SubTotalInvoice").text("Rp. "+ subTotal +"");
}

function calculateGrandTotal() {
    grandTotal = 0;
    grandTotal = (subTotal - (subTotal *(discount/100)));
    $("#grandTotal").attr("placeholder", "Rp. "+ grandTotal +"");
    $("#TotalInvoice").text("Rp. "+ grandTotal +"");

}

function calculateRpDiscount() {
    RpDiscount = 0;
    RpDiscount += subTotal*(discount/100);
    $("#DiscountInvoice").text("Rp. "+ RpDiscount +"");
}

function calculateRecievedAmount() {
    recievedAmount = $('#RecievedAmount').val();
    recievedAmount = recievedAmount*1;
    $("#AmountInvoice").text("Rp. "+recievedAmount+"");
}

function calculateCash() {
    cash = 0;
    cash += recievedAmount - grandTotal;
        $("#CashInvoice").text("Rp. "+ cash +"");
}

$(function(){
    $('#printInvoice').click(function(){
        // alert("PRINT");
        if(visit_count == 0){
            // alert("VISIT COUNT 0")
            $.ajax({
                url: '/api/member/'+idMember,
                type: 'GET',
                dataType: 'json',
                success: function (member) {
                    pembeli = member;
                    setDiskon();
                    $.each(pembeli, function (index, p) {
                        visit_count[0] = p.visitCount;
                        visit_count[0] +=1;
                        // alert(visit_count[0]);
                        createJson(visit_count[0]);
                    });
                }
            });
        } else {
            visit_count[0] += 1;
            createJson(visit_count[0]);
        }
        // sendJSON = [];
        // sendJSON[0] = new Object();
        // sendJSON[0].id_produk = "hehehe";
        // sendJSON[0].jumlahBeli = 1;
        // sendJSON[1] = new Object();
        // sendJSON[1].id_produk = 'Dua';
        // sendJSON[1].jumlah_beli = 2;
        // sendJSON[1].jumlah_beli = 9;
        // sendJSON[0].jumlahBeli = 3;
        console.log(sendJSON);
    });
});

function createJson(v) {
    $.each(selectedProducts , function (index,product) {
        sendJSON[index] = new Object();
        sendJSON[index].id_produk = product.id_produk;
        sendJSON[index].count_product = jumlahBeli[product.id_produk]*1;
        sendJSON[index].discount = discount;
        sendJSON[index].cash = cash;
        sendJSON[index].recievedAmount = recievedAmount;
        sendJSON[index].total = subTotal;
        sendJSON[index].id_member = idMember;
        sendJSON[index].visit_count = v;
    });
    sendData(sendJSON);
}

function sendData(transaction) {
    $.ajax({
        url: "/api/transaksiPenjualan/create",
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(transaction),
        success: function (transaction) {
            productAddSuccess(transaction);
        }
        // ,
        // error: function (request, message, error) {
        //     handleException(request, message, error);
        // }
    });
}
