function changePassword(){
    let doc = document.changePassword;
    if(doc.password.value !== doc.verifyPassword.value){
        doc.password.classList.add("border");
        doc.password.classList.add("border-danger");
        document.getElementById("errorPassword").classList.remove("hide");
        doc.verifyPassword.classList.add("border");
        doc.verifyPassword.classList.add("border-danger");
        document.getElementById("errorVerifyPassword").classList.remove("hide");
    }else if(doc.currentPassword.value !== "" && doc.password.value !== "" && doc.verifyPassword.value !== ""){
        if(doc.currentPassword.value !== doc.password.value) {
            doc.submit();
        }else{
            alert("현재 비밀번호와 변경할 비밀번호가 일치합니다.");
        }
    }else{
        alert("입력한 비밀번호에 문제가 없는지 확인해 주세요");
    }
}

document.getElementById("verifyButton").addEventListener('click',function (){
    changePassword();
});