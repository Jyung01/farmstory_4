function updatePass() {
    const pass1 = document.querySelector('input[name="pass1"]').value;
    const pass2 = document.querySelector('input[name="pass2"]').value;
    
    if(pass1 == '') {
        alert('비밀번호를 입력해주세요.');
        return;
    }
    
    if(pass1 != pass2) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }
    
    fetch('/farmstory/user/updatePass.do?pass=' + pass1)
    .then(resp => resp.text())
    .then(result => {
        if(result == 'success') {
            alert('비밀번호가 수정되었습니다.');
        } else {
            alert('비밀번호 수정에 실패했습니다.');
        }
    });
	
}

if (window.location.pathname.includes('myinfo.do') && window.location.search.includes('success')) {
    alert('회원정보가 수정되었습니다.');

    window.history.replaceState({}, document.title, window.location.pathname);
} else if (window.location.pathname.includes('myinfo.do') && window.location.search.includes('fail')) {
	alert('회원정보 수정에 실패했습니다.');
}