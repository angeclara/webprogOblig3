// This function will launch when the page is loading and finds the correct customer by locating the customer id
$(function () {
    const id = window.location.search.substring(1);
    const url = "fetchCustomer?" + id;
    $.get(url, function (customer) {
        $("#id").val(customer.id);
        $("#fNavn").val(customer.fornavn);
        $("#eNavn").val(customer.etternavn);
        $("#telefonnr").val(customer.telefon);
        $("#ePost").val(customer.epost);
    });
});

// This function is used to modify the customer information and communicates with the server by storing the new user input
function endre() {
    const kunde = {
        id : $("#id").val(),
        fornavn: $("#fNavn").val(),
        etternavn : $("#eNavn").val(),
        telefon: $("#telefonnr").val(),
        epost: $("#ePost").val()
    };

    $.post("/modify", kunde, function () {
        window.location.href = 'index.html';
    });
}