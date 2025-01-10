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
import LiveMonitoring from './operator/LiveMonitoring';
import SignalControl from './operator/SignalControl';
import Reports from './operator/Reports';

interface OperatorDashboardProps {
  onLogout: () => void;
}

function OperatorDashboard({ onLogout }: OperatorDashboardProps) {
  const [currentTab, setCurrentTab] = useState(0);

  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setCurrentTab(newValue);
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Operator Dashboard
          </Typography>
          <Button color="inherit" onClick={onLogout}>
            Logout
          </Button>
        </Toolbar>
      </AppBar>
      
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={currentTab} onChange={handleTabChange}>
          <Tab label="Live Monitoring" />
          <Tab label="Signal Control" />
          <Tab label="Reports" />
        </Tabs>
      </Box>

      <Container maxWidth="lg" sx={{ mt: 4 }}>
        {currentTab === 0 && <LiveMonitoring />}
        {currentTab === 1 && <SignalControl />}
        {currentTab === 2 && <Reports />}
      </Container>
    </Box>
  );
}

export default OperatorDashboard;