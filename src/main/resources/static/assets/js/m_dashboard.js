var dataBulanan = [];
var a = 100;

$(document).ready (function() {
    monthlyGL();
});

function monthlyGL() {
    $.ajax({
        url: '/api/generalLedgerMonthASC',
        type: 'GET',
        dataType: 'json',
        success: function (order) {
            orders = order;
            orderFix(orders, "bulan");
            // orderListSuccess(orders);
        }
    });
}

function orderFix(orders){
    dataBulanan[0] = 0;
    dataBulanan[1] = 0;
    dataBulanan[2] = 0;
    dataBulanan[3] = 0;
    dataBulanan[4] = 0;
    dataBulanan[5] = 0;
    dataBulanan[6] = 0;
    dataBulanan[7] = 0;
    dataBulanan[8] = 0;
    dataBulanan[9] = 0;
    dataBulanan[10] = 0;
    dataBulanan[11] = 0;
    var date = 0;
    var i = 0;
    $.each(orders, function (index,order) {
        if(date == 0){
            date = order.created_at;
            calculateTotal(order, i);
        } else {
            if (date == order.created_at){
                calculateTotal(order, i);
            } else{
                date = order.created_at;
                i++;
                console.log("wkwk");
                calculateTotal(order, i);
            }
        }
    });

    $.each(dataBulanan, function (index, data) {
       if(data == 0){
           dataBulanan[index] = NaN;
       }
    });
    // console.log(dataBulanan);
    runGraphic();
}

function calculateTotal(order, i) {
    dataBulanan[i] += order.total_penjualan;

}

function runGraphic(){
    var ctx = document.getElementById('myChart').getContext('2d');
    var chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'line',

        // The data for our dataset
        data: {
            labels: ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
            datasets: [{
                label: "Statistic Macitepos",
                backgroundColor: 'rgba(74, 142, 249 , 0.5)',
                borderColor: 'green',
                data: [0,dataBulanan[0], dataBulanan[1], dataBulanan[2], dataBulanan[3],
                        dataBulanan[4], dataBulanan[5], dataBulanan[6], dataBulanan[7],
                        dataBulanan[8], dataBulanan[9], dataBulanan[10], dataBulanan[11]],
            }]
        },

        // Configuration options go here
        options: {}
    });
}