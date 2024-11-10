# Project-Smart-traffic-management-system
# Traffic Management System

A hybrid traffic management solution combining a web interface and Java Swing desktop application for comprehensive traffic monitoring and control.

## Features

### User Roles

- **Admin**
  - Sensor Configuration
  - Traffic Rules Management
  - System Control
  
- **Operator**
  - Real-time Traffic Monitoring
  - Signal Configuration
  - Report Generation
  
- **Public User**
  - View Traffic Updates
  - Report Traffic Issues

### Web Interface

The web interface provides:
- Responsive design for all devices
- Role-based access control
- Real-time dashboard updates
- Interactive traffic monitoring
- User-friendly issue reporting

### Java Desktop Application

The desktop application offers:
- Native performance
- Rich user interface
- Quick access to system controls
- Dedicated monitoring tools
- Secure administrative functions

## Getting Started

### Prerequisites

- Node.js (v14 or higher)
- Java Development Kit (JDK 8 or higher)
- Modern web browser
- npm (comes with Node.js)

### Installation

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Install web dependencies:
```bash
npm install
```

3. Compile the Java application:
```bash
javac TrafficManagementSystem.java
```

### Running the Application

#### Web Interface
```bash
npm run dev
```
The web interface will be available at `http://localhost:5173`

#### Desktop Application
```bash
java TrafficManagementSystem
```

## Usage

### Web Interface

1. Access the web interface through your browser
2. Log in with your credentials
3. Select your user role
4. Navigate through the dashboard using the menu options
5. Use the interface to monitor traffic or report issues

### Desktop Application

1. Launch the Java application
2. Log in with your credentials
3. Use the menu buttons to access different functions
4. Navigate between panels using the intuitive interface

## Project Structure

```
traffic-management-system/
├── src/
│   ├── main.js           # Web application main script
│   ├── style.css         # Global styles
│   └── index.html        # Web interface markup
├── TrafficManagementSystem.java  # Desktop application
├── package.json          # Project dependencies
└── README.md            # Project documentation
```

## Technical Details

### Web Technologies
- Vite.js for development server
- Vanilla JavaScript for functionality
- CSS3 for styling
- Responsive design principles

### Desktop Technologies
- Java Swing for GUI
- AWT for layouts
- Event-driven architecture
- CardLayout for navigation

## Security

- Role-based access control
- Secure login system
- Session management
- Input validation

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request


## Acknowledgments

- Built with Vite.js
- Java Swing GUI toolkit
- Modern web technologies
- Traffic management best practices
