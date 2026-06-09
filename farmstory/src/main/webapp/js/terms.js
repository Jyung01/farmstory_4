function checkTerms() {
    const termsCheck = document.querySelector('input.terms');
    const privacyCheck = document.querySelector('input.privacy');
    
    if(!termsCheck.checked) {
        alert('사이트 이용약관에 동의해주세요.');
        return false;
    }
    
    if(!privacyCheck.checked) {
        alert('개인정보 취급방침에 동의해주세요.');
        return false;
    }
    
    location.href = '/farmstory/user/register.do';
}