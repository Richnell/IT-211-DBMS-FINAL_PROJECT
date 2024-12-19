CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

-- Create the MoodLogs table
CREATE TABLE IF NOT EXISTS MoodLogs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    mood VARCHAR(50) NOT NULL,
    log_date DATE NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);

-- Create the Tips table
CREATE TABLE IF NOT EXISTS Tips (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mood VARCHAR(50) NOT NULL,
    tip TEXT NOT NULL
);

