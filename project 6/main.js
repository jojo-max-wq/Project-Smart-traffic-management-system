// Form toggle functionality
function toggleForms() {
    const loginForm = document.getElementById('loginForm');
    const signupForm = document.getElementById('signupForm');
    loginForm.classList.toggle('hidden');
    signupForm.classList.toggle('hidden');
}

// Handle login form submission
function handleLogin(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('userRole').value;

    // Hide all forms and dashboards first
    document.getElementById('loginForm').classList.add('hidden');
    document.getElementById('signupForm').classList.add('hidden');
    document.getElementById('adminDashboard').classList.add('hidden');
    document.getElementById('operatorDashboard').classList.add('hidden');
    document.getElementById('publicDashboard').classList.add('hidden');

    // Show appropriate dashboard based on role
    switch(role) {
        case 'admin':
            document.getElementById('adminDashboard').classList.remove('hidden');
            break;
        case 'operator':
            document.getElementById('operatorDashboard').classList.remove('hidden');
            break;
        case 'public':
            document.getElementById('publicDashboard').classList.remove('hidden');
            break;
    }
}

// Handle signup form submission
function handleSignup(event) {
    event.preventDefault();
    const username = document.getElementById('newUsername').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('newPassword').value;
    const role = document.getElementById('newUserRole').value;

    // Here you would typically make an API call to create a new user
    console.log('Signup attempt:', { username, email, role });
    
    alert('Account created successfully! Please login.');
    toggleForms();
}

// Dashboard section handling
function showSection(section) {
    const contentDiv = document.getElementById('dashboardContent') || 
                      document.getElementById('operatorContent') || 
                      document.getElementById('publicContent');
    
    let content = '';
    
    switch(section) {
        case 'sensorConfig':
            content = `
                <h3>Sensor Configuration</h3>
                <div class="sensor-grid">
                    <div class="sensor-item">
                        <h4>Sensor 1</h4>
                        <input type="text" placeholder="Location">
                        <select>
                            <option>Active</option>
                            <option>Inactive</option>
                        </select>
                        <button>Update</button>
                    </div>
                    <div class="sensor-item">
                        <h4>Sensor 2</h4>
                        <input type="text" placeholder="Location">
                        <select>
                            <option>Active</option>
                            <option>Inactive</option>
                        </select>
                        <button>Update</button>
                    </div>
                </div>
            `;
            break;
        case 'trafficRules':
            content = `
                <h3>Traffic Rules Management</h3>
                <div class="rules-list">
                    <div class="rule-item">
                        <input type="text" placeholder="Rule Name">
                        <textarea placeholder="Rule Description"></textarea>
                        <button>Save Rule</button>
                    </div>
                </div>
            `;
            break;
        case 'monitoring':
            content = `
                <h3>Live Traffic Monitoring</h3>
                <div class="monitoring-grid">
                    <div class="traffic-camera">
                        <h4>Junction A</h4>
                        <div class="camera-feed">Camera Feed Placeholder</div>
                        <div class="controls">
                            <button>Adjust Signal</button>
                            <button>Emergency Override</button>
                        </div>
                    </div>
                </div>
            `;
            break;
        case 'trafficUpdates':
            content = `
                <h3>Current Traffic Updates</h3>
                <div class="updates-list">
                    <div class="update-item">
                        <h4>Main Street Junction</h4>
                        <p>Heavy traffic due to construction work</p>
                        <span class="timestamp">Updated 5 mins ago</span>
                    </div>
                </div>
            `;
            break;
        case 'reportIssue':
            content = `
                <h3>Report Traffic Issue</h3>
                <form class="report-form">
                    <input type="text" placeholder="Location">
                    <select>
                        <option>Signal Malfunction</option>
                        <option>Accident</option>
                        <option>Road Damage</option>
                    </select>
                    <textarea placeholder="Description"></textarea>
                    <button type="submit">Submit Report</button>
                </form>
            `;
            break;
        default:
            content = '<h3>Select a section from the menu</h3>';
    }
    
    contentDiv.innerHTML = content;
}