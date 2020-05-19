function linkStartPage() {
    document.getElementById("startseite").onclick = function () {
        location.href = "http://localhost:8181/Kundenverwaltung/index";
    };
}
function linkCustomerPage() {
    document.getElementById("kunden").onclick = function () {
        location.href = "http://localhost:8181/Kundenverwaltung/customers";
    };
}

function toggleButton() {
     $("#formButton").click(function(){
            $("#form1").toggle();
        });
}