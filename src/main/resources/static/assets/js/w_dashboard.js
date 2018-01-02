var food = ["A1", "A2", "A3", "A4", "A5"];
var drink = ["B1", "B2", "B3", "B4", "B5"];
var drug = ["C1", "C2", "C3", "C4", "C5"];
var cosmetic = ["D1", "D2", "D3", "D4", "D5"];
var office  = ["E1", "E2", "E3", "E4", "E5"];
var household = ["F1", "F2",    "F3", "F4", "F5"];

$(document).on("click", "#inlineRadio1", function () {
    showOption("food");
});
$(document).on("click", "#inlineRadio2", function () {
    showOption("drink");
});
$(document).on("click", "#inlineRadio3", function () {
    showOption("drug");
});
$(document).on("click", "#inlineRadio4", function () {
    showOption("cosmetic");
});
$(document).on("click", "#inlineRadio5", function () {
    showOption("office");
});
$(document).on("click", "#inlineRadio6", function () {
    showOption("houseHold");
});

function showOption(category) {
    if (category == "food"){
        $("#sugestionRak").html("Available Number map = ");
        $.each(food, function (index, rak) {
            showOptionOnModal(rak);
        });
    } else if(category == "drink") {
        $("#sugestionRak").html("Available Number map = ");
        $.each(drink, function (index, rak) {
            showOptionOnModal(rak);
        });
    } else if(category == "drug") {
        $("#sugestionRak").html("Available Number map = ");
        $.each(drug, function (index, rak) {
            showOptionOnModal(rak);
        });
    } else if(category == "cosmetic") {
        $("#sugestionRak").html("Available Number map = ");
        $.each(cosmetic, function (index, rak) {
            showOptionOnModal(rak);
        });
    } else if(category == "office") {
        $("#sugestionRak").html("Available Number map = ");
        $.each(office, function (index, rak) {
            showOptionOnModal(rak);
        });
    } else if(category == "houseHold") {
        $("#sugestionRak").html("Available Number map = ");
        $.each(household, function (index, rak) {
            showOptionOnModal(rak);
        });
    }
}

function showOptionOnModal(rak){
    $("#sugestionRak").append(
        showIt(rak));
}

function showIt(rak) {
    rakList =   rak+" ";
    return rakList;
}