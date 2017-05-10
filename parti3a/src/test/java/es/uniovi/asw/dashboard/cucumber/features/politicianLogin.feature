Feature: Log into the system

Scenario: Politician is logging in
	Given the politician is in the login page
	And introduces his credentials, "admin@me.com" and "admin" into the login form
	When he pushes the "Log in" button and selects dashboard
	Then he gets redirected to the dashboard view
	
Scenario: Participant is logging in
	Given the participant is in the login page
	And introduces credentials, "user1@me.com" and "user1" into the login form
	When pushes the "Log in" button and selects participants
	Then he gets redirected to the participants view