$(function () {
    reloadMenuShoppingCart();
    $("#shopping-cart").on("click", ".delete-item-from-cart", deleteFromMenuShoppingCart);
});

function reloadMenuShoppingCart() {
    $.post("/shoppingcart/getCart")
        .done(function (data) {
            showMenuShoppingCart(data);
        });
}

function showMenuShoppingCart(data) {
    $("#shopping-cart").html("");
    let itemsCount = 0;

    if (data) {
        for (const item of data.items) {
            let li = $("<li>").addClass("dropdown-cart__item");
            let div = $("<div>").addClass("media");
            div.append($("<img>").addClass("dropdown-cart__img").attr("src", item.product.imageUrl).attr("alt", item.product.title));
            let divBody = $("<div>").addClass("media-body pl-3");
            divBody.append($("<a>").attr("href", "#").addClass("h6").text(item.product.title));
            divBody.append($("<span>").addClass("text-primary").text("$" + item.product.price.toFixed(2)));
            if (item.quantity > 1) {
                divBody.append($("<span>").addClass("text-secondary").text(" (x" + item.quantity + ")"));
            }
            div.append(divBody);
            li.append(div);

            let a = $("<a>").addClass("dropdown-cart__item-remove").attr("href", "#");
            a.append($("<i>").addClass("ti-close delete-item-from-cart").attr("item-id", item.product.id));
            li.append(a);

            $("#shopping-cart").append(li);
            itemsCount++;
        }
    }

    if (itemsCount === 0) {
        menuShoppingCartEmpty();
    } else {
        let liTotal = `<li class="px-2 py-4 text-center">Subtotal: <span class="text-primary font-weight-semiBold"> $${data.totalSum.toFixed(2)}</span></li>`;
        $("#shopping-cart").append(liTotal);

        let li = $("<li>").addClass("dropdown-cart__item");
        let div = $("<div>").addClass("media");
        let divBody = $("<div>").addClass("media-body pl-3");
        divBody.append($("<a>").addClass("btn btn-outline-primary mx-1").attr("href", "/shoppingcart/").text("View Cart"));
        divBody.append($("<a>").addClass("btn btn-primary mx-1").attr("href", "/shoppingcart/checkout").text("Checkout"));
        div.append(divBody);
        li.append(div);

        $("#shopping-cart").append(li);
    }
}

function menuShoppingCartEmpty() {
    $("#shopping-cart").html("");

    let li = $("<li>").addClass("dropdown-cart__item");
    let div = $("<div>").addClass("media");
    let divBody = $("<div>").addClass("media-body pl-3");
    divBody.append($("<p>").addClass("h6").text("Your shopping cart is empty"));
    div.append(divBody);
    li.append(div);
    $("#shopping-cart").append(li);
}

function deleteFromMenuShoppingCart(e) {
    e.preventDefault();

    $.ajax({
        type: "DELETE",
        url: "/shoppingcart/?productId=" + this.getAttribute("item-id"),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            NotificationSuccess("Item deleted from your cart");
            reloadMenuShoppingCart();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.responseJSON);
        }
    });

    return false;
}