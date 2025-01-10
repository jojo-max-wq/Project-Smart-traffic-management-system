import React, { useState } from 'react';
import { Box, Typography, Paper, Grid, Button, FormControl, InputLabel, Select, MenuItem } from '@mui/material';

const SignalControl = () => {
  const [selectedIntersection, setSelectedIntersection] = useState('');
  const [signalMode, setSignalMode] = useState('auto');

  return (
    <Box>
      <Typography variant="h5" gutterBottom>Signal Control</Typography>
      
      <Grid container spacing={3}>
        <Grid item xs={12} md={6}>
          <Paper elevation={2} sx={{ p: 2 }}>
            <FormControl fullWidth sx={{ mb: 2 }}>
              <InputLabel>Intersection</InputLabel>
              <Select
                value={selectedIntersection}
                label="Intersection"
                onChange={(e) => setSelectedIntersection(e.target.value)}
              >
                <MenuItem value="main-broadway">Main St & Broadway</MenuItem>
                <MenuItem value="fifth-park">5th Ave & Park Rd</MenuItem>
                <MenuItem value="west-east">West Blvd & East Ave</MenuItem>
              </Select>
            </FormControl>

            <FormControl fullWidth sx={{ mb: 2 }}>
              <InputLabel>Mode</InputLabel>
              <Select
                value={signalMode}
                label="Mode"
                onChange={(e) => setSignalMode(e.target.value)}
              >
                <MenuItem value="auto">Automatic</MenuItem>
                <MenuItem value="manual">Manual Control</MenuItem>
                <MenuItem value="emergency">Emergency Mode</MenuItem>
              </Select>
            </FormControl>

            <Button variant="contained" color="primary" fullWidth>
              Apply Changes
            </Button>
          </Paper>
        </Grid>

        <Grid item xs={12} md={6}>
          <Paper elevation={2} sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>Current Status</Typography>
            <Typography>Mode: {signalMode.charAt(0).toUpperCase() + signalMode.slice(1)}</Typography>
            <Typography>Last Updated: {new Date().toLocaleTimeString()}</Typography>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};

export default SignalControl;