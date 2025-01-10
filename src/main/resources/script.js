// JavaScript for enhanced interactivity
document.addEventListener('DOMContentLoaded', function() {
    // Traffic flow simulation
    function updateTrafficFlow() {
        const flowRates = {
            'Main Street': Math.random() * 100,
            'Broadway': Math.random() * 100,
            '5th Avenue': Math.random() * 100
        };
        
        // Update UI elements
        Object.entries(flowRates).forEach(([street, rate]) => {
            const element = document.getElementById(`flow-${street.replace(' ', '-')}`);
            if (element) {
                element.textContent = `${Math.round(rate)}%`;
                element.className = rate > 80 ? 'alert-danger' : 
                                  rate > 60 ? 'alert-warning' : 
                                  'alert-success';
            }
        });
    }

    // Real-time sensor status updates
    function updateSensorStatus() {
        const sensors = document.querySelectorAll('.sensor-status');
        sensors.forEach(sensor => {
            const status = Math.random() > 0.9 ? 'Maintenance Required' : 'Operating';
            sensor.textContent = status;
            sensor.className = `sensor-status ${status === 'Operating' ? 'text-success' : 'text-warning'}`;
        });
    }

    // Initialize charts if Chart.js is available
    if (typeof Chart !== 'undefined') {
        const ctx = document.getElementById('trafficChart');
        if (ctx) {
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: Array.from({length: 24}, (_, i) => `${i}:00`),
                    datasets: [{
                        label: 'Traffic Volume',
                        data: Array.from({length: 24}, () => Math.random() * 100),
                        borderColor: 'rgb(75, 192, 192)',
                        tension: 0.1
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        }
    }

    // Set up periodic updates
    setInterval(updateTrafficFlow, 5000);
    setInterval(updateSensorStatus, 10000);

    // Event listeners for interactive elements
    document.querySelectorAll('.control-button').forEach(button => {
        button.addEventListener('click', function(e) {
            const action = e.target.dataset.action;
            console.log(`Control action triggered: ${action}`);
            // Implement control actions here
        });
    });

    // Form submission handling
    const reportForm = document.getElementById('issue-report-form');
    if (reportForm) {
        reportForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const formData = new FormData(reportForm);
            console.log('Issue report submitted:', Object.fromEntries(formData));
            // Implement form submission logic here
        });
    }
});