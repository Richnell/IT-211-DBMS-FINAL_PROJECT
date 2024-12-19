-- Create the users table
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

-- Pre-defined values
INSERT INTO Tips (mood, tip) VALUES
('Happy', 'Keep doing what makes you happy!'),
('Happy', 'Share your happiness with someone else.'),
('Happy', 'Laugh, it’s contagious!'),
('Happy', 'Celebrate the little victories in life.'),
('Sad', 'Talk to a friend.'),
('Sad', 'Write down your feelings.'),
('Sad', 'Remember that it\'s okay to feel down sometimes.'),
('Sad', 'Try to focus on something that makes you feel better.'),
('Stressed', 'Take deep breaths.'),
('Stressed', 'Organize your tasks.'),
('Stressed', 'Break your tasks into smaller steps.'),
('Stressed', 'Give yourself a break and come back to it later.'),
('Anxious', 'Focus on your breathing.'),
('Anxious', 'Write down your worries.'),
('Anxious', 'Try grounding exercises (5 things you can see, 4 things you can touch, etc.).'),
('Anxious', 'Remember that you are not alone in this.'),
('Angry', 'Calm yourself with a walk.'),
('Angry', 'Talk to someone you trust.'),
('Angry', 'Take a deep breath and count to 10.'),
('Angry', 'Channel your energy into something productive.'),
('Grateful', 'Write down three things you\'re grateful for today.'),
('Grateful', 'Share your gratitude with someone else.'),
('Grateful', 'Take a moment to reflect on your blessings.'),
('Grateful', 'Carry this feeling with you throughout the day.'),
('Excited', 'Share your excitement with someone you trust.'),
('Excited', 'Take action towards what excites you.'),
('Excited', 'Use your energy to work on something creative.'),
('Excited', 'Celebrate your excitement in a positive way.'),
('Lonely', 'Reach out to someone you care about.'),
('Lonely', 'Engage in a hobby you love.'),
('Lonely', 'Remember that loneliness is a temporary feeling.'),
('Lonely', 'Consider joining a community or group with similar interests.'),
('Confident', 'Use your confidence to help others around you.'),
('Confident', 'Take on a challenge that excites you.'),
('Confident', 'Celebrate your strengths and achievements.'),
('Confident', 'Keep pushing forward with your positive mindset.'),
('Hopeful', 'Visualize your goals and keep moving forward.'),
('Hopeful', 'Share your hope with someone else.'),
('Hopeful', 'Remind yourself that good things are coming.'),
('Hopeful', 'Stay patient and keep a positive mindset.');
