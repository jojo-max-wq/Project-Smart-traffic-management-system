import React, { useState } from 'react';
import { 
  Box, 
  Typography, 
  TextField, 
  Button, 
  Paper,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Alert,
  Stack
} from '@mui/material';

const ReportIssue = () => {
  const [formData, setFormData] = useState({
    location: '',
    issueType: '',
    description: ''
  });
  const [submitted, setSubmitted] = useState(false);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log('Issue reported:', formData);
    setSubmitted(true);
    setFormData({ location: '', issueType: '', description: '' });
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | { name?: string; value: unknown }>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name as string]: value
    }));
  };

  return (
    <Box>
      <Typography variant="h5" gutterBottom>Report Traffic Issue</Typography>
      <Paper elevation={2} sx={{ p: 3 }}>
        {submitted && (
          <Alert severity="success" sx={{ mb: 2 }}>
            Issue reported successfully!
          </Alert>
        )}
        <form onSubmit={handleSubmit}>
          <Stack spacing={3}>
            <TextField
              name="location"
              label="Location"
              value={formData.location}
              onChange={handleChange}
              required
              fullWidth
            />
            <FormControl fullWidth required>
              <InputLabel>Issue Type</InputLabel>
              <Select
                name="issueType"
                value={formData.issueType}
                label="Issue Type"
                onChange={handleChange}
              >
                <MenuItem value="accident">Accident</MenuItem>
                <MenuItem value="construction">Construction</MenuItem>
                <MenuItem value="traffic">Heavy Traffic</MenuItem>
                <MenuItem value="other">Other</MenuItem>
              </Select>
            </FormControl>
            <TextField
              name="description"
              label="Description"
              value={formData.description}
              onChange={handleChange}
              multiline
              rows={4}
              required
              fullWidth
            />
            <Button 
              type="submit" 
              variant="contained" 
              color="primary"
              size="large"
            >
              Submit Report
            </Button>
          </Stack>
        </form>
      </Paper>
    </Box>
  );
};

export default ReportIssue;