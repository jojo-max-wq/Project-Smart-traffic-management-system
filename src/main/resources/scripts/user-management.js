// Form validation and user management functionality
document.addEventListener('DOMContentLoaded', function() {
    loadUsers();
});

function validateForm() {
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const role = document.getElementById('role').value;

    // Username validation
    if (username.length < 3) {
        alert('Username must be at least 3 characters long');
        return false;
    }

    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert('Please enter a valid email address');
        return false;
    }

    // Role validation
    if (!role) {
        alert('Please select a role');
        return false;
    }

    // If validation passes, add user
    addUser({
        username: username,
        email: email,
        role: role
    });

    return false; // Prevent form submission
}

function addUser(user) {
    // In a real application, this would make an API call
    const users = JSON.parse(localStorage.getItem('users') || '[]');
    users.push(user);
    localStorage.setItem('users', JSON.stringify(users));
    loadUsers();
    clearForm();
}

function loadUsers() {
    const users = JSON.parse(localStorage.getItem('users') || '[]');
    const tableBody = document.getElementById('userTableBody');
    tableBody.innerHTML = '';

    users.forEach((user, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>
                <button class="btn btn-sm btn-danger" onclick="deleteUser(${index})">Delete</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

function deleteUser(index) {
    const users = JSON.parse(localStorage.getItem('users') || '[]');
    users.splice(index, 1);
    localStorage.setItem('users', JSON.stringify(users));
    loadUsers();
}

function clearForm() {
    document.getElementById('userForm').reset();
}