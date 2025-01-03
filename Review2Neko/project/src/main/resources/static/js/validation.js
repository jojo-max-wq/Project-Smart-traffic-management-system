// Form validation
function validateLogin(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const userType = document.getElementById('userType').value;

    if (username.length < 3) {
        alert('Username must be at least 3 characters long');
        return false;
    }

    if (password.length < 6) {
        alert('Password must be at least 6 characters long');
        return false;
    }

    // Simulate login - in real app, this would make an API call
    redirectToDashboard(userType);
    return false;
}

function redirectToDashboard(userType) {
    switch(userType) {
        case 'admin':
            window.location.href = '/admin-dashboard';
            break;
        case 'operator':
            window.location.href = '/operator-dashboard';
            break;
        case 'public':
            window.location.href = '/public-dashboard';
            break;
    }
}