function infoGet(target, id) {
    var form = document.createElement("form");
    form.setAttribute("method", "get");
    form.setAttribute("action", "/" + target + "/" + id);
    document.body.appendChild(form);
    form.submit();
}

function updateGet(target, id) {
    var form = document.createElement("form");
    form.setAttribute("method", "get");
    form.setAttribute("action", "/" + target + "/" + id + "/edit");
    document.body.appendChild(form);
    form.submit();
}

function logout() {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "/logout");
    document.body.appendChild(form);
    form.submit();
}

function buyProduct(id) {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "/orders/" + id);
    document.body.appendChild(form);
    form.submit();
}

function orderApply(id) {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "/orders/" + id + "/apply");
    document.body.appendChild(form);
    form.submit();
}

function orderUnApply(id) {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "/orders/" + id + "/unapply");
    document.body.appendChild(form);
    form.submit();
}