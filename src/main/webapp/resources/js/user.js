document.addEventListener("DOMContentLoaded", function () {

    var section = document.querySelector("#user-donation-details");

    let numberOfBags = section.querySelector("span[data-quantity]").dataset.quantity;

    let conjugateBags = 'worek';
    if(numberOfBags >= 2 && numberOfBags <= 4) {
        conjugateBags = "worki"
    } else if (numberOfBags >= 5) {
        conjugateBags = "worków"
    }

    section.querySelector("span[data-conjugate-bags]").innerText = conjugateBags;

});