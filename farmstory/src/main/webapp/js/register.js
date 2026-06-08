function checkUserid() {
    const value = document.querySelector('input[name="uid"]').value;
    
    if(value == '') {
        alert('아이디를 입력해주세요!');
        return;
    }
    
    fetch('/farmstory/user/check.do?type=userid&value=' + value)
    .then(resp => resp.text())
    .then(result => {
        const span = document.querySelector('.uidResult');
        if(result == 'yes') {
            span.style.color = 'green';
            span.innerText = '사용 가능한 아이디입니다.';
        } else {
            span.style.color = 'red';
            span.innerText = '이미 사용중인 아이디입니다.';
        }
    });
}

function checkPass() {
    const pass1 = document.querySelector('input[name="pass1"]').value;
    const pass2 = document.querySelector('input[name="pass2"]').value;
    
    const span = document.querySelector('.passResult');
    
    if(pass1 == '' || pass2 == '') {
        return;
    }
    
    if(pass1 == pass2) {
        span.style.color = 'green';
        span.innerText = '비밀번호가 일치합니다.';
    } else {
        span.style.color = 'red';
        span.innerText = '비밀번호가 일치하지 않습니다.';
    }
}
function checkNick() {
    const value = document.querySelector('input[name="nick"]').value;
    
    if(value == '') {
        alert('별명을 입력해주세요!');
        return;
    }
    
    fetch('/farmstory/user/check.do?type=nick&value=' + value)
    .then(resp => resp.text())
    .then(result => {
        const span = document.querySelector('.nickResult');
        if(result == 'yes') {
            span.style.color = 'green';
            span.innerText = '사용 가능한 별명입니다.';
        } else {
            span.style.color = 'red';
            span.innerText = '이미 사용중인 별명입니다.';
        }
    });
}



