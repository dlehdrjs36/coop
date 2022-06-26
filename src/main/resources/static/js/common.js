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

function buyProduct() {
    const productId = this.dataset.productid;
    if(confirm("해당 상품을 구매하시겠습니까?")){
        fetch("/orders/" + productId, { // url 입력 및 [options] 값 설정
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(res => {
            return res.json();
        }).then(data => { //응답 결과를 json으로 파싱
            if(data.success == true) {
                location.href = "/shop";
            }else {
                location.href = "/login";
            }
        }).catch(err => { // 오류 발생시 오류를 담아서 보여줌
            alert("상품 구매에 실패하였습니다.");
            console.log('Fetch Error', err);
        });
    }
}

function orderApply() {
    const orderId = this.dataset.orderid;
    const productType = this.dataset.type;
    const buttonElement = this;

    if(buttonElement.parentNode.childNodes[3].textContent == "적용") {
        alert("이미 적용된 상품 입니다.");
        return false;
    }

    if (confirm("해당 상품을 적용하시겠습니까?")) {
        fetch("/orders/" + orderId + "/apply", {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                type : productType
            })
        }).then(res => {
            return res.json();
        }).then(data => { //응답 결과를 json으로 파싱
            if (data.success == true) {
                buttonElement.parentNode.childNodes[3].textContent = "적용";
                alert("상품이 적용되었습니다.");
            } else {
                alert("상품 적용에 실패하였습니다.");
            }
        }).catch(err => { // 오류 발생시 오류를 담아서 보여줌
            alert("상품 구매에 실패하였습니다.");
            console.log('Fetch Error', err);
        });
    }
}

function orderUnapply() {
    const orderId = this.dataset.orderid;
    const buttonElement = this;

    if(buttonElement.parentNode.childNodes[3].textContent == "미적용") {
        alert("이미 미적용된 상품 입니다.");
        return false;
    }

    if (confirm("해당 상품을 미적용하시겠습니까?")) {
        fetch("/orders/" + orderId + "/unapply", {
            method: 'post',
            headers: {
                'Accept': 'application/json'
            }
        }).then(res => {
            return res.json();
        }).then(data => { //응답 결과를 json으로 파싱
            if (data.success == true) {
                buttonElement.parentNode.childNodes[3].textContent = "미적용";
                alert("상품이 미적용되었습니다.");
            } else {
                alert("상품 미적용에 실패하였습니다.");
            }
        }).catch(err => { // 오류 발생시 오류를 담아서 보여줌
            alert("상품 구매에 실패하였습니다.");
            console.log('Fetch Error', err);
        });
    }
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
        alert("상품 구매에 실패하였습니다.");
        console.log('Fetch Error', err);
    });
}


document.addEventListener("DOMContentLoaded", ready)
function ready() {
    //상품 구매
    const buyBtn = document.querySelectorAll(".productBuy");
    for (let i = 0; i < buyBtn.length; i++) {
        buyBtn[i].addEventListener('click', buyProduct);
    }

    //상품 적용
    const applyBtn = document.querySelectorAll(".productApply");
    for (let i = 0; i < applyBtn.length; i++) {
        applyBtn[i].addEventListener('click', orderApply);
    }

    //상품 미적용
    const unApplyBtn = document.querySelectorAll(".productUnApply");
    for (let i = 0; i < unApplyBtn.length; i++) {
        unApplyBtn[i].addEventListener('click', orderUnapply);
    }

    //대댓글 입력 폼
    const comments = document.querySelectorAll(".parentComment");
    for (var i = 0; i < comments.length; i++) {
        comments[i].addEventListener('click', createCommentForm);
    }
    //댓글 삭제 폼
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

            //댓글 내용 변경
            let commentDiv = removeForm.parentNode;
            commentDiv.classList.add("align-self-center");
            commentDiv.innerHTML = "<span>삭제된 댓글 입니다.</span>";

            //댓글 아이콘 변경
            let iconDiv = commentDiv.previousSibling.previousSibling;
            if (iconDiv.nodeName == "DIV") {
                while (iconDiv.hasChildNodes()) {	// 부모노드가 자식이 있는지 여부를 알아낸다
                    iconDiv.removeChild(iconDiv.firstChild);
                }
                let imgElement = document.createElement("img");
                imgElement.setAttribute("src", "https://dummyimage.com/50x50/ced4da/6c757d.jpg");
                imgElement.setAttribute("class", "rounded-circle");
                imgElement.setAttribute("alt", "댓글 아이콘");
                iconDiv.appendChild(imgElement);
            }
            removeForm.remove(); //댓글 삭제 폼 삭제
            alert("댓글이 삭제되었습니다.");
        } else {
            alert("댓글이 삭제에 실패하였습니다.");
        }
    }).catch(err => { // 오류 발생시 오류를 담아서 보여줌
        alert("상품 구매에 실패하였습니다.");
        console.log('Fetch Error', err);
    });
}