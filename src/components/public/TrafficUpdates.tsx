import React from 'react';
import { Box, Typography, List, ListItem, ListItemText, Paper } from '@mui/material';

const TrafficUpdates = () => {
  const updates = [
    { id: 1, message: 'Heavy traffic on Main Street - Delay: 15 mins', time: '10 minutes ago' },
    { id: 2, message: 'Construction work on 5th Avenue - Use alternate route', time: '30 minutes ago' },
    { id: 3, message: 'Accident cleared on Broadway - Traffic normal', time: '1 hour ago' }
  ];

  return (
    <Box>
      <Typography variant="h5" gutterBottom>Live Traffic Updates</Typography>
      <Paper elevation={2}>
        <List>
          {updates.map((update) => (
            <ListItem key={update.id} divider>
              <ListItemText 
                primary={update.message}
                secondary={update.time}
              />
            </ListItem>
          ))}
        </List>
      </Paper>
    </Box>
  );
};

export default TrafficUpdates;