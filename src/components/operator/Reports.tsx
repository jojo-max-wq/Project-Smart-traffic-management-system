import React from 'react';
import { Box, Typography, Paper, Table, TableBody, TableCell, TableHead, TableRow } from '@mui/material';

const Reports = () => {
  const reports = [
    { id: 1, time: '09:00', location: 'Main St & Broadway', type: 'Heavy Traffic', status: 'Resolved' },
    { id: 2, time: '10:30', location: '5th Ave & Park Rd', type: 'Signal Malfunction', status: 'In Progress' },
    { id: 3, time: '11:45', location: 'West Blvd', type: 'Accident', status: 'Resolved' },
  ];

  return (
    <Box>
      <Typography variant="h5" gutterBottom>Traffic Reports</Typography>
      
      <Paper elevation={2}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Time</TableCell>
              <TableCell>Location</TableCell>
              <TableCell>Type</TableCell>
              <TableCell>Status</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {reports.map((report) => (
              <TableRow key={report.id}>
                <TableCell>{report.time}</TableCell>
                <TableCell>{report.location}</TableCell>
                <TableCell>{report.type}</TableCell>
                <TableCell>{report.status}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Paper>
    </Box>
  );
};

export default Reports;