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
    // fetch("/posts/test", { // url 입력 및 [options] 값 설정
    //     method: 'post',
    //     headers: {
    //         'Accept': 'application/json'
    //     }
    // })
    //     .then(res => {
    //         console.log(res);
    //     }) //응답 결과를 json으로 파싱
    //     .then(data => {
    //         // var recommedCount = document.querySelector("#recommendCount");
    //         // recommedCount.setAttribute("value", data.recommedCount);
    //         // [ data.키값 ] 이런 형태로 value 추출 가능
    //         console.log(data); //응답 결과를 console 창에 출력
    //
    //     })
    //     .catch(err => { // 오류 발생시 오류를 담아서 보여줌
    //         console.log('Fetch Error', err);
    //     });

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