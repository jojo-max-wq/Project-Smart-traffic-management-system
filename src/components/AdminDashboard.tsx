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
import SensorConfig from './admin/SensorConfig';
import TrafficRules from './admin/TrafficRules';
import SystemAnalytics from './admin/SystemAnalytics';
import UserManagement from './admin/UserManagement';

interface AdminDashboardProps {
  onLogout: () => void;
}

function AdminDashboard({ onLogout }: AdminDashboardProps) {
  const [currentTab, setCurrentTab] = useState(0);

  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setCurrentTab(newValue);
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Admin Dashboard
          </Typography>
          <Button color="inherit" onClick={onLogout}>
            Logout
          </Button>
        </Toolbar>
      </AppBar>
      
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={currentTab} onChange={handleTabChange}>
          <Tab label="Sensor Configuration" />
          <Tab label="Traffic Rules" />
          <Tab label="System Analytics" />
          <Tab label="User Management" />
        </Tabs>
      </Box>

      <Container maxWidth="lg" sx={{ mt: 4 }}>
        {currentTab === 0 && <SensorConfig />}
        {currentTab === 1 && <TrafficRules />}
        {currentTab === 2 && <SystemAnalytics />}
        {currentTab === 3 && <UserManagement />}
      </Container>
    </Box>
  );
}

export default AdminDashboard;