const express = require('express');
const path = require('path');
const app = express();
const port = 3000;

// Serve static files from resources directory
app.use(express.static(path.join(__dirname, 'src/main/resources/static')));
app.use(express.static(path.join(__dirname, 'src/main/resources/templates')));

// Routes
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'src/main/resources/templates/login.html'));
});

app.get('/admin-dashboard', (req, res) => {
    res.sendFile(path.join(__dirname, 'src/main/resources/templates/admin-dashboard.html'));
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});