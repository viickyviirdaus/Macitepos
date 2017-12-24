var lastOrder;
var persentaseDiskon =0;
var subTotal = 0, discount = 0, totalAkhir =0, Amount =0, cash=0;

$(document).ready(function () {
    $.ajax({
        url: '/api/lastOrder/',
        type: 'GET',
        dataType: 'json',
        success: function (order) {
            lastOrder = order;
            saveThisDiscount(lastOrder);
            showPrintInvoice(lastOrder);
            showTotalPrintInvoice();

        }
    });

    setTimeout(function () {
        window.print();
    },1250);

});

function saveThisDiscount(lo){
    $.each(lo,function (index, l) {
        persentaseDiskon = l.transaksi_penjualan.member.diskon;
    });
}

function showPrintInvoice(allOrder) {
    $.each(allOrder , function (index , order) {
        no = index+1;
        namaProduk = order.produk.nama_produk;
        price = order.produk.harga_penjualan;
        qty = order.jumlah_penjualan;
        total = price * qty;
        totalAkhir = order.transaksi_penjualan.total_penjualan;
        Amount = order.transaksi_penjualan.pembayaran_penjualan;
        cash = order.transaksi_penjualan.kembalian_penjualan;
        subTotal += total;
        discount = subTotal*(persentaseDiskon/100);
        // totalAkhir += ta;
        // Amount = amt;
        // cash = c;

        // subTotal =
        listProductPrintInvoice(no,namaProduk,price,qty,total);
        // calculatePrintInvoice(totalAkhir,amount,cash,total);
    });
}

function listProductPrintInvoice(no,namaProduk,price,qty,total) {
    $('#PrintInvoiceTable').append(
        productPrintInvoiceShow(no,namaProduk,price,qty,total));
}

function productPrintInvoiceShow(no,namaProduk,price,qty,total) {
    productListPrintInvoice =
        '<tr>' +
        '<td>' + no + '</td>' +
        '<td>' + namaProduk + '</td>' +
        '<td>' + price + '</td>' +
        '<td>' + qty + '</td>' +
        '<td>Rp. <span class="text-right">'+ total + '</span></td>' +
        '</tr>';
    return productListPrintInvoice;
}

function calculatePrintInvoice(ta,amt,c,ttl) {
    subTotal += ttl;
    discount += subTotal*(persentaseDiskon/100);
    totalAkhir += ta;
    Amount = amt;
    cash = c;
}

function showTotalPrintInvoice(){
    $("#SubTotalPrintInvoice").text("Rp. "+ subTotal +"");
    $("#DiscountPrintInvoice").text("Rp. "+ discount +"");
    $("#TotalPrintInvoice").text("Rp. "+ totalAkhir +"");
    $("#AmountPrintInvoice").text("Rp. "+ Amount +"");
    $("#CashPrintInvoice").text("Rp. "+ cash +"");

}