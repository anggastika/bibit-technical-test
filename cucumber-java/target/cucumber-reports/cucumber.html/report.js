$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("edit-profile.feature");
formatter.feature({
  "line": 2,
  "name": "Edit Profile Account",
  "description": "",
  "id": "edit-profile-account",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@test"
    }
  ]
});
formatter.background({
  "line": 4,
  "name": "Go to profile page",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "user go to homepage stockbit",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "user click entry point login",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "user input username \"USERNAME\" and password \"PASSWORD\"",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "user click button login",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "user skip popup avatar",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "user click profile",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "user check username \"USERNAME\"",
  "keyword": "And "
});
formatter.match({
  "location": "BaseSteps.hompage()"
});
formatter.result({
  "duration": 2681097224,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.entryPointLogin()"
});
formatter.result({
  "duration": 289049345,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "USERNAME",
      "offset": 21
    },
    {
      "val": "PASSWORD",
      "offset": 45
    }
  ],
  "location": "LoginSteps.fieldUsernameAndPassword(String,String)"
});
formatter.result({
  "duration": 1614422663,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.loginButton()"
});
formatter.result({
  "duration": 187314923,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.skipPopUpAvatar()"
});
formatter.result({
  "duration": 7550448074,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.goToProfile()"
});
formatter.result({
  "duration": 1000192466,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "USERNAME",
      "offset": 21
    }
  ],
  "location": "LoginSteps.validateUser(String)"
});
formatter.result({
  "duration": 2358251,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Failed login to stockbit",
  "description": "",
  "id": "edit-profile-account;failed-login-to-stockbit",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 14,
  "name": "user click entry point edit profile",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "user click public profile",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "user click edit button in my public profile page",
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "user edit biography profile \"I\u0027m QA Engineer\"",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "user choose location \"DKI Jakarta\"",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "user click save button edit profile",
  "keyword": "Then "
});
formatter.match({
  "location": "EditProfileSteps.entryPointProfile()"
});
formatter.result({
  "duration": 144926859,
  "status": "passed"
});
formatter.match({
  "location": "EditProfileSteps.editProfile()"
});
formatter.result({
  "duration": 354958889,
  "status": "passed"
});
formatter.match({
  "location": "EditProfileSteps.goToEdit()"
});
formatter.result({
  "duration": 170845206,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "I\u0027m QA Engineer",
      "offset": 29
    }
  ],
  "location": "EditProfileSteps.editBiography(String)"
});
formatter.result({
  "duration": 228222386,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "DKI Jakarta",
      "offset": 22
    }
  ],
  "location": "EditProfileSteps.editLocation(String)"
});
formatter.result({
  "duration": 91524529,
  "status": "passed"
});
formatter.match({
  "location": "EditProfileSteps.clickSaveButtonEditProfile()"
});
formatter.result({
  "duration": 809665064,
  "status": "passed"
});
});