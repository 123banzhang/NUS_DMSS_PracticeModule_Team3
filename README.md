# NUS_DMSS_PracticeModule_Team3

##NUS DMSS practiceModule Team3 group project
###Project Title:  The Oasis

###Overview
New NUS students usually face the problem of not knowing what resources and services are available at the university and how to quickly access relevant information. As a result, new students are likely to experience a lot of inconvenience in an unfamiliar environment, resulting in a lot of unnecessary wasted time.

Therefore, the main objective of this project is to develop an AI-based online chat platform that will enable students to quickly get the exact information they need and help them perform various tasks conveniently. The system allows students to send text or voice content to communicate with the AI. Students can access the site through a browser on a computer or mobile device with an Internet connection.

The system provides powerful natural language processing capabilities that enable the metahuman to accurately recognise a student's text or voice messages and respond as the student expects. 

The system consists of two main components: an online chat system for students and a back-end management system for administrators. The online chat system has an attractive GUI and allows students to customise the personality and appearance of the metahuman, which is presented as a 3D character. The back-end management system allows the administrator to manage student information, metahuman configuration, and statistics and analysis of students’ data.


###Functional Requirements
####User-Side Functional Requirements:
1. The system allows user registration and login: Users should be able to create accounts and log in to the system to use the system functionality.
2. The system allows users to choose a metahuman role: Users should be able to select a metahuman role specific to different topics to access relevant information.
3. The system allows users to chat with the metahuman: Users should be able to ask questions to the metahuman and receive answers regarding courses, news, to-do items, events, and more.
4. The system allows users to engage in text-based conversations with metahuman: Users should have the capability to send text-based messages to metahuman, and metahuman should respond appropriately to user queries and requests.
####Admin-Side Functional Requirements:
1. The system allows admin login: Administrators should be able to log in to the admin console.
2. The system allows administrators to create metahuman roles: Administrators should be able to create new metahuman roles and assign them to specific colleges and majors.
3. The system allows administrators to edit metahuman roles: Administrators should be able to edit the information and functionality of existing metahuman roles.
4. The system allows administrators to delete metahuman roles: Administrators should be able to delete metahuman roles that are no longer needed.
5. The system allows administrators to manage news, course information, to-do items, and events: Administrators should be able to add, edit, and delete relevant information to ensure up-to-date content.
###Non-functional Requirements
1. Performance: The system should run smoothly. Responses from MetaHuman should be prompt. When many concurrent users are using the system and many requests need to be processed, its performance should be strong enough to handle all requests.
2. Scalability: Growing the user base of the system and growing MetaHuman roles created by admin should not cause performance degradation.
3. Availability: Ensure pretty high availability of the system to prevent downtime during critical periods(during user chatting & admin managing).
4. Security: Implement robust security measures to protect user data(user info data and chat data) and maintain privacy. The system should protect http requests and require authentication and authorization for safety.
5. Usability: The user interface should be intuitive and user-friendly.
6. Error Handling: The system should be able to detect and handle errors, which also should not cause data loss or system crashes.


###Effort Estimates
####	Estimated hours = 110 hours / 5 people
####	Work Breakdown Structure (WBS)
 

####	Implementation & Coding Tasks
#####Infrastructure:
1. Set up Development and Collaboration Tools 
2. Set up CI and CD Tools: Github, Github Actions, K8s, Docker
3. Set up other infrastructures: Domain name, DigitalOcean Droplet, OpenAI appKey/AliCloud AI Model Deploy Service
#####Backend:
1. Authorization system, including user login and registration
2. Metahuman display system, including querying and filtering metahuman
3. MetaHuman Conversation System, including AI dialog with metahuman
4. MetaHuman Configuration System, include CRUD metahuman and Modify detailed information about a MetaHuman, such as name, gender, background, motivation, description, etc.
#####Frontend:
1. Login page
2. Register page
3. MetaHuman list & search page
4. MetaHuman Conversation page
5. Admin dashboard
6. User management page
7. Metahuman list (add/delete/search/active/deactive the metahuman)
8. Metahuman configuration page (configure the name/gender/background/ motivation/description… of the metahuman)
9. Static pages (about us, donate)
