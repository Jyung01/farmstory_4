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



function sendEmail() {
    const email = document.querySelector('input[name="email"]').value;
    
    if(email == '') {
        alert('이메일을 입력해주세요!');
        return;
    }
    
    // 이메일 중복확인 먼저!
    fetch('/farmstory/user/check.do?type=email&value=' + email)
    .then(resp => resp.text())
    .then(result => {
        if(result == 'no') {
            const span = document.querySelector('.emailResult');
            span.style.color = 'red';
            span.innerText = '이미 사용중인 이메일입니다.';
        } else {
            // 중복 아니면 인증번호 발송
            fetch('/farmstory/user/email.do?email=' + email)
            .then(resp => resp.text())
            .then(result => {
                if(result == 'sent') {
                    document.querySelector('.auth').style.display = 'block';
                    const span = document.querySelector('.emailResult');
                    span.style.color = 'green';
                    span.innerText = '인증번호가 발송되었습니다.';
                }
            });
        }
    });
}

function checkAuth() {
    const auth = document.querySelector('input[name="auth"]').value;
    
    if(auth == '') {
        alert('인증번호를 입력해주세요!');
        return;
    }
    
    fetch('/farmstory/user/checkAuth.do?auth=' + auth)
    .then(resp => resp.text())
    .then(result => {
        const span = document.querySelector('.authResult');
        if(result == 'yes') {
            span.style.color = 'green';
            span.innerText = '인증이 완료되었습니다.';
        } else {
            span.style.color = 'red';
            span.innerText = '인증번호가 틀렸습니다.';
        }
    });
}


function validateForm() {
    const userid = document.querySelector('input[name="uid"]').value;
    const pass1 = document.querySelector('input[name="pass1"]').value;
    const pass2 = document.querySelector('input[name="pass2"]').value;
    const name = document.querySelector('input[name="name"]').value;
    const nick = document.querySelector('input[name="nick"]').value;
    const email = document.querySelector('input[name="email"]').value;
    const hp = document.querySelector('input[name="hp"]').value;
    
    if(userid == '') { alert('아이디를 입력해주세요.'); return false; }
    if(document.querySelector('.uidResult').style.color != 'green') { alert('아이디 중복확인을 해주세요.'); return false; }
    if(pass1 == '') { alert('비밀번호를 입력해주세요.'); return false; }
    if(pass1 != pass2) { alert('비밀번호가 일치하지 않습니다.'); return false; }
    if(name == '') { alert('이름을 입력해주세요.'); return false; }
    if(nick == '') { alert('별명을 입력해주세요.'); return false; }
    if(document.querySelector('.nickResult').style.color != 'green') { alert('별명 중복확인을 해주세요.'); return false; }
    if(email == '') { alert('이메일을 입력해주세요.'); return false; }
    if(document.querySelector('.authResult').style.color != 'green') { alert('이메일 인증을 완료해주세요.'); return false; }
    if(hp == '') { alert('휴대폰을 입력해주세요.'); return false; }
    
    return true;
}
function searchZip() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector('input[name="zip"]').value = data.zonecode;
            document.querySelector('input[name="addr1"]').value = data.address;
            document.querySelector('input[name="addr2"]').focus();
        }
    }).open();
}

document.addEventListener('DOMContentLoaded', function () {

    const btnNick = document.getElementById('btnNickCheck');

    if (!btnNick) return;

    btnNick.addEventListener('click', function () {

        const nick = document.querySelector('input[name="nick"]').value.trim();
        const resultSpan = document.querySelector('.nickResult');

        if (!nick) {
            resultSpan.innerText = "닉네임을 입력하세요.";
            resultSpan.style.color = "red";
            return;
        }

        fetch(`${path}/user/check.do?type=nick&value=` + encodeURIComponent(nick))
            .then(resp => resp.text())
            .then(data => {

                if (data == 1) {
                    resultSpan.innerText = "❌ 사용 불가능한 닉네임입니다.";
                    resultSpan.style.color = "red";
                } else {
                    resultSpan.innerText = "✅ 사용 가능한 닉네임입니다.";
                    resultSpan.style.color = "green";
                }

            });

    });

});


