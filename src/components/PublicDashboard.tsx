import { useState } from 'react';
import { 
  Box, 
  AppBar, 
  Toolbar, 
  Typography, 
  Button, 
  Tabs, 
  Tab,
  Container 
} from '@mui/material';
import TrafficUpdates from './public/TrafficUpdates';
import ReportIssue from './public/ReportIssue';

interface PublicDashboardProps {
  onLogout: () => void;
}

function PublicDashboard({ onLogout }: PublicDashboardProps) {
  const [currentTab, setCurrentTab] = useState(0);

  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setCurrentTab(newValue);
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Traffic Updates
          </Typography>
          <Button color="inherit" onClick={onLogout}>
            Logout
          </Button>
        </Toolbar>
      </AppBar>
      
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={currentTab} onChange={handleTabChange}>
          <Tab label="Traffic Updates" />
          <Tab label="Report Issue" />
        </Tabs>
      </Box>

      <Container maxWidth="lg" sx={{ mt: 4 }}>
        {currentTab === 0 && <TrafficUpdates />}
        {currentTab === 1 && <ReportIssue />}
      </Container>
    </Box>
  );
}

export default PublicDashboard;