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