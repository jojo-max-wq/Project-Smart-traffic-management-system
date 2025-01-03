// Dashboard functionality
document.addEventListener('DOMContentLoaded', function() {
    initializeDashboard();
    loadSensorData();
    loadRulesData();
    initializeCharts();
});

function initializeDashboard() {
    // Tab switching
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            navLinks.forEach(l => l.classList.remove('active'));
            this.classList.add('active');
            const tabId = this.getAttribute('data-tab');
            showTab(tabId);
        });
    });
}

function showTab(tabId) {
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.style.display = 'none';
    });
    const selectedTab = document.getElementById(tabId);
    if (selectedTab) {
        selectedTab.style.display = 'block';
    }
}

// Sensor Management
function loadSensorData() {
    const sensorData = [
        { id: 'S001', location: 'Main Street', status: 'active', lastUpdated: '2 mins ago' },
        { id: 'S002', location: 'Broadway & 5th', status: 'active', lastUpdated: '5 mins ago' },
        { id: 'S003', location: 'Park Avenue', status: 'inactive', lastUpdated: '1 hour ago' }
    ];

    const tbody = document.getElementById('sensorTableBody');
    tbody.innerHTML = '';

    sensorData.forEach(sensor => {
        const row = `
            <tr>
                <td>${sensor.id}</td>
                <td>${sensor.location}</td>
                <td><span class="traffic-light ${sensor.status === 'active' ? 'green' : 'red'}"></span>${sensor.status}</td>
                <td>${sensor.lastUpdated}</td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="editSensor('${sensor.id}')">Edit</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteSensor('${sensor.id}')">Delete</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

function showAddSensorModal() {
    const modal = new bootstrap.Modal(document.getElementById('addSensorModal'));
    modal.show();
}

function addSensor() {
    // Implement sensor addition logic
    loadSensorData();
    bootstrap.Modal.getInstance(document.getElementById('addSensorModal')).hide();
}

// Traffic Rules Management
function loadRulesData() {
    const rulesData = [
        { id: 'R001', description: 'Peak Hour Signal Timing', priority: 'High', status: 'Active' },
        { id: 'R002', 'description': 'Night Mode', priority: 'Medium', status: 'Active' },
        { id: 'R003', 'description': 'Emergency Vehicle Priority', priority: 'High', status: 'Active' }
    ];

    const tbody = document.getElementById('rulesTableBody');
    tbody.innerHTML = '';

    rulesData.forEach(rule => {
        const row = `
            <tr>
                <td>${rule.id}</td>
                <td>${rule.description}</td>
                <td><span class="badge bg-${rule.priority === 'High' ? 'danger' : 'warning'}">${rule.priority}</span></td>
                <td>${rule.status}</td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="editRule('${rule.id}')">Edit</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteRule('${rule.id}')">Delete</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

// Analytics Charts
function initializeCharts() {
    // Traffic Flow Chart
    const trafficCtx = document.getElementById('trafficChart').getContext('2d');
    new Chart(trafficCtx, {
        type: 'line',
        data: {
            labels: ['00:00', '03:00', '06:00', '09:00', '12:00', '15:00', '18:00', '21:00'],
            datasets: [{
                label: 'Traffic Flow',
                data: [30, 25, 45, 85, 65, 75, 95, 45],
                borderColor: '#0056b3',
                tension: 0.4
            }]
        },
        options: {
            responsive: true
        }
    });

    // Peak Hours Chart
    const peakCtx = document.getElementById('peakHoursChart').getContext('2d');
    new Chart(peakCtx, {
        type: 'bar',
        data: {
            labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
            datasets: [{
                label: 'Peak Hour Traffic',
                data: [85, 78, 90, 82, 88],
                backgroundColor: '#28a745'
            }]
        },
        options: {
            responsive: true
        }
    });
}

// System Control Functions
function toggleEmergencyMode() {
    const status = document.getElementById('systemStatus');
    if (status.classList.contains('bg-success')) {
        status.classList.replace('bg-success', 'bg-danger');
        status.textContent = 'Emergency Mode';
    } else {
        status.classList.replace('bg-danger', 'bg-success');
        status.textContent = 'Running';
    }
}

function backupSystem() {
    alert('System backup initiated. This may take a few minutes.');
}