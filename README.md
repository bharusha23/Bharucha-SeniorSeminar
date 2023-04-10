# Bharucha-SeniorSeminar
My goal was to make a program that generates a schedule for sessions to maximize preferences of the students and put the students in the sessions. 

My approach is to schedule the most popular classes based on a non-weighted counting of each students preferences, putting the most popular classes in at different time slots (so there are the fewest conflicts between the popular classes) and recalculating the popularity every time. Then I add the students by prioritizing putting in people who had sessions at first place, then second, until every student who had a preference that could be fit would be fit. Then I add the students that don't have any preferences or whose preferences couldn't be fit into this time slot by putting them in any session that fits. 

There is some error occuring: 10 students are not being scheduled for the 3rd time slot. I can't understand why, but my best guess is that it has something to do with my quick rearranging of sessions to prevent the Adventure Session for being in the same timeslot twice. However, I cannot figure out what has gone wrong. 

However, I am happy about how most of the code has gone, and if I could work out the bug, I think I've done a good job of maxmizing students' preferences. 
