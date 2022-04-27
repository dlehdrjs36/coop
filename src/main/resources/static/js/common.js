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

function replyGet(id) {
    var upperPostIdElement = document.createElement("input");
    upperPostIdElement.setAttribute("type", "hidden");
    upperPostIdElement.setAttribute("name", "upperPostId");
    upperPostIdElement.setAttribute("value", id);

    var form = document.createElement("form");
    form.setAttribute("method", "get");
    form.setAttribute("action", "/posts/reply");
    form.appendChild(upperPostIdElement);

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

function recommend(id){
    fetch("/posts/" + id + "/recommend", { // url 입력 및 [options] 값 설정
        method: 'post',
        headers: {
            'Accept': 'application/json'
        }
    }).then(res => {
        return res.json();
    }).then(data => { //응답 결과를 json으로 파싱
        const recommedCount = document.querySelector("#recommendCount");
        // [ data.키값 ] 이런 형태로 value 추출 가능
        recommedCount.innerText = data.recommendCount;

    }).catch(err => { // 오류 발생시 오류를 담아서 보여줌
        console.log('Fetch Error', err);
    });
}


document.addEventListener("DOMContentLoaded", ready)
function ready() {
    //대댓글 입력 폼
    const comments = document.querySelectorAll(".parentComment");
    for (var i = 0; i < comments.length; i++) {
        comments[i].addEventListener('click', createCommentForm);
    }
}
function createCommentForm() {

    let childForms = document.querySelectorAll(".childCommentForm");
    for (let i = 0; i < childForms.length; i++) {
        childForms[i].remove();
    }
    addChildComment(this);
}

function addChildComment(parent) {
    const postId = parent.dataset.postid;
    const commentId = parent.dataset.commentid;

    var upperCommentElement = document.createElement("input");
    upperCommentElement.setAttribute("type", "hidden");
    upperCommentElement.setAttribute("name", "upperCommentId");
    upperCommentElement.setAttribute("value", commentId);

    var nicknameLabelElement = document.createElement("label");
    nicknameLabelElement.setAttribute("for", "nickname");
    nicknameLabelElement.innerText="닉네임";

    var nicknameElement = document.createElement("input");
    nicknameElement.setAttribute("type", "text");
    nicknameElement.setAttribute("name", "nickname");
    nicknameElement.setAttribute("class", "form-control");

    var passwordLabelElement = document.createElement("label");
    passwordLabelElement.setAttribute("for", "password");
    passwordLabelElement.innerText="패스워드";

    var passwordElement = document.createElement("input");
    passwordElement.setAttribute("type", "password");
    passwordElement.setAttribute("name", "password");
    passwordElement.setAttribute("class", "form-control mb-2");

    var contentElement = document.createElement("textarea");
    contentElement.setAttribute("rows", "3");
    contentElement.setAttribute("name", "content");
    contentElement.setAttribute("class", "form-control mb-2");

    var buttonElement = document.createElement("button");
    buttonElement.setAttribute("type", "submit");
    buttonElement.setAttribute("class", "mb-2 btn btn-sm btn-dark")
    buttonElement.innerText = "댓글 등록";

    var formElement = document.createElement("form");
    formElement.setAttribute("method", "post");
    formElement.setAttribute("action", "/comments/" + postId);
    // formElement.appendChild(postElement);
    formElement.appendChild(upperCommentElement);
    formElement.appendChild(nicknameLabelElement);
    formElement.appendChild(nicknameElement);
    formElement.appendChild(passwordLabelElement);
    formElement.appendChild(passwordElement);
    formElement.appendChild(contentElement);
    formElement.appendChild(buttonElement);

    var divElemnt = document.createElement("div");
    divElemnt.setAttribute("class", "d-flex mt-4 mb-4 childCommentForm");
    divElemnt.appendChild(formElement);

    parent.insertAdjacentHTML("afterend", divElemnt.outerHTML);
    // parent.appendChild(divElemnt);
}

// function addComment(id){
//     var form = document.querySelector("#commentForm");
//
//     fetch("/comments/" + id , { // url 입력 및 [options] 값 설정
//         method: 'post',
//         headers: {
//             'Accept': 'application/json'
//         },
//         body:
//     }).then(res => {
//         return res.json();
//     }).then(data => { //응답 결과를 json으로 파싱
//         const recommedCount = document.querySelector("#recommendCount");
//         // [ data.키값 ] 이런 형태로 value 추출 가능
//         recommedCount.innerText = data.recommendCount;
//
//     }).catch(err => { // 오류 발생시 오류를 담아서 보여줌
//         console.log('Fetch Error', err);
//     });
// }


