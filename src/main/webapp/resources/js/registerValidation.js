document.getElementById('register-form-submit').onsubmit = registrationFormValidation();
document.getElementsByName('outcome').onsubmit = checkOutcomeIn();

function registrationFormValidation() {
    if (document.getElementById('password').value !== document.getElementById('confirm-password').value) {
        alert("Passwords do not match!");
        return false;
    }
    if (document.getElementById('security-number').value !== document.getElementById('confirm-security-number').value) {
        alert("Security numbers do not match!");
        return false;
    }
    return true;
}

function checkOutcomeIn() {
    alert("HI!!!");

}