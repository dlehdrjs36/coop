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
        const recommendCount = document.querySelector("#recommendCount");
        // [ data.키값 ] 이런 형태로 value 추출 가능
        recommendCount.innerText = data.recommendCount;

        const recommendIcon = document.querySelector(".recommendIcon");
        if (data.recommendAt) {
            recommendIcon.classList.add("text-danger");
        } else {
            recommendIcon.classList.remove("text-danger");
        }

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

    const removeIcons = document.querySelectorAll(".removeComment");
    for (var j = 0; j < removeIcons.length; j++) {
        removeIcons[j].addEventListener('click', removeCommentForm);
    }
}

function createCommentForm() {
    if (this.parentNode.nextSibling.nodeName == "DIV") {
        this.parentNode.nextSibling.remove();
    }else {
        let childForms = document.querySelectorAll(".childCommentForm");
        for (let i = 0; i < childForms.length; i++) {
            childForms[i].remove();
        }
        addChildComment(this);
    }
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

    // parent.insertAdjacentHTML("afterend", divElemnt.outerHTML);
    parent.parentNode.insertAdjacentHTML("afterend", divElemnt.outerHTML);

}

function removeCommentForm() {
    if (this.parentNode.nextSibling.nodeName == "DIV") {
        this.parentNode.nextSibling.remove();
    }else {
        let removeForms = document.querySelectorAll(".removeCommentForm");
        for (let i = 0; i < removeForms.length; i++) {
            removeForms[i].remove();
        }
        addRemoveCommentForm(this);
    }
}

function addRemoveCommentForm(element){
    const commentId = element.dataset.commentid;

    var passwordLabelElement = document.createElement("label");
    passwordLabelElement.setAttribute("for", "removePassword");
    passwordLabelElement.textContent="패스워드";

    var passwordElement = document.createElement("input");
    passwordElement.setAttribute("type", "password");
    passwordElement.setAttribute("name", "removePassword");
    passwordElement.setAttribute("class", "form-control mb-2");

    var buttonElement = document.createElement("button");
    buttonElement.setAttribute("type", "button");
    buttonElement.setAttribute("data-commentid", commentId);
    buttonElement.setAttribute("class", "mb-2 btn btn-sm btn-danger remove");
    buttonElement.textContent = "댓글 삭제";

    var divElement = document.createElement("div");
    divElement.setAttribute("class", "d-flex mt-4 mb-4 removeCommentForm");
    divElement.appendChild(passwordLabelElement);
    divElement.appendChild(passwordElement);
    divElement.appendChild(buttonElement);
    element.parentNode.insertAdjacentHTML("afterend", divElement.outerHTML);

    let removeButton = document.querySelector(".remove");
    removeButton.addEventListener('click', removeComment);
}

function removeComment() {

    const commentId = this.dataset.commentid;

    let removePassword = document.querySelector("input[name='removePassword']").value;

    if(removePassword == "") {
        alert("삭제할 댓글의 비밀번호를 입력해 주세요");
        return false;
    }

    fetch("/comments/" + commentId, {
        method: 'delete',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            password : removePassword
        })
    }).then(res => {
        return res.json();
    }).then(data => { //응답 결과를 json으로 파싱
        //삭제된 경우
        if (data.isRemove) {
            let removeForm = document.querySelector(".removeCommentForm");
            let commentDiv = removeForm.parentNode;
            commentDiv.classList.add("align-self-center");
            commentDiv.innerHTML = "<span>삭제된 댓글 입니다.</span>";
            removeForm.remove(); //댓글 삭제 폼 삭제
            alert("댓글이 삭제되었습니다.");
        } else {
            alert("댓글이 삭제에 실패하였습니다.");
        }
    }).catch(err => { // 오류 발생시 오류를 담아서 보여줌
        console.log('Fetch Error', err);
    });
}