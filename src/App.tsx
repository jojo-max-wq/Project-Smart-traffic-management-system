import { useState } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import AdminDashboard from './components/AdminDashboard';
import OperatorDashboard from './components/OperatorDashboard';
import PublicDashboard from './components/PublicDashboard';
import { ThemeProvider, createTheme } from '@mui/material';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2',
    },
    secondary: {
      main: '#dc004e',
    },
  },
});

function App() {
  const [user, setUser] = useState<{ username: string; role: string } | null>(null);

  const handleLogin = (username: string, password: string) => {
    // Simple role-based login logic
    if (username.startsWith('admin')) {
      setUser({ username, role: 'admin' });
    } else if (username.startsWith('operator')) {
      setUser({ username, role: 'operator' });
    } else {
      setUser({ username, role: 'public' });
    }
  };

  const handleLogout = () => {
    setUser(null);
  };

  return (
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <Routes>
          <Route 
            path="/" 
            element={
              !user ? (
                <Login onLogin={handleLogin} />
              ) : (
                <Navigate 
                  to={`/${user.role}`} 
                  replace 
                />
              )
            } 
          />
          <Route 
            path="/admin/*" 
            element={
              user?.role === 'admin' ? (
                <AdminDashboard onLogout={handleLogout} />
              ) : (
                <Navigate to="/" replace />
              )
            } 
          />
          <Route 
            path="/operator/*" 
            element={
              user?.role === 'operator' ? (
                <OperatorDashboard onLogout={handleLogout} />
              ) : (
                <Navigate to="/" replace />
              )
            } 
          />
          <Route 
            path="/public/*" 
            element={
              user?.role === 'public' ? (
                <PublicDashboard onLogout={handleLogout} />
              ) : (
                <Navigate to="/" replace />
              )
            } 
          />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;