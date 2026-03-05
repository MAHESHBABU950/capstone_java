Feature: WordPress Website Validation

Scenario: Validate WordPress functionality

Given User launches wordpress website
Then Verify homepage title
When User hover Extend and click Get WordPress
Then Verify header text as "Get WordPress"
When User hover Community and click Photo Directory
And User searches for "nature"
Then Verify photos are displayed