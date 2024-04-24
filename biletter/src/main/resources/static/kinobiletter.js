// I call on getAll() and fetchMovies() so that the page is properly displayed from the start
$(function () {
    getAll();
    fetchMovies();
});


// This function is used to retrieve the data from the Filmer table (check schema.sql and data.sql)
// I have chosen getMapping here as I am retrieving data from the server and not storing data.
function fetchMovies() {
    $.get("/fetchMovies", function (data) {

        // I call upon the format movies function with the data that I have gotten from the server
        formatMovies(data);
    });
}


// This function is used to make my dropdown select menu. I use an extended for loop to loop through the array/list
function formatMovies(movies) {
    let valg = "<select id='valg'>";
    valg += "<option>Velg film</option>";

    for (const movie of movies) {
        valg += "<option>" + movie.film + "</option>";
    }
    valg += "</select>";
    $("#movie").html(valg);
}

// This function is the main function that is used to retrieve the user input, and check the input for errors
// This function is also used to store the user input in a database (Kunde table)
function kjop() {

    // fetch user input
    let antall = document.getElementById("antall").value;
    let fornavn = document.getElementById("fNavn").value;
    let etternavn = document.getElementById("eNavn").value;
    let telefon = document.getElementById("telefonnr").value;
    let epost = document.getElementById("ePost").value;
    let film = document.getElementById("valg").value;

    // define regExpressions
    const monsternummer = /^\d{8}$/g;
    const monsterEpost = /^[\w-\.]+@[\w-]+\.+[\w-]{2,4}$/g;
    const monsterAntall = /^[1-9]$/g;

    /* This is a boolean used to run through my error messages, this makes sure that the customer is not stored
    unless all conditions are met
     */
    let test;

    if (!fornavn) {
        let feil1 = "<b>fyll inn med et navn</b>";
        document.getElementById("feilnavn").innerHTML = feil1;
        test = false;
    } else {
        test = true;
        document.getElementById("feilnavn").innerHTML = "";
    }

    if (!etternavn) {
        let feil2 = "<b>fyll inn med et navn</b>";
        document.getElementById("feilenavn").innerHTML = feil2;
        test = false;
    } else {
        document.getElementById("feilenavn").innerHTML = "";
    }

    if (!monsternummer.test(telefon) || !telefon) {
       let  feil3 = "<b>Fyll inn med et gyldig telefonnummer</b>"
        document.getElementById("feilnummer").innerHTML = feil3;
        test = false;
    } else {
        document.getElementById("feilnummer").innerHTML = "";

    }

    if (!monsterEpost.test(epost) || !epost) {
        let feil4 = "<b>Fyll inn med en gyldig epost-adresse</b>"
        document.getElementById("feilepost").innerHTML = feil4;
        test = false;
    } else {
        document.getElementById("feilepost").innerHTML = "";
    }

    if (!monsterAntall.test(antall) || !antall) {
        let feil5 = "<b>Fyll inn et gyldig antall</b>"
        document.getElementById("feilantall").innerHTML = feil5;
        test = false;
    } else {
        document.getElementById("feilantall").innerHTML = "";
    }

    // if the test is false the function will not store the values
    if (!test) {
        return false;
    }

    // I defined a kunde object with the attributes, matching the ones in the Kunde table and Kunde class.
    const kunde ={
        fornavn: fornavn,
        etternavn : etternavn,
        telefon: telefon,
        epost: epost,
        antall: antall,
        film: film
    };

    // I used postMapping here as we are sending data to the server, and storing data requires more space.
    $.post("/save", kunde, function () {
        getAll();
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#saveerror").text(json.message);
        });

    // This will clear out the input fields after a customer has been registered
    $("#fNavn").val("");
    $("#eNavn").val("");
    $("#telefonnr").val("");
    $("#ePost").val("")
    $("#antall").val("");

}


// This function is used to fetch the customers from the server, I've used GetMapping for this
function getAll() {
    $.get("/fetch", function(data){
        formatCustomers(data);
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#fetcherror").text(json.message);
        });
}

// This function is used to list the customers in a table, I've used an extended for loop to do this.
function formatCustomers(customers) {
    let ut = "<table class='table table-striped'>"
    ut += "<tr><th>Navn</th><th>Telefonnr</th><th>E-post</th><th>antall</th><th>film</th>"
    for (const customer of customers) {
        ut += "<tr>";
        ut += "<td>" + customer.fornavn + " " + customer.etternavn + "</td>";
        ut += "<td>" + customer.telefon + "</td>";
        ut += "<td>" + customer.epost + "</td>";
        ut += "<td>" + customer.antall + "x</td>" + "<td>" + customer.film + "</td></tr>";
    }
    ut += "</table>"
    $("#customerRegistry").html(ut);
}

// This function is used to delete all the customers, and is done using GetMapping
// I have called upon the getAll() function again so that the updated list of customers will be shown (empty)
function slett() {
    $.get("/delete", function () {
        getAll();
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#deleteerror").text(json.message);
        });
}
